package com.lft.miaosha.controller;

import com.lft.miaosha.annotation.AccessLimit;
import com.lft.miaosha.common.constant.RedisConstants;
import com.lft.miaosha.common.key.impl.MSGoodsKeyPrefix;
import com.lft.miaosha.common.key.impl.MSOrderKeyPrefix;
import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.common.util.Md5Util;
import com.lft.miaosha.common.util.UuidUtil;
import com.lft.miaosha.entity.mo.MiaoshaMessageMo;
import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.entity.po.MiaoshaOrder;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.service.GoodsService;
import com.lft.miaosha.service.MiaoshaGoodsService;
import com.lft.miaosha.service.MiaoshaOrderService;
import com.lft.miaosha.service.MqSenderService;
import com.lft.miaosha.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Name:      GoodsController
 * Package Name:    com.lft.miaosha.controller
 * <p>
 * Function: 		A {@code GoodsController} object With Some FUNCTION.
 * Date:            2021-05-15 16:06
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Controller
@RequestMapping ("miaosha")
public class MiaoshaOrderController implements InitializingBean {
    private static Logger log = LoggerFactory.getLogger(MiaoshaOrderController.class);
    
    private Map<Long, Boolean> localOverMap = new HashMap<>();
    
    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;
    @Autowired
    private MiaoshaOrderService miaoshaOrderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MqSenderService mqSenderService;
    
    /**
     * Spring Boot 初始化的时候 执行
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<MiaoshaGoods> miaoshaGoodsList = miaoshaGoodsService.getAllMiaoshaGoods();
        if (miaoshaGoodsList == null) {
            return;
        }
        for (MiaoshaGoods miaoshaGoods : miaoshaGoodsList) {
            redisService
                    .set(MSGoodsKeyPrefix.KEY_PREFIX_GET_MSGOODS_STOCK_BY_GID, "" + miaoshaGoods.getGoodsId(), miaoshaGoods
                            .getStockCount());
            localOverMap.put(miaoshaGoods.getGoodsId(), false);
        }
    }
    
    /**
     * JMeter 压测 5000*10 三次
     * QPS 5210.4 个/s
     * QPS 5405.8 个/s
     * QPS 5776.8 个/s
     * <p>
     * GET POST 有什么区别？
     * GET是从服务端请求数据，具有幂等性
     * <a href="/delete?id=1212"></a>
     * POST是向服务端提交数据，不具有幂等性
     * @param model
     * @param miaoshaUser
     * @param goodsId
     * @return
     */
    @RequestMapping (value = "{path}/execute/miaosha", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public R doMiaosha2(Model model, MiaoshaUser miaoshaUser, @RequestParam ("goodsId") Long goodsId, @PathVariable ("path") String path) {
        if (miaoshaUser == null) {
            return R.ERROR().code(ResultCode.LOGIN_ERROR.getCode())
                    .message(ResultCode.LOGIN_ERROR.getMessage());
        }
        // 将用户添加到 model 属性中
        model.addAttribute("user", miaoshaUser);
        
        // 验证 path
        Boolean check = miaoshaOrderService.checkPath(miaoshaUser, goodsId, path);
        if (!check) {
            return R.ERROR().code(ResultCode.MIAOSHA_PATH_ERROR.getCode()).message(ResultCode.MIAOSHA_PATH_ERROR.getMessage());
        }
        
        // 内存标记，减少 Redis 访问
        Boolean localOver = localOverMap.get(goodsId);
        if (localOver) {
            return R.ERROR().code(ResultCode.MIAOSHA_OVER_ERROR.getCode()).message(ResultCode.MIAOSHA_OVER_ERROR.getMessage());
        }
        
        // log.info("==================== 执行缓存中减库存 ====================");
        // 先将缓存中的库存减1
        Long newStock = redisService.decr(MSGoodsKeyPrefix.KEY_PREFIX_GET_MSGOODS_STOCK_BY_GID, "" + goodsId);
        if (newStock < 0) {
            localOverMap.put(goodsId, true);
            return R.ERROR().code(ResultCode.MIAOSHA_OVER_ERROR.getCode()).message(ResultCode.MIAOSHA_OVER_ERROR.getMessage());
        }
        
        // 判断是否已经秒杀到了
        // log.info("==================== 查看是否有订单 ====================");
        MiaoshaOrder order = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(miaoshaUser.getId(), goodsId);
        if (order != null) {
            return R.ERROR().code(ResultCode.ORDER_REPEAT_ERROR.getCode())
                    .message(ResultCode.ORDER_REPEAT_ERROR.getMessage());
        }
        
        // 入队
        // log.info("==================== 执行 RabbitMQ 入队 ====================");
        MiaoshaMessageMo miaoshaMessageMo = new MiaoshaMessageMo();
        miaoshaMessageMo.setUser(miaoshaUser);
        miaoshaMessageMo.setGoodsId(goodsId);
        mqSenderService.sendMiaoshaMessage(miaoshaMessageMo);
        
        // 返回排队中
        // log.info("==================== 返回排队中结果 ====================");
        return R.OK().code(ResultCode.QUEUE_UP.getCode()).message(ResultCode.QUEUE_UP.getMessage());
    }
    
    /**
     * 轮询请求秒杀结果
     * 返回 orderId 表示成功
     * 返回 -1 表示秒杀失败
     * 返回 0 表示排队中
     * @param model
     * @param miaoshaUser
     * @param goodsId
     * @return
     */
    @AccessLimit (seconds = 5, maxCount = 10, loginRequired = true)
    @GetMapping ("get/miaosha/result")
    @ResponseBody
    public R getMiaoshaResult(Model model, MiaoshaUser miaoshaUser, @RequestParam ("goodsId") Long goodsId) {
        if (miaoshaUser == null) {
            return R.ERROR().code(ResultCode.LOGIN_ERROR.getCode())
                    .message(ResultCode.LOGIN_ERROR.getMessage());
        }
        // 将用户添加到 model 属性中
        model.addAttribute("user", miaoshaUser);
        
        // 判断是否已经秒杀到了
        Long result = miaoshaOrderService.getMiaoshaResult(miaoshaUser.getId(), goodsId);
        
        return R.OK().data("result", result);
    }
    
    @AccessLimit (seconds = 5, maxCount = 1, loginRequired = true)
    @RequestMapping (value = "get/miaosha/path", method = RequestMethod.GET)
    @ResponseBody
    public R getMiaoshaPath(Model model, MiaoshaUser miaoshaUser, @RequestParam ("goodsId") Long goodsId,
                            @RequestParam (value = "verifyCode", defaultValue = "0", required = false) String verifyCode,
                            HttpServletRequest request) {
        if (miaoshaUser == null) {
            return R.ERROR().code(ResultCode.LOGIN_ERROR.getCode())
                    .message(ResultCode.LOGIN_ERROR.getMessage());
        }
        
        // 校验验证码
        String verifyCodeFromCache = redisService
                .get(MSOrderKeyPrefix.KEY_PREFIX_GET_VERIFYCODE_BY_UID_GID, "" + miaoshaUser
                        .getId() + RedisConstants.SPILT + goodsId, String.class);
        // 验证码过期了
        if (verifyCodeFromCache == null) {
            return R.ERROR().code(ResultCode.MIAOSHAO_VERDUE_VERIFY_CODE_ERROR.getCode())
                    .message(ResultCode.MIAOSHAO_VERDUE_VERIFY_CODE_ERROR.getMessage());
        }
        // 验证码错误
        if (!verifyCodeFromCache.equals(verifyCode)) {
            return R.ERROR().code(ResultCode.MIAOSHAO_WRONG_VERIFY_CODE_ERROR.getCode())
                    .message(ResultCode.MIAOSHAO_WRONG_VERIFY_CODE_ERROR.getMessage());
        }
        //
        String str = Md5Util.md5(UuidUtil.getToken() + "0123456789");
        redisService.set(MSOrderKeyPrefix.KEY_PREFIX_GET_MSPATH_BY_GID, "" + miaoshaUser.getId() + RedisConstants.SPILT + goodsId, str);
        return R.OK().data("path", str);
    }
    
    @RequestMapping (value = "get/verifyCode", method = RequestMethod.GET)
    @ResponseBody
    public R getVerifyCode(HttpServletResponse response, MiaoshaUser miaoshaUser, @RequestParam ("goodsId") Long goodsId) {
        if (miaoshaUser == null) {
            return R.ERROR().code(ResultCode.LOGIN_ERROR.getCode())
                    .message(ResultCode.LOGIN_ERROR.getMessage());
        }
        ServletOutputStream outputStream = null;
        try {
            BufferedImage image = miaoshaOrderService.createMiaoshaVerifyCode(miaoshaUser, goodsId);
            outputStream = response.getOutputStream();
            ImageIO.write(image, "JPEG", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

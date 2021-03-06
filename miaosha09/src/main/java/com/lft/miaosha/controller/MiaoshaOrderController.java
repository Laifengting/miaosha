package com.lft.miaosha.controller;

import com.lft.miaosha.common.key.impl.GoodsKeyPrefix;
import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * Spring Boot ?????????????????? ??????
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
                    .set(GoodsKeyPrefix.KEY_PREFIX_GET_GOODS_STOCK_BY_GID, "" + miaoshaGoods.getGoodsId(), miaoshaGoods.getStockCount());
            localOverMap.put(miaoshaGoods.getGoodsId(), false);
        }
    }
    
    /**
     * JMeter ?????? 5000*10 ??????
     * QPS 5210.4 ???/s
     * QPS 5405.8 ???/s
     * QPS 5776.8 ???/s
     * <p>
     * GET POST ??????????????????
     * GET?????????????????????????????????????????????
     * <a href="/delete?id=1212"></a>
     * POST????????????????????????????????????????????????
     * @param model
     * @param miaoshaUser
     * @param goodsId
     * @return
     */
    @RequestMapping (value = "execute/miaosha", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public R doMiaosha2(Model model, MiaoshaUser miaoshaUser, @RequestParam ("goodsId") Long goodsId) {
        if (miaoshaUser == null) {
            return R.ERROR().code(ResultCode.LOGIN_ERROR.getCode())
                    .message(ResultCode.LOGIN_ERROR.getMessage());
        }
        // ?????????????????? model ?????????
        model.addAttribute("user", miaoshaUser);
        
        // ????????????????????? Redis ??????
        Boolean localOver = localOverMap.get(goodsId);
        if (localOver) {
            return R.ERROR().code(ResultCode.MIAOSHA_OVER_ERROR.getCode()).message(ResultCode.MIAOSHA_OVER_ERROR.getMessage());
        }
        
        // log.info("==================== ???????????????????????? ====================");
        // ???????????????????????????1
        Long newStock = redisService.decr(GoodsKeyPrefix.KEY_PREFIX_GET_GOODS_STOCK_BY_GID, "" + goodsId);
        if (newStock < 0) {
            localOverMap.put(goodsId, true);
            return R.ERROR().code(ResultCode.MIAOSHA_OVER_ERROR.getCode()).message(ResultCode.MIAOSHA_OVER_ERROR.getMessage());
        }
        
        // ??????????????????????????????
        // log.info("==================== ????????????????????? ====================");
        MiaoshaOrder order = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(miaoshaUser.getId(), goodsId);
        if (order != null) {
            return R.ERROR().code(ResultCode.ORDER_REPEAT_ERROR.getCode())
                    .message(ResultCode.ORDER_REPEAT_ERROR.getMessage());
        }
        
        // ??????
        // log.info("==================== ?????? RabbitMQ ?????? ====================");
        MiaoshaMessageMo miaoshaMessageMo = new MiaoshaMessageMo();
        miaoshaMessageMo.setUser(miaoshaUser);
        miaoshaMessageMo.setGoodsId(goodsId);
        mqSenderService.sendMiaoshaMessage(miaoshaMessageMo);
        
        // ???????????????
        // log.info("==================== ????????????????????? ====================");
        return R.OK().code(ResultCode.QUEUE_UP.getCode()).message(ResultCode.QUEUE_UP.getMessage());
    }
    
    /**
     * ????????????????????????
     * ?????? orderId ????????????
     * ?????? -1 ??????????????????
     * ?????? 0 ???????????????
     * @param model
     * @param miaoshaUser
     * @param goodsId
     * @return
     */
    @GetMapping ("get/miaosha/result")
    @ResponseBody
    public R getMiaoshaResult(Model model, MiaoshaUser miaoshaUser, @RequestParam ("goodsId") Long goodsId) {
        if (miaoshaUser == null) {
            return R.ERROR().code(ResultCode.LOGIN_ERROR.getCode())
                    .message(ResultCode.LOGIN_ERROR.getMessage());
        }
        // ?????????????????? model ?????????
        model.addAttribute("user", miaoshaUser);
        
        // ??????????????????????????????
        Long result = miaoshaOrderService.getMiaoshaResult(miaoshaUser.getId(), goodsId);
        
        return R.OK().data("result", result);
    }
}

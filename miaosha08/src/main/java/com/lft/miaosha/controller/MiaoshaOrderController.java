package com.lft.miaosha.controller;

import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.entity.po.MiaoshaOrder;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.entity.vo.OrderInfoVo;
import com.lft.miaosha.service.GoodsService;
import com.lft.miaosha.service.MiaoshaGoodsService;
import com.lft.miaosha.service.MiaoshaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class MiaoshaOrderController {
    
    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;
    @Autowired
    private MiaoshaOrderService miaoshaOrderService;
    @Autowired
    private GoodsService goodsService;
    
    /**
     * JMeter 压测 5000*10
     * QPS 296.4 个/s
     * @param model
     * @param miaoshaUser
     * @param goodsId
     * @return
     */
    @RequestMapping ("do/miaosha")
    @Transactional
    public String doMiaosha(Model model, MiaoshaUser miaoshaUser, @RequestParam ("goodsId") Long goodsId) {
        if (miaoshaUser == null) {
            return "login";
        }
        // 将用户添加到 model 属性中
        model.addAttribute("user", miaoshaUser);
        
        // 获取商品库存
        MiaoshaGoods miaoshaGoods = miaoshaGoodsService.getMiaoshaGoodsByGoodsId(goodsId);
        Integer stockCount = miaoshaGoods.getStockCount();
        if (stockCount <= 0) {
            model.addAttribute("errmsg", ResultCode.NO_STOCK_ERROR.getMessage());
            return "miaosha_fail";
        }
        
        // 判断是否已经秒杀到了
        MiaoshaOrder order = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(miaoshaUser.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", ResultCode.ORDER_REPEAT_ERROR.getMessage());
            return "miaosha_fail";
        }
        
        // 执行秒杀
        OrderInfoVo orderInfoVo = miaoshaOrderService.miaosha(miaoshaUser, miaoshaGoods);
        
        // 获取 GoodVO 详情
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(miaoshaGoods.getGoodsId());
        model.addAttribute("orderInfo", orderInfoVo);
        model.addAttribute("goods", goodsVo);
        return "order_detail";
    }
    
    /**
     * GET POST 有什么区别？
     * GET是从服务端请求数据，具有幂等性
     * <a href="/delete?id=1212"></a>
     * POST是向服务端提交数据，不具有幂等性
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
        // 将用户添加到 model 属性中
        model.addAttribute("user", miaoshaUser);
        
        // 获取商品库存
        MiaoshaGoods miaoshaGoods = miaoshaGoodsService.getMiaoshaGoodsByGoodsId(goodsId);
        Integer stockCount = miaoshaGoods.getStockCount();
        if (stockCount <= 0) {
            return R.ERROR().code(ResultCode.MIAOSHA_OVER_ERROR.getCode())
                    .message(ResultCode.MIAOSHA_OVER_ERROR.getMessage());
        }
        
        // 判断是否已经秒杀到了
        MiaoshaOrder order = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(miaoshaUser.getId(), goodsId);
        if (order != null) {
            return R.ERROR().code(ResultCode.ORDER_REPEAT_ERROR.getCode())
                    .message(ResultCode.ORDER_REPEAT_ERROR.getMessage());
        }
        
        // 执行秒杀
        OrderInfoVo orderInfoVo = miaoshaOrderService.miaosha(miaoshaUser, miaoshaGoods);
        
        return R.OK().data("orderId", orderInfoVo.getId());
    }
}

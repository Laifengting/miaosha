package com.lft.miaosha.controller;

import com.lft.miaosha.common.exception.ExceptionCode;
import com.lft.miaosha.common.key.impl.AddressKeyPrefix;
import com.lft.miaosha.common.key.impl.GoodsKeyPrefix;
import com.lft.miaosha.common.key.impl.OrderKeyPrefix;
import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.entity.po.Address;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.po.OrderInfo;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.exception.MsException;
import com.lft.miaosha.service.AddressService;
import com.lft.miaosha.service.GoodsService;
import com.lft.miaosha.service.OrderInfoService;
import com.lft.miaosha.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping ("order")
public class OrderInfoController {
    
    @Autowired
    private OrderInfoService orderInfoService;
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private AddressService addressService;
    
    @RequestMapping ("get/order/info/by/{orderId}")
    @ResponseBody
    public R getOrderInfoById(Model model, MiaoshaUser miaoshaUser, @PathVariable ("orderId") Long orderId) {
        if (miaoshaUser == null) {
            return R.ERROR().code(ResultCode.LOGIN_ERROR.getCode())
                    .message(ResultCode.LOGIN_ERROR.getMessage());
        }
        // ??????????????????
        OrderInfo orderInfo = redisService.get(OrderKeyPrefix.KEY_PREFIX_GET_ORDERINFO_BY_OID, "" + orderId, OrderInfo.class);
        
        GoodsVo goodsVo = null;
        Address address = null;
        
        // ???????????????
        if (orderInfo != null) {
            goodsVo = getGoodsVoFromCacheOrDbAndSetToCache(orderInfo);
            address = getAddressFromCacheOrDbAndSetToCache(orderInfo);
            return R.OK().data("user", miaoshaUser).data("orderInfo", orderInfo).data("goodsVo", goodsVo).data("address", address);
        }
        // ???????????????????????????????????????????????????
        orderInfo = orderInfoService.getOrderInfoById(orderId);
        // ?????????????????????
        if (orderInfo != null) {
            // ????????????????????????????????????
            redisService.set(OrderKeyPrefix.KEY_PREFIX_GET_ORDERINFO_BY_OID, "" + orderId, orderInfo);
            // ?????? ???????????????
            goodsVo = getGoodsVoFromCacheOrDbAndSetToCache(orderInfo);
            address = getAddressFromCacheOrDbAndSetToCache(orderInfo);
        }
        // ?????????
        return R.OK().data("user", miaoshaUser).data("orderInfo", orderInfo).data("goodsVo", goodsVo).data("address", address);
    }
    
    private GoodsVo getGoodsVoFromCacheOrDbAndSetToCache(OrderInfo orderInfo) {
        // ???????????????
        GoodsVo goodsVo = redisService.get(GoodsKeyPrefix.KEY_PREFIX_GET_GOODSVO_BY_GID, "" + orderInfo.getGoodsId(), GoodsVo.class);
        // ?????????????????????
        if (goodsVo == null) {
            // ??????????????????
            goodsVo = goodsService.getGoodsVoByGoodsId(orderInfo.getGoodsId());
            // ?????????????????????????????????
            if (goodsVo == null) {
                throw new MsException(ExceptionCode.NULL_OBJECT_EXCEPTION);
            }
            // ????????????????????????????????????
            redisService.set(GoodsKeyPrefix.KEY_PREFIX_GET_GOODSVO_BY_GID, "" + orderInfo.getGoodsId(), goodsVo);
            // ????????????
            return goodsVo;
        } else {
            // ??????????????????????????????
            return goodsVo;
        }
    }
    
    private Address getAddressFromCacheOrDbAndSetToCache(OrderInfo orderInfo) {
        // ???????????????
        Address address = redisService
                .get(AddressKeyPrefix.KEY_PREFIX_GET_ADDRESS_BY_UID_AID, "" +
                        orderInfo.getUserId() + ":" +
                        orderInfo.getDeliveryAddressId(), Address.class);
        // ?????????????????????
        if (address != null) {
            return address;
        }
        // ??????????????????
        address = addressService.getAddressByUserIdAndAddressId(orderInfo.getUserId(), orderInfo.getDeliveryAddressId());
        
        // ???????????????
        if (address != null) {
            // ???????????????
            redisService
                    .set(AddressKeyPrefix.KEY_PREFIX_GET_ADDRESS_BY_UID_AID, "" +
                            orderInfo.getUserId() + ":" +
                            orderInfo.getDeliveryAddressId(), address);
        }
        // ????????????
        return address;
    }
    
}

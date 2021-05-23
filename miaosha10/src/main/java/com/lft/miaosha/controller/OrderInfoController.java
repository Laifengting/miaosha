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
        // 先从缓存中取
        OrderInfo orderInfo = redisService.get(OrderKeyPrefix.KEY_PREFIX_GET_ORDERINFO_BY_OID, "" + orderId, OrderInfo.class);
        
        GoodsVo goodsVo = null;
        Address address = null;
        
        // 如果缓存有
        if (orderInfo != null) {
            goodsVo = getGoodsVoFromCacheOrDbAndSetToCache(orderInfo);
            address = getAddressFromCacheOrDbAndSetToCache(orderInfo);
            return R.OK().data("user", miaoshaUser).data("orderInfo", orderInfo).data("goodsVo", goodsVo).data("address", address);
        }
        // 缓存中没有，先从数据库中查询出来。
        orderInfo = orderInfoService.getOrderInfoById(orderId);
        // 数据库中若没有
        if (orderInfo != null) {
            // 数据库中有，先存到缓存中
            redisService.set(OrderKeyPrefix.KEY_PREFIX_GET_ORDERINFO_BY_OID, "" + orderId, orderInfo);
            // 获取 商品和地址
            goodsVo = getGoodsVoFromCacheOrDbAndSetToCache(orderInfo);
            address = getAddressFromCacheOrDbAndSetToCache(orderInfo);
        }
        // 再返回
        return R.OK().data("user", miaoshaUser).data("orderInfo", orderInfo).data("goodsVo", goodsVo).data("address", address);
    }
    
    private GoodsVo getGoodsVoFromCacheOrDbAndSetToCache(OrderInfo orderInfo) {
        // 从缓存中取
        GoodsVo goodsVo = redisService.get(GoodsKeyPrefix.KEY_PREFIX_GET_GOODSVO_BY_GID, "" + orderInfo.getGoodsId(), GoodsVo.class);
        // 缓存中如果没有
        if (goodsVo == null) {
            // 从数据库中取
            goodsVo = goodsService.getGoodsVoByGoodsId(orderInfo.getGoodsId());
            // 数据库如果没有抛出异常
            if (goodsVo == null) {
                throw new MsException(ExceptionCode.NULL_OBJECT_EXCEPTION);
            }
            // 数据库中有，先保存到缓存
            redisService.set(GoodsKeyPrefix.KEY_PREFIX_GET_GOODSVO_BY_GID, "" + orderInfo.getGoodsId(), goodsVo);
            // 然后返回
            return goodsVo;
        } else {
            // 如果缓存中有直接返回
            return goodsVo;
        }
    }
    
    private Address getAddressFromCacheOrDbAndSetToCache(OrderInfo orderInfo) {
        // 从缓存中取
        Address address = redisService
                .get(AddressKeyPrefix.KEY_PREFIX_GET_ADDRESS_BY_UID_AID, "" +
                        orderInfo.getUserId() + ":" +
                        orderInfo.getDeliveryAddressId(), Address.class);
        // 缓存中如果没有
        if (address != null) {
            return address;
        }
        // 从数据库中取
        address = addressService.getAddressByUserIdAndAddressId(orderInfo.getUserId(), orderInfo.getDeliveryAddressId());
        
        // 数据库中有
        if (address != null) {
            // 保存到缓存
            redisService
                    .set(AddressKeyPrefix.KEY_PREFIX_GET_ADDRESS_BY_UID_AID, "" +
                            orderInfo.getUserId() + ":" +
                            orderInfo.getDeliveryAddressId(), address);
        }
        // 然后返回
        return address;
    }
    
}

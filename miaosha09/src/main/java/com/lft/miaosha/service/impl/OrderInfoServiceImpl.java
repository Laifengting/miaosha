package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.key.impl.OrderKeyPrefix;
import com.lft.miaosha.dao.OrderInfoMapper;
import com.lft.miaosha.entity.po.Address;
import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.po.OrderInfo;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.entity.vo.OrderInfoVo;
import com.lft.miaosha.service.AddressService;
import com.lft.miaosha.service.GoodsService;
import com.lft.miaosha.service.OrderInfoService;
import com.lft.miaosha.service.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Class Name:      OrderInfoServiceImpl
 * Package Name:    com.lft.miaosha.service.impl
 * <p>
 * Function: 		A {@code OrderInfoServiceImpl} object With Some FUNCTION.
 * Date:            2021-05-16 15:59
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private RedisService redisService;
    
    @Override
    @Transactional
    public OrderInfoVo addOrder(MiaoshaUser miaoshaUser, MiaoshaGoods miaoshaGoods) {
        if (miaoshaUser == null || miaoshaGoods == null) {
            return null;
        }
        
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(miaoshaUser.getId());
        orderInfo.setGoodsId(miaoshaGoods.getGoodsId());
        
        // 从数据库中获取 GoodsVo 商品信息
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(orderInfo.getGoodsId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(miaoshaGoods.getMiaoshaPrice());
        orderInfo.setGmtCreated(new Date());
        orderInfo.setOrderChannel(2);
        orderInfo.setStatus(0);
        // 从数据库中根据用户 id 获取默认地址信息
        Address address = addressService.getAllByUserId(orderInfo.getUserId()).get(0);
        if (address == null) {
            return null;
        }
        // 设置地址
        orderInfo.setDeliveryAddressId(address.getId());
        
        // 向数据库中插入订单
        Integer result = orderInfoMapper.insert(orderInfo);
        // 如果插入失败抛出异常
        if (result < 0) {
            return null;
        }
        
        // 如果插入订单成功放入到缓存中
        redisService.set(OrderKeyPrefix.KEY_PREFIX_GET_ORDERINFO_BY_OID, "" + orderInfo.getId(), orderInfo);
        
        // 创建 OrderInfoVo 对象，增加详细地址信息
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        BeanUtils.copyProperties(orderInfo, orderInfoVo);
        orderInfoVo.setName(address.getName());
        orderInfoVo.setAddressDetail(address.getAddressDetail());
        // 返回
        return orderInfoVo;
    }
    
    @Override
    public OrderInfo getOrderInfoById(Long orderId) {
        // 从缓存中获取订单详情
        OrderInfo orderInfo = redisService.get(OrderKeyPrefix.KEY_PREFIX_GET_ORDERINFO_BY_OID, "" + orderId, OrderInfo.class);
        // 缓存中有数据直接返回
        if (orderInfo != null) {
            return orderInfo;
        }
        // 缓存中没有从数据库中查询
        orderInfo = orderInfoMapper.selectById(orderId);
        
        // 如果数据库中不为空，放到缓存中一份
        if (orderInfo != null) {
            redisService.set(OrderKeyPrefix.KEY_PREFIX_GET_ORDERINFO_BY_OID, "" + orderId, orderInfo);
        }
        
        return orderInfo;
    }
}

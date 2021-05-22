package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.constant.RedisConstants;
import com.lft.miaosha.common.exception.ExceptionCode;
import com.lft.miaosha.common.key.impl.MSOrderKeyPrefix;
import com.lft.miaosha.dao.MiaoshaOrderMapper;
import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.entity.po.MiaoshaOrder;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.vo.OrderInfoVo;
import com.lft.miaosha.exception.MsException;
import com.lft.miaosha.service.AddressService;
import com.lft.miaosha.service.MiaoshaGoodsService;
import com.lft.miaosha.service.MiaoshaOrderService;
import com.lft.miaosha.service.OrderInfoService;
import com.lft.miaosha.service.RedisService;
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
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService {
    
    @Autowired
    private MiaoshaOrderMapper miaoshaOrderMapper;
    
    @Autowired
    private OrderInfoService orderInfoService;
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;
    
    @Autowired
    private RedisService redisService;
    
    /**
     * 根据用户id 商品id 查询 秒杀订单
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long userId, Long goodsId) {
        // 从缓存中查询
        MiaoshaOrder miaoshaOrder = redisService
                .get(MSOrderKeyPrefix.KEY_PREFIX_GET_MSORDER_BY_UID_GID, "" + userId + ":" + goodsId, MiaoshaOrder.class);
        // 如果缓存中有数据直接返回
        if (miaoshaOrder != null) {
            return miaoshaOrder;
        }
        // 如果缓存中没有从数据库查询
        miaoshaOrder = miaoshaOrderMapper.selectMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        // 如果数据库中数据不为空
        if (miaoshaOrder != null) {
            // 放到缓存中
            redisService.set(MSOrderKeyPrefix.KEY_PREFIX_GET_MSORDER_BY_UID_GID, "" + userId + ":" + goodsId, miaoshaOrder);
        }
        // 返回
        return miaoshaOrder;
    }
    
    /**
     * 根据用户 商品 生成订单信息
     * @param miaoshaUser
     * @param goodsVo
     * @return
     */
    @Override
    @Transactional
    public OrderInfoVo miaosha(MiaoshaUser miaoshaUser, MiaoshaGoods miaoshaGoods) {
        miaoshaGoods.setGmtModified(new Date());
        // 第 1 步 减库存
        Integer reduceStockResult = miaoshaGoodsService.reduceStock(miaoshaGoods);
        if (reduceStockResult <= 0) {
            throw new MsException(ExceptionCode.CREATE_ORDER_EXCEPTION);
        }
        
        // 第 2 步 下订单 订单详情
        OrderInfoVo orderInfoVo = orderInfoService.addOrder(miaoshaUser, miaoshaGoods);
        
        // 第 3 步 写入秒杀订单
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setOrderId(orderInfoVo.getId());
        miaoshaOrder.setUserId(orderInfoVo.getUserId());
        miaoshaOrder.setGoodsId(orderInfoVo.getGoodsId());
        miaoshaOrder.setGmtCreated(new Date());
        Integer result = miaoshaOrderMapper.addMiaoshaOrder(miaoshaOrder);
        
        if (result <= 0) {
            throw new MsException(ExceptionCode.CREATE_ORDER_EXCEPTION);
        }
        // 数据库插入成功，生成订单对象到缓存中
        redisService.set(MSOrderKeyPrefix.KEY_PREFIX_GET_MSORDER_BY_UID_GID, RedisConstants.EMPTY_STRING + miaoshaUser
                .getId() + RedisConstants.SPILT + miaoshaGoods
                .getGoodsId(), miaoshaOrder);
        // 返回订单信息
        return orderInfoVo;
    }
    
}

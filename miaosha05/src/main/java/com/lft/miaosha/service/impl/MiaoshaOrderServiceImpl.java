package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.exception.ExceptionCode;
import com.lft.miaosha.dao.MiaoshaOrderMapper;
import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.entity.po.MiaoshaOrder;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.entity.vo.OrderInfoVo;
import com.lft.miaosha.exception.MsException;
import com.lft.miaosha.service.AddressService;
import com.lft.miaosha.service.MiaoshaOrderService;
import com.lft.miaosha.service.OrderInfoService;
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
    
    /**
     * 根据用户id 商品id 查询 秒杀订单
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long userId, Long goodsId) {
        return miaoshaOrderMapper.selectMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }
    
    /**
     * 根据用户 商品 生成订单信息
     * @param miaoshaUser
     * @param goodsVo
     * @return
     */
    @Override
    @Transactional
    public OrderInfoVo miaosha(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {
        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setGoodsId(goodsVo.getId());
        miaoshaGoods.setStockCount(goodsVo.getStockCount() - 1);
        // 减库存
        Integer reduceStockResult = miaoshaOrderMapper.reduceStock(miaoshaGoods);
        
        // 下订单
        OrderInfoVo orderInfoVo = orderInfoService.addOrder(miaoshaUser, goodsVo);
        
        // 写入秒杀订单
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setOrderId(orderInfoVo.getId());
        miaoshaOrder.setUserId(orderInfoVo.getUserId());
        miaoshaOrder.setGoodsId(orderInfoVo.getGoodsId());
        miaoshaOrder.setGmtCreated(new Date());
        Integer result = miaoshaOrderMapper.addMiaoshaOrder(miaoshaOrder);
        if (result <= 0) {
            throw new MsException(ExceptionCode.CREATE_ORDER_EXCEPTION);
        }
        return orderInfoVo;
    }
    
}

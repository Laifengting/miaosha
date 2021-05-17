package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.exception.ExceptionCode;
import com.lft.miaosha.dao.OrderInfoMapper;
import com.lft.miaosha.entity.po.Address;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.po.OrderInfo;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.entity.vo.OrderInfoVo;
import com.lft.miaosha.exception.MsException;
import com.lft.miaosha.service.AddressService;
import com.lft.miaosha.service.OrderInfoService;
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
    
    @Override
    @Transactional
    public OrderInfoVo addOrder(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {
        if (miaoshaUser == null || goodsVo == null) {
            throw new MsException(ExceptionCode.ILLEGAL_ARGUMENT_EXCEPTION);
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(miaoshaUser.getId());
        
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        
        orderInfo.setGmtCreated(new Date());
        orderInfo.setOrderChannel(2);
        orderInfo.setStatus(0);
        
        Address address = addressService.getAllByUserId(orderInfo.getUserId()).get(0);
        if (address == null) {
            throw new MsException(ExceptionCode.NULL_ADDRESS_EXCEPTION);
        }
        
        orderInfo.setDeliveryAddressId(address.getId());
        
        Integer result = orderInfoMapper.insert(orderInfo);
        if (result < 0) {
            throw new MsException(ExceptionCode.CREATE_ORDER_EXCEPTION);
        }
        
        // 增加详细地址信息
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        BeanUtils.copyProperties(orderInfo, orderInfoVo);
        orderInfoVo.setName(address.getName());
        orderInfoVo.setAddressDetail(address.getAddressDetail());
        
        return orderInfoVo;
    }
}

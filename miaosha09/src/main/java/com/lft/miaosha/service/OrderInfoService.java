package com.lft.miaosha.service;

import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.po.OrderInfo;
import com.lft.miaosha.entity.vo.OrderInfoVo;

/**
 * Class Name:      OrderInfoService
 * Package Name:    com.lft.miaosha.service
 * <p>
 * Function: 		A {@code OrderInfoService} object With Some FUNCTION.
 * Date:            2021-05-16 15:56
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public interface OrderInfoService {
    OrderInfoVo addOrder(MiaoshaUser miaoshaUser, MiaoshaGoods miaoshaGoods);
    
    OrderInfo getOrderInfoById(Long orderId);
    
}

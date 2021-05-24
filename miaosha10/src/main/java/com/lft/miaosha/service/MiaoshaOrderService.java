package com.lft.miaosha.service;

import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.entity.po.MiaoshaOrder;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.vo.OrderInfoVo;

import java.awt.image.BufferedImage;

/**
 * Class Name:      MiaoshaGoodsUService
 * Package Name:    com.lft.miaosha.service
 * <p>
 * Function: 		A {@code MiaoshaGoodsUService} object With Some FUNCTION.
 * Date:            2021-05-16 16:06
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public interface MiaoshaOrderService {
    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long id, Long goodsId);
    
    OrderInfoVo miaosha(MiaoshaUser miaoshaUser, MiaoshaGoods miaoshaGoods);
    
    Long getMiaoshaResult(Long userId, Long goodsId);
    
    Boolean checkPath(MiaoshaUser miaoshaUser, Long goodsId, String path);
    
    BufferedImage createMiaoshaVerifyCode(MiaoshaUser miaoshaUser, Long goodsId);
}

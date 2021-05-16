package com.lft.miaosha.service;

import com.lft.miaosha.entity.po.MiaoshaGoods;

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
public interface MiaoshaGoodsService {
    MiaoshaGoods getMiaoshaGoodsByGoodsId(Long goodsId);
}

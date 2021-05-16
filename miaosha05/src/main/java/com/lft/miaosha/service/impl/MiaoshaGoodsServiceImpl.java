package com.lft.miaosha.service.impl;

import com.lft.miaosha.dao.MiaoshaGoodsMapper;
import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.service.MiaoshaGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MiaoshaGoodsServiceImpl implements MiaoshaGoodsService {
    @Autowired
    private MiaoshaGoodsMapper miaoshaGoodsMapper;
    
    @Override
    public MiaoshaGoods getMiaoshaGoodsByGoodsId(Long goodsId) {
        return miaoshaGoodsMapper.selectMiaoshaGoodsByGoodsId(goodsId);
    }
}

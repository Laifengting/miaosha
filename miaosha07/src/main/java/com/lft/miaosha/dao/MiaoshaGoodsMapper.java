package com.lft.miaosha.dao;

import com.lft.miaosha.entity.po.MiaoshaGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Class Name:      MiaoshaUserMapper
 * Package Name:    com.lft.miaosha.dao
 * <p>
 * Function: 		A {@code MiaoshaUserMapper} object With Some FUNCTION.
 * Date:            2021-05-15 15:28
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Mapper
public interface MiaoshaGoodsMapper {
    MiaoshaGoods selectMiaoshaGoodsByGoodsId(@Param ("goodsId") Long goodsId);
    
    List<MiaoshaGoods> selectAllMiaoshaGoods();
    
    Integer insertMiaoshaGoods(MiaoshaGoods miaoshaGoods);
    
    Integer updateMiaoshaGoodsById(MiaoshaGoods miaoshaGoods);
    
    Integer reduceStock(MiaoshaGoods miaoshaGoods);
    
    boolean deleteMiaoshaGoodsById(@Param ("id") Long id);
}

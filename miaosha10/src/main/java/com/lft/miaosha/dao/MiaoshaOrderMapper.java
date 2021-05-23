package com.lft.miaosha.dao;

import com.lft.miaosha.entity.po.MiaoshaOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface MiaoshaOrderMapper {
    MiaoshaOrder selectMiaoshaOrderByUserIdGoodsId(@Param ("userId") Long userId, @Param ("goodsId") Long goodsId);
    
    Integer addMiaoshaOrder(MiaoshaOrder miaoshaOrder);
    
    MiaoshaOrder selectByUidGid(@Param ("userId") Long userId, @Param ("goodsId") Long goodsId);
}

package com.lft.miaosha.dao;

import com.lft.miaosha.entity.po.Goods;
import com.lft.miaosha.entity.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Class Name:      GoodsMapper
 * Package Name:    com.lft.miaosha.dao
 * <p>
 * Function: 		A {@code GoodsMapper} object With Some FUNCTION.
 * Date:            2021-05-16 15:56
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Mapper
public interface GoodsMapper {
    List<GoodsVo> selectAllGoodsVo();
    
    GoodsVo selectGoodsVoByGoodsId(Long goodsId);
    
}

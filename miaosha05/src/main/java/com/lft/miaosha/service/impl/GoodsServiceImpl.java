package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.exception.ExceptionCode;
import com.lft.miaosha.dao.GoodsMapper;
import com.lft.miaosha.entity.po.Goods;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.exception.MsException;
import com.lft.miaosha.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class Name:      GoodsServiceImpl
 * Package Name:    com.lft.miaosha.service.impl
 * <p>
 * Function: 		A {@code GoodsServiceImpl} object With Some FUNCTION.
 * Date:            2021-05-16 15:55
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    
    @Autowired
    private GoodsMapper goodsMapper;
    
    @Override
    public List<GoodsVo> getAllGoods() {
        return goodsMapper.selectAllGoodsVo();
    }
    
    @Override
    public GoodsVo getGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.selectGoodsVoByGoodsId(goodsId);
    }
}

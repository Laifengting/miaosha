package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.key.impl.GoodsKeyPrefix;
import com.lft.miaosha.dao.GoodsMapper;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.service.GoodsService;
import com.lft.miaosha.service.RedisService;
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
    
    @Autowired
    private RedisService redisService;
    
    @Override
    public List<GoodsVo> getAllGoods() {
        // 从缓存中读取数据
        List<GoodsVo> list = redisService.get(GoodsKeyPrefix.KEY_PREFIX_GET_ALL_GOODSVOS, "", List.class);
        // 缓存中有数据直接返回
        if (list != null) {
            return list;
        }
        // 缓存中没有数据查询数据库
        list = goodsMapper.selectAllGoodsVo();
        // 数据库中有数据保存到缓存
        if (list != null) {
            redisService.set(GoodsKeyPrefix.KEY_PREFIX_GET_ALL_GOODSVOS, "", list);
        }
        // 返回
        return list;
    }
    
    @Override
    public GoodsVo getGoodsVoByGoodsId(Long goodsId) {
        // 从缓存中读取数据
        GoodsVo goodsVo = redisService.get(GoodsKeyPrefix.KEY_PREFIX_GET_GOODSVO_BY_GID, "" + goodsId, GoodsVo.class);
        // 如果缓存中不为空直接返回
        if (goodsVo != null) {
            return goodsVo;
        }
        // 如果缓存中为空，从数据库中查询
        goodsVo = goodsMapper.selectGoodsVoByGoodsId(goodsId);
        // 数据库中不为空，放一份到缓存中
        if (goodsVo != null) {
            redisService.set(GoodsKeyPrefix.KEY_PREFIX_GET_GOODSVO_BY_GID, "" + goodsId, goodsVo);
        }
        // 返回结果
        return goodsVo;
    }
}

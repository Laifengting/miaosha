package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.exception.ExceptionCode;
import com.lft.miaosha.common.key.impl.MSGoodsKeyPrefix;
import com.lft.miaosha.dao.MiaoshaGoodsMapper;
import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.exception.MsException;
import com.lft.miaosha.service.MiaoshaGoodsService;
import com.lft.miaosha.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    
    @Autowired
    private RedisService redisService;
    
    @Override
    public MiaoshaGoods getMiaoshaGoodsByGoodsId(Long goodsId) {
        // 从缓存中取数据
        MiaoshaGoods miaoshaGoods = redisService.get(MSGoodsKeyPrefix.KEY_PREFIX_GET_MSGOODS_BY_GID, "" + goodsId, MiaoshaGoods.class);
        if (miaoshaGoods != null) {
            // 缓存中有直接返回
            return miaoshaGoods;
        }
        // 缓存中没有，查询数据库
        miaoshaGoods = miaoshaGoodsMapper.selectMiaoshaGoodsByGoodsId(goodsId);
        // 数据库查询的值不为空
        if (miaoshaGoods != null) {
            // 放到缓存中
            redisService.set(MSGoodsKeyPrefix.KEY_PREFIX_GET_MSGOODS_BY_GID, "" + goodsId, miaoshaGoods);
        }
        // 返回
        return miaoshaGoods;
    }
    
    @Override
    @Transactional
    public Integer reduceStock(MiaoshaGoods miaoshaGoods) {
        // 清除缓存
        redisService.delete(MSGoodsKeyPrefix.KEY_PREFIX_GET_MSGOODS_BY_GID, "" + miaoshaGoods.getGoodsId());
        // 执行修改库存
        Integer result = miaoshaGoodsMapper.reduceStock(miaoshaGoods);
        if (result <= 0) {
            throw new MsException(ExceptionCode.LOGIN_EXCEPTION);
        }
        return result;
    }
    
    @Override
    public List<MiaoshaGoods> getAllMiaoshaGoods() {
        return miaoshaGoodsMapper.selectAllMiaoshaGoods();
    }
}

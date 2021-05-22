package com.lft.miaosha.common.key.impl;

import com.lft.miaosha.common.constant.RedisConstants;
import com.lft.miaosha.common.key.ExpireSeconds;

/**
 * Class Name:      UserKeyPrefix
 * Package Name:    com.lft.miaosha.common.util.com.lft.miaosha.common.key.impl
 * <p>
 * Function: 		A {@code UserKeyPrefix} object With Some FUNCTION.
 * Date:            2021-05-15 11:36
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class GoodsKeyPrefix extends BaseKeyPrefix {
    // 过期时间是1天
    public static GoodsKeyPrefix KEY_PREFIX_GET_GOODSVO_BY_GOODS_ID =
            new GoodsKeyPrefix(ExpireSeconds.DAY, RedisConstants.GOODS_KEY_SUFFIX_GET_GOODSVO_BY_GOODS_ID);
    
    public static GoodsKeyPrefix KEY_PREFIX_GET_ALL_GOODSVOS =
            new GoodsKeyPrefix(ExpireSeconds.DAY, RedisConstants.GOODS_KEY_SUFFIX_GET_ALL_GOODSVOS);
    
    // 过期时间是1分钟
    public static GoodsKeyPrefix KEY_PREFIX_GET_HTML_FOR_GOODS_LIST =
            new GoodsKeyPrefix(ExpireSeconds.MINUTE, RedisConstants.GOODS_KEY_SUFFIX_GET_HTML_FOR_GOODS_LIST);
    
    public static GoodsKeyPrefix KEY_PREFIX_GET_HTML_FOR_GOODS_DETAIL_BY_GID =
            new GoodsKeyPrefix(ExpireSeconds.MINUTE, RedisConstants.GOODS_KEY_SUFFIX_GET_HTML_FOR_GOODS_DETAIL_BY_GID);
    
    private GoodsKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    
    private GoodsKeyPrefix(String prefix) {
        super(prefix);
    }
}

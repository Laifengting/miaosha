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
public class MSGoodsKeyPrefix extends BaseKeyPrefix {
    // 过期时间是1天
    public static MSGoodsKeyPrefix KEY_PREFIX_GET_MSGOODS_BY_GID =
            new MSGoodsKeyPrefix(ExpireSeconds.MINUTE, RedisConstants.MS_GOODS_KEY_SUFFIX_GET_MSGOODS_BY_GID);
    
    // 过期时间是1周
    public static MSGoodsKeyPrefix KEY_PREFIX_GET_MSGOODS_STOCK_BY_GID =
            new MSGoodsKeyPrefix(ExpireSeconds.MINUTE * 5, RedisConstants.GOODS_KEY_SUFFIX_GET_MSGOODS_STOCK_BY_GID);
    
    // 过期时间是1天
    public static MSGoodsKeyPrefix KEY_PREFIX_IS_MSGOODS_SELL_OUT_BY_GID =
            new MSGoodsKeyPrefix(ExpireSeconds.MINUTE * 5, RedisConstants.MS_GOODS_KEY_SUFFIX_IS_MSGOODS_SELL_OUT_BY_GID);
    
    private MSGoodsKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    
    private MSGoodsKeyPrefix(String prefix) {
        super(prefix);
    }
}

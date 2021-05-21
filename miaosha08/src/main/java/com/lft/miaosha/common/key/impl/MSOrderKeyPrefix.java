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
public class MSOrderKeyPrefix extends BaseKeyPrefix {
    // 过期时间是1天
    public static MSOrderKeyPrefix KEY_PREFIX_GET_MSORDER_BY_UID_GID =
            new MSOrderKeyPrefix(ExpireSeconds.FOREVER, RedisConstants.MS_ORDER_KEY_SUFFIX_GET_MSORDER_BY_UID_GID);
    
    private MSOrderKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    
    private MSOrderKeyPrefix(String prefix) {
        super(prefix);
    }
}

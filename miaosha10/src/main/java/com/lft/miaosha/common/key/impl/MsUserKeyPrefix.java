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
public class MsUserKeyPrefix extends BaseKeyPrefix {
    // 过期时间是7天
    public static MsUserKeyPrefix KEY_PREFIX_GET_USER_BY_ID =
            new MsUserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.USER_KEY_SUFFIX_GET_USER_BY_UID);
    
    public static MsUserKeyPrefix KEY_PREFIX_GET_USER_BY_TOKEN =
            new MsUserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.USER_KEY_SUFFIX_GET_USER_BY_TOKEN);
    
    public MsUserKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    
    public MsUserKeyPrefix(String prefix) {
        super(prefix);
    }
}

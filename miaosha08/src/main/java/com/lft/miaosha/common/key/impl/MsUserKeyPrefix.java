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
    public static MsUserKeyPrefix KEY_PREFIX_ID = new MsUserKeyPrefix(ExpireSeconds.FOREVER, RedisConstants.USER_KEY_SUFFIX_ID);
    public static MsUserKeyPrefix KEY_PREFIX_TOKEN = new MsUserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.USER_KEY_SUFFIX_TOKEN);
    
    private MsUserKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    
    private MsUserKeyPrefix(String prefix) {
        super(prefix);
    }
}

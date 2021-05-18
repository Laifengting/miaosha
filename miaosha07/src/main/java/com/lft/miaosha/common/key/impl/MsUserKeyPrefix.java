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
    public static MsUserKeyPrefix ID_KEY = new MsUserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.ID);
    public static MsUserKeyPrefix TOKEN_KEY = new MsUserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.TOKEN);
    public static MsUserKeyPrefix NAME_KEY = new MsUserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.NAME);
    public static MsUserKeyPrefix PHONE_KEY = new MsUserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.PHONE);
    public static MsUserKeyPrefix DATE_KEY = new MsUserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.DATE);
    
    private MsUserKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    
    private MsUserKeyPrefix(String prefix) {
        super(prefix);
    }
}

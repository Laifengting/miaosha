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
public class UserKeyPrefix extends BaseKeyPrefix {
    // 过期时间是7天
    public static UserKeyPrefix ID_KEY = new UserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.ID);
    public static UserKeyPrefix NAME_KEY = new UserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.NAME);
    public static UserKeyPrefix PHONE_KEY = new UserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.PHONE);
    public static UserKeyPrefix DATE_KEY = new UserKeyPrefix(ExpireSeconds.WEEK, RedisConstants.DATE);
    
    private UserKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    
    private UserKeyPrefix(String prefix) {
        super(prefix);
    }
}

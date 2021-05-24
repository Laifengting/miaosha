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
public class AddressKeyPrefix extends BaseKeyPrefix {
    // 过期时间是永不过期
    public static AddressKeyPrefix KEY_PREFIX_GET_ADDRESS_BY_UID_AID =
            new AddressKeyPrefix(ExpireSeconds.WEEK, RedisConstants.ADDRESS_KEY_SUFFIX_GET_ADDRESS_BY_UID_AID);
    
    public static AddressKeyPrefix KEY_PREFIX_GET_ALL_ADDRS_BY_UID =
            new AddressKeyPrefix(ExpireSeconds.WEEK, RedisConstants.ADDRESS_KEY_SUFFIX_GET_ALL_ADDRS_BY_UID);
    
    private AddressKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    
    private AddressKeyPrefix(String prefix) {
        super(prefix);
    }
}

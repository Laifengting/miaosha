package com.lft.miaosha.common.key.impl;

import com.lft.miaosha.common.constant.RedisConstants;
import com.lft.miaosha.common.key.ExpireSeconds;

/**
 * Class Name:      OrderKeyPrefix
 * Package Name:    com.lft.miaosha.common.util.com.lft.miaosha.common.key.impl
 * <p>
 * Function: 		A {@code OrderKeyPrefix} object With Some FUNCTION.
 * Date:            2021-05-15 11:36
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class OrderKeyPrefix extends BaseKeyPrefix {
    // 过期时间是7天
    public static OrderKeyPrefix KEY_PREFIX_GET_ORDERINFO_BY_OID =
            new OrderKeyPrefix(ExpireSeconds.MINUTE * 5, RedisConstants.ORDER_KEY_SUFFIX_GET_ORDERINFO_BY_OID);
    
    public OrderKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    
    public OrderKeyPrefix(String prefix) {
        super(prefix);
    }
}

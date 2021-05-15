package com.lft.miaosha.common.key.impl;

import com.lft.miaosha.common.constant.RedisConstants;

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
    public static OrderKeyPrefix ORDER_ID_KEY = new OrderKeyPrefix(RedisConstants.ORDER_ID);
    public static OrderKeyPrefix NAME_KEY = new OrderKeyPrefix(RedisConstants.NAME);
    public static OrderKeyPrefix PHONE_KEY = new OrderKeyPrefix(RedisConstants.PHONE);
    public static OrderKeyPrefix ADDRESS_KEY = new OrderKeyPrefix(RedisConstants.ADDRESS);
    public static OrderKeyPrefix DATE_KEY = new OrderKeyPrefix(RedisConstants.DATE);
    public OrderKeyPrefix(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public OrderKeyPrefix(String prefix) {
        super(prefix);
    }
}

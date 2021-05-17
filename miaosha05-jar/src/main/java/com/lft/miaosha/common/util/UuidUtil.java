package com.lft.miaosha.common.util;

import java.util.UUID;

/**
 * Class Name:      UuidUtil
 * Package Name:    com.lft.miaosha.common.util
 * <p>
 * Function: 		A {@code UuidUtil} object With Some FUNCTION.
 * Date:            2021-05-16 10:18
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class UuidUtil {
    public static String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

package com.lft.miaosha.common.util;

import java.util.UUID;

/**
 * Class Name:      RandomSaltUtil
 * Package Name:    com.lft.miaosha.common.util
 * <p>
 * Function: 		A {@code RandomSaltUtil} object With Some FUNCTION.
 * Date:            2021-05-15 13:52
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class RandomSaltUtil {
    
    public static String getRandomSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
    }
}

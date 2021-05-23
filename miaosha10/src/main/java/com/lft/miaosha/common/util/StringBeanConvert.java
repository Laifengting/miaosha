package com.lft.miaosha.common.util;

import com.alibaba.fastjson.JSON;

/**
 * Class Name:      JsonBeanConvernt
 * Package Name:    com.lft.miaosha.common.util
 * <p>
 * Function: 		A {@code JsonBeanConvernt} object With Some FUNCTION.
 * Date:            2021-05-22 12:49
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class StringBeanConvert {
    
    /**
     * 对象转JSON字符串
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == Integer.class || clazz == int.class || clazz == Long.class || clazz == long.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else {
            return JSON.toJSONString(value);
        }
    }
    
    /**
     * JSON字符串转对象
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        } else if (clazz == Integer.class || clazz == int.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == Long.class || clazz == long.class) {
            return (T) Long.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }
}

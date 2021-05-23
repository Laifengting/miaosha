package com.lft.miaosha.common.key;

/**
 * Class Name:      Prefix
 * Package Name:    com.lft.miaosha.common.util.com.lft.miaosha.common.key
 * <p>
 * Function: 		A {@code Prefix} object With Some FUNCTION.
 * Date:            2021-05-15 11:31
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public interface KeyPrefix {
    /**
     * 获取 key 前缀
     * @return
     */
    public String getPrefix();
    
    /**
     * 获取过期时间（秒）
     * -1 表示永不过期。
     * -2 表示已经失败
     * @return
     */
    public Integer expireSeconds();
}

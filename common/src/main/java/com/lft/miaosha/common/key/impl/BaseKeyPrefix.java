package com.lft.miaosha.common.key.impl;

import com.lft.miaosha.common.constant.RedisConstants;
import com.lft.miaosha.common.key.KeyPrefix;

/**
 * Class Name:      BaseKeyPrefix
 * Package Name:    com.lft.miaosha.common.util.com.lft.miaosha.common.key.impl
 * <p>
 * Function: 		A {@code BaseKeyPrefix} object With Some FUNCTION.
 * Date:            2021-05-15 11:33
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public abstract class BaseKeyPrefix implements KeyPrefix {
    private Integer expireSeconds;
    private String prefix;
    
    public BaseKeyPrefix(String prefix) {
        // 默认 -1 代表永不过期
        this(-1, prefix);
    }
    
    public BaseKeyPrefix(Integer expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
    
    /**
     * 获取 com.lft.miaosha.common.key 前缀
     * @return
     */
    @Override
    public String getPrefix() {
        String simpleName = getClass().getSimpleName();
        return simpleName + RedisConstants.SPILT + prefix + RedisConstants.SPILT;
    }
    
    /**
     * 获取过期时间（秒）
     * @return
     */
    @Override
    public Integer expireSeconds() {
        // 默认 -1 代表永不过期
        return expireSeconds;
    }
}

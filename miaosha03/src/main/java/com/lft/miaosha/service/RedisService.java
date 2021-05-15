package com.lft.miaosha.service;

import com.lft.miaosha.common.key.KeyPrefix;

/**
 * Class Name:      RedisService
 * Package Name:    com.lft.miaosha.service
 * <p>
 * Function: 		A {@code RedisService} object With Some FUNCTION.
 * Date:            2021-05-15 8:52
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public interface RedisService {
    /**
     * 从 Redis 中获取 key 对应的对象
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz);
    
    /**
     * 向 Redis 放入 key 和对应的对象
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    <T> Boolean set(KeyPrefix keyPrefix, String key, T value);
    
    /**
     * 数值数据自增 1
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    <T> Long incr(KeyPrefix keyPrefix, String key);
    
    /**
     * 数值数据自减 1
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    <T> Long decr(KeyPrefix keyPrefix, String key);
    
    /**
     * 判断是否存在该 key
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    <T> Boolean existsKey(KeyPrefix keyPrefix, String key);
    
    /**
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
     * @param keyPrefix
     * @param key
     * @param expireSeconds 过期时间，单位：秒
     * @param <T>
     * @return 成功返回1 如果存在 和 发生异常 返回 0
     */
    <T> Long expire(KeyPrefix keyPrefix, String key, Integer expireSeconds);
    
    /**
     * 以秒为单位，返回给定 key 的剩余生存时间
     * @param keyPrefix
     * @param key
     * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key 的剩余生存时间。 发生异常 返回 0
     */
    <T> Long ttl(KeyPrefix keyPrefix, String key);
    
    /**
     * 移除给定 key 的生存时间，将这个 key 从『易失的』(带生存时间 key )转换成『持久的』(一个不带生存时间、永不过期的 key )
     * @param keyPrefix
     * @param key
     * @return 当生存时间移除成功时，返回 1 .如果 key 不存在或 key 没有设置生存时间，返回 0 ， 发生异常 返回 -1
     */
    <T> Long persist(KeyPrefix keyPrefix, String key);
}

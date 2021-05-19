package com.lft.miaosha.service.impl;

import com.alibaba.fastjson.JSON;
import com.lft.miaosha.common.key.KeyPrefix;
import com.lft.miaosha.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Class Name:      RedisServiceImpl
 * Package Name:    com.lft.miaosha.service.impl
 * <p>
 * Function: 		A {@code RedisServiceImpl} object With Some FUNCTION.
 * Date:            2021-05-15 8:52
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Service
public class RedisServiceImpl implements RedisService {
    
    @Autowired
    private JedisPool jedisPool;
    
    /**
     * Redis get 方法
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(keyPrefix.getPrefix() + key);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * Redis set 方法
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    @Override
    public <T> Boolean set(KeyPrefix keyPrefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null) {
                return false;
            }
            // 生成真正的 key
            String realKey = keyPrefix.getPrefix() + key;
            Integer expireSeconds = keyPrefix.expireSeconds();
            // 不过期
            if (expireSeconds <= 0) {
                jedis.set(realKey, str);
                return true;
            }
            // 有过期时间
            jedis.setex(realKey, expireSeconds, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 把数值类型的值 自增 1
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public Long incr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 生成真正的 key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 把数值类型的值 自减 1
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public Long decr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 生成真正的 key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 判断是否存在该 key
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public Boolean existsKey(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 生成真正的 key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
     * @param keyPrefix
     * @param key
     * @param expireSeconds
     * @return
     */
    @Override
    public Long expire(KeyPrefix keyPrefix, String key, Integer expireSeconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.expire(realKey, expireSeconds);
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 以秒为单位，返回给定 key 的剩余生存时间
     * @param keyPrefix
     * @param key
     * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key 的剩余生存时间。 发生异常 返回 0
     */
    @Override
    public Long ttl(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.ttl(realKey);
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 移除给定 key 的生存时间，将这个 key 从『易失的』(带生存时间 key )转换成『持久的』(一个不带生存时间、永不过期的 key )
     * @param keyPrefix
     * @param key
     * @return 当生存时间移除成功时，返回 1 .如果 key 不存在或 key 没有设置生存时间，返回 0 ， 发生异常 返回 -1
     */
    @Override
    public Long persist(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.persist(realKey);
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 移除指定 key
     * @param keyPrefix
     * @param key
     * @return
     */
    @Override
    public boolean delete(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.del(realKey) > 0;
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 清空当前数据库中的所有 key,此命令从不失败。
     * @return 总是返回 OK
     */
    public String flushDB() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.flushDB();
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 显示当前库中 key 的数量
     * @return 总是返回 OK
     */
    public Long dbsize() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.dbSize();
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 显示所有库中 key 的数量
     * @return 总是返回 OK
     */
    public String info() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.info();
        } finally {
            returnToPool(jedis);
        }
    }
    
    /**
     * 对象转JSON字符串
     * @param value
     * @param <T>
     * @return
     */
    private <T> String beanToString(T value) {
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
    private <T> T stringToBean(String str, Class<T> clazz) {
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
    
    /**
     * 关闭连接，返回给连接池
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}

package com.lft.miaosha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Class Name:      RedisConfig
 * Package Name:    com.lft.miaosha.config
 * <p>
 * Function: 		A {@code RedisConfig} object With Some FUNCTION.
 * Date:            2021-05-15 8:45
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Configuration
public class RedisConfig {
    @Value ("${spring.redis.host}")
    private String host;
    
    @Value ("${spring.redis.port}")
    private Integer port;
    
    @Value ("${spring.redis.timeout}")
    private Integer timeout;
    
    @Value ("${spring.redis.password}")
    private String password;
    
    @Value ("${spring.redis.jedis.pool.max-active}")
    private Integer maxActive;
    
    @Value ("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;
    
    @Value ("${spring.redis.jedis.pool.max-wait}")
    private Integer maxWait;
    
    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        JedisPool jedisPool = new JedisPool(poolConfig, host, port, timeout, password, 0);
        return jedisPool;
    }
}

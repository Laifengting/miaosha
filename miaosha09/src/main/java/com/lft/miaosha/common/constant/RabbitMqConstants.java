package com.lft.miaosha.common.constant;

/**
 * Class Name:      RabbitMqConstants
 * Package Name:    com.lft.miaosha.common.constant
 * <p>
 * Function: 		A {@code RabbitMqConstants} object With Some FUNCTION.
 * Date:            2021-05-22 12:46
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class RabbitMqConstants {
    //================================== 队列名常量 ==================================//
    /**
     * 用于Drect模式交换机的队列
     */
    public static final String DEFAULT_QUEUE = "default_queue";
    /**
     * 用于Drect模式交换机的队列
     */
    public static final String DIRECT_QUEUE = "direct_queue";
    /**
     * 用于Topic模式交换机的队列 1
     */
    public static final String TOPIC_QUEUE_1 = "topic_queue_1";
    /**
     * 用于Topic模式交换机的队列 2
     */
    public static final String TOPIC_QUEUE_2 = "topic_queue_2";
    /**
     * 用于Fanout模式交换机的队列 1
     */
    public static final String FANOUT_QUEUE_1 = "fanout_queue_1";
    /**
     * 用于Fanout模式交换机的队列 2
     */
    public static final String FANOUT_QUEUE_2 = "fanout_queue_2";
    /**
     * 用于Headers模式交换机的队列
     */
    public static final String HEADERS_QUEUE = "headers_queue";
    
    //================================== 交换机常量 ==================================//
    /**
     * default 的交换机
     */
    public static final String DEFAULT_EXCHANGE = "default_exchange";
    /**
     * direct 模式交换机
     */
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    /**
     * topic 模式交换机1
     */
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    /**
     * fanout 模式交换机
     */
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    /**
     * headers 模式交换机
     */
    public static final String HEADERS_EXCHANGE = "headers_exchange";
    
    //================================== 路由 key 常量 ==================================//
    /**
     * direct key
     */
    public static final String ROUTING_KEY_DIRECT = "direct.key";
    /**
     * topic key
     */
    public static final String ROUTING_KEY_TOPIC_1 = "topic.key";
    /**
     * topic key
     * # 通配符 匹配 1 个或多个
     */
    public static final String ROUTING_KEY_TOPIC_2 = "topic.#";
    /**
     * headers key
     */
    public static final String ROUTING_KEY_HEADERS = "headers.key";
    
}

package com.lft.miaosha.rabbitmq;

import com.lft.miaosha.common.constant.RabbitMqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name:      MQConfig
 * Package Name:    com.lft.miaosha.rabbitmq
 * <p>
 * Function: 		A {@code MQConfig} object With Some FUNCTION.
 * Date:            2021-05-22 12:41
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Configuration
public class MQConfig {
    
    //==================================================== 创建队列 ====================================================//
    
    /**
     * DirectExchange 模式交换机使用的队列
     * @return
     */
    @Bean
    public Queue defaultQueue() {
        // name:队列名称，是否是持久队列（重启之后消息会保存）。
        return new Queue(RabbitMqConstants.DEFAULT_QUEUE, true);
    }
    
    /**
     * DirectExchange 模式交换机使用的队列
     * @return
     */
    @Bean
    public Queue directQueue() {
        // name:队列名称，是否是持久队列（重启之后消息会保存）。
        return new Queue(RabbitMqConstants.DIRECT_QUEUE, true);
    }
    
    /**
     * TopicExchange 模式交换机  使用的队列1
     * @return
     */
    @Bean
    public Queue topicQueue1() {
        // name:队列名称，是否是持久队列（重启之后消息会保存）。
        return new Queue(RabbitMqConstants.TOPIC_QUEUE_1, true);
    }
    
    /**
     * TopicExchange 模式交换机  使用的队列2
     * @return
     */
    @Bean
    public Queue topicQueue2() {
        // name:队列名称，是否是持久队列（重启之后消息会保存）。
        return new Queue(RabbitMqConstants.TOPIC_QUEUE_2, true);
    }
    
    /**
     * FanoutExchange （广播模式）模式交换机使用的队列 1
     * @return
     */
    @Bean
    public Queue fanoutQueue1() {
        // name:队列名称，是否是持久队列（重启之后消息会保存）。
        return new Queue(RabbitMqConstants.FANOUT_QUEUE_1, true);
    }
    
    /**
     * FanoutExchange （广播模式）模式交换机使用的队列 2
     * @return
     */
    @Bean
    public Queue fanoutQueue2() {
        // name:队列名称，是否是持久队列（重启之后消息会保存）。
        return new Queue(RabbitMqConstants.FANOUT_QUEUE_2, true);
    }
    
    /**
     * HeadersExchange 模式交换机使用的队列
     * @return
     */
    @Bean
    public Queue headersQueue() {
        // name:队列名称，是否是持久队列（重启之后消息会保存）。
        return new Queue(RabbitMqConstants.HEADERS_QUEUE, true);
    }
    
    //==================================================== 创建交换机 ====================================================//
    
    /**
     * DirectExchange 交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMqConstants.DIRECT_EXCHANGE);
    }
    
    /**
     * TopicExchange交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMqConstants.TOPIC_EXCHANGE);
    }
    
    /**
     * FanoutExchange交换机（广播模式）
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMqConstants.FANOUT_EXCHANGE);
    }
    
    /**
     * HeadersExchange交换机
     * @return
     */
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(RabbitMqConstants.HEADERS_EXCHANGE);
    }
    
    //==================================================== 将队列绑定到交换机 ====================================================//
    
    /**
     * 绑定 directQueue 到 direct Exchange 交换机
     * @return
     */
    @Bean
    public Binding directQueueBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(RabbitMqConstants.ROUTING_KEY_DIRECT);
    }
    
    /**
     * 绑定 topicQueue1 到 topic Exchange 交换机1
     * @return
     */
    @Bean
    public Binding topicQueueBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(RabbitMqConstants.ROUTING_KEY_TOPIC_1);
    }
    
    /**
     * 绑定 topicQueue2 到 topic Exchange 交换机2
     * @return
     */
    @Bean
    public Binding topicQueueBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(RabbitMqConstants.ROUTING_KEY_TOPIC_2);
    }
    
    /**
     * 绑定 fanoutQueue 到 fanout Exchange （广播模式）交换机 1
     * @return
     */
    @Bean
    public Binding fanoutQueueBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
    
    /**
     * 绑定 fanoutQueue 到 fanout Exchange （广播模式）交换机 2
     * @return
     */
    @Bean
    public Binding fanoutQueueBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
    
    /**
     * 绑定 headersQueue 到 headers Exchange 交换机 1
     * @return
     */
    @Bean
    public Binding headersQueueBinding() {
        Map<String, Object> map = new HashMap<>();
        map.put("header1", "value1");
        map.put("header2", "value2");
        map.put("header3", "value3");
        // whereAll() 所有的 headers 都必须匹配
        // whereAny() 任一个 headers 匹配
        return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
    }
}

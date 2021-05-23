package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.constant.RabbitMqConstants;
import com.lft.miaosha.common.util.StringBeanConvert;
import com.lft.miaosha.entity.mo.MiaoshaMessageMo;
import com.lft.miaosha.service.MqSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class Name:      MQSender
 * Package Name:    com.lft.miaosha.rabbitmq
 * <p>
 * Function: 		A {@code MQSender} object With Some FUNCTION.
 * Date:            2021-05-22 12:40
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Service
public class MqSenderServiceImpl implements MqSenderService {
    private static Logger log = LoggerFactory.getLogger(MqSenderServiceImpl.class);
    @Autowired
    private AmqpTemplate amqpTemplate;
    
    @Override
    public void sendMiaoshaMessage(MiaoshaMessageMo miaoshaMessageMo) {
        String msg = StringBeanConvert.beanToString(miaoshaMessageMo);
        log.info("Send Message : " + msg);
        amqpTemplate.convertAndSend(RabbitMqConstants.TOPIC_EXCHANGE, RabbitMqConstants.ROUTING_KEY_MIAOSHA, msg);
    }
    //
    // @Override
    // public void sendDefaultMessage(Object message) {
    //     String msg = StringBeanConvert.beanToString(message);
    //     log.info("Send Default Message:" + msg);
    //     // 路由 key 直接指向队列名
    //     amqpTemplate.convertAndSend(RabbitMqConstants.DEFAULT_QUEUE, msg);
    // }
    //
    // @Override
    // public void sendDirectMessage(Object message) {
    //     String msg = StringBeanConvert.beanToString(message);
    //     log.info("Send Direct Message:" + msg);
    //     amqpTemplate.convertAndSend(RabbitMqConstants.DIRECT_EXCHANGE, RabbitMqConstants.ROUTING_KEY_DIRECT, msg);
    // }
    //
    // @Override
    // public void sendTopicMessage(Object message) {
    //     String msg = StringBeanConvert.beanToString(message);
    //     log.info("Send Topic Message 1:" + msg);
    //     amqpTemplate.convertAndSend(RabbitMqConstants.TOPIC_EXCHANGE, RabbitMqConstants.ROUTING_KEY_TOPIC_1, msg + "1");
    //     amqpTemplate.convertAndSend(RabbitMqConstants.TOPIC_EXCHANGE, RabbitMqConstants.ROUTING_KEY_TOPIC_2, msg + "2");
    // }
    //
    // @Override
    // public void sendFanoutMessage(Object message) {
    //     String msg = StringBeanConvert.beanToString(message);
    //     log.info("Send Topic Message 1:" + msg);
    //     amqpTemplate.convertAndSend(RabbitMqConstants.FANOUT_EXCHANGE, "", msg + "1");
    // }
    //
    // @Override
    // public void sendHeaderMessage(Object message) {
    //     String msg = StringBeanConvert.beanToString(message);
    //     log.info("Send Topic Message 1:" + msg);
    //     MessageProperties messageProperties = new MessageProperties();
    //     messageProperties.setHeader("header1", "value1");
    //     messageProperties.setHeader("header2", "value2");
    //     messageProperties.setHeader("header3", "value3");
    //     Message obj = new Message(msg.getBytes(), messageProperties);
    //     amqpTemplate.convertAndSend(RabbitMqConstants.HEADERS_EXCHANGE, "", obj);
    // }
}

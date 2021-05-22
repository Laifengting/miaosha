package com.lft.miaosha.rabbitmq;

import com.lft.miaosha.common.constant.RabbitMqConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Class Name:      MQReceive
 * Package Name:    com.lft.miaosha.rabbitmq
 * <p>
 * Function: 		A {@code MQReceive} object With Some FUNCTION.
 * Date:            2021-05-22 12:41
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Service
public class MQReceive {
    private static Logger log = LoggerFactory.getLogger(MQReceive.class);
    
    @RabbitListener (queues = RabbitMqConstants.DEFAULT_QUEUE)
    public <T> void receiveDefaultMessage(String msg) {
        log.info("Receive Default Queue Msg: " + msg);
    }
    
    @RabbitListener (queues = RabbitMqConstants.DIRECT_QUEUE)
    public <T> void receiveDirectMessage(String msg) {
        log.info("Receive Direct Queue Msg: " + msg);
    }
    
    @RabbitListener (queues = RabbitMqConstants.TOPIC_QUEUE_1)
    public <T> void receiveTopicQueue1Message(String msg) {
        log.info("Receive Topic Queue1 Msg: " + msg);
    }
    
    @RabbitListener (queues = RabbitMqConstants.TOPIC_QUEUE_2)
    public <T> void receiveTopicQueue2Message(String msg) {
        log.info("Receive Topic Queue2 Msg: " + msg);
    }
    
    @RabbitListener (queues = RabbitMqConstants.FANOUT_QUEUE_1)
    public <T> void receiveFanoutQueue1Message(String msg) {
        log.info("Receive Fanout Queue1 Msg: " + msg);
    }
    
    @RabbitListener (queues = RabbitMqConstants.FANOUT_QUEUE_2)
    public <T> void receiveFanoutQueue2Message(String msg) {
        log.info("Receive Fanout Queue2 Msg: " + msg);
    }
    
    @RabbitListener (queues = RabbitMqConstants.HEADERS_QUEUE)
    public <T> void receiveHeadersQueueMessage(byte[] msg) {
        log.info("Receive Headers Queue Msg: " + new String(msg));
    }
}

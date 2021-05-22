package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.constant.RabbitMqConstants;
import com.lft.miaosha.common.util.StringBeanConvert;
import com.lft.miaosha.entity.mo.MiaoshaMessageMo;
import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.entity.po.MiaoshaOrder;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.vo.OrderInfoVo;
import com.lft.miaosha.service.MiaoshaGoodsService;
import com.lft.miaosha.service.MiaoshaOrderService;
import com.lft.miaosha.service.MqReceiveService;
import com.lft.miaosha.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MqReceiveServiceImpl implements MqReceiveService {
    private static Logger log = LoggerFactory.getLogger(MqReceiveServiceImpl.class);
    
    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;
    
    @Autowired
    private MiaoshaOrderService miaoshaOrderService;
    
    @Autowired
    private RedisService redisService;
    
    @RabbitListener (queues = RabbitMqConstants.MIAOSHA_QUEUE)
    @Override
    public void receiveMiaoshaMessage(String msg) {
        log.info("Receive  Msg : " + msg);
        MiaoshaMessageMo mm = StringBeanConvert.stringToBean(msg, MiaoshaMessageMo.class);
        MiaoshaUser user = mm.getUser();
        Long goodsId = mm.getGoodsId();
        
        // 判断商品库存
        log.info("==================== 队列中收到消息后，判断数据库中库存 ====================");
        MiaoshaGoods miaoshaGoods = miaoshaGoodsService.getMiaoshaGoodsByGoodsId(goodsId);
        Integer stockCount = miaoshaGoods.getStockCount();
        if (stockCount <= 0) {
            return;
        }
        
        // 判断是否已经秒杀到了
        log.info("==================== 队列中收到消息后，判断该用户该商品是否已经有订单 ====================");
        MiaoshaOrder order = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return;
        }
        
        // 执行秒杀,生成 订单信息 和 秒杀订单
        log.info("==================== 队列中收到消息后，执行秒杀 ====================");
        OrderInfoVo orderInfoVo = miaoshaOrderService.miaosha(user, miaoshaGoods);
        
    }
    
    //
    // @RabbitListener (queues = RabbitMqConstants.DEFAULT_QUEUE)
    // public <T> void receiveDefaultMessage(String msg) {
    //     log.info("Receive Default Queue Msg: " + msg);
    // }
    //
    // @RabbitListener (queues = RabbitMqConstants.DIRECT_QUEUE)
    // public <T> void receiveDirectMessage(String msg) {
    //     log.info("Receive Direct Queue Msg: " + msg);
    // }
    //
    // @RabbitListener (queues = RabbitMqConstants.TOPIC_QUEUE_1)
    // public <T> void receiveTopicQueue1Message(String msg) {
    //     log.info("Receive Topic Queue1 Msg: " + msg);
    // }
    //
    // @RabbitListener (queues = RabbitMqConstants.TOPIC_QUEUE_2)
    // public <T> void receiveTopicQueue2Message(String msg) {
    //     log.info("Receive Topic Queue2 Msg: " + msg);
    // }
    //
    // @RabbitListener (queues = RabbitMqConstants.FANOUT_QUEUE_1)
    // public <T> void receiveFanoutQueue1Message(String msg) {
    //     log.info("Receive Fanout Queue1 Msg: " + msg);
    // }
    //
    // @RabbitListener (queues = RabbitMqConstants.FANOUT_QUEUE_2)
    // public <T> void receiveFanoutQueue2Message(String msg) {
    //     log.info("Receive Fanout Queue2 Msg: " + msg);
    // }
    //
    // @RabbitListener (queues = RabbitMqConstants.HEADERS_QUEUE)
    // public <T> void receiveHeadersQueueMessage(byte[] msg) {
    //     log.info("Receive Headers Queue Msg: " + new String(msg));
    // }
}

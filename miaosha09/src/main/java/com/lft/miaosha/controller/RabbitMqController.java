package com.lft.miaosha.controller;

import com.lft.miaosha.common.result.R;
import com.lft.miaosha.rabbitmq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class Name:      SimpleController
 * Package Name:    com.lft.miaosha01.controller
 * <p>
 * Function: 		A {@code SimpleController} object With Some FUNCTION.
 * Date:            2021-05-14 20:28
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Controller
@RequestMapping ("simple")
public class RabbitMqController {
    
    @Autowired
    private MQSender mqSender;
    
    @RequestMapping ("/mq/default")
    @ResponseBody
    public R mq1() {
        mqSender.sendDefaultMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
        return R.OK().message("Hello, RabbitMQ");
    }
    
    @RequestMapping ("/mq/direct")
    @ResponseBody
    public R mq2() {
        mqSender.sendDirectMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
        return R.OK().message("Hello, RabbitMQ");
    }
    
    @RequestMapping ("/mq/topic")
    @ResponseBody
    public R mq3() {
        mqSender.sendTopicMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
        return R.OK().message("Hello, RabbitMQ");
    }
    
    @RequestMapping ("/mq/fanout")
    @ResponseBody
    public R mq4() {
        mqSender.sendFanoutMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
        return R.OK().message("Hello, RabbitMQ");
    }
    
    @RequestMapping ("/mq/headers")
    @ResponseBody
    public R mq5() {
        mqSender.sendHeaderMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
        return R.OK().message("Hello, RabbitMQ");
    }
}

package com.lft.miaosha.controller;

import com.lft.miaosha.service.impl.MqSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private MqSenderServiceImpl mqSenderServiceImpl;
    //
    // @RequestMapping ("/mq/default")
    // @ResponseBody
    // public R mq1() {
    //     mqSenderServiceImpl.sendDefaultMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
    //     return R.OK().message("Hello, RabbitMQ");
    // }
    //
    // @RequestMapping ("/mq/direct")
    // @ResponseBody
    // public R mq2() {
    //     mqSenderServiceImpl.sendDirectMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
    //     return R.OK().message("Hello, RabbitMQ");
    // }
    //
    // @RequestMapping ("/mq/topic")
    // @ResponseBody
    // public R mq3() {
    //     mqSenderServiceImpl.sendTopicMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
    //     return R.OK().message("Hello, RabbitMQ");
    // }
    //
    // @RequestMapping ("/mq/fanout")
    // @ResponseBody
    // public R mq4() {
    //     mqSenderServiceImpl.sendFanoutMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
    //     return R.OK().message("Hello, RabbitMQ");
    // }
    //
    // @RequestMapping ("/mq/headers")
    // @ResponseBody
    // public R mq5() {
    //     mqSenderServiceImpl.sendHeaderMessage("Hello,I'm RabbitMQ,I'm sending Message!!!!!");
    //     return R.OK().message("Hello, RabbitMQ");
    // }
}

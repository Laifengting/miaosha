package com.lft.miaosha.controller;

import com.lft.miaosha.common.result.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class SimpleController {
    /*
     * Controller 有两种输出方式：
     * 1. Rest API Json 输出
     * 2. 页面
     */
    @RequestMapping ("/")
    @ResponseBody
    public String index() {
        return "Hello__World!";
    }
    
    @RequestMapping ("/hello")
    @ResponseBody
    public R hello() {
        return R.OK().message("Hello-------------World!!");
    }
    
    @RequestMapping ("/hello/error")
    @ResponseBody
    public R helloError() {
        return R.ERROR().message("Wrong~~~~~~~~~~~~~~~~~~~~");
    }
    
    @RequestMapping ("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "我是你爸爸");
        return "hello";
    }
}

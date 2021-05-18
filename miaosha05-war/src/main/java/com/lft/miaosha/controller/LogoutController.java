package com.lft.miaosha.controller;

import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class Name:      LoginController
 * Package Name:    com.lft.miaosha.controller
 * <p>
 * Function: 		A {@code LoginController} object With Some FUNCTION.
 * Date:            2021-05-15 14:00
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Controller
@RequestMapping ("logout")
public class LogoutController {
    private static Logger log = LoggerFactory.getLogger(LogoutController.class);
    
    @Autowired
    MiaoshaUserService miaoshaUserService;
    
    @RequestMapping ("to/logout")
    public String toLogin(Model model, MiaoshaUser miaoshaUser) {
        miaoshaUser = null;
        model.addAttribute("user", miaoshaUser);
        return "login";
    }
}

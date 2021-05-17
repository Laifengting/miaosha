package com.lft.miaosha.controller;

import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class Name:      GoodsController
 * Package Name:    com.lft.miaosha.controller
 * <p>
 * Function: 		A {@code GoodsController} object With Some FUNCTION.
 * Date:            2021-05-15 16:06
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Controller
@RequestMapping ("order")
public class OrderInfoController {
    
    @Autowired
    MiaoshaUserService miaoshaUserService;
    
    @RequestMapping ("to/list")
    public String toList(Model model, MiaoshaUser miaoshaUser) {
        if (miaoshaUser == null) {
            return "login";
        } else {
            // 将用户添加到 model 属性中
            model.addAttribute("user", miaoshaUser);
            return "order";
        }
    }
}

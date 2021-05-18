package com.lft.miaosha.controller;

import com.lft.miaosha.common.result.R;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping ("user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    MiaoshaUserService miaoshaUserService;
    
    @RequestMapping ("info")
    @ResponseBody
    public R info(MiaoshaUser user) {
        return R.OK().data("user", user);
    }
}

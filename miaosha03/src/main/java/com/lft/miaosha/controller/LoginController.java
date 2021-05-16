package com.lft.miaosha.controller;

import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.entity.dto.LoginDto;
import com.lft.miaosha.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

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
@RequestMapping ("login")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    MiaoshaUserService miaoshaUserService;
    
    @RequestMapping ("to/login")
    public String toLogin() {
        return "login";
    }
    
    @RequestMapping ("do/login")
    @ResponseBody
    public R doLogin(@Valid LoginDto loginDto) {
        log.info(loginDto.toString());
        // 参数校验
        String password = loginDto.getPassword();
        String mobile = loginDto.getMobile();
        // 登录
        ResultCode resultCode = miaoshaUserService.login(loginDto);
        if (resultCode.getCode() == 200100) {
            return R.OK().code(resultCode.getCode()).message(resultCode.getMessage());
        } else {
            return R.ERROR().code(resultCode.getCode()).message(resultCode.getMessage());
        }
    }
}

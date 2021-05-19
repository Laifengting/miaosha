package com.lft.miaosha.controller;

import com.lft.miaosha.common.result.R;
import com.lft.miaosha.entity.dto.LoginDto;
import com.lft.miaosha.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    
    /**
     * JMeter 压测 5000*10 三次
     * QPS 2067.3 个/s
     * QPS 3152.6 个/s
     * QPS 3345.4 个/s
     * @param loginDto
     * @param request
     * @param response
     * @return
     */
    @RequestMapping ("do/login")
    @ResponseBody
    public R doLogin(@Valid LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        log.info(loginDto.toString());
        // 登录
        R resultCode = miaoshaUserService.login(loginDto, request, response);
        if (resultCode.getCode() == 200100) {
            return resultCode;
        } else {
            return R.ERROR().code(resultCode.getCode()).message(resultCode.getMsg());
        }
    }
}

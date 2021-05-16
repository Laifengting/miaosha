package com.lft.miaosha.controller;

import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.entity.dto.RegisterDto;
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
@RequestMapping ("register")
public class RegisterController {
    private static Logger log = LoggerFactory.getLogger(RegisterController.class);
    
    @Autowired
    MiaoshaUserService miaoshaUserService;
    
    @RequestMapping ("to/register")
    public String toRegister() {
        log.info("跳转到注册页面");
        return "register";
    }
    
    @RequestMapping ("do/register")
    @ResponseBody
    public R doLogin(@Valid RegisterDto registerDto) {
        log.info(registerDto.toString());
        // 参数校验
        String password = registerDto.getPassword();
        String nickname = registerDto.getNickname();
        String mobile = registerDto.getMobile();
        // 注册
        ResultCode resultCode = miaoshaUserService.register(registerDto);
        int j = 10 / 0;
        if (resultCode.getCode() == 200200) {
            return R.OK().code(resultCode.getCode()).message(resultCode.getMessage());
        } else {
            return R.ERROR().code(resultCode.getCode()).message(resultCode.getMessage());
        }
    }
}

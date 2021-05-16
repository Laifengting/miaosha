package com.lft.miaosha.service;

import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.entity.dto.LoginDto;
import com.lft.miaosha.entity.dto.RegisterDto;
import com.lft.miaosha.entity.po.MiaoshaUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class Name:      MiaoshaUserService
 * Package Name:    com.lft.miaosha.service
 * <p>
 * Function: 		A {@code MiaoshaUserService} object With Some FUNCTION.
 * Date:            2021-05-15 15:32
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public interface MiaoshaUserService {
    MiaoshaUser getUserById(Long id);
    
    R login(LoginDto loginDto, HttpServletRequest request, HttpServletResponse response);
    
    ResultCode register(RegisterDto registerDto);
    
    MiaoshaUser getUserFromCache(String cookieToken, HttpServletResponse response);
    
    public String getTokenFromCookie(HttpServletRequest request, String cookieNameToken);
}

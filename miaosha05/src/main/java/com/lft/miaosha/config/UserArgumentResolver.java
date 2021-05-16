package com.lft.miaosha.config;

import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.service.MiaoshaUserService;
import com.lft.miaosha.service.impl.MiaoshaUserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class Name:      UserArgumentResolver
 * Package Name:    com.lft.miaosha.config
 * <p>
 * Function: 		A {@code UserArgumentResolver} object With Some FUNCTION.
 * Date:            2021-05-16 13:46
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    MiaoshaUserService miaoshaUserService;
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();
        return parameterType == MiaoshaUser.class;
    }
    
    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String paramToken = request.getParameter(MiaoshaUserServiceImpl.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, MiaoshaUserServiceImpl.COOKIE_NAME_TOKEN);
        
        // 如果都是空返回登录页面
        if (StringUtils.isEmpty(paramToken) && StringUtils.isEmpty(cookieToken)) {
            return null;
        }
        // 参数优先于缓存
        String token = StringUtils.isNotEmpty(paramToken) ? paramToken : cookieToken;
        return miaoshaUserService.getUserFromCache(token, response);
    }
    
    private String getCookieValue(HttpServletRequest request, String cookieNameToken) {
        return miaoshaUserService.getTokenFromCookie(request, cookieNameToken);
    }
}

package com.lft.miaosha.resolver;

import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.interceptor.context.MiaoshaUserContext;
import com.lft.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

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
        return MiaoshaUserContext.getUser();
    }
}

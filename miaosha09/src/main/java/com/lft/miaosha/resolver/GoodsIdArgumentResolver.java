package com.lft.miaosha.resolver;

import com.lft.miaosha.common.constant.RedisConstants;
import com.lft.miaosha.service.MiaoshaUserService;
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
public class GoodsIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    MiaoshaUserService miaoshaUserService;
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();
        return parameterType == Long.class;
    }
    
    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String goodsId = request.getParameter(RedisConstants.GOODS_KEY_SUFFIX_GOODS_ID);
        if (StringUtils.isEmpty(goodsId)) {
            return null;
        }
        Long result = Long.parseLong(goodsId);
        return result;
    }
}

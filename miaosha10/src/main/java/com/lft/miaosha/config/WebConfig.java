package com.lft.miaosha.config;

import com.lft.miaosha.interceptor.AccessLimitInterceptor;
import com.lft.miaosha.interceptor.CheckParamInterceptor;
import com.lft.miaosha.resolver.GoodsIdArgumentResolver;
import com.lft.miaosha.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Class Name:      WebConfig
 * Package Name:    com.lft.miaosha.config
 * <p>
 * Function: 		A {@code WebConfig} object With Some FUNCTION.
 * Date:            2021-05-16 13:43
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    UserArgumentResolver userArgumentResolver;
    @Autowired
    GoodsIdArgumentResolver goodsIdArgumentResolver;
    
    @Autowired
    CheckParamInterceptor checkParamInterceptor;
    
    @Autowired
    AccessLimitInterceptor accessLimitInterceptor;
    
    /**
     * 添加参数解析器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
        argumentResolvers.add(goodsIdArgumentResolver);
    }
    
    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 这个地方可以定义拦截器的具体的拦截路径
        registry.addInterceptor(accessLimitInterceptor).addPathPatterns("/**");
    }
}

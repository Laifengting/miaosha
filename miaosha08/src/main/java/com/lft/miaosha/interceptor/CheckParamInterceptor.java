package com.lft.miaosha.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lft.miaosha.annotation.ParamNotNull;
import com.lft.miaosha.common.result.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Name:      CheckParamInterceptor
 * Package Name:    com.lft.miaosha.annotation
 * <p>
 * Function: 		拦截请求方法中的参数。不能为空
 * Date:            2021-05-20 13:17
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Component
public class CheckParamInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        List<String> list = getParamsName((HandlerMethod) handler);
        for (String s : list) {
            String parameter = request.getParameter(s);
            if (StringUtils.isEmpty(parameter)) {
                JSONObject jsonObject = new JSONObject();
                // 这个地方是定义缺省参数或者参数为空时返回的数据
                jsonObject.put("code", ResultCode.LOGIN_ERROR.getCode());
                jsonObject.put("msg", "缺少必要的" + s + "值，请登录后再操作");
                response.setHeader("Content-type", "application/json;charset=UTF-8");
                response.setHeader("Access-Control-Allow-Origin", "*");//跨域
                response.getWriter().write(jsonObject.toJSONString());
                return false;
            }
        }
        return true;
    }
    
    private List<String> getParamsName(HandlerMethod handlerMethod) {
        Parameter[] parameters = handlerMethod.getMethod().getParameters();
        List<String> list = new ArrayList<>();
        for (Parameter parameter : parameters) {
            // 判断这个参数是否被加入了 ParamNotNull 的注解
            // .isAnnotationPresent()  这个方法可以看一下
            if (parameter.isAnnotationPresent(ParamNotNull.class)) {
                list.add(parameter.getName());
            }
        }
        return list;
    }
}

package com.lft.miaosha.interceptor;

import com.alibaba.fastjson.JSON;
import com.lft.miaosha.annotation.AccessLimit;
import com.lft.miaosha.common.constant.RedisConstants;
import com.lft.miaosha.common.key.impl.MsUserKeyPrefix;
import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.interceptor.context.MiaoshaUserContext;
import com.lft.miaosha.service.MiaoshaUserService;
import com.lft.miaosha.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class AccessLimitInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private MiaoshaUserService miaoshaUserService;
    
    @Autowired
    private RedisService redisService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 先处理用户登录问题
        MiaoshaUser user = getUser(request, response);
        MiaoshaUserContext.setUser(user);
        
        HandlerMethod hm = (HandlerMethod) handler;
        AccessLimit accessLimitAnnotaion = hm.getMethodAnnotation(AccessLimit.class);
        if (accessLimitAnnotaion == null) {
            return true;
        }
        
        int seconds = accessLimitAnnotaion.seconds();
        int maxCount = accessLimitAnnotaion.maxCount();
        boolean loginRequired = accessLimitAnnotaion.loginRequired();
        String key = request.getRequestURI();
        
        if (loginRequired) {
            if (user == null) {
                render(response, R.ERROR().code(ResultCode.LOGIN_REQUIRED_ERROR.getCode())
                                  .message(ResultCode.LOGIN_REQUIRED_ERROR.getMessage()));
                return false;
            }
            key += RedisConstants.SPILT + user.getId();
        } else {
            // do nothing
        }
        // 检验请求次数
        MsUserKeyPrefix prefix = new MsUserKeyPrefix(seconds, RedisConstants.USER_KEY_SUFFIX_GET_URI_REQUEST_COUNT_BY_UID);
        Integer count = redisService.get(prefix, key, Integer.class);
        if (count == null) {
            redisService.set(prefix, key, 1);
        } else if (count < maxCount) {
            redisService.incr(prefix, key);
        } else {
            render(response, R.ERROR().code(ResultCode.MIAOSHAO_ACCESS_LIMIT_REACHED_ERROR.getCode())
                              .message(ResultCode.MIAOSHAO_ACCESS_LIMIT_REACHED_ERROR.getMessage()));
        }
        return true;
    }
    
    private void render(HttpServletResponse response, R r) throws Exception {
        response.setCharacterEncoding("utf-8");    //设置 HttpServletResponse使用utf-8编码
        response.setHeader("Content-Type", "application/json; charset=UTF-8");  //设置响应头的编码
        ServletOutputStream out = response.getOutputStream();
        String string = JSON.toJSONString(r);
        out.write(string.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
    
    private MiaoshaUser getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(RedisConstants.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, RedisConstants.COOKIE_NAME_TOKEN);
        
        // 如果都是空返回登录页面
        if (StringUtils.isEmpty(paramToken) && StringUtils.isEmpty(cookieToken)) {
            return null;
        }
        // 参数优先于 cookie
        String token = StringUtils.isNotEmpty(paramToken) ? paramToken : cookieToken;
        return miaoshaUserService.getUserFromCache(token, response);
    }
    
    private String getCookieValue(HttpServletRequest request, String cookieNameToken) {
        return miaoshaUserService.getTokenFromCookie(request, cookieNameToken);
    }
}

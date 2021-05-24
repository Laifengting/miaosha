package com.lft.miaosha.interceptor.context;

import com.lft.miaosha.entity.po.MiaoshaUser;

/**
 * Class Name:      MiaoshaUserContext
 * Package Name:    com.lft.miaosha.interceptor.context
 * <p>
 * Function: 		A {@code MiaoshaUserContext} object With Some FUNCTION.
 * Date:            2021-05-24 13:15
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class MiaoshaUserContext {
    
    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<>();
    
    public static void setUser(MiaoshaUser user) {
        userHolder.set(user);
    }
    
    public static MiaoshaUser getUser() {
        return userHolder.get();
    }
}


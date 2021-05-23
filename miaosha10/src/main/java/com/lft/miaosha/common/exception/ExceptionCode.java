package com.lft.miaosha.common.exception;

/**
 * Class Name:      StatusCode
 * Package Name:    com.lft.miaosha01.common
 * <p>
 * Function: 		A {@code StatusCode} object With Some FUNCTION.
 * Date:            2021-05-14 21:00
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public enum ExceptionCode {
    //===================================== 通用JAVA异常 =====================================//
    EXCEPTION(500800, "异常"),
    NULL_POINTER_EXCEPTION(500801, "空指针异常"),
    ILLEGAL_ARGUMENT_EXCEPTION(500802, "非法参数异常"),
    BIND_EXCEPTION(500803, "绑定异常"),
    ARITHMETIC_EXCEPTION(500804, "算术异常"),
    NULL_OBJECT_EXCEPTION(500805, "空对象异常"),
    
    //===================================== 登录注册异常 =====================================//
    LOGIN_EXCEPTION(500806, "登录异常"),
    WRONG_PASSWORD_EXCEPTION(500807, "密码错误异常"),
    
    //===================================== 商品异常 =====================================//
    NULL_TOKEN_EXCEPTION(500808, "空token异常"),
    USER_NOT_IN_CACHE_EXCEPTION(500809, "缓存中无数据或已过期"),
    UPDATE_LOGIN_COUNT_AND_LAST_LOGIN_TIME_EXCEPTION(500810, "更新登录次数和上次登录时间异常"),
    
    //===================================== 秒杀商品异常 =====================================//
    CREATE_ORDER_EXCEPTION(500811, "创建订单失败异常"),
    CREATE_MIAOSHA_ORDER_EXCEPTION(500812, "创建秒杀订单失败异常"),
    NULL_ADDRESS_EXCEPTION(500813, "空地址异常"),
    NULL_ARGUMENT_EXCEPTION(500814, "空参数异常"),
    
    //===================================== 订单异常 =====================================//
    MOBILE_NOT_EXISTS_EXCEPTION(500815, "手机号码不存在异常"),
    ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION(500816, "数组索引越界异常"),
    
    //===================================== 秒杀订单异常 =====================================//
    
    //===================================== 用户异常 =====================================//
    USERINFO_UPDATE_FAILED_EXCEPTION(500817, "用户信息更新失败异常");
    
    private Integer code;
    private String message;
    
    ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Integer getCode() {
        return this.code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}

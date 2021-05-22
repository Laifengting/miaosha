package com.lft.miaosha.common.result;

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
public enum ResultCode {
    //######################### 成功枚举 #########################//
    /**
     * 正常
     */
    OK(200000, "成功"),
    /**
     * 登录成功
     */
    LOGIN_SUCCESSR(200100, "登录成功"),
    /**
     * 注册成功
     */
    REGISTER_SUCCESSR(200200, "注册成功"),
    
    //######################### 错误枚举 #########################//
    /**
     * 失败
     */
    ERROR(500100, "失败"),
    /**
     * 服务端错误
     */
    SERVER_ERROR(500200, "服务端错误"),
    /**
     * 登录错误
     */
    LOGIN_ERROR(500300, "登录错误，请尝试重新登录"),
    /**
     * 注册错误
     */
    REGISTER_ERROR(500303, "注册错误"),
    /**
     * 注册失败
     */
    REGISTER_FAILED_ERROR(500313, "注册失败"),
    /**
     * 密码为空
     */
    PASSWORD_EMPTY_ERROR(500301, "密码不能为空"),
    /**
     * 手机号码为空
     */
    MOBILE_EMPTY_ERROR(500302, "手机号码不能为空"),
    /**
     * 手机号码格式错误
     */
    MOBILE_ERROR(500303, "手机号码格式错误"),
    /**
     * 手机号码不存在
     */
    MOBILE_NOT_FOUND_ERROR(500304, "手机号码不存在"),
    /**
     * 手机号码已经存在
     */
    MOBILE_FOUND_ERROR(500314, "手机号码已经存在"),
    /**
     * 密码错误
     */
    WRONG_PASSWORD_ERROR(500305, "密码错误"),
    
    /**
     * 商品错误
     */
    PRODUCT_ERROR(500400, "商品错误"),
    
    /**
     * 订单错误
     */
    ORDER_ERROR(500500, "订单错误"),
    
    /**
     * 秒杀错误
     */
    MIAOSHA_ERROR(500600, "秒杀错误"),
    
    /**
     * 重复下单错误
     */
    ORDER_REPEAT_ERROR(500601, "重复下单错误"),
    
    /**
     * 秒杀结束
     */
    MIAOSHA_OVER_ERROR(500602, "秒杀结束"),
    
    /**
     * 秒杀错误
     */
    NO_STOCK_ERROR(500611, "库存不足错误");
    
    private Integer code;
    private String message;
    
    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Integer getCode() {
        return this.code;
    }
    
    public String getMessage() {
        return message;
    }
}

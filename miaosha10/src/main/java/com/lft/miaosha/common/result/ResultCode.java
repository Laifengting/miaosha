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
    //######################### 失败枚举 #########################//
    /**
     * 失败
     */
    ERROR(500100, "失败"),
    
    //######################### 登录注册枚举 #########################//
    /**
     * 登录成功
     */
    LOGIN_SUCCESSR(200100, "登录成功"),
    /**
     * 注册成功
     */
    REGISTER_SUCCESSR(200200, "注册成功"),
    
    //######################### 秒杀枚举 #########################//
    /**
     * 秒杀排队中
     */
    QUEUE_UP(300000, "秒杀排队中..."),
    
    //######################### 系统错误枚举 #########################//
    /**
     * 服务端错误
     */
    SERVER_ERROR(500200, "服务端错误"),
    
    //######################### 登录注册错误枚举 #########################//
    /**
     * 注册错误
     */
    REGISTER_ERROR(500301, "注册错误"),
    /**
     * 注册失败
     */
    REGISTER_FAILED_ERROR(500302, "注册失败"),
    /**
     * 需要登录
     */
    LOGIN_REQUIRED_ERROR(500303, "请登录后再操作"),
    /**
     * 登录错误
     */
    LOGIN_ERROR(500304, "登录错误，请尝试重新登录"),
    
    //######################### 用户信息错误枚举，号段 500300-500399 #########################//
    //=== 手机号码相关 ===//
    /**
     * 手机号码为空
     */
    MOBILE_EMPTY_ERROR(500305, "手机号码不能为空"),
    /**
     * 手机号码格式错误
     */
    MOBILE_ERROR(500306, "手机号码格式错误"),
    /**
     * 手机号码不存在
     */
    MOBILE_NOT_FOUND_ERROR(500307, "手机号码不存在"),
    /**
     * 手机号码已经存在
     */
    MOBILE_FOUND_ERROR(500308, "手机号码已经存在"),
    
    //=== 密码相关 ===//
    /**
     * 密码为空
     */
    PASSWORD_EMPTY_ERROR(500309, "密码不能为空"),
    /**
     * 密码错误
     */
    WRONG_PASSWORD_ERROR(500310, "密码错误"),
    
    //######################### 商品信息错误枚举，号段 500400-500499 #########################//
    //=== 商品相关 ===//
    /**
     * 商品错误
     */
    PRODUCT_ERROR(500400, "商品错误"),
    
    //######################### 秒杀操作错误枚举，号段 500500-500599 #########################//
    //=== 秒杀相关 ===//
    /**
     * 访问太频繁
     */
    MIAOSHAO_ACCESS_LIMIT_REACHED_ERROR(500501, "访问太频繁"),
    /**
     * 验证码已经过期，请重新获取验证码
     */
    MIAOSHAO_VERDUE_VERIFY_CODE_ERROR(500502, "验证码已经过期，请重新获取验证码"),
    /**
     * 验证码输入错误，请重新输入验证码
     */
    MIAOSHAO_WRONG_VERIFY_CODE_ERROR(500503, "验证码错误，请重新输入验证码"),
    
    //######################### 秒杀商品错误枚举，号段 500600-500699 #########################//
    //=== 秒杀商品相关 ===//
    /**
     * 秒杀错误
     */
    MIAOSHA_ERROR(500601, "秒杀错误"),
    
    /**
     * 秒杀结束
     */
    MIAOSHA_OVER_ERROR(500602, "秒杀结束"),
    
    /**
     * 秒杀错误
     */
    NO_STOCK_ERROR(500603, "库存不足错误"),
    
    //######################### 订单错误枚举，号段 500700-500799 #########################//
    //=== 订单相关 ===//
    /**
     * 订单错误
     */
    ORDER_ERROR(500701, "订单错误"),
    
    /**
     * 重复下单错误
     */
    ORDER_REPEAT_ERROR(500702, "重复下单错误"),
    
    /**
     * 秒杀路径错误
     */
    MIAOSHA_PATH_ERROR(500703, "秒杀路径错误");
    
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

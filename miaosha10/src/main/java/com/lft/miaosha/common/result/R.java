package com.lft.miaosha.common.result;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name:      R
 * Package Name:    com.lft.miaosha01.common
 * <p>
 * Function: 		A {@code R} object With Some FUNCTION.
 * Date:            2021-05-14 20:32
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class R {
    /**
     * 是否成功标记
     */
    private Boolean success;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Map<String, Object> data = new HashMap<>();
    
    /**
     * 私有化构造器
     */
    private R() {
    }
    
    /**
     * 静态方法返回 OK
     * @return
     */
    public static R OK() {
        R r = new R();
        r.setSuccess(true).setCode(ResultCode.OK.getCode()).setMsg(ResultCode.OK.getMessage());
        return r;
    }
    
    /**
     * 静态方法返回 ERROR
     * @return
     */
    public static R ERROR() {
        R r = new R();
        r.setSuccess(false).setCode(ResultCode.ERROR.getCode()).setMsg(ResultCode.ERROR.getMessage());
        return r;
    }
    
    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }
    
    public R message(String message) {
        this.setMsg(message);
        return this;
    }
    
    public R code(Integer code) {
        this.setCode(code);
        return this;
    }
    
    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    
    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
    
    /**
     * 常用的getter setter 方法
     * @return
     */
    public Boolean getSuccess() {
        return success;
    }
    
    public R setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public R setCode(Integer code) {
        this.code = code;
        return this;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public R setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    
    public Map<String, Object> getData() {
        return data;
    }
    
    public R setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}

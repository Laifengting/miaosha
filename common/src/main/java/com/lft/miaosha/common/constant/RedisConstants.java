package com.lft.miaosha.common.constant;

public class RedisConstants {
    
    /**
     * key 分隔符
     */
    public static final String SPILT = ":";
    
    /**
     * redis库0  没指定时默认保存位置
     */
    public static final Integer DATEBASE0 = 0;
    
    /**
     * redis库1  保存档案树
     */
    public static final Integer DATEBASE1 = 1;
    
    /**
     * redis库2 保存档案表格
     * 保存分页码
     */
    public static final Integer DATEBASE2 = 2;
    
    /**
     * redis库3 保存档案image url
     */
    public static final Integer DATEBASE3 = 3;
    
    /**
     * redis库4 保存手机验证码
     */
    public static final Integer DATEBASE4 = 4;
    
    /**
     * redis库5 保存身份认证信息
     */
    public static final Integer DATEBASE5 = 5;
    
    /**
     * redis库6 记录身份认证次数
     */
    public static final Integer DATEBASE6 = 6;
    
    /**
     * redis库7 记录重发次数
     */
    public static final Integer DATEBASE7 = 7;
    
    /**
     * redis库8 记录任务参数
     */
    public static final Integer DATEBASE8 = 8;
    
    /**
     * id
     */
    public static final String ID = "id";
    /**
     * 姓名
     */
    public static final String NAME = "name";
    /**
     * 手机号
     */
    public static final String PHONE = "phone";
    /**
     * 时间日期
     */
    public static final String DATE = "date";
    /**
     * 订单号
     */
    public static final String ORDER_ID = "order:id";
    /**
     * 地址
     */
    public static final String ADDRESS = "address";
}

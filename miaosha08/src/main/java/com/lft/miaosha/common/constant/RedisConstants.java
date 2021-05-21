package com.lft.miaosha.common.constant;

public class RedisConstants {
    //================================== Redis Key 分隔符常量 ==================================//
    /**
     * key 分隔符
     */
    public static final String SPILT = ":";
    
    //================================== Redis 数据库 ==================================//
    
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
    
    //================================== COOKIE 常量 ==================================//
    /**
     * token
     */
    public static final String COOKIE_NAME_TOKEN = "token";
    
    //================================== 用户常量 ==================================//
    /**
     * 用户 id 手机号
     */
    public static final String USER_KEY_SUFFIX_ID = "id";
    /**
     * 根据手机号码获取 user
     */
    public static final String USER_KEY_SUFFIX_GET_USER_BY_UID = "get:user:by:uid";
    
    /**
     * 根据 token 获取用户
     */
    public static final String USER_KEY_SUFFIX_GET_USER_BY_TOKEN = "get:user:by:token";
    
    //================================== 订单常量 ==================================//
    /**
     * 订单号
     */
    public static final String ORDER_KEY_SUFFIX_ORDER_ID = "orderId";
    
    /**
     * 根据 orderId 获取 订单信息
     */
    public static final String ORDER_KEY_SUFFIX_GET_ORDERINFO_BY_OID = "get:orderInfo:by:oid";
    
    /**
     * 地址
     */
    public static final String ORDER_KEY_SUFFIX_ADDRESS = "address";
    
    //================================== 商品常量 ==================================//
    /**
     * 商品列表
     */
    public static final String GOODS_KEY_SUFFIX_GOODS_ID = "goodId";
    
    /**
     * 商品列表
     */
    public static final String GOODS_KEY_SUFFIX_GET_HTML_FOR_GOODS_LIST = "get:html:for:goodsList";
    
    /**
     * 商品详情
     */
    public static final String GOODS_KEY_SUFFIX_GET_HTML_FOR_GOODS_DETAIL_BY_GID = "get:html:for:goodsdetail:by:gid";
    
    /**
     * 通过商品 id 获取 GoodsVo
     */
    public static final String GOODS_KEY_SUFFIX_GET_GOODSVO_BY_GOODS_ID = "get:gv:by:gid";
    
    /**
     * 获取所有商品 vo
     */
    public static final String GOODS_KEY_SUFFIX_GET_ALL_GOODSVOS = "get:all:goodsvos";
    
    //================================== 地址常量 ==================================//
    /**
     * 地址 id
     */
    public static final String ADDRESS_KEY_SUFFIX_ADDRESS_ID = "addressId";
    
    /**
     * 通过 userId 和 addressId 获取 Address
     */
    public static final String ADDRESS_KEY_SUFFIX_GET_ADDRESS_BY_UID_AID = "get:add:by:uid:aid";
    
    /**
     * 获取某一用户的所有收货地址
     */
    public static final String ADDRESS_KEY_SUFFIX_GET_ALL_ADDRS_BY_UID = "get:all:addrs:by:uid";
    
    //================================== 秒杀商品常量 ==================================//
    /**
     * 秒杀商品列表
     */
    public static final String MS_GOODS_KEY_SUFFIX_GOODS_ID = "ms:goods:id";
    
    /**
     *
     */
    public static final String MS_GOODS_KEY_SUFFIX_GET_MSGOODS_BY_GID = "get:msgoodsList:by:gid";
    
    /**
     * 通过 userId 和 goodsId 获取 MiaoshaGoods
     */
    public static final String MS_GOODS_KEY_SUFFIX_GET_MSGOODS_BY_UID_GID = "get:msgoods:by:uid:gid";
    
    //================================== 秒杀订单常量 ==================================//
    public static final String MS_ORDER_KEY_SUFFIX_GET_MSORDER_BY_UID_GID = "get:msorder:by:uid:gid";
    
}

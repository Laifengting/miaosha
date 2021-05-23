package com.lft.miaosha.entity.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class Name:      OrderInfo
 * Package Name:    com.lft.miaosha.entity.po
 * <p>
 * Function: 		A {@code OrderInfo} object With Some FUNCTION.
 * Date:            2021-05-16 15:47
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class OrderInfo {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddressId;
    private String goodsName;
    /**
     * 下单数量
     */
    private Integer goodsCount;
    private BigDecimal goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date gmtCreated;
    private Date gmtPay;
    
    public OrderInfo() {
    }
    
    public OrderInfo(Long id, Long userId, Long goodsId, Long deliveryAddressId, String goodsName, Integer goodsCount,
                     BigDecimal goodsPrice, Integer orderChannel, Integer status, Date gmtCreated, Date gmtPay) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.deliveryAddressId = deliveryAddressId;
        this.goodsName = goodsName;
        this.goodsCount = goodsCount;
        this.goodsPrice = goodsPrice;
        this.orderChannel = orderChannel;
        this.status = status;
        this.gmtCreated = gmtCreated;
        this.gmtPay = gmtPay;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getGoodsId() {
        return goodsId;
    }
    
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    
    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }
    
    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }
    
    public String getGoodsName() {
        return goodsName;
    }
    
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public Integer getGoodsCount() {
        return goodsCount;
    }
    
    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
    
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }
    
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    
    public Integer getOrderChannel() {
        return orderChannel;
    }
    
    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Date getGmtCreated() {
        return gmtCreated;
    }
    
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
    
    public Date getGmtPay() {
        return gmtPay;
    }
    
    public void setGmtPay(Date gmtPay) {
        this.gmtPay = gmtPay;
    }
    
    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", deliveryAddressId=" + deliveryAddressId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCount=" + goodsCount +
                ", goodsPrice=" + goodsPrice +
                ", orderChannel=" + orderChannel +
                ", status=" + status +
                ", gmtCreated=" + gmtCreated +
                ", gmtPay=" + gmtPay +
                '}';
    }
}

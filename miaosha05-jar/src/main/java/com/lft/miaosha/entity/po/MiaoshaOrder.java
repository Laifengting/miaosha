package com.lft.miaosha.entity.po;

import java.util.Date;

/**
 * Class Name:      Goods
 * Package Name:    com.lft.miaosha.entity.po
 * <p>
 * Function: 		A {@code Goods} object With Some FUNCTION.
 * Date:            2021-05-16 15:41
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class MiaoshaOrder {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
    private Date gmtCreated;
    
    public MiaoshaOrder() {
    }
    
    public MiaoshaOrder(Long id, Long userId, Long orderId, Long goodsId, Date gmtCreated) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.gmtCreated = gmtCreated;
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
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public Long getGoodsId() {
        return goodsId;
    }
    
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    
    public Date getGmtCreated() {
        return gmtCreated;
    }
    
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
    
    @Override
    public String toString() {
        return "MiaoshaOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", gmtCreated=" + gmtCreated +
                '}';
    }
}

package com.lft.miaosha.entity.po;

import java.math.BigDecimal;
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
public class MiaoshaGoods {
    private Long id;
    private Long goodsId;
    private BigDecimal miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Date gmtCreated;
    
    public MiaoshaGoods() {
    }
    
    public MiaoshaGoods(Long id, Long goodsId, Integer stockCount, Date startDate, Date endDate, Date gmtCreated) {
        this.id = id;
        this.goodsId = goodsId;
        this.stockCount = stockCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gmtCreated = gmtCreated;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getGoodsId() {
        return goodsId;
    }
    
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    
    public Integer getStockCount() {
        return stockCount;
    }
    
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public Date getGmtCreated() {
        return gmtCreated;
    }
    
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
    
    @Override
    public String toString() {
        return "MiaoshaGoods{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", stockCount=" + stockCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", gmtCreated=" + gmtCreated +
                '}';
    }
}

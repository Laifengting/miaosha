package com.lft.miaosha.entity.vo;

import com.lft.miaosha.entity.po.Goods;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class Name:      GoodsVo
 * Package Name:    com.lft.miaosha.entity.vo
 * <p>
 * Function: 		A {@code GoodsVo} object With Some FUNCTION.
 * Date:            2021-05-16 16:24
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class GoodsVo extends Goods {
    private BigDecimal miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    
    public BigDecimal getMiaoshaPrice() {
        return miaoshaPrice;
    }
    
    public void setMiaoshaPrice(BigDecimal miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
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
    
    @Override
    public String toString() {
        return "GoodsVo{" +
                "miaoshaPrice=" + miaoshaPrice +
                ", stockCount=" + stockCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

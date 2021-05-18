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
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImage;
    private String goodsDetail;
    private BigDecimal goodsPrice;
    private Integer goodsStock;
    private Date gmtCreated;
    private Date gmtModified;
    
    public Goods() {
    }
    
    public Goods(Long id, String goodsName, String goodsTitle, String goodsImage, String goodsDetail, BigDecimal goodsPrice,
                 Integer goodsStock, Date gmtCreated, Date gmtModified) {
        this.id = id;
        this.goodsName = goodsName;
        this.goodsTitle = goodsTitle;
        this.goodsImage = goodsImage;
        this.goodsDetail = goodsDetail;
        this.goodsPrice = goodsPrice;
        this.goodsStock = goodsStock;
        this.gmtCreated = gmtCreated;
        this.gmtModified = gmtModified;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getGoodsName() {
        return goodsName;
    }
    
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public String getGoodsTitle() {
        return goodsTitle;
    }
    
    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }
    
    public String getGoodsImage() {
        return goodsImage;
    }
    
    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }
    
    public String getGoodsDetail() {
        return goodsDetail;
    }
    
    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
    
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }
    
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    
    public Integer getGoodsStock() {
        return goodsStock;
    }
    
    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }
    
    public Date getGmtCreated() {
        return gmtCreated;
    }
    
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
    
    public Date getGmtModified() {
        return gmtModified;
    }
    
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
    
    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsImage='" + goodsImage + '\'' +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStock=" + goodsStock +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }
}

package com.lft.miaosha.entity.vo;

import com.lft.miaosha.entity.po.OrderInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class Name:      OrderInfoVo
 * Package Name:    com.lft.miaosha.entity.vo
 * <p>
 * Function: 		A {@code OrderInfoVo} object With Some FUNCTION.
 * Date:            2021-05-16 23:38
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class OrderInfoVo extends OrderInfo {
    private String name;
    private String addressDetail;
    
    public OrderInfoVo() {
    }
    
    public OrderInfoVo(Long id, Long userId, Long goodsId, Long deliveryAddressId, String goodsName, Integer goodsCount,
                       BigDecimal goodsPrice, Integer orderChannel, Integer status, Date gmtCreated, Date gmtPay) {
        super(id, userId, goodsId, deliveryAddressId, goodsName, goodsCount, goodsPrice, orderChannel, status, gmtCreated, gmtPay);
    }
    
    public OrderInfoVo(String name, String addressDetail) {
        this.name = name;
        this.addressDetail = addressDetail;
    }
    
    public OrderInfoVo(Long id, Long userId, Long goodsId, Long deliveryAddressId, String goodsName, Integer goodsCount,
                       BigDecimal goodsPrice, Integer orderChannel, Integer status, Date gmtCreated, Date gmtPay, String name,
                       String addressDetail) {
        super(id, userId, goodsId, deliveryAddressId, goodsName, goodsCount, goodsPrice, orderChannel, status, gmtCreated, gmtPay);
        this.name = name;
        this.addressDetail = addressDetail;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddressDetail() {
        return addressDetail;
    }
    
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
    
    @Override
    public String toString() {
        return "OrderInfoVo{" +
                "name='" + name + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                '}';
    }
}

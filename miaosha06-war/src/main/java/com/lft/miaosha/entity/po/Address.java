package com.lft.miaosha.entity.po;

import java.util.Date;

/**
 * Class Name:      Address
 * Package Name:    com.lft.miaosha.entity.po
 * <p>
 * Function: 		A {@code Address} object With Some FUNCTION.
 * Date:            2021-05-16 21:29
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class Address {
    
    private Long id;
    private String name;
    private Long userId;
    private String addressDetail;
    private Date gmtCreated;
    private Date gmtModified;
    
    public Address() {
    }
    
    public Address(Long id, String name, Long userId, String addressDetail, Date gmtCreated, Date gmtModified) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.addressDetail = addressDetail;
        this.gmtCreated = gmtCreated;
        this.gmtModified = gmtModified;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getAddressDetail() {
        return addressDetail;
    }
    
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", addressDetail='" + addressDetail + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }
}

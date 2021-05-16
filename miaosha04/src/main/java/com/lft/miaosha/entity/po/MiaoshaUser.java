package com.lft.miaosha.entity.po;

import java.util.Date;

/**
 * Class Name:      MiaoshaUser
 * Package Name:    com.lft.miaosha.entity.po
 * <p>
 * Function: 		A {@code MiaoshaUser} object With Some FUNCTION.
 * Date:            2021-05-15 15:19
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class MiaoshaUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date gmtCreated;
    private Date gmtLastLogin;
    private Integer loginCount;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getHead() {
        return head;
    }
    
    public void setHead(String head) {
        this.head = head;
    }
    
    public Date getGmtCreated() {
        return gmtCreated;
    }
    
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
    
    public Date getGmtLastLogin() {
        return gmtLastLogin;
    }
    
    public void setGmtLastLogin(Date gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
    }
    
    public Integer getLoginCount() {
        return loginCount;
    }
    
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
    
    @Override
    public String toString() {
        return "MiaoshaUser{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", head='" + head + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtLastLogin=" + gmtLastLogin +
                ", loginCount=" + loginCount +
                '}';
    }
}

package com.lft.miaosha.entity.dto;

/**
 * Class Name:      Login
 * Package Name:    com.lft.miaosha.entity.dto
 * <p>
 * Function: 		A {@code Login} object With Some FUNCTION.
 * Date:            2021-05-15 14:13
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class RegisterDto {
    private String mobile;
    private String nickname;
    private String password;
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
    
    @Override
    public String toString() {
        return "RegisterDto{" +
                "mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

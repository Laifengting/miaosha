package com.lft.miaosha.entity.dto;

import com.lft.miaosha.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

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
    @IsMobile
    private String mobile;
    @NotNull
    private String nickname;
    @NotNull
    @Length (min = 6, max = 32)
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

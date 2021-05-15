package com.lft.miaosha.entity.po;

/**
 * Class Name:      User
 * Package Name:    com.lft.miaosha.entity.po
 * <p>
 * Function: 		A {@code User} object With Some FUNCTION.
 * Date:            2021-05-14 22:54
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class User {
    private Integer id;
    private String name;
    
    public User() {
    }
    
    public User(String name) {
        this.name = name;
    }
    
    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Integer getId() {
        return id;
    }
    
    public User setId(Integer id) {
        this.id = id;
        return this;
        
    }
    
    public String getName() {
        return name;
    }
    
    public User setName(String name) {
        this.name = name;
        return this;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

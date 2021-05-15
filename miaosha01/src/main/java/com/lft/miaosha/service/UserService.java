package com.lft.miaosha.service;

import com.lft.miaosha.entity.po.User;

/**
 * Class Name:      UserService
 * Package Name:    com.lft.miaosha.service
 * <p>
 * Function: 		A {@code UserService} object With Some FUNCTION.
 * Date:            2021-05-14 22:57
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public interface UserService {
    User getById(Integer id);
    
    Boolean tx();
}

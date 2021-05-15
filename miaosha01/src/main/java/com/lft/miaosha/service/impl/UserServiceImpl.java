package com.lft.miaosha.service.impl;

import com.lft.miaosha.dao.UserMapper;
import com.lft.miaosha.entity.po.User;
import com.lft.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class Name:      UserServiceImpl
 * Package Name:    com.lft.miaosha.service.impl
 * <p>
 * Function: 		A {@code UserServiceImpl} object With Some FUNCTION.
 * Date:            2021-05-14 22:57
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }
    
    @Override
    @Transactional
    public Boolean tx() {
        User user = new User();
        user.setId(3).setName("Jack");
        Integer result1 = userMapper.insert(user);
        
        User user2 = new User();
        user2.setId(1).setName("Helen");
        Integer result2 = userMapper.insert(user2);
        
        return result1 > 0 && result2 > 0;
    }
}

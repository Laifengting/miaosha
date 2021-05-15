package com.lft.miaosha.controller;

import com.lft.miaosha.common.key.impl.UserKeyPrefix;
import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.entity.po.User;
import com.lft.miaosha.service.RedisService;
import com.lft.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class Name:      UserController
 * Package Name:    com.lft.miaosha.controller
 * <p>
 * Function: 		A {@code UserController} object With Some FUNCTION.
 * Date:            2021-05-14 23:05
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Controller
@RequestMapping ("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    
    @RequestMapping ("getById/{id}")
    @ResponseBody
    public R getById(@PathVariable ("id") Integer id) {
        User user = userService.getById(id);
        return R.OK().data("user", user);
    }
    
    @RequestMapping ("db/tx")
    @ResponseBody
    public R dbTx() {
        Boolean result = userService.tx();
        return result ? R.OK() : R.ERROR().message(ResultCode.SERVER_ERROR.getMessage());
    }
    
    @RequestMapping ("redis/set")
    @ResponseBody
    public R redisSet() {
        User user = new User();
        user.setId(10).setName("测试Redis");
        redisService.set(UserKeyPrefix.ID_KEY, "1", user);
        return R.OK();
    }
    
    @RequestMapping ("redis/get")
    @ResponseBody
    public R redisGet() {
        String value = redisService.get(UserKeyPrefix.ID_KEY, "1", String.class);
        return R.OK().data("value", value);
    }
    
}

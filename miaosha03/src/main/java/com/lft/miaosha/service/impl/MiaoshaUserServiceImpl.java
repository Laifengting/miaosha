package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.exception.ExceptionCode;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.common.util.Md5Util;
import com.lft.miaosha.common.util.RandomSaltUtil;
import com.lft.miaosha.dao.MiaoshaUserMapper;
import com.lft.miaosha.entity.dto.LoginDto;
import com.lft.miaosha.entity.dto.RegisterDto;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.exception.MsException;
import com.lft.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Class Name:      MiaoshaUserServiceImpl
 * Package Name:    com.lft.miaosha.service.impl
 * <p>
 * Function: 		A {@code MiaoshaUserServiceImpl} object With Some FUNCTION.
 * Date:            2021-05-15 15:33
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {
    @Autowired
    MiaoshaUserMapper miaoshaUserMapper;
    
    @Override
    public MiaoshaUser getUserById(Long id) {
        return miaoshaUserMapper.selectUserById(id);
    }
    
    @Override
    public ResultCode login(LoginDto loginDto) {
        if (loginDto == null) {
            return ResultCode.LOGIN_ERROR;
        }
        String mobile = loginDto.getMobile();
        String formPassword = loginDto.getPassword();
        
        // 判断手机号是否存在
        MiaoshaUser miaoshaUser = getUserById(Long.parseLong(mobile));
        if (miaoshaUser == null) {
            return ResultCode.MOBILE_NOT_FOUND_ERROR;
        }
        
        String dbPassword = miaoshaUser.getPassword();
        String saltDb = miaoshaUser.getSalt();
        String formPassToDbPass = Md5Util.formPassToDbPass(formPassword, saltDb);
        if (!formPassToDbPass.equals(dbPassword)) {
            return ResultCode.WRONG_PASSWORD_ERROR;
        }
        return ResultCode.LOGIN_SUCCESSR;
    }
    
    @Override
    public ResultCode register(RegisterDto registerDto) {
        if (registerDto == null) {
            throw new MsException(ExceptionCode.NULL_OBJECT_EXCEPTION);
        }
        String mobile = registerDto.getMobile();
        String nickname = registerDto.getNickname();
        String formPassword = registerDto.getPassword();
        
        // 判断手机号是否存在
        MiaoshaUser miaoshaUserFromDb = getUserById(Long.parseLong(mobile));
        if (miaoshaUserFromDb != null) {
            throw new MsException(ExceptionCode.NULL_OBJECT_EXCEPTION);
        }
        
        String randomSalt = RandomSaltUtil.getRandomSalt();
        String formPassToDbPass = Md5Util.formPassToDbPass(formPassword, randomSalt);
        
        MiaoshaUser miaoshaUser = new MiaoshaUser();
        miaoshaUser.setId(Long.parseLong(mobile));
        miaoshaUser.setNickname(nickname);
        miaoshaUser.setSalt(randomSalt);
        miaoshaUser.setPassword(formPassToDbPass);
        miaoshaUser.setGmtCreated(new Date());
        miaoshaUser.setHead("头像");
        
        Integer result = miaoshaUserMapper.insertUser(miaoshaUser);
        if (result <= 0) {
            return ResultCode.REGISTER_FAILED_ERROR;
        }
        return ResultCode.REGISTER_SUCCESSR;
    }
    
}

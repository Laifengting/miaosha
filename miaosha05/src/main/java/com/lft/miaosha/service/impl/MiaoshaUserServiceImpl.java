package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.exception.ExceptionCode;
import com.lft.miaosha.common.key.impl.MsUserKeyPrefix;
import com.lft.miaosha.common.result.R;
import com.lft.miaosha.common.result.ResultCode;
import com.lft.miaosha.common.util.Md5Util;
import com.lft.miaosha.common.util.RandomSaltUtil;
import com.lft.miaosha.common.util.UuidUtil;
import com.lft.miaosha.dao.MiaoshaUserMapper;
import com.lft.miaosha.entity.dto.LoginDto;
import com.lft.miaosha.entity.dto.RegisterDto;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.exception.MsException;
import com.lft.miaosha.service.MiaoshaUserService;
import com.lft.miaosha.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    public static final String COOKIE_NAME_TOKEN = "ms_user_token";
    private static final Logger log = LoggerFactory.getLogger(MiaoshaUserServiceImpl.class);
    
    @Autowired
    MiaoshaUserMapper miaoshaUserMapper;
    
    @Autowired
    RedisService redisService;
    
    /**
     * 根据手机号码获取用户
     * @param id 手机号码
     * @return
     */
    @Override
    public MiaoshaUser getUserById(Long id) {
        return miaoshaUserMapper.selectUserById(id);
    }
    
    /**
     * 登录方法
     * @param loginDto
     * @param request
     * @param response
     * @return
     */
    @Override
    public R login(@Valid LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        // 从 cookie 中获取
        String tokenFromCookie = getTokenFromCookie(request, COOKIE_NAME_TOKEN);
        MiaoshaUser userFromCache = null;
        if (StringUtils.isNotEmpty(tokenFromCookie)) {
            // 从缓存中获取对象
            userFromCache = getUserFromCache(tokenFromCookie, response);
        }
        
        // 从传入的参数中获取手机号码和密码
        String mobile = loginDto.getMobile();
        String formPassword = loginDto.getPassword();
        
        // 缓存中对象不为空
        if (userFromCache != null) {
            if (mobile.equals(userFromCache.getId())) {
                // 直接返回带对象数据的结果
                return R.OK().data("user", userFromCache).code(ResultCode.LOGIN_SUCCESSR.getCode())
                        .message(ResultCode.LOGIN_SUCCESSR.getMessage());
            }
        }
        
        // cookie 中没有值或者 cookie 中的用户不是当前登录用户 尝试使用手机号和密码从数据库中获取登录信息
        // 根据手机号码从数据库中查询出 用户
        MiaoshaUser miaoshaUserFromDb = getUserById(Long.parseLong(mobile));
        // 用户为空抛出异常
        if (miaoshaUserFromDb == null) {
            throw new MsException(ExceptionCode.ILLEGAL_ARGUMENT_EXCEPTION);
        }
        // 修改登录次数和登录时间
        miaoshaUserFromDb.setLoginCount(miaoshaUserFromDb.getLoginCount() + 1);
        miaoshaUserFromDb.setGmtLastLogin(new Date());
        // 更新到数据库
        Integer result = updateLoginCountAndTime(miaoshaUserFromDb);
        if (result <= 0) {
            throw new MsException(ExceptionCode.UPDATE_LOGIN_COUNT_AND_LAST_LOGIN_TIME_EXCEPTION);
        }
        log.info(miaoshaUserFromDb.toString());
        // 用户不为空，获取用户的 密码，数据库随机盐值
        String dbPassword = miaoshaUserFromDb.getPassword();
        String saltDb = miaoshaUserFromDb.getSalt();
        // 将表单密码通过数据库盐值转换成 数据库密码
        String formPassToDbPass = Md5Util.formPassToDbPass(formPassword, saltDb);
        // 对比两密码如果不相等就抛出异常
        if (!formPassToDbPass.equals(dbPassword)) {
            throw new MsException(ExceptionCode.WRONG_PASSWORD_EXCEPTION);
        }
        // 如果密码正确 生成 token
        String token = UuidUtil.getToken();
        // 生成 cookie 放入到 响应体中
        addCookie(miaoshaUserFromDb, token, response);
        
        // 返回登录成功
        return R.OK().data("user", miaoshaUserFromDb).code(ResultCode.LOGIN_SUCCESSR.getCode())
                .message(ResultCode.LOGIN_SUCCESSR.getMessage());
    }
    
    /**
     * 注册
     * @param registerDto
     * @return
     */
    @Override
    public ResultCode register(RegisterDto registerDto) {
        // 校验传入参数
        if (registerDto == null) {
            throw new MsException(ExceptionCode.NULL_OBJECT_EXCEPTION);
        }
        // 获取手机号，昵称，表单密码
        String mobile = registerDto.getMobile();
        String nickname = registerDto.getNickname();
        String formPassword = registerDto.getPassword();
        
        // 判断手机号是否存在
        MiaoshaUser miaoshaUserFromDb = getUserById(Long.parseLong(mobile));
        if (miaoshaUserFromDb != null) {
            throw new MsException(ExceptionCode.NULL_OBJECT_EXCEPTION);
        }
        // 生成随机盐值
        String randomSalt = RandomSaltUtil.getRandomSalt();
        // 将表单密码转化成数据库密码
        String formPassToDbPass = Md5Util.formPassToDbPass(formPassword, randomSalt);
        
        // 创建 对象
        MiaoshaUser miaoshaUser = new MiaoshaUser();
        // 设置属性
        miaoshaUser.setId(Long.parseLong(mobile));
        miaoshaUser.setNickname(nickname);
        miaoshaUser.setSalt(randomSalt);
        miaoshaUser.setPassword(formPassToDbPass);
        miaoshaUser.setGmtCreated(new Date());
        miaoshaUser.setHead("头像");
        // 插入到数据库中
        Integer result = miaoshaUserMapper.insertUser(miaoshaUser);
        // 影响行数小于 1 说明注册失败
        if (result <= 0) {
            return ResultCode.REGISTER_FAILED_ERROR;
        }
        // 否则返回注册成功
        return ResultCode.REGISTER_SUCCESSR;
    }
    
    /**
     * 从缓存中获取 User
     * @param cookieToken
     * @param response
     * @return
     */
    @Override
    public MiaoshaUser getUserFromCache(String cookieToken, HttpServletResponse response) {
        if (StringUtils.isEmpty(cookieToken)) {
            return null;
        }
        // 如果 cookie 中的的 token 不为空，试着从缓存中获取对象
        MiaoshaUser miaoshaUser = redisService.get(MsUserKeyPrefix.TOKEN_KEY, cookieToken, MiaoshaUser.class);
        // 如果用户为空。直接返回空
        if (miaoshaUser == null) {
            return null;
        } else {
            // 先添加到缓存
            addCookie(miaoshaUser, cookieToken, response);
            // 再返回用户
            return miaoshaUser;
        }
    }
    
    /**
     * 生成 token，并将 token 放入cookie中，再将 token 和 对象 保存到缓存中
     * @param miaoshaUser
     * @param response
     */
    private void addCookie(MiaoshaUser miaoshaUser, String token, HttpServletResponse response) {
        // 将 token 放入到 Redis 中
        redisService.set(MsUserKeyPrefix.TOKEN_KEY, token, miaoshaUser);
        // 创建 cookie
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        // 设置 cookie 过期时间
        cookie.setMaxAge(MsUserKeyPrefix.TOKEN_KEY.expireSeconds());
        // 设置 cookie 存放的路径("/"表示存放在根目录下，所有的应用共享 cookie)
        cookie.setPath("/");
        // 将 cookie 加入到响应体中
        response.addCookie(cookie);
    }
    
    /**
     * 从 cookie 中获取 token 方法
     * @param request
     * @param cookieNameToken
     * @return
     */
    @Override
    public String getTokenFromCookie(HttpServletRequest request, String cookieNameToken) {
        // 声明一个变量用于接收 cookie 中的 token 值
        String tokenFromRequestCookie = null;
        
        // 先从请求中获取 cookies
        Cookie[] cookies = request.getCookies();
        // 如果 cookies 为空直接返回空
        if (cookies == null) {
            return null;
        }
        
        // 遍历请求中的 cookies
        for (Cookie cookie : cookies) {
            // 获取每一个 cookie 的名字
            String name = cookie.getName();
            // 如果 cookie 名字跟 ms_user_token 相等
            if (cookieNameToken.equals(name)) {
                // 就将该 cookie 的值 赋值给 tokenFromRequestCookies
                tokenFromRequestCookie = cookie.getValue();
            }
        }
        return tokenFromRequestCookie;
    }
    
    /**
     * 更新登录次数和时间方法
     * @param miaoshaUserFromDb
     * @return
     */
    private Integer updateLoginCountAndTime(MiaoshaUser miaoshaUserFromDb) {
        return miaoshaUserMapper.updateCountAndTime(miaoshaUserFromDb);
    }
}

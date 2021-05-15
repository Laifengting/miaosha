package com.lft.miaosha.dao;

import com.lft.miaosha.entity.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Class Name:      UserMapper
 * Package Name:    com.lft.miaosha.dao
 * <p>
 * Function: 		A {@code UserMapper} object With Some FUNCTION.
 * Date:            2021-05-14 22:54
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Mapper
public interface UserMapper {
    @Select ("SELECT * FROM user WHERE id = #{id}")
    User selectById(@Param ("id") Integer id);
    
    @Insert ("INSERT INTO user(id,name) VALUES(#{id},#{name})")
    Integer insert(User user);
}

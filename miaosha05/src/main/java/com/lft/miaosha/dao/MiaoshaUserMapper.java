package com.lft.miaosha.dao;

import com.lft.miaosha.entity.po.MiaoshaUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Class Name:      MiaoshaUserMapper
 * Package Name:    com.lft.miaosha.dao
 * <p>
 * Function: 		A {@code MiaoshaUserMapper} object With Some FUNCTION.
 * Date:            2021-05-15 15:28
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Mapper
public interface MiaoshaUserMapper {
    @Select ("SELECT * FROM miaosha_user WHERE id = #{id}")
    MiaoshaUser selectUserById(@Param ("id") Long id);
    
    @Insert ("INSERT INTO miaosha_user(id,nickname,password,salt,head,gmt_created,gmt_last_login,login_count) " +
            "values(#{id},#{nickname},#{password},#{salt},#{head},#{gmtCreated},#{gmtCreated},1)")
    Integer insertUser(MiaoshaUser miaoshaUser);
    
    @Update ("UPDATE miaosha_user SET gmt_last_login = #{gmtLastLogin},login_count = #{loginCount} WHERE id = #{id}")
    Integer updateCountAndTime(MiaoshaUser miaoshaUserFromDb);
}

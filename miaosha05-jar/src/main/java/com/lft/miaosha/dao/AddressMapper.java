package com.lft.miaosha.dao;

import com.lft.miaosha.entity.po.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Class Name:      GoodsMapper
 * Package Name:    com.lft.miaosha.dao
 * <p>
 * Function: 		A {@code GoodsMapper} object With Some FUNCTION.
 * Date:            2021-05-16 15:56
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Mapper
public interface AddressMapper {
    
    List<Address> selectAllByUserId(@Param ("userId") Long userId);
    
    Address selectAddressByUserIdAndAddressId(@Param ("userId") Long userId, @Param ("addressId") Long addressId);
    
    Integer insertAddress(Address address);
    
    Integer updateAddressByIdAndUserId(Address address);
    
    Boolean deleteAddressById(@Param ("addressId") Long addressId);
}

package com.lft.miaosha.service;

import com.lft.miaosha.entity.po.Address;

import java.util.List;

/**
 * Class Name:      GoodsService
 * Package Name:    com.lft.miaosha.service
 * <p>
 * Function: 		A {@code GoodsService} object With Some FUNCTION.
 * Date:            2021-05-16 15:55
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public interface AddressService {
    
    List<Address> getAllByUserId(Long userId);
    
    Address getAddressByUserIdAndAddressId(Long userId, Long addressId);
    
    Address getDefaultAddress(Long id);
}

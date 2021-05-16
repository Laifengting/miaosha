package com.lft.miaosha.service.impl;

import com.lft.miaosha.dao.AddressMapper;
import com.lft.miaosha.entity.po.Address;
import com.lft.miaosha.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class Name:      AddressServiceImpl
 * Package Name:    com.lft.miaosha.service.impl
 * <p>
 * Function: 		A {@code AddressServiceImpl} object With Some FUNCTION.
 * Date:            2021-05-16 21:33
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Service
public class AddressServiceImpl implements AddressService {
    
    @Autowired
    private AddressMapper addressMapper;
    
    @Override
    public List<Address> getAllByUserId(Long userId) {
        return addressMapper.selectAllByUserId(userId);
    }
    
    @Override
    public Address getAddressByUserIdAndAddressId(Long userId, Long addressId) {
        return addressMapper.selectAddressByUserIdAndAddressId(userId, addressId);
    }
}

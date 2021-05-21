package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.key.impl.AddressKeyPrefix;
import com.lft.miaosha.dao.AddressMapper;
import com.lft.miaosha.entity.po.Address;
import com.lft.miaosha.service.AddressService;
import com.lft.miaosha.service.RedisService;
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
    
    @Autowired
    private RedisService redisService;
    
    @Override
    public List<Address> getAllByUserId(Long userId) {
        // 从缓存中获取
        List<Address> list = redisService.get(AddressKeyPrefix.KEY_PREFIX_GET_ALL_ADDRS_BY_UID, "" + userId, List.class);
        // 缓存中不为空，直接返回
        if (list != null) {
            return list;
        }
        // 缓存中没有数据，从数据库获取
        list = addressMapper.selectAllByUserId(userId);
        // 数据库不为空
        if (list != null) {
            // 保存到缓存
            redisService.set(AddressKeyPrefix.KEY_PREFIX_GET_ALL_ADDRS_BY_UID, "" + userId, list);
        }
        // 返回
        return list;
    }
    
    @Override
    public Address getAddressByUserIdAndAddressId(Long userId, Long addressId) {
        // 从缓存中获取地址
        Address address = redisService
                .get(AddressKeyPrefix.KEY_PREFIX_GET_ADDRESS_BY_UID_AID, "" + userId + ":" + addressId, Address.class);
        // 缓存中如果不为空，直接返回
        if (address != null) {
            return address;
        }
        // 缓存中如果为空，从数据库获取
        address = addressMapper.selectAddressByUserIdAndAddressId(userId, addressId);
        // 数据库中如果不为空，保存到缓存
        if (address != null) {
            redisService.set(AddressKeyPrefix.KEY_PREFIX_GET_ADDRESS_BY_UID_AID, "" + userId + ":" + addressId, address);
        }
        // 返回
        return address;
    }
    
    @Override
    public Address getDefaultAddress(Long id) {
        List<Address> addresses = addressMapper.selectAllByUserId(id);
        return addresses.get(0);
    }
}

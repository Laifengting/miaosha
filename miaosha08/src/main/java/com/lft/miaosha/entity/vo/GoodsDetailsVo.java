package com.lft.miaosha.entity.vo;

import com.lft.miaosha.entity.po.Address;
import com.lft.miaosha.entity.po.MiaoshaUser;

/**
 * Class Name:      GoodsVo
 * Package Name:    com.lft.miaosha.entity.vo
 * <p>
 * Function: 		A {@code GoodsVo} object With Some FUNCTION.
 * Date:            2021-05-16 16:24
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class GoodsDetailsVo {
    private GoodsVo goods;
    private MiaoshaUser user;
    private Address userAddress;
    private int miaoshaStatus;
    private int remainSeconds;
    
    public GoodsVo getGoods() {
        return goods;
    }
    
    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }
    
    public MiaoshaUser getUser() {
        return user;
    }
    
    public void setUser(MiaoshaUser user) {
        this.user = user;
    }
    
    public Address getUserAddress() {
        return userAddress;
    }
    
    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }
    
    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }
    
    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }
    
    public int getRemainSeconds() {
        return remainSeconds;
    }
    
    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }
    
    @Override
    public String toString() {
        return "GoodsDetailsVo{" +
                "goods=" + goods +
                ", user=" + user +
                ", userAddress=" + userAddress +
                ", miaoshaStatus=" + miaoshaStatus +
                ", remainSeconds=" + remainSeconds +
                '}';
    }
}

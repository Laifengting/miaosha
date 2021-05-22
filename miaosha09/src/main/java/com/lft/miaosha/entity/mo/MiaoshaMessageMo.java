package com.lft.miaosha.entity.mo;

import com.lft.miaosha.entity.po.MiaoshaUser;

/**
 * Class Name:      MiaoshaMessageMo
 * Package Name:    com.lft.miaosha.entity.mo
 * <p>
 * Function: 		A {@code MiaoshaMessageMo} object With Some FUNCTION.
 * Date:            2021-05-22 21:30
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class MiaoshaMessageMo {
    private MiaoshaUser user;
    private Long goodsId;
    
    public MiaoshaUser getUser() {
        return user;
    }
    
    public void setUser(MiaoshaUser user) {
        this.user = user;
    }
    
    public Long getGoodsId() {
        return goodsId;
    }
    
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}

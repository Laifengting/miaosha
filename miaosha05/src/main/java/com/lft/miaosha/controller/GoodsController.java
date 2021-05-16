package com.lft.miaosha.controller;

import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.service.GoodsService;
import com.lft.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Class Name:      GoodsController
 * Package Name:    com.lft.miaosha.controller
 * <p>
 * Function: 		A {@code GoodsController} object With Some FUNCTION.
 * Date:            2021-05-15 16:06
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Controller
@RequestMapping ("goods")
public class GoodsController {
    
    @Autowired
    private MiaoshaUserService miaoshaUserService;
    
    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping ("to/list")
    public String toList(Model model, MiaoshaUser miaoshaUser) {
        if (miaoshaUser == null) {
            return "login";
        } else {
            // 将用户添加到 model 属性中
            model.addAttribute("user", miaoshaUser);
            // 查询商品列表
            List<GoodsVo> allGoods = goodsService.getAllGoods();
            model.addAttribute("goodsList", allGoods);
            return "goods";
        }
    }
    
    @RequestMapping ("to/detail/{goodsId}")
    public String toDetail(Model model, MiaoshaUser miaoshaUser, @PathVariable ("goodsId") Long goodsId) {
        if (miaoshaUser == null) {
            return "login";
        } else {
            // 将用户添加到 model 属性中
            model.addAttribute("user", miaoshaUser);
            
            // 查询商品详情
            GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
            model.addAttribute("goods", goods);
            
            // 秒杀时间
            long startTime = goods.getStartDate().getTime();
            long endTime = goods.getEndDate().getTime();
            
            long now = System.currentTimeMillis();
            
            int miaoshaStatus = 0;
            int remainSeconds = 0;
            
            // 秒杀还没开始，倒计时
            if (now < startTime) {
                miaoshaStatus = 0;
                remainSeconds = (int) ((startTime - now) / 1000);
            }
            // 秒杀已经结束
            else if (now > endTime) {
                miaoshaStatus = 2;
                remainSeconds = -1;
            }
            // 秒杀正在进行
            else {
                miaoshaStatus = 1;
                remainSeconds = 0;
            }
            model.addAttribute("miaoshaStatus", miaoshaStatus);
            model.addAttribute("remainSeconds", remainSeconds);
            return "goods_detail";
        }
    }
}

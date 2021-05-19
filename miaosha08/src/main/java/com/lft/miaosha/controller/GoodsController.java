package com.lft.miaosha.controller;

import com.lft.miaosha.common.key.impl.GoodsKeyPrefix;
import com.lft.miaosha.entity.po.Address;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.service.AddressService;
import com.lft.miaosha.service.GoodsService;
import com.lft.miaosha.service.MiaoshaUserService;
import com.lft.miaosha.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private RedisService redisService;
    
    /**
     * 页面缓存步骤：1
     * 用于手动渲染的视图解析器
     */
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    // /**
    //  * JMeter 压测 5000*10 三次
    //  * QPS 882.6 个/s
    //  * QPS 1780.9 个/s
    //  * QPS 1733.8 个/s
    // @RequestMapping ("to/list")
    // public String toList(Model model, MiaoshaUser miaoshaUser) {
    //     if (miaoshaUser == null) {
    //         return "login";
    //     } else {
    //         // 将用户添加到 model 属性中
    //         model.addAttribute("user", miaoshaUser);
    //         // 查询商品列表
    //         List<GoodsVo> allGoods = goodsService.getAllGoods();
    //         model.addAttribute("goodsList", allGoods);
    //         return "goods";
    //     }
    // }
    
    /**
     * JMeter 压测 5000*10 三次
     * QPS 3855.6 个/s
     * QPS 4541.3 个/s
     * QPS 4662.0 个/s
     * @param modeRe
     * @param miaoshaUser
     * @return
     */
    // 页面缓存步骤：2
    @RequestMapping (value = "to/list", produces = "text/html;charset=utf-8")
    // 页面缓存步骤：3
    @ResponseBody
    public String toList(Model model, MiaoshaUser miaoshaUser, HttpServletRequest request, HttpServletResponse response) {
        if (miaoshaUser == null) {
            return "login";
        } else {
            // 将用户添加到 model 属性中
            model.addAttribute("user", miaoshaUser);
            
            // 页面缓存步骤：4
            // 从缓存中取页面
            String html = redisService.get(GoodsKeyPrefix.KEY_PREFIX_GOODS_LIST, "all:goods", String.class);
            // 如果缓存中有页面直接返回
            if (StringUtils.isNotEmpty(html)) {
                // 页面缓存步骤：5
                return html;
            }
            
            // 页面缓存步骤：6
            // 如果缓存中没有页面，进行手动渲染
            // 查询商品列表
            List<GoodsVo> allGoods = goodsService.getAllGoods();
            model.addAttribute("goodsList", allGoods);
            
            // SpringWebContext context = new SpringWebContext() 类过期，使用 WebContext
            IWebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
            // 页面缓存步骤：7
            // 手动渲染
            html = thymeleafViewResolver.getTemplateEngine().process("goods", context);
            if (StringUtils.isNotEmpty(html)) {
                // 页面缓存步骤：8
                // 保存到缓存中
                redisService.set(GoodsKeyPrefix.KEY_PREFIX_GOODS_LIST, "all:goods", html);
            }
            // 页面缓存步骤：9
            return html;
        }
    }
    
    /**
     * JMeter 压测 5000*10 三次
     * QPS 2487.9 个/s
     * QPS 3573.5 个/s
     * QPS 4384.4 个/s
     * @param model
     * @param miaoshaUser
     * @param goodsId
     * @param request
     * @param response
     * @return
     */
    // RUL缓存步骤：1
    @RequestMapping (value = "to/detail/{goodsId}", produces = "text/html;charset=utf-8")
    // RUL缓存步骤：2
    @ResponseBody
    public String toDetail(Model model, MiaoshaUser miaoshaUser, @PathVariable ("goodsId") Long goodsId, HttpServletRequest request,
                           HttpServletResponse response) {
        if (miaoshaUser == null) {
            return "login";
        } else {
            // 将用户添加到 model 属性中
            model.addAttribute("user", miaoshaUser);
            
            // RUL缓存步骤：3
            // 从缓存中取页面
            String html = redisService.get(GoodsKeyPrefix.KEY_PREFIX_GOODS_DETAIL, "" + goodsId, String.class);
            if (StringUtils.isNotEmpty(html)) {
                return html;
            }
            
            // 查询商品详情
            GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
            model.addAttribute("goods", goods);
            
            // 查询当前用户的默认地址
            Address userAddress = addressService.getDefaultAddress(miaoshaUser.getId());
            model.addAttribute("userAddress", userAddress);
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
            
            // RUL缓存步骤：4
            // 缓存中没 进行手动渲染
            IWebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
            // 手动渲染
            // RUL缓存步骤：5
            html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", context);
            if (StringUtils.isNotEmpty(html)) {
                // RUL缓存步骤：6
                // 保存到缓存中
                redisService.set(GoodsKeyPrefix.KEY_PREFIX_GOODS_DETAIL, "" + goodsId, html);
            }
            // RUL缓存步骤：7
            return html;
        }
    }
}

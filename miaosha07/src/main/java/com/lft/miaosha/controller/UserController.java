package com.lft.miaosha.controller;

import com.lft.miaosha.common.result.R;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.vo.GoodsVo;
import com.lft.miaosha.service.GoodsService;
import com.lft.miaosha.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Class Name:      LoginController
 * Package Name:    com.lft.miaosha.controller
 * <p>
 * Function: 		A {@code LoginController} object With Some FUNCTION.
 * Date:            2021-05-15 14:00
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Controller
@RequestMapping ("user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private MiaoshaUserService miaoshaUserService;
    
    @Autowired
    private GoodsService goodsService;
    
    /**
     * JMeter 压测 5000*10 三次
     * QPS 1531.7 个/s
     * QPS 2631.7 个/s
     * QPS 2590.1 个/s
     * @param model
     * @param user
     * @return
     */
    @RequestMapping ("info")
    @ResponseBody
    public R info(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        List<GoodsVo> allGoods = goodsService.getAllGoods();
        return R.OK().data("goods", allGoods);
    }
}

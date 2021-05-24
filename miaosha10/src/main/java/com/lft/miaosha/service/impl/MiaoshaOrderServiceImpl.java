package com.lft.miaosha.service.impl;

import com.lft.miaosha.common.constant.RedisConstants;
import com.lft.miaosha.common.key.impl.MSGoodsKeyPrefix;
import com.lft.miaosha.common.key.impl.MSOrderKeyPrefix;
import com.lft.miaosha.dao.MiaoshaOrderMapper;
import com.lft.miaosha.entity.po.MiaoshaGoods;
import com.lft.miaosha.entity.po.MiaoshaOrder;
import com.lft.miaosha.entity.po.MiaoshaUser;
import com.lft.miaosha.entity.vo.OrderInfoVo;
import com.lft.miaosha.service.AddressService;
import com.lft.miaosha.service.MiaoshaGoodsService;
import com.lft.miaosha.service.MiaoshaOrderService;
import com.lft.miaosha.service.OrderInfoService;
import com.lft.miaosha.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

/**
 * Class Name:      OrderInfoServiceImpl
 * Package Name:    com.lft.miaosha.service.impl
 * <p>
 * Function: 		A {@code OrderInfoServiceImpl} object With Some FUNCTION.
 * Date:            2021-05-16 15:59
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Service
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService {
    
    private static Logger log = LoggerFactory.getLogger(MiaoshaOrderServiceImpl.class);
    
    @Autowired
    private MiaoshaOrderMapper miaoshaOrderMapper;
    
    @Autowired
    private OrderInfoService orderInfoService;
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;
    
    @Autowired
    private RedisService redisService;
    
    /**
     * 根据用户id 商品id 查询 秒杀订单
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long userId, Long goodsId) {
        // 从缓存中查询
        MiaoshaOrder miaoshaOrder = redisService
                .get(MSOrderKeyPrefix.KEY_PREFIX_GET_MSORDER_BY_UID_GID, "" + userId + ":" + goodsId, MiaoshaOrder.class);
        // 如果缓存中有数据直接返回
        if (miaoshaOrder != null) {
            return miaoshaOrder;
        }
        // 如果缓存中没有从数据库查询
        miaoshaOrder = miaoshaOrderMapper.selectMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        // 如果数据库中数据不为空
        if (miaoshaOrder != null) {
            // 放到缓存中
            redisService.set(MSOrderKeyPrefix.KEY_PREFIX_GET_MSORDER_BY_UID_GID, "" + userId + ":" + goodsId, miaoshaOrder);
        }
        // 返回
        return miaoshaOrder;
    }
    
    /**
     * 根据用户 商品 生成订单信息
     * @param miaoshaUser
     * @param goodsVo
     * @return
     */
    @Override
    @Transactional
    public OrderInfoVo miaosha(MiaoshaUser miaoshaUser, MiaoshaGoods miaoshaGoods) {
        miaoshaGoods.setGmtModified(new Date());
        // // 第 1 步 减库存
        log.info("==================== 真正的数据库执行秒杀操作 减库存 ====================");
        Integer reduceStockResult = miaoshaGoodsService.reduceStock(miaoshaGoods);
        // 减库存失败，抛
        if (reduceStockResult <= 0) {
            return null;
        }
        
        // // 第 2 步 下订单 订单详情
        log.info("==================== 真正的数据库执行秒杀操作 添加订单详情 ====================");
        OrderInfoVo orderInfoVo = orderInfoService.addOrder(miaoshaUser, miaoshaGoods);
        
        // // 第 3 步 写入秒杀订单
        // 设置属性
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setOrderId(orderInfoVo.getId());
        miaoshaOrder.setUserId(orderInfoVo.getUserId());
        miaoshaOrder.setGoodsId(orderInfoVo.getGoodsId());
        miaoshaOrder.setGmtCreated(new Date());
        // 写入秒杀订单到数据库
        log.info("==================== 真正的数据库执行秒杀操作 添加秒杀订单 ====================");
        Integer result = miaoshaOrderMapper.addMiaoshaOrder(miaoshaOrder);
        // 订单写入失败
        if (result <= 0) {
            // 标记秒杀商品卖完
            setMiaoshaGoodsOver(miaoshaOrder.getGoodsId());
            return null;
        }
        // // 第 4 步 数据库插入成功，生成订单对象到缓存中
        redisService.set(MSOrderKeyPrefix.KEY_PREFIX_GET_MSORDER_BY_UID_GID, RedisConstants.EMPTY_STRING + miaoshaUser
                .getId() + RedisConstants.SPILT + miaoshaGoods
                .getGoodsId(), miaoshaOrder);
        // 返回订单信息
        return orderInfoVo;
    }
    
    /**
     * 获取秒杀结果
     * @param userId
     * @param goodsId
     * @return 订单编号 表示秒杀成功，-1 表示失败，0 表示排队中
     */
    @Override
    public Long getMiaoshaResult(Long userId, Long goodsId) {
        MiaoshaOrder miaoshaOrder = miaoshaOrderMapper.selectByUidGid(userId, goodsId);
        if (miaoshaOrder != null) {
            return miaoshaOrder.getId();
        } else {
            // 判断是否是商品卖完了
            boolean isOver = getMiaoshaGoodsOver(goodsId);
            // 如果商品卖完了
            if (isOver) {
                // 就是秒杀失败,返回 -1
                return -1L;
            } else {
                // 没有卖完，表示在排列中，返回 0
                return 0L;
            }
        }
    }
    
    @Override
    public Boolean checkPath(MiaoshaUser miaoshaUser, Long goodsId, String path) {
        if (miaoshaUser == null || path == null || goodsId <= 0) {
            return false;
        }
        String pathFromRedis = redisService
                .get(MSOrderKeyPrefix.KEY_PREFIX_GET_MSPATH_BY_GID, "" + miaoshaUser
                        .getId() + RedisConstants.SPILT + goodsId, String.class);
        return path.equals(pathFromRedis);
    }
    
    @Override
    public BufferedImage createMiaoshaVerifyCode(MiaoshaUser miaoshaUser, Long goodsId) {
        if (miaoshaUser == null || goodsId <= 0) {
            return null;
        }
        int width = 100;
        int height = 32;
        // 创建 image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形
        Graphics g = image.getGraphics();
        
        // 设置当前颜色
        g.setColor(new Color(0xDCDCDC));
        // 填充指定矩形（背景）
        g.fillRect(0, 0, width, height);
        // 设置当前颜色
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        
        // 创建一个随机对象
        Random random = new Random();
        
        // 绘制干扰点
        // 设置当前颜色
        g.setColor(Color.white);
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            // 绘制椭圆形
            g.drawOval(x, y, 0, 0);
        }
        
        // 创建验证码
        String verifyCode = createVerifyCode(random);
        
        String suffix = "=?";
        // 设置当前颜色
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode + suffix, 8, 24);
        g.dispose();
        
        // 把验证码保存到 缓存中
        int rnd = calc(verifyCode);
        redisService
                .set(MSOrderKeyPrefix.KEY_PREFIX_GET_VERIFYCODE_BY_UID_GID, "" + miaoshaUser.getId() + RedisConstants.SPILT + goodsId, rnd);
        return image;
    }
    
    /**
     * 创建验证码
     * @param random
     * @return
     */
    private String createVerifyCode(Random random) {
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(10);
        char[] operatorList = {'+', '-', '*'};
        char operator1 = operatorList[random.nextInt(3)];
        char operator2 = operatorList[random.nextInt(3)];
        return "" + num1 + operator1 + num2 + operator2 + num3;
    }
    
    // 计算出验证码结果
    private int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");
            return (int) scriptEngine.eval(exp);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    private boolean getMiaoshaGoodsOver(Long goodsId) {
        return redisService.existsKey(MSGoodsKeyPrefix.KEY_PREFIX_IS_MSGOODS_SELL_OUT_BY_GID, "" + goodsId);
    }
    
    private void setMiaoshaGoodsOver(Long goodsId) {
        redisService.set(MSGoodsKeyPrefix.KEY_PREFIX_IS_MSGOODS_SELL_OUT_BY_GID, "" + goodsId, true);
    }
    
}

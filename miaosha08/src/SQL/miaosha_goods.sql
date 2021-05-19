# 使用库
USE miaosha;

# 删除表
DROP TABLE IF EXISTS miaosha_goods;

# 新建表
CREATE TABLE miaosha_goods (
	id            BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀活动ID',
	goods_id      BIGINT(20)     DEFAULT NULL COMMENT '商品ID',
	miaosha_price DECIMAL(20, 2) DEFAULT 0.00 COMMENT '秒杀价',
	stock_count   INT(11)        DEFAULT NULL COMMENT '库存数量',
	start_date    DATETIME       DEFAULT NULL COMMENT '秒杀开始时间',
	end_date      DATETIME       DEFAULT NULL COMMENT '秒杀结束时间',
	gmt_created   DATETIME       DEFAULT NULL COMMENT '创建时间',
	gmt_modified  DATETIME       DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci
	COMMENT ='秒杀商品表'
	AUTO_INCREMENT = 3;

# 插入测试数据
INSERT INTO miaosha_goods(goods_id, miaosha_price, stock_count, start_date, end_date)
	VALUES (3, 9.9, 100, "2021-05-16 20:00:00", "2021-05-16 22:00:00"),
		   (4, 19.9, 100, "2021-05-17 02:00:00", "2021-05-17 23:59:59"),
		   (5, 29.9, 100, "2021-05-18 20:00:00", "2021-05-18 22:00:00");

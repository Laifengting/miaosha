# 使用库
USE miaosha;

# 删除表
DROP TABLE IF EXISTS goods;

# 新建表
CREATE TABLE goods (
	id           BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
	goods_name   VARCHAR(32)    DEFAULT NULL COMMENT '商品名称',
	goods_title  VARCHAR(64)    DEFAULT NULL COMMENT '商品标题',
	goods_image  VARCHAR(255)   DEFAULT NULL COMMENT '商品图片',
	goods_detail LONGTEXT       DEFAULT NULL COMMENT '商品详情',
	goods_price  DECIMAL(20, 2) DEFAULT NULL COMMENT '商品单价,保留两位小数',
	goods_stock  INT(11)        DEFAULT NULL COMMENT '商品库存,-1表示没有限制',
	gmt_created  DATETIME       DEFAULT NULL COMMENT '创建时间',
	gmt_modified DATETIME       DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci
	COMMENT ='商品表'
	AUTO_INCREMENT = 3;

# 插入测试数据
INSERT INTO goods(goods_name, goods_title, goods_image, goods_detail, goods_price, goods_stock)
	VALUES ('小米8 手机', '小米手机旗舰机8大续航大屏幕', '/img/8.jpg', '小米8手机的详情介绍', 9.9, 100),
		   ('小米9 手机', '小米手机旗舰机9大续航大屏幕', '/img/9.jpg', '小米9手机的详情介绍', 19.9, 100),
		   ('小米10 手机', '小米手机旗舰机10大续航大屏幕', '/img/10.jpg', '小米10手机的详情介绍', 29.9, 100);

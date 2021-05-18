# 使用库
USE miaosha;

# 删除表
DROP TABLE IF EXISTS order_info;

# 新建表
CREATE TABLE order_info (
	id                  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
	user_id             BIGINT(20)     DEFAULT NULL COMMENT '用户ID',
	goods_id            BIGINT(20)     DEFAULT NULL COMMENT '商品ID',
	delivery_address_id BIGINT(20)     DEFAULT NULL COMMENT '收货地址ID',
	goods_name          VARCHAR(32)    DEFAULT NULL COMMENT '商品名称',
	goods_count         INT(11)        DEFAULT NULL COMMENT '商品数量',
	goods_price         DECIMAL(20, 2) DEFAULT NULL COMMENT '商品单价,保留两位小数',
	order_channel       TINYINT(4)     DEFAULT 0 COMMENT '订单通道,1:PC,2:Android,3:IOS',
	status              TINYINT(4)     DEFAULT 0 COMMENT '订单状态，0:待支付,1:已支付,2:已发货,3:已收货,4:已退款,5:已完成',
	gmt_created         DATETIME       DEFAULT NULL COMMENT '创建时间',
	gmt_pay             DATETIME       DEFAULT NULL COMMENT '支付时间',
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci
	COMMENT ='订单信息表'
	AUTO_INCREMENT = 12;


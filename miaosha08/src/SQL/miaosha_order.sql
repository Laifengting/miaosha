# 使用库
USE miaosha;

# 删除表
DROP TABLE IF EXISTS miaosha_order;

# 新建表
CREATE TABLE miaosha_order (
	id          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀订单ID',
	user_id     BIGINT(20) DEFAULT NULL COMMENT '用户ID',
	order_id    BIGINT(20) DEFAULT NULL COMMENT '订单ID',
	goods_id    BIGINT(20) DEFAULT NULL COMMENT '商品ID',
	gmt_created DATETIME   DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci
	COMMENT ='秒杀订单表'
	AUTO_INCREMENT = 3;



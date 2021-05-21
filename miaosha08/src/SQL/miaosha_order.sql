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

# 创建唯一索引约束（u_uid_gid 约束名）
ALTER TABLE miaosha_order
	ADD CONSTRAINT u_uid_gid UNIQUE KEY (user_id, goods_id);

# 删除约束
ALTER TABLE miaosha_order
	DROP KEY miaosha_order_un;

# 创建外键（级联CASCADE/约束RESTRICT/设空 SET NULL/设置为默认SET DEFAULT）
ALTER TABLE miaosha_order
	ADD CONSTRAINT miaosha_order_fk FOREIGN KEY (goods_id)
		REFERENCES miaosha_goods(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

# 创建索引
CREATE INDEX miaosha_order_user_id_idx USING BTREE ON miaosha_order(user_id);

# 创建联合索引
CREATE INDEX miaosha_order_user_id_idx USING BTREE ON miaosha_order(user_id, order_id, goods_id);

# 删除表，并重新创建表
TRUNCATE TABLE miaosha.miaosha_order;

# 删除表内容
DELETE
	FROM miaosha_order
	WHERE id = 1;

# 分析表
ANALYZE TABLE miaosha.miaosha_order;

# 检查表
CHECK TABLE miaosha.miaosha_order;

# 优化表
OPTIMIZE TABLE miaosha.miaosha_order;

# 修复表
REPAIR TABLE miaosha.miaosha_order;


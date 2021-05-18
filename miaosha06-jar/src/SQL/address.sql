# 使用库
USE miaosha;

# 删除表
DROP TABLE IF EXISTS address;

# 新建表
CREATE TABLE address (
	id             BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
	name           VARCHAR(32) DEFAULT NULL COMMENT '收件人姓名',
	user_id        BIGINT(20)  DEFAULT NULL COMMENT '用户ID/手机号码',
	address_detail LONGTEXT    DEFAULT NULL COMMENT '详细地址',
	gmt_created    DATETIME    DEFAULT NOW() COMMENT '创建时间',
	gmt_modified   DATETIME    DEFAULT NOW() COMMENT '修改时间',
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci
	COMMENT ='地址表'
	AUTO_INCREMENT = 1;

# 插入测试数据
INSERT INTO address (name, user_id, address_detail)
	VALUES ('laifengting', 13023573685, '江苏省 南通市 通州区 西亭镇 西亭圆通速递'),
		   ('laifengting', 13023573685, '江苏省 南通市 通州区 骑岸镇 骑岸中通快递'),
		   ('laifengting', 2, '江苏省 南通市 通州区 石港镇 石港百世速递'),
		   ('laifengting', 2, '江苏省 南通市 通州区 金沙镇 金沙顺丰速递'),
		   ('laifengting', 3, '江苏省 南通市 通州区 兴仁镇 兴仁德邦快递'),
		   ('laifengting', 3, '江苏省 南通市 通州区 兴东镇 兴东极兔速递');

# 删除库
DROP DATABASE IF EXISTS miaosha;

# 新建库
CREATE DATABASE miaosha DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 使用库
USE miaosha;

# 删除表
DROP TABLE IF EXISTS miaosha_user;

# 新建表
CREATE TABLE miaosha_user (
	id             BIGINT(20)   NOT NULL COMMENT '用户 id，手机号码',
	nickname       VARCHAR(255) NOT NULL COMMENT '用户昵称',
	password       VARCHAR(32)  DEFAULT NULL COMMENT 'MD5(MD5(password明文 + 固定 salt) + salt)',
	salt           VARCHAR(10)  DEFAULT NULL COMMENT 'MD5加密的盐值',
	head           VARCHAR(128) DEFAULT NULL COMMENT '头像，云存储的 ID',
	gmt_created    DATETIME     DEFAULT NULL COMMENT '注册时间',
	gmt_last_login DATETIME     DEFAULT NULL COMMENT '上次登录时间',
	login_count    INT(11)      DEFAULT 0 COMMENT '登录次数',
	PRIMARY KEY (id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_unicode_ci
	COMMENT ='用户表'
	AUTO_INCREMENT = 1;

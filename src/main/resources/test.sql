USE `test`;

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(16) NOT NULL UNIQUE COMMENT '唯一编码',
  `app_id` int(11) NOT NULL COMMENT '应用来源',
  `created_date` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `send_date` datetime NOT NULL DEFAULT NOW() COMMENT '发送时间',
  `status` int(1) DEFAULT 1 COMMENT '状态',
  `send` boolean DEFAULT false COMMENT '发送成功',
  `important` boolean DEFAULT false COMMENT '重要消息',

  `subject` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件标题',
  `from` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件发件人',
  `to` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件收件人',
  `body` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件内容',

  `error_code` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '错误码',
  `error_description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '错误信息',

  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息';


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(16) NOT NULL UNIQUE COMMENT '用户名',
  `password` varchar(16) NOT NULL COMMENT '密码',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `email` varchar(255) CHARACTER SET utf8 UNIQUE COMMENT '邮件',
  `phone` varchar(255) CHARACTER SET utf8 UNIQUE COMMENT '手机号',

  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

DROP TABLE IF EXISTS `user_setting`;
CREATE TABLE `user_setting` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(16) NOT NULL COMMENT '用户',
  `app_id` varchar(16) NOT NULL COMMENT '应用',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态',

  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户配置';


CREATE TABLE `app_info` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL UNIQUE COMMENT 'APP名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='应用信息表';

DROP TABLE IF EXISTS `app_setting`;
CREATE TABLE `app_setting` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(16) NOT NULL COMMENT '应用',
  `send_limit` int(1) DEFAULT 0 COMMENT '重发次数',
  `send_time` datetime COMMENT '重发时间',
  `silence_start_time` time COMMENT '静默开始时间',
  `silence_end_time` time COMMENT '静默开始时间',

  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用配置';

-- 应用信息
INSERT INTO `app_info` VALUES (1, 'testapp1');
INSERT INTO `app_info` VALUES (2, 'testapp2');

-- 用户信息
INSERT INTO `user` VALUES (1, 'testuser1', '111111', 1, 'testuser1@localhost', '18611897539');
INSERT INTO `user` VALUES (2, 'testuser2', '111111', 1, 'testuser2@localhost', '18511897539');

-- 应用配置
INSERT INTO `app_setting` VALUES (1, 2, 0, 2016-07-04 14:29:30, 14:00:30, 14:29:30);

-- 用户配置
INSERT INTO `user_setting` VALUES (1, 1, 1, 1);

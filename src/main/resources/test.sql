USE `test`;

DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(16) DEFAULT NULL COMMENT '唯一编码',
  `app_id` int(11) DEFAULT NULL COMMENT '应用来源',
  `created_date` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `send_date` datetime NOT NULL DEFAULT NOW() COMMENT '发送时间',
  `procedure_id` int(11) DEFAULT NULL COMMENT '规则代码',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `success` int(11) DEFAULT NULL COMMENT '成功个数',
  `fail` int(11) DEFAULT NULL COMMENT '失败个数',

  `mail_subject` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件标题',
  `mail_from` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件发件人',
  `mail_to` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件收件人',
  `mail_body` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件内容',

  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件';

DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(16) DEFAULT NULL COMMENT '唯一编码',
  `app_id` int(11) DEFAULT NULL COMMENT '应用来源',
  `created_date` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `send_date` datetime NOT NULL DEFAULT NOW() COMMENT '发送时间',
  `procedure_id` int(11) DEFAULT NULL COMMENT '规则代码',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `success` int(11) DEFAULT NULL COMMENT '成功个数',
  `fail` int(11) DEFAULT NULL COMMENT '失败个数',

  `sms_content` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '短信内容',
  `sms_recevier` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件发件人',

  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件';




CREATE TABLE `app_info` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `enabled` int(2) DEFAULT NULL COMMENT '是否可用',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `qq` varchar(14) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(100) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='应用信息表';

-- 应用信息
INSERT INTO `app_info` VALUES ('1', 'test1', 'ffff', '1', null, null, null, null);
INSERT INTO `app_info` VALUES ('2', 'test2', 'aaaa', '2', null, null, null, null);
INSERT INTO `app_info` VALUES ('3', 'test3', 'bbbb', '1', null, null, null, null);
INSERT INTO `app_info` VALUES ('4', 'test4', 'cccc', '2', null, null, null, null);
INSERT INTO `app_info` VALUES ('5', 'test5', 'dddd', '1', null, null, null, null);

-- 邮件信息
INSERT INTO `email` VALUES (1,'0111111111111111', 1, '2016-01-01 12:00:00', '2016-01-01 12:00:00', null, 1, 1, 0, 'test01', 'from01@localhost', 'to01@localhost', 'test01body');
INSERT INTO `email` VALUES (2,'0222222222222222', 1, '2016-01-01 12:00:00', '2016-01-01 12:00:00', null, 1, 1, 0, 'test02', 'from02@localhost', 'to02@localhost', 'test02body');

-- 短信消息
INSERT INTO `sms` VALUES (1,'1111111111111111', 1, '2016-01-01 12:00:00', '2016-01-01 12:00:00', null, 1, 1, 0, 'test01', '18511897539');
INSERT INTO `sms` VALUES (2,'1222222222222222', 1, '2016-01-01 12:00:00', '2016-01-01 12:00:00', null, 1, 1, 0, 'test02', '15055119876');

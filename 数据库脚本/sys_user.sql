/*
Navicat MySQL Data Transfer

Source Server         : itcast_0513
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : slife

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-10-23 16:43:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(18) NOT NULL COMMENT '用户id',
  `create_id` bigint(18) DEFAULT NULL COMMENT '创建者id',
  `update_id` bigint(18) DEFAULT NULL COMMENT '修改者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '删除标记Y：正常；N：删除；A：审核',
  `login_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '登录用户名',
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '登录密码',
  `no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `mobile` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `photo` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像',
  `login_flag` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否可登录：可以:Y 否:N',
  `sys_company_id` bigint(18) DEFAULT NULL COMMENT '所在公司id',
  PRIMARY KEY (`id`),
  KEY `sys_company_id` (`sys_company_id`),
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`sys_company_id`) REFERENCES `sys_company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, '1', '2017-03-31 13:46:22', '2017-10-12 20:44:12', '描述信息', 'Y', 'admin', '7c10c6e49aea719bbff805fb14096719cd5fc982074c6ee641168024', '1', '超级管理员', '122741483@qq.com', '0788-2548756', '13248754856', '/img/log9.jpeg', '1', '1');

/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-04-16 17:06:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role_relation_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_relation_permission`;
CREATE TABLE `role_relation_permission` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_relation_permission
-- ----------------------------
INSERT INTO `role_relation_permission` VALUES ('1', '1', '1');
INSERT INTO `role_relation_permission` VALUES ('2', '1', '2');
INSERT INTO `role_relation_permission` VALUES ('3', '2', '2');
INSERT INTO `role_relation_permission` VALUES ('4', '2', '3');

-- ----------------------------
-- Table structure for user_relation_role
-- ----------------------------
DROP TABLE IF EXISTS `user_relation_role`;
CREATE TABLE `user_relation_role` (
  `id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_relation_role
-- ----------------------------
INSERT INTO `user_relation_role` VALUES ('1', '1', '1');
INSERT INTO `user_relation_role` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for xb_permission
-- ----------------------------
DROP TABLE IF EXISTS `xb_permission`;
CREATE TABLE `xb_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(100) DEFAULT NULL,
  `permission_abbr` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xb_permission
-- ----------------------------
INSERT INTO `xb_permission` VALUES ('1', '查询用户', 'FIND_USER');
INSERT INTO `xb_permission` VALUES ('2', '删除用户', 'DELETE_USER');
INSERT INTO `xb_permission` VALUES ('3', '查询当前用户权限', 'FIND_CURRENTUSER');

-- ----------------------------
-- Table structure for xb_role
-- ----------------------------
DROP TABLE IF EXISTS `xb_role`;
CREATE TABLE `xb_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL,
  `role_abbr` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xb_role
-- ----------------------------
INSERT INTO `xb_role` VALUES ('1', '管理员', 'admin');
INSERT INTO `xb_role` VALUES ('2', '访客', 'vistor');

-- ----------------------------
-- Table structure for xb_user
-- ----------------------------
DROP TABLE IF EXISTS `xb_user`;
CREATE TABLE `xb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `mobile` int(12) DEFAULT NULL,
  `sex` tinyint(2) DEFAULT NULL COMMENT '1:男 2：女',
  `enabled` tinyint(2) DEFAULT NULL,
  `session_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xb_user
-- ----------------------------
INSERT INTO `xb_user` VALUES ('1', 'ltzhang', '$2a$10$NnE91ZfnGc2JR/70fJ9yPOAk.4IunScCu8h2Gm7qkGcqz9nyvem56', '1665516979', '1', '0', '0b31b184-c15a-4ff6-8bca-bb136873bb5b');
INSERT INTO `xb_user` VALUES ('2', 'test', '$2a$10$NnE91ZfnGc2JR/70fJ9yPOAk.4IunScCu8h2Gm7qkGcqz9nyvem56', '122111', '2', '1', 'fe7db42e-63a8-4be5-9a37-2fe71f5f1426');

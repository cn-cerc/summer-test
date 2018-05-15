/*
 Navicat Premium Data Transfer

 Source Server         : i-develop
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : rm-wz9512wu1my95469lpo.mysql.rds.aliyuncs.com:3306
 Source Schema         : sample

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 15/05/2018 17:32:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ip_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `ip_blacklist`;
CREATE TABLE `ip_blacklist`  (
  `UID_` bigint(11) NOT NULL AUTO_INCREMENT,
  `ip_` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime_` datetime(0) NOT NULL,
  PRIMARY KEY (`UID_`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 286 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

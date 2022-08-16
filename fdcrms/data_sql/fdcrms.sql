/*
 Navicat Premium Data Transfer

 Source Server         : MySQL57
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3307
 Source Schema         : fdcrms

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 16/08/2022 16:20:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_bill`;
CREATE TABLE `t_bill`  (
  `bill_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '账单编号',
  `bill_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '账单日期',
  `bill_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消费类型',
  `bill_money` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '消费金额',
  `bill_consumer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消费人',
  PRIMARY KEY (`bill_no`) USING BTREE,
  INDEX `FK_Bill_Mem`(`bill_consumer`) USING BTREE,
  CONSTRAINT `FK_Bill_Mem` FOREIGN KEY (`bill_consumer`) REFERENCES `t_member` (`mem_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20220007 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账单记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_bill
-- ----------------------------
INSERT INTO `t_bill` VALUES (20220001, '2022-07-29 23:36:30', '西服', 999.00, '张三丰');
INSERT INTO `t_bill` VALUES (20220002, '2022-08-02 00:27:00', '西服', 1000.00, '张三丰');
INSERT INTO `t_bill` VALUES (20220003, '2022-08-03 23:02:00', '太极跌打丸', 1000.00, '张三丰');
INSERT INTO `t_bill` VALUES (20220004, '2022-07-29 23:36:30', '高跟鞋', 1999.00, '赵敏');
INSERT INTO `t_bill` VALUES (20220005, '2022-09-01 23:46:03', '西服', 2999.00, '张三丰');
INSERT INTO `t_bill` VALUES (20220006, '2023-01-01 23:57:32', '高跟鞋', 9999.00, '赵敏');

-- ----------------------------
-- Table structure for t_login
-- ----------------------------
DROP TABLE IF EXISTS `t_login`;
CREATE TABLE `t_login`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `u_pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE,
  INDEX `FK_NameByPhone`(`u_name`) USING BTREE,
  CONSTRAINT `FK_NameByPhone` FOREIGN KEY (`u_name`) REFERENCES `t_member` (`mem_phone`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_login
-- ----------------------------
INSERT INTO `t_login` VALUES (1, '18715757908', '123456');
INSERT INTO `t_login` VALUES (2, '15228825709', '123456');

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`  (
  `mem_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '家庭成员编号',
  `mem_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '家庭成员姓名',
  `mem_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '家庭成员登录电话',
  `mem_gender` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '家庭成员性别',
  `mem_age` int(11) NOT NULL COMMENT '家庭成员年龄',
  PRIMARY KEY (`mem_no`) USING BTREE,
  UNIQUE INDEX `mem_phone`(`mem_phone`) USING BTREE COMMENT '家庭成员手机号唯一约束',
  UNIQUE INDEX `mem_name`(`mem_name`) USING BTREE COMMENT '家庭成员姓名唯一约束'
) ENGINE = InnoDB AUTO_INCREMENT = 1003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家庭成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES (1001, '张三丰', '18715757908', '男', 32);
INSERT INTO `t_member` VALUES (1002, '赵敏', '15228825709', '女', 20);

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : wintermilktea

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 22/12/2021 09:25:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Admin
-- ----------------------------
DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_password` varchar(255) DEFAULT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of Admin
-- ----------------------------
BEGIN;
INSERT INTO `Admin` VALUES (1, 'dc76e9f0c0006e8f919e0c515c66dbba3982f785', 'Boss');
COMMIT;

-- ----------------------------
-- Table structure for Product
-- ----------------------------
DROP TABLE IF EXISTS `Product`;
CREATE TABLE `Product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  `product_price` decimal(10,2) DEFAULT NULL,
  `product_image_id` varchar(255) DEFAULT NULL,
  `product_material_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`,`product_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of Product
-- ----------------------------
BEGIN;
INSERT INTO `Product` VALUES (1, '水', '饮品', 2.00, NULL, '水@测试');
INSERT INTO `Product` VALUES (2, '火', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` varchar(255) NOT NULL,
  `comment_order_id` varchar(255) DEFAULT NULL,
  `comment_user_id` varchar(0) DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  `comment_finish` tinyint(255) DEFAULT '0' COMMENT '0为false、1为true',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `material_name` varchar(255) NOT NULL,
  `material_stock` int(255) DEFAULT '0',
  `material_price` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`material_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of material
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` varchar(255) NOT NULL,
  `order_customer_id` varchar(255) DEFAULT NULL,
  `order_price` decimal(65,2) DEFAULT NULL,
  `order_is_pay` tinyint(255) DEFAULT '0',
  `order_real_pay` decimal(65,0) DEFAULT NULL,
  `order_pay_date_time` datetime(6) DEFAULT NULL,
  `order_is_finish` tinyint(255) DEFAULT '0',
  `order_product_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_integral` double(255,0) DEFAULT NULL,
  `user_birthday` date DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_order_list` varchar(255) DEFAULT NULL,
  `user_image_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

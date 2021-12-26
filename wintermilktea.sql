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

 Date: 26/12/2021 17:13:59
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
  PRIMARY KEY (`product_id`,`product_name`) USING BTREE,
  KEY `product_name` (`product_name`),
  KEY `产品原料关联` (`product_material_list`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of Product
-- ----------------------------
BEGIN;
INSERT INTO `Product` VALUES (1, 'ji', 'ji', 3.00, 'iii', 'ji@ji@Ji');
INSERT INTO `Product` VALUES (2, '可乐', '饮品', 13.00, 'iii', '可乐@纯净水@果葡萄浆');
INSERT INTO `Product` VALUES (3, '果粒橙', '饮品', 13.00, 'iii', '可乐@纯净水@果葡萄浆');
INSERT INTO `Product` VALUES (4, '橙汁', '饮品', 13.00, 'iii', '可乐@纯净水@果葡萄浆');
INSERT INTO `Product` VALUES (5, '青瓜汁', '饮品', 13.00, 'iii', '纯净水@果葡萄浆');
INSERT INTO `Product` VALUES (6, '苹果汁', '饮品', 13.00, 'iii', '纯净水@果葡萄浆');
INSERT INTO `Product` VALUES (7, '草莓汁', '饮品', 13.00, 'iii', '可乐@纯净水@果葡萄浆');
INSERT INTO `Product` VALUES (9, '冬乳奶茶1', '奶茶', 11.00, 'iiiiii', '几把');
INSERT INTO `Product` VALUES (10, '冬乳奶茶-1483632412', '奶茶', 18.00, 'iiiiii', '水@糖@奶茶粉');
INSERT INTO `Product` VALUES (11, '冬乳奶茶-595611310', '奶茶', 18.00, 'iiiiii', '水@糖@奶茶粉');
INSERT INTO `Product` VALUES (12, '冬乳奶茶-1160470624', '奶茶', 18.00, 'iiiiii', '水@糖@奶茶粉');
INSERT INTO `Product` VALUES (13, '草草草?productMaterialList=null', NULL, NULL, NULL, '');
INSERT INTO `Product` VALUES (14, '草草草', NULL, NULL, NULL, '');
INSERT INTO `Product` VALUES (15, '草草草', NULL, NULL, NULL, 'null');
INSERT INTO `Product` VALUES (16, '草草草', NULL, NULL, NULL, 'null');
INSERT INTO `Product` VALUES (17, '草ds草草', NULL, NULL, NULL, 'null');
INSERT INTO `Product` VALUES (18, '草ds草dddd草', NULL, NULL, NULL, 'null');
INSERT INTO `Product` VALUES (19, '草ds草dddd草', NULL, NULL, NULL, 'null@cao@aa@www');
INSERT INTO `Product` VALUES (21, '冬乳奶茶-2081995926', '奶茶', 18.00, 'iiiiii', '水@糖@奶茶粉');
INSERT INTO `Product` VALUES (22, '冬乳奶茶-1358763659', '奶茶', 18.00, 'iiiiii', '水@糖@奶茶粉');
INSERT INTO `Product` VALUES (23, '冬乳奶茶661572984', '奶茶', 18.00, 'iiiiii', '水@糖@奶茶粉');
INSERT INTO `Product` VALUES (25, '草ds草dddd草', NULL, NULL, NULL, 'null@cao@aa@www');
INSERT INTO `Product` VALUES (27, '冬乳奶茶', '奶茶', 18.00, 'iiiiii', '水@糖@奶茶粉');
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` varchar(255) NOT NULL,
  `comment_order_id` varchar(255) DEFAULT NULL,
  `comment_user_id` varchar(255) DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  `comment_finish` tinyint(255) DEFAULT '0' COMMENT '0为false、1为true',
  PRIMARY KEY (`comment_id`),
  KEY `评价订单关联` (`comment_order_id`),
  KEY `评价用户关联` (`comment_user_id`),
  CONSTRAINT `评价用户关联` FOREIGN KEY (`comment_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `评价订单关联` FOREIGN KEY (`comment_order_id`) REFERENCES `order` (`order_id`)
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
INSERT INTO `material` VALUES ('test1', 13, 111.00);
INSERT INTO `material` VALUES ('test2', 14, 697.30);
INSERT INTO `material` VALUES ('奶茶粉', 50, 3.00);
INSERT INTO `material` VALUES ('果葡萄浆', 230, 2.00);
INSERT INTO `material` VALUES ('纯净水', 100, 1.00);
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` varchar(255) NOT NULL,
  `order_customer_id` varchar(255) DEFAULT NULL,
  `order_price` decimal(65,2) DEFAULT NULL,
  `order_is_pay` tinyint(1) DEFAULT '0',
  `order_real_pay` decimal(65,0) DEFAULT NULL,
  `order_pay_date_time` datetime(6) DEFAULT NULL,
  `order_is_finish` tinyint(1) DEFAULT '0',
  `order_create_date_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `订单用户关联` (`order_customer_id`),
  CONSTRAINT `订单用户关联` FOREIGN KEY (`order_customer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES ('202112252103051', '1', 15.00, 0, -1, NULL, 0, '2021-12-25 21:03:05.082000');
INSERT INTO `order` VALUES ('3', '1', 1.00, 1, NULL, '2021-03-22 00:00:00.000000', 1, NULL);
INSERT INTO `order` VALUES ('4', '1', 1.00, 1, NULL, '2021-03-22 00:00:00.000000', 1, NULL);
INSERT INTO `order` VALUES ('test', 'test', 13.20, 1, 6667, '2021-12-24 21:19:24.770000', 1, '2021-12-24 21:19:24.770000');
INSERT INTO `order` VALUES ('test1111', 'test', 13.20, 0, 0, '2021-12-24 21:23:46.698000', 0, '2021-12-24 21:23:46.698000');
INSERT INTO `order` VALUES ('test1211', 'test', 13.20, 0, 0, '2021-12-24 21:25:52.396000', 0, '2021-12-24 21:25:52.396000');
COMMIT;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `item_id` varchar(255) NOT NULL,
  `order_id` varchar(255) NOT NULL,
  `product_id` int(255) DEFAULT NULL,
  `item_num` int(11) DEFAULT NULL,
  `cup_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `关联` (`order_id`),
  CONSTRAINT `关联` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_item
-- ----------------------------
BEGIN;
INSERT INTO `order_item` VALUES ('2021122521030510', '202112252103051', 6, 3, '大杯');
COMMIT;

-- ----------------------------
-- Table structure for shoppingcar
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcar`;
CREATE TABLE `shoppingcar` (
  `customer_id` varchar(255) NOT NULL,
  `order_item_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of shoppingcar
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for shoppingcar_item
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcar_item`;
CREATE TABLE `shoppingcar_item` (
  `shoppingcar_item_id` int(255) NOT NULL AUTO_INCREMENT,
  `shoppingcar_id` varchar(255) DEFAULT NULL,
  `shoppingcar_order_item_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shoppingcar_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of shoppingcar_item
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
  `user_phone` varchar(255) NOT NULL,
  `user_image_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`user_phone`) USING BTREE,
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('011c945f30ce2cbafc452f39840f025693339c42', 'cao', '011c945f30ce2cbafc452f39840f025693339c42', NULL, NULL, '1111', NULL);
INSERT INTO `user` VALUES ('1', '123', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', NULL, NULL, '44444', NULL);
INSERT INTO `user` VALUES ('1233', NULL, '40bd001563085fc35165329ea1ff5c5ecbdbbeef', NULL, NULL, '12345678900', NULL);
INSERT INTO `user` VALUES ('2ea6201a068c5fa0eea5d81a3863321a87f8d533', '歌坛噩梦', 'fea7f657f56a2a448da7d4b535ee5e279caf3d9a', NULL, NULL, '1111111', NULL);
INSERT INTO `user` VALUES ('77bce9fb18f977ea576bbcd143b2b521073f0cd6', NULL, 'e7d0dc6d36cea367b1c9cc97ad81b429e434744c', NULL, NULL, '333333', NULL);
INSERT INTO `user` VALUES ('d9ed43826f8ebbf754a3cd0e45f68b1f4072358d', NULL, '14019988a92023b21c8fbafb2b615c6ce575da38', NULL, NULL, '3333336', NULL);
INSERT INTO `user` VALUES ('id3', 'test3', 'test', 663, '2021-12-24', 'test', 'test');
INSERT INTO `user` VALUES ('test', 'test2', 'test', 663, '2021-12-23', 'test', 'test');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

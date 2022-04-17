/*
 Navicat Premium Data Transfer

 Source Server         : conn
 Source Server Type    : MySQL
 Source Server Version : 100424
 Source Host           : localhost:3306
 Source Schema         : cms_db

 Target Server Type    : MySQL
 Target Server Version : 100424
 File Encoding         : 65001

 Date: 17/04/2022 23:53:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int NULL DEFAULT 0,
  `create_time` timestamp NULL DEFAULT current_timestamp,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('19127494', '$2a$10$nmT8WiNKULrghK4phPPrIeYBTblimHiSno5.scnef4lN9Ncx0pjzO', 1, '2022-04-17 23:02:14');
INSERT INTO `account` VALUES ('19127541', '$2a$10$cTvB8666lmssOa1M9ZCJIetE/bO87hWOnIcqi37UjwZenRcYNfCIu', 1, '2022-04-17 23:07:01');
INSERT INTO `account` VALUES ('19127546', '$2a$10$QVDTjX00U2GInpofg59sMeRmyc45O4g/RFY9t9k1VpepsKfG1b9ru', 1, '2022-04-17 23:05:55');
INSERT INTO `account` VALUES ('admin', '$2a$10$mCLTJ6/8dq6DO2O5jWXnjeiPtzHwqWzSQf2Th.K5tmxprevxlQA.K', 0, '2022-04-15 09:26:53');

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `area_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`area_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES (1, 'Tầng trệt', 1);
INSERT INTO `area` VALUES (2, 'Tầng 1', 1);
INSERT INTO `area` VALUES (3, 'Tầng 2', 1);

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `user_id` int NOT NULL,
  `check_time` timestamp NOT NULL DEFAULT current_timestamp,
  `type` int NOT NULL,
  PRIMARY KEY (`user_id`, `check_time`) USING BTREE,
  CONSTRAINT `fk_attendance_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of attendance
-- ----------------------------

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `bill_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `discount` float(255, 0) NULL DEFAULT NULL,
  `customer_id` int NULL DEFAULT NULL,
  `payment_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `promotion` int NULL DEFAULT NULL,
  `total` float(255, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`bill_id`) USING BTREE,
  INDEX `fk_customer_id`(`customer_id`) USING BTREE,
  INDEX `fk_bill_promotion_idx`(`promotion`) USING BTREE,
  INDEX `fk_bill_user_idx`(`user_id`) USING BTREE,
  CONSTRAINT `fk_bill_promotion` FOREIGN KEY (`promotion`) REFERENCES `promotion` (`promotion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bill_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (1, 2, '2022-03-01 10:30:24', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (2, 3, '2022-03-04 10:30:24', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (3, 4, '2022-03-06 10:30:24', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (4, 2, '2022-03-08 11:30:25', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (5, 3, '2022-03-17 17:30:25', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (6, 4, '2022-03-18 19:30:25', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (7, 2, '2022-03-19 22:30:26', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (8, 3, '2022-03-22 19:30:26', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (9, 4, '2022-03-24 15:30:26', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (10, 2, '2022-03-30 16:30:28', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (11, 3, '2022-04-17 23:34:14', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (12, 4, '2022-04-17 23:34:14', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (13, 2, '2022-04-17 23:34:15', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (14, 3, '2022-04-17 23:34:15', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `bill` VALUES (15, 4, '2022-04-17 23:34:15', NULL, NULL, NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for billdetail
-- ----------------------------
DROP TABLE IF EXISTS `billdetail`;
CREATE TABLE `billdetail`  (
  `billDetail_id` int NOT NULL AUTO_INCREMENT,
  `bill_id` int NULL DEFAULT NULL,
  `item_id` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `note` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `discount` float NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  PRIMARY KEY (`billDetail_id`) USING BTREE,
  INDEX `fk_billDetail_bill_idx`(`bill_id`) USING BTREE,
  INDEX `fk_billDetail_item_idx`(`item_id`) USING BTREE,
  CONSTRAINT `fk_billDetail_bill` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`bill_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_billDetail_item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of billdetail
-- ----------------------------
INSERT INTO `billdetail` VALUES (1, 1, 59, 5, NULL, NULL, 145000);
INSERT INTO `billdetail` VALUES (2, 1, 30, 4, NULL, NULL, 144000);
INSERT INTO `billdetail` VALUES (3, 1, 6, 2, NULL, NULL, 48000);
INSERT INTO `billdetail` VALUES (4, 1, 12, 2, NULL, NULL, 68000);
INSERT INTO `billdetail` VALUES (5, 1, 18, 3, NULL, NULL, 60000);
INSERT INTO `billdetail` VALUES (6, 2, 32, 4, NULL, NULL, 116000);
INSERT INTO `billdetail` VALUES (7, 2, 14, 3, NULL, NULL, 84000);
INSERT INTO `billdetail` VALUES (8, 3, 30, 1, NULL, NULL, 36000);
INSERT INTO `billdetail` VALUES (9, 3, 33, 4, NULL, NULL, 116000);
INSERT INTO `billdetail` VALUES (10, 3, 40, 1, NULL, NULL, 29000);
INSERT INTO `billdetail` VALUES (11, 3, 18, 4, NULL, NULL, 80000);
INSERT INTO `billdetail` VALUES (12, 3, 52, 1, NULL, NULL, 19000);
INSERT INTO `billdetail` VALUES (13, 4, 46, 4, NULL, NULL, 48000);
INSERT INTO `billdetail` VALUES (14, 4, 50, 1, NULL, NULL, 19000);
INSERT INTO `billdetail` VALUES (15, 5, 4, 1, NULL, NULL, 24000);
INSERT INTO `billdetail` VALUES (16, 6, 33, 3, NULL, NULL, 87000);
INSERT INTO `billdetail` VALUES (17, 7, 8, 2, NULL, NULL, 68000);
INSERT INTO `billdetail` VALUES (18, 7, 53, 3, NULL, NULL, 117000);
INSERT INTO `billdetail` VALUES (19, 7, 17, 3, NULL, NULL, 60000);
INSERT INTO `billdetail` VALUES (20, 7, 7, 3, NULL, NULL, 102000);
INSERT INTO `billdetail` VALUES (21, 8, 21, 4, NULL, NULL, 80000);
INSERT INTO `billdetail` VALUES (22, 8, 31, 1, NULL, NULL, 29000);
INSERT INTO `billdetail` VALUES (23, 8, 54, 3, NULL, NULL, 87000);
INSERT INTO `billdetail` VALUES (24, 8, 42, 3, NULL, NULL, 30000);
INSERT INTO `billdetail` VALUES (25, 8, 36, 1, NULL, NULL, 29000);
INSERT INTO `billdetail` VALUES (26, 9, 31, 3, NULL, NULL, 87000);
INSERT INTO `billdetail` VALUES (27, 10, 53, 2, NULL, NULL, 78000);
INSERT INTO `billdetail` VALUES (28, 10, 19, 1, NULL, NULL, 20000);
INSERT INTO `billdetail` VALUES (29, 11, 6, 3, NULL, NULL, 72000);
INSERT INTO `billdetail` VALUES (30, 11, 41, 4, NULL, NULL, 116000);
INSERT INTO `billdetail` VALUES (31, 11, 21, 2, NULL, NULL, 40000);
INSERT INTO `billdetail` VALUES (32, 12, 1, 1, NULL, NULL, 20000);
INSERT INTO `billdetail` VALUES (33, 12, 19, 2, NULL, NULL, 40000);
INSERT INTO `billdetail` VALUES (34, 12, 31, 3, NULL, NULL, 87000);
INSERT INTO `billdetail` VALUES (35, 12, 44, 3, NULL, NULL, 30000);
INSERT INTO `billdetail` VALUES (36, 13, 60, 2, NULL, NULL, 64000);
INSERT INTO `billdetail` VALUES (37, 13, 46, 3, NULL, NULL, 36000);
INSERT INTO `billdetail` VALUES (38, 13, 55, 1, NULL, NULL, 29000);
INSERT INTO `billdetail` VALUES (39, 13, 45, 2, NULL, NULL, 20000);
INSERT INTO `billdetail` VALUES (40, 13, 40, 3, NULL, NULL, 87000);
INSERT INTO `billdetail` VALUES (41, 14, 37, 3, NULL, NULL, 87000);
INSERT INTO `billdetail` VALUES (42, 14, 19, 2, NULL, NULL, 40000);
INSERT INTO `billdetail` VALUES (43, 14, 32, 3, NULL, NULL, 87000);
INSERT INTO `billdetail` VALUES (44, 15, 25, 2, NULL, NULL, 64000);
INSERT INTO `billdetail` VALUES (45, 15, 61, 2, NULL, NULL, 70000);
INSERT INTO `billdetail` VALUES (46, 15, 10, 3, NULL, NULL, 93000);

-- ----------------------------
-- Table structure for billpromotion
-- ----------------------------
DROP TABLE IF EXISTS `billpromotion`;
CREATE TABLE `billpromotion`  (
  `billPromotion_id` int NOT NULL AUTO_INCREMENT,
  `promotion_id` int NOT NULL,
  `discount` float NULL DEFAULT NULL,
  PRIMARY KEY (`billPromotion_id`) USING BTREE,
  INDEX `fk_billPromotion_promotion_idx`(`promotion_id`) USING BTREE,
  CONSTRAINT `fk_billPromotion_promotion` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`promotion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of billpromotion
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `icon_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Cà phê', NULL);
INSERT INTO `category` VALUES (2, 'Nước ép', NULL);
INSERT INTO `category` VALUES (3, 'Trà', NULL);
INSERT INTO `category` VALUES (4, 'Trà sữa', NULL);
INSERT INTO `category` VALUES (5, 'Nước ngọt', NULL);
INSERT INTO `category` VALUES (6, 'Bánh ngọt', NULL);
INSERT INTO `category` VALUES (7, 'Bánh mì', NULL);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `point` int NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Nguyễn Văn A ', '123456789', 0, '2001-05-06');
INSERT INTO `customer` VALUES (2, 'Nguyễn Văn B', '456789123', 0, '2000-04-16');
INSERT INTO `customer` VALUES (3, 'Nguyễn Văn C', '789123456', 0, '1995-05-23');
INSERT INTO `customer` VALUES (4, 'Nguyễn Văn D', '987654321', 0, '1997-11-12');
INSERT INTO `customer` VALUES (5, 'Nguyễn Văn E', '654321987', 0, '1999-08-30');
INSERT INTO `customer` VALUES (6, 'Nguyễn Văn F', '321987654', 0, '2002-04-01');
INSERT INTO `customer` VALUES (7, 'Nguyễn Văn G', '159687423', 0, '1998-03-19');
INSERT INTO `customer` VALUES (8, 'Nguyễn Văn H', '652314897', 0, '2000-01-17');
INSERT INTO `customer` VALUES (9, 'Nguyễn Văn I', '165238497', 0, '1994-02-26');
INSERT INTO `customer` VALUES (10, 'Nguyễn Văn J', '453268971', 0, '1999-05-29');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `fk_item_type_idx`(`category`) USING BTREE,
  CONSTRAINT `fk_item_type` FOREIGN KEY (`category`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1, 'Bạc Sỉu Đá', 'Bạc sỉu chính là \"Ly sữa trắng kèm một chút cà phê\". Thức uống này rất phù hợp những ai vừa muốn trải nghiệm chút vị đắng của cà phê vừa muốn thưởng thức vị ngọt béo ngậy từ sữa.', 1, NULL, 20000);
INSERT INTO `item` VALUES (2, 'Bạc Sỉu Nóng', 'Bạc sỉu chính là \"Ly sữa trắng kèm một chút cà phê\". Thức uống này rất phù hợp những ai vừa muốn trải nghiệm chút vị đắng của cà phê vừa muốn thưởng thức vị ngọt béo ngậy từ sữa.', 1, NULL, 24000);
INSERT INTO `item` VALUES (3, 'Cà phê đen đá', 'Không ngọt ngào như Bạc sỉu hay Cà phê sữa, Cà phê đen mang trong mình phong vị trầm lắng, thi vị hơn. Người ta thường phải ngồi rất lâu mới cảm nhận được hết hương thơm ngào ngạt, phảng phất mùi cacao và cái đắng mượt mà trôi tuột xuống vòm họng.', 1, NULL, 20000);
INSERT INTO `item` VALUES (4, 'Cà phê đen nóng', 'Không ngọt ngào như Bạc sỉu hay Cà phê sữa, Cà phê đen mang trong mình phong vị trầm lắng, thi vị hơn. Người ta thường phải ngồi rất lâu mới cảm nhận được hết hương thơm ngào ngạt, phảng phất mùi cacao và cái đắng mượt mà trôi tuột xuống vòm họng.', 1, NULL, 24000);
INSERT INTO `item` VALUES (5, 'Cà phê sữa đá', 'Cà phê được pha phin truyền thống kết hợp với sữa đặc tạo nên hương vị đậm đà, hài hòa giữa vị ngọt đầu lưỡi và vị đắng thanh thoát nơi hậu vị.', 1, NULL, 20000);
INSERT INTO `item` VALUES (6, 'Cà phê sữa nóng', 'Cà phê được pha phin truyền thống kết hợp với sữa đặc tạo nên hương vị đậm đà, hài hòa giữa vị ngọt đầu lưỡi và vị đắng thanh thoát nơi hậu vị.', 1, NULL, 24000);
INSERT INTO `item` VALUES (7, 'Mocha nóng', 'Loại cà phê được tạo nên từ sự kết hợp hoàn hảo của vị đắng đậm đà của Espresso và sốt sô-cô-la ngọt ngào mang tới hương vị đầy lôi cuốn, đánh thức mọi giác quan nên đây chính là thức uống ưa thích của phụ nữ và giới trẻ.', 1, NULL, 34000);
INSERT INTO `item` VALUES (8, 'Mocha đá', 'Loại cà phê được tạo nên từ sự kết hợp hoàn hảo của vị đắng đậm đà của Espresso và sốt sô-cô-la ngọt ngào mang tới hương vị đầy lôi cuốn, đánh thức mọi giác quan nên đây chính là thức uống ưa thích của phụ nữ và giới trẻ.', 1, NULL, 34000);
INSERT INTO `item` VALUES (9, 'Espresso nóng', 'Một tách Espresso nguyên bản được bắt đầu bởi những hạt Arabica chất lượng, phối trộn với tỉ lệ cân đối hạt Robusta, cho ra vị ngọt caramel, vị chua dịu và sánh đặc.', 1, NULL, 28000);
INSERT INTO `item` VALUES (10, 'Espresso đá', 'Một tách Espresso nguyên bản được bắt đầu bởi những hạt Arabica chất lượng, phối trộn với tỉ lệ cân đối hạt Robusta, cho ra vị ngọt caramel, vị chua dịu và sánh đặc.', 1, NULL, 31000);
INSERT INTO `item` VALUES (11, 'Cappuccino nóng', 'Cappuchino là thức uống hòa quyện giữa hương thơm của sữa, vị béo của bọt kem cùng vị đậm đà từ cà phê Espresso. Tất cả tạo nên một hương vị đặc biệt, nhẹ nhàng, trầm lắng và tinh tế.', 1, NULL, 34000);
INSERT INTO `item` VALUES (12, 'Cappuccino đá', 'Cappuchino là thức uống hòa quyện giữa hương thơm của sữa, vị béo của bọt kem cùng vị đậm đà từ cà phê Espresso. Tất cả tạo nên một hương vị đặc biệt, nhẹ nhàng, trầm lắng và tinh tế.', 1, NULL, 34000);
INSERT INTO `item` VALUES (13, 'Americano nóng', 'Americano được pha chế bằng cách pha thêm nước với tỷ lệ nhất định vào tách cà phê Espresso, từ đó mang lại hương vị nhẹ nhàng và giữ trọn được mùi hương cà phê đặc trưng.', 1, NULL, 28000);
INSERT INTO `item` VALUES (14, 'Americano đá', 'Americano được pha chế bằng cách pha thêm nước với tỷ lệ nhất định vào tách cà phê Espresso, từ đó mang lại hương vị nhẹ nhàng và giữ trọn được mùi hương cà phê đặc trưng.', 1, NULL, 28000);
INSERT INTO `item` VALUES (15, 'Latte nóng', 'Một sự kết hợp tinh tế giữa vị đắng cà phê Espresso nguyên chất hòa quyện cùng vị sữa nóng ngọt ngào, bên trên là một lớp kem mỏng nhẹ tạo nên một tách cà phê hoàn hảo về hương vị lẫn nhãn quan.', 1, NULL, 34000);
INSERT INTO `item` VALUES (16, 'Latte đá', 'Một sự kết hợp tinh tế giữa vị đắng cà phê Espresso nguyên chất hòa quyện cùng vị sữa nóng ngọt ngào, bên trên là một lớp kem mỏng nhẹ tạo nên một tách cà phê hoàn hảo về hương vị lẫn nhãn quan.', 1, NULL, 34000);
INSERT INTO `item` VALUES (17, 'Nước ép cam', 'Nước ép cam thơm ngon bổ dưỡng', 2, NULL, 20000);
INSERT INTO `item` VALUES (18, 'Nước ép dâu', 'Nước ép dâu thơm ngon bổ dưỡng', 2, NULL, 20000);
INSERT INTO `item` VALUES (19, 'Nước ép táo', 'Nước ép táo thơm ngon bổ dưỡng', 2, NULL, 20000);
INSERT INTO `item` VALUES (20, 'Nước ép xoài', 'Nước ép xoài thơm ngon bổ dưỡng', 2, NULL, 20000);
INSERT INTO `item` VALUES (21, 'Nước ép nho', 'Nước ép nho thơm ngon bổ dưỡng', 2, NULL, 20000);
INSERT INTO `item` VALUES (22, 'Nước ép kiwi', 'Nước ép kiwi thơm ngon bổ dưỡng', 2, NULL, 20000);
INSERT INTO `item` VALUES (23, 'Nước ép bưởi', 'Nước ép bưởi thơm ngon bổ dưỡng', 2, NULL, 20000);
INSERT INTO `item` VALUES (24, 'Nước ép mãng cầu', 'Nước ép mãng cầu thơm ngon bổ dưỡng', 2, NULL, 20000);
INSERT INTO `item` VALUES (25, 'Trà long nhãn hạt chia đá', 'Vị nhãn ngọt, tươi mát đặc trưng hoà quyện tinh tế cùng vị trà oolong hảo hạng và hạt chia mang đến cho bạn một thức uống không chỉ thơm ngon mà còn bổ dưỡng.', 3, NULL, 32000);
INSERT INTO `item` VALUES (26, 'Trà long nhãn hạt chia nóng', 'Vị nhãn ngọt, tươi mát đặc trưng hoà quyện tinh tế cùng vị trà oolong hảo hạng và hạt chia mang đến cho bạn một thức uống không chỉ thơm ngon mà còn bổ dưỡng.', 3, NULL, 36000);
INSERT INTO `item` VALUES (27, 'Trà hạt sen đá', 'Nền trà oolong hảo hạng kết hợp cùng hạt sen tươi, bùi bùi và lớp foam cheese béo ngậy. Trà hạt sen là thức uống thanh mát, nhẹ nhàng phù hợp cho cả buổi sáng và chiều tối.', 3, NULL, 32000);
INSERT INTO `item` VALUES (28, 'Trà hạt sen nóng', 'Nền trà oolong hảo hạng kết hợp cùng hạt sen tươi, bùi bùi và lớp foam cheese béo ngậy. Trà hạt sen là thức uống thanh mát, nhẹ nhàng phù hợp cho cả buổi sáng và chiều tối.', 3, NULL, 36000);
INSERT INTO `item` VALUES (29, 'Trà đào cam sả đá', 'Vị thanh ngọt của đào Hy Lạp, vị chua dịu của Cam Vàng nguyên vỏ, vị chát của trà đen tươi được ủ mới mỗi 4 tiếng, cùng hương thơm nồng đặc trưng của sả chính là điểm sáng làm nên sức hấp dẫn của thức uống này.', 3, NULL, 32000);
INSERT INTO `item` VALUES (30, 'Trà đào cam sả nóng', 'Vị thanh ngọt của đào Hy Lạp, vị chua dịu của Cam Vàng nguyên vỏ, vị chát của trà đen tươi được ủ mới mỗi 4 tiếng, cùng hương thơm nồng đặc trưng của sả chính là điểm sáng làm nên sức hấp dẫn của thức uống này.', 3, NULL, 36000);
INSERT INTO `item` VALUES (31, 'Matcha Đậu Đỏ', 'Matcha Đậu Đỏ thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (32, 'Sữa tươi trân châu baby kem cà phê', 'Sữa tươi trân châu baby kem cà phê thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (33, 'Oolong trân châu baby kem cà phê', 'Oolong trân châu baby kem cà phê thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (34, 'Trà xanh sữa vị nhài', 'Trà xanh sữa vị nhài thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (35, 'Trà sữa matcha', 'Trà sữa matcha thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (36, 'Trà sữa oolong', 'Trà sữa oolong thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (37, 'Trà sữa khoai môn hoàng kim', 'Trà sữa khoai môn hoàng kim thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (38, 'Trà sữa Socola', 'Trà sữa Socola thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (39, 'Trà sữa bạc hà', 'Trà sữa bạc hà thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (40, 'Trà sữa dâu tây', 'Trà sữa dâu tây thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (41, 'Trà sữa trân châu', 'Trà sữa trân châu thơm ngon.', 4, NULL, 29000);
INSERT INTO `item` VALUES (42, 'Coca cola', 'Nước ngọt', 5, NULL, 10000);
INSERT INTO `item` VALUES (43, 'Sprite', 'Nước ngọt', 5, NULL, 10000);
INSERT INTO `item` VALUES (44, 'Mirinda cam', 'Nước ngọt', 5, NULL, 10000);
INSERT INTO `item` VALUES (45, 'Sting dâu', 'Nước ngọt', 5, NULL, 10000);
INSERT INTO `item` VALUES (46, 'Red bull', 'Nước ngọt', 5, NULL, 12000);
INSERT INTO `item` VALUES (47, 'Mochi Kem Chocolate', 'Bao bọc bởi lớp vỏ Mochi dẻo thơm, bên trong là lớp kem lạnh cùng nhân chocolate độc đáo.', 6, NULL, 19000);
INSERT INTO `item` VALUES (48, 'Mochi Kem Dừa Dứa', 'Bao bọc bởi lớp vỏ Mochi dẻo thơm, bên trong là lớp kem lạnh cùng nhân dừa dứa thơm lừng lạ miệng.', 6, NULL, 19000);
INSERT INTO `item` VALUES (49, 'Mochi Kem Matcha', 'Bao bọc bởi lớp vỏ Mochi dẻo thơm, bên trong là lớp kem lạnh cùng nhân trà xanh đậm vị.', 6, NULL, 19000);
INSERT INTO `item` VALUES (50, 'Mochi Kem Phúc Bồn Tử', 'Bao bọc bởi lớp vỏ Mochi dẻo thơm, bên trong là lớp kem lạnh cùng nhân phúc bồn tử ngọt ngào.', 6, NULL, 19000);
INSERT INTO `item` VALUES (51, 'Mochi Kem Việt Quất', 'Bao bọc bởi lớp vỏ Mochi dẻo thơm, bên trong là lớp kem lạnh cùng nhân việt quất đặc trưng thơm thơm, ngọt dịu.', 6, NULL, 19000);
INSERT INTO `item` VALUES (52, 'Mochi Kem Xoài', 'Bao bọc bởi lớp vỏ Mochi dẻo thơm, bên trong là lớp kem lạnh cùng nhân xoài chua chua ngọt ngọt.', 6, NULL, 19000);
INSERT INTO `item` VALUES (53, 'Mousse Gấu Chocolate', 'Với vẻ ngoài đáng yêu và hương vị ngọt ngào, thơm béo nhất định bạn phải thử ít nhất 1 lần.', 6, NULL, 39000);
INSERT INTO `item` VALUES (54, 'Mousse Passion Cheese', 'Tận hưởng chiếc bánh mát lạnh với sự kết hợp hoàn hảo của vị béo ngậy của nhân kem phô mai, cân bằng với vị chua thanh từ chanh dây.', 6, NULL, 29000);
INSERT INTO `item` VALUES (55, 'Mousse Red Velvet', 'Bánh nhiều lớp được phủ lớp kem bên trên bằng Cream cheese.', 6, NULL, 29000);
INSERT INTO `item` VALUES (56, 'Mousse Tiramisu', 'Hương vị dễ ghiền được tạo nên bởi chút đắng nhẹ của cà phê, lớp kem trứng béo ngọt dịu hấp dẫn.', 6, NULL, 32000);
INSERT INTO `item` VALUES (57, 'Bánh mì que Pate', 'Vỏ bánh mì giòn tan, kết hợp với lớp nhân pate béo béo đậm đà sẽ là lựa chọn lý tưởng nhẹ nhàng để lấp đầy chiếc bụng đói , cho 1 bữa sáng - trưa - chiều - tối của bạn thêm phần thú vị.', 7, NULL, 12000);
INSERT INTO `item` VALUES (58, 'Bánh mì que Pate Cay', 'Vỏ bánh mì giòn tan, kết hợp với lớp nhân pate béo béo đậm đà và 1 chút cay cay sẽ là lựa chọn lý tưởng nhẹ nhàng để lấp đầy chiếc bụng đói , cho 1 bữa sáng - trưa - chiều - tối của bạn thêm phần thú vị.', 7, NULL, 12000);
INSERT INTO `item` VALUES (59, 'Bánh mì Thịt nguội', 'Gói gọn trong ổ bánh mì Việt Nam, là từng lớp chả, từng lớp jambon hòa quyện cùng bơ và pate thơm lừng, thêm dưa rau cho bữa sáng đầy năng lượng.', 7, NULL, 29000);
INSERT INTO `item` VALUES (60, 'Chà bông phô mai', 'Chiếc bánh với lớp phô mai vàng sánh mịn bên trong, được bọc ngoài lớp vỏ xốp mềm thơm lừng. Thêm lớp chà bông mằn mặn hấp dẫn bên trên.', 7, NULL, 32000);
INSERT INTO `item` VALUES (61, 'Croissant Trứng Muối', 'Croissant trứng muối thơm lừng, bên ngoài vỏ bánh giòn hấp dẫn bên trong trứng muối vị ngon khó cưỡng.', 7, NULL, 35000);

-- ----------------------------
-- Table structure for itempromotion
-- ----------------------------
DROP TABLE IF EXISTS `itempromotion`;
CREATE TABLE `itempromotion`  (
  `itemPromotion_id` int NOT NULL AUTO_INCREMENT,
  `promotion_id` int NOT NULL,
  `item_id` int NOT NULL,
  `discount` float NULL DEFAULT NULL,
  PRIMARY KEY (`itemPromotion_id`, `promotion_id`, `item_id`) USING BTREE,
  INDEX `fk_ itemPromotion_item_idx`(`item_id`) USING BTREE,
  INDEX `fk_ itemPromotion_promotion_idx`(`promotion_id`) USING BTREE,
  CONSTRAINT `fk_ itemPromotion_item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ itemPromotion_promotion` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`promotion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of itempromotion
-- ----------------------------

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion`  (
  `promotion_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `startDate` date NULL DEFAULT NULL,
  `endDate` date NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`promotion_id`) USING BTREE,
  UNIQUE INDEX `name_UNIQUE`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of promotion
-- ----------------------------

-- ----------------------------
-- Table structure for table
-- ----------------------------
DROP TABLE IF EXISTS `table`;
CREATE TABLE `table`  (
  `table_id` int NOT NULL AUTO_INCREMENT,
  `area_id` int NULL DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bill_id` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`table_id`) USING BTREE,
  INDEX `fk_table_bill_idx`(`bill_id`) USING BTREE,
  INDEX `fk_table_area_idx`(`area_id`) USING BTREE,
  CONSTRAINT `fk_table_area` FOREIGN KEY (`area_id`) REFERENCES `area` (`area_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_table_bill` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`bill_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of table
-- ----------------------------
INSERT INTO `table` VALUES (1, 1, 'Bàn 1', NULL, 1);
INSERT INTO `table` VALUES (2, 1, 'Bàn 2', NULL, 1);
INSERT INTO `table` VALUES (3, 1, 'Bàn 3', NULL, 1);
INSERT INTO `table` VALUES (4, 1, 'Bàn 4', NULL, 1);
INSERT INTO `table` VALUES (5, 1, 'Bàn 5', NULL, 1);
INSERT INTO `table` VALUES (6, 1, 'Bàn 6', NULL, 1);
INSERT INTO `table` VALUES (7, 1, 'Bàn 7', NULL, 1);
INSERT INTO `table` VALUES (8, 1, 'Bàn 8', NULL, 1);
INSERT INTO `table` VALUES (9, 1, 'Bàn 9', NULL, 1);
INSERT INTO `table` VALUES (10, 1, 'Bàn 10', NULL, 1);
INSERT INTO `table` VALUES (11, 2, 'Bàn 1', NULL, 1);
INSERT INTO `table` VALUES (12, 2, 'Bàn 2', NULL, 1);
INSERT INTO `table` VALUES (13, 2, 'Bàn 3', NULL, 1);
INSERT INTO `table` VALUES (14, 2, 'Bàn 4', NULL, 1);
INSERT INTO `table` VALUES (15, 2, 'Bàn 5', NULL, 1);
INSERT INTO `table` VALUES (16, 2, 'Bàn 6', NULL, 1);
INSERT INTO `table` VALUES (17, 2, 'Bàn 7', NULL, 1);
INSERT INTO `table` VALUES (18, 2, 'Bàn 8', NULL, 1);
INSERT INTO `table` VALUES (19, 3, 'Bàn 1', NULL, 1);
INSERT INTO `table` VALUES (20, 3, 'Bàn 2', NULL, 1);
INSERT INTO `table` VALUES (21, 3, 'Bàn 3', NULL, 1);
INSERT INTO `table` VALUES (22, 3, 'Bàn 4', NULL, 1);
INSERT INTO `table` VALUES (23, 3, 'Bàn 5', NULL, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `email_UNIQUE`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '', '2022-04-15', 'admin', '', '');
INSERT INTO `user` VALUES (2, 'Đỗ Minh Nhật', '2001-11-04', '19127494', 'TP.HCM', '123456789');
INSERT INTO `user` VALUES (3, 'Trần Hoàng Thái', '2001-09-11', '19127546', 'TP.HCM', '1357902468');
INSERT INTO `user` VALUES (4, 'Nguyễn Thái Sơn', '2001-03-09', '19127541', 'TP.HCM', '987654321');

SET FOREIGN_KEY_CHECKS = 1;

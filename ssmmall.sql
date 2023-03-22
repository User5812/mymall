/*
 Navicat Premium Data Transfer

 Source Server         : TU
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : ssmmall

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 20/02/2023 08:22:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `link_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人',
  `link_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系地址',
  `link_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '收货地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '哈喽', '北京市前门大街120号', '13909768928', 21);
INSERT INTO `address` VALUES (3, 'tu', '文韬', '17816851454', 24);
INSERT INTO `address` VALUES (4, '哈哈哈', '南京', '13988776655', 21);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updatetime` datetime(0) NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_key`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '13988997788', NULL, '2022-10-08 22:21:26', '2023-02-16 17:39:46', 'ead08e95e9c867a2ee833c232bf55dbc', 1);
INSERT INTO `admin` VALUES (2, 'admin1', '13877889900', 'admin1@qq.com', '2022-10-10 21:28:42', '2022-10-11 20:39:41', 'a7dbef0f88b54fad3f91d010a30eff55', 1);
INSERT INTO `admin` VALUES (3, 'tu', '17816851454', '2635030465@qq.com', '2023-02-10 22:26:40', NULL, 'c0f7e7f02bd12dd3b105870c8ac2d745', 1);

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片地址',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '轮播图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, 'http://localhost:9090/api/files/1622091454711', 'http://www.taobao.com');
INSERT INTO `banner` VALUES (2, 'http://localhost:9090/api/files/1622091460427', 'http://www.jd.com');
INSERT INTO `banner` VALUES (3, 'http://localhost:9090/api/files/1622091465428', 'http://www.baidu.com');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `categoryname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '图书', '1001');
INSERT INTO `category` VALUES (2, '物联网设备', '1002');
INSERT INTO `category` VALUES (4, '手机', '1005');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品描述',
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品编号',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '原价',
  `discount` double(10, 2) NULL DEFAULT NULL COMMENT '折扣',
  `store` int(11) NULL DEFAULT NULL COMMENT '库存',
  `praise` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `sales` int(11) NULL DEFAULT 0 COMMENT '销量',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `imgs` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品图片',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `recommend` tinyint(1) NULL DEFAULT NULL COMMENT '是否推荐：0不推荐，1推荐',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态：1启用 0禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '希望之线（东野圭吾新代表作，高分作品TOP5！）', '《白夜行》《嫌疑人X的献身》《解忧杂货店》《恶意》后，豆瓣东野圭吾第五高分新作！好看到爆炸的人情推理长篇。上市首周登顶日本畅销书总榜，周刊文春推理榜年度推荐。', 'g1001', 35.00, 0.90, 998, 3, 2, 1, 'http://localhost:9090/api/files/1622098971802', '2023-02-18 17:23:50', 1, 1);
INSERT INTO `goods` VALUES (2, 'Apple 苹果 iPhone 11 全网通手机', 'iPhone 11 全网通手机', 'g1002', 4569.00, 0.95, 97, 0, 3, 4, 'http://localhost:9090/api/files/1622091526177', '2023-02-18 20:22:33', 1, 1);
INSERT INTO `goods` VALUES (3, '独立思考：谁在影响谁', '独立思考是获取自由与成功的*重要能力之一，是终身学习的践行核心，本系列书籍已经影响10,000,000人投入学习、阅读和践行', 'g1002', 49.00, 0.50, 97, 0, 3, 1, 'http://localhost:9090/api/files/1622092102515', '2023-02-18 17:24:23', 1, 1);
INSERT INTO `goods` VALUES (4, '文城（余华新书，时隔8年重磅归来，《活着》之后又一精彩力作！）', '余华时隔8年重磅新作！写《活着》的余华又回来了！人生就是自己的往事和他人的序章。关于一个人和他一生的寻找，以及一群人和一个汹涌的年代。精彩过瘾，不负等待！易烊千玺挚爱作家。限量赠送余华珍藏生肖漫画！', 'g1004', 39.50, 0.90, 995, 0, 5, 1, 'http://localhost:9090/api/files/1622092112546', '2023-02-18 17:25:11', 1, 1);
INSERT INTO `goods` VALUES (5, '艺术是什么：极简西方美术史', 'B站27万人喜欢上的西方美术史课', 'g1006', 79.00, 0.47, 998, 0, 2, 1, 'http://localhost:9090/api/files/1622092120531', '2023-02-18 17:25:19', 1, 1);
INSERT INTO `goods` VALUES (6, '艺术是什么：极简西方美术史', 'B站27万人喜欢上的西方美术史课', 'g1006', 80.00, 0.47, 998, 0, 2, 1, 'http://localhost:9090/api/files/1622092120531', '2023-02-18 17:25:25', 1, 1);
INSERT INTO `goods` VALUES (7, '希望之线（东野圭吾新代表作，高分作品TOP5！）', '《白夜行》《嫌疑人X的献身》《解忧杂货店》《恶意》后，豆瓣东野圭吾第五高分新作！好看到爆炸的人情推理长篇。上市首周登顶日本畅销书总榜，周刊文春推理榜年度推荐。', 'g1001', 35.00, 0.90, 998, 3, 2, 1, 'http://localhost:9090/api/files/1622098971802', '2023-02-18 17:25:35', 1, 1);
INSERT INTO `goods` VALUES (8, 'Vivo X90Pro+ 全网通', 'Vivo X90Pro+ 全网通', 'g1003', 6599.00, 0.95, 5, 0, 3, 4, 'http://localhost:9090/api/files/2eff9e794da53f050b0ce712ed45345', '2023-02-18 20:25:42', 1, 1);
INSERT INTO `goods` VALUES (16, 'NB物联网智能开关模块手机远程控制器改装抽水泵路灯定制共享设备', '本产品即买即用，配套免费的微信小程序同时兼容安卓和Ios设备', '123456', 95.00, 0.80, 8, 0, 0, 2, 'http://localhost:9090/api/files/1676718458005', '2023-02-18 19:07:39', 1, 1);
INSERT INTO `goods` VALUES (17, 'ESP32开发板', '裸板', '1231564', 20.00, 0.95, 886, 0, 0, 2, 'http://localhost:9090/api/files/1676718507779', '2023-02-18 19:08:28', 1, 1);
INSERT INTO `goods` VALUES (18, '适用于arduino uno r3入门学习套件 scratch物联网创客编程开发板', '不要钱关注嘉然免费送', '5555', 498.00, 0.10, 1, 0, 0, 2, 'http://localhost:9090/api/files/1676718575821', '2023-02-18 19:09:36', 1, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `order_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '下单人id',
  `link_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `link_address` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '送货地址',
  `state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '待付款' COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '20210602115530120326', 175.00, 21, '张三', '13909768928', '北京市前门大街120号', '已发货', '2021-06-02 11:55:30');
INSERT INTO `orders` VALUES (4, '20210606113132315324', 22845.00, 21, '张三', '13909768928', '北京市前门大街120号', '已发货', '2021-06-06 11:31:32');
INSERT INTO `orders` VALUES (5, '20210606142208712260', 31.50, 21, '张三', '13909768928', '北京市前门大街120号', '待发货', '2021-06-06 14:22:08');
INSERT INTO `orders` VALUES (6, '20210606142338521670', 4569.00, 21, '张三', '13909768928', '北京市前门大街120号', '已发货', '2021-06-06 14:23:38');
INSERT INTO `orders` VALUES (7, '20210606223033775406', 217.40, 21, '张三', '13909768928', '北京市前门大街120号', '已发货', '2021-06-06 22:30:33');
INSERT INTO `orders` VALUES (8, '20220121225225592406', 9222.55, 21, '张三', '13909768928', '北京市前门大街120号', '已发货', '2022-01-21 22:52:25');
INSERT INTO `orders` VALUES (9, '20220216210337905723', 31.50, 22, '夹克', '13099887799', '合肥', '已发货', '2022-02-16 21:03:37');
INSERT INTO `orders` VALUES (10, '20220216210447949603', 25.50, 21, '哈哈哈', '13988776655', '南京', '待发货', '2022-02-16 21:04:47');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `account` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '账户余额',
  `identify` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`, `nick_name`, `email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (11, 'tom', '123456', '汤姆', 'tom@qq.com', '13685249632', '上海', 20, 0.00, '0');
INSERT INTO `user` VALUES (21, 'hello', '123456', '哈喽1', 'hello@qq.com', '13695285413', '北京', 25, 112500.00, '0');
INSERT INTO `user` VALUES (23, 'jerry', 'c0f7e7f02bd12dd3b105870c8ac2d745', '杰瑞', 'jerry@163.com', '13696965656', '北京', 21, 0.00, '0');
INSERT INTO `user` VALUES (24, 'tu', 'c0f7e7f02bd12dd3b105870c8ac2d745', '文韬', '2635030465@qq.com', '17816851454', '杭州', 22, 0.00, '1');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.199.114
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 192.168.199.114:3306
 Source Schema         : xms

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 27/07/2023 17:39:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '货名',
  `storage` int(0) NOT NULL COMMENT '仓库',
  `goodsType` int(0) NOT NULL COMMENT '分类',
  `count` int(0) NULL DEFAULT NULL COMMENT '数量',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (6, '篮球', 1, 4, 30, '测试\n');
INSERT INTO `goods` VALUES (7, '足球', 1, 4, 40, NULL);
INSERT INTO `goods` VALUES (8, '乒乓球', 1, 4, 8, NULL);
INSERT INTO `goods` VALUES (9, '电脑', 3, 2, 23, '');
INSERT INTO `goods` VALUES (10, '手机', 3, 2, 23, '');
INSERT INTO `goods` VALUES (11, '面包', 4, 1, 23, '');
INSERT INTO `goods` VALUES (12, '面粉', 4, 1, 24, '');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (1, '食品类', NULL);
INSERT INTO `goodstype` VALUES (2, '电子产品类', '');
INSERT INTO `goodstype` VALUES (4, '器材类', NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL,
  `menuCode` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menuName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `menuLevel` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单的父code',
  `menuClick` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点击触发的函数',
  `menuRight` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `menuComponent` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menuIcon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '001', '管理员管理', '1', NULL, 'Admin', '0', 'admin/AdminGo.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (2, '002', '用户管理', '1', NULL, 'User', '0,1', 'user/UserGo.vue', 'el-icon-user-solid');
INSERT INTO `menu` VALUES (3, '003', '仓库管理', '1', NULL, 'Storage', '0,1', 'storage/StorageGo.vue', 'el-icon-office-building');
INSERT INTO `menu` VALUES (4, '004', '物品分类管理', '1', NULL, 'Goodstype', '0,1', 'goodstype/GoodstypeGo.vue', 'el-icon-menu');
INSERT INTO `menu` VALUES (5, '005', '物品管理 ', '1', NULL, 'Goods', '0,1,2', 'goods/GoodsGo.vue', 'el-icon-s-management');
INSERT INTO `menu` VALUES (6, '006', '记录管理', '1', NULL, 'Record', '0,1,2', 'record/RecordGo.vue', 'el-icon-s-order');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods` int(0) NOT NULL COMMENT '货品id',
  `userId` int(0) NULL DEFAULT NULL COMMENT '取货人/补货人',
  `admin_id` int(0) NULL DEFAULT NULL COMMENT '操作人id',
  `count` int(0) NULL DEFAULT NULL COMMENT '数量',
  `createtime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 6, 4, 2, 1, '2023-06-22 17:22:10', NULL);
INSERT INTO `record` VALUES (2, 8, 13, 1, 9, '2023-06-28 21:18:35', '');
INSERT INTO `record` VALUES (3, 6, 4, 1, 20, '2023-07-03 21:53:58', '');
INSERT INTO `record` VALUES (4, 7, 7, 1, 6, '2023-07-03 21:54:01', '');
INSERT INTO `record` VALUES (5, 8, 5, 2, -10, '2023-07-03 21:54:04', '');
INSERT INTO `record` VALUES (6, 8, 5, 2, -12, '2023-07-03 21:54:06', '');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '仓库名',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES (1, '仓库1', '测试');
INSERT INTO `storage` VALUES (3, '仓库2', '测试');
INSERT INTO `storage` VALUES (4, '仓库3', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `age` int(0) NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色 0超级管理员，1管理员，2普通账号',
  `isValid` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Y' COMMENT '是否有效，Y有效，其他无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1231230', 'wzy', '123', 18, 0, '13144565367', 0, 'Y');
INSERT INTO `user` VALUES (2, '1231231', 'zsh', '123', 18, 1, '14141414', 1, 'Y');
INSERT INTO `user` VALUES (3, '1231232', 'zzz2', '123', 20, 0, '32323232', 2, 'Y');
INSERT INTO `user` VALUES (4, '4343434', 'sss2', '123', 21, 1, '242424242', 2, 'Y');
INSERT INTO `user` VALUES (5, '2131231e', 'cswew', '123', 23, 1, '234242', 2, 'Y');
INSERT INTO `user` VALUES (6, '232', 'csew', '123', 21, 2, '353535', 2, 'Y');
INSERT INTO `user` VALUES (7, '242442', 'csews', '123', 23, 2, '3535', 2, 'Y');
INSERT INTO `user` VALUES (8, '52525', 'csdfs', '123', 43, 2, '3535', 2, 'Y');
INSERT INTO `user` VALUES (9, '23231', 'csff', '123', 23, 2, '213242', 2, 'Y');
INSERT INTO `user` VALUES (10, '2424', 'csegge', '123', 43, 2, '2313', 1, 'Y');
INSERT INTO `user` VALUES (11, '25252', 'csge', '123', 32, 2, '21321', 2, 'Y');
INSERT INTO `user` VALUES (13, '12312321', 'ssss', '123123132', 18, 1, '23434234234', 2, 'Y');
INSERT INTO `user` VALUES (14, '143443', 'hgf', '123', 24, 2, '355754', 1, 'Y');
INSERT INTO `user` VALUES (15, '2434234', 'tre', '123', 32, 0, '7544574', 1, 'Y');
INSERT INTO `user` VALUES (16, '3435425', 'yryry', '435454', 15, 1, '543656654', 2, 'Y');
INSERT INTO `user` VALUES (17, '35353', 'fefe', '123', 34, 1, '23241', 1, 'Y');
INSERT INTO `user` VALUES (18, '343434', 'grgr', '123', 34, 0, '15854654325', 1, 'Y');
INSERT INTO `user` VALUES (19, '123123123', 'dwdw', '123', 23, 1, '15656745673', 1, 'Y');
INSERT INTO `user` VALUES (20, '12312312', 'wdf', '123', 21, 1, '15832345345', 1, 'Y');
INSERT INTO `user` VALUES (21, '32424', 'fefs', '123', 23, 1, '15854545434', 2, 'Y');
INSERT INTO `user` VALUES (22, '123213122', 'fgrs', '123', 54, 1, '15843425467', 2, 'Y');
INSERT INTO `user` VALUES (23, '3242141', 'egeg', '123', 23, 1, '15823421342', 2, 'Y');
INSERT INTO `user` VALUES (24, '1231221314', 'wfwdd', '123', 23, 1, '15812321342', 1, 'Y');
INSERT INTO `user` VALUES (25, '2424244', 'fegdd', '123', 23, 1, '15856436782', 2, 'Y');
INSERT INTO `user` VALUES (26, '1214124124', 'wfwd', '123', 34, 1, '15634356212', 2, 'Y');
INSERT INTO `user` VALUES (27, '1232134142', 'wdfdd', '123', 23, 1, '15632467453', 2, 'Y');
INSERT INTO `user` VALUES (28, '12131341', 'wfwfwf', '123', 12, 1, '15674425686', 1, 'Y');
INSERT INTO `user` VALUES (29, '123123234', 'wgw44', '123', 23, 1, '15671234567', 2, 'Y');

SET FOREIGN_KEY_CHECKS = 1;

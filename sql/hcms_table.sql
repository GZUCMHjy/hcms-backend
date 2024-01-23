/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : hcms

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 22/01/2024 23:45:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
                          `admin_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员id,主键',
                          `admin_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员姓名',
                          `admin_gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员性别',
                          `admin_position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员职位',
                          `admin_institution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员工作单位',
                          `warehouse_id` int NOT NULL COMMENT '管理员管理仓库id,外键',
                          `admin_tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员电话',
                          `admin_acct` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员登录账号,一般来说也是手机号',
                          `admin_pwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员登录密码',
                          `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                          `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                          `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                          PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for ann
-- ----------------------------
DROP TABLE IF EXISTS `ann`;
CREATE TABLE `ann`  (
                        `ann_id` int NOT NULL COMMENT '公告的id,主键',
                        `admin_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告的发布者',
                        `admin_id` int NOT NULL COMMENT '管理员的id,外键',
                        `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布内容',
                        `publishdate` datetime NOT NULL COMMENT '发布日期',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                        `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                        PRIMARY KEY (`ann_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ann
-- ----------------------------

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
                          `apply_id` int NOT NULL COMMENT '申请表id,主键',
                          `hctype_list` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品类型申请信息列表(危化品类型id,申请数,核发数)',
                          `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请人姓名',
                          `user_tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请人联系电话',
                          `apply_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请内容',
                          `apply_purpost` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请用途(教学实验)',
                          `resvdate` datetime NOT NULL COMMENT '预约发料日期(精确到天,类似用户向平台预约时间)',
                          `apply_type` int NOT NULL COMMENT '申请类型(1领用 0 调配使用)',
                          `apply_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '批准状态(-1 未通过, 0 批准中  1已同意)',
                          `apply_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审评意见',
                          `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                          `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                          `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                          PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of apply
-- ----------------------------

-- ----------------------------
-- Table structure for hc
-- ----------------------------
DROP TABLE IF EXISTS `hc`;
CREATE TABLE `hc`  (
                       `hc_id` int NOT NULL COMMENT '危化品id,主键',
                       `states` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的物理状态(固体,液体,气体)',
                       `hc_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的名称',
                       `hc_formula` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的分子式',
                       `hc_remnant` int NOT NULL COMMENT '危化品的余量',
                       `price` decimal(10, 2) NOT NULL COMMENT '危化品单价',
                       `profile` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的简介',
                       `producationdate` datetime NOT NULL COMMENT '危化品的生产日期',
                       `cas` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的cas编号',
                       `hctypt_id` int NOT NULL COMMENT '危化品类型id,外键',
                       `shelflife` int NOT NULL COMMENT '危化品的保质期,以月为单位',
                       `pur_id` int NOT NULL COMMENT '采购表id,外键',
                       `borrowed` tinyint NOT NULL COMMENT '危化品是否借用,用以判断是否达到最大存储期限(一年)',
                       `wh_id` int NOT NULL COMMENT '所在仓库id,外键',
                       `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品所处状态,枚举(在存,运输,使用)',
                       `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                       `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                       `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                       PRIMARY KEY (`hc_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hc
-- ----------------------------

-- ----------------------------
-- Table structure for hcib
-- ----------------------------
DROP TABLE IF EXISTS `hcib`;
CREATE TABLE `hcib`  (
                         `hcib_id` int NOT NULL COMMENT '危化品入库记录id 主键',
                         `hc_id` int NOT NULL COMMENT '危化品id,外键',
                         `hctype_id` int NOT NULL COMMENT '危化品类型id,外键',
                         `ib_id` int NOT NULL COMMENT '入库记录id,外键',
                         `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                         `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                         `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                         PRIMARY KEY (`hcib_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hcib
-- ----------------------------

-- ----------------------------
-- Table structure for hcob
-- ----------------------------
DROP TABLE IF EXISTS `hcob`;
CREATE TABLE `hcob`  (
                         `hcob_id` int NOT NULL COMMENT '危化品出库记录id,主键',
                         `hc_id` int NOT NULL COMMENT '危化品id,外键',
                         `hctype_id` int NOT NULL COMMENT '危化品类型id,外键',
                         `ob_id` int NOT NULL COMMENT '出库记录id,外键',
                         `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                         `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                         `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                         PRIMARY KEY (`hcob_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hcob
-- ----------------------------

-- ----------------------------
-- Table structure for hctype
-- ----------------------------
DROP TABLE IF EXISTS `hctype`;
CREATE TABLE `hctype`  (
                           `hctype_id` int NOT NULL COMMENT '危化品类型id,主键',
                           `hc_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品名',
                           `hc_spec` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品规格',
                           `hctype_limit` int NOT NULL COMMENT '危化品限制数量',
                           `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                           `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                           `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                           `hc_unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的单位(ml,g)',
                           PRIMARY KEY (`hctype_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hctype
-- ----------------------------
INSERT INTO `hctype` VALUES (1, '盐酸', '500', 400, '2024-01-22 23:37:55', '2024-01-22 23:37:55', 0, 'ml');
INSERT INTO `hctype` VALUES (2, '硫酸', '500', 300, '2024-01-22 23:39:37', '2024-01-22 23:39:37', 0, 'ml');
INSERT INTO `hctype` VALUES (3, '4-甲氧基苯甲酰氯', '25', 100, '2024-01-22 23:41:47', '2024-01-22 23:41:47', 0, 'g');
INSERT INTO `hctype` VALUES (4, '四氢呋喃(不含稳定剂)，ACS/HPLC级', '4', 50, '2024-01-22 23:42:29', '2024-01-22 23:42:29', 0, 'L');

-- ----------------------------
-- Table structure for ib
-- ----------------------------
DROP TABLE IF EXISTS `ib`;
CREATE TABLE `ib`  (
                       `ib_id` int NOT NULL COMMENT '入库记录id,主键',
                       `whstart_id` int NOT NULL COMMENT '起始仓库id,-1表示无,外键',
                       `whend_id` int NOT NULL COMMENT '目的仓库id,-1表示无,外键',
                       `teacher_id` int NOT NULL COMMENT '指导老师id,外键',
                       `admina_id` int NOT NULL COMMENT '管理员a id,外键',
                       `adminb_id` int NOT NULL COMMENT '管理员b id,外键',
                       `user_id` int NOT NULL COMMENT '使用人id,外键',
                       `hctype_id` int NOT NULL COMMENT '危化品类型id,外键',
                       `ib_time` datetime NOT NULL COMMENT '入库时间',
                       `ib_nums` int NOT NULL COMMENT '入库数量(入了多少瓶,多少个最小单位)',
                       `ib_quantity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入库总量(入了多少总量,500ml*3+200ml)',
                       `wh_remnant` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '仓库余量(剩余多少总量,500ml*3+200ml)',
                       `ib_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入库目的(水仙花实验)',
                       `ib_purpose` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途,枚举类型:教学实验,期末归库,调配使用,采购入库',
                       `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                       `updataTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                       `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                       PRIMARY KEY (`ib_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ib
-- ----------------------------

-- ----------------------------
-- Table structure for lab
-- ----------------------------
DROP TABLE IF EXISTS `lab`;
CREATE TABLE `lab`  (
                        `lab_id` int NOT NULL COMMENT '实验室id,主键',
                        `lab_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实验室名称',
                        `wh_id` int NOT NULL COMMENT '所在仓库id,外键',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                        `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                        PRIMARY KEY (`lab_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab
-- ----------------------------
INSERT INTO `lab` VALUES (1, '药科楼B202', 2, '2024-01-22 23:36:59', '2024-01-22 23:36:59', 0);

-- ----------------------------
-- Table structure for ob
-- ----------------------------
DROP TABLE IF EXISTS `ob`;
CREATE TABLE `ob`  (
                       `ob_id` int NOT NULL COMMENT '出库记录id,主键',
                       `whstart_id` int NOT NULL COMMENT '起始仓库id,-1表示无,外键',
                       `whend_id` int NOT NULL COMMENT '目的仓库id,-1表示无,外键',
                       `teacher_id` int NOT NULL COMMENT '指导老师id,外键',
                       `admina_id` int NOT NULL COMMENT '管理员a id,外键',
                       `adminb_id` int NOT NULL COMMENT '管理员b id,外键',
                       `user_id` int NOT NULL COMMENT '使用人id,外键',
                       `hctype_id` int NOT NULL COMMENT '危化品类型id,外键',
                       `ob_time` datetime NOT NULL COMMENT '出库时间',
                       `ob_nums` int NOT NULL COMMENT '出库数量(出了多少瓶,多少个最小单位)',
                       `ob_quantity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出库总量(出了多少总量,500ml*3+200ml)',
                       `wh_remnant` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '仓库余量(剩余多少总量,500ml*3+200ml)',
                       `ob_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出库目的(水仙花实验)',
                       `ranout` tinyint NOT NULL DEFAULT 0 COMMENT '是否用完,只用与用户从总库领取危化品的查询',
                       `ob_purpose` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途,枚举类型:教学实验,期末归库,调配使用',
                       `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                       `updataTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                       `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                       PRIMARY KEY (`ob_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ob
-- ----------------------------

-- ----------------------------
-- Table structure for pur
-- ----------------------------
DROP TABLE IF EXISTS `pur`;
CREATE TABLE `pur`  (
                        `pur_id` int NOT NULL COMMENT '采购表的id,主键',
                        `user_id` int NOT NULL COMMENT '用户表的id,外键',
                        `hctype_list` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '采购危化品类型列表(危化品类型id,采购数量,采购单价)',
                        `totalprice` decimal(10, 2) NOT NULL COMMENT '采购总价',
                        `file` blob NOT NULL COMMENT '已通过的审批材料',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                        `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                        PRIMARY KEY (`pur_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pur
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id,主键',
                         `user_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
                         `user_gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户性别',
                         `user_position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户职位',
                         `user_institution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户工作单位',
                         `lab_id` int NOT NULL COMMENT '所属实验室id,是外键,但是并没有在数据库设计中体现',
                         `user_tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电话号码',
                         `user_acct` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录账号,一般来说就是手机号',
                         `user_pwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录密码',
                         `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                         `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                         `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                         PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for wh
-- ----------------------------
DROP TABLE IF EXISTS `wh`;
CREATE TABLE `wh`  (
                       `wh_id` int NOT NULL COMMENT '仓库id,主键',
                       `wh_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '仓库名字',
                       `a_id` int NOT NULL COMMENT '管理员A的id,外键',
                       `b_id` int NOT NULL COMMENT '管理员B的id,外键',
                       `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                       `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                       `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
                       PRIMARY KEY (`wh_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wh
-- ----------------------------
INSERT INTO `wh` VALUES (1, 'C536', 1, 2, '2024-01-22 23:34:36', '2024-01-22 23:34:36', 0);
INSERT INTO `wh` VALUES (2, '药科楼B202', 3, 4, '2024-01-22 23:35:17', '2024-01-22 23:35:17', 0);

SET FOREIGN_KEY_CHECKS = 1;

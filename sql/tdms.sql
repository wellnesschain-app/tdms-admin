/*
 Navicat Premium Data Transfer

 Source Server         : td
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 192.169.190.147:3306
 Source Schema         : tdms

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 29/03/2019 09:36:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员昵称',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', 'dbe0a723a988fe995a40d100189f3dad', '超级管理员', '2018-11-21 10:37:52', '1,2,3,4,5,6,7,8,9,10,11,12');
INSERT INTO `t_admin` VALUES (2, 'caiwu', '3f509f0f8508992b90675d74568d6958', '财务管理', '2019-03-15 13:57:41', '3,4,5,6,7,9,10');
INSERT INTO `t_admin` VALUES (5, 'tivon', '3f509f0f8508992b90675d74568d6958', '', '2019-03-16 13:59:24', '4');
INSERT INTO `t_admin` VALUES (6, 'tdcw', '3f509f0f8508992b90675d74568d6958', '财务', '2019-03-20 16:02:00', '3,4,5,6,7,9,10');
INSERT INTO `t_admin` VALUES (7, 'conan', '664c4a1055ebb522b173f4967e4f5ea9', '运营部', '2019-03-25 15:55:10', '1,2,4,7,10,11');

-- ----------------------------
-- Table structure for t_backstage_transfer_records
-- ----------------------------
DROP TABLE IF EXISTS `t_backstage_transfer_records`;
CREATE TABLE `t_backstage_transfer_records`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `balance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '积分',
  `usebalance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'wnct',
  `usdtbalance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'usdt',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '转账时间',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员账号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 261 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_balance
-- ----------------------------
DROP TABLE IF EXISTS `t_balance`;
CREATE TABLE `t_balance`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `balance` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '积分总余额',
  `useBalance` decimal(20, 2) NULL DEFAULT 0.00 COMMENT 'WNCT总余额',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `ustdBalance` decimal(20, 2) NULL DEFAULT 0.00 COMMENT 'ustd 数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique`(`addr`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 903 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner`  (
  `F01` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `F02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `F03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `F04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `F05` int(1) NULL DEFAULT 0 COMMENT '显示状态',
  PRIMARY KEY (`F01`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_bonus
-- ----------------------------
DROP TABLE IF EXISTS `t_bonus`;
CREATE TABLE `t_bonus`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint(255) NULL DEFAULT NULL COMMENT '用户ID',
  `val` decimal(20, 4) NULL DEFAULT NULL COMMENT '数量',
  `type` int(1) NULL DEFAULT 0 COMMENT '0为静态分红 1为动态分红',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_buyrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_buyrecord`;
CREATE TABLE `t_buyrecord`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(255) NULL DEFAULT NULL COMMENT '用户ID',
  `wid` bigint(255) NULL DEFAULT NULL COMMENT '财富ID',
  `val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购买金额(人民币)',
  `pVal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购买金额(PAC)',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '购买时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cancel_order_record
-- ----------------------------
DROP TABLE IF EXISTS `t_cancel_order_record`;
CREATE TABLE `t_cancel_order_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `order_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_exrate
-- ----------------------------
DROP TABLE IF EXISTS `t_exrate`;
CREATE TABLE `t_exrate`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `ustd` double(20, 4) NULL DEFAULT 0.0000 COMMENT '台大币等值',
  `doler` double(20, 4) NULL DEFAULT 0.0000 COMMENT '美元',
  `cny` double(20, 4) NULL DEFAULT 0.0000 COMMENT '人民币等值',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_fee
-- ----------------------------
DROP TABLE IF EXISTS `t_fee`;
CREATE TABLE `t_fee`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `fee` decimal(20, 6) NULL DEFAULT 0.000000 COMMENT '回收总量',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_group_consumption_total
-- ----------------------------
DROP TABLE IF EXISTS `t_group_consumption_total`;
CREATE TABLE `t_group_consumption_total`  (
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `total_amount` decimal(14, 2) NOT NULL COMMENT '所有下属团队的累积消费值',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '下属团队累积消费表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_idcardaudit
-- ----------------------------
DROP TABLE IF EXISTS `t_idcardaudit`;
CREATE TABLE `t_idcardaudit`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(255) NULL DEFAULT NULL COMMENT '用户id',
  `img1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面照',
  `img2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证反面照',
  `img3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手持照',
  `applyTime` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` int(1) NULL DEFAULT 0 COMMENT '审核状态0:待审核 1：通过 2：未通过',
  `rejectMsg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驳回理由',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_idcardimg
-- ----------------------------
DROP TABLE IF EXISTS `t_idcardimg`;
CREATE TABLE `t_idcardimg`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(255) NULL DEFAULT NULL COMMENT '用户id',
  `img1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面',
  `img2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证反面',
  `img3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证手持',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniqueId`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_node_setup
-- ----------------------------
DROP TABLE IF EXISTS `t_node_setup`;
CREATE TABLE `t_node_setup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级',
  `promotion_conditions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '晋级条件',
  `direct_promotion_award` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '直推奖励',
  `development_award` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发展奖励',
  `same_award` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同级奖励',
  `tourism_fund` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '旅游基金',
  `car_purchase_fund` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购车基金',
  `purchase_fund` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购房基金',
  `cash_fund` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现金基金',
  `gradation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '级差',
  `static_calculation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '静态算力',
  `static_return` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收益',
  `frequency` int(255) NULL DEFAULT NULL COMMENT '次数',
  `bought_static_return` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通过购买晋级时的节点收益',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_node_setup
-- ----------------------------
INSERT INTO `t_node_setup` VALUES (1, '体验节点', '100', '8', '5', NULL, NULL, NULL, NULL, NULL, '无', '2.0', '0.1', 50, NULL);
INSERT INTO `t_node_setup` VALUES (2, '高级用户', '500', '9', '5', NULL, NULL, NULL, NULL, NULL, '无', '2.5', '0.5', 50, NULL);
INSERT INTO `t_node_setup` VALUES (3, '初级节点', '1000', '10', '5', NULL, NULL, NULL, NULL, NULL, '有', '3.0', '1', 50, NULL);
INSERT INTO `t_node_setup` VALUES (4, '中级节点', '10000', '15', '5', '2', '1', NULL, NULL, NULL, '有', '3.5', '1', 50, '10');
INSERT INTO `t_node_setup` VALUES (5, '高级节点', '50000', '20', '5', '2', '1', '1', NULL, NULL, '有', '4.0', '1', 50, '50');
INSERT INTO `t_node_setup` VALUES (6, '超级节点', '不能购买，需晋级', '25', '5', '2', '1', '1', '1', NULL, '有', '6.0', '1', 50, NULL);
INSERT INTO `t_node_setup` VALUES (7, '合伙人', '不能购买，需晋级', '30', '5', '2', '1', '1', '1', '2', '有', '6.0', '1', 50, NULL);

-- ----------------------------
-- Table structure for t_notices
-- ----------------------------
DROP TABLE IF EXISTS `t_notices`;
CREATE TABLE `t_notices`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '公告类型',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `count` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '浏览量',
  `status` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_pac_scan
-- ----------------------------
DROP TABLE IF EXISTS `t_pac_scan`;
CREATE TABLE `t_pac_scan`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `blockNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区块高度',
  `fromAddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转出方',
  `toAddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收方',
  `val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易数量',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易时间戳',
  `txHash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易哈希',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `txHash`(`txHash`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_postpac_record
-- ----------------------------
DROP TABLE IF EXISTS `t_postpac_record`;
CREATE TABLE `t_postpac_record`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `fromAddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前账户',
  `toAddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收账户',
  `val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `rejectMsg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '驳回理由',
  `applicationTime` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `isAuthin` int(1) NULL DEFAULT 0 COMMENT '是否实名 0:未实名 1:已实名',
  `status` int(1) NULL DEFAULT 0 COMMENT '审核状态 0:待审核 1:审核通过 2:驳回',
  `fee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手续费',
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际到账',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_procedures
-- ----------------------------
DROP TABLE IF EXISTS `t_procedures`;
CREATE TABLE `t_procedures`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tp_transfer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'TP转账手续费',
  `wnct_transfer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'wnct转账手续费',
  `wnct_withdraw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'wnct提币手续费(%)',
  `usdt_transfer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'usdt转账手续费',
  `usdt_withdraw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'usdt提币手续费(单笔5个)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_procedures
-- ----------------------------
INSERT INTO `t_procedures` VALUES (1, '0', '0', '5', '0', '5');

-- ----------------------------
-- Table structure for t_release
-- ----------------------------
DROP TABLE IF EXISTS `t_release`;
CREATE TABLE `t_release`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `cid` bigint(255) NULL DEFAULT NULL COMMENT '冻结id',
  `uid` bigint(255) NULL DEFAULT NULL COMMENT '用户ID',
  `releases` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '释放金额',
  `val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投入金额',
  `surplus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '剩余金额',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '释放时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_release_record
-- ----------------------------
DROP TABLE IF EXISTS `t_release_record`;
CREATE TABLE `t_release_record`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `purse_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '释放数量',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '释放时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sale_center
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_center`;
CREATE TABLE `t_sale_center`  (
  `id` int(22) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '挂售时间 （创建时间）',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '挂售金额',
  `order_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0上架 1已售 2撤销',
  `sale_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '挂售人钱包地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_static_award_record
-- ----------------------------
DROP TABLE IF EXISTS `t_static_award_record`;
CREATE TABLE `t_static_award_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `node_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称(category为1时)',
  `earn_amount` decimal(14, 2) NOT NULL COMMENT '收益(WNCT)',
  `category` smallint(1) NOT NULL COMMENT '类型(1-我的节点,2-社区节点)',
  `create_time` datetime(0) NOT NULL COMMENT '收益时间',
  `node_id` int(11) NULL DEFAULT NULL COMMENT '节点ID(category为1时)',
  `direct_child_uid` int(11) NULL DEFAULT 0 COMMENT '社区节点UID(仅category=2时)',
  `direct_child_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社区节点账号(仅category=2时)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3822 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '静态收益记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_transaction_records
-- ----------------------------
DROP TABLE IF EXISTS `t_transaction_records`;
CREATE TABLE `t_transaction_records`  (
  `id` int(255) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `status` int(1) NULL DEFAULT NULL COMMENT '0:充值 1:买入 2:卖出 3转入 4转出 5静态收益 6动态收益 7系统奖励 8直推奖励 9发展奖励 10同级奖励 11级差奖励 12 转出tp 13 转入wnct 14 转出wnct 15 动态算力收益 16消费tp 17消费wnct 18转入tp 19 转入usdt 20转出usdt 21消耗USDT 22 收获USDT 卖出TP 23撤销返还TP 24 购买TP 交易大厅',
  `collection_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款地址',
  `send_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送地址',
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易时间 创建时间',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5067 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_transfer_record
-- ----------------------------
DROP TABLE IF EXISTS `t_transfer_record`;
CREATE TABLE `t_transfer_record`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `fromAddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转出地址',
  `toAddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收地址',
  `pVal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等值pac',
  `val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易数量',
  `fee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手续费',
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际到账',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '交易时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户/手机号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `level` int(1) NULL DEFAULT 0 COMMENT '1:普通用户 2:高级用户 3:初级节点',
  `status` int(255) NULL DEFAULT 1 COMMENT '1:正常 2:锁定',
  `bought_level` bit(1) NULL DEFAULT b'0' COMMENT '当前级别是否通过购买获得(0-晋级获得, 1-购买获得)',
  `sq_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二级密码',
  `usdt_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'usdt钱包地址',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique`(`addr`, `code`, `uid`) USING BTREE,
  UNIQUE INDEX `uniqueAccount`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10086934 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_consumption_total
-- ----------------------------
DROP TABLE IF EXISTS `t_user_consumption_total`;
CREATE TABLE `t_user_consumption_total`  (
  `uid` int(11) NOT NULL COMMENT '用户id',
  `total_amount` decimal(12, 2) NOT NULL COMMENT '累积消费(tp)',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户个人累积消费' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint(255) NULL DEFAULT NULL COMMENT '用户id',
  `level` int(11) NULL DEFAULT 1 COMMENT '等级 1:普通会员2:节点3:超级节点4:合伙人',
  `pid` bigint(255) NULL DEFAULT NULL COMMENT '直属上级id',
  `down` bigint(255) NULL DEFAULT 0 COMMENT '伞下人数',
  `achievement` decimal(20, 6) NULL DEFAULT 0.000000 COMMENT '业绩',
  `pids` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '所有上级',
  `nodeCount` int(10) NULL DEFAULT 0 COMMENT '推荐的节点数量',
  `sNodeCount` int(10) NULL DEFAULT 0 COMMENT '推荐的超级节点数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 908 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_node
-- ----------------------------
DROP TABLE IF EXISTS `t_user_node`;
CREATE TABLE `t_user_node`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点名称',
  `calculation` decimal(5, 2) NOT NULL COMMENT '算力',
  `create_time` datetime(0) NOT NULL COMMENT '购买时间',
  `left_day` int(8) NOT NULL COMMENT '剩余时间(天)',
  `static_return` decimal(5, 2) NOT NULL COMMENT '节点权益(份数)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 366 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户节点表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_static_param
-- ----------------------------
DROP TABLE IF EXISTS `t_user_static_param`;
CREATE TABLE `t_user_static_param`  (
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `calculation` decimal(5, 2) NOT NULL COMMENT '算力',
  `static_return` decimal(5, 2) NOT NULL COMMENT '节点权益(配套份数)',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户静态算力收益参数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wallet
-- ----------------------------
DROP TABLE IF EXISTS `t_wallet`;
CREATE TABLE `t_wallet`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `pKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '私钥',
  `tPwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易密码',
  `keystorePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包文件路径',
  `uid` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `qrcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `usdt_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'USDT钱包地址',
  `usdt_qrcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'usdt二维码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `unique`(`addr`, `keystorePath`, `uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 947 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wealth
-- ----------------------------
DROP TABLE IF EXISTS `t_wealth`;
CREATE TABLE `t_wealth`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `plan_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划名称',
  `min_money` decimal(10, 0) NULL DEFAULT NULL COMMENT '最小起投金额',
  `max_money` decimal(10, 0) NULL DEFAULT NULL COMMENT '最大起投金额',
  `interest_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '每天释放利率',
  `instructions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态:0正在进行 1已下架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wealthcold
-- ----------------------------
DROP TABLE IF EXISTS `t_wealthcold`;
CREATE TABLE `t_wealthcold`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(255) NULL DEFAULT NULL COMMENT '用户id',
  `wid` bigint(20) NULL DEFAULT NULL COMMENT '财富id',
  `val` decimal(20, 4) NULL DEFAULT NULL COMMENT '投入金额',
  `rate` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '利率',
  `buyinTime` datetime(0) NULL DEFAULT NULL COMMENT '投入时间',
  `lastTime` datetime(0) NULL DEFAULT NULL COMMENT '最后释放时间',
  `releases` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '释放金额',
  `surplus` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '剩余金额',
  `stat` int(1) NULL DEFAULT 0 COMMENT '状态:0正在收益 1:受益结束',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wnct_scan
-- ----------------------------
DROP TABLE IF EXISTS `t_wnct_scan`;
CREATE TABLE `t_wnct_scan`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `blockNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区块高度',
  `fromAddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转出方',
  `toAddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收方',
  `val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'USTD交易数量',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易时间戳',
  `txHash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易哈希',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `txHash`(`txHash`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 859 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wnct_tp_percentage
-- ----------------------------
DROP TABLE IF EXISTS `t_wnct_tp_percentage`;
CREATE TABLE `t_wnct_tp_percentage`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `wnct_percentage_max` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'wnct百分比最大值',
  `wnct_percentage_min` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'wnct百分比最小值',
  `tp_percentage_max` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tp百分比最大值',
  `tp_percentage_min` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tp百分比最大值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wnct_tp_percentage
-- ----------------------------
INSERT INTO `t_wnct_tp_percentage` VALUES (00000000001, '50', '0', '100', '50');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : kaifaku
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 106.54.127.177:3306
 Source Schema         : claimmain

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 04/03/2021 15:15:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for claim_case_investigate_detail
-- ----------------------------
DROP TABLE IF EXISTS `claim_case_investigate_detail`;
CREATE TABLE `claim_case_investigate_detail`  (
  `investigate_detail_no` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '调查序号',
  `rpt_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报案号',
  `inv_detail_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提调类型',
  `inv_detail_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调查状态',
  `inv_detail_cause` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调查原因',
  `inv_detail_people` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交查人',
  `inv_detail_time` datetime(0) NOT NULL COMMENT '交查时间',
  `inv_detail_item` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调查项目',
  `inv_detail_nature` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调查定性',
  `inv_detail_gist` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调查定性依据',
  `inv_detail_face` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事实与依据',
  `conclusion` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '结论',
  `inv_detail_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证明材料及件数',
  `inv_detail_replenish` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主管补充',
  `inv_detail_survey` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否有异地调查',
  `inv_detail_scan` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否扫描',
  `inv_detail_proof` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '阳性资料证据',
  `inv_detail_after_time` datetime(0) DEFAULT NULL COMMENT '查迄时间',
  `inv_detail_organ` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调查机构',
  `inv_detail_after_people` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调查人',
  `inv_detail_argument` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务退回理由',
  `inv_detail_result_argument` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '结果退回理由',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据状态（Y-有效，N-无效）',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`investigate_detail_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '案件调查明细表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

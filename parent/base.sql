/*
Navicat MySQL Data Transfer

Source Server         : 腾讯
Source Server Version : 50641
Source Host           : 123.207.55.47:3306
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2018-09-22 16:30:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crm_in_storage_work
-- ----------------------------
DROP TABLE IF EXISTS `crm_in_storage_work`;
CREATE TABLE `crm_in_storage_work` (
  `id` varchar(32) NOT NULL,
  `from_order` varchar(32) NOT NULL COMMENT '来源单号',
  `order_type` varchar(20) NOT NULL COMMENT '订单类型',
  `order_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单日期',
  `vendor_id` varchar(32) NOT NULL COMMENT '供应商编码',
  `vendor_name` varchar(32) NOT NULL COMMENT '供应商名称',
  `warehouse_id` varchar(32) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
  `status` int(4) NOT NULL DEFAULT '10' COMMENT '订单状态 10制单20审核30部分入库40完全入库',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `audit_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '审核时间',
  `audit_emp` varchar(32) DEFAULT NULL COMMENT '审核人',
  `audit_emp_id` varchar(32) DEFAULT NULL COMMENT '审核人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `in_storage_work_vendor_id` (`vendor_id`),
  KEY `in_storage_work_warehouse_id` (`warehouse_id`),
  CONSTRAINT `in_storage_work_vendor_id` FOREIGN KEY (`vendor_id`) REFERENCES `eidp_vendor` (`id`),
  CONSTRAINT `in_storage_work_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `eidp_warehouse` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库作业';

-- ----------------------------
-- Records of crm_in_storage_work
-- ----------------------------

-- ----------------------------
-- Table structure for crm_in_storage_work_part
-- ----------------------------
DROP TABLE IF EXISTS `crm_in_storage_work_part`;
CREATE TABLE `crm_in_storage_work_part` (
  `id` varchar(32) NOT NULL,
  `from_order_part` varchar(32) NOT NULL COMMENT '来源明细编号',
  `in_storage_work` varchar(32) NOT NULL COMMENT '入库单号',
  `goods_id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_name` varchar(200) NOT NULL COMMENT '产品名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '单位编码',
  `goods_unit_name` varchar(50) NOT NULL COMMENT '单位名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `plan_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '计划数量',
  `receiving_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '入库数量',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `in_storage_work_part_in_storage_work` (`in_storage_work`),
  KEY `in_storage_work_part_goods_id` (`goods_id`),
  KEY `in_storage_work_part_goods_unit_id` (`goods_unit_id`),
  CONSTRAINT `in_storage_work_part_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `eidp_goods_archive` (`id`),
  CONSTRAINT `in_storage_work_part_goods_unit_id` FOREIGN KEY (`goods_unit_id`) REFERENCES `eidp_goods_unit` (`id`),
  CONSTRAINT `in_storage_work_part_in_storage_work` FOREIGN KEY (`in_storage_work`) REFERENCES `crm_in_storage_work` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库作业明细';

-- ----------------------------
-- Records of crm_in_storage_work_part
-- ----------------------------

-- ----------------------------
-- Table structure for crm_out_storage_work
-- ----------------------------
DROP TABLE IF EXISTS `crm_out_storage_work`;
CREATE TABLE `crm_out_storage_work` (
  `id` varchar(32) NOT NULL,
  `from_order` varchar(32) NOT NULL COMMENT '来源单号',
  `order_type` varchar(20) NOT NULL COMMENT '订单类型',
  `order_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单日期',
  `customer_id` varchar(32) NOT NULL COMMENT '客户编码',
  `customer_name` varchar(32) NOT NULL COMMENT '客户名称',
  `warehouse_id` varchar(32) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
  `status` int(4) NOT NULL DEFAULT '10' COMMENT '订单状态 10制单20审核30部分入库40完全入库',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `audit_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '审核时间',
  `audit_emp` varchar(32) DEFAULT NULL COMMENT '审核人',
  `audit_emp_id` varchar(32) DEFAULT NULL COMMENT '审核人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `out_storage_work_customer_id` (`customer_id`),
  KEY `out_storage_work_warehouse_id` (`warehouse_id`),
  CONSTRAINT `out_storage_work_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `eidp_customer` (`id`),
  CONSTRAINT `out_storage_work_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `eidp_warehouse` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库作业';

-- ----------------------------
-- Records of crm_out_storage_work
-- ----------------------------

-- ----------------------------
-- Table structure for crm_out_storage_work_part
-- ----------------------------
DROP TABLE IF EXISTS `crm_out_storage_work_part`;
CREATE TABLE `crm_out_storage_work_part` (
  `id` varchar(32) NOT NULL,
  `from_order_part` varchar(32) NOT NULL COMMENT '来源明细编号',
  `out_storage_work` varchar(32) NOT NULL COMMENT '入库单号',
  `goods_id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_name` varchar(200) NOT NULL COMMENT '产品名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '单位编码',
  `goods_unit_name` varchar(50) NOT NULL COMMENT '单位名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `plan_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '计划数量',
  `delivery_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '出库数量',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `out_storage_work_part_out_storage_work` (`out_storage_work`),
  KEY `out_storage_work_part_goods_id` (`goods_id`),
  KEY `out_storage_work_part_goods_unit_id` (`goods_unit_id`),
  CONSTRAINT `out_storage_work_part_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `eidp_goods_archive` (`id`),
  CONSTRAINT `out_storage_work_part_goods_unit_id` FOREIGN KEY (`goods_unit_id`) REFERENCES `eidp_goods_unit` (`id`),
  CONSTRAINT `out_storage_work_part_out_storage_work` FOREIGN KEY (`out_storage_work`) REFERENCES `crm_out_storage_work` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库作业明细';

-- ----------------------------
-- Records of crm_out_storage_work_part
-- ----------------------------

-- ----------------------------
-- Table structure for crm_purchase_apply
-- ----------------------------
DROP TABLE IF EXISTS `crm_purchase_apply`;
CREATE TABLE `crm_purchase_apply` (
  `id` varchar(32) NOT NULL,
  `vendor_id` varchar(32) NOT NULL COMMENT '供应商编码',
  `vendor_name` varchar(32) NOT NULL COMMENT '供应商名称',
  `warehouse_id` varchar(32) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
  `status` int(4) NOT NULL DEFAULT '10' COMMENT '订单状态 10制单20审核30部分下推40已下推',
  `apply_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '申请日期',
  `arrival_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '到货日期',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `audit_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '审核时间',
  `audit_emp` varchar(32) DEFAULT NULL COMMENT '审核人',
  `audit_emp_id` varchar(32) DEFAULT NULL COMMENT '审核人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `purchase_apply_vendor_id` (`vendor_id`),
  KEY `purchase_apply_warehouse_id` (`warehouse_id`),
  CONSTRAINT `purchase_apply_vendor_id` FOREIGN KEY (`vendor_id`) REFERENCES `eidp_vendor` (`id`),
  CONSTRAINT `purchase_apply_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `eidp_warehouse` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购申请';

-- ----------------------------
-- Records of crm_purchase_apply
-- ----------------------------

-- ----------------------------
-- Table structure for crm_purchase_apply_part
-- ----------------------------
DROP TABLE IF EXISTS `crm_purchase_apply_part`;
CREATE TABLE `crm_purchase_apply_part` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `purchase_apply_id` varchar(32) NOT NULL COMMENT '采购订单号',
  `goods_id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_name` varchar(200) NOT NULL COMMENT '产品名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '单位编码',
  `goods_unit_name` varchar(50) NOT NULL COMMENT '单位名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `apply_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '申请数量',
  `push_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '下推数量',
  `unit_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '单位价格',
  `unit_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税单价',
  `total_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '总价',
  `total_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税总价',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '税率',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `crm_purchase_apply_part_purchase_apply_id` (`purchase_apply_id`),
  CONSTRAINT `crm_purchase_apply_part_purchase_apply_id` FOREIGN KEY (`purchase_apply_id`) REFERENCES `crm_purchase_apply` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购申请明细';

-- ----------------------------
-- Records of crm_purchase_apply_part
-- ----------------------------

-- ----------------------------
-- Table structure for crm_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `crm_purchase_order`;
CREATE TABLE `crm_purchase_order` (
  `id` varchar(32) NOT NULL COMMENT '采购订单单号',
  `vendor_id` varchar(32) NOT NULL COMMENT '供应商编码',
  `vendor_name` varchar(32) NOT NULL COMMENT '供应商名称',
  `warehouse_id` varchar(32) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
  `status` int(4) NOT NULL DEFAULT '10' COMMENT '订单状态 10制单20审核30部分收货40已收货',
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单日期',
  `arrival_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '到货日期',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `audit_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '审核时间',
  `audit_emp` varchar(32) DEFAULT NULL COMMENT '审核人',
  `audit_emp_id` varchar(32) DEFAULT NULL COMMENT '审核人编号',
  `delete_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  `delete_emp` varchar(32) DEFAULT NULL COMMENT '删除人',
  `delete_emp_id` varchar(32) DEFAULT NULL COMMENT '删除人编号',
  `from_order` varchar(32) DEFAULT NULL COMMENT '来源单号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `purchase_order_vendor_id` (`vendor_id`),
  KEY `purchase_order_warehouse_id` (`warehouse_id`),
  CONSTRAINT `purchase_order_vendor_id` FOREIGN KEY (`vendor_id`) REFERENCES `eidp_vendor` (`id`),
  CONSTRAINT `purchase_order_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `eidp_warehouse` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crm_purchase_order
-- ----------------------------

-- ----------------------------
-- Table structure for crm_purchase_order_part
-- ----------------------------
DROP TABLE IF EXISTS `crm_purchase_order_part`;
CREATE TABLE `crm_purchase_order_part` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `purchase_order_id` varchar(32) NOT NULL COMMENT '采购订单号',
  `goods_id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_name` varchar(200) NOT NULL COMMENT '产品名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '单位编码',
  `goods_unit_name` varchar(50) NOT NULL COMMENT '单位名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `purchase_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '申请数量',
  `receiving_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '收货数量',
  `unit_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '单位价格',
  `unit_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税单价',
  `total_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '总价',
  `total_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税总价',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '税率',
  `from_order_part` varchar(32) DEFAULT NULL COMMENT '来源明细单号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `crm_purchase_order_part_purchase_order_id` (`purchase_order_id`),
  CONSTRAINT `crm_purchase_order_part_purchase_order_id` FOREIGN KEY (`purchase_order_id`) REFERENCES `crm_purchase_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购申请明细';

-- ----------------------------
-- Records of crm_purchase_order_part
-- ----------------------------

-- ----------------------------
-- Table structure for crm_sales_order
-- ----------------------------
DROP TABLE IF EXISTS `crm_sales_order`;
CREATE TABLE `crm_sales_order` (
  `id` varchar(32) NOT NULL COMMENT '销售订单主键',
  `customer_id` varchar(32) NOT NULL COMMENT '客户编码',
  `customer_name` varchar(50) NOT NULL COMMENT '客户名称',
  `status` int(4) NOT NULL DEFAULT '10' COMMENT '订单状态 10制单20审核30部分入库40完全入库',
  `warehouse_id` varchar(32) NOT NULL COMMENT '发货仓库编码',
  `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单日期',
  `delivery_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '申请发货日期',
  `receipt` varchar(50) DEFAULT NULL COMMENT '发票号',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `audit_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '审核时间',
  `audit_emp` varchar(32) DEFAULT NULL COMMENT '审核人',
  `audit_emp_id` varchar(32) DEFAULT NULL COMMENT '审核人编号',
  `delete_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  `delete_emp` varchar(32) DEFAULT NULL COMMENT '删除人',
  `delete_emp_id` varchar(32) DEFAULT NULL COMMENT '删除人编号',
  `address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sales_order_customer_id` (`customer_id`),
  KEY `sales_order_warehouse_id` (`warehouse_id`),
  CONSTRAINT `sales_order_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `eidp_customer` (`id`),
  CONSTRAINT `sales_order_warehouse_id` FOREIGN KEY (`warehouse_id`) REFERENCES `eidp_warehouse` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售订单';

-- ----------------------------
-- Records of crm_sales_order
-- ----------------------------

-- ----------------------------
-- Table structure for crm_sales_order_part
-- ----------------------------
DROP TABLE IF EXISTS `crm_sales_order_part`;
CREATE TABLE `crm_sales_order_part` (
  `id` varchar(32) NOT NULL COMMENT '明细流水',
  `sales_order_id` varchar(32) NOT NULL COMMENT '销售单号',
  `goods_id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_name` varchar(200) NOT NULL COMMENT '产品名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '单位编码',
  `goods_unit_name` varchar(50) NOT NULL COMMENT '单位名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `unit_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '单位价格',
  `unit_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税单价',
  `total_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '总价',
  `total_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税总价',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '税率',
  `sales_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '销售数量',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `delivery_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '出库数量',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sales_order_part_sales_order_id` (`sales_order_id`),
  CONSTRAINT `sales_order_part_sales_order_id` FOREIGN KEY (`sales_order_id`) REFERENCES `crm_sales_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售订单明细';

-- ----------------------------
-- Records of crm_sales_order_part
-- ----------------------------

-- ----------------------------
-- Table structure for crm_stock_begin
-- ----------------------------
DROP TABLE IF EXISTS `crm_stock_begin`;
CREATE TABLE `crm_stock_begin` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `warehouse_id` varchar(32) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
  `goods_id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_name` varchar(200) NOT NULL COMMENT '产品名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '单位编码',
  `goods_unit_name` varchar(50) NOT NULL COMMENT '单位名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `stock_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '期初库存',
  `unit_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '单位价格',
  `unit_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税单价',
  `total_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '总价',
  `total_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税总价',
  `rate_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '税金总额',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `status` tinyint(10) NOT NULL DEFAULT '10' COMMENT '状态10制单20已审核',
  `audit_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '审核时间',
  `audit_emp` varchar(32) DEFAULT NULL COMMENT '审核人',
  `audit_emp_id` varchar(32) DEFAULT NULL COMMENT '审核人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存期初';

-- ----------------------------
-- Records of crm_stock_begin
-- ----------------------------

-- ----------------------------
-- Table structure for crm_stock_part
-- ----------------------------
DROP TABLE IF EXISTS `crm_stock_part`;
CREATE TABLE `crm_stock_part` (
  `id` varchar(32) NOT NULL COMMENT '库存流水',
  `warehouse_id` varchar(32) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
  `goods_id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_name` varchar(200) NOT NULL COMMENT '产品名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '单位编码',
  `goods_unit_name` varchar(50) NOT NULL COMMENT '单位名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `total_stock_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '总库存',
  `stock_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '可用库存',
  `in_stock_amount` decimal(16,4) DEFAULT '0.0000' COMMENT '入库库存',
  `lock_stock_amount` decimal(16,4) DEFAULT '0.0000' COMMENT '锁定库存',
  `unit_cost_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '单位成本',
  `unit_cost_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税单位成本',
  `total_cost_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '总成本',
  `total_cost_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税总成本',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `delete_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  `delete_emp` varchar(32) DEFAULT NULL COMMENT '删除人',
  `delete_emp_id` varchar(32) DEFAULT NULL COMMENT '删除人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存';

-- ----------------------------
-- Records of crm_stock_part
-- ----------------------------

-- ----------------------------
-- Table structure for crm_stock_trade_log
-- ----------------------------
DROP TABLE IF EXISTS `crm_stock_trade_log`;
CREATE TABLE `crm_stock_trade_log` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `trade_type` varchar(20) NOT NULL COMMENT '交易类型 10库存期初20入库30出库',
  `trade_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '库存交易时间',
  `warehouse_id` varchar(32) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
  `goods_id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_name` varchar(200) NOT NULL COMMENT '产品名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '单位编码',
  `goods_unit_name` varchar(50) NOT NULL COMMENT '单位名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `form_order` varchar(32) NOT NULL COMMENT '来源单号',
  `trade_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '交易数量',
  `unit_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '单位价格',
  `unit_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税单价',
  `total_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '总金额',
  `total_price_rate` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '含税总金额',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `delete_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  `delete_emp` varchar(32) DEFAULT NULL COMMENT '删除人',
  `delete_emp_id` varchar(32) DEFAULT NULL COMMENT '删除人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存交易日志';

-- ----------------------------
-- Records of crm_stock_trade_log
-- ----------------------------

-- ----------------------------
-- Table structure for eidp_customer
-- ----------------------------
DROP TABLE IF EXISTS `eidp_customer`;
CREATE TABLE `eidp_customer` (
  `id` varchar(32) NOT NULL COMMENT '客户编码',
  `customer_name` varchar(50) NOT NULL COMMENT '客户名称',
  `customer_type` varchar(20) DEFAULT NULL COMMENT '客户类型 10售后20商场30零售',
  `customer_emp_id` varchar(32) DEFAULT NULL COMMENT '客户负责人编号',
  `customer_emp_name` varchar(50) DEFAULT NULL COMMENT '客户负责人 名称',
  `sales_type` varchar(20) DEFAULT NULL COMMENT '销售方式10直销20委托代销30零售',
  `customer_level` varchar(20) DEFAULT NULL COMMENT '客户级别10VIP客户20重要客户30一般客户',
  `receivable_amount` decimal(16,4) DEFAULT '0.0000' COMMENT '应收金额',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `post_code` varchar(50) DEFAULT NULL COMMENT '邮编',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `contact_fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `contact_email` varchar(50) DEFAULT NULL COMMENT '邮件',
  `legal_person` varchar(50) DEFAULT NULL COMMENT '法人',
  `business_license` varchar(50) DEFAULT NULL COMMENT '营业执照',
  `taxation_code` varchar(50) DEFAULT NULL COMMENT '税务登记号',
  `opening_bank` varchar(100) DEFAULT NULL COMMENT '开户行',
  `opening_bank_account` varchar(50) DEFAULT NULL COMMENT '开户账号',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `version` int(11) unsigned DEFAULT '0' COMMENT '版本号',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户';

-- ----------------------------
-- Records of eidp_customer
-- ----------------------------

-- ----------------------------
-- Table structure for eidp_emp
-- ----------------------------
DROP TABLE IF EXISTS `eidp_emp`;
CREATE TABLE `eidp_emp` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '姓名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `sex` char(2) DEFAULT NULL,
  `department` varchar(32) DEFAULT NULL COMMENT '所属机构',
  `superior` varchar(32) DEFAULT NULL COMMENT '上司',
  `post` varchar(100) DEFAULT NULL COMMENT '职务',
  `title` varchar(100) DEFAULT NULL COMMENT '职称',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `birth` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出生日期',
  `enrollment` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '入职日期',
  `resign` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '离职日期',
  `contract` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '合同到期日期',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_emp` varchar(50) DEFAULT NULL COMMENT '创建人',
  `modifytime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `modify_emp` varchar(50) DEFAULT NULL COMMENT '修改人',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`username`) USING BTREE COMMENT '姓名唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of eidp_emp
-- ----------------------------
INSERT INTO `eidp_emp` VALUES ('0001', '王伟华', '$2a$10$Ygy1RsrwA.7RMRrvd.3vm.g3QpoumFW0HZFdJlhJsJU2qGzEaHKay', '1', '1', null, 'Java', 'Java软件工程师', '18138404092', '979982272@qq.com', '2017-08-13 15:08:53', '2017-08-13 15:08:53', '2017-08-13 15:08:53', '2017-08-13 15:08:53', '2017-08-13 15:08:53', null, null, '2017-08-13 15:08:53', null, null, '0', '0');
INSERT INTO `eidp_emp` VALUES ('0002', '0000', '$2a$10$zXAPSAFnJFq22eGsnnrC..jqXqtZJH5Q7qGmpLaTHqq0XveZbGp2O', '1', '1', '', 'Java', 'Java软件工程师', '18138404092', '979982272@qq.com', '2018-05-26 22:25:31', '2018-05-26 22:25:31', '2018-05-26 22:25:31', '2018-05-26 22:25:31', '2018-05-26 22:25:31', '', '', '2018-05-26 22:25:31', '', '', '0', '0');

-- ----------------------------
-- Table structure for eidp_emp_role
-- ----------------------------
DROP TABLE IF EXISTS `eidp_emp_role`;
CREATE TABLE `eidp_emp_role` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '用户编码',
  `emp_id` varchar(32) NOT NULL COMMENT '用户编码',
  `role_id` int(4) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eidp_emp_role
-- ----------------------------

-- ----------------------------
-- Table structure for eidp_goods_archive
-- ----------------------------
DROP TABLE IF EXISTS `eidp_goods_archive`;
CREATE TABLE `eidp_goods_archive` (
  `id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_type_id` varchar(32) NOT NULL COMMENT '产品类型',
  `goods_type_name` varchar(50) DEFAULT NULL COMMENT '产品类型名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '产品单位',
  `goods_unit_name` varchar(50) DEFAULT NULL COMMENT '产品单位名称',
  `goods_brand_id` varchar(32) NOT NULL COMMENT '品牌',
  `goods_brand_name` varchar(50) DEFAULT NULL COMMENT '产品品牌名称',
  `goods_name` varchar(200) DEFAULT NULL COMMENT '产品名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `goods_barcode` varchar(50) DEFAULT NULL COMMENT '条形码',
  `sap_code` varchar(50) DEFAULT NULL COMMENT 'sap物料编码',
  `normal_price` decimal(16,4) DEFAULT '0.0000' COMMENT '标准价格',
  `rate` decimal(16,4) DEFAULT '0.0000' COMMENT '税率',
  `purchase_price` decimal(16,4) DEFAULT '0.0000' COMMENT '采购参考价',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `delete_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  `delete_emp` varchar(32) DEFAULT NULL COMMENT '删除人',
  `delete_emp_id` varchar(32) DEFAULT NULL COMMENT '删除人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `goods_name` (`goods_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品档案';

-- ----------------------------
-- Records of eidp_goods_archive
-- ----------------------------

-- ----------------------------
-- Table structure for eidp_goods_brand
-- ----------------------------
DROP TABLE IF EXISTS `eidp_goods_brand`;
CREATE TABLE `eidp_goods_brand` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `chn_name` varchar(32) NOT NULL COMMENT '中文名称',
  `eng_name` varchar(32) DEFAULT NULL COMMENT '英文名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌';

-- ----------------------------
-- Records of eidp_goods_brand
-- ----------------------------
INSERT INTO `eidp_goods_brand` VALUES ('22306c99f0154c38a98038813b8f42b3', 'sdf', 'sdf', 'sdf', '0', '0');

-- ----------------------------
-- Table structure for eidp_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `eidp_goods_type`;
CREATE TABLE `eidp_goods_type` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级主键',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_emp` varchar(50) DEFAULT NULL COMMENT '创建人',
  `modifytime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `modify_emp` varchar(50) DEFAULT NULL COMMENT '修改人',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品类型';

-- ----------------------------
-- Records of eidp_goods_type
-- ----------------------------
INSERT INTO `eidp_goods_type` VALUES ('dd', 'dd', null, null, 's', '2018-07-05 16:28:31', null, null, null, null, null, '0', '0');
INSERT INTO `eidp_goods_type` VALUES ('df', 'df', null, null, null, '2018-07-05 16:39:17', null, null, null, null, null, '0', '0');
INSERT INTO `eidp_goods_type` VALUES ('q', 'q', null, null, null, '2018-07-05 16:28:00', null, null, null, null, null, '0', '0');
INSERT INTO `eidp_goods_type` VALUES ('s', 's', null, null, 'q', '2018-07-05 16:28:07', null, null, null, null, null, '0', '0');

-- ----------------------------
-- Table structure for eidp_goods_unit
-- ----------------------------
DROP TABLE IF EXISTS `eidp_goods_unit`;
CREATE TABLE `eidp_goods_unit` (
  `id` varchar(32) NOT NULL COMMENT '单位编号',
  `unit_name` varchar(50) NOT NULL COMMENT '单位名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unit_name` (`unit_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品单位';

-- ----------------------------
-- Records of eidp_goods_unit
-- ----------------------------
INSERT INTO `eidp_goods_unit` VALUES ('s', 's', 's', '2018-07-09 21:27:13', null, null, null, null, null, '0', '0');

-- ----------------------------
-- Table structure for eidp_menu
-- ----------------------------
DROP TABLE IF EXISTS `eidp_menu`;
CREATE TABLE `eidp_menu` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `level` int(4) DEFAULT NULL COMMENT '等级',
  `parent_id` int(4) DEFAULT NULL COMMENT '父级编码',
  `url` varchar(100) DEFAULT NULL COMMENT '地址',
  `text` varchar(100) DEFAULT NULL COMMENT '文本',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eidp_menu
-- ----------------------------
INSERT INTO `eidp_menu` VALUES ('1', '1', null, null, '系统管理', 'glyphicon glyphicon-cog', null, '0');
INSERT INTO `eidp_menu` VALUES ('2', '2', '1', null, '开发平台', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('3', '2', '1', null, '主数据', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('4', '2', '1', null, '来往单位', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('5', '2', '1', null, '资源', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('6', '3', '2', '/base/development', '程序制作', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('7', '3', '2', '/base/emp', '用户管理', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('8', '3', '3', '/base/goods', '产品档案', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('9', '1', null, null, '进销存', 'fa fa-shopping-bag', null, '0');
INSERT INTO `eidp_menu` VALUES ('10', '2', '9', null, '库存', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('11', '3', '10', '/crm/stock/stockBegin', '库存期初', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('12', '3', '3', '/base/goodsType', '产品类型', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('13', '3', '3', '/base/goodsBrand', '品牌', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('14', '3', '3', '/base/goodsUnit', '单位', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('15', '3', '4', '/base/customer', '客户', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('16', '3', '4', '/base/vendor', '供应商', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('17', '3', '5', '/base/warehouse', '仓库', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('19', '3', '10', '/crm/stock/stockPart', '库存管理', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('20', '3', '10', '/crm/stock/stockTrade', '库存交易日志', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('21', '2', '9', null, '采购', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('22', '3', '21', '/crm/purchase/purchaseApply', '采购申请', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('23', '3', '21', '/crm/purchase/purchaseOrder', '采购订单', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('24', '3', '21', '/crm/purchase/purchaseOrderPart', '采购台账', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('25', '2', '9', null, '销售', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('26', '3', '25', '/crm/sales/salesOrder', '销售订单', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('27', '3', '25', '/crm', '销售退货', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('28', '3', '25', '/crm/sales/salesOrderPart', '销售台账', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('29', '2', '9', null, '仓储', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('30', '3', '29', '/crm/inStorage/inStorageWork', '入库作业', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('31', '3', '29', '/crm/outStorage/outStorageWork', '出库作业', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('32', '3', '2', '/base/organization', '组织机构', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('33', '3', '2', '/base/role', '用户角色', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('34', '3', '2', '/base/menu', '菜单管理', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('37', '2', '1', null, '系统监控', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('38', '3', '37', 'http://47.93.187.183:8080', '微服务监控', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('39', '3', '37', 'http://47.93.187.183:9090/', '注册中心', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('40', '3', '37', 'http://47.93.187.183:8081/swagger-ui.html', '接口文档', null, null, '0');
INSERT INTO `eidp_menu` VALUES ('41', '3', '37', 'http://47.93.187.183:9097/', '链路跟踪', null, null, '0');

-- ----------------------------
-- Table structure for eidp_organization
-- ----------------------------
DROP TABLE IF EXISTS `eidp_organization`;
CREATE TABLE `eidp_organization` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '机构编码',
  `organization_name` varchar(100) NOT NULL COMMENT '机构名称',
  `link_man` varchar(50) DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `remaker` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `organization_code` varchar(32) NOT NULL COMMENT '组织机构编码',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eidp_organization
-- ----------------------------
INSERT INTO `eidp_organization` VALUES ('16', '信息管理部', '王伟华', '13435784959', null, null, '0', 'xgb', '0');
INSERT INTO `eidp_organization` VALUES ('17', '管理员', '王伟华', '13435784959', null, null, '0', 'admin', '0');

-- ----------------------------
-- Table structure for eidp_organization_emp
-- ----------------------------
DROP TABLE IF EXISTS `eidp_organization_emp`;
CREATE TABLE `eidp_organization_emp` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `organization_id` int(4) NOT NULL COMMENT '机构编码',
  `emp_id` varchar(32) NOT NULL COMMENT '角色编码',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `organization` (`organization_id`),
  KEY `emp` (`emp_id`),
  CONSTRAINT `emp` FOREIGN KEY (`emp_id`) REFERENCES `eidp_emp` (`id`),
  CONSTRAINT `organization` FOREIGN KEY (`organization_id`) REFERENCES `eidp_organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eidp_organization_emp
-- ----------------------------
INSERT INTO `eidp_organization_emp` VALUES ('20', '17', '0002', '0');
INSERT INTO `eidp_organization_emp` VALUES ('21', '16', '0001', '0');

-- ----------------------------
-- Table structure for eidp_organization_role
-- ----------------------------
DROP TABLE IF EXISTS `eidp_organization_role`;
CREATE TABLE `eidp_organization_role` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `organization_id` int(4) NOT NULL COMMENT '机构编码',
  `role_id` int(4) NOT NULL COMMENT '角色编码',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `organization_role_organization_id` (`organization_id`),
  KEY `organization_role_role_id` (`role_id`),
  CONSTRAINT `organization_role_organization_id` FOREIGN KEY (`organization_id`) REFERENCES `eidp_organization` (`id`),
  CONSTRAINT `organization_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `eidp_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eidp_organization_role
-- ----------------------------
INSERT INTO `eidp_organization_role` VALUES ('12', '16', '3', '0');
INSERT INTO `eidp_organization_role` VALUES ('13', '17', '4', '0');

-- ----------------------------
-- Table structure for eidp_role
-- ----------------------------
DROP TABLE IF EXISTS `eidp_role`;
CREATE TABLE `eidp_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remaker` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eidp_role
-- ----------------------------
INSERT INTO `eidp_role` VALUES ('3', '开发人员', null, '0', '0');
INSERT INTO `eidp_role` VALUES ('4', '管理人员', null, '0', '0');

-- ----------------------------
-- Table structure for eidp_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `eidp_role_menu`;
CREATE TABLE `eidp_role_menu` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `role_id` int(4) NOT NULL COMMENT '角色编码',
  `menu_id` int(4) NOT NULL COMMENT '菜单编码',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `role_menu_role_id` (`role_id`),
  KEY `role_menu_menu_id` (`menu_id`),
  CONSTRAINT `role_menu_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `eidp_menu` (`id`),
  CONSTRAINT `role_menu_role_id` FOREIGN KEY (`role_id`) REFERENCES `eidp_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1723 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eidp_role_menu
-- ----------------------------
INSERT INTO `eidp_role_menu` VALUES ('179', '3', '32', '0');
INSERT INTO `eidp_role_menu` VALUES ('180', '3', '1', '0');
INSERT INTO `eidp_role_menu` VALUES ('181', '3', '33', '0');
INSERT INTO `eidp_role_menu` VALUES ('182', '3', '2', '0');
INSERT INTO `eidp_role_menu` VALUES ('183', '3', '4', '0');
INSERT INTO `eidp_role_menu` VALUES ('184', '3', '25', '0');
INSERT INTO `eidp_role_menu` VALUES ('185', '3', '9', '0');
INSERT INTO `eidp_role_menu` VALUES ('186', '3', '26', '0');
INSERT INTO `eidp_role_menu` VALUES ('187', '3', '15', '0');
INSERT INTO `eidp_role_menu` VALUES ('1685', '4', '1', '0');
INSERT INTO `eidp_role_menu` VALUES ('1686', '4', '2', '0');
INSERT INTO `eidp_role_menu` VALUES ('1687', '4', '3', '0');
INSERT INTO `eidp_role_menu` VALUES ('1688', '4', '4', '0');
INSERT INTO `eidp_role_menu` VALUES ('1689', '4', '5', '0');
INSERT INTO `eidp_role_menu` VALUES ('1690', '4', '6', '0');
INSERT INTO `eidp_role_menu` VALUES ('1691', '4', '7', '0');
INSERT INTO `eidp_role_menu` VALUES ('1692', '4', '8', '0');
INSERT INTO `eidp_role_menu` VALUES ('1693', '4', '9', '0');
INSERT INTO `eidp_role_menu` VALUES ('1694', '4', '10', '0');
INSERT INTO `eidp_role_menu` VALUES ('1695', '4', '11', '0');
INSERT INTO `eidp_role_menu` VALUES ('1696', '4', '12', '0');
INSERT INTO `eidp_role_menu` VALUES ('1697', '4', '13', '0');
INSERT INTO `eidp_role_menu` VALUES ('1698', '4', '14', '0');
INSERT INTO `eidp_role_menu` VALUES ('1699', '4', '15', '0');
INSERT INTO `eidp_role_menu` VALUES ('1700', '4', '16', '0');
INSERT INTO `eidp_role_menu` VALUES ('1701', '4', '17', '0');
INSERT INTO `eidp_role_menu` VALUES ('1702', '4', '19', '0');
INSERT INTO `eidp_role_menu` VALUES ('1703', '4', '20', '0');
INSERT INTO `eidp_role_menu` VALUES ('1704', '4', '21', '0');
INSERT INTO `eidp_role_menu` VALUES ('1705', '4', '22', '0');
INSERT INTO `eidp_role_menu` VALUES ('1706', '4', '23', '0');
INSERT INTO `eidp_role_menu` VALUES ('1707', '4', '24', '0');
INSERT INTO `eidp_role_menu` VALUES ('1708', '4', '25', '0');
INSERT INTO `eidp_role_menu` VALUES ('1709', '4', '26', '0');
INSERT INTO `eidp_role_menu` VALUES ('1710', '4', '27', '0');
INSERT INTO `eidp_role_menu` VALUES ('1711', '4', '28', '0');
INSERT INTO `eidp_role_menu` VALUES ('1712', '4', '29', '0');
INSERT INTO `eidp_role_menu` VALUES ('1713', '4', '30', '0');
INSERT INTO `eidp_role_menu` VALUES ('1714', '4', '31', '0');
INSERT INTO `eidp_role_menu` VALUES ('1715', '4', '32', '0');
INSERT INTO `eidp_role_menu` VALUES ('1716', '4', '33', '0');
INSERT INTO `eidp_role_menu` VALUES ('1717', '4', '34', '0');
INSERT INTO `eidp_role_menu` VALUES ('1718', '4', '37', '0');
INSERT INTO `eidp_role_menu` VALUES ('1719', '4', '38', '0');
INSERT INTO `eidp_role_menu` VALUES ('1720', '4', '39', '0');
INSERT INTO `eidp_role_menu` VALUES ('1721', '4', '40', '0');
INSERT INTO `eidp_role_menu` VALUES ('1722', '4', '41', '0');

-- ----------------------------
-- Table structure for eidp_vendor
-- ----------------------------
DROP TABLE IF EXISTS `eidp_vendor`;
CREATE TABLE `eidp_vendor` (
  `id` varchar(32) NOT NULL COMMENT '供应商编码',
  `vendor_name` varchar(50) NOT NULL COMMENT '供应商名称',
  `vendor_emp_id` varchar(32) NOT NULL COMMENT '负责人编号',
  `vendor_emp_name` varchar(50) NOT NULL COMMENT '负责人 名称',
  `payable_amount` decimal(16,4) DEFAULT '0.0000' COMMENT '应收金额',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `post_code` varchar(50) DEFAULT NULL COMMENT '邮编',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `contact_fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `contact_email` varchar(50) DEFAULT NULL COMMENT '邮件',
  `legal_person` varchar(50) DEFAULT NULL COMMENT '法人',
  `business_license` varchar(50) DEFAULT NULL COMMENT '营业执照',
  `taxation_code` varchar(50) DEFAULT NULL COMMENT '税务登记号',
  `opening_bank` varchar(100) DEFAULT NULL COMMENT '开户行',
  `opening_bank_account` varchar(50) DEFAULT NULL COMMENT '开户账号',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商';

-- ----------------------------
-- Records of eidp_vendor
-- ----------------------------
INSERT INTO `eidp_vendor` VALUES ('dsf', 'sdf', '0001', '王伟华', '0.0000', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '2018-07-09 21:30:49', null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for eidp_vendor_goods
-- ----------------------------
DROP TABLE IF EXISTS `eidp_vendor_goods`;
CREATE TABLE `eidp_vendor_goods` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `goods_id` varchar(32) NOT NULL COMMENT '产品编码',
  `goods_name` varchar(200) NOT NULL COMMENT '产品名称',
  `goods_type_id` varchar(32) NOT NULL COMMENT '产品类型编码',
  `goods_type_name` varchar(50) NOT NULL COMMENT '产品类型名称',
  `goods_unit_id` varchar(32) NOT NULL COMMENT '产品单位编码',
  `goods_unit_name` varchar(50) NOT NULL COMMENT '产品单位名称',
  `goods_brand_id` varchar(32) NOT NULL COMMENT '产品品牌编码',
  `goods_brand_name` varchar(50) NOT NULL COMMENT '产品品牌名称',
  `goods_model` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `vendor_id` varchar(32) NOT NULL COMMENT '供应商编码',
  `vendor_name` varchar(50) DEFAULT NULL COMMENT '供应商名称',
  `normal_price` decimal(16,4) DEFAULT '0.0000' COMMENT '供应价格',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `vendor_goods_goods_id` (`goods_id`),
  KEY `vendor_goods_goods_type_id` (`goods_type_id`),
  KEY `vendor_goods_goods_unit_id` (`goods_unit_id`),
  KEY `vendor_goods_goods_brand_id` (`goods_brand_id`),
  CONSTRAINT `vendor_goods_goods_brand_id` FOREIGN KEY (`goods_brand_id`) REFERENCES `eidp_goods_brand` (`id`),
  CONSTRAINT `vendor_goods_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `eidp_goods_archive` (`id`),
  CONSTRAINT `vendor_goods_goods_type_id` FOREIGN KEY (`goods_type_id`) REFERENCES `eidp_goods_type` (`id`),
  CONSTRAINT `vendor_goods_goods_unit_id` FOREIGN KEY (`goods_unit_id`) REFERENCES `eidp_goods_unit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商产品';

-- ----------------------------
-- Records of eidp_vendor_goods
-- ----------------------------

-- ----------------------------
-- Table structure for eidp_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `eidp_warehouse`;
CREATE TABLE `eidp_warehouse` (
  `id` varchar(32) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
  `warehouse_type` varchar(32) NOT NULL COMMENT '仓库类型编码',
  `warehouse_type_name` varchar(100) NOT NULL COMMENT '类型名称10售后仓20物料仓30限量仓40零售仓',
  `department` varchar(32) DEFAULT NULL COMMENT '所属机构',
  `area` varchar(50) DEFAULT NULL COMMENT '所属区域',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `post_code` varchar(50) DEFAULT NULL COMMENT '邮编',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `contact_fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `contact_email` varchar(50) DEFAULT NULL COMMENT '邮件',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `delete_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  `delete_emp` varchar(32) DEFAULT NULL COMMENT '删除人',
  `delete_emp_id` varchar(32) DEFAULT NULL COMMENT '删除人编号',
  `server_flag` int(11) DEFAULT '0' COMMENT '是否删除0：正常,1删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_emp` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_emp_id` varchar(32) DEFAULT NULL COMMENT '创建人编号',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_emp` varchar(32) DEFAULT NULL COMMENT '修改人',
  `modify_emp_id` varchar(32) DEFAULT NULL COMMENT '修改人编号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库';

-- ----------------------------
-- Records of eidp_warehouse
-- ----------------------------

-- ----------------------------
-- Function structure for getChildList
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildList`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `getChildList`(menuid varchar(100)) RETURNS varchar(2000) CHARSET utf8
BEGIN   
DECLARE str varchar(2000);  
DECLARE cid varchar(100);   
SET str = '$';   
SET cid = menuid;   
WHILE cid is not null DO   
    SET str = concat(str, ',', cid);   
    SELECT group_concat(id) INTO cid FROM eidp_menu where FIND_IN_SET(parent_id, cid) > 0;   
END WHILE;   
RETURN str;   
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getMenusList
-- ----------------------------
DROP FUNCTION IF EXISTS `getMenusList`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `getMenusList`(rootId varchar(100)) RETURNS varchar(1000) CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default rootId;   
DECLARE cid varchar(100) default rootId;

  
WHILE rootId is not null  do   
    SET fid =(SELECT parent_id FROM eidp_menu WHERE id = rootId);   
    IF fid is not null THEN   
        SET str = concat(str, ',', fid);   
        SET rootId = fid;   
    ELSE   
        SET rootId = fid;   
    END IF;   
END WHILE;   

WHILE cid is not null DO   
    SET str = concat(str, ',', cid);   
    SELECT group_concat(id) INTO cid FROM eidp_menu where FIND_IN_SET(parent_id, cid) > 0;   
END WHILE;

return str;  
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getParentList
-- ----------------------------
DROP FUNCTION IF EXISTS `getParentList`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `getParentList`(rootId varchar(100)) RETURNS varchar(1000) CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default rootId;   
  
WHILE rootId is not null  do   
    SET fid =(SELECT parent_id FROM eidp_menu WHERE id = rootId);   
    IF fid is not null THEN   
        SET str = concat(str, ',', fid);   
        SET rootId = fid;   
    ELSE   
        SET rootId = fid;   
    END IF;   
END WHILE;   

return str;  
END
;;
DELIMITER ;

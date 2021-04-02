-- 受理详情表 accept_detail_info
CREATE TABLE `accept_detail_info` (
`work_order_no` varchar(64) NOT NULL COMMENT '工单编号',
`channel_code` varchar(2) DEFAULT NULL COMMENT '受理渠道 信息需求受理渠道：电话中心；在线客服；邮箱；网站；柜面；寿险；微信；监管；媒体；\r\n预约受理渠道：电话；邮箱；网站；柜面；APP；寿险；微信；媒体；在线客服；监管部门；\r\n投诉受理渠道：电话；邮箱；APP；监管部门；媒体；信函；来访；官网；其他',
`item_code` varchar(10) DEFAULT NULL COMMENT '服务项目',
`call_person_id` varchar(15) DEFAULT NULL COMMENT '来电人编号 person_info中的编号',
`call_relation_by` varchar(2) DEFAULT NULL COMMENT '来电人与被保人关系',
`priority_level` varchar(2) DEFAULT NULL COMMENT '优先级',
`contacts_person_id` varchar(15) DEFAULT NULL COMMENT '联系人编号 person_info中的编号',
`contacts_relation_by` varchar(2) DEFAULT NULL COMMENT '联系人与被保人关系',
`call_center_id` varchar(32) DEFAULT NULL COMMENT '电话中心业务流水号',
`email` varchar(32) DEFAULT NULL COMMENT 'E-MAIL',
`organ_code` varchar(15) DEFAULT NULL COMMENT '出单机构',
`content` varchar(2000) DEFAULT NULL COMMENT '业务内容',
`complaint_person_id` varchar(15) DEFAULT NULL COMMENT '投诉人编号 person_info中的编号；当工单是预约环节此人是申请人',
`status` varchar(2) DEFAULT NULL COMMENT '状态',
`create_by` varchar(10) NOT NULL COMMENT '创建人 原操作人，存放user_info中的流水号',
`create_time` datetime NOT NULL COMMENT '创建时间',
`update_by` varchar(10) NOT NULL COMMENT '更新人 每次修改人员，存放user_info中的流水号',
`update_time` datetime NOT NULL COMMENT '更新时间',
`prop0` varchar(2) DEFAULT NULL COMMENT '见转意表',
`prop1` varchar(2) DEFAULT NULL COMMENT '见转意表',
`prop2` varchar(2) DEFAULT NULL COMMENT '见转意表',
`prop3` varchar(4) DEFAULT NULL COMMENT '见转意表',
`prop4` varchar(4) DEFAULT NULL COMMENT '见转意表',
`prop5` varchar(4) DEFAULT NULL COMMENT '见转意表',
`prop6` varchar(4) DEFAULT NULL COMMENT '见转意表',
`prop7` varchar(15) DEFAULT NULL COMMENT '见转意表',
`prop8` varchar(15) DEFAULT NULL COMMENT '见转意表',
`prop9` varchar(15) DEFAULT NULL COMMENT '见转意表',
`prop10` varchar(30) DEFAULT NULL COMMENT '见转意表',
`prop11` varchar(20) DEFAULT NULL COMMENT '见转意表',
`prop12` varchar(20) DEFAULT NULL COMMENT '见转意表',
`prop13` varchar(20) DEFAULT NULL COMMENT '见转意表',
`prop14` varchar(30) DEFAULT NULL COMMENT '见转意表',
`prop15` varchar(20) DEFAULT NULL COMMENT '见转意表',
`prop16` varchar(32) DEFAULT NULL COMMENT '见转意表',
`prop17` varchar(32) DEFAULT NULL COMMENT '见转意表',
`prop18` varchar(32) DEFAULT NULL COMMENT '见转意表',
`prop19` varchar(32) DEFAULT NULL COMMENT '见转意表',
`prop20` varchar(32) DEFAULT NULL COMMENT '见转意表',
`prop21` varchar(32) DEFAULT NULL COMMENT '见转意表',
`prop22` varchar(32) DEFAULT NULL COMMENT '见转意表',
`prop23` varchar(50) DEFAULT NULL COMMENT '见转意表',
`prop24` varchar(50) DEFAULT NULL COMMENT '见转意表',
`prop25` varchar(50) DEFAULT NULL COMMENT '见转意表',
`prop26` varchar(100) DEFAULT NULL COMMENT '见转意表',
`prop27` varchar(100) DEFAULT NULL COMMENT '见转意表',
`prop28` varchar(200) DEFAULT NULL COMMENT '见转意表',
`prop29` varchar(500) DEFAULT NULL COMMENT '见转意表',
`prop30` varchar(500) DEFAULT NULL COMMENT '见转意表',
`prop31` varchar(1000) DEFAULT NULL COMMENT '见转意表',
PRIMARY KEY (`work_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='受理详情表';

-- 附件信息表 attachment_info
CREATE TABLE `attachment_info` (
`attachment_id` varchar(32) NOT NULL COMMENT '附件流水号',
`business_no` varchar(32) NOT NULL COMMENT '业务号 是什么环节的附件',
`sub_business_no` varchar(32) DEFAULT NULL COMMENT '子业务号',
`business_type` varchar(4) DEFAULT NULL COMMENT '业务类型',
`fileNetId` varchar(32) DEFAULT NULL COMMENT '文件系统中编号 外部系统主键',
`attachment_name` varchar(32) DEFAULT NULL COMMENT '附件名称',
`attachment_url` varchar(1024) DEFAULT NULL COMMENT '所在地址',
`attachment_type` varchar(32) DEFAULT NULL COMMENT '附件类型',
`state` char(1) DEFAULT NULL COMMENT '附件状态',
`remark` varchar(128) DEFAULT NULL COMMENT '备注',
`order_id` int(11) DEFAULT NULL COMMENT '排序',
`status` varchar(2) DEFAULT NULL COMMENT '状态',
`uplod_by` varchar(32) NOT NULL COMMENT '上传人',
`upload_time` datetime NOT NULL COMMENT '上传时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件信息表';

-- 再次来电信息表 call_again
CREATE TABLE `call_again` (
`call_serial_num` varchar(32) NOT NULL COMMENT '来电流水号',
`work_order_no` varchar(20) NOT NULL COMMENT '工单号',
`remark` varchar(2000) DEFAULT NULL COMMENT '说明',
`status` varchar(2) DEFAULT NULL COMMENT '状态',
`created_by` varchar(10) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(10) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`call_serial_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='再次来电信息表';

-- 业务码表 code_dict
CREATE TABLE `code_dict` (
`code_type` varchar(64) NOT NULL COMMENT '编码类型 类型code',
`code` varchar(64) NOT NULL COMMENT '编码值 code值',
`code_name` varchar(128) DEFAULT NULL COMMENT '编码名称 字面意思',
`code_alias` varchar(128) DEFAULT NULL COMMENT '编码简称',
`status` char(1) NOT NULL COMMENT '编码状态 有效状态: 1-有效；0-无效',
`parent_code` varchar(64) DEFAULT NULL COMMENT '父节点 父节点Code值',
`parent_code_type` varchar(64) DEFAULT NULL COMMENT '父节点type',
`remarks` varchar(512) DEFAULT NULL COMMENT '备注',
`order_no` int(11) DEFAULT NULL COMMENT '编码排序 排序',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`code_type`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务码表';

-- 协办信息表 collaborative_from
CREATE TABLE `collaborative_from` (
`collaborative_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '协办流水号',
`work_order_no` varchar(32) NOT NULL COMMENT '工单号',
`from_user_id` varchar(10) DEFAULT NULL COMMENT '用户编号',
`um_code` varchar(32) DEFAULT NULL COMMENT '协办方UM账号',
`solicit_opinion` varchar(50) DEFAULT NULL COMMENT '征求处理意见',
`attachment_flag` varchar(1) DEFAULT NULL COMMENT '有无附件',
`handle_state` varchar(4) DEFAULT NULL COMMENT '处理状态 01-意见处理等待;02-意见处理完成;',
`status` varchar(4) DEFAULT NULL COMMENT '状态 01-协办中；02-已协办；',
`opinion` varchar(512) DEFAULT NULL COMMENT '处理意见 同flow_log中意见',
`valid_flag` varchar(2) DEFAULT NULL COMMENT '投诉是否成立',
`non_reason` varchar(512) DEFAULT NULL COMMENT '投诉不成立理由',
`treatment_plan` varchar(32) DEFAULT NULL COMMENT '处理方案',
`treatment_basis` varchar(32) DEFAULT NULL COMMENT '处理依据',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`collaborative_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COMMENT='协办信息表';

-- 修改明细表 edit_detail
CREATE TABLE `edit_detail` (
`detail_id` varchar(15) NOT NULL COMMENT '明细流水号',
`edit_id` varchar(32) NOT NULL COMMENT '修改流水号',
`key_dict_type` varchar(32) DEFAULT NULL COMMENT '属性名字典类型 针对对象在字典中的属性和名称的定义',
`item_name` varchar(32) DEFAULT NULL COMMENT '修改属性名称',
`item_key` varchar(32) DEFAULT NULL COMMENT '修改属性编码',
`old_value` varchar(32) DEFAULT NULL COMMENT '原值',
`now_value` varchar(32) DEFAULT NULL COMMENT '新值',
`value_dict_type` varchar(32) DEFAULT NULL COMMENT '属性值字典类型 针对具体属性在字典中的类型',
`order_no` int(11) DEFAULT NULL COMMENT '排序',
`status` varchar(2) DEFAULT NULL COMMENT '状态',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='修改明细表';

-- 修改信息表 edit_info
CREATE TABLE `edit_info` (
`edit_id` varchar(32) NOT NULL COMMENT '编辑流水号',
`work_order_id` varchar(32) NOT NULL COMMENT '工单号',
`other_id` varchar(32) DEFAULT NULL COMMENT '其他业务流水编号',
`other_type` varchar(4) DEFAULT NULL COMMENT '其他业务类型',
`edit_reason` varchar(50) DEFAULT NULL COMMENT '修改原因',
`edit_remark` varchar(50) DEFAULT NULL COMMENT '修改说明',
`status` varchar(2) DEFAULT NULL COMMENT '状态',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`edit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='修改信息表';

-- 受理信息映射表 field_map
CREATE TABLE `field_map` (
`source_name` varchar(32) NOT NULL COMMENT '源名称 可对象或者表名',
`type_code` varchar(32) NOT NULL COMMENT '类型编码 不同业务类型等',
`field_order` int(11) NOT NULL COMMENT '排序 字段排序',
`source_filed_name` varchar(32) NOT NULL COMMENT '源列名 不定字段对应真实名称',
`source_field_mean` varchar(32) DEFAULT NULL COMMENT '源字段名称 不定字段对应的真实名的意思',
`target_table_name` varchar(32) DEFAULT NULL COMMENT '目标表名 含有不定字段的表名',
`target_column_name` varchar(32) DEFAULT NULL COMMENT '目标列名 不定字段名',
`target_column_mean` varchar(32) DEFAULT NULL COMMENT '目标字段名称',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
`status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 1-有效;0-无效',
PRIMARY KEY (`source_name`,`type_code`,`field_order`,`source_filed_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='受理信息映射表 ';

-- 业务流转信息表 flow_log
CREATE TABLE `flow_log` (
`flow_id` varchar(32) NOT NULL COMMENT '流水号',
`work_order_no` varchar(64) DEFAULT NULL COMMENT '业务号',
`sub_id` varchar(32) DEFAULT NULL COMMENT '子业务号',
`sub_type` varchar(2) DEFAULT NULL COMMENT '子业务类型',
`link_code` varchar(2) DEFAULT NULL COMMENT '状态',
`operate_code` varchar(2) DEFAULT NULL COMMENT '操作',
`make_by` varchar(32) DEFAULT NULL COMMENT '处理人',
`um_num` varchar(64) DEFAULT NULL COMMENT 'UM账号',
`make_time` datetime DEFAULT NULL COMMENT '处理时间',
`remarks` varchar(512) DEFAULT NULL COMMENT '说明',
`opinion` varchar(512) DEFAULT NULL COMMENT '处理意见',
`to_department` varchar(10) DEFAULT NULL COMMENT '流转部门 针对当前操作有转办或协办的操作',
`to_reason` varchar(128) DEFAULT NULL COMMENT '流转原因',
`attachment_flag` varchar(1) DEFAULT NULL COMMENT '是否有附件',
`status` varchar(2) DEFAULT NULL COMMENT '备用状态',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务流转信息表';

-- HCS预约修改 hcs_modification
CREATE TABLE `hcs_modification` (
`work_order_no` varchar(32) NOT NULL COMMENT '工单号',
`alter_id` varchar(10) NOT NULL COMMENT '修改序列号',
`alter_by` varchar(20) DEFAULT NULL COMMENT '修改人',
`um_code` varchar(32) DEFAULT NULL COMMENT '修改人UM编号',
`alter_time` datetime DEFAULT NULL COMMENT '修改时间',
`alter_channel` varchar(2) DEFAULT NULL COMMENT '修改渠道',
`alter_content` varchar(1024) DEFAULT NULL COMMENT '修改内容描述',
`rela_edit_id` varchar(32) DEFAULT NULL COMMENT '关联修改流水号 关联edit_info表中的主键',
`call_serial_num` varchar(32) DEFAULT NULL COMMENT '来电流水号',
`status` varchar(2) DEFAULT NULL COMMENT '状态',
`created_by` varchar(10) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(10) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
`modify_priority` int(11) DEFAULT NULL COMMENT '处理优先级 取值 0,1 越大优先级越高',
`alter_type` varchar(64) DEFAULT NULL COMMENT '修改类型',
`other_no` varchar(32) DEFAULT NULL COMMENT '外部受理关联号 主要关联外部系统受理的工单号',
PRIMARY KEY (`work_order_no`,`alter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='HCS预约修改 '

-- 人员信息表 person_info
CREATE TABLE `person_info` (
`person_id` varchar(15) NOT NULL COMMENT '人员编号 本系统编号',
`name` varchar(32) DEFAULT NULL COMMENT '姓名',
`sex` varchar(2) DEFAULT NULL COMMENT '性别 01-男；02-女',
`identity` varchar(10) DEFAULT NULL COMMENT '身份',
`mobile_phone` varchar(15) DEFAULT NULL COMMENT '移动电话',
`line_phone` varchar(50) DEFAULT NULL COMMENT '固定电话 国家区号-区号-号码-分机号;"-"不能丢',
`home_phone` varchar(50) DEFAULT NULL COMMENT '家庭电话 国家区号-区号-号码-分机号;"-"不能丢',
`work_phone` varchar(50) DEFAULT NULL COMMENT '办公电话 国家区号-区号-号码-分机号;"-"不能丢',
`language` varchar(20) DEFAULT NULL COMMENT '语言',
`email` varchar(50) DEFAULT NULL COMMENT 'E-MAIL',
`fax` varchar(50) DEFAULT NULL COMMENT '传真',
`id_number` varchar(32) DEFAULT NULL COMMENT '证件号',
`id_type` varchar(4) DEFAULT NULL COMMENT '证件类型',
`birthday` date DEFAULT NULL COMMENT '出生日期',
`country` varchar(10) DEFAULT NULL COMMENT '国家',
`province` varchar(10) DEFAULT NULL COMMENT '省',
`city` varchar(10) DEFAULT NULL COMMENT '市',
`district` varchar(10) DEFAULT NULL COMMENT '区',
`address` varchar(2000) DEFAULT NULL COMMENT '联系地址',
`other_customer_no` varchar(15) DEFAULT NULL COMMENT '客户编号 外部系统编号',
`created_by` varchar(10) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(10) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='人员信息表';

-- 质检受理 quality_inspection_accept
CREATE TABLE `quality_inspection_accept` (
`inspection_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '质检流水号',
`work_order_no` varchar(32) NOT NULL COMMENT '工单号',
`result` varchar(32) DEFAULT NULL COMMENT '质检结果 01-合格;02-不合格',
`status` varchar(2) DEFAULT NULL COMMENT '质检状态 01-待质检;\r\n02-质检中;03-质检完成',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`inspection_id`,`work_order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='质检受理'

-- 质检处理 quality_inspection_handle

CREATE TABLE `quality_inspection_handle` (
`inspection_handler_id` varchar(32) NOT NULL COMMENT '质检处理流水号',
`inspection_id` bigint(20) NOT NULL COMMENT '质检编号',
`work_order_no` varchar(32) NOT NULL COMMENT '工单编号',
`score` varchar(20) DEFAULT NULL COMMENT '质检评分',
`appeal_flag` varchar(2) DEFAULT NULL COMMENT '是否申诉',
`appeal_reason` varchar(64) DEFAULT NULL COMMENT '申诉理由',
`status` varchar(2) DEFAULT NULL COMMENT '状态 差错确认状态	cs_confirm_status	01-待确认；02-已确认；03-结案；04-无；',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`inspection_handler_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='质检处理 '

-- 质检项目 quality_inspection_item

CREATE TABLE `quality_inspection_item` (
`item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水编号',
`inspection_handler_id` varchar(32) NOT NULL COMMENT '质检处理流水号',
`inspection_id` varchar(32) NOT NULL COMMENT '质检编号',
`item_type` varchar(20) DEFAULT NULL COMMENT '组',
`item_key` varchar(20) DEFAULT NULL COMMENT '名',
`value` varchar(50) DEFAULT NULL COMMENT '值',
`item_remark` varchar(64) DEFAULT NULL COMMENT '说明',
`status` varchar(2) DEFAULT NULL COMMENT '状态 Y-有效;N-无效',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8mb4 COMMENT='质检项目 '

-- 主键记录表 sys_maxno
CREATE TABLE `sys_maxno` (
`notype` varchar(50) DEFAULT NULL COMMENT '最大值编码',
`nolimit` varchar(12) DEFAULT NULL COMMENT '编码类型',
`maxno` bigint(20) DEFAULT NULL COMMENT '最大值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 转办信息表 transfer_by
CREATE TABLE `transfer_by` (
`transfer_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '转办流水号',
`work_order_no` varchar(32) NOT NULL COMMENT '工单号',
`to_user_id` varchar(10) DEFAULT NULL COMMENT '接收人编号',
`um_code` varchar(32) DEFAULT NULL COMMENT '接收方UM账号',
`reason` varchar(32) DEFAULT NULL COMMENT '转交处理原因',
`attachment_flag` varchar(1) DEFAULT NULL COMMENT '附件标志',
`status` varchar(2) DEFAULT NULL COMMENT '状态',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`transfer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COMMENT='转办信息表';

-- 用户信息表 user_info
CREATE TABLE `user_info` (
`user_id` varchar(10) NOT NULL COMMENT '用户编号',
`user_code` varchar(20) DEFAULT NULL COMMENT '用户外部系统ID',
`user_name` varchar(15) NOT NULL COMMENT '用户名称',
`um_code` varchar(32) DEFAULT NULL COMMENT 'UM账号',
`user_state` char(1) DEFAULT NULL COMMENT '有效状态',
`created_by` varchar(10) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(10) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表 针对系统操作用户';

-- 工单处理信息表 work_handle_info
CREATE TABLE `work_handle_info` (
`handle_id` bigint(20) NOT NULL COMMENT '处理流水号',
`handle_type` varchar(4) NOT NULL COMMENT '处理类型 处理；取消；转办；协办；质检',
`work_order_no` varchar(64) NOT NULL COMMENT '工单号',
`flag` varchar(32) DEFAULT NULL COMMENT '标志',
`status` varchar(32) DEFAULT NULL COMMENT '状态',
`created_by` varchar(32) NOT NULL COMMENT '创建人',
`created_time` datetime NOT NULL COMMENT '创建时间',
`updated_by` varchar(32) NOT NULL COMMENT '更新人',
`updated_time` datetime NOT NULL COMMENT '更新时间',
`remark` varchar(2000) DEFAULT NULL COMMENT '业务说明',
`handle_prop1` varchar(2) DEFAULT NULL COMMENT '处理属性1 见转意表',
`handle_prop2` varchar(2) DEFAULT NULL COMMENT '处理属性2 见转意表',
`handle_prop3` varchar(4) DEFAULT NULL COMMENT '处理属性3 见转意表',
`handle_prop4` varchar(4) DEFAULT NULL COMMENT '处理属性4 见转意表',
`handle_prop5` varchar(4) DEFAULT NULL COMMENT '处理属性5 见转意表',
`handle_prop6` varchar(4) DEFAULT NULL COMMENT '处理属性6 见转意表',
`handle_prop7` varchar(10) DEFAULT NULL COMMENT '处理属性7 见转意表',
`handle_prop8` varchar(10) DEFAULT NULL COMMENT '处理属性8 见转意表',
`handle_prop9` varchar(10) DEFAULT NULL COMMENT '处理属性9 见转意表',
`handle_prop10` varchar(15) DEFAULT NULL COMMENT '处理属性10 见转意表',
`handle_prop11` varchar(15) DEFAULT NULL COMMENT '处理属性11 见转意表',
`handle_prop12` varchar(15) DEFAULT NULL COMMENT '处理属性12 见转意表',
`handle_prop13` varchar(20) DEFAULT NULL COMMENT '处理属性13 见转意表',
`handle_prop14` varchar(20) DEFAULT NULL COMMENT '处理属性14 见转意表',
`handle_prop15` varchar(20) DEFAULT NULL COMMENT '处理属性15 见转意表',
`handle_prop16` varchar(32) DEFAULT NULL COMMENT '处理属性16 见转意表',
`handle_prop17` varchar(32) DEFAULT NULL COMMENT '处理属性17 见转意表',
`handle_prop18` varchar(32) DEFAULT NULL COMMENT '处理属性18 见转意表',
`handle_prop19` varchar(32) DEFAULT NULL COMMENT '处理属性19 见转意表',
`handle_prop20` varchar(32) DEFAULT NULL COMMENT '处理属性20 见转意表',
`handle_prop21` varchar(32) DEFAULT NULL COMMENT '处理属性21 见转意表',
`handle_prop22` varchar(32) DEFAULT NULL COMMENT '处理属性22 见转意表',
`handle_prop23` varchar(32) DEFAULT NULL COMMENT '处理属性23 见转意表',
`handle_prop24` varchar(32) DEFAULT NULL COMMENT '处理属性24 见转意表',
`handle_prop25` varchar(32) DEFAULT NULL COMMENT '处理属性25 见转意表',
`handle_prop26` varchar(32) DEFAULT NULL COMMENT '处理属性26 见转意表',
`handle_prop27` varchar(50) DEFAULT NULL COMMENT '处理属性27 见转意表',
`handle_prop28` varchar(50) DEFAULT NULL COMMENT '处理属性28 见转意表',
`handle_prop29` varchar(50) DEFAULT NULL COMMENT '处理属性29 见转意表',
`handle_prop30` varchar(500) DEFAULT NULL COMMENT '处理属性30 见转意表',
PRIMARY KEY (`handle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工单处理信息表';

-- 工单申请信息表 work_order_accept
CREATE TABLE `work_order_accept` (
`work_order_no` varchar(32) NOT NULL COMMENT '工单号',
`business_type` varchar(4) NOT NULL COMMENT '业务类型 01-信息需求，02-预约，03-投诉；',
`policy_no` varchar(32) DEFAULT NULL COMMENT '保单号',
`policy_item_no` varchar(32) DEFAULT NULL COMMENT '分单号',
`risk_code` varchar(20) DEFAULT NULL COMMENT '险种号',
`insured_no` varchar(15) DEFAULT NULL COMMENT '被保人客户号，person_info编号',
`insured_name` varchar(20) DEFAULT NULL COMMENT '被保人',
`holder_no` varchar(15) DEFAULT NULL COMMENT '投保人客户号，person_info编号',
`holder_name` varchar(20) DEFAULT NULL COMMENT '投保人',
`accept_by` varchar(20) DEFAULT NULL COMMENT '受理人',
`accept_time` datetime DEFAULT NULL COMMENT '受理时间',
`modify_by` varchar(20) DEFAULT NULL COMMENT '处理人 仅争对获取操作的人',
`modify_time` datetime DEFAULT NULL COMMENT '修改时间',
`vip_flag` varchar(2) DEFAULT NULL COMMENT 'VIP标识',
`organ_code` varchar(15) DEFAULT NULL COMMENT '出单机构',
`status` varchar(2) NOT NULL COMMENT '状态 每个业务流程中的状态；如：待处理、处理中（&协办表=协办中）、处理完成、质检中、质检完成；\r\n信息需求公共查询中状态：01-待处理；02-处理中；03-已处理；04-已完成；05-已取消；',
`end_date` datetime DEFAULT NULL COMMENT '结案日期 首次结案日期',
`click_time` datetime DEFAULT NULL COMMENT '处理时间 信息需求，预约点击处理超链接记录此时间；投诉为完成时间',
`hang_flag` varchar(2) DEFAULT NULL COMMENT '有无挂起',
`hang_reason` varchar(32) DEFAULT NULL COMMENT '挂起原因',
`create_by` varchar(10) NOT NULL COMMENT '创建人',
`create_time` datetime NOT NULL COMMENT '创建时间',
`update_by` varchar(10) DEFAULT NULL,
`update_time` datetime DEFAULT NULL,
`other_no` varchar(32) DEFAULT NULL COMMENT '外部受理案件号',
`activation_num` int(11) DEFAULT '0' COMMENT '激活次数',
`state` int(11) NOT NULL DEFAULT '1' COMMENT '数据有效状态 1-有效;0-无效;',
`last_end_date` datetime DEFAULT NULL COMMENT '最后结案日期 最后一次结案日期',
PRIMARY KEY (`work_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工单申请信息表 '

CREATE TABLE hang_up_log(
hang_up_id BIGINT NOT NULL AUTO_INCREMENT  COMMENT '挂起流水号' ,
work_order_no VARCHAR(32)    COMMENT '工单号' ,
hang_state VARCHAR(32)    COMMENT '挂起状态' ,
hang_reason VARCHAR(32)    COMMENT '挂起原因' ,
up_time DATETIME NOT NULL   COMMENT '挂起时间' ,
unhang_reason VARCHAR(32)    COMMENT '解挂原因' ,
down_time DATETIME    COMMENT '解挂时间' ,
times DECIMAL(10,2)    COMMENT '挂到解时长' ,
status VARCHAR(2)    COMMENT '数据状态 Y-有效；N-无效' ,
created_by VARCHAR(32) NOT NULL   COMMENT '创建人' ,
created_time DATETIME NOT NULL   COMMENT '创建时间' ,
updated_by VARCHAR(32)    COMMENT '更新人' ,
updated_time DATETIME    COMMENT '更新时间' ,
PRIMARY KEY (hang_up_id)
) COMMENT = '挂起解挂记录表 挂起解挂记录表';

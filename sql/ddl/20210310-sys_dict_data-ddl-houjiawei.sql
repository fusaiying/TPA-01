
DELETE from sys_dict_type where dict_type = 'negotiation_type';
INSERT INTO `sys_dict_type` (  `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '协谈类型', 'negotiation_type', '0', 'admin', NULL, 'admin', NULL, '协谈类型');


DELETE from sys_dict_type where dict_type = 'negotiation_sub_type';
INSERT INTO `sys_dict_type` (  `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (  '协谈细类', 'negotiation_sub_type', '0', 'admin', '2021-01-27 09:36:58', '', NULL, NULL);


DELETE from sys_dict_data where dict_type = 'negotiation_type';

INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '1', '协议', '01', 'negotiation_type', '', '', 'Y', '0', 'admin', '2020-07-09 16:40:31', 'admin', '2020-07-09 16:40:31', 'oooo');
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '2', '通融', '02', 'negotiation_type', '', '', 'N', '0', 'admin', '2020-07-09 16:40:38', 'admin', '2020-07-09 16:40:38', 'oooo');
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '4', '复杂协谈', '04', 'negotiation_type', NULL, NULL, 'N', '0', 'admin', '2021-01-27 09:34:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '6', '拒付', '06', 'negotiation_type', NULL, NULL, 'N', '0', 'admin', '2021-01-27 09:35:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '8', '翻译', '08', 'negotiation_type', NULL, NULL, 'N', '0', 'admin', '2021-01-27 09:35:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '9', '简单协谈', '09', 'negotiation_type', NULL, NULL, 'N', '0', 'admin', '2021-01-27 09:36:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '10', '缺材料', '10', 'negotiation_type', NULL, NULL, 'N', '0', 'admin', '2021-01-27 09:36:17', '', NULL, NULL);


DELETE from sys_dict_data where dict_type = 'negotiation_sub_type';

INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('1', '单纯拒付', '0006', 'negotiation_sub_type', '', '04', 'N', '0', 'admin', '2020-07-09 16:40:31', 'admin', '2020-07-09 16:40:31', '');
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '2', '部分给付', '0007', 'negotiation_sub_type', '', '04', 'N', '0', 'admin', '2020-07-09 16:40:38', 'admin', '2020-07-09 16:40:38', '');
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '3', '拒付解约', '0008', 'negotiation_sub_type', '', '04', 'N', '0', 'admin', '2020-07-09 16:40:47', 'admin', '2020-07-09 16:40:47', '');
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '4', '不实告知二核', '0009', 'negotiation_sub_type', NULL, '04', 'N', '0', 'admin', '2021-01-27 09:34:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '5', '英语', '0010', 'negotiation_sub_type', NULL, '08', 'N', '0', 'admin', '2021-01-27 09:34:55', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '6', '其他语种', '0011', 'negotiation_sub_type', NULL, '08', 'N', '0', 'admin', '2021-01-27 09:35:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '7', 'H201问题', '0001', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:35:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '8', '协议问题', '0002', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:35:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '9', '发票及费用明细问题', '0003', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:36:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '10', '病史问题', '0004', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:36:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '11', '其他', '0005', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:36:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '12', '证件问题', '0013', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:36:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '13', '申请书问题', '0014', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:36:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '14', '授权委托书', '0015', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:36:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '15', '直接卡问题', '0016', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:36:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '16', '扫描问题', '0017', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:36:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` ( `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( '17', '医保咨询', '0018', 'negotiation_sub_type', NULL, '09', 'N', '0', 'admin', '2021-01-27 09:36:17', '', NULL, NULL);

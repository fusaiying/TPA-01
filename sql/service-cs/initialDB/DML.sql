INSERT INTO `sn-cs`.field_map (source_name,type_code,field_order,source_filed_name,source_field_mean,target_table_name,target_column_name,target_column_mean,created_by,created_time,updated_by,updated_time,status) VALUES
('ComplaintAcceptVo','投诉',1,'complaintTime','预约时间','accept_detail_info','prop10','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintAcceptVo','投诉',2,'attachmentFlag','有无附件','accept_detail_info','prop0','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintAcceptVo','投诉',2,'persuasionFlag','是否已劝解','accept_detail_info','prop2','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',1,'level1','一级投诉分类','work_handle_info','handleProp3','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',2,'level2','二级投诉分类','work_handle_info','handleProp4','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',3,'pieceworkFlag','是否计件','work_handle_info','flag','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',4,'complaintStatus','投诉状态','work_handle_info','handleProp5','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',5,'complaintTenable','投诉是否成立','work_handle_info','handleProp1','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',6,'faseReason','投诉不成立理由','work_handle_info','handleProp6','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',7,'repeatedComplaint','重复投诉','work_handle_info','handleProp7','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
;
INSERT INTO `sn-cs`.field_map (source_name,type_code,field_order,source_filed_name,source_field_mean,target_table_name,target_column_name,target_column_mean,created_by,created_time,updated_by,updated_time,status) VALUES
('ComplaintDealVo','投诉',8,'reason1','一级投诉原因','work_handle_info','handleProp8','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',9,'reason2','二级投诉原因','work_handle_info','handleProp9','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',10,'reason3','三级投诉原因','work_handle_info','handleProp10','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',11,'complaintLink','投诉环节(报保监)','work_handle_info','handleProp11','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',12,'complaintQuestion','投诉问题(报保监)','work_handle_info','handleProp12','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',13,'outsideState','行协调解或外部鉴定状态','work_handle_info','handleProp13','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',14,'riskType','险种类型','work_handle_info','handleProp14','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',15,'marketChannel','营销渠道','work_handle_info','handleProp15','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',16,'complaintCategory','投诉业务类别','work_handle_info','handleProp16','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',17,'rootDepartment','投诉根因部门','work_handle_info','handleProp17','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
;
INSERT INTO `sn-cs`.field_map (source_name,type_code,field_order,source_filed_name,source_field_mean,target_table_name,target_column_name,target_column_mean,created_by,created_time,updated_by,updated_time,status) VALUES
('ComplaintDealVo','投诉',18,'actionCause','致诉原因','work_handle_info','handleProp30','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',19,'treatmentProgress','处理进展','work_handle_info','handleProp18','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',20,'treatmentResult','处理结果','work_handle_info','remark','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',21,'customerFeedback','客户反馈','work_handle_info','handleProp2','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',22,'rootImprovement','根因改善','work_handle_info','handleProp19','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',23,'actPromptly','投诉损失','work_handle_info','handleProp20','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ComplaintDealVo','投诉',24,'improvementMeasures','改善措施','work_handle_info','handleProp21','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('DemandAcceptVo','信息需求',1,'bankTransfer','是否涉及银行转账','accept_detail_info','prop2','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('DemandAcceptVo','信息需求',2,'bankName','开户行','accept_detail_info','prop7','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('DemandAcceptVo','信息需求',3,'bankLocation','开户地','accept_detail_info','prop8','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
;
INSERT INTO `sn-cs`.field_map (source_name,type_code,field_order,source_filed_name,source_field_mean,target_table_name,target_column_name,target_column_mean,created_by,created_time,updated_by,updated_time,status) VALUES
('DemandAcceptVo','信息需求',4,'accountNumber','账号','accept_detail_info','prop10','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('DemandAcceptVo','信息需求',5,'bankHolder','户名','accept_detail_info','prop11','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('DemandAcceptVo','信息需求',6,'attachmentFlag','有无附件','accept_detail_info','prop1','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',1,'outpatientSettlement','门诊直接结算服务项目','accept_detail_info','prop0','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',2,'visitType','就诊类型','accept_detail_info','prop2','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',3,'disease','疾病名称','accept_detail_info','prop7','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',4,'symptomsSigns','症状或体征','accept_detail_info','prop16','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',5,'symptomTimes','本次疾病/症状起病时间','accept_detail_info','prop4','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',6,'timeUnit','时间单位','accept_detail_info','prop3','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',7,'accidentFlag','是否意外','accept_detail_info','prop1','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
;
INSERT INTO `sn-cs`.field_map (source_name,type_code,field_order,source_filed_name,source_field_mean,target_table_name,target_column_name,target_column_mean,created_by,created_time,updated_by,updated_time,status) VALUES
('ReservationAcceptVo','预约',8,'accidentReason','意外原因','accept_detail_info','prop17','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',9,'validCertificate','是否持有效证件','accept_detail_info','prop5','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',10,'settlementCard','是否持有直结卡','accept_detail_info','prop6','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',11,'complaintTime','预约时间','accept_detail_info','prop18','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',12,'hospitalDays','预计住院天数','accept_detail_info','prop8','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',13,'medicalInstitution','医疗机构ID','accept_detail_info','prop11','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',14,'department','科室','accept_detail_info','prop10','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',15,'bunk','床位','accept_detail_info','prop9','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',16,'compensationRatio','医院赔付比例','accept_detail_info','prop12','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',17,'appointmentDate','预约日期','accept_detail_info','prop14','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
;
INSERT INTO `sn-cs`.field_map (source_name,type_code,field_order,source_filed_name,source_field_mean,target_table_name,target_column_name,target_column_mean,created_by,created_time,updated_by,updated_time,status) VALUES
('ReservationAcceptVo','预约',18,'hospitalWorkCall','医院电话','accept_detail_info','prop15','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',19,'earliestTime','类似疾病症状最早发生时间','accept_detail_info','prop19','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',20,'firstFlag','是否首次就诊','accept_detail_info','prop20','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',21,'clinicDate','就诊日期','accept_detail_info','prop21','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',22,'clinicTime','就诊时间','accept_detail_info','prop22','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',23,'province','就诊医院省','accept_detail_info','prop23','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',24,'city','就诊医院市','accept_detail_info','prop24','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',25,'settlementFlag','是否申请直结','accept_detail_info','prop25','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',26,'hospitalName','医疗机构名称','accept_detail_info','prop26','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationAcceptVo','预约',27,'hospitalAddress','医院地址','accept_detail_info','prop29','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
;
INSERT INTO `sn-cs`.field_map (source_name,type_code,field_order,source_filed_name,source_field_mean,target_table_name,target_column_name,target_column_mean,created_by,created_time,updated_by,updated_time,status) VALUES
('ReservationDealVo','预约',1,'times','处理时长','work_handle_info','handleProp13','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationDealVo','预约',2,'businessProcess','业务处理情况','work_handle_info','handleProp1','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationDealVo','预约',3,'customerFeedback','客户反馈','work_handle_info','handleProp2','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationDealVo','预约',4,'closeType','结案类型','work_handle_info','handleProp3','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ReservationDealVo','预约',5,'costsIncurred','是否需要担保函','work_handle_info','handleProp30','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ServiceProcessingVo','信息需求',1,'businessProcess','业务处理情况','work_handle_info','handleProp1','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ServiceProcessingVo','信息需求',2,'customerFeedback','客户反馈','work_handle_info','handleProp2','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ServiceProcessingVo','信息需求',3,'closeType','结案类型','work_handle_info','handleProp3','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
,('ServiceProcessingVo','信息需求',4,'costsIncurred','安抚或通融发生费用成本','work_handle_info','handleProp30','','admin','2021-01-22 18:00:00','admin','2021-01-22 18:00:00',1)
;


INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_classify_level1','01','内部投诉',NULL,'1',NULL,NULL,'一级投诉分类',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_classify_level1','02','外部投诉',NULL,'1',NULL,NULL,'一级投诉分类',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_classify_level2','01','常规投诉',NULL,'1','01',NULL,'二级投诉分类',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_classify_level2','02','涉媒投诉',NULL,'1','01',NULL,'二级投诉分类',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_classify_level2','03','涉诉投诉',NULL,'1','01',NULL,'二级投诉分类',3,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_classify_level2','04','涉监投诉',NULL,'1','01',NULL,'二级投诉分类',4,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_classify_level2','05','监管投诉',NULL,'1','02',NULL,'二级投诉分类',5,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_classify_level2','06','媒体投诉',NULL,'1','02',NULL,'二级投诉分类',6,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_classify_level2','07','群体投诉',NULL,'1','02',NULL,'二级投诉分类',7,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_classify_level2','08','其他外部投诉',NULL,'1','02',NULL,'二级投诉分类',8,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_complaint_business_item','1','互联网销售业务',NULL,'1',NULL,NULL,'投诉业务类别',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_complaint_business_item','3','互联网销售业务',NULL,'1',NULL,NULL,'投诉业务类别',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_complaint_business_item','5','互联网销售业务',NULL,'1',NULL,NULL,'投诉业务类别',3,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_complaint_business_item','6','互联网销售业务',NULL,'1',NULL,NULL,'投诉业务类别',4,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_complaint_business_item','7','互联网销售业务',NULL,'1',NULL,NULL,'投诉业务类别',5,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_complaint_business_item','8','互联网销售业务',NULL,'1',NULL,NULL,'投诉业务类别',6,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_complaint_business_item','9','互联网销售业务',NULL,'1',NULL,NULL,'投诉业务类别',7,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_class_complaint','01','时效性',NULL,'1',NULL,NULL,'投诉质检分类',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_class_complaint','02','准确性',NULL,'1',NULL,NULL,'投诉质检分类',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_class_complaint','03','真实性',NULL,'1',NULL,NULL,'投诉质检分类',3,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_inspection_item_complaint','01','投诉件录入时效',NULL,'1','01','cs_inspection_class_complaint','投诉质检项目',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','02','响应时间',NULL,'1','01','cs_inspection_class_complaint','投诉质检项目',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','03','根因改善闭环时效',NULL,'1','01','cs_inspection_class_complaint','投诉质检项目',3,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','04','受理渠道',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',4,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','05','投诉分类',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',5,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','06','监管计件',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',6,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','07','监管撤诉状态',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',7,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','08','投诉原因',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',8,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','09','客户反馈',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',9,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','10','投诉损失',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',10,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_inspection_item_complaint','11','投诉是否成立',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',11,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','12','投诉根因部门',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',12,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','13','根因改善',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',13,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','14','致诉根因',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',14,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','15','处理结果',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',15,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','16','附件完整性',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',16,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','17','征求处理意见',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',17,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','18','处理意见',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',18,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','19','行协调或外部鉴定状态',NULL,'1','02','cs_inspection_class_complaint','投诉质检项目',19,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_complaint','20','案件真实性',NULL,'1','03','cs_inspection_class_complaint','投诉质检项目',20,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_inspection_item_demand','21','是否时效内响应客户',NULL,'1',NULL,NULL,'信息需求质检项目',21,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_demand','22','是否符合短信结案规则',NULL,'1',NULL,NULL,'信息需求质检项目',22,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_demand','23','是否电话通知客户',NULL,'1',NULL,NULL,'信息需求质检项目',23,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_demand','24','是否满足客户诉求',NULL,'1',NULL,NULL,'信息需求质检项目',24,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_demand','25','是否及时升级投诉',NULL,'1',NULL,NULL,'信息需求质检项目',25,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_demand','26','是否规范记录',NULL,'1',NULL,NULL,'信息需求质检项目',26,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_inspection_item_demand','27','是否有其他错误',NULL,'1',NULL,NULL,'信息需求质检项目',27,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','07','金融旗舰店-移动端',NULL,'1','1',NULL,'投保来源',8,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','08','寿险绑定',NULL,'1','6',NULL,'投保来源',22,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','10199','员福e生保H5',NULL,'1','6',NULL,'投保来源',23,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_insurance_source','104','天津快保科技',NULL,'1','1',NULL,'投保来源',10,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','105','北京众信易保科技有限公司',NULL,'1','1',NULL,'投保来源',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','108','百度移动端',NULL,'1','1',NULL,'投保来源',4,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','113','北京盈保倍信息科技有限公司',NULL,'1','1',NULL,'投保来源',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','13','金融旗舰店',NULL,'1','1',NULL,'投保来源',11,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','213','平安健康APP',NULL,'1','6',NULL,'投保来源',27,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','218','平安健康大使',NULL,'1','5',NULL,'投保来源',20,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','26','平安银行信用卡中心',NULL,'1','3',NULL,'投保来源',17,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','329','酷保云',NULL,'1','1',NULL,'投保来源',9,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','40','平安银行广州财富',NULL,'1','3',NULL,'投保来源',13,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_insurance_source','42','百度',NULL,'1','1',NULL,'投保来源',3,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','43','平安银行网络金融',NULL,'1','3',NULL,'投保来源',19,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','437','平安银行零售',NULL,'1','3',NULL,'投保来源',14,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','538','蚂蚁保险平台',NULL,'1','1',NULL,'投保来源',7,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','577','产险电销张江销售中心',NULL,'1','6',NULL,'投保来源',29,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','579','产险电销天津销售中心',NULL,'1','6',NULL,'投保来源',30,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','600','蚂蚁金融',NULL,'1','1',NULL,'投保来源',6,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','63','平安银行上海社区金融',NULL,'1','3',NULL,'投保来源',18,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','630','平安健康APP_QZ',NULL,'1','6',NULL,'投保来源',24,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','764','正新易保科技',NULL,'1','1',NULL,'投保来源',5,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_insurance_source','773','平安银行汽融',NULL,'1','3',NULL,'投保来源',12,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','790','鑫山保险（跳转）',NULL,'1','6',NULL,'投保来源',25,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','920','E保无忧（医保卡）',NULL,'1','6',NULL,'投保来源',26,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','929','平安银行网络金融（对接）',NULL,'1','3',NULL,'投保来源',16,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','952','产健共保借款人意外险',NULL,'1','6',NULL,'投保来源',31,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','986','平安健康app（医保）',NULL,'1','6',NULL,'投保来源',28,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','987','平安健康大使（医保）',NULL,'1','5',NULL,'投保来源',21,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_insurance_source','99','平安银行杭州财富',NULL,'1','3',NULL,'投保来源',15,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','18','电话服务',NULL,'1',NULL,NULL,'一级投诉原因',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','22','保全服务',NULL,'1',NULL,NULL,'一级投诉原因',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level1','23','承保服务',NULL,'1',NULL,NULL,'一级投诉原因',3,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','27','其他',NULL,'1',NULL,NULL,'一级投诉原因',4,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','28','产品设计',NULL,'1',NULL,NULL,'一级投诉原因',5,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','29','重新投保服务',NULL,'1',NULL,NULL,'一级投诉原因',6,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','31','增值服务',NULL,'1',NULL,NULL,'一级投诉原因',7,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','34','健康管理服务',NULL,'1',NULL,NULL,'一级投诉原因',8,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','35','医疗网络服务',NULL,'1',NULL,NULL,'一级投诉原因',9,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','36','网络平台服务',NULL,'1',NULL,NULL,'一级投诉原因',10,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','97','理赔服务',NULL,'1',NULL,NULL,'一级投诉原因',11,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level1','99','销售服务',NULL,'1',NULL,NULL,'一级投诉原因',12,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level2','01','保险责任',NULL,'1','97',NULL,'二级投诉原因',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','02','不实告知',NULL,'1','97',NULL,'二级投诉原因',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','03','理赔时效',NULL,'1','97',NULL,'二级投诉原因',3,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','04','材料及流程',NULL,'1','97',NULL,'二级投诉原因',4,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','05','服务态度',NULL,'1','97',NULL,'二级投诉原因',5,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','06','赔款转账',NULL,'1','97',NULL,'二级投诉原因',6,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','07','销售通知',NULL,'1','99',NULL,'二级投诉原因',7,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','08','售前服务',NULL,'1','99',NULL,'二级投诉原因',8,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','09','更换业务员',NULL,'1','99',NULL,'二级投诉原因',9,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','10','投保渠道',NULL,'1','99',NULL,'二级投诉原因',10,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level2','11','佣金争议',NULL,'1','99',NULL,'二级投诉原因',11,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','12','核保争议',NULL,'1','23',NULL,'二级投诉原因',12,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','13','未收到合同、发票等材料',NULL,'1','23',NULL,'二级投诉原因',13,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','14','投保信息无校验',NULL,'1','23',NULL,'二级投诉原因',14,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','15','保单查询',NULL,'1','23',NULL,'二级投诉原因',15,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','16','新保扣费争议',NULL,'1','23',NULL,'二级投诉原因',16,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','17','重新投保扣费争议',NULL,'1','29',NULL,'二级投诉原因',17,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','18','重新投保、转保通知',NULL,'1','29',NULL,'二级投诉原因',18,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','19','重新投保无锁控',NULL,'1','29',NULL,'二级投诉原因',19,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','20','通知争议',NULL,'1','22',NULL,'二级投诉原因',20,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level2','21','材料及流程',NULL,'1','22',NULL,'二级投诉原因',21,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','22','时效争议',NULL,'1','22',NULL,'二级投诉原因',22,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','23','退保争议',NULL,'1','22',NULL,'二级投诉原因',23,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','24','积分同步',NULL,'1','34',NULL,'二级投诉原因',24,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','25','积分兑换',NULL,'1','34',NULL,'二级投诉原因',25,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','26','账户修改',NULL,'1','34',NULL,'二级投诉原因',26,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','27','费率争议',NULL,'1','28',NULL,'二级投诉原因',27,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','28','条款争议',NULL,'1','28',NULL,'二级投诉原因',28,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','29','转保争议',NULL,'1','28',NULL,'二级投诉原因',29,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','30','服务态度',NULL,'1','35',NULL,'二级投诉原因',30,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level2','31','就医流程',NULL,'1','35',NULL,'二级投诉原因',31,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','32','绿通服务',NULL,'1','35',NULL,'二级投诉原因',32,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','33','救援服务',NULL,'1','35',NULL,'二级投诉原因',33,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','34','合作医院',NULL,'1','35',NULL,'二级投诉原因',34,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','35','预约争议',NULL,'1','18',NULL,'二级投诉原因',35,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','36','回访争议',NULL,'1','18',NULL,'二级投诉原因',36,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','37','人员服务',NULL,'1','18',NULL,'二级投诉原因',37,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','38','信息同步争议',NULL,'1','36',NULL,'二级投诉原因',38,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','39','平台活动',NULL,'1','36',NULL,'二级投诉原因',39,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','40','账户注册',NULL,'1','36',NULL,'二级投诉原因',40,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level2','41','账户解绑',NULL,'1','36',NULL,'二级投诉原因',41,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','42','佣金争议',NULL,'1','36',NULL,'二级投诉原因',42,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','43','时效争议',NULL,'1','31',NULL,'二级投诉原因',43,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','44','方式争议',NULL,'1','31',NULL,'二级投诉原因',44,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level2','45','其他',NULL,'1','27',NULL,'二级投诉原因',45,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','01','医院不符争议',NULL,'1','01',NULL,'三级投诉原因',5,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','02','疾病责任争议',NULL,'1','01',NULL,'三级投诉原因',6,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','03','免赔额争议',NULL,'1','01',NULL,'三级投诉原因',7,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','04','药物/治疗手段/门诊争议',NULL,'1','01',NULL,'三级投诉原因',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','05','赔付比例争议',NULL,'1','01',NULL,'三级投诉原因',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level3','06','预授权结论争议',NULL,'1','01',NULL,'三级投诉原因',3,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','07','其他责任争议',NULL,'1','01',NULL,'三级投诉原因',4,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','08','-',NULL,'1','02',NULL,'三级投诉原因',8,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','09','调查时效',NULL,'1','03',NULL,'三级投诉原因',10,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','10','审核时效',NULL,'1','03',NULL,'三级投诉原因',9,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','11','协谈时效',NULL,'1','03',NULL,'三级投诉原因',11,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','12','录入时效',NULL,'1','03',NULL,'三级投诉原因',12,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','13','预授权时效',NULL,'1','03',NULL,'三级投诉原因',13,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','14','材料提交争议',NULL,'1','04',NULL,'三级投诉原因',16,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','15','发票争议',NULL,'1','04',NULL,'三级投诉原因',15,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level3','16','原材料退回',NULL,'1','04',NULL,'三级投诉原因',14,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','17','材料缺失争议',NULL,'1','04',NULL,'三级投诉原因',17,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','18','理赔通知',NULL,'1','04',NULL,'三级投诉原因',18,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','19','-',NULL,'1','05',NULL,'三级投诉原因',19,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','20','转账时效',NULL,'1','06',NULL,'三级投诉原因',20,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','21','转账失败',NULL,'1','06',NULL,'三级投诉原因',21,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','22','支付形式',NULL,'1','06',NULL,'三级投诉原因',22,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','23','-',NULL,'1','07',NULL,'三级投诉原因',23,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','24','说明义务争议',NULL,'1','08',NULL,'三级投诉原因',24,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','25','合同/函件签订',NULL,'1','08',NULL,'三级投诉原因',25,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level3','26','-',NULL,'1','09',NULL,'三级投诉原因',26,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','27','-',NULL,'1','10',NULL,'三级投诉原因',27,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','28','-',NULL,'1','11',NULL,'三级投诉原因',28,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','29','团核结论',NULL,'1','12',NULL,'三级投诉原因',30,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','30','团核时效',NULL,'1','12',NULL,'三级投诉原因',29,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','31','个核结论',NULL,'1','12',NULL,'三级投诉原因',31,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','32','个核时效',NULL,'1','12',NULL,'三级投诉原因',33,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','33','其他核保争议',NULL,'1','12',NULL,'三级投诉原因',32,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','34','-',NULL,'1','13',NULL,'三级投诉原因',34,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','35','-',NULL,'1','14',NULL,'三级投诉原因',35,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level3','36','-',NULL,'1','15',NULL,'三级投诉原因',36,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','37','新保未扣费',NULL,'1','16',NULL,'三级投诉原因',37,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','38','新保重复扣费',NULL,'1','16',NULL,'三级投诉原因',38,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','39','新保承保时效',NULL,'1','16',NULL,'三级投诉原因',39,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','40','重新投保未扣费',NULL,'1','17',NULL,'三级投诉原因',40,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','41','重新投保重复扣费',NULL,'1','17',NULL,'三级投诉原因',41,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','42','重新投保绑卡争议',NULL,'1','17',NULL,'三级投诉原因',43,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','43','重新投保承保时效',NULL,'1','17',NULL,'三级投诉原因',42,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','44','-',NULL,'1','18',NULL,'三级投诉原因',44,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','45','-',NULL,'1','19',NULL,'三级投诉原因',45,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level3','46','-',NULL,'1','20',NULL,'三级投诉原因',46,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','47','-',NULL,'1','21',NULL,'三级投诉原因',47,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','48','-',NULL,'1','22',NULL,'三级投诉原因',48,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','49','退保时效差',NULL,'1','23',NULL,'三级投诉原因',50,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','50','未全额退保',NULL,'1','23',NULL,'三级投诉原因',49,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','51','退保手续、流程',NULL,'1','23',NULL,'三级投诉原因',51,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','52','-',NULL,'1','24',NULL,'三级投诉原因',52,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','53','-',NULL,'1','25',NULL,'三级投诉原因',53,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','54','-',NULL,'1','26',NULL,'三级投诉原因',54,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','55','-',NULL,'1','27',NULL,'三级投诉原因',55,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level3','56','-',NULL,'1','28',NULL,'三级投诉原因',56,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','57','-',NULL,'1','29',NULL,'三级投诉原因',57,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','58','驻点人员',NULL,'1','30',NULL,'三级投诉原因',58,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','59','医务人员',NULL,'1','30',NULL,'三级投诉原因',59,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','60','-',NULL,'1','31',NULL,'三级投诉原因',60,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','61','-',NULL,'1','32',NULL,'三级投诉原因',61,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','62','-',NULL,'1','33',NULL,'三级投诉原因',62,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','63','-',NULL,'1','34',NULL,'三级投诉原因',63,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','64','预约时效',NULL,'1','35',NULL,'三级投诉原因',64,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','65','预约错误',NULL,'1','35',NULL,'三级投诉原因',65,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level3','66','-',NULL,'1','36',NULL,'三级投诉原因',66,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','67','-',NULL,'1','37',NULL,'三级投诉原因',67,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','68','-',NULL,'1','38',NULL,'三级投诉原因',68,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','69','积分兑换',NULL,'1','39',NULL,'三级投诉原因',69,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','70','自营活动',NULL,'1','39',NULL,'三级投诉原因',70,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','71','-',NULL,'1','40',NULL,'三级投诉原因',71,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','72','-',NULL,'1','41',NULL,'三级投诉原因',72,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','73','-',NULL,'1','42',NULL,'三级投诉原因',73,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','74','-',NULL,'1','43',NULL,'三级投诉原因',74,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_reason_level3','75','-',NULL,'1','44',NULL,'三级投诉原因',75,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_reason_level3','76','其他',NULL,'1','45',NULL,'三级投诉原因',76,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_service_item','01','增值服务类',NULL,'1',NULL,NULL,'服务项目',1,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_service_item','02','理赔类',NULL,'1',NULL,NULL,'服务项目',2,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_service_item','03','其他',NULL,'1',NULL,NULL,'服务项目',3,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_service_item','04','VIP门诊预约',NULL,'1',NULL,NULL,'服务项目',4,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_service_item','05','特殊门诊直接结算服务',NULL,'1',NULL,NULL,'服务项目',5,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_service_item','06','门诊直接结算',NULL,'1',NULL,NULL,'服务项目',6,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_service_item','07','住院直接结算',NULL,'1',NULL,NULL,'服务项目',7,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_service_item','08','门诊中医治疗直接结算',NULL,'1',NULL,NULL,'服务项目',8,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
,('cs_service_item','B00006','投诉',NULL,'1',NULL,NULL,'服务项目',9,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;
INSERT INTO `sn-cs`.code_dict (code_type,code,code_name,code_alias,status,parent_code,parent_code_type,remarks,order_no,created_by,created_time,updated_by,updated_time) VALUES
('cs_service_item','B00034','根因改善',NULL,'1',NULL,NULL,'服务项目',10,'admin','2021-02-09 09:36:00','admin','2021-02-09 09:36:00')
;


delete from claimmain.sys_dict_type where dict_type in (
'cs_action_type',
'cs_appeal_flag',
'cs_attachment_type',
'cs_business_type',
'cs_cancle_reason',
'cs_channel',
'cs_classify_level1',
'cs_collaborative_state',
'cs_communication_language',
'cs_complaint_item',
'cs_confirm_status',
'cs_consultation_type',
'cs_demandAcceptVo',
'cs_demand_item',
'cs_direct_settlement',
'cs_drop_status',
'cs_eidt_reason',
'cs_end_case',
'cs_feedback_type',
'cs_handle_state',
'cs_identity',
'cs_inspection_result',
'cs_inspection_state',
'cs_link_circ',
'cs_link_code',
'cs_marketingchannel_code',
'cs_mediation_appraisal',
'cs_opinion_handle',
'cs_order_state',
'cs_organization',
'cs_priority',
'cs_question_circ',
'cs_reason_level1',
'cs_relation',
'cs_reservation_item',
'cs_risk_type',
'cs_service_item',
'cs_sex',
'cs_time_unit',
'cs_value',
'cs_vip_flag',
'cs_whether_flag'
);


INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('操作类型', 'cs_action_type', '0', 'admin', '2021-03-02 14:38:03', '', NULL, '客服操作类型编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('是否申诉', 'cs_appeal_flag', '0', 'admin', '2021-03-12 10:38:00', 'admin', '2021-03-12 10:38:26', NULL);
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('附件类型', 'cs_attachment_type', '0', 'admin', '2021-02-26 19:15:07', 'admin', '2021-02-26 19:15:55', '客服附件类型编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('业务类型', 'cs_business_type', '0', 'admin', '2021-02-09 15:17:35', '', NULL, '客服业务类型编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('取消原因', 'cs_cancle_reason', '0', 'admin', '2021-02-27 09:39:24', '', NULL, '客服取消原因编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('受理渠道', 'cs_channel', '0', 'admin', '2021-01-24 14:16:22', 'admin', '2021-01-26 11:45:43', '客服受理渠道编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('一级投诉分类', 'cs_classify_level1', '0', 'admin', '2021-03-05 09:48:56', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('协办状态', 'cs_collaborative_state', '0', 'admin', '2021-02-27 12:06:31', '', NULL, '客服协办状态编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('沟通语言', 'cs_communication_language', '0', 'admin', '2021-01-24 19:03:52', '', NULL, '客服沟通语言编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('投诉服务项目', 'cs_complaint_item', '0', 'admin', '2021-02-27 09:45:18', '', NULL, '客服投诉服务项目编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('差错确认状态', 'cs_confirm_status', '0', 'admin', '2021-02-27 12:08:05', '', NULL, '客服差错确认状态编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('就诊类型', 'cs_consultation_type', '0', 'admin', '2021-03-05 21:26:57', '', NULL, '客服就诊类型编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('信息需求受理对象属性', 'cs_demandAcceptVo', '0', 'admin', '2021-03-02 14:45:40', 'admin', '2021-03-07 18:13:03', '客服信息需求受理对象属性编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('信息需求服务项目', 'cs_demand_item', '0', 'admin', '2021-01-24 14:13:53', '', NULL, '客服信息需求服务项目编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('门诊直结结算服务项目', 'cs_direct_settlement', '0', 'admin', '2021-03-05 21:24:08', '', NULL, '客服门诊直结结算服务项目编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('撤诉状态', 'cs_drop_status', '0', 'admin', '2021-02-27 10:00:37', '', NULL, '客服撤诉状态编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('修改原因', 'cs_eidt_reason', '0', 'admin', '2021-02-26 19:20:24', '', NULL, '客服修改原因编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('结案类型', 'cs_end_case', '0', 'admin', '2021-02-26 19:02:13', '', NULL, '客服结案类型编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('客户反馈', 'cs_feedback_type', '0', 'admin', '2021-02-08 10:03:41', 'admin', '2021-02-08 10:03:59', '客服客户反馈编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('业务处理情况', 'cs_handle_state', '0', 'admin', '2021-02-26 19:10:54', '', NULL, '客服业务处理情况编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('投诉人身份', 'cs_identity', '0', 'admin', '2021-02-27 09:46:58', '', NULL, '客服投诉人身份编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('质检结果', 'cs_inspection_result', '0', 'admin', '2021-01-24 22:08:31', '', NULL, '客服质检结果编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('质检状态', 'cs_inspection_state', '0', 'admin', '2021-01-24 22:02:04', 'admin', '2021-02-18 10:40:10', '客服质检状态编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('投诉环节', 'cs_link_circ', '0', 'admin', '2021-02-27 11:54:39', '', NULL, '客服投诉环节（报保监）编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('流程类型', 'cs_link_code', '0', 'admin', '2021-03-24 11:54:57', 'admin', '2021-03-24 11:55:26', '流程类型列表');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('营销渠道', 'cs_marketingchannel_code', '0', 'admin', '2021-03-26 09:15:51', 'admin', '2021-03-26 09:22:01', '营销渠道');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('行协调解或外部鉴定状态', 'cs_mediation_appraisal', '0', 'admin', '2021-02-27 12:01:35', '', NULL, '客服行协调解或外部鉴定状态编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('意见处理状态', 'cs_opinion_handle', '0', 'admin', '2021-02-28 21:50:19', 'admin', '2021-02-28 21:50:42', '客服意见处理状态编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('工单状态', 'cs_order_state', '0', 'admin', '2021-01-24 14:08:43', 'admin', '2021-02-26 19:09:44', '客服工单状态编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('出单机构', 'cs_organization', '0', 'admin', '2021-01-28 10:52:48', '', NULL, '客服出单机构编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('优先级', 'cs_priority', '0', 'admin', '2021-01-24 14:02:49', 'admin', '2021-01-24 14:05:40', '客户优先级编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('投诉问题', 'cs_question_circ', '0', 'admin', '2021-02-27 11:58:39', '', NULL, '客服投诉问题（报保监）编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('一级投诉原因', 'cs_reason_level1', '0', 'admin', '2021-03-05 09:50:39', '', NULL, '一级投诉原因');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('人员关系', 'cs_relation', '0', 'admin', '2021-02-18 18:11:34', '', NULL, '客服人员关系编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('预约服务项目', 'cs_reservation_item', '0', 'admin', '2021-03-04 16:28:58', '', NULL, '预约服务项目');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('险种类型', 'cs_risk_type', '0', 'admin', '2021-02-27 12:03:00', '', NULL, '客服险种类型编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('服务项目', 'cs_service_item', '0', 'admin', '2021-01-24 13:51:51', '', NULL, '客服服务项目编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('性别', 'cs_sex', '0', 'admin', '2021-01-24 14:23:21', '', NULL, '客服性别编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('时间单位', 'cs_time_unit', '0', 'admin', '2021-03-05 21:30:24', '', NULL, '客服时间单位编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('是否存在差错', 'cs_value', '0', 'admin', '2021-03-12 10:19:29', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('VIP标识', 'cs_vip_flag', '0', 'admin', '2021-01-24 14:06:48', '', NULL, '客服VIP标识编码');
INSERT INTO claimmain.sys_dict_type
(dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
VALUES('是否', 'cs_whether_flag', '0', 'admin', '2021-02-09 16:25:42', 'admin', '2021-02-26 19:00:57', '客服是否编码');


delete from claimmain.sys_dict_data where dict_type in (
'cs_action_type',
'cs_appeal_flag',
'cs_attachment_type',
'cs_business_type',
'cs_cancle_reason',
'cs_channel',
'cs_classify_level1',
'cs_collaborative_state',
'cs_communication_language',
'cs_complaint_item',
'cs_confirm_status',
'cs_consultation_type',
'cs_demandAcceptVo',
'cs_demand_item',
'cs_direct_settlement',
'cs_drop_status',
'cs_eidt_reason',
'cs_end_case',
'cs_feedback_type',
'cs_handle_state',
'cs_identity',
'cs_inspection_result',
'cs_inspection_state',
'cs_link_circ',
'cs_link_code',
'cs_marketingchannel_code',
'cs_mediation_appraisal',
'cs_opinion_handle',
'cs_order_state',
'cs_organization',
'cs_priority',
'cs_question_circ',
'cs_reason_level1',
'cs_relation',
'cs_reservation_item',
'cs_risk_type',
'cs_service_item',
'cs_sex',
'cs_time_unit',
'cs_value',
'cs_vip_flag',
'cs_whether_flag'
);

INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '增值服务类', '01', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 13:56:10', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '理赔类', '02', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 13:57:31', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '其他', '03', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 13:57:53', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, 'VIP门诊预约', '04', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 13:58:31', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '特殊门诊直接结算服务', '05', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 13:59:11', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '门诊直接结算', '06', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 13:59:34', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(7, '住院直接结算', '07', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 13:59:51', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(8, '门诊中医治疗直接结算', '08', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:00:23', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(9, '投诉', 'B00006', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:01:01', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(10, '根因改善', 'B00034', 'cs_service_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:01:32', '', NULL, '客服服务项目类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '加急', '01', 'cs_priority', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:04:24', '', NULL, '客户优先级编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '普通', '02', 'cs_priority', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:04:43', '', NULL, '客户优先级编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '是', '01', 'cs_vip_flag', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:07:20', '', NULL, '客服VIP标识编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '否', '02', 'cs_vip_flag', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:07:37', '', NULL, '客服VIP标识编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '待处理', '01', 'cs_order_state', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:09:19', 'admin', '2021-01-24 14:11:40', '客服处理状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '处理中', '02', 'cs_order_state', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:09:38', '', NULL, '客服处理状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '已处理', '03', 'cs_order_state', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:10:11', '', NULL, '客服处理状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '已完成', '04', 'cs_order_state', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:10:42', '', NULL, '客服处理状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '已取消', '05', 'cs_order_state', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:11:23', '', NULL, '客服处理状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '增值服务类', '01', 'cs_demand_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:14:28', '', NULL, '客服信息需求服务项目编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '理赔类', '02', 'cs_demand_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:14:47', '', NULL, '客服信息需求服务项目编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '其他', '03', 'cs_demand_item', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:15:06', '', NULL, '客服信息需求服务项目编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '电话中心', '01', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:17:02', 'admin', '2021-01-26 11:46:09', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '在线客服', '02', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:17:21', 'admin', '2021-01-26 11:46:15', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '邮箱', '03', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:17:48', 'admin', '2021-01-26 11:46:19', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '网站', '04', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:18:51', 'admin', '2021-01-26 11:46:25', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '柜面', '05', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:19:07', 'admin', '2021-01-26 11:46:30', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '寿险', '06', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:19:25', 'admin', '2021-01-26 11:46:35', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(7, '微信', '07', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:19:43', 'admin', '2021-01-26 11:46:46', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(8, '监管', '08', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:20:07', 'admin', '2021-01-26 11:47:04', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(9, '媒体', '09', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:20:24', 'admin', '2021-01-26 11:47:11', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(10, 'APP', '10', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:20:43', 'admin', '2021-01-26 11:47:17', '客服受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(11, 'TPA系统', '11', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:20:59', '', NULL, '客服信息需求受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(12, '信函', '12', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:21:14', '', NULL, '客服信息需求受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(13, '来访', '13', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:21:26', '', NULL, '客服信息需求受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(14, '官网', '14', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:21:42', '', NULL, '客服信息需求受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(15, '其他', '15', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:22:07', '', NULL, '客服信息需求受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(16, '电话', '16', 'cs_channel', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:22:19', '', NULL, '客服信息需求受理渠道编码编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '男', '01', 'cs_sex', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:23:47', '', NULL, '客服性别编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '女', '02', 'cs_sex', NULL, NULL, 'N', '0', 'admin', '2021-01-24 14:24:06', '', NULL, '客服性别编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '中文', '01', 'cs_communication_language', NULL, NULL, 'N', '0', 'admin', '2021-01-24 19:04:33', 'admin', '2021-01-24 19:05:20', '客服沟通语言编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '英文', '02', 'cs_communication_language', NULL, NULL, 'N', '0', 'admin', '2021-01-24 19:05:11', '', NULL, '客服沟通语言编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '中英文', '03', 'cs_communication_language', NULL, NULL, 'N', '0', 'admin', '2021-01-24 19:05:41', '', NULL, '客服沟通语言编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '待质检', '01', 'cs_inspection_state', NULL, NULL, 'N', '0', 'admin', '2021-01-24 22:02:43', '', NULL, '客服质检状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '质检中', '02', 'cs_inspection_state', NULL, NULL, 'N', '0', 'admin', '2021-01-24 22:03:02', 'admin', '2021-01-24 22:03:16', '客服质检状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '质检完成', '03', 'cs_inspection_state', NULL, NULL, 'N', '0', 'admin', '2021-01-24 22:03:30', '', NULL, '客服质检状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '合格', '01', 'cs_inspection_result', NULL, NULL, 'N', '0', 'admin', '2021-01-24 22:09:12', 'admin', '2021-01-24 22:09:18', '客服质检结果编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '不合格', '02', 'cs_inspection_result', NULL, NULL, 'N', '0', 'admin', '2021-01-24 22:09:31', '', NULL, '客服质检结果编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '上海分公司本部', '0000001', 'cs_organization', NULL, NULL, 'N', '0', 'admin', '2021-01-28 10:54:31', '', NULL, '客服出单机构编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '上海分公司', '00000011', 'cs_organization', NULL, NULL, 'N', '0', 'admin', '2021-01-28 10:55:12', '', NULL, '客服出单机构编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '满意', '01', 'cs_feedback_type', NULL, NULL, 'N', '0', 'admin', '2021-02-08 10:04:52', 'admin', '2021-02-08 10:06:11', '客服客户反馈编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '接受', '02', 'cs_feedback_type', NULL, NULL, 'N', '0', 'admin', '2021-02-08 10:06:00', 'admin', '2021-02-08 10:06:22', '客服客户反馈编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '不接受', '03', 'cs_feedback_type', NULL, NULL, 'N', '0', 'admin', '2021-02-08 10:06:42', '', NULL, '客服客户反馈编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '信息需求', '01', 'cs_business_type', NULL, NULL, 'N', '0', 'admin', '2021-02-09 15:18:06', '', NULL, '客服业务类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '预约', '02', 'cs_business_type', NULL, NULL, 'N', '0', 'admin', '2021-02-09 15:18:22', '', NULL, '客服业务类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '投诉', '03', 'cs_business_type', NULL, NULL, 'N', '0', 'admin', '2021-02-09 15:18:41', '', NULL, '客服业务类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '是', '01', 'cs_whether_flag', NULL, NULL, 'N', '0', 'admin', '2021-02-09 16:27:12', '', NULL, '投诉是否成立');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '否', '02', 'cs_whether_flag', NULL, NULL, 'N', '0', 'admin', '2021-02-09 16:27:28', 'admin', '2021-02-09 16:31:30', '投诉是否成立');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '本人', '01', 'cs_relation', NULL, NULL, 'N', '0', 'admin', '2021-02-18 18:12:16', '', NULL, '客服人员关系编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '父母', '02', 'cs_relation', NULL, NULL, 'N', '0', 'admin', '2021-02-18 18:12:34', '', NULL, '客服人员关系编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '子女', '03', 'cs_relation', NULL, NULL, 'N', '0', 'admin', '2021-02-18 18:12:50', '', NULL, '客服人员关系编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '配偶', '04', 'cs_relation', NULL, NULL, 'N', '0', 'admin', '2021-02-18 18:13:06', '', NULL, '客服人员关系编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '朋友', '05', 'cs_relation', NULL, NULL, 'N', '0', 'admin', '2021-02-18 18:13:25', '', NULL, '客服人员关系编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '医院', '06', 'cs_relation', NULL, NULL, 'N', '0', 'admin', '2021-02-18 18:14:07', '', NULL, '客服人员关系编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(7, '代理人', '07', 'cs_relation', NULL, NULL, 'N', '0', 'admin', '2021-02-18 18:14:30', '', NULL, '客服人员关系编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(8, '雇佣', '08', 'cs_relation', NULL, NULL, 'N', '0', 'admin', '2021-02-18 18:14:51', '', NULL, '客服人员关系编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(9, '其他', '09', 'cs_relation', NULL, NULL, 'N', '0', 'admin', '2021-02-18 18:15:04', '', NULL, '客服人员关系编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '正常结案', '01', 'cs_end_case', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:03:10', 'admin', '2021-02-26 19:03:17', '客服结案类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '通融处理', '02', 'cs_end_case', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:05:57', '', NULL, '客服结案类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '安抚处理', '03', 'cs_end_case', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:06:24', '', NULL, '客服结案类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '成功', '01', 'cs_handle_state', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:11:44', 'admin', '2021-02-27 09:41:32', '客服业务处理情况编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '失败', '02', 'cs_handle_state', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:12:23', 'admin', '2021-02-27 09:41:36', '客服业务处理情况编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '担保函', '01', 'cs_attachment_type', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:17:23', 'admin', '2021-02-27 09:42:04', '客服附件类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '邮件', '02', 'cs_attachment_type', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:18:16', 'admin', '2021-02-27 09:42:08', '客服附件类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '其他', '03', 'cs_attachment_type', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:18:35', 'admin', '2021-02-27 09:42:11', '客服附件类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '客户补充资料', '04', 'cs_attachment_type', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:18:55', 'admin', '2021-02-27 09:42:14', '客服附件类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '审批附件', '05', 'cs_attachment_type', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:19:21', 'admin', '2021-02-27 09:42:18', '客服附件类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, 'GCC附件', '06', 'cs_attachment_type', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:19:41', 'admin', '2021-02-27 09:42:28', '客服附件类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '客户申请变动', '01', 'cs_eidt_reason', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:21:01', 'admin', '2021-02-27 09:42:45', '客服修改原因编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '操作失误', '02', 'cs_eidt_reason', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:21:33', 'admin', '2021-02-27 09:42:48', '客服修改原因编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '其他原因', '03', 'cs_eidt_reason', NULL, NULL, 'N', '0', 'admin', '2021-02-26 19:21:54', 'admin', '2021-02-27 09:42:52', '客服修改原因编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '客户申请变动', '01', 'cs_cancle_reason', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:40:46', 'admin', '2021-02-27 09:43:57', '客服取消原因编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '操作失误', '02', 'cs_cancle_reason', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:44:13', '', NULL, '客服取消原因编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '其他原因', '03', 'cs_cancle_reason', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:44:33', '', NULL, '客服取消原因编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '投诉', 'B00006', 'cs_complaint_item', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:45:51', '', NULL, '客服投诉服务项目编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '根因改善', 'B00034', 'cs_complaint_item', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:46:06', '', NULL, '客服投诉服务项目编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '投保人', '01', 'cs_identity', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:47:20', '', NULL, '客服投诉人身份编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '被保险人', '02', 'cs_identity', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:47:35', '', NULL, '客服投诉人身份编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '亲属', '03', 'cs_identity', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:47:55', '', NULL, '客服投诉人身份编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '律师', '04', 'cs_identity', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:48:29', '', NULL, '客服投诉人身份编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '记者', '05', 'cs_identity', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:48:44', '', NULL, '客服投诉人身份编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '业务员', '06', 'cs_identity', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:49:04', '', NULL, '客服投诉人身份编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(7, '团单HR', '07', 'cs_identity', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:49:23', '', NULL, '客服投诉人身份编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(8, '其他', '08', 'cs_identity', NULL, NULL, 'N', '0', 'admin', '2021-02-27 09:49:45', '', NULL, '客服投诉人身份编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '待撤诉', '01', 'cs_drop_status', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:52:31', '', NULL, '客服撤诉状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '未撤诉', '02', 'cs_drop_status', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:52:51', '', NULL, '客服撤诉状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '已撤诉', '03', 'cs_drop_status', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:53:45', '', NULL, '客服撤诉状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '宣传展业', '01', 'cs_link_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:55:04', '', NULL, '客服投诉环节（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '核保', '02', 'cs_link_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:55:20', '', NULL, '客服投诉环节（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '承保', '03', 'cs_link_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:55:37', '', NULL, '客服投诉环节（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '保全', '04', 'cs_link_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:55:55', '', NULL, '客服投诉环节（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '续期', '05', 'cs_link_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:56:18', '', NULL, '客服投诉环节（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '理赔给付', '06', 'cs_link_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:56:46', '', NULL, '客服投诉环节（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(7, '退保', '07', 'cs_link_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:57:38', '', NULL, '客服投诉环节（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(8, '其他', '08', 'cs_link_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:57:57', '', NULL, '客服投诉环节（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '销售误导', '01', 'cs_question_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:59:01', '', NULL, '客服投诉问题（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '续期服务', '02', 'cs_question_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:59:17', '', NULL, '客服投诉问题（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '通知服务', '03', 'cs_question_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:59:32', '', NULL, '客服投诉问题（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '理赔纠纷', '04', 'cs_question_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 11:59:48', '', NULL, '客服投诉问题（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '服务态度', '05', 'cs_question_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:00:03', '', NULL, '客服投诉问题（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '承保服务', '06', 'cs_question_circ', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:00:19', '', NULL, '客服投诉问题（报保监）编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '成功', '01', 'cs_mediation_appraisal', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:02:05', '', NULL, '客服行协调解或外部鉴定状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '失败', '02', 'cs_mediation_appraisal', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:02:17', '', NULL, '客服行协调解或外部鉴定状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '健康险', '01', 'cs_risk_type', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:03:32', '', NULL, '客服险种类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '意外险', '02', 'cs_risk_type', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:03:56', '', NULL, '客服险种类型编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '协办中', '01', 'cs_collaborative_state', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:06:54', '', NULL, '客服协办状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '已协办', '02', 'cs_collaborative_state', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:07:07', '', NULL, '客服协办状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '协办取消', '03', 'cs_collaborative_state', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:07:29', '', NULL, '客服协办状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '待确认', '01', 'cs_confirm_status', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:08:34', '', NULL, '客服差错确认状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '已确认', '02', 'cs_confirm_status', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:09:02', '', NULL, '客服差错确认状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '无确认', '03', 'cs_confirm_status', NULL, NULL, 'N', '0', 'admin', '2021-02-27 12:09:19', '', NULL, '客服差错确认状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '意见处理等待', '01', 'cs_opinion_handle', NULL, NULL, 'N', '0', 'admin', '2021-02-28 21:51:18', 'admin', '2021-02-28 21:51:37', '客服意见处理状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '意见处理完成', '02', 'cs_opinion_handle', NULL, NULL, 'N', '0', 'admin', '2021-02-28 21:51:32', 'admin', '2021-02-28 21:51:41', '客服意见处理状态编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '新增', '01', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:38:53', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '获取', '02', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:39:09', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '修改', '03', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:39:26', 'admin', '2021-03-25 11:11:38', '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '取消', '04', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:39:47', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '处理', '05', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:40:09', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '受理', '06', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:40:26', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(7, '上传附件', '07', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:40:58', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(8, '提交', '08', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:41:14', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(9, '确定', '09', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:41:37', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(10, '转办', '10', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:42:04', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(11, '协办完成', '11', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:42:16', 'admin', '2021-03-19 15:31:43', '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(12, '暂存', '12', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:42:41', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(13, '发起质检', '13', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:42:59', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '电话中心业务流水号', 'callCenterId', 'cs_accept_vo', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:48:30', '', NULL, '客服受理对象属性编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '工单编号', 'workOrderNo', 'cs_accept_vo', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:48:50', '', NULL, '客服受理对象属性编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '业务类型', 'businessType', 'cs_accept_vo', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:49:17', '', NULL, '客服受理对象属性编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '受理渠道', 'channelCode', 'cs_accept_vo', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:49:59', '', NULL, '客服受理对象属性编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '服务项目', 'itemCode', 'cs_accept_vo', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:50:33', 'admin', '2021-03-02 14:50:40', '客服受理对象属性编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '保单号', 'policyNo', 'cs_accept_vo', NULL, NULL, 'N', '0', 'admin', '2021-03-02 14:51:11', '', NULL, '客服受理对象属性编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(14, '响应', '14', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-04 09:45:09', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, 'VIP门诊预约', '04', 'cs_reservation_item', NULL, NULL, 'N', '0', 'admin', '2021-03-04 16:30:58', 'admin', '2021-03-04 16:32:25', 'VIP门诊预约');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '特殊门诊直接结算服务', '05', 'cs_reservation_item', NULL, NULL, 'N', '0', 'admin', '2021-03-04 16:31:43', '', NULL, '05-特殊门诊直接结算服务');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '门诊直接结算', '06', 'cs_reservation_item', NULL, NULL, 'N', '0', 'admin', '2021-03-04 16:33:25', '', NULL, '06-门诊直接结算');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '住院直接结算', '07', 'cs_reservation_item', NULL, NULL, 'N', '0', 'admin', '2021-03-04 16:33:51', '', NULL, '07-住院直接结算');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '门诊中医治疗直接结算', '08', 'cs_reservation_item', NULL, NULL, 'N', '0', 'admin', '2021-03-04 16:34:07', '', NULL, '08-门诊中医治疗直接结算');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '内部投诉', '01', 'cs_classify_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:49:29', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '外部投诉', '02', 'cs_classify_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:49:48', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '电话服务', '18', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:51:42', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '保全服务', '22', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:52:04', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '承保服务', '23', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:52:30', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '其他', '27', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:52:46', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '产品设计', '28', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:53:06', '', NULL, '28-产品设计');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '重新投保服务', '29', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:53:22', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '增值服务', '31', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:53:38', '', NULL, '31-增值服务');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '健康管理服务', '34', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:53:53', '', NULL, '34-健康管理服务');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '医疗网络服务', '35', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:54:44', '', NULL, '35-医疗网络服务');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '网络平台服务', '36', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:54:59', '', NULL, '36-网络平台服务');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '理赔服务', '97', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:55:15', '', NULL, '97-理赔服务');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '销售服务', '99', 'cs_reason_level1', NULL, NULL, 'N', '0', 'admin', '2021-03-05 09:55:31', '', NULL, '99-销售服务');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '有', '01', 'cs_direct_settlement', NULL, NULL, 'N', '0', 'admin', '2021-03-05 21:24:47', '', NULL, '客服门诊直结结算服务项目编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '没有', '02', 'cs_direct_settlement', NULL, NULL, 'N', '0', 'admin', '2021-03-05 21:24:58', '', NULL, '客服门诊直结结算服务项目编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '门诊', '01', 'cs_consultation_type', NULL, NULL, 'N', '0', 'admin', '2021-03-05 21:27:31', '', NULL, '客服就诊类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '住院', '02', 'cs_consultation_type', NULL, NULL, 'N', '0', 'admin', '2021-03-05 21:27:46', '', NULL, '客服就诊类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '牙科', '03', 'cs_consultation_type', NULL, NULL, 'N', '0', 'admin', '2021-03-05 21:28:08', '', NULL, '客服就诊类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '天', '01', 'cs_time_unit', NULL, NULL, 'N', '0', 'admin', '2021-03-05 21:30:52', '', NULL, '客服时间单位编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '周', '02', 'cs_time_unit', NULL, NULL, 'N', '0', 'admin', '2021-03-05 21:31:13', '', NULL, '客服时间单位编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '月', '03', 'cs_time_unit', NULL, NULL, 'N', '0', 'admin', '2021-03-05 21:31:39', '', NULL, '客服时间单位编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '电话中心业务流水号', 'callCenterId', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:14:07', 'admin', '2021-03-07 18:14:52', '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '工单号', 'workOrderNo', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:14:47', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '业务类型', 'businessType', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:15:31', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '业务-服务项目', 'businessService', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:16:01', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '受理渠道', 'channelCode', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:16:27', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '服务项目', 'itemCode', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:16:50', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(7, '保单号', 'policyNo', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:17:11', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(8, '分单号', 'policyItemNo', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:17:38', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(9, '险种号', 'riskCode', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:17:57', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(10, '被保人客户号', 'insuredNo', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:19:02', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(11, '被保人', 'insuredName', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:20:05', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(12, '投保人客户号', 'holderNo', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:20:24', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(13, '投保人', 'holderName', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:20:47', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(14, '受理人', 'acceptBy', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:21:10', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(15, 'VIP标识', 'vipFlag', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:22:13', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(16, '出单机构', 'organCode', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:22:28', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(17, 'E-MAIL', 'email', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:22:57', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(18, '优先级', 'priorityLevel', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:23:16', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(19, '来电人与被保人关系', 'callRelationBy', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:24:35', 'admin', '2021-03-07 18:27:40', '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(20, '联系人与被保人关系', 'contactsRelationBy', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:28:11', '', NULL, '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(21, '来电人姓名', 'callPerson.name', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:32:37', 'admin', '2021-03-07 18:37:39', '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(22, '来电人性别', 'callPerson.sex', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:35:07', 'admin', '2021-03-07 18:37:44', '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(23, '来电人身份', 'callPerson.identity', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:36:07', 'admin', '2021-03-07 18:37:52', '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(24, '来电人移动电话', 'callPerson.mobilePhone', 'cs_accept_attribute', NULL, NULL, 'N', '0', 'admin', '2021-03-07 18:37:01', 'admin', '2021-03-07 20:46:13', '客服信息需求受理对象属性');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '是', '1', 'cs_value', NULL, NULL, 'N', '0', 'admin', '2021-03-12 10:19:59', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '否', '0', 'cs_value', NULL, NULL, 'N', '0', 'admin', '2021-03-12 10:20:24', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '是', '1', 'cs_appeal_flag', NULL, NULL, 'N', '0', 'admin', '2021-03-12 10:38:44', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '否', '0', 'cs_appeal_flag', NULL, NULL, 'N', '0', 'admin', '2021-03-12 10:38:57', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(15, '发起协办', '15', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-19 15:29:36', 'admin', '2021-03-19 15:30:45', '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(16, '撤销协办', '16', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-19 15:31:26', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '受理渠道', 'channelCode', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 09:59:55', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '保单号', 'policyNo', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:00:06', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '分单号', 'policyItemNo', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:00:16', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '险种号', 'riskCode', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:00:27', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '被保人客户号', 'insuredNo', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:00:38', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '被保人', 'insuredName', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:00:49', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投保人客户号', 'holderNo', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:01:03', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投保人', 'holderName', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:01:14', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '受理人', 'acceptBy', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:01:24', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '受理时间', 'acceptTime', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:01:34', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '修改时间', 'modifyTime', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:01:48', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, 'VIP标识', 'vipFlag', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:01:59', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '出单机构', 'organCode', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:02:08', 'admin', '2021-03-24 11:18:02', NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '状态', 'status', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:02:19', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '处理人', 'modifyBy', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:02:27', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, 'E-MAIL', 'email', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:02:37', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '优先级', 'priorityLevel', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:02:49', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人关系', 'callRelationBy', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:02:58', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人与被保人关系', 'contactsRelationBy', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:03:09', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人编号', 'callPerson.personId', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:14:57', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人性别', 'callPerson.sex', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:15:07', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人身份', 'callPerson.identity', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:15:18', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人移动电话', 'callPerson.mobilePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:15:28', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人移动电话', 'callPerson.mobilePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:15:38', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人固定电话', 'callPerson.linePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:15:48', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人固定电话', 'callPerson.linePhone1', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:15:58', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人家庭电话', 'callPerson.homePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:16:12', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人家庭电话', 'callPerson.homePhone1', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:16:25', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人办公电话', 'callPerson.workPhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:16:35', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人办公电话', 'callPerson.workPhone1', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:16:45', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人语言', 'callPerson.language', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:16:55', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人E-MAIL', 'callPerson.email', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:17:04', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人传真', 'callPerson.fax', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:17:14', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人证件号', 'callPerson.idNumber', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:17:23', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人证件类型', 'callPerson.idType', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:17:31', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人出生日期', 'callPerson.birthday', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:17:41', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人联系地址', 'callPerson.address', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:17:54', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人编号', 'contactsPerson.personId', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:18:04', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人姓名', 'contactsPerson.name', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:18:16', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人性别', 'contactsPerson.sex', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:18:25', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人身份', 'contactsPerson.identity', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:18:38', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人移动电话', 'contactsPerson.mobilePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:18:48', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人固定电话', 'contactsPerson.linePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:18:58', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人固定电话', 'contactsPerson.linePhone1', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:19:08', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人家庭电话', 'contactsPerson.homePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:19:19', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人家庭电话', 'contactsPerson.homePhone1', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:19:32', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人办公电话', 'contactsPerson.workPhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:19:44', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人办公电话', 'contactsPerson.workPhone1', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:19:54', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人语言', 'contactsPerson.language', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:20:04', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人E-MAIL', 'contactsPerson.email', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:20:13', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人传真', 'contactsPerson.fax', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:20:22', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人证件号', 'contactsPerson.idNumber', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:20:30', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人证件类型', 'contactsPerson.idType', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:20:40', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人出生日期', 'contactsPerson.birthday', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:21:00', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人地址', 'contactsPerson.address', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:21:10', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人编号', 'complaintPerson.personId', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:21:19', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人姓名', 'complaintPerson.name', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:21:34', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人身份', 'complaintPerson.identity', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:21:46', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人移动电话', 'complaintPerson.mobilePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:21:57', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人固定电话', 'complaintPerson.linePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:22:07', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人固定电话', 'complaintPerson.linePhone1', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:22:16', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人家庭电话', 'complaintPerson.homePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:22:25', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人家庭电话', 'complaintPerson.homePhone1', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:22:35', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人办公电话', 'complaintPerson.workPhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:22:46', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人办公电话', 'complaintPerson.workPhone1', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:22:56', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人语言', 'complaintPerson.language', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:23:05', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人E-MAIL', 'complaintPerson.email', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:23:17', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人传真', 'complaintPerson.fax', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:23:26', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人证件号', 'complaintPerson.idNumber', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:23:36', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人证件类型', 'complaintPerson.idType', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:23:45', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人出生日期', 'complaintPerson.birthday', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:23:54', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '投诉人联系地址', 'complaintPerson.address', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:24:03', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '是否涉及银行转账', 'bankTransfer', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:24:12', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '开户行', 'bankName', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:24:21', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '开户地', 'bankLocation', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:24:30', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '账号', 'accountNumber', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:24:39', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '户名', 'bankHolder', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:24:59', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '有无附件', 'attachmentFlag', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:25:08', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '业务内容', 'content', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:25:16', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人姓名', 'callName', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:25:26', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人号码', 'callMobilePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:25:35', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人性别', 'contactsSex', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:25:44', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人姓名', 'contactsName', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:25:55', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人语言', 'contactsLanguage', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:26:06', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人电话', 'contactsMobilePhone', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:26:29', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人国家', 'contactsCountry', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:26:37', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人区号', 'contactsQuhao', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:26:48', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人号码', 'contactsNumber', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:26:57', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人分机号', 'contactsSecondNumber', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:27:06', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人国家', 'officeCountry', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:27:14', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人区号', 'officeQuhao', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:27:24', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人号码', 'officeNumber', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:27:34', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '联系人分机号', 'officeSecondNumber', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:27:43', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '修改原因', 'editReason', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:27:51', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '修改说明', 'editRemark', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:28:00', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '电话中心业务流水号', 'callCenterId', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:28:09', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '待处理', '01', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 11:58:06', 'admin', '2021-03-25 18:39:21', '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '处理中', '02', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 11:59:39', '', NULL, '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '已处理', '03', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 12:02:31', '', NULL, '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(4, '已完成', '04', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 12:03:22', '', NULL, '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(5, '已取消', '05', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 12:03:39', 'admin', '2021-03-25 18:39:03', '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(6, '协办中', '06', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 12:03:55', '', NULL, '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(7, '已协办', '07', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 12:04:18', '', NULL, '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(8, '待质检', '08', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 12:04:40', '', NULL, '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(9, '质检中', '09', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 12:05:13', '', NULL, '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(10, '质检完成', '10', 'cs_link_code', NULL, NULL, 'N', '0', 'admin', '2021-03-24 12:05:29', '', NULL, '客服操作流程编码');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(17, '退回修改', '17', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-24 16:57:13', 'admin', '2021-03-24 16:57:38', '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(18, '结案', '18', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-24 16:58:00', 'admin', '2021-03-24 16:58:18', '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '本次疾病/症状起病时间', 'symptomTimes', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-24 10:28:09', '', NULL, NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '来电人姓名', 'callPerson.name', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-23 19:00:17', 'admin', '2021-03-23 19:03:17', NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(19, '差错提交', '19', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-25 11:12:21', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '疾病或体征', 'symptomsSigns', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-23 19:00:17', 'admin', '2021-03-23 19:03:17', NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '预约时间', 'complaintTime', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-23 19:00:17', 'admin', '2021-03-23 19:03:17', NULL);
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(20, '案件复核', '20', 'cs_action_type', NULL, NULL, 'N', '0', 'admin', '2021-03-25 18:55:40', '', NULL, '客服操作类型编码类型');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(1, '银保渠道', '01', 'cs_marketingchannel_code', NULL, NULL, 'N', '0', 'admin', '2021-03-26 09:20:00', '', NULL, '营销渠道码值');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(2, '直销', '02', 'cs_marketingchannel_code', NULL, NULL, 'N', '0', 'admin', '2021-03-26 09:20:35', '', NULL, '营销渠道码值');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(3, '兼业代理', '03', 'cs_marketingchannel_code', NULL, NULL, 'N', '0', 'admin', '2021-03-26 09:20:36', 'admin', '2021-03-26 09:20:54', '营销渠道码值');
INSERT INTO claimmain.sys_dict_data
(dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
VALUES(0, '科室', 'department', 'cs_demandAcceptVo', NULL, NULL, 'N', '0', 'admin', '2021-03-23 18:41:55', '', NULL, NULL);









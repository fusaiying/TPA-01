
alter table claim_case_policy modify ss_flag varchar(2) null comment '社保标记';
alter table claim_case_policy modify org_policy_no varchar(20) null comment '原保单号';
alter table claim_case_policy modify org_policy_item_no varchar(20) null comment '原分单号';


ALTER TABLE claim_batch ADD claim_flag VARCHAR(2) COMMENT '是否仅结算理赔责任';
ALTER TABLE claim_batch ADD notice_day VARCHAR(10) COMMENT '支付通知时间';
alter table  claim_batch  modify `hospital_code` varchar(20)  NULL COMMENT '就诊医院ID';
alter table  claim_batch  modify `batch_total` DECIMAL(16,2)  NULL COMMENT '批次总金额';
alter table  claim_batch  modify `currency` DECIMAL(16,2)  NULL COMMENT '账单币种';
alter table  claim_batch  modify `cont_type` varchar(2)  NULL COMMENT '保单类型';
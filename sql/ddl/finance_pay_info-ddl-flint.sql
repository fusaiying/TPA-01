alter table finance_pay_info add accRecordID VARCHAR(20) null comment '应付记账ID' after pay_fail_reason;

alter table finance_pay_info change accRecordID acc_record_id varchar(255) null comment '应付记账ID';
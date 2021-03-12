alter table claim_case_cal add total_bill_amount decimal(16,2) null comment '总账单金额' after claim_check;

alter table claim_case_cal add total_bill_amount_foreign decimal(16,2) null comment '总外币账单金额' after total_bill_amount;

alter table finance_pay_info add trade_serial varchar(20) null comment '交易申请流水号' after acc_record_id;

alter table finance_borrow_info add acc_record_id varchar(20) null comment '应付记账ID' after borrow_amount;

alter table finance_borrow_info add trade_serial varchar(20) null comment '交易申请流水号' after acc_record_id;

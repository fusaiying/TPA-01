alter table claim_batch
    add direct_receipt_sign VARCHAR(2) null comment '批次是否单张发票' after cont_no;

alter table claim_batch
    add case_flag VARCHAR(2) null comment '案件第五位标识码' after direct_receipt_sign;
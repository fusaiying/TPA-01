
alter table claim_case_debt_receipt
    add insured_name varchar(50) not null comment '被保人姓名' after insured_no;

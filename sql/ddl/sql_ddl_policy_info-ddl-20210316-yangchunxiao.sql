
alter table policy_info
    add settle_flag varchar(2) null comment '结算标记(结算后置为Y)' after ss_flag;
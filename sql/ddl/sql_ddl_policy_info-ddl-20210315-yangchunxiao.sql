alter table policy_info
    add app_name varchar(100) not null comment '投保人姓名' after name;

alter table sys_user
    add organ_code VARCHAR(20) null comment '机构编码' after login_date;

alter table sys_user
    add organ_name VARCHAR(20) null comment '机构名称' after organ_code;

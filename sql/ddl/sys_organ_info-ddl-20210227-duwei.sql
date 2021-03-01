alter table sys_organ_info modify parent_organ_code varchar(20) null comment '上级机构编码';

alter table sys_organ_info
    add organ_lev int(1) not null comment '机构级别' after parent_organ_code;

alter table sys_organ_info modify organ_name_en varchar(100) not null comment '机构英文名称';
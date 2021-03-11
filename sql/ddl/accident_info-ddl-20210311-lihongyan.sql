DROP TABLE IF EXISTS accident_info;
CREATE TABLE accident_info
(
    pbw_accident_id               BIGINT(20)     NOT NULL AUTO_INCREMENT COMMENT 'E结算案件事故信息ID' ,
    pbw_case_id               BIGINT(20)     NOT NULL  COMMENT 'E结算案件信息ID' ,
    acci_date            DATETIME       NOT NULL COMMENT '出险日期',
    acci_place           VARCHAR(100)            COMMENT '出险地点',
    acci_desc            VARCHAR(1000)           COMMENT '案件描述',
    status               varchar(1)     NOT NULL COMMENT '数据状态(Y-成功,N-失败)',
    create_by            VARCHAR(20)    NOT NULL COMMENT '创建人',
    create_time          DATETIME       NOT NULL COMMENT '创建时间',
    update_by            VARCHAR(20) COMMENT '更新人',
    update_time          DATETIME COMMENT '更新时间',
    PRIMARY KEY (pbw_accident_id)
) engine=innodb auto_increment=100 COMMENT = 'E结算案件事故信息表';
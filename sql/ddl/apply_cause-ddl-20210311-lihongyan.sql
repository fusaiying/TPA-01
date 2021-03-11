DROP TABLE IF EXISTS apply_cause;
CREATE TABLE apply_cause
(
    pbw_apply_id               BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'E结算案件申请原因ID' ,
    pbw_case_id               BIGINT(20)     NOT NULL  COMMENT 'E结算案件信息ID' ,
    claim_casuse         VARCHAR(16)    NOT NULL COMMENT '申请原因代码',
    status               varchar(1)     NOT NULL COMMENT '数据状态(Y-成功,N-失败)',
    create_by            VARCHAR(20)    NOT NULL COMMENT '创建人',
    create_time          DATETIME       NOT NULL COMMENT '创建时间',
    update_by            VARCHAR(20) COMMENT '更新人',
    update_time          DATETIME COMMENT '更新时间',
    PRIMARY KEY (pbw_apply_id)
) engine=innodb auto_increment=100 COMMENT = 'E结算案件申请原因表';
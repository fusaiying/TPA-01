DROP TABLE IF EXISTS case_info;
CREATE TABLE case_info
(
    pbw_case_id               BIGINT(20)     NOT NULL  COMMENT 'E结算案件信息ID' ,
    docu_no               VARCHAR(16)    NOT NULL COMMENT '报案号',
    batch_no             VARCHAR(32)    NOT NULL COMMENT '所属批次号',
    hospiral_id          VARCHAR(32)    NOT NULL COMMENT '医院ID',
    client_no            VARCHAR(32)    NOT NULL COMMENT '出险人客户号',
    cert_no              VARCHAR(16)    NOT NULL COMMENT '分单号',
    region_code          VARCHAR(10)    NOT NULL COMMENT '承保机构',
    accepting_branch     VARCHAR(16)    NOT NULL COMMENT '受理机构',
    pbw_batch_no         VARCHAR(20)    NOT NULL COMMENT 'pbw批次号',
    status               varchar(1)     NOT NULL COMMENT '数据状态(Y-成功,N-失败)',
    succ                 varchar(1)     COMMENT '数据状态(Y-成功,N-失败)',
    message              varchar(2000) COMMENT '返回信息',
    create_by            VARCHAR(20)    NOT NULL COMMENT '创建人',
    create_time          DATETIME       NOT NULL COMMENT '创建时间',
    update_by            VARCHAR(20) COMMENT '更新人',
    update_time          DATETIME COMMENT '更新时间',
    PRIMARY KEY (pbw_case_id)
) engine=innodb auto_increment=100 COMMENT = 'E结算案件信息表';
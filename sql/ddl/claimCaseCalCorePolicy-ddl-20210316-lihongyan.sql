DROP TABLE IF EXISTS claim_case_cal_core_policy;
CREATE TABLE claim_case_cal_core_policy
(
    core_policy_id BIGINT(20) NOT NULL   COMMENT '核心案件保单ID' ,
    core_id            BIGINT(20) NOT NULL   COMMENT '核心案件ID' ,
    rpt_no            VARCHAR(20)  NOT NULL COMMENT '报案号',
    policy_no         VARCHAR(20)  NOT NULL COMMENT '保单号',
    policy_item_no    VARCHAR(20)  NOT NULL COMMENT '分单号',
    policy_manage_com VARCHAR(10)  NOT NULL COMMENT '承保机构',
    plan_code         VARCHAR(20) COMMENT '险种计划',
    insured_property  VARCHAR(2) COMMENT '投保性质',
    product_code      VARCHAR(20) COMMENT '产品代码',
    risk_code         VARCHAR(20)  NOT NULL COMMENT '险种编码',
    insured_no        VARCHAR(20)  NOT NULL COMMENT '被保人编码',
    name              varchar(100) NOT NULL COMMENT '被保人姓名',
    department        VARCHAR(15) COMMENT '部门',
    client_number     VARCHAR(16) COMMENT '投保人编码',
    amount_type        VARCHAR(2) COMMENT '费用类型',
    fee_amount         DECIMAL(16, 2) COMMENT '费用金额',
    brno              int(2) NOT NULL COMMENT '主附契约号',
    status            VARCHAR(1) null comment '数据状态（Y-有效，N-无效）',
    create_by         VARCHAR(20)  NOT NULL COMMENT '创建人',
    create_time       DATETIME     NOT NULL COMMENT '创建时间',
    update_by         VARCHAR(20) COMMENT '更新人',
    update_time       DATETIME COMMENT '更新时间',
    PRIMARY KEY (core_policy_id)
) engine=innodb auto_increment=100 COMMENT = '核心案件同步信息保单表';
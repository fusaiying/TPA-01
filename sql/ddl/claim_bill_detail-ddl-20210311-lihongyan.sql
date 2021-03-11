DROP TABLE IF EXISTS claim_bill_detail;
CREATE TABLE claim_bill_detail
(
    pbw_bill_id               BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'E结算案件账单费用明细ID' ,
    pbw_case_id               BIGINT(20)     NOT NULL  COMMENT 'E结算案件信息ID' ,
    fee_type               VARCHAR(2)    NOT NULL COMMENT '费用类型编码',
    fee_amt               decimal(12,2)    NOT NULL COMMENT '费用类别金额',
    discount_amt               decimal(12,2)  NOT NULL  COMMENT '费用折扣金额',
    status               varchar(1)     NOT NULL COMMENT '数据状态(Y-成功,N-失败)',
    create_by            VARCHAR(20)    NOT NULL COMMENT '创建人',
    create_time          DATETIME       NOT NULL COMMENT '创建时间',
    update_by            VARCHAR(20) COMMENT '更新人',
    update_time          DATETIME COMMENT '更新时间',
    PRIMARY KEY (pbw_id)
) engine=innodb auto_increment=100 COMMENT = 'E结算案件账单费用明细表';
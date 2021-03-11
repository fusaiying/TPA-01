DROP TABLE IF EXISTS claim_bill;
CREATE TABLE claim_bill
(
    pbw_bill_id               BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'E结算案件账单信息ID' ,
    pbw_case_id               BIGINT(20)     NOT NULL  COMMENT 'E结算案件信息ID' ,
    bill_no               VARCHAR(40)    NOT NULL COMMENT '账单号',
    bill_type               VARCHAR(1)    NOT NULL COMMENT '账单类型',
    invoice_type               VARCHAR(1)    NOT NULL COMMENT '材料类型',
    therapy_type               VARCHAR(1)    NOT NULL COMMENT '治疗类型',
    bill_amt             decimal(12,2)    NOT NULL COMMENT '账单金额',
    doctor_name               VARCHAR(50)    COMMENT '医生姓名',
    currency_code               VARCHAR(5)    NOT NULL COMMENT '币种',
    other_insurance_amt              decimal(12,2)     COMMENT '第三方先期给付',
    shi_amt               decimal(12,2)     COMMENT '社保先期给付',
    customer_own_amt               decimal(12,2)     COMMENT '自付额',
    hospital_discount               decimal(12,2)     COMMENT '医院折扣金额',
    hospital_code               VARCHAR(40)   NOT NULL  COMMENT '医院代码',
    disease_code               VARCHAR(20)   NOT NULL  COMMENT '疾病代码',
    disease_desc               VARCHAR(100)   NOT NULL  COMMENT '疾病描述',
    enter_date               DATETIME   NOT NULL  COMMENT '就诊开始日期',
    out_date               DATETIME   NOT NULL  COMMENT '就诊结束日期',
    status               varchar(1)     NOT NULL COMMENT '数据状态(Y-成功,N-失败)',
    create_by            VARCHAR(20)    NOT NULL COMMENT '创建人',
    create_time          DATETIME       NOT NULL COMMENT '创建时间',
    update_by            VARCHAR(20) COMMENT '更新人',
    update_time          DATETIME COMMENT '更新时间',
    PRIMARY KEY (pbw_id)
) engine=innodb auto_increment=100 COMMENT = 'E结算案件账单信息表';
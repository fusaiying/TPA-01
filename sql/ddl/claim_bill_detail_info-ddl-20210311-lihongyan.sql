DROP TABLE IF EXISTS claim_bill_detail_info;
CREATE TABLE claim_bill_detail_info
(
    pbw_bill_id               BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'E结算案件费用项目明细信息ID' ,
    pbw_case_id               BIGINT(20)     NOT NULL  COMMENT 'E结算案件信息ID' ,
    fee_type               VARCHAR(2)     COMMENT '费用类型编码',
    detail_code               VARCHAR(50)     COMMENT '费用明细代码',
    detail_name               VARCHAR(100)     COMMENT '费用明细名称',
    detail_amt               decimal(12,2)     COMMENT '费用明细金额',
    quantity_unit               VARCHAR(100)     COMMENT '医疗服务计量单位',
    service_amount               bigint(10)     COMMENT '服务数量信息',
    status               varchar(1)     NOT NULL COMMENT '数据状态(Y-成功,N-失败)',
    create_by            VARCHAR(20)    NOT NULL COMMENT '创建人',
    create_time          DATETIME       NOT NULL COMMENT '创建时间',
    update_by            VARCHAR(20) COMMENT '更新人',
    update_time          DATETIME COMMENT '更新时间',
    PRIMARY KEY (pbw_bill_id)
) engine=innodb auto_increment=100 COMMENT = 'E结算案件费用项目明细信息表';
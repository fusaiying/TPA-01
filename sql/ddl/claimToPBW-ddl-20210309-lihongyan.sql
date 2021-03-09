DROP TABLE IF EXISTS claim_to_pbw;
CREATE TABLE claim_to_pbw
(
    pbw_id               BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '返回给pbw信息ID' ,
    pbw_batch_no         VARCHAR(20)    NOT NULL COMMENT 'pbw唯一标识',
    succ                 varchar(1) COMMENT '数据状态(Y-成功,N-失败)',
    message              varchar(32) COMMENT '返回信息',
    create_by            VARCHAR(20)    NOT NULL COMMENT '创建人',
    create_time          DATETIME       NOT NULL COMMENT '创建时间',
    update_by            VARCHAR(20) COMMENT '更新人',
    update_time          DATETIME COMMENT '更新时间',
    PRIMARY KEY (pbw_id)
) engine=innodb auto_increment=100 COMMENT = '返回给pbw信息表';
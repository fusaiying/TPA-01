alter table claim_case_filing_detail add dept_code varchar(16) ;
alter table claim_case_filing_detail add remark varchar(4000) ;
ALTER TABLE claim_case_filing_detail MODIFY COLUMN claim_type VARCHAR(2) DEFAULT NULL COMMENT '理赔类型';





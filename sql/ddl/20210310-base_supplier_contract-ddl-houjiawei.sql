
ALTER TABLE base_supplier_contract MODIFY COLUMN deposit decimal(16) DEFAULT NULL COMMENT '押金金额';

ALTER TABLE base_supplier_contract MODIFY COLUMN advicenum VARCHAR(16) DEFAULT NULL COMMENT '就诊次数';

ALTER TABLE base_supplier_contract MODIFY COLUMN tel VARCHAR(200) DEFAULT NULL COMMENT '办公电话';

ALTER TABLE base_supplier_contract MODIFY COLUMN box_ridge_code VARCHAR(200) DEFAULT NULL COMMENT '盒脊编号';


ALTER TABLE base_supplier_contract_bak MODIFY COLUMN deposit decimal(16) DEFAULT NULL COMMENT '押金金额';

ALTER TABLE base_supplier_contract_bak MODIFY COLUMN advicenum VARCHAR(16) DEFAULT NULL COMMENT '就诊次数';

ALTER TABLE base_supplier_contract_bak MODIFY COLUMN tel VARCHAR(200) DEFAULT NULL COMMENT '办公电话';

ALTER TABLE base_supplier_contract_bak MODIFY COLUMN box_ridge_code VARCHAR(200) DEFAULT NULL COMMENT '盒脊编号';
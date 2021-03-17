
	ALTER TABLE finance_pay_info ADD transaction_code VARCHAR(20) COMMENT '交易编码';
	ALTER TABLE finance_pay_info ADD international_completed_by VARCHAR(40) COMMENT '国际收支填报人';
	ALTER TABLE finance_pay_info ADD international_completed_phone VARCHAR(22) COMMENT '国际收支填报人电话';
	ALTER TABLE finance_pay_info ADD transaction_postscript VARCHAR(2000) COMMENT '交易附言';
	ALTER TABLE finance_pay_info ADD transaction_postscript VARCHAR(2000) COMMENT '交易附言';
	ALTER TABLE finance_pay_info MODIFY COLUMN pay_currency VARCHAR(3) NOT NULL COMMENT '支付币种';







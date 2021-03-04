package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * base_bank（银行信息）对象 base_bank
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseCheckInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String providerCode;

    /** 开户银行编码 */
    @Excel(name = "审核结果")
    private String checkResult;

    /** 开户银行名称 */
    @Excel(name = "审核意见")
    private String checkAdvice;


    /** 状态 */
    @Excel(name = "状态",readConverterExp = "01=-有效，02-无效")
    private String status;

    private String checkResultName;

}

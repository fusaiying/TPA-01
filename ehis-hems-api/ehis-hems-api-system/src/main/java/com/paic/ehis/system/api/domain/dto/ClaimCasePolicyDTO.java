package com.paic.ehis.system.api.domain.dto;



import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 案件保单关联对象 claim_case_policy
 * 
 * @author sino
 * @date 2021-01-14
 */
@Data
public class ClaimCasePolicyDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 被保人编码 */
    @Excel(name = "被保人编码")
    private String insuredNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    private  String policyItemNo;//分单号
    private Date validStartDate;//有效起期
    private Date validEndDate;//有效止期
    private  String policyRiskType;//保单险类
    private  String policyStatus;//保单状态
    private  String policyType;//保单类型
    private  String planCode;//计划编码
    private  String ssFlag;//社保标记
}

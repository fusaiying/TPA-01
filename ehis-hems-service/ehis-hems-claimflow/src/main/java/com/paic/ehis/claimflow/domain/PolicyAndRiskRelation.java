package com.paic.ehis.claimflow.domain;


import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 保单险种关联对象 policy_risk_relation
 *
 * @author sino
 * @date 2021-01-09
 */
@Data
public class PolicyAndRiskRelation implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 关联ID */
    private Long relationId;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 险种编码 */
    @Excel(name = "险种编码")
    private String riskCode;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    private Date startTime;

    private Date endTime;

    /** 结算标记 */
    private String settleFlag;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

}

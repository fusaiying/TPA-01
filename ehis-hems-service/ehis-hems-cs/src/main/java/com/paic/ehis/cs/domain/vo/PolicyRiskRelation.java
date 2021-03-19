package com.paic.ehis.cs.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 保单险种关联对象 policy_risk_relation
 * 
 * @author sino
 * @date 2021-01-09
 */
@Data
public class PolicyRiskRelation extends BaseEntity
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

    /** 险种名称 */
    @Excel(name = "险种名称")
    private String riskName;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 有效起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validStartDate;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

}

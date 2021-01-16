package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 保单险种关联对象 policy_risk_relation
 * 
 * @author sino
 * @date 2021-01-09
 */
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

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setRelationId(Long relationId) 
    {
        this.relationId = relationId;
    }

    public Long getRelationId() 
    {
        return relationId;
    }
    public void setPolicyNo(String policyNo) 
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() 
    {
        return policyNo;
    }
    public void setRiskCode(String riskCode) 
    {
        this.riskCode = riskCode;
    }

    public String getRiskCode() 
    {
        return riskCode;
    }
    public void setRiskName(String riskName) 
    {
        this.riskName = riskName;
    }

    public String getRiskName() 
    {
        return riskName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("relationId", getRelationId())
            .append("policyNo", getPolicyNo())
            .append("riskCode", getRiskCode())
            .append("riskName", getRiskName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

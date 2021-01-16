package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 计划信息 对象 claim_product_plan
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimProductPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划编码 */
    private String planCode;

    /** 产品编码 */
    private String riskCode;

    /** 计划名称 */
    @Excel(name = "计划名称")
    private String planName;

    /** 保障区域 */
    @Excel(name = "保障区域")
    private String ensureRegion;

    /** 计划描述 */
    @Excel(name = "计划描述")
    private String planDescription;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setPlanCode(String planCode) 
    {
        this.planCode = planCode;
    }

    public String getPlanCode() 
    {
        return planCode;
    }
    public void setRiskCode(String riskCode) 
    {
        this.riskCode = riskCode;
    }

    public String getRiskCode() 
    {
        return riskCode;
    }
    public void setPlanName(String planName) 
    {
        this.planName = planName;
    }

    public String getPlanName() 
    {
        return planName;
    }
    public void setEnsureRegion(String ensureRegion) 
    {
        this.ensureRegion = ensureRegion;
    }

    public String getEnsureRegion() 
    {
        return ensureRegion;
    }
    public void setPlanDescription(String planDescription) 
    {
        this.planDescription = planDescription;
    }

    public String getPlanDescription() 
    {
        return planDescription;
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
            .append("planCode", getPlanCode())
            .append("riskCode", getRiskCode())
            .append("planName", getPlanName())
            .append("ensureRegion", getEnsureRegion())
            .append("planDescription", getPlanDescription())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

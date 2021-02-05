package com.paic.ehis.claimcal.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 责任信息 对象 claim_product_duty
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimProductDuty extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 责任编码 */
    private String dutyCode;

    /** 计划编码 */
    private String planCode;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String riskCode;

    /** 责任名称 */
    @Excel(name = "责任名称")
    private String dutyName;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setDutyCode(String dutyCode) 
    {
        this.dutyCode = dutyCode;
    }

    public String getDutyCode() 
    {
        return dutyCode;
    }
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
    public void setDutyName(String dutyName) 
    {
        this.dutyName = dutyName;
    }

    public String getDutyName() 
    {
        return dutyName;
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
            .append("dutyCode", getDutyCode())
            .append("planCode", getPlanCode())
            .append("riskCode", getRiskCode())
            .append("dutyName", getDutyName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

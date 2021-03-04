package com.paic.ehis.claimcal.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 责任明细信息 对象 claim_product_duty_detail
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimProductDutyDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 责任明细编码 */
    private String dutyDetailCode;

    /** 责任编码 */
    private String dutyCode;

    /** 计划编码 */
    @Excel(name = "计划编码")
    private String planCode;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String riskCode;

    /** 责任明细名称 */
    @Excel(name = "责任明细名称")
    private String dutyDetailName;

    /** 申请原因编码 */
    @Excel(name = "申请原因编码")
    private String applyReason;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setDutyDetailCode(String dutyDetailCode) 
    {
        this.dutyDetailCode = dutyDetailCode;
    }

    public String getDutyDetailCode() 
    {
        return dutyDetailCode;
    }
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
    public void setDutyDetailName(String dutyDetailName) 
    {
        this.dutyDetailName = dutyDetailName;
    }

    public String getDutyDetailName() 
    {
        return dutyDetailName;
    }
    public void setApplyReason(String applyReason) 
    {
        this.applyReason = applyReason;
    }

    public String getApplyReason() 
    {
        return applyReason;
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
            .append("dutyDetailCode", getDutyDetailCode())
            .append("dutyCode", getDutyCode())
            .append("planCode", getPlanCode())
            .append("riskCode", getRiskCode())
            .append("dutyDetailName", getDutyDetailName())
            .append("applyReason", getApplyReason())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

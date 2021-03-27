package com.paic.ehis.finance.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * TPA服务费结算明细对象 finance_tpa_settle_detail
 * 
 * @author sino
 * @date 2021-01-30
 */
public class FinanceTpaSettleDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务明细ID */
    private Long detailId;

    /** 结算任务号 */
    @Excel(name = "结算任务号")
    private String settleTaskNo;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 险种编码 */
    private String riskCode;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 服务费金额 */
    @Excel(name = "服务费金额")
    private BigDecimal serviceAmount;

    /* 备注 */
    private String remark;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }
    public void setSettleTaskNo(String settleTaskNo) 
    {
        this.settleTaskNo = settleTaskNo;
    }

    public String getSettleTaskNo() 
    {
        return settleTaskNo;
    }
    public void setCompanyCode(String companyCode) 
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() 
    {
        return companyCode;
    }
    public void setPolicyNo(String policyNo) 
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() 
    {
        return policyNo;
    }
    public void setPolicyItemNo(String policyItemNo) 
    {
        this.policyItemNo = policyItemNo;
    }

    public String getPolicyItemNo() 
    {
        return policyItemNo;
    }
    public void setServiceAmount(BigDecimal serviceAmount) 
    {
        this.serviceAmount = serviceAmount;
    }

    public BigDecimal getServiceAmount() 
    {
        return serviceAmount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDeptCode(String deptCode) 
    {
        this.deptCode = deptCode;
    }

    public String getDeptCode() 
    {
        return deptCode;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("detailId", getDetailId())
            .append("settleTaskNo", getSettleTaskNo())
            .append("companyCode", getCompanyCode())
            .append("policyNo", getPolicyNo())
            .append("policyItemNo", getPolicyItemNo())
            .append("serviceAmount", getServiceAmount())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptCode", getDeptCode())
            .toString();
    }
}

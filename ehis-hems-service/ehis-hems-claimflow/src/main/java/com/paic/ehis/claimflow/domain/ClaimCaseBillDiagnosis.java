package com.paic.ehis.claimflow.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 案件账单诊断信息对象 claim_case_bill_diagnosis
 * 
 * @author sino
 * @date 2021-01-15
 */
public class ClaimCaseBillDiagnosis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 诊断信息ID */
    private Long digId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 账单ID */
    @Excel(name = "账单ID")
    private Long billId;

    /** ICD10编码 */
    @Excel(name = "ICD10编码")
    private String icdCode;

    /** 诊断类型 01-主要诊断、02-次要诊断 */
    @Excel(name = "诊断类型 01-主要诊断、02-次要诊断")
    private String digType;

    /** 次要诊断次序 */
    @Excel(name = "次要诊断次序")
    private Long digOrder;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setDigId(Long digId) 
    {
        this.digId = digId;
    }

    public Long getDigId() 
    {
        return digId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setBillId(Long billId) 
    {
        this.billId = billId;
    }

    public Long getBillId() 
    {
        return billId;
    }
    public void setIcdCode(String icdCode) 
    {
        this.icdCode = icdCode;
    }

    public String getIcdCode() 
    {
        return icdCode;
    }
    public void setDigType(String digType) 
    {
        this.digType = digType;
    }

    public String getDigType() 
    {
        return digType;
    }
    public void setDigOrder(Long digOrder) 
    {
        this.digOrder = digOrder;
    }

    public Long getDigOrder() 
    {
        return digOrder;
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
            .append("digId", getDigId())
            .append("rptNo", getRptNo())
            .append("billId", getBillId())
            .append("icdCode", getIcdCode())
            .append("digType", getDigType())
            .append("digOrder", getDigOrder())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

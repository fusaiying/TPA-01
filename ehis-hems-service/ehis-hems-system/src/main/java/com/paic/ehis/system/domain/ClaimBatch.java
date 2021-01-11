package com.paic.ehis.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 理赔批次 对象 claim_batch
 * 
 * @author sino
 * @date 2021-01-05
 */
public class ClaimBatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 批次号 */
    private String batchNo;

    /** 交单来源 */
    @Excel(name = "交单来源")
    private String source;

    /** 就诊医院ID */
    @Excel(name = "就诊医院ID")
    private String hospitalCode;

    /** 批次理赔类型 */
    @Excel(name = "批次理赔类型")
    private String claimType;

    /** 交单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitDate;

    /** 案件数量 */
    @Excel(name = "案件数量")
    private Integer caseload;

    /** 批次总金额 */
    @Excel(name = "批次总金额")
    private BigDecimal batchTotal;

    /** 交单机构编码 */
    @Excel(name = "交单机构编码")
    private String organCode;

    /** 账单币种 */
    @Excel(name = "账单币种")
    private String currency;

    /** 保单类型 */
    @Excel(name = "保单类型")
    private String contType;

    /** 是否收到发票 */
    @Excel(name = "是否收到发票")
    private String billRecevieFlag;

    /** 优先原因 */
    @Excel(name = "优先原因")
    private String priReason;

    /** 批次状态 01-处理中 02-交单复核 03-交单完成 04-交单退回 05-交单无效 */
    @Excel(name = "批次状态 01-处理中 02-交单复核 03-交单完成 04-交单退回 05-交单无效")
    private String batchStatus;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String expressNumber;

    /** 接单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveDate;

    /** 交件人 */
    @Excel(name = "交件人")
    private String sendBy;

    /** 特殊案件类型 */
    @Excel(name = "特殊案件类型")
    private String speccaseType;

    /** 投保单位 */
    @Excel(name = "投保单位")
    private String issuingUnit;

    /** 保单号 */
    @Excel(name = "保单号")
    private String contNo;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setHospitalCode(String hospitalCode) 
    {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalCode() 
    {
        return hospitalCode;
    }
    public void setClaimType(String claimType) 
    {
        this.claimType = claimType;
    }

    public String getClaimType() 
    {
        return claimType;
    }
    public void setSubmitDate(Date submitDate) 
    {
        this.submitDate = submitDate;
    }

    public Date getSubmitDate() 
    {
        return submitDate;
    }
    public void setCaseload(Integer caseload) 
    {
        this.caseload = caseload;
    }

    public Integer getCaseload() 
    {
        return caseload;
    }
    public void setBatchTotal(BigDecimal batchTotal) 
    {
        this.batchTotal = batchTotal;
    }

    public BigDecimal getBatchTotal() 
    {
        return batchTotal;
    }
    public void setOrganCode(String organCode) 
    {
        this.organCode = organCode;
    }

    public String getOrganCode() 
    {
        return organCode;
    }
    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }
    public void setContType(String contType) 
    {
        this.contType = contType;
    }

    public String getContType() 
    {
        return contType;
    }
    public void setBillRecevieFlag(String billRecevieFlag) 
    {
        this.billRecevieFlag = billRecevieFlag;
    }

    public String getBillRecevieFlag() 
    {
        return billRecevieFlag;
    }
    public void setPriReason(String priReason) 
    {
        this.priReason = priReason;
    }

    public String getPriReason() 
    {
        return priReason;
    }
    public void setBatchStatus(String batchStatus) 
    {
        this.batchStatus = batchStatus;
    }

    public String getBatchStatus() 
    {
        return batchStatus;
    }
    public void setExpressNumber(String expressNumber) 
    {
        this.expressNumber = expressNumber;
    }

    public String getExpressNumber() 
    {
        return expressNumber;
    }
    public void setReceiveDate(Date receiveDate) 
    {
        this.receiveDate = receiveDate;
    }

    public Date getReceiveDate() 
    {
        return receiveDate;
    }
    public void setSendBy(String sendBy) 
    {
        this.sendBy = sendBy;
    }

    public String getSendBy() 
    {
        return sendBy;
    }
    public void setSpeccaseType(String speccaseType) 
    {
        this.speccaseType = speccaseType;
    }

    public String getSpeccaseType() 
    {
        return speccaseType;
    }
    public void setIssuingUnit(String issuingUnit) 
    {
        this.issuingUnit = issuingUnit;
    }

    public String getIssuingUnit() 
    {
        return issuingUnit;
    }
    public void setContNo(String contNo) 
    {
        this.contNo = contNo;
    }

    public String getContNo() 
    {
        return contNo;
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
            .append("batchNo", getBatchNo())
            .append("source", getSource())
            .append("hospitalCode", getHospitalCode())
            .append("claimType", getClaimType())
            .append("submitDate", getSubmitDate())
            .append("caseload", getCaseload())
            .append("batchTotal", getBatchTotal())
            .append("organCode", getOrganCode())
            .append("currency", getCurrency())
            .append("contType", getContType())
            .append("billRecevieFlag", getBillRecevieFlag())
            .append("priReason", getPriReason())
            .append("remark", getRemark())
            .append("batchStatus", getBatchStatus())
            .append("expressNumber", getExpressNumber())
            .append("receiveDate", getReceiveDate())
            .append("sendBy", getSendBy())
            .append("speccaseType", getSpeccaseType())
            .append("issuingUnit", getIssuingUnit())
            .append("contNo", getContNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

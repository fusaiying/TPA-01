package com.paic.ehis.claimflow.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 案件信息 对象 claim_case
 *
 * @author sino
 * @date 2021-01-09
 */
@Data
public class ClaimCase extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 批次号
     */
    @Excel(name = "批次号")
    private String batchNo;

    /**
     * 报案号
     */
    @Excel(name = "报案号")
    private String rptNo;

    /**
     * 归档号
     */
//    @Excel(name = "归档号")
    private String filingNo;

    /**
     *
     */
    @Excel(name = "案件类型")
    private String claimType;

    @Excel(name = "案件性质", readConverterExp = "01=TPA案件，02=核心案件")
    private String caseProp;

    /**
     * 案件状态
     */
    @Excel(name = "案件状态")
    private String caseStatus;

    /**
     * 撤件原因
     */
    private String pulloutReason;

    /**
     * 撤件描述
     */
    private String pulloutDescribe;

    /**
     * 数据状态（Y-有效，N-无效）
     */
//    @Excel(name = "数据状态", readConverterExp = "Y=有效，N=无效")
    private String status;

    /**
     * 出单公司编码
     */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /**
     * 被保人姓名
     */
    @Excel(name = "被保人姓名")
    private String name;

    /**
     * 被保人证件号
     */
    @Excel(name = "被保人证件号")
    private String idNo;

    /**
     * 交单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitdate;

    /**
     * 保单号
     */
    @Excel(name = "保单号")
    private String policyNo;

    /**
     * 结案日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endCaseTime;

    /**
     * 是否申诉案件
     */
    @Excel(name = "是否申诉案件")
    private String isAppeal;

    /**
     * 支付状态
     */
    @Excel(name = "支付状态")
    private String payStatus;

    /**
     * 是否财务回退
     */
    @Excel(name = "是否财务回退")
    private String isFinanceBack;

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public String getCaseProp() {
        return caseProp;
    }

    public void setCaseProp(String caseProp) {
        this.caseProp = caseProp;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public Date getEndCaseTime() {
        return endCaseTime;
    }

    public void setEndCaseTime(Date endCaseTime) {
        this.endCaseTime = endCaseTime;
    }

    public String getIsAppeal() {
        return isAppeal;
    }

    public void setIsAppeal(String isAppeal) {
        this.isAppeal = isAppeal;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getIsFinanceBack() {
        return isFinanceBack;
    }

    public void setIsFinanceBack(String isFinanceBack) {
        this.isFinanceBack = isFinanceBack;
    }

    /*撤件类别*/
    private String pulloutType;

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getRptNo() {
        return rptNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setFilingNo(String filingNo) {
        this.filingNo = filingNo;
    }

    public String getFilingNo() {
        return filingNo;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setPulloutReason(String pulloutReason) {
        this.pulloutReason = pulloutReason;
    }

    public String getPulloutReason() {
        return pulloutReason;
    }

    public void setPulloutDescribe(String pulloutDescribe) {
        this.pulloutDescribe = pulloutDescribe;
    }

    public String getPulloutDescribe() {
        return pulloutDescribe;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getPulloutType() {
        return pulloutType;
    }

    public void setPulloutType(String pulloutType) {
        this.pulloutType = pulloutType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rptNo", getRptNo())
                .append("batchNo", getBatchNo())
                .append("filingNo", getFilingNo())
                .append("caseStatus", getCaseStatus())
                .append("pulloutReason", getPulloutReason())
                .append("pulloutDescribe", getPulloutDescribe())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("caseProp", getCaseProp())
                .toString();
    }
}

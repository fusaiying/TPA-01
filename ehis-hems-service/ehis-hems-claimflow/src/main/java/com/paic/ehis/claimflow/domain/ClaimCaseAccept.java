package com.paic.ehis.claimflow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 案件受理信息 对象 claim_case_accept
 *
 * @author sino
 * @date 2021-01-09
 */
public class ClaimCaseAccept extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long acceptId;

    /**
     * 报案号
     */
    private String rptNo;

    /**
     * 索赔金额
     */
    @Excel(name = "索赔金额")
    private BigDecimal claimAmount;

    /**
     * 出险日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出险日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accDate;

    /**
     * 出险类型
     */
    @Excel(name = "出险类型")
    private String accType;

    /**
     * 事故者现状
     */
    @Excel(name = "事故者现状")
    private String currSituation;

    /**
     * 首次重疾确诊日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "首次重疾确诊日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstSerIllnessDate;

    /**
     * 死亡日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "死亡日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deathDate;

    /**
     * 残疾确诊日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "残疾确诊日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date disabilityDate;

    /**
     * 是否预授权
     */
    @Excel(name = "是否预授权")
    private String preAuthFlag;

    /**
     * 预授权项目
     */
    @Excel(name = "预授权项目")
    private String preAuthItem;

    /**
     * 预授权号
     */
    @Excel(name = "预授权号")
    private String preAuthNo;

    /**
     * 材料齐全日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "材料齐全日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date materialCompleteDate;

    /**
     * 公司签收日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "公司签收日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiptDate;

    /**
     * 优先原因
     */
    @Excel(name = "优先原因")
    private String priReason;

    /**
     * 事故发生省
     */
    @Excel(name = "事故发生省")
    private String accProvince;

    /**
     * 事故发生市
     */
    @Excel(name = "事故发生市")
    private String accCity;

    /**
     * 事故发生区
     */
    @Excel(name = "事故发生区")
    private String accDistrict;

    /**
     * 事故发生详细地址
     */
    @Excel(name = "事故发生详细地址")
    private String accAddress;

    /**
     * 事故经过
     */
    @Excel(name = "事故经过")
    private String accDescribe;

    /**
     * 数据状态（Y-有效，N-无效）
     */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getRptNo() {
        return rptNo;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setAccDate(Date accDate) {
        this.accDate = accDate;
    }

    public Date getAccDate() {
        return accDate;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getAccType() {
        return accType;
    }

    public void setCurrSituation(String currSituation) {
        this.currSituation = currSituation;
    }

    public String getCurrSituation() {
        return currSituation;
    }

    public void setFirstSerIllnessDate(Date firstSerIllnessDate) {
        this.firstSerIllnessDate = firstSerIllnessDate;
    }

    public Date getFirstSerIllnessDate() {
        return firstSerIllnessDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDisabilityDate(Date disabilityDate) {
        this.disabilityDate = disabilityDate;
    }

    public Date getDisabilityDate() {
        return disabilityDate;
    }

    public void setPreAuthFlag(String preAuthFlag) {
        this.preAuthFlag = preAuthFlag;
    }

    public String getPreAuthFlag() {
        return preAuthFlag;
    }

    public void setPreAuthItem(String preAuthItem) {
        this.preAuthItem = preAuthItem;
    }

    public String getPreAuthItem() {
        return preAuthItem;
    }

    public void setPreAuthNo(String preAuthNo) {
        this.preAuthNo = preAuthNo;
    }

    public String getPreAuthNo() {
        return preAuthNo;
    }

    public void setMaterialCompleteDate(Date materialCompleteDate) {
        this.materialCompleteDate = materialCompleteDate;
    }

    public Date getMaterialCompleteDate() {
        return materialCompleteDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setPriReason(String priReason) {
        this.priReason = priReason;
    }

    public String getPriReason() {
        return priReason;
    }

    public void setAccProvince(String accProvince) {
        this.accProvince = accProvince;
    }

    public String getAccProvince() {
        return accProvince;
    }

    public void setAccCity(String accCity) {
        this.accCity = accCity;
    }

    public String getAccCity() {
        return accCity;
    }

    public void setAccDistrict(String accDistrict) {
        this.accDistrict = accDistrict;
    }

    public String getAccDistrict() {
        return accDistrict;
    }

    public void setAccAddress(String accAddress) {
        this.accAddress = accAddress;
    }

    public String getAccAddress() {
        return accAddress;
    }

    public void setAccDescribe(String accDescribe) {
        this.accDescribe = accDescribe;
    }

    public String getAccDescribe() {
        return accDescribe;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Long getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Long acceptId) {
        this.acceptId = acceptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rptNo", getRptNo())
                .append("claimAmount", getClaimAmount())
                .append("accDate", getAccDate())
                .append("accType", getAccType())
                .append("currSituation", getCurrSituation())
                .append("firstSerIllnessDate", getFirstSerIllnessDate())
                .append("deathDate", getDeathDate())
                .append("disabilityDate", getDisabilityDate())
                .append("preAuthFlag", getPreAuthFlag())
                .append("preAuthItem", getPreAuthItem())
                .append("preAuthNo", getPreAuthNo())
                .append("materialCompleteDate", getMaterialCompleteDate())
                .append("receiptDate", getReceiptDate())
                .append("priReason", getPriReason())
                .append("accProvince", getAccProvince())
                .append("accCity", getAccCity())
                .append("accDistrict", getAccDistrict())
                .append("accAddress", getAccAddress())
                .append("accDescribe", getAccDescribe())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("acceptId", getAcceptId())
                .toString();
    }
}

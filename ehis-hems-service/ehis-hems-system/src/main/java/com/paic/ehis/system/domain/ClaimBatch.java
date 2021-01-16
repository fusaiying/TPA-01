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
public class ClaimBatch extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 批次号
     */
    @Excel(name = "批次号")
    private String batchno;

    /**
     * 交单来源
     */
    @Excel(name = "交单来源")
    private String source;

    /**
     * 就诊医院ID
     */
    @Excel(name = "就诊医院ID")
    private String hospitalcode;

    /**
     * 批次理赔类型
     */
    @Excel(name = "批次理赔类型")
    private String claimtype;

    /**
     * 交单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitdate;

    /**
     * 案件数量
     */
    @Excel(name = "案件数量")
    private Integer casenum;

    /**
     * 批次总金额
     */
    @Excel(name = "批次总金额")
    private BigDecimal batchtotal;

    /**
     * 交单机构编码
     */
    @Excel(name = "交单机构编码")
    private String organcode;

    /**
     * 账单币种
     */
    @Excel(name = "账单币种")
    private String currency;

    /**
     * 保单类型
     */
    @Excel(name = "保单类型")
    private String conttype;

    /**
     * 是否收到发票
     */
    @Excel(name = "是否收到发票")
    private String billrecevieflag;

    /**
     * 优先原因
     */
    @Excel(name = "优先原因")
    private String prireason;

    /**
     * 批次状态 01-处理中 02-交单复核 03-交单完成 04-交单退回 05-交单无效
     */
    @Excel(name = "批次状态 01-处理中 02-交单复核 03-交单完成 04-交单退回 05-交单无效")
    private String batchstatus;

    /**
     * 快递单号
     */
    @Excel(name = "快递单号")
    private String expressnumber;

    /**
     * 接单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receivedate;

    /**
     * 交件人
     */
    @Excel(name = "交件人")
    private String sendby;

    /**
     * 特殊案件类型
     */
    @Excel(name = "特殊案件类型")
    private String speccasetype;

    /**
     * 投保单位
     */
    @Excel(name = "投保单位")
    private String issuingunit;

    /**
     * 保单号
     */
    @Excel(name = "保单号")
    private String contno;

    /**
     * 状态（Y-有效，N-无效）
     */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHospitalcode() {
        return hospitalcode;
    }

    public void setHospitalcode(String hospitalcode) {
        this.hospitalcode = hospitalcode;
    }

    public String getClaimtype() {
        return claimtype;
    }

    public void setClaimtype(String claimtype) {
        this.claimtype = claimtype;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public Integer getCasenum() {
        return casenum;
    }

    public void setCasenum(Integer casenum) {
        this.casenum = casenum;
    }

    public BigDecimal getBatchtotal() {
        return batchtotal;
    }

    public void setBatchtotal(BigDecimal batchtotal) {
        this.batchtotal = batchtotal;
    }

    public String getOrgancode() {
        return organcode;
    }

    public void setOrgancode(String organcode) {
        this.organcode = organcode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getConttype() {
        return conttype;
    }

    public void setConttype(String conttype) {
        this.conttype = conttype;
    }

    public String getBillrecevieflag() {
        return billrecevieflag;
    }

    public void setBillrecevieflag(String billrecevieflag) {
        this.billrecevieflag = billrecevieflag;
    }

    public String getPrireason() {
        return prireason;
    }

    public void setPrireason(String prireason) {
        this.prireason = prireason;
    }

    public String getBatchstatus() {
        return batchstatus;
    }

    public void setBatchstatus(String batchstatus) {
        this.batchstatus = batchstatus;
    }

    public String getExpressnumber() {
        return expressnumber;
    }

    public void setExpressnumber(String expressnumber) {
        this.expressnumber = expressnumber;
    }

    public Date getReceivedate() {
        return receivedate;
    }

    public void setReceivedate(Date receivedate) {
        this.receivedate = receivedate;
    }

    public String getSendby() {
        return sendby;
    }

    public void setSendby(String sendby) {
        this.sendby = sendby;
    }

    public String getSpeccasetype() {
        return speccasetype;
    }

    public void setSpeccasetype(String speccasetype) {
        this.speccasetype = speccasetype;
    }

    public String getIssuingunit() {
        return issuingunit;
    }

    public void setIssuingunit(String issuingunit) {
        this.issuingunit = issuingunit;
    }

    public String getContno() {
        return contno;
    }

    public void setContno(String contno) {
        this.contno = contno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("batchno", getBatchno())
                .append("source", getSource())
                .append("hospitalcode", getHospitalcode())
                .append("claimtype", getClaimtype())
                .append("submitdate", getSubmitdate())
                .append("casenum", getCasenum())
                .append("batchtotal", getBatchtotal())
                .append("organcode", getOrgancode())
                .append("currency", getCurrency())
                .append("conttype", getConttype())
                .append("billrecevieflag", getBillrecevieflag())
                .append("prireason", getPrireason())
                .append("remark", getRemark())
                .append("batchstatus", getBatchstatus())
                .append("expressnumber", getExpressnumber())
                .append("receivedate", getReceivedate())
                .append("sendby", getSendby())
                .append("speccasetype", getSpeccasetype())
                .append("issuingunit", getIssuingunit())
                .append("contno", getContno())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

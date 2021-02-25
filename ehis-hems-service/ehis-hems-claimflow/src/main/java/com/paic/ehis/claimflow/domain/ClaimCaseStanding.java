package com.paic.ehis.claimflow.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 报案台账信息 对象 claim_case_standing
 *
 * @author sino
 * @date 2021-01-05
 */
public class ClaimCaseStanding extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 前三个月日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dBefore;

    /** 现在日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dNow;

    private String standingId;


    /**
     * 报案号
     */
    private String rptno;

    /**
     * 证件号码
     */
    @Excel(name = "证件号码")
    private String idno;

    /**
     * 被保险人姓名
     */
    @Excel(name = "被保险人姓名")
    private String name;

    /**
     * 理赔材料
     */
    @Excel(name = "理赔材料")
    private String claimmaterials;

    /**
     * 其他（案件去向）
     */
    @Excel(name = "其他", readConverterExp = "案=件去向")
    private String otherinfo;

    /**
     * 状态（Y-有效，N-无效）
     */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;


    /**
     * 交单机构编码
     */
    @Excel(name = "交单机构编码")
    private String organcode;

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


    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 出单公司名称 */
    @Excel(name = "出单公司名称")
    private String companyName;

    public Date getdBefore() {
        return dBefore;
    }

    public void setdBefore(Date dBefore) {
        this.dBefore = dBefore;
    }

    public Date getdNow() {
        return dNow;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStandingId() {
        return standingId;
    }

    public void setStandingId(String standingId) {
        this.standingId = standingId;
    }

    public void setdNow(Date dNow) {
        this.dNow = dNow;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getOrgancode() {
        return organcode;
    }

    public void setOrgancode(String organcode) {
        this.organcode = organcode;
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

    public String getRptno() {
        return rptno;
    }

    public void setRptno(String rptno) {
        this.rptno = rptno;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClaimmaterials() {
        return claimmaterials;
    }

    public void setClaimmaterials(String claimmaterials) {
        this.claimmaterials = claimmaterials;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClaimCaseStanding{" +
                "dBefore=" + dBefore +
                ", dNow=" + dNow +
                ", standingId='" + standingId + '\'' +
                ", rptno='" + rptno + '\'' +
                ", idno='" + idno + '\'' +
                ", name='" + name + '\'' +
                ", claimmaterials='" + claimmaterials + '\'' +
                ", otherinfo='" + otherinfo + '\'' +
                ", status='" + status + '\'' +
                ", organcode='" + organcode + '\'' +
                ", expressnumber='" + expressnumber + '\'' +
                ", receivedate=" + receivedate +
                ", sendby='" + sendby + '\'' +
                ", policyNo='" + policyNo + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}

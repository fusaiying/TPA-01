package com.paic.ehis.claimmgt.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;


import java.io.Serializable;
import java.util.Date;


public class ClaimCaseStandingDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;


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
     * 快递单号
     */
    @Excel(name = "快递单号")
    private String expressnumber;

    /**
     * 交件人
     */
    @Excel(name = "交件人")
    private String sendby;

    /**
     * 交单机构编码
     */
    @Excel(name = "交单机构编码")
    private String organcode;

    /** 创建者 */
    private String createBy;

    private Date receivedate;

    private Date receiveStartDate;

    private Date receiveEndDate;


    /** 前三个月日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dBefore;

    /** 现在日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dNow;

    public Date getdBefore() {
        return dBefore;
    }

    public void setdBefore(Date dBefore) {
        this.dBefore = dBefore;
    }

    public Date getdNow() {
        return dNow;
    }

    public void setdNow(Date dNow) {
        this.dNow = dNow;
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

    public String getExpressnumber() {
        return expressnumber;
    }

    public void setExpressnumber(String expressnumber) {
        this.expressnumber = expressnumber;
    }

    public String getSendby() {
        return sendby;
    }

    public void setSendby(String sendby) {
        this.sendby = sendby;
    }

    public String getOrgancode() {
        return organcode;
    }

    public void setOrgancode(String organcode) {
        this.organcode = organcode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getReceivedate() {
        return receivedate;
    }

    public void setReceivedate(Date receivedate) {
        this.receivedate = receivedate;
    }

    public Date getReceiveStartDate() {
        return receiveStartDate;
    }

    public void setReceiveStartDate(Date receiveStartDate) {
        this.receiveStartDate = receiveStartDate;
    }

    public Date getReceiveEndDate() {
        return receiveEndDate;
    }

    public void setReceiveEndDate(Date receiveEndDate) {
        this.receiveEndDate = receiveEndDate;
    }
}

package com.paic.ehis.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 案件申请人信息对象 claim_case_register
 *
 * @author sino
 * @date 2021-01-09
 */
public class ClaimCaseRegister extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 报案号
     */
    private String rptNo;

    /**
     * 申请人姓名
     */
    @Excel(name = "申请人姓名")
    private String rgtName;

    /**
     * 申请人性别
     */
    @Excel(name = "申请人性别")
    private String rgtSex;

    /**
     * 申请人证件类型
     */
    @Excel(name = "申请人证件类型")
    private String rgtIdType;

    /**
     * 申请人证件号
     */
    @Excel(name = "申请人证件号")
    private String rgtIdNo;

    /**
     * 申请人生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请人生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rgtBirthday;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String rgtMobile;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String rgtEmail;

    /**
     * 电话号
     */
    @Excel(name = "电话号")
    private String rgtPhone;

    /**
     * 省
     */
    @Excel(name = "省")
    private String rgtProvince;

    /**
     * 市
     */
    @Excel(name = "市")
    private String rgtCity;

    /**
     * 区
     */
    @Excel(name = "区")
    private String rgtDistrict;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址")
    private String rgtAddress;

    private String relationIns;

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

    public void setRgtName(String rgtName) {
        this.rgtName = rgtName;
    }

    public String getRgtName() {
        return rgtName;
    }

    public void setRgtSex(String rgtSex) {
        this.rgtSex = rgtSex;
    }

    public String getRgtSex() {
        return rgtSex;
    }

    public void setRgtIdType(String rgtIdType) {
        this.rgtIdType = rgtIdType;
    }

    public String getRgtIdType() {
        return rgtIdType;
    }

    public void setRgtIdNo(String rgtIdNo) {
        this.rgtIdNo = rgtIdNo;
    }

    public String getRgtIdNo() {
        return rgtIdNo;
    }

    public void setRgtBirthday(Date rgtBirthday) {
        this.rgtBirthday = rgtBirthday;
    }

    public Date getRgtBirthday() {
        return rgtBirthday;
    }

    public void setRgtMobile(String rgtMobile) {
        this.rgtMobile = rgtMobile;
    }

    public String getRgtMobile() {
        return rgtMobile;
    }

    public void setRgtEmail(String rgtEmail) {
        this.rgtEmail = rgtEmail;
    }

    public String getRgtEmail() {
        return rgtEmail;
    }

    public void setRgtPhone(String rgtPhone) {
        this.rgtPhone = rgtPhone;
    }

    public String getRgtPhone() {
        return rgtPhone;
    }

    public void setRgtProvince(String rgtProvince) {
        this.rgtProvince = rgtProvince;
    }

    public String getRgtProvince() {
        return rgtProvince;
    }

    public void setRgtCity(String rgtCity) {
        this.rgtCity = rgtCity;
    }

    public String getRgtCity() {
        return rgtCity;
    }

    public void setRgtDistrict(String rgtDistrict) {
        this.rgtDistrict = rgtDistrict;
    }

    public String getRgtDistrict() {
        return rgtDistrict;
    }

    public void setRgtAddress(String rgtAddress) {
        this.rgtAddress = rgtAddress;
    }

    public String getRgtAddress() {
        return rgtAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getRelationIns() {
        return relationIns;
    }

    public void setRelationIns(String relationIns) {
        this.relationIns = relationIns;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rptNo", getRptNo())
                .append("rgtName", getRgtName())
                .append("rgtSex", getRgtSex())
                .append("rgtIdType", getRgtIdType())
                .append("rgtIdNo", getRgtIdNo())
                .append("rgtBirthday", getRgtBirthday())
                .append("rgtMobile", getRgtMobile())
                .append("rgtEmail", getRgtEmail())
                .append("rgtPhone", getRgtPhone())
                .append("rgtProvince", getRgtProvince())
                .append("rgtCity", getRgtCity())
                .append("rgtDistrict", getRgtDistrict())
                .append("rgtAddress", getRgtAddress())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("relationIns", getRelationIns())
                .toString();
    }
}

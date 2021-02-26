package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class ClaimCaseDiscussionVO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "监控时效", width = 30, dateFormat = "dd-HH-mm")
    private String monitoringTime;//监控时效=监控时效=当前系统时间-案件受理完成时间，格式为：a天b时c分,仅在处理中工作池显示
    @Excel(name = "停留时长", width = 30, dateFormat = "dd-HH-mm")
    private String stopTime;//停留时长=当前系统时间-协谈接收时间，格式为：a天b时c分,仅在处理中工作池显示
    //批次号
    @Excel(name = "批次号")
    private String batchNo;//claim_case
    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;
    /**交单来源*/
    @Excel(name = "交单来源")
    private String source;
    /** 出单公司名称 */
    @Excel(name = "出单公司名称")
    private String companyName;
    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;
    /** 协谈类型 */
    @Excel(name = "协谈类型")
    private String discType;
    /*提交用户*/
    @Excel(name = "提交用户")
    private String createBy;
    /*案件状态*/
    @Excel(name = "案件状态")
    private String caseStatus;
    /*承保结构*/
    @Excel(name = "承保结构")
    private String organCode;
    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;
    /**年龄*/
    @Excel(name = "年龄")
    private int age;
    /*出险日期*/
    @Excel(name = "出险日期")
    private String accDate;
    /*事故类型*/
    @Excel(name = "事故类型")
    private String accType;
    /*性别*/
    @Excel(name = "性别")
    private String sex;
    /*生日*/
    @Excel(name = "生日")
    private Date birthday;

    /**协谈细类*/
    private String discSubType;

    /** 转出意见 */
    private String disView;

    private String discId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMonitoringTime() {
        return monitoringTime;
    }

    public void setMonitoringTime(String monitoringTime) {
        this.monitoringTime = monitoringTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getRptNo() {
        return rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscType() {
        return discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDiscSubType() {
        return discSubType;
    }

    public void setDiscSubType(String discSubType) {
        this.discSubType = discSubType;
    }

    public String getDisView() {
        return disView;
    }

    public void setDisView(String disView) {
        this.disView = disView;
    }

    public String getDiscId() {
        return discId;
    }

    public void setDiscId(String discId) {
        this.discId = discId;
    }
}

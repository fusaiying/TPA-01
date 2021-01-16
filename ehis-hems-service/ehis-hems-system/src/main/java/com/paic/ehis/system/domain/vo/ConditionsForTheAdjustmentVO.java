package com.paic.ehis.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;

import java.io.Serializable;

public class ConditionsForTheAdjustmentVO implements Serializable {
    private static final long serialVersionUID = 1L;
    //批次号
    @Excel(name = "批次号")
    private String batchNo;//claim_case
    //报案号
    @Excel(name = "报案号")
    private String rptNo;//claim_case
    //案件状态
    @Excel(name = "批次状态 00-挂起 01-交单复核 02-交单退回 03-交单失败 04-交单完成 05-受理 30-问题件 97-撤件可申诉 98-撤件 06-录入 07-审核 31-协谈 32-调查 08-抽检 99-结案")
    private String caseStatus;//claim_case
    //被保人姓名
    @Excel(name = "被保人姓名")
    private String name;//claim_case_insured
    //出单公司
    @Excel(name = "出单公司")
    private String company_name;//policy_info
    //承保机构
    @Excel(name = "承保机构")
    private String organ_code;//交单机构编码
    //停留时长
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "停留时长", width = 30, dateFormat = "dd-HH-mm")
    private String stayTime;//停留时长=当前系统时间-审核接收时间，格式为：a天b时c分，仅在处理中工作池显示
    //监控时效
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "监控时效", width = 30, dateFormat = "dd-HH-mm")
    private String monitoringTime;//监控时效=监控时效=当前系统时间-案件受理完成时间，格式为：a天b时c分,仅在处理中工作池显示
    //是否调查
    @Excel(name = "是否调查", readConverterExp = "01=是,02=否")
    private String surveyCode;//是否调查   01=“是”，02=“否”
    //提交用户
    @Excel(name = "提交用户")
    private String update_by;//更新人

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

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getOrgan_code() {
        return organ_code;
    }

    public void setOrgan_code(String organ_code) {
        this.organ_code = organ_code;
    }

    public String getStayTime() {
        return stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    public String getMonitoringTime() {
        return monitoringTime;
    }

    public void setMonitoringTime(String monitoringTime) {
        this.monitoringTime = monitoringTime;
    }

    public String getSurveyCode() {
        return surveyCode;
    }

    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    @Override
    public String toString() {
        return "ConditionsForTheAdjustmentVO{" +
                "batchNo='" + batchNo + '\'' +
                ", rptNo='" + rptNo + '\'' +
                ", caseStatus='" + caseStatus + '\'' +
                ", name='" + name + '\'' +
                ", company_name='" + company_name + '\'' +
                ", organ_code='" + organ_code + '\'' +
                ", stayTime='" + stayTime + '\'' +
                ", monitoringTime='" + monitoringTime + '\'' +
                ", surveyCode='" + surveyCode + '\'' +
                ", update_by='" + update_by + '\'' +
                '}';
    }
}

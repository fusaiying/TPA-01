package com.paic.ehis.cs.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data
public class QualityDTO {

    /** 结案日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;
    private String endCaseStartDate;
    private String endCaseEndDate;

    /** 质检日期*/
    private Date updatedTime;
    private String inspectionStartDate;
    private String inspectionEndDate;

    /** 出单机构 */
    @Excel(name = "出单机构")
    private String organCode;

    /** 质检人*/
    private String updatedBy;

    /** 服务项目*/
    private String itemCode;

    /** 质检状态*/
    @Excel(name = "质检状态")
    private String status;

    /** 质检结果*/
    @Excel(name = "质检结果")
    private String result;

    private String num1;
    private String num2;
    private String res;

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndCaseStartDate() {
        return endCaseStartDate;
    }

    public void setEndCaseStartDate(String endCaseStartDate) {
        this.endCaseStartDate = endCaseStartDate;
    }

    public String getEndCaseEndDate() {
        return endCaseEndDate;
    }

    public void setEndCaseEndDate(String endCaseEndDate) {
        this.endCaseEndDate = endCaseEndDate;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getInspectionStartDate() {
        return inspectionStartDate;
    }

    public void setInspectionStartDate(String inspectionStartDate) {
        this.inspectionStartDate = inspectionStartDate;
    }

    public String getInspectionEndDate() {
        return inspectionEndDate;
    }

    public void setInspectionEndDate(String inspectionEndDate) {
        this.inspectionEndDate = inspectionEndDate;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setEndCaseStartDate() {
    }

    public void setModifyBy() {
    }

    public void setEndCaseEndDate() {
    }
}

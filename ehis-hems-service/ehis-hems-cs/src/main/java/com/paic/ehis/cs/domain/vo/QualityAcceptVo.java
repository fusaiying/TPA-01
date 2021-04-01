package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class QualityAcceptVo {

    @Excel(name = "工单号")
    private String workOrderNo;

    /** 质检编号 */
    private String inspectionId;

    private String businessType;

    private String policyNo;

    private String policyItemNo;

    @Excel(name = "出单机构",readConverterExp = "0000001=上海分公司本部,00000011=上海分公司")
    private String organCode;

   // @Excel(name = "服务项目",readConverterExp = "01=增值服务类,02=理赔类,03=其他,04=VIP门诊预约,05=特殊门诊直接结算服务,06=门诊直接结算,07=住院直接结算,08=门诊中医治疗直接结算,B00006=投诉,B00034=根因改善")
    private String itemCode;

    @Excel(name = "处理人")
    private String modifyBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    //@Excel(name = "质检分组")
    private String itemType;

    //@Excel(name = "质检人")
    private String updatedBy;

   // @Excel(name = "质检日期")
    private String updatedTime;

    private String createdTime;


    @Excel(name = "质检状态")
    private String status;

    @Excel(name = "质检结果",readConverterExp = "01=合格,02=不合格")
    private String result;

    private String num;


    public String getWorkOrderNo() {
        return workOrderNo;
    }

    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyItemNo() {
        return policyItemNo;
    }

    public void setPolicyItemNo(String policyItemNo) {
        this.policyItemNo = policyItemNo;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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
}

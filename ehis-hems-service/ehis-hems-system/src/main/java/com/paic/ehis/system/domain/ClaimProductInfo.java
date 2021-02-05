package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 产品信息对象 claim_product
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimProductInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品编码 */
    private String riskCode;

    /** 产品名称 */
    private String riskName;

    /** 产品性质 */
    private String riskNature;

    /** 产品类别 */
    private String riskType;

    /** 产品分类 */
    private String riskClass;

    /** 产品状态 */
    private String riskStatus;

    /** 同步日期 */
    private Date synchronizeTime;

    /** 审核结论 */
    private String examineConclusion;

    /** 结论说明 */
    private String conclusionExplanation;

    /** 状态（Y-有效，N-无效） */
    private String status;

    /** 等待期 */
    private String waitingPeriod;

    /** 出单公司 */
    private String company;

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskNature() {
        return riskNature;
    }

    public void setRiskNature(String riskNature) {
        this.riskNature = riskNature;
    }


    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(String riskClass) {
        this.riskClass = riskClass;
    }

    public String getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(String riskStatus) {
        this.riskStatus = riskStatus;
    }


    public Date getSynchronizeTime() {
        return synchronizeTime;
    }

    public void setSynchronizeTime(Date synchronizeTime) {
        this.synchronizeTime = synchronizeTime;
    }

    public String getExamineConclusion() {
        return examineConclusion;
    }

    public void setExamineConclusion(String examineConclusion) {
        this.examineConclusion = examineConclusion;
    }

    public String getConclusionExplanation() {
        return conclusionExplanation;
    }

    public void setConclusionExplanation(String conclusionExplanation) {
        this.conclusionExplanation = conclusionExplanation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(String waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("riskCode", getRiskCode())
            .append("riskName", getRiskName())
            .append("riskNature", getRiskNature())
            .append("riskType", getRiskType())
            .append("riskClass", getRiskClass())
            .append("riskStatus", getRiskStatus())
            .append("synchronizeTime", getSynchronizeTime())
            .append("examineConclusion", getExamineConclusion())
            .append("conclusionExplanation", getConclusionExplanation())
            .append("status", getStatus())
            .append("waitingPeriod", getWaitingPeriod())
            .append("company", getCompany())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

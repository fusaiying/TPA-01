package com.paic.ehis.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 产品信息对象 claim_product
 * 
 * @author sino
 * @date 2021-01-14
 */
public class ClaimProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品编码 */
    private String riskCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String riskName;

    /** 产品性质 */
    @Excel(name = "产品性质")
    private String riskNature;

    /** 产品类别 */
    @Excel(name = "产品类别")
    private String riskType;

    /** 产品分类 */
    @Excel(name = "产品分类")
    private String riskClass;

    /** 产品状态 */
    @Excel(name = "产品状态")
    private String riskStatus;

    /** 等待期 */
    @Excel(name = "等待期")
    private String waitingPeriod;

    /** 同步日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date synchronizeTime;

    /** 审核结论 */
    @Excel(name = "审核结论")
    private String examineConclusion;

    /** 结论说明 */
    @Excel(name = "结论说明")
    private String conclusionExplanation;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setRiskCode(String riskCode) 
    {
        this.riskCode = riskCode;
    }

    public String getRiskCode() 
    {
        return riskCode;
    }
    public void setRiskName(String riskName) 
    {
        this.riskName = riskName;
    }

    public String getRiskName() 
    {
        return riskName;
    }
    public void setRiskNature(String riskNature) 
    {
        this.riskNature = riskNature;
    }

    public String getRiskNature() 
    {
        return riskNature;
    }
    public void setRiskType(String riskType) 
    {
        this.riskType = riskType;
    }

    public String getRiskType() 
    {
        return riskType;
    }
    public void setRiskClass(String riskClass) 
    {
        this.riskClass = riskClass;
    }

    public String getRiskClass() 
    {
        return riskClass;
    }
    public void setRiskStatus(String riskStatus) 
    {
        this.riskStatus = riskStatus;
    }

    public String getRiskStatus() 
    {
        return riskStatus;
    }
    public void setWaitingPeriod(String waitingPeriod) 
    {
        this.waitingPeriod = waitingPeriod;
    }

    public String getWaitingPeriod() 
    {
        return waitingPeriod;
    }
    public void setSynchronizeTime(Date synchronizeTime) 
    {
        this.synchronizeTime = synchronizeTime;
    }

    public Date getSynchronizeTime() 
    {
        return synchronizeTime;
    }
    public void setExamineConclusion(String examineConclusion) 
    {
        this.examineConclusion = examineConclusion;
    }

    public String getExamineConclusion() 
    {
        return examineConclusion;
    }
    public void setConclusionExplanation(String conclusionExplanation) 
    {
        this.conclusionExplanation = conclusionExplanation;
    }

    public String getConclusionExplanation() 
    {
        return conclusionExplanation;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("waitingPeriod", getWaitingPeriod())
            .append("synchronizeTime", getSynchronizeTime())
            .append("examineConclusion", getExamineConclusion())
            .append("conclusionExplanation", getConclusionExplanation())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

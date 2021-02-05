package com.paic.ehis.claimcal.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品状态轨迹 对象 claim_product_task_log
 * 
 * @author sino
 * @date 2021-01-07
 */
public class ClaimProductTaskLogInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 轨迹号 */
    private String riskLogNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String riskCode;

    /** 产品状态 */
    @Excel(name = "产品状态")
    private String riskStatus;

    /** 是否历史 */
    @Excel(name = "是否历史")
    private String isHistory;

    /** 审核结论 */
    @Excel(name = "审核结论")
    private String examineConclusion;

    /** 结论说明 */
    @Excel(name = "结论说明")
    private String conclusionExplanation;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setRiskLogNo(String riskLogNo) 
    {
        this.riskLogNo = riskLogNo;
    }

    public String getRiskLogNo() 
    {
        return riskLogNo;
    }
    public void setRiskCode(String riskCode) 
    {
        this.riskCode = riskCode;
    }

    public String getRiskCode() 
    {
        return riskCode;
    }
    public void setRiskStatus(String riskStatus) 
    {
        this.riskStatus = riskStatus;
    }

    public String getRiskStatus() 
    {
        return riskStatus;
    }
    public void setIsHistory(String isHistory) 
    {
        this.isHistory = isHistory;
    }

    public String getIsHistory() 
    {
        return isHistory;
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
            .append("riskLogNo", getRiskLogNo())
            .append("riskCode", getRiskCode())
            .append("riskStatus", getRiskStatus())
            .append("isHistory", getIsHistory())
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

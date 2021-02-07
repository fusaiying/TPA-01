package com.paic.ehis.claimflow.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 案件协谈信息对象 claim_case_discussion
 *
 * @author sino
 * @date 2021-01-15
 */
public class ClaimCaseDiscussion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 协谈ID */
    private Long discId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 协谈类型 */
    @Excel(name = "协谈类型")
    private String discType;

    /** 协谈细类 */
    @Excel(name = "协谈细类")
    private String discSubType;

    /** 转出意见 */
    @Excel(name = "转出意见")
    private String disView;

    /** 协谈结论 */
    @Excel(name = "协谈结论")
    private String conclusion;

    /** 协谈意见 */
    @Excel(name = "协谈意见")
    private String conclusionView;

    /** 是否历史任务 Y-是、N-否 */
    @Excel(name = "是否历史任务 Y-是、N-否")
    private String isHistory;


    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setDiscId(Long discId)
    {
        this.discId = discId;
    }

    public Long getDiscId()
    {
        return discId;
    }
    public void setRptNo(String rptNo)
    {
        this.rptNo = rptNo;
    }

    public String getRptNo()
    {
        return rptNo;
    }
    public void setDiscType(String discType)
    {
        this.discType = discType;
    }

    public String getDiscType()
    {
        return discType;
    }
    public void setDiscSubType(String discSubType)
    {
        this.discSubType = discSubType;
    }

    public String getDiscSubType()
    {
        return discSubType;
    }
    public void setDisView(String disView)
    {
        this.disView = disView;
    }

    public String getDisView()
    {
        return disView;
    }
    public void setConclusion(String conclusion)
    {
        this.conclusion = conclusion;
    }

    public String getConclusion()
    {
        return conclusion;
    }
    public void setConclusionView(String conclusionView)
    {
        this.conclusionView = conclusionView;
    }

    public String getConclusionView()
    {
        return conclusionView;
    }
    public void setIsHistory(String isHistory)
    {
        this.isHistory = isHistory;
    }

    public String getIsHistory()
    {
        return isHistory;
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
                .append("discId", getDiscId())
                .append("rptNo", getRptNo())
                .append("discType", getDiscType())
                .append("discSubType", getDiscSubType())
                .append("disView", getDisView())
                .append("conclusion", getConclusion())
                .append("conclusionView", getConclusionView())
                .append("isHistory", getIsHistory())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

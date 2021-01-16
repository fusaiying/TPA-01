package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 案件问题件对象 claim_case_problem
 *
 * @author sino
 * @date 2021-01-11
 */
public class ClaimCaseProblem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 问题件ID */
    private Long problemId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 问题件类型 */
    @Excel(name = "问题件类型")
    private String problemType;

    /** 转出意见 */
    @Excel(name = "转出意见")
    private String problemView;

    /** 处理结论 */
    @Excel(name = "处理结论")
    private String conclusion;

    /** 处理意见 */
    @Excel(name = "处理意见")
    private String conclusionView;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setProblemId(Long problemId)
    {
        this.problemId = problemId;
    }

    public Long getProblemId()
    {
        return problemId;
    }
    public void setRptNo(String rptNo)
    {
        this.rptNo = rptNo;
    }

    public String getRptNo()
    {
        return rptNo;
    }
    public void setProblemType(String problemType)
    {
        this.problemType = problemType;
    }

    public String getProblemType()
    {
        return problemType;
    }
    public void setProblemView(String problemView)
    {
        this.problemView = problemView;
    }

    public String getProblemView()
    {
        return problemView;
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
                .append("problemId", getProblemId())
                .append("rptNo", getRptNo())
                .append("problemType", getProblemType())
                .append("problemView", getProblemView())
                .append("conclusion", getConclusion())
                .append("conclusionView", getConclusionView())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
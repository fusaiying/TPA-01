package com.paic.ehis.claimflow.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 案件问题件对象 claim_case_problem
 *
 * @author sino
 * @date 2021-01-11
 */
public class ClaimCaseProblem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 批次号 */
    private String batchNo;
    /** 案件类型 */
    private String claimType;

    private String name;

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

    /** 是否历史任务（Y-是，N-否） */
    @Excel(name = "是否历史任务", readConverterExp = "Y=是,N=否")
    private String isHistory;

    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;

    /**
     * 交单来源
     */
    @Excel(name = "交单来源")
    private String source;

    /**停留时间*/
    @Excel(name = "停留时间")
    private String stopTime;

    /** 前一个月日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dBefore1;

    /** 现在日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dNow1;

    public String getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(String isHistory) {
        this.isHistory = isHistory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getdBefore1() {
        return dBefore1;
    }

    public void setdBefore1(Date dBefore1) {
        this.dBefore1 = dBefore1;
    }

    public Date getdNow1() {
        return dNow1;
    }

    public void setdNow1(Date dNow1) {
        this.dNow1 = dNow1;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
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
                .append("isHistory", getIsHistory())
                .toString();
    }
}
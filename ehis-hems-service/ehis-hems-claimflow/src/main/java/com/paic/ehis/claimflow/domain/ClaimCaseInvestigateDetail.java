package com.paic.ehis.claimflow.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;

/**
 * 案件调查明细对象 claim_case_investigate_detail
 * 
 * @author sino
 * @date 2021-03-04
 */
public class ClaimCaseInvestigateDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 调查序号 */
    private Long investigateDetailNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 提调类型 */
    @Excel(name = "提调类型")
    private String invDetailType;

    /** 调查状态 */
    @Excel(name = "调查状态")
    private String invDetailStatus;

    /** 调查原因 */
    @Excel(name = "调查原因")
    private String invDetailCause;

    /** 交查人 */
    @Excel(name = "交查人")
    private String invDetailPeople;

    /** 交查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invDetailTime;

    /** 调查项目 */
    @Excel(name = "调查项目")
    private String invDetailItem;

    /** 调查定性 */
    @Excel(name = "调查定性")
    private String invDetailNature;

    /** 调查定性依据 */
    @Excel(name = "调查定性依据")
    private String invDetailGist;

    /** 事实与依据 */
    @Excel(name = "事实与依据")
    private String invDetailFace;

    /** 结论 */
    @Excel(name = "结论")
    private String conclusion;

    /** 证明材料及件数 */
    @Excel(name = "证明材料及件数")
    private String invDetailNum;

    /** 主管补充 */
    @Excel(name = "主管补充")
    private String invDetailReplenish;

    /** 是否有异地调查 */
    @Excel(name = "是否有异地调查")
    private String invDetailSurvey;

    /** 是否扫描 */
    @Excel(name = "是否扫描")
    private String invDetailScan;

    /** 阳性资料证据 */
    @Excel(name = "阳性资料证据")
    private String invDetailProof;

    /** 查迄时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "查迄时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invDetailAfterTime;

    /** 调查机构 */
    @Excel(name = "调查机构")
    private String invDetailOrgan;

    /** 调查人 */
    @Excel(name = "调查人")
    private String invDetailAfterPeople;

    /** 任务退回理由 */
    @Excel(name = "任务退回理由")
    private String invDetailArgument;

    /** 结果退回理由 */
    @Excel(name = "结果退回理由")
    private String invDetailResultArgument;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setInvestigateDetailNo(Long investigateDetailNo) 
    {
        this.investigateDetailNo = investigateDetailNo;
    }
    public Long getInvestigateDetailNo() 
    {
        return investigateDetailNo;
    }

    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }
    public String getRptNo() 
    {
        return rptNo;
    }

    public void setInvDetailType(String invDetailType) 
    {
        this.invDetailType = invDetailType;
    }
    public String getInvDetailType() 
    {
        return invDetailType;
    }

    public void setInvDetailStatus(String invDetailStatus) 
    {
        this.invDetailStatus = invDetailStatus;
    }
    public String getInvDetailStatus() 
    {
        return invDetailStatus;
    }

    public void setInvDetailCause(String invDetailCause) 
    {
        this.invDetailCause = invDetailCause;
    }

    public String getInvDetailCause() 
    {
        return invDetailCause;
    }
    public void setInvDetailPeople(String invDetailPeople) 
    {
        this.invDetailPeople = invDetailPeople;
    }

    public String getInvDetailPeople() 
    {
        return invDetailPeople;
    }

    public void setInvDetailTime(Date invDetailTime) 
    {
        this.invDetailTime = invDetailTime;
    }

    public Date getInvDetailTime() 
    {
        return invDetailTime;
    }

    public void setInvDetailItem(String invDetailItem) 
    {
        this.invDetailItem = invDetailItem;
    }

    public String getInvDetailItem() 
    {
        return invDetailItem;
    }

    public void setInvDetailNature(String invDetailNature) 
    {
        this.invDetailNature = invDetailNature;
    }

    public String getInvDetailNature() 
    {
        return invDetailNature;
    }

    public void setInvDetailGist(String invDetailGist) 
    {
        this.invDetailGist = invDetailGist;
    }

    public String getInvDetailGist() 
    {
        return invDetailGist;
    }

    public void setInvDetailFace(String invDetailFace) 
    {
        this.invDetailFace = invDetailFace;
    }

    public String getInvDetailFace() 
    {
        return invDetailFace;
    }

    public void setConclusion(String conclusion) 
    {
        this.conclusion = conclusion;
    }

    public String getConclusion() 
    {
        return conclusion;
    }

    public void setInvDetailNum(String invDetailNum) 
    {
        this.invDetailNum = invDetailNum;
    }

    public String getInvDetailNum() 
    {
        return invDetailNum;
    }
    public void setInvDetailReplenish(String invDetailReplenish) 
    {
        this.invDetailReplenish = invDetailReplenish;
    }

    public String getInvDetailReplenish() 
    {
        return invDetailReplenish;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setInvDetailSurvey(String invDetailSurvey) 
    {
        this.invDetailSurvey = invDetailSurvey;
    }

    public String getInvDetailSurvey() 
    {
        return invDetailSurvey;
    }
    public void setInvDetailScan(String invDetailScan) 
    {
        this.invDetailScan = invDetailScan;
    }

    public String getInvDetailScan() 
    {
        return invDetailScan;
    }
    public void setInvDetailProof(String invDetailProof) 
    {
        this.invDetailProof = invDetailProof;
    }

    public String getInvDetailProof() 
    {
        return invDetailProof;
    }
    public void setInvDetailAfterTime(Date invDetailAfterTime) 
    {
        this.invDetailAfterTime = invDetailAfterTime;
    }

    public Date getInvDetailAfterTime() 
    {
        return invDetailAfterTime;
    }
    public void setInvDetailOrgan(String invDetailOrgan) 
    {
        this.invDetailOrgan = invDetailOrgan;
    }

    public String getInvDetailOrgan() 
    {
        return invDetailOrgan;
    }
    public void setInvDetailAfterPeople(String invDetailAfterPeople) 
    {
        this.invDetailAfterPeople = invDetailAfterPeople;
    }

    public String getInvDetailAfterPeople() 
    {
        return invDetailAfterPeople;
    }
    public void setInvDetailArgument(String invDetailArgument) 
    {
        this.invDetailArgument = invDetailArgument;
    }

    public String getInvDetailArgument() 
    {
        return invDetailArgument;
    }
    public void setInvDetailResultArgument(String invDetailResultArgument) 
    {
        this.invDetailResultArgument = invDetailResultArgument;
    }

    public String getInvDetailResultArgument() 
    {
        return invDetailResultArgument;
    }

    @Override
    public String toString() {
        return "ClaimCaseInvestigateDetail{" +
                "investigateDetailNo=" + investigateDetailNo +
                ", rptNo='" + rptNo + '\'' +
                ", invDetailType='" + invDetailType + '\'' +
                ", invDetailStatus='" + invDetailStatus + '\'' +
                ", invDetailCause='" + invDetailCause + '\'' +
                ", invDetailPeople='" + invDetailPeople + '\'' +
                ", invDetailTime=" + invDetailTime +
                ", invDetailItem='" + invDetailItem + '\'' +
                ", invDetailNature='" + invDetailNature + '\'' +
                ", invDetailGist='" + invDetailGist + '\'' +
                ", invDetailFace='" + invDetailFace + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", invDetailNum='" + invDetailNum + '\'' +
                ", invDetailReplenish='" + invDetailReplenish + '\'' +
                ", invDetailSurvey='" + invDetailSurvey + '\'' +
                ", invDetailScan='" + invDetailScan + '\'' +
                ", invDetailProof='" + invDetailProof + '\'' +
                ", invDetailAfterTime=" + invDetailAfterTime +
                ", invDetailOrgan='" + invDetailOrgan + '\'' +
                ", invDetailAfterPeople='" + invDetailAfterPeople + '\'' +
                ", invDetailArgument='" + invDetailArgument + '\'' +
                ", invDetailResultArgument='" + invDetailResultArgument + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

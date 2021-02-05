package com.paic.ehis.order.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * service_balance_exam_log(服务结算审核日志)对象 service_balance_exam_log
 *
 * @author chenhaiming
 * @date 2021-01-25
 */
public class ServiceBalanceExamLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long serialNo;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNo;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 业务状态 */
    @Excel(name = "业务状态")
    private String bussinessStatus;

    /** 审核结论 */
    @Excel(name = "审核结论")
    private String examineConclusion;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String examineOpinion;

    public void setSerialNo(Long serialNo)
    {
        this.serialNo = serialNo;
    }

    public Long getSerialNo()
    {
        return serialNo;
    }
    public void setTaskNo(String taskNo)
    {
        this.taskNo = taskNo;
    }

    public String getTaskNo()
    {
        return taskNo;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setBussinessStatus(String bussinessStatus)
    {
        this.bussinessStatus = bussinessStatus;
    }

    public String getBussinessStatus()
    {
        return bussinessStatus;
    }
    public void setExamineConclusion(String examineConclusion)
    {
        this.examineConclusion = examineConclusion;
    }

    public String getExamineConclusion()
    {
        return examineConclusion;
    }
    public void setExamineOpinion(String examineOpinion)
    {
        this.examineOpinion = examineOpinion;
    }

    public String getExamineOpinion()
    {
        return examineOpinion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("serialNo", getSerialNo())
                .append("taskNo", getTaskNo())
                .append("status", getStatus())
                .append("bussinessStatus", getBussinessStatus())
                .append("examineConclusion", getExamineConclusion())
                .append("examineOpinion", getExamineOpinion())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

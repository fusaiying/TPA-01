package com.paic.ehis.claimcal.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 案件操作记录 对象 claim_case_record
 *
 * @author sino
 * @date 2021-01-09
 */
public class ClaimCaseRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 流程记录ID
     */
    private Long recordId;

    /**
     * 报案号
     */
    @Excel(name = "报案号")
    private String rptNo;

    /**
     * 流程节点
     */
    @Excel(name = "流程节点")
    private String operation;

    /**
     * 流程节点操作人
     */
    @Excel(name = "流程节点操作人")
    private String operator;

    /**
     * 是否历史节点（Y-是，N-否）
     */
    @Excel(name = "是否历史节点", readConverterExp = "Y=是,N=否")
    private String historyFlag;

    /**
     * 数据状态（Y-有效，N-无效）
     */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    //上一环节流程记录ID
    private Long orgRecordId;

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getRptNo() {
        return rptNo;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setHistoryFlag(String historyFlag) {
        this.historyFlag = historyFlag;
    }

    public String getHistoryFlag() {
        return historyFlag;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Long getOrgRecordId() {
        return orgRecordId;
    }

    public void setOrgRecordId(Long orgRecordId) {
        this.orgRecordId = orgRecordId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("recordId", getRecordId())
                .append("rptNo", getRptNo())
                .append("operation", getOperation())
                .append("operator", getOperator())
                .append("historyFlag", getHistoryFlag())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("orgRecordId", getOrgRecordId())
                .toString();
    }
}

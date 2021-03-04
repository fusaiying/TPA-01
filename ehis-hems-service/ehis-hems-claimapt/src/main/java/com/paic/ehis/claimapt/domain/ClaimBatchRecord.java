package com.paic.ehis.claimapt.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 理赔批次流程记录 对象 claim_batch_record
 *
 * @author sino
 * @date 2021-01-05
 */
public class ClaimBatchRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 流程记录ID
     */
    private Long recordid;

    /**
     * 批次号
     */
    @Excel(name = "批次号")
    private String batchno;

    /**
     * 流程节点
     */
    @Excel(name = "流程节点")
    private String operation;

    /**
     * 结论
     */
    @Excel(name = "结论")
    private String conclusion;

    /**
     * 不通过类型
     */
    @Excel(name = "不通过类型")
    private String nopasstype;

    /**
     * 状态（Y-有效，N-无效）
     */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;


    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getNopasstype() {
        return nopasstype;
    }

    public void setNopasstype(String nopasstype) {
        this.nopasstype = nopasstype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("recordid", getRecordid())
                .append("batchno", getBatchno())
                .append("operation", getOperation())
                .append("conclusion", getConclusion())
                .append("nopasstype", getNopasstype())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

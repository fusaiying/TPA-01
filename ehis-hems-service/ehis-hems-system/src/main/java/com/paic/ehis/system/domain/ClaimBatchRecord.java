package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 理赔批次流程记录 对象 claim_batch_record
 * 
 * @author sino
 * @date 2021-01-05
 */
public class ClaimBatchRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流程记录ID */
    private Long recordId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 流程节点 */
    @Excel(name = "流程节点")
    private String operation;

    /** 结论 */
    @Excel(name = "结论")
    private String conclusion;

    /** 不通过类型 */
    @Excel(name = "不通过类型")
    private String nopassType;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setOperation(String operation) 
    {
        this.operation = operation;
    }

    public String getOperation() 
    {
        return operation;
    }
    public void setConclusion(String conclusion) 
    {
        this.conclusion = conclusion;
    }

    public String getConclusion() 
    {
        return conclusion;
    }
    public void setNopassType(String nopassType) 
    {
        this.nopassType = nopassType;
    }

    public String getNopassType() 
    {
        return nopassType;
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
            .append("recordId", getRecordId())
            .append("batchNo", getBatchNo())
            .append("operation", getOperation())
            .append("conclusion", getConclusion())
            .append("nopassType", getNopassType())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

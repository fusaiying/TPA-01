package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 费用项信息 对象 base_feeitem
 * 
 * @author sino
 * @date 2021-01-05
 */
public class BaseFeeitem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 费用项编码 */
    private String feeitemCode;

    /** 费用项名称 */
    @Excel(name = "费用项名称")
    private String feeitemName;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setFeeitemCode(String feeitemCode) 
    {
        this.feeitemCode = feeitemCode;
    }

    public String getFeeitemCode() 
    {
        return feeitemCode;
    }
    public void setFeeitemName(String feeitemName) 
    {
        this.feeitemName = feeitemName;
    }

    public String getFeeitemName() 
    {
        return feeitemName;
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
            .append("feeitemCode", getFeeitemCode())
            .append("feeitemName", getFeeitemName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

package com.paic.ehis.base.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

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
    @Excel(name = "费用项编码")
    private String feeitemcode;

    /** 费用项名称 */
    @Excel(name = "费用项名称")
    private String feeitemname;

    /** 状态（Y-有效，N-无效） */
    private String status;

    /** 创建时间 */
    @Excel(name ="创建日期",dateFormat = "yyyy-MM-dd" )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 创建者 */
    @Excel(name ="创建者" )
    private String createBy;

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setFeeitemcode(String feeitemcode)
    {
        this.feeitemcode = feeitemcode;
    }

    public String getFeeitemcode()
    {
        return feeitemcode;
    }
    public void setFeeitemname(String feeitemname)
    {
        this.feeitemname = feeitemname;
    }

    public String getFeeitemname()
    {
        return feeitemname;
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
            .append("feeitemcode", getFeeitemcode())
            .append("feeitemname", getFeeitemname())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

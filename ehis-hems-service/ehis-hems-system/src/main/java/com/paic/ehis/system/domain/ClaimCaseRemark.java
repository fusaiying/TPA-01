package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 案件备注对象 claim_case_remark
 * 
 * @author sino
 * @date 2021-01-09
 */
public class ClaimCaseRemark extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 备注ID */
    private Long remarkId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 备注类型 */
    @Excel(name = "备注类型")
    private String remarkType;

    /** 岗位编码 */
    @Excel(name = "岗位编码")
    private String station;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setRemarkId(Long remarkId) 
    {
        this.remarkId = remarkId;
    }

    public Long getRemarkId() 
    {
        return remarkId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setRemarkType(String remarkType) 
    {
        this.remarkType = remarkType;
    }

    public String getRemarkType() 
    {
        return remarkType;
    }
    public void setStation(String station) 
    {
        this.station = station;
    }

    public String getStation() 
    {
        return station;
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
            .append("remarkId", getRemarkId())
            .append("rptNo", getRptNo())
            .append("remarkType", getRemarkType())
            .append("remark", getRemark())
            .append("station", getStation())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

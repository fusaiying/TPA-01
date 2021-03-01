package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 业务码 对象 code_dict
 * 
 * @author sino
 * @date 2021-02-27
 */
public class CodeDict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码类型 类型code */
    private String codeType;

    /** 编码值 code值 */
    private String code;

    /** 编码名称 字面意思 */
    @Excel(name = "编码名称 字面意思")
    private String codeName;

    /** 编码简称 */
    @Excel(name = "编码简称")
    private String codeAlias;

    /** 编码状态 有效状态: 1-有效；0-无效 */
    @Excel(name = "编码状态 有效状态: 1-有效；0-无效")
    private String status;

    /** 父节点 父节点Code值 */
    @Excel(name = "父节点 父节点Code值")
    private String parentCode;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 编码排序 排序 */
    @Excel(name = "编码排序 排序")
    private Long orderNo;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setCodeType(String codeType) 
    {
        this.codeType = codeType;
    }

    public String getCodeType() 
    {
        return codeType;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setCodeName(String codeName) 
    {
        this.codeName = codeName;
    }

    public String getCodeName() 
    {
        return codeName;
    }
    public void setCodeAlias(String codeAlias) 
    {
        this.codeAlias = codeAlias;
    }

    public String getCodeAlias() 
    {
        return codeAlias;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setParentCode(String parentCode) 
    {
        this.parentCode = parentCode;
    }

    public String getParentCode() 
    {
        return parentCode;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setOrderNo(Long orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Long getOrderNo() 
    {
        return orderNo;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("codeType", getCodeType())
            .append("code", getCode())
            .append("codeName", getCodeName())
            .append("codeAlias", getCodeAlias())
            .append("status", getStatus())
            .append("parentCode", getParentCode())
            .append("remarks", getRemarks())
            .append("orderNo", getOrderNo())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}

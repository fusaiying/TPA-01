package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 受理信息映射 对象 field_map
 * 
 * @author sino
 * @date 2021-01-22
 */
public class FieldMap extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 源名称 可对象或者表名 */
    private String sourceName;

    /** 类型编码 不同业务类型等 */
    private String typeCode;

    /** 排序 字段排序 */
    private String fieldOrder;

    /** 源列名 不定字段对应真实名称 */
    private String sourceFiledName;

    /** 源字段名称 不定字段对应的真实名的意思 */
    @Excel(name = "源字段名称 不定字段对应的真实名的意思")
    private String sourceFieldMean;

    /** 目标表名 含有不定字段的表名 */
    @Excel(name = "目标表名 含有不定字段的表名")
    private String targetTableName;

    /** 目标列名 不定字段名 */
    @Excel(name = "目标列名 不定字段名")
    private String targetColumnName;

    /** 目标字段名称 */
    @Excel(name = "目标字段名称")
    private String targetColumnMean;

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

    public void setSourceName(String sourceName) 
    {
        this.sourceName = sourceName;
    }

    public String getSourceName() 
    {
        return sourceName;
    }
    public void setTypeCode(String typeCode) 
    {
        this.typeCode = typeCode;
    }

    public String getTypeCode() 
    {
        return typeCode;
    }
    public void setFieldOrder(String fieldOrder) 
    {
        this.fieldOrder = fieldOrder;
    }

    public String getFieldOrder() 
    {
        return fieldOrder;
    }
    public void setSourceFiledName(String sourceFiledName) 
    {
        this.sourceFiledName = sourceFiledName;
    }

    public String getSourceFiledName() 
    {
        return sourceFiledName;
    }
    public void setSourceFieldMean(String sourceFieldMean) 
    {
        this.sourceFieldMean = sourceFieldMean;
    }

    public String getSourceFieldMean() 
    {
        return sourceFieldMean;
    }
    public void setTargetTableName(String targetTableName) 
    {
        this.targetTableName = targetTableName;
    }

    public String getTargetTableName() 
    {
        return targetTableName;
    }
    public void setTargetColumnName(String targetColumnName) 
    {
        this.targetColumnName = targetColumnName;
    }

    public String getTargetColumnName() 
    {
        return targetColumnName;
    }
    public void setTargetColumnMean(String targetColumnMean) 
    {
        this.targetColumnMean = targetColumnMean;
    }

    public String getTargetColumnMean() 
    {
        return targetColumnMean;
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
            .append("sourceName", getSourceName())
            .append("typeCode", getTypeCode())
            .append("fieldOrder", getFieldOrder())
            .append("sourceFiledName", getSourceFiledName())
            .append("sourceFieldMean", getSourceFieldMean())
            .append("targetTableName", getTargetTableName())
            .append("targetColumnName", getTargetColumnName())
            .append("targetColumnMean", getTargetColumnMean())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}

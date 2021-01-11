package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * ICD10数据 对象 base_icd10
 * 
 * @author sino
 * @date 2021-01-05
 */
public class BaseIcd10 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ICD10编码 */
    private String icdCode;

    /** ICD10名称 */
    @Excel(name = "ICD10名称")
    private String icdName;

    /** ICD10大类 */
    @Excel(name = "ICD10大类")
    private String icdClass;

    /** 来源（1-基础库，2-人工录入） */
    @Excel(name = "来源", readConverterExp = "1=-基础库，2-人工录入")
    private String source;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setIcdCode(String icdCode) 
    {
        this.icdCode = icdCode;
    }

    public String getIcdCode() 
    {
        return icdCode;
    }
    public void setIcdName(String icdName) 
    {
        this.icdName = icdName;
    }

    public String getIcdName() 
    {
        return icdName;
    }
    public void setIcdClass(String icdClass) 
    {
        this.icdClass = icdClass;
    }

    public String getIcdClass() 
    {
        return icdClass;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
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
            .append("icdCode", getIcdCode())
            .append("icdName", getIcdName())
            .append("icdClass", getIcdClass())
            .append("source", getSource())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

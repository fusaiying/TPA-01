package com.paic.ehis.system.api.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * ICD10数据 对象 base_icd10
 * 
 * @author sino
 * @date 2021-01-05
 */
public class BaseIcd10 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //@Excel(name = "流水号")
    private String icdNo;

    /** ICD10编码 */
    @Excel(name = "ICD10编码")
    private String icdcode;

    /** ICD10名称 */
    @Excel(name = "ICD10名称")
    private String icdmname;

    /** 状态（1-有效，2-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    /** 来源（1-基础库，2-人工录入） */
    @Excel(name = "来源", readConverterExp = "1=基础库,2=人工录入")
    private String source;

    /** 创建者 */
    @Excel(name = "创建人")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建日期",dateFormat =  "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /*大类*/
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

    public String getIcdNo() {
        return icdNo;
    }

    public void setIcdNo(String icdNo) {
        this.icdNo = icdNo;
    }

    private String icdClass;

    public String getIcdClass() {
        return icdClass;
    }

    public void setIcdClass(String icdClass) {
        this.icdClass = icdClass;
    }

    public String getIcdcode() {
        return icdcode;
    }

    public void setIcdcode(String icdcode) {
        this.icdcode = icdcode;
    }

    public String getIcdmname() {
        return icdmname;
    }

    public void setIcdmname(String icdmname) {
        this.icdmname = icdmname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("icdcode", getIcdcode())
                .append("icdmname", getIcdmname())
                .append("status", getStatus())
                .append("source", getSource())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("icdNo", getIcdNo())
                .toString();
    }
}

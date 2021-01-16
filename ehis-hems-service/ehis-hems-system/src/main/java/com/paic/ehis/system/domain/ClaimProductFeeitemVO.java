package com.paic.ehis.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 福利 对象 ClaimProductFeeitemVO
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimProductFeeitemVO
{

    /** 编码 */
    private String value;

    /** 名称 */
    private String label;

    /** 计划编码 */
    private String planCode;

    /** 责任编码 */
    private String dutyCode;

    /** 责任明细编码 */
    private String dutyDetailCode;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public String getDutyDetailCode() {
        return dutyDetailCode;
    }

    public void setDutyDetailCode(String dutyDetailCode) {
        this.dutyDetailCode = dutyDetailCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("value", getValue())
            .append("label", getLabel())
            .append("planCode", getPlanCode())
            .append("dutyCode", getDutyCode())
            .append("dutyDetailCode", getDutyDetailCode())
            .toString();
    }
}

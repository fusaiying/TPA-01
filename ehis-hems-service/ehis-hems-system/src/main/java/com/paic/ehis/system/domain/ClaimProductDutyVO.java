package com.paic.ehis.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 福利 对象 ClaimProductDutyVO
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimProductDutyVO
{

    /** 编码 */
    private String value;

    /** 名称 */
    private String label;

    /** 计划编码 */
    private String planCode;

    /** 子列表 */
    private List<com.paic.ehis.system.domain.ClaimProductDutyDetailVO> children;

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

    public List<com.paic.ehis.system.domain.ClaimProductDutyDetailVO> getChildren() {
        return children;
    }

    public void setChildren(List<com.paic.ehis.system.domain.ClaimProductDutyDetailVO> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("value", getValue())
            .append("label", getLabel())
            .append("planCode", getPlanCode())
            .append("children", getChildren().toString())
            .toString();
    }
}

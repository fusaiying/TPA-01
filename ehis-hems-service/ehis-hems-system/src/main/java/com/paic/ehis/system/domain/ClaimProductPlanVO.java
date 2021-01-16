package com.paic.ehis.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 福利 对象 ClaimProductPlanVO
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimProductPlanVO
{

    /** 编码 */
    private String value;

    /** 名称 */
    private String label;

    /** 子列表 */
    private List<ClaimProductDutyVO> children;

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

    public List<ClaimProductDutyVO> getChildren() {
        return children;
    }

    public void setChildren(List<ClaimProductDutyVO> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("value", getValue())
            .append("lable", getLabel())
            .append("children", getChildren().toString())
            .toString();
    }
}

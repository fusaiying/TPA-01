package com.paic.ehis.system.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 规则条件对象 hmp_benefit_rule_condition
 * 
 * @author sino
 * @date 2020-11-23
 */
public class HmpBenefitRuleCondition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则编码 */
    @Excel(name = "规则编码")
    private String rulecode;

    /** 条件类型 */
    private String conditiontype;

    /** 条件编码 */
    private String conditioncode;

    /** 条件名称 */
    @Excel(name = "条件名称")
    private String conditionname;

    public void setRulecode(String rulecode) 
    {
        this.rulecode = rulecode;
    }

    public String getRulecode() 
    {
        return rulecode;
    }
    public void setConditiontype(String conditiontype) 
    {
        this.conditiontype = conditiontype;
    }

    public String getConditiontype() 
    {
        return conditiontype;
    }
    public void setConditioncode(String conditioncode) 
    {
        this.conditioncode = conditioncode;
    }

    public String getConditioncode() 
    {
        return conditioncode;
    }
    public void setConditionname(String conditionname) 
    {
        this.conditionname = conditionname;
    }

    public String getConditionname() 
    {
        return conditionname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("rulecode", getRulecode())
            .append("conditiontype", getConditiontype())
            .append("conditioncode", getConditioncode())
            .append("conditionname", getConditionname())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

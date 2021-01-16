package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 规则条件 对象 claim_rule_constraint
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimRuleConstraint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 条件号 */
    private String constraintNo;

    /** 规则号 */
    @Excel(name = "规则号")
    private String ruleNo;

    /** 条件类型 */
    @Excel(name = "条件类型")
    private String constraintType;

    /** 条件值1 */
    @Excel(name = "条件值1")
    private String value1;

    /** 条件值2(区间条件时使用) */
    @Excel(name = "条件值2(区间条件时使用)")
    private String value2;

    /** 条件单位 */
    @Excel(name = "条件单位")
    private String constraintUnit;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setConstraintNo(String constraintNo) 
    {
        this.constraintNo = constraintNo;
    }

    public String getConstraintNo() 
    {
        return constraintNo;
    }
    public void setRuleNo(String ruleNo) 
    {
        this.ruleNo = ruleNo;
    }

    public String getRuleNo() 
    {
        return ruleNo;
    }
    public void setConstraintType(String constraintType) 
    {
        this.constraintType = constraintType;
    }

    public String getConstraintType() 
    {
        return constraintType;
    }
    public void setValue1(String value1) 
    {
        this.value1 = value1;
    }

    public String getValue1() 
    {
        return value1;
    }
    public void setValue2(String value2) 
    {
        this.value2 = value2;
    }

    public String getValue2() 
    {
        return value2;
    }
    public void setConstraintUnit(String constraintUnit) 
    {
        this.constraintUnit = constraintUnit;
    }

    public String getConstraintUnit() 
    {
        return constraintUnit;
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
            .append("constraintNo", getConstraintNo())
            .append("ruleNo", getRuleNo())
            .append("constraintType", getConstraintType())
            .append("value1", getValue1())
            .append("value2", getValue2())
            .append("constraintUnit", getConstraintUnit())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

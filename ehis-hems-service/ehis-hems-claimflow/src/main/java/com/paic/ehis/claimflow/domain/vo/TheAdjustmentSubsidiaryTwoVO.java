package com.paic.ehis.claimflow.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class TheAdjustmentSubsidiaryTwoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ruleNo;//规则编码
    private String ruleDescription;//规则描述
    private String ruleElement;//规则要素
    private BigDecimal elementValue;//要素值
    private String elementUnit;//要素单位
    private Long ruleExcId;//理算规则例外ID

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public String getRuleElement() {
        return ruleElement;
    }

    public void setRuleElement(String ruleElement) {
        this.ruleElement = ruleElement;
    }

    public BigDecimal getElementValue() {
        return elementValue;
    }

    public void setElementValue(BigDecimal elementValue) {
        this.elementValue = elementValue;
    }

    public String getElementUnit() {
        return elementUnit;
    }

    public void setElementUnit(String elementUnit) {
        this.elementUnit = elementUnit;
    }

    public Long getRuleExcId() {
        return ruleExcId;
    }

    public void setRuleExcId(Long ruleExcId) {
        this.ruleExcId = ruleExcId;
    }

    @Override
    public String toString() {
        return "TheAdjustmentSubsidiaryTwoVO{" +
                "ruleNo='" + ruleNo + '\'' +
                ", ruleDescription='" + ruleDescription + '\'' +
                ", ruleElement='" + ruleElement + '\'' +
                ", elementValue=" + elementValue +
                ", elementUnit='" + elementUnit + '\'' +
                ", ruleExcId=" + ruleExcId +
                '}';
    }
}

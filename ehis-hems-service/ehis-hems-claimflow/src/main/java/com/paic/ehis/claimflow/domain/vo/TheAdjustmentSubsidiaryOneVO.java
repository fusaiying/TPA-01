package com.paic.ehis.claimflow.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class TheAdjustmentSubsidiaryOneVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ruleNo;//规则编码
    private String dutyDetailName;//责任明细名称
    private String feeitemName;//费用项名称
    private String ruleDescription;//规则描述
    private BigDecimal usedValue;//本次使用值
    private BigDecimal surplusValue;//本次计算前剩余值
    private Long calRuleId;//赔付费用明细ID

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public String getDutyDetailName() {
        return dutyDetailName;
    }

    public void setDutyDetailName(String dutyDetailName) {
        this.dutyDetailName = dutyDetailName;
    }

    public String getFeeitemName() {
        return feeitemName;
    }

    public void setFeeitemName(String feeitemName) {
        this.feeitemName = feeitemName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public BigDecimal getUsedValue() {
        return usedValue;
    }

    public void setUsedValue(BigDecimal usedValue) {
        this.usedValue = usedValue;
    }

    public BigDecimal getSurplusValue() {
        return surplusValue;
    }

    public void setSurplusValue(BigDecimal surplusValue) {
        this.surplusValue = surplusValue;
    }

    public Long getCalRuleId() {
        return calRuleId;
    }

    public void setCalRuleId(Long calRuleId) {
        this.calRuleId = calRuleId;
    }

    @Override
    public String toString() {
        return "TheAdjustmentSubsidiaryOneVO{" +
                "ruleNo='" + ruleNo + '\'' +
                ", dutyDetailName='" + dutyDetailName + '\'' +
                ", feeitemName='" + feeitemName + '\'' +
                ", ruleDescription='" + ruleDescription + '\'' +
                ", usedValue=" + usedValue +
                ", surplusValue=" + surplusValue +
                ", calRuleId=" + calRuleId +
                '}';
    }
}

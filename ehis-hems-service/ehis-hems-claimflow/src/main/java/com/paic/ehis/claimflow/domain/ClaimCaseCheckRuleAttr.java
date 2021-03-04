package com.paic.ehis.claimflow.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 案件抽检规则属性对象 claim_case_check_rule_attr
 * 
 * @author sino
 * @date 2021-01-30
 */
public class ClaimCaseCheckRuleAttr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long attrId;

    /** 抽检规则编码 */
    @Excel(name = "抽检规则编码")
    private String checkRuleNo;

    /** 赔付结论 */
    @Excel(name = "赔付结论")
    private String payConclusion;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setAttrId(Long attrId) 
    {
        this.attrId = attrId;
    }

    public Long getAttrId() 
    {
        return attrId;
    }
    public void setCheckRuleNo(String checkRuleNo) 
    {
        this.checkRuleNo = checkRuleNo;
    }

    public String getCheckRuleNo() 
    {
        return checkRuleNo;
    }
    public void setPayConclusion(String payConclusion) 
    {
        this.payConclusion = payConclusion;
    }

    public String getPayConclusion() 
    {
        return payConclusion;
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
            .append("attrId", getAttrId())
            .append("checkRuleNo", getCheckRuleNo())
            .append("payConclusion", getPayConclusion())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

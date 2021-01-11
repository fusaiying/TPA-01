package com.paic.ehis.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 权益分配规则对象 hmp_benefit_rule
 * 
 * @author sino
 * @date 2020-11-23
 */
public class HmpBenefitRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则编码 */
    private String rulecode;

    /** 规则名称 */
    @Excel(name = "规则名称")
    private String rulename;

    /** 规则描述 */
    @Excel(name = "规则描述")
    private String ruledescription;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startdate;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enddate;

    @Excel(name = "状态")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRulecode(String rulecode)
    {
        this.rulecode = rulecode;
    }

    public String getRulecode() 
    {
        return rulecode;
    }
    public void setRulename(String rulename) 
    {
        this.rulename = rulename;
    }

    public String getRulename() 
    {
        return rulename;
    }
    public void setRuledescription(String ruledescription) 
    {
        this.ruledescription = ruledescription;
    }

    public String getRuledescription() 
    {
        return ruledescription;
    }
    public void setStartdate(Date startdate) 
    {
        this.startdate = startdate;
    }

    public Date getStartdate() 
    {
        return startdate;
    }
    public void setEnddate(Date enddate) 
    {
        this.enddate = enddate;
    }

    public Date getEnddate() 
    {
        return enddate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("rulecode", getRulecode())
            .append("rulename", getRulename())
            .append("ruledescription", getRuledescription())
            .append("startdate", getStartdate())
            .append("enddate", getEnddate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}

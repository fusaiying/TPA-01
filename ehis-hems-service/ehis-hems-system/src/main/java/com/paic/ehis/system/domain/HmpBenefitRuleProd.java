package com.paic.ehis.system.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 分配内容对象 hmp_benefit_rule_prod
 * 
 * @author sino
 * @date 2020-11-23
 */
public class HmpBenefitRuleProd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则编码 */
    private String rulecode;

    /** 产品编码 */
    private String productcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productname;

    /** 服务编码 */
    private String servicecode;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String servicename;

    public void setRulecode(String rulecode) 
    {
        this.rulecode = rulecode;
    }

    public String getRulecode() 
    {
        return rulecode;
    }
    public void setProductcode(String productcode) 
    {
        this.productcode = productcode;
    }

    public String getProductcode() 
    {
        return productcode;
    }
    public void setProductname(String productname) 
    {
        this.productname = productname;
    }

    public String getProductname() 
    {
        return productname;
    }
    public void setServicecode(String servicecode) 
    {
        this.servicecode = servicecode;
    }

    public String getServicecode() 
    {
        return servicecode;
    }
    public void setServicename(String servicename) 
    {
        this.servicename = servicename;
    }

    public String getServicename() 
    {
        return servicename;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("rulecode", getRulecode())
            .append("productcode", getProductcode())
            .append("productname", getProductname())
            .append("servicecode", getServicecode())
            .append("servicename", getServicename())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

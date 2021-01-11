package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 出单公司信息 对象 base_issuingcompany
 * 
 * @author sino
 * @date 2021-01-05
 */
public class BaseIssuingcompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出单公司编码 */
    private String companyCode;

    /** 出单公司名称 */
    @Excel(name = "出单公司名称")
    private String companyName;

    /** 出单公司简写名称 */
    @Excel(name = "出单公司简写名称")
    private String simpleName;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setCompanyCode(String companyCode) 
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() 
    {
        return companyCode;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setSimpleName(String simpleName) 
    {
        this.simpleName = simpleName;
    }

    public String getSimpleName() 
    {
        return simpleName;
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
            .append("companyCode", getCompanyCode())
            .append("companyName", getCompanyName())
            .append("simpleName", getSimpleName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

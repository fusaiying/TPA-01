package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * base_provider_newtworktypet_risk(医疗网络险种信息)对象 base_provider_newtworktypet_risk
 * 
 * @author sino
 * @date 2021-01-04
 */
public class BaseProviderNewtworktypetRisk extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serNo;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String providerCode;

    /** 网络类型编码 */
    @Excel(name = "网络类型编码")
    private String networktypeCode;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 险种编码 */
    @Excel(name = "险种编码")
    private String riskCode;

    /** 险种名称 */
    @Excel(name = "险种名称")
    private String riskName;

    public void setSerNo(String serNo) 
    {
        this.serNo = serNo;
    }

    public String getSerNo() 
    {
        return serNo;
    }
    public void setProviderCode(String providerCode) 
    {
        this.providerCode = providerCode;
    }

    public String getProviderCode() 
    {
        return providerCode;
    }
    public void setNetworktypeCode(String networktypeCode) 
    {
        this.networktypeCode = networktypeCode;
    }

    public String getNetworktypeCode() 
    {
        return networktypeCode;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setRiskCode(String riskCode) 
    {
        this.riskCode = riskCode;
    }

    public String getRiskCode() 
    {
        return riskCode;
    }
    public void setRiskName(String riskName) 
    {
        this.riskName = riskName;
    }

    public String getRiskName() 
    {
        return riskName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serNo", getSerNo())
            .append("providerCode", getProviderCode())
            .append("networktypeCode", getNetworktypeCode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("riskCode", getRiskCode())
            .append("riskName", getRiskName())
            .toString();
    }
}

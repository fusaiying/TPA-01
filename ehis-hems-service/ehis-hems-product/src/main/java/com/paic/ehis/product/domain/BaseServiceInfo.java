package com.paic.ehis.product.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * base_service_info（服务项目）对象 base_service_info
 * 
 * @author sino
 * @date 2021-01-11
 */
@Data
public class BaseServiceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 服务项目编码 */
    @Excel(name = "服务项目编码")
    private String serviceCode;

    /** 服务项目名称 */
    @Excel(name = "服务项目名称")
    private String serviceName;

    /** 类别编码 */
    @Excel(name = "类别编码")
    private String categoryCode;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /** 别名 */
    @Excel(name = "别名")
    private String alias;

    /** 种类编码 */
    @Excel(name = "种类编码")
    private String servertypeCode;

    /** 种类名称 */
    @Excel(name = "种类名称")
    private String servertypeName;

    /** 来源 */
    @Excel(name = "来源")
    private String orgin;

    /** 状态(01-待发布，02-已发布) */
    @Excel(name = "状态(01-待发布，02-已发布)")
    private String status;

    /** 原编码 */
    @Excel(name = "原编码")
    private String originalCode;

    /** 原名称 */
    @Excel(name = "原名称")
    private String originalName;

    /** 条款 */
    @Excel(name = "条款")
    private String clause;

    /** 数据状态（Y-使用，N-注销） */
    @Excel(name = "数据状态", readConverterExp = "Y=-使用，N-注销")
    private String state;

    /** 服务项目编码名称 */
    @Excel(name = "服务项目编码名称")
    private String serviceCodeName;

    /** 种类编码名称 */
    @Excel(name = "种类编码名称")
    private String typeCodeName;

    /** 类别编码名称 */
    @Excel(name = "类别编码名称")
    private String categoryCodeName;

    public void setSerialNo(String serialNo) 
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo() 
    {
        return serialNo;
    }
    public void setServiceCode(String serviceCode) 
    {
        this.serviceCode = serviceCode;
    }

    public String getServiceCode() 
    {
        return serviceCode;
    }
    public void setServiceName(String serviceName) 
    {
        this.serviceName = serviceName;
    }

    public String getServiceName() 
    {
        return serviceName;
    }
    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setAlias(String alias) 
    {
        this.alias = alias;
    }

    public String getAlias() 
    {
        return alias;
    }
    public void setServertypeCode(String servertypeCode) 
    {
        this.servertypeCode = servertypeCode;
    }

    public String getServertypeCode() 
    {
        return servertypeCode;
    }
    public void setServertypeName(String servertypeName) 
    {
        this.servertypeName = servertypeName;
    }

    public String getServertypeName() 
    {
        return servertypeName;
    }
    public void setOrgin(String orgin) 
    {
        this.orgin = orgin;
    }

    public String getOrgin() 
    {
        return orgin;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setOriginalCode(String originalCode) 
    {
        this.originalCode = originalCode;
    }

    public String getOriginalCode() 
    {
        return originalCode;
    }
    public void setOriginalName(String originalName) 
    {
        this.originalName = originalName;
    }

    public String getOriginalName() 
    {
        return originalName;
    }
    public void setClause(String clause) 
    {
        this.clause = clause;
    }

    public String getClause() 
    {
        return clause;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serialNo", getSerialNo())
            .append("serviceCode", getServiceCode())
            .append("serviceName", getServiceName())
            .append("categoryCode", getCategoryCode())
            .append("categoryName", getCategoryName())
            .append("alias", getAlias())
            .append("servertypeCode", getServertypeCode())
            .append("servertypeName", getServertypeName())
            .append("orgin", getOrgin())
            .append("status", getStatus())
            .append("originalCode", getOriginalCode())
            .append("originalName", getOriginalName())
            .append("clause", getClause())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

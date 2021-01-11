package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_provider_newtworktypet_data(医疗网络类型信息)对象 base_provider_newtworktypet_data
 * 
 * @author sino
 * @date 2021-01-04
 */
@Data
public class BaseProviderNewtworktypetData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serNo;

    /** 网络类型名称 */
    @Excel(name = "网络类型名称")
    private String networktypeName;

    /** 网络类型编码 */
    @Excel(name = "网络类型编码")
    private String networktypeCode;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setSerNo(String serNo) 
    {
        this.serNo = serNo;
    }

    public String getSerNo() 
    {
        return serNo;
    }
    public void setNetworktypeName(String networktypeName) 
    {
        this.networktypeName = networktypeName;
    }

    public String getNetworktypeName() 
    {
        return networktypeName;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serNo", getSerNo())
            .append("networktypeName", getNetworktypeName())
            .append("networktypeCode", getNetworktypeCode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

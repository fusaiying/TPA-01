package com.paic.ehis.system.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 机构信息对象 sys_organ_info
 * 
 * @author sino
 * @date 2021-02-19
 */
public class SysOrganInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 映射ID */
    private Long organId;

    /** 机构编码 */
    @Excel(name = "机构编码")
    private String organCode;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String organName;

    /** 机构英文名称 */
    @Excel(name = "机构英文名称")
    private String organNameEn;

    /** 上级机构编码 */
    @Excel(name = "上级机构编码")
    private String parentOrganCode;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setOrganId(Long organId) 
    {
        this.organId = organId;
    }

    public Long getOrganId() 
    {
        return organId;
    }
    public void setOrganCode(String organCode) 
    {
        this.organCode = organCode;
    }

    public String getOrganCode() 
    {
        return organCode;
    }
    public void setOrganName(String organName) 
    {
        this.organName = organName;
    }

    public String getOrganName() 
    {
        return organName;
    }
    public void setOrganNameEn(String organNameEn) 
    {
        this.organNameEn = organNameEn;
    }

    public String getOrganNameEn() 
    {
        return organNameEn;
    }
    public void setParentOrganCode(String parentOrganCode) 
    {
        this.parentOrganCode = parentOrganCode;
    }

    public String getParentOrganCode() 
    {
        return parentOrganCode;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("organId", getOrganId())
            .append("organCode", getOrganCode())
            .append("organName", getOrganName())
            .append("organNameEn", getOrganNameEn())
            .append("parentOrganCode", getParentOrganCode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

package com.paic.ehis.claimmgt.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 案件分配规则对象 claim_case_dist
 * 
 * @author sino
 * @date 2021-01-22
 */
@Data
public class ClaimCaseDist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分配规则ID */
    private Long distId;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    /** 角色 */
    @Excel(name = "角色")
    private Long roleId;
    /** 用户名称 */
    private String userName;
    /** 是否均分 */
    @Excel(name = "是否均分")
    private String isEqually;

    /** 分配比例 */
    @Excel(name = "分配比例")
    private BigDecimal rate;
    /**角色名*/
    private String roleName;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setDistId(Long distId) 
    {
        this.distId = distId;
    }

    public Long getDistId() 
    {
        return distId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setIsEqually(String isEqually) 
    {
        this.isEqually = isEqually;
    }

    public String getIsEqually() 
    {
        return isEqually;
    }
    public void setRate(BigDecimal rate) 
    {
        this.rate = rate;
    }

    public BigDecimal getRate() 
    {
        return rate;
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
            .append("distId", getDistId())
            .append("userId", getUserId())
            .append("roleId", getRoleId())
            .append("isEqually", getIsEqually())
            .append("rate", getRate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

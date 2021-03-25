package com.paic.ehis.claimmgt.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 理赔用户工作承接对象 claim_user_take_on
 *
 * @author sino
 * @date 2021-01-22
 */
@Data
public class ClaimUserTakeOn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 承接ID */
    private String takeOnId;

    /** 交接用户 */
    @Excel(name = "交接用户")
    private String userName;

    /** 承接用户 */
    @Excel(name = "承接用户")
    private String takeOnUserName;


    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("takeOnId", getTakeOnId())
                .append("userId", getUserName())
                .append("takeOnUserId", getTakeOnUserName())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

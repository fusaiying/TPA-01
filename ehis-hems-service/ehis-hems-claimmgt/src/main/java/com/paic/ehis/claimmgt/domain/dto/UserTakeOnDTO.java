package com.paic.ehis.claimmgt.domain.dto;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class UserTakeOnDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 承接ID */
    private String takeOnId;
    /** 用户名称 */
    private String userName;
    /** 角色ID */
    private String roleId;
    /** 承接用户 */
    @Excel(name = "承接用户")
    private String takeOnUserId;
    /**角色名*/
    private String roleName;
    /** 交接用户 */
    @Excel(name = "交接用户")
    private String userId;
    /** 更新者 */
    private String updateBy;
    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;
}

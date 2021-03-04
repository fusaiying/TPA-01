package com.paic.ehis.claimapt.domain.DTO;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class SysUserDTO extends BaseEntity {

    /** 帐号状态 */
    private String status;

    /** 删除标志 */
    private String delFlag;

    /** 用户名称 */
    private String userName;

}

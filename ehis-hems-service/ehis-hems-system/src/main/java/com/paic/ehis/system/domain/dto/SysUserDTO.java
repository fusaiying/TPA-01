package com.paic.ehis.system.domain.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysUserDTO implements Serializable {

    private static final long serialVersionUID = 5019747081798902279L;

    /** 帐号状态 */
    private String status;

    /** 删除标志 */
    private String delFlag;

}

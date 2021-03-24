package com.paic.ehis.system.api.domain.dto;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoleUserInfoDTO extends BaseEntity {
    /** 用户名 */
    private String userName;

    /** 所属机构编码 */
    private String orangeCode;

    /** 角色编码 */
    private String roleCode;

    /** 分配规则ID */
    private Long distId;

    /** 分配比例 */
    private BigDecimal rate;

    /** 是否均分 */
    private String isEqually;

    /** 是否有效 */
    private String status;
}

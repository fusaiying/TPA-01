package com.paic.ehis.claimmgt.domain.dto;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

@Data
public class ClaimCaseOperationDTO {
    /** 用户名称 */
    private String userName;

    /** 用户所属机构 */
    private String userOrganCode;

    /** 当前环节 */
    private String operation;
}

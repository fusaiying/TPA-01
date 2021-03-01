package com.paic.ehis.system.domain.dto;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class SysUserByOrganCodeDTO extends BaseEntity {

    private String organCode;

    private String userName;
}

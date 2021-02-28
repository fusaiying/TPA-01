package com.paic.ehis.system.domain.dto;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class OrganListDTO extends BaseEntity {
    private String organCode;

    private String organName;

}

package com.paic.ehis.system.api.domain.dto;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class MenuIdDTO extends BaseEntity {
    /** 菜单ID */
    private Long menuId;
}

package com.paic.ehis.claimcal.domain.dto;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class UpdateClaimProductDTO extends BaseEntity {
    /** 任务改派操作人 */
    private String updateBy;
    /**接收任务改派的编码集合*/
    private List<String> riskCode;

}

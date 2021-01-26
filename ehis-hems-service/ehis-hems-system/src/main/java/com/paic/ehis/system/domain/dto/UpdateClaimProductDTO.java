package com.paic.ehis.system.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateClaimProductDTO implements Serializable {
    /** 任务改派操作人 */
    private String updateBy;
    /**接收任务改派的编码集合*/
    private List<String> riskCode;

}

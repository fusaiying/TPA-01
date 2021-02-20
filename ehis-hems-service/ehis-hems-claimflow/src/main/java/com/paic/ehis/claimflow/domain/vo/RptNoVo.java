package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class RptNoVo extends BaseEntity {

    /** 报案号 */
    private String rptNo;

    /** 批次号 */
    private String batchNo;
}

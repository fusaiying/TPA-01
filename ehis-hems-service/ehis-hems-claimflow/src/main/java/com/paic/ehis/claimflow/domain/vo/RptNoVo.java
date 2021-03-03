package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class RptNoVo extends BaseEntity {

    /** 报案号 */
    private String rptNo;

    /** 批次号 */
    private String batchNo;

    /** 发票是否归档 */
    private String isFiling;

    /** 发票是否退回 */
    private String isInvoiceBack;

    /** 批次是否单张发票 */
    private String isSingle;

    /** 备注 */
    private String remark;
}

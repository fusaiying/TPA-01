package com.paic.ehis.claimflow.domain.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: lyc
 * @CreateTime: 2021/2/25
 * @Company:
 */
@Data
public class ClaimStandingRptNoDTO {

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 证件类型
     */
    private String idType;
    /**
     * 被保人
     */
    private String name;

    /**
     * 报案号
     */
    private String rptNo;
    /**
     * 批次号
     */
    private String batchNo;

}

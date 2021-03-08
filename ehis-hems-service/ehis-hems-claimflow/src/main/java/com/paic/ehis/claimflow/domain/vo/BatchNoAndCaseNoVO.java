package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

@Data
public class BatchNoAndCaseNoVO {
    /**状态*/
    private String status;

    /**返回信息*/
    private String message;

    /***/
    private BatchNoAndCaseNo batchNoAndCaseNo;
}

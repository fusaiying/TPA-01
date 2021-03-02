package com.paic.ehis.claimflow.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class BatchNoRptNoDTO extends BaseEntity {

    /**
     * 交单来源
     */
    //private String source;

    /**
     * 就诊医院ID
     */
    private String provider;

    /**
     * 批次理赔类型
     */
    //private String claimType;

    /**
     * 交单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date billDate;

    /**
     * 案件数量
     */
    private String batchCount;

    /**
     * 批次总金额
     */
    private String batchAmount;

    /**
     * 交单机构编码
     */
    private String branchRegion;

    /**
     * 是否收到发票
     */
    private String isReceiptSubmit;

    /**
     * 收单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveDate;

    /**
     * 批次是否单张发票
     */
    private String directReceiptSign;

    /**
     * 案件第五位标识码
     */
    private String caseFlag;
}

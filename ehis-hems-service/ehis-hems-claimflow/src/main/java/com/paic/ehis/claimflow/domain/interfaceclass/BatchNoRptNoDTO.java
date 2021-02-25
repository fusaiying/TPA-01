package com.paic.ehis.claimflow.domain.interfaceclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BatchNoRptNoDTO extends BaseEntity {

    /**
     * 交单来源
     */
    private String source;

    /**
     * 就诊医院ID
     */
    private String hospitalCode;

    /**
     * 批次理赔类型
     */
    private String claimType;

    /**
     * 交单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submitDate;

    /**
     * 案件数量
     */
    private Integer casenum;

    /**
     * 批次总金额
     */
    private BigDecimal batchTotal;

    /**
     * 交单机构编码
     */
    private String organCode;

    /**
     * 账单币种
     */
    private String currency;

    /**
     * 保单类型
     */
    private String contType;

    /**
     * 是否收到发票
     */
    private String billRecevieFlag;

    /**
     * 优先原因
     */
    private String priReason;

    /**
     * 批次备注
     */
    private String remark;

    /**
     * 批次状态 01-处理中 02-交单复核 03-交单完成 04-交单退回 05-交单无效
     */
    private String batchStatus;

    /**
     * 快递单号
     */
    private String expressNumber;

    /**
     * 接单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveDate;

    /**
     * 交件人
     */
    private String sendBy;

    /**
     * 特殊案件类型
     */
    private String speccaseType;

    /**
     * 投保单位
     */
    private String issuingUnit;

    /**
     * 保单号
     */
    private String contNo;
}

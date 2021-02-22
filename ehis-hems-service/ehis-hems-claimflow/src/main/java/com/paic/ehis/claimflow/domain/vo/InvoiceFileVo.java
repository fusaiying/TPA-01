package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;


@Data
public class InvoiceFileVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;
    /**
     * 是否收到发票
     */
    @Excel(name = "是否收到发票")
    private String billRecevieFlag;
    /** 批次号 */
    private String batchNo;

    /** 发票盒号 */
    @Excel(name = "发票盒号")
    private String invoiceBoxNo;

    /** 发票是否归档 */
    @Excel(name = "发票是否归档")
    private String isFiling;

    /** 发票是否退回 */
    @Excel(name = "发票是否退回")
    private String isInvoiceBack;

    /** 批次是否单张发票 */
    @Excel(name = "批次是否单张发票")
    private String isSingle;
    /** 就诊医院编码 */
    @Excel(name = "就诊医院编码")
    private String hospitalCode;
    /**  */
    @Excel(name = "案件类型")
    private String claimType;
    /**
     * 交单机构编码
     */
    @Excel(name = "交单机构编码")
    private String organcode;

    private String orgName;

    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;

    private String status;
}

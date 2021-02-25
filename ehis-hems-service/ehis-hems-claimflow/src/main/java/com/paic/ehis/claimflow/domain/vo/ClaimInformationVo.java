package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ClaimInformationVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String claimType;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 案件状态 */
    @Excel(name = "案件状态",readConverterExp = "00=挂起,01=交单复核,02=交单退回,03=交单失效,04=交单完成,05=受理,06=录入,07=审核,08=抽检,30=问题件,31=协谈,32=调查,97=撤件可申诉,98=撤件,99=结案")
    private String caseStatus;

    /** 被保人姓名 */
    @Excel(name = "被保险人")
    private String name;

    /** 被保人证件号 */
    @Excel(name = "证件号码")
    private String idNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 出险日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "就诊日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accDate;

    /** 赔付结论 */
    @Excel(name = "赔付结论")
    private String payConclusion;

    /** 给付金额 */
    @Excel(name = "给付金额")
    private String paymentAmount;

    /**  审核人 */
    @Excel(name ="审核人")
    private String updateBy;

    /** 是否调查 */
    @Excel(name = "有无调查 ",readConverterExp = "01=是,02=否")
    private String investigation;

    /** 账单币种 */
    private String billCurrency;

    /** 账单金额 */
    private BigDecimal billAmount;

    /** 被保人客户号 */
    private String insuredNo;

}

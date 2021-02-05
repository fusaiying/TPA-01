package com.paic.ehis.claimcal.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ClaimInformationVo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;
    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;

    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;
    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;
    /** 账单币种 */
    @Excel(name = "账单币种")
    private String billCurrency;

    /** 账单金额 */
    @Excel(name = "账单金额")
    private BigDecimal billAmount;
    /** 出险日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出险日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accDate;

    /** 是否调查 */
    @Excel(name = "是否调查 ",readConverterExp = "01=是,02=否")
    private String investigation;

    /** 被保人客户号 */
    private String insuredNo;
    /** 赔付结论 */
    @Excel(name = "赔付结论")
    private String payConclusion;
    /**
     * 给付金额
     */
    @Excel(name = "给付金额")
    private String paymentAmount;
    /**  审核人 */
    @Excel(name ="审核人")
    private String operator;
}

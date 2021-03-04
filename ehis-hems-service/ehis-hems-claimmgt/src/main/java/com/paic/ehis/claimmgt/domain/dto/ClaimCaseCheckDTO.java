package com.paic.ehis.claimmgt.domain.dto;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ClaimCaseCheckDTO extends BaseEntity {

    /** 抽检规则编码 */
    private String checkRuleNo;

    /** 案件类型 */
    @Excel(name = "案件类型")
    private String caseType;

    /** 赔付结论 */
    @Excel(name = "赔付结论")
    private String payConclusion;

    /** 金额类型 */
    @Excel(name = "金额类型")
    private String amountType;

    /** 抽检金额 */
    @Excel(name = "抽检金额")
    private BigDecimal checkAmount;

    /** 赔付金额 */
    @Excel(name = "赔付金额")
    private BigDecimal payAmount;

    /** 拒付金额 */
    @Excel(name = "拒付金额")
    private BigDecimal amount;

    /** 抽检比例 */
    @Excel(name = "抽检比例")
    private BigDecimal rate;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 报案号 */
    private String rptNo;

    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;
}

package com.paic.ehis.claimflow.domain.interfaceclass;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 案件账单诊断信息对象 claim_case_bill_diagnosis
 * 
 * @author sino
 * @date 2021-02-25
 */
@Data
public class ClaimCaseBillDiagnosis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 诊断信息ID */
    private Long digId;

    /** 账单ID */
    private Long billId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** ICD10编码 */
    @Excel(name = "ICD10编码")
    private String icdCode;

    /** 诊断类型 01-主要诊断、02-次要诊断 */
    @Excel(name = "诊断类型 01-主要诊断、02-次要诊断")
    private String digType;

    /** 次要诊断次序 */
    @Excel(name = "次要诊断次序")
    private Long digOrder;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

}

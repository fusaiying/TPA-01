package com.paic.ehis.system.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 案件账单明细对象 claim_case_bill
 * 
 * @author sino
 * @date 2021-01-15
 */
@Data
public class ClaimCaseBillDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账单ID */
    private Long billId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 就诊医院编码 */
    @Excel(name = "就诊医院编码")
    private String hospitalCode;

    /** 科室 */
    @Excel(name = "科室")
    private String department;

    /** 是否定点医院 */
    @Excel(name = "是否定点医院")
    private String isDesHospital;

    /** 出险类型 */
    @Excel(name = "出险类型")
    private String accType;

    /** 账单币种 */
    @Excel(name = "账单币种")
    private String billCurrency;

    /** 账单金额 */
    @Excel(name = "账单金额")
    private BigDecimal billAmount;

    /** 就诊次数 */
    @Excel(name = "就诊次数")
    private Long visNumber;

    /** 治疗类型 */
    @Excel(name = "治疗类型")
    private String treatmentType;

    /** 治疗起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "治疗起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatmentStartDate;

    /** 治疗止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "治疗止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatmentEndDate;

    /** 治疗天数 */
    @Excel(name = "治疗天数")
    private Long treatmentDays;

    /** 账单类型 */
    @Excel(name = "账单类型")
    private String billType;
}

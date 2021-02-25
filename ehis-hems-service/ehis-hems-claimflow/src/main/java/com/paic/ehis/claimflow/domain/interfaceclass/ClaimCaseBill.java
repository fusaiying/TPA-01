package com.paic.ehis.claimflow.domain.interfaceclass;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 案件账单明细对象 claim_case_bill
 * 
 * @author sino
 * @date 2021-02-25
 */
@Data
public class ClaimCaseBill extends BaseEntity
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

    /** 发票号 */
    @Excel(name = "发票号")
    private String invoiceNo;

    /** 账单号 */
    @Excel(name = "账单号")
    private String billNo;

    /** 账单类型 */
    @Excel(name = "账单类型")
    private String billType;

    /** 社保先期给付 */
    @Excel(name = "社保先期给付")
    private BigDecimal ssAdvancePayment;

    /** 第三方先期给付 */
    @Excel(name = "第三方先期给付")
    private BigDecimal tpAdvancePayment;

    /** 是否分摊先期给付 */
    @Excel(name = "是否分摊先期给付")
    private String isShareAp;

    /** 交易流水号 */
    @Excel(name = "交易流水号")
    private String transSerialNo;

    /** 交易流水自付额 */
    @Excel(name = "交易流水自付额")
    private BigDecimal transSerialCopay;

    /** 自付额 */
    @Excel(name = "自付额")
    private BigDecimal copay;

    /** 是否分摊自付额 */
    @Excel(name = "是否分摊自付额")
    private String isShareCopay;

    /** 医院折扣金额 */
    @Excel(name = "医院折扣金额")
    private BigDecimal hosDiscountAmount;

    /** 是否分摊折扣金额 */
    @Excel(name = "是否分摊折扣金额")
    private String isShareDisAmount;

    /** 自费金额 */
    @Excel(name = "自费金额")
    private BigDecimal selfAmount;

    /** 部分自费金额 */
    @Excel(name = "部分自费金额")
    private BigDecimal partSelfAmount;

    /** 不合理金额 */
    @Excel(name = "不合理金额")
    private BigDecimal unableAmount;

    /** 临床诊断 */
    @Excel(name = "临床诊断")
    private String clinicalDiagnosis;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 材料类型 */
    private String materialType;

    /** 医生姓名 */
    private String doctorName;

    /** 疾病代码、疾病描述 */
    private List<ClaimCaseBillDiagnosis> billDiagnosis;

    /** 账单费用明细 */
    private List<ClaimCaseBillDetail> billDetailList;

}

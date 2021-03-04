package com.paic.ehis.claimflow.domain.vo;


import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CaseCalBillItemVo
 * @Description: TODO  理算审核 账单费用项明细
 * @Author 硠君
 * @Date create in 16:04 2021/1/21
 * @Version 1.0
 **/
@Data
public class CaseCalBillItemVo implements Serializable {

	 private static final long serialVersionUID = 1L;

    /** 赔付费用明细ID */
    @Excel(name = "账单明细ID",combo = "")
    private Long calItemId;

    /** 报案号 */
    @Excel(name = "报案号",combo = "")
    private String rptNo;

    /** 账单ID */
    @Excel(name = "账单ID",combo = "")
    private Long billId;

    /** 保单号 */
    @Excel(name = "保单号",combo = "")
    private String policyNo;        //返回查询  保单险种关联

    /** 产品编码 */
    @Excel(name = "产品编码",combo = "")
    private String riskCode;        ////返回查询  保单险种关联

    @Excel(name = "责任明细编码",combo = "")
    private String dutyDetailCode;

    /** 责任编码 */
    @Excel(name = "责任编码",combo = "")
    private String dutyDetailName;

    /** 费用项编码 */
    @Excel(name = "费用项编码",combo = "")
    private String feeItemCode; //    ccbd.fee_item_code

    /** 费用项名称 */
    @Excel(name = "费用项名称",combo = "")
    private String feeItemName; //	ccbd.fee_item_name,

    /** 费用项账单金额 */
    @Excel(name = "费用项账单金额",combo = "")
    private BigDecimal billDetailAmount;    //	ccbd.bill_detail_amount,

    /** 自费金额 */
    @Excel(name = "自费金额",combo = "")
    private BigDecimal selfAmount;      //	ccbd.self_amount,

    /** 部分自费金额 */
    @Excel(name = "部分自费金额",combo = "")
    private BigDecimal partSelfAmount;  //	ccbd.part_self_amount,

    /** 费用项先期给付 */
    @Excel(name = "费用项先期给付",combo = "")
    private BigDecimal advancePayment;  //	ccbd.advance_payment,

    /** 费用项自付额 */
    @Excel(name = "费用项自付额",combo = "")
    private BigDecimal billDetailCopay; //	ccbd.bill_detail_copay

    /** 费用项医院折扣金额 */
    @Excel(name = "费用项医院折扣金额",combo = "")
    private BigDecimal hosDiscountAmount;   //	ccbd.hos_discount_amount,

    /** 不合理金额 */
    @Excel(name = "不合理金额",combo = "")
    private BigDecimal unableAmount;    //ccbd.unable_amount,

    /** 理算金额 */
    @Excel(name = "理算金额",combo = "")
    private BigDecimal calAmount;       //	ccci.cal_amount,

    /** 拒赔金额 */
    @Excel(name = "拒赔金额" ,combo = "" )
    private BigDecimal refusedAmount;   //	ccci.refused_amount,

    /** 本次使用免赔额 */
    @Excel(name = "本次使用免赔额",combo = "")
    private BigDecimal deduUsed;         //	ccci.dedu_used,

    /** 赔付比例 */
    @Excel(name = "赔付比例",combo = "")
    private BigDecimal payRate;         //	ccci.pay_rate,
}

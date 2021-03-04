package com.paic.ehis.finance.domain;


import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName TpaSettleInfo
 * @Description: TODO TPA服务费结算信息
 * @Author 硠君
 * @Date create in 9:37 2021/2/1
 * @Version 1.0
 **/
@Data
public class TpaSettleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 结算任务号 */
    private String settleTaskNo;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 出单公司名称 */
    @Excel(name = "出单公司")
    private String companyName;

    /** 险种编码 */
    @Excel(name = "险种编码")
    private String riskCode;

    /** 险种名称 */
    @Excel(name = "险种")
    private String riskName;

    /** 结算类型 01-服务费 02-保费比例% */
    @Excel(name = "结算类型", readConverterExp = "01=服务费,02=保费比例%")
    private String settlementType;

    /** 结算值 */
    private BigDecimal settlementValue;

    /** 服务费结算总金额 */
    @Excel(name = "服务费结算总金额")
    private BigDecimal serviceSettleAmount;

    /** 总保费 */
    @Excel(name = "总保费")
    private BigDecimal sumPerm;

    /** 总人数 */
    @Excel(name = "总人数")
    private int totalPeople;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    private List<TpaSettleDetailInfo> detailInfos;
}

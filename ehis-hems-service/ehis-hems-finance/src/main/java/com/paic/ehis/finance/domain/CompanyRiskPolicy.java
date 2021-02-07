package com.paic.ehis.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CompanyRiskPolicy
 * @Description: TODO 出单公司、险种、保单 关联信息
 * @Author 硠君
 * @Date create in 16:19 2021/1/30
 * @Version 1.0
 **/
@Data
public class CompanyRiskPolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 保单号 */
    private String policyNo;

    /** 险种编码 */
    private String riskCode;

    /** 险种名称 */
    private String riskName;

    /** 分单号 */
    private String policyItemNo;

    /** 被保人编码 */
    private String insuredNo;

    /** 被保人姓名 */
    private String name;

    /** 投保人姓名 */
    private String appName;

    /** 有效起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validStartDate;

    /** 出单公司编码 */
    private String companyCode;

    /** 出单公司名称 */
    private String companyName;

    /** 保费 */
    private BigDecimal prem;

    /** 总保费 */
    private BigDecimal sumPerm;

    /** 总人数 */
    private int totalPeople;

    /** 最早时期 */
    private Date bestStartDate;
}

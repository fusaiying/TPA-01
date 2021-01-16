package com.paic.ehis.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PolicyVo  implements Serializable {
    private static final long serialVersionUID = 1L;



    /** 保单号 */
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;


    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;

    /** 有效起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validStartDate;

    /** 有效止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validEndDate;

    /** 保单险类 */
    @Excel(name = "保单险类")
    private String policyRiskType;

    /** 保单状态 */
    @Excel(name = "保单状态")
    private String policyStatus;

    /** 特别约定 */
    @Excel(name = "特别约定")
    private String specialAgreement;

    /** 出单公司名称 */
    @Excel(name = "出单公司名称")
    private String companyName;

    /** 保单类型 */
    @Excel(name = "保单类型")
    private String policyType;


    /**  社保标记 */
    @Excel(name = "社保标记")
    private String ssFlag;

    /** 险种名称 */
    @Excel(name = "险种名称")
    private String riskName;


    private  List<DutyVo> minData;




}

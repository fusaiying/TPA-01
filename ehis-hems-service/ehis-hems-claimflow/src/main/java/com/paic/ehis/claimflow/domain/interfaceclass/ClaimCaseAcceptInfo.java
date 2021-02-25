package com.paic.ehis.claimflow.domain.interfaceclass;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 案件受理信息 对象 claim_case_accept
 * 
 * @author sino
 * @date 2021-02-24
 */
@Data
public class ClaimCaseAcceptInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 案件受理ID */
    private Long acceptId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 索赔金额 */
    @Excel(name = "索赔金额")
    private BigDecimal claimAmount;

    /** 出险日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出险日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accDate;

    /** 出险类型 */
    @Excel(name = "出险类型")
    private String accType;

    /** 事故者现状 */
    @Excel(name = "事故者现状")
    private String currSituation;

    /** 首次重疾确诊日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "首次重疾确诊日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstSerIllnessDate;

    /** 死亡日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "死亡日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deathDate;

    /** 残疾确诊日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "残疾确诊日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date disabilityDate;

    /** 是否预授权 */
    @Excel(name = "是否预授权")
    private String preAuthFlag;

    /** 预授权项目 */
    @Excel(name = "预授权项目")
    private String preAuthItem;

    /** 预授权号 */
    @Excel(name = "预授权号")
    private String preAuthNo;

    /** 材料齐全日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "材料齐全日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date materialCompleteDate;

    /** 公司签收日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "公司签收日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiptDate;

    /** 优先原因 */
    @Excel(name = "优先原因")
    private String priReason;

    /** 事故发生省 */
    @Excel(name = "事故发生省")
    private String accProvince;

    /** 事故发生市 */
    @Excel(name = "事故发生市")
    private String accCity;

    /** 事故发生区 */
    @Excel(name = "事故发生区")
    private String accDistrict;

    /** 事故发生详细地址 */
    @Excel(name = "事故发生详细地址")
    private String accAddress;

    /** 事故经过 */
    @Excel(name = "事故经过")
    private String accDescribe;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 客户申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "客户申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date customerApplicationDate;

    /** 申请类别 */
    private List<String> applyType;
}

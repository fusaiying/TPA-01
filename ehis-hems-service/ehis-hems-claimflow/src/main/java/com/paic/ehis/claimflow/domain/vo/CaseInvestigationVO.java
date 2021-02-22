package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CaseInvestigationVO
 * @Description: TODO
 * @Author 硠君
 * @Date create in 17:22 2021/2/22
 * @Version 1.0
 **/
@Data
public class CaseInvestigationVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 调查任务号 */
    private String invNo;

    /** 提调事项 */
    private String invView;

    /** 保单号 */
    private String policyNo;

    /** 调查机构编码 */
    private String invOrganCode;

    /** 提调原因 */
    private String invReason;

    /** 提调类型 */
    private String invType;

    /** 提调机构编码 */
    private String organCode;

    /** 报案号 */
    private String rptNo;

    /** 出险类型 */
    private String accType;

    /** 出险类型 */
    private String accDescribe;

    /** 事故经过 */
    private String accAddress;

    /** 事故发生区 */
    private String accDistrict;

    /** 事故发生市 */
    private String accCity;

    /** 事故发生省 */
    private String accProvince;

    /** 出险日期 */
    private String accDate;

    /** 被保人姓名 */
    private String name;

    /** 被保人性别 */
    private String sex;

    /** 被保人证件类型 */
    private String idType;

    /** 被保人证件号 */
    private String idNo;
}

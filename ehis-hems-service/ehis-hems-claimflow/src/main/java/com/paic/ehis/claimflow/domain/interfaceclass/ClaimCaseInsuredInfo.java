package com.paic.ehis.claimflow.domain.interfaceclass;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.claimflow.domain.ClaimCasePolicy;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 案件被保人信息对象 claim_case_insured
 * 
 * @author sino
 * @date 2021-02-24
 */
@Data
public class ClaimCaseInsuredInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 案件被保人ID */
    private Long ciId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 被保人客户号 */
    @Excel(name = "被保人客户号")
    private String insuredNo;

    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;

    /** 被保人性别 */
    @Excel(name = "被保人性别")
    private String sex;

    /** 被保人证件类型 */
    @Excel(name = "被保人证件类型")
    private String idType;

    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;

    /** 被保人生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "被保人生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 职业编码 */
    @Excel(name = "职业编码")
    private String occupation;

    /** 国籍 */
    @Excel(name = "国籍")
    private String nationality;

    /** 证件有效期起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "证件有效期起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date idStartDate;

    /** 证件有效期止期（长期=9999-12-31） */
    @Excel(name = "证件有效期止期", readConverterExp = "长=期=9999-12-31")
    private Date idEndDate;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 电话号 */
    @Excel(name = "电话号")
    private String phone;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 保单信息 */
    private List<ClaimCasePolicyInfo> casePolicyInfoList;

}

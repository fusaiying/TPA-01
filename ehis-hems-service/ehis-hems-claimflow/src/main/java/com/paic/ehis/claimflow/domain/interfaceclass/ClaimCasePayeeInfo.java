package com.paic.ehis.claimflow.domain.interfaceclass;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 案件领款人信息对象 claim_case_payee
 * 
 * @author sino
 * @date 2021-02-24
 */
@Data
public class ClaimCasePayeeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 领款人ID */
    private Long payeeId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 领款方式 */
    @Excel(name = "领款方式")
    private String payMode;

    /** 与被保人关系 */
    @Excel(name = "与被保人关系")
    private String relationIns;

    /** 领款人姓名 */
    @Excel(name = "领款人姓名")
    private String payeeName;

    /** 领款人性别 */
    @Excel(name = "领款人性别")
    private String payeeSex;

    /** 领款人证件类型 */
    @Excel(name = "领款人证件类型")
    private String payeeIdType;

    /** 领款人证件号 */
    @Excel(name = "领款人证件号")
    private String payeeIdNo;

    /** 职业编码 */
    @Excel(name = "职业编码")
    private String payeeOccupation;

    /** 国籍 */
    @Excel(name = "国籍")
    private String payeeNationality;

    /** 证件有效期止期（长期=9999-12-31） */
    @Excel(name = "证件有效期止期", readConverterExp = "长=期=9999-12-31")
    private Date idEndDate;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String payeeMobile;

    /** 开户行编码 */
    @Excel(name = "开户行编码")
    private String payeeBank;

    /** 账户属性 */
    @Excel(name = "账户属性")
    private String accAttribute;

    /** 账号 */
    @Excel(name = "账号")
    private String accNo;

    /** 分配比例 */
    @Excel(name = "分配比例")
    private BigDecimal payeeRatio;

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

}

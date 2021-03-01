package com.paic.ehis.claimflow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 案件追讨白名单对象 claim_debt_whitelist
 *
 * @author sino
 * @date 2021-01-20
 */
@Data
public class ClaimDebtWhitelist extends BaseEntity
{

    private static final long serialVersionUID = 1L;

    /** 追讨白名单ID */
    private Long debtWhitelistId;

    /** 被保人姓名 */
    @Excel(name = "被保人名称")
    private String name;

    /** 证件类型 */
    @Excel(name = "证件类型",readConverterExp = "0=其他,1=身份证,2=护照,3=军人证,4=少儿证,5=异常身份证,6=港澳台回乡证,7=驾驶证,8=转换不详,9=不详," +
    "A=出生证,B=税务登记证,C=工商登记证,D=组织机构代码证,E=律师证,F=房产证,G=行驶证,K=港澳台居住证,L=户口本,O=外国人永久居留身份证")
    private String  idType;

    /** 被保人证件号 */
    @Excel(name = "证件号码")
    private String idNo;

    /*性别*/
    @Excel(name = "性别",readConverterExp = "M=男,F=女")
    private String sex;
    /*生日*/
    @Excel(name = "出生日期",width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 等级 */
    @Excel(name = "等级")
    private String level;

    /** 追讨金额上限 */
    @Excel(name = "金额上限")
    private BigDecimal debtAmountUp;

    /** 追缴通知标记 */
    @Excel(name = "追缴通知",readConverterExp = "01=是,02=否")
    private String recMessageFlag;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间")
    private Date createTime;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createBy;

    /** 数据状态（Y-有效，N-无效） */
    private String status;

    /** 剩余金额 */
    private BigDecimal residualAmount;

    /** 被保人客户号 */
    private String insuredNo;

    /** 报案号 */
    private String rptNo;

    private String[] insuredNos;
}

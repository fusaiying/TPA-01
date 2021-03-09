package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 人员信息 对象 person_info
 * 
 * @author sino
 * @date 2021-01-21
 */
@Data
public class Person extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员编号 本系统编号 */
    private String personId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 01-男；02-女 */
    @Excel(name = "性别 01-男；02-女")
    private String sex;

    /** 身份 */
    @Excel(name = "身份")
    private String identity;

    /** 移动电话 */
    @Excel(name = "移动电话")
    private String mobilePhone;

    /** 固定电话 国家区号-区号-号码-分机号;"-"不能丢 */
    @Excel(name = "固定电话")
    private String linePhone;

    private String[] linePhone1;

    /** 家庭电话 国家区号-区号-号码-分机号;"-"不能丢 */
    @Excel(name = "家庭电话")
    private String homePhone;

    private String[] homePhone1;

    /** 办公电话 国家区号-区号-号码-分机号;"-"不能丢 */
    @Excel(name = "办公电话")
    private String workPhone;

    private String[] workPhone1;

    /** 语言 */
    @Excel(name = "语言")
    private String language;

    /** E-MAIL */
    @Excel(name = "E-MAIL")
    private String email;

    /** 传真 */
    @Excel(name = "传真")
    private String fax;

    /** 证件号 */
    @Excel(name = "证件号")
    private String idNumber;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idType;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;

    /** 客户编号 外部系统编号 */
    @Excel(name = "客户编号 外部系统编号")
    private String otherCustomerNo;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

}

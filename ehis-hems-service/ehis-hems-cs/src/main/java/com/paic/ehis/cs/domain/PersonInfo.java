package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class PersonInfo {
    private String personId ;
    /** 姓名 */
    private String name ;
    /** 性别 */
    private Integer sex ;
    /** 身份 */
    private String identity ;
    /** 移动电话 */
    private String mobilePhone ;
    /** 固定电话;国家区号-区号-号码-分机号;"-"不能丢 */
    private String linePhone ;
    /** 家庭电话;国家区号-区号-号码-分机号;"-"不能丢 */
    private String homePhone ;
    /** 办公电话;国家区号-区号-号码-分机号;"-"不能丢 */
    private String workPhone ;
    /** 语言 */
    private String language ;
    /** E-MAIL */
    private String email ;
    /** 证件号 */
    private String idNumber ;
    /** 证件类型 */
    private String idType ;
    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday ;
    /** 联系地址 */
    private String address ;
    /** 客户编号;外部系统编号 */
    private String otherCustomerNo ;
    /** 创建人 */
    private String createdBy ;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime ;

}

package com.paic.ehis.base.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * base_contacts（联系人信息）对象 base_contacts
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseContacts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 所属单位编码 */
    @Excel(name = "所属单位编码")
    private String supplierCode;

    /** 所属单位类型 */
    @Excel(name = "所属单位类型")
    private String supplierType;

    /** 联系人类型 */
    @Excel(name = "联系人类型")
    private String placeType;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 角色 */
    @Excel(name = "角色")
    private String role;

    /** 手机 */
    @Excel(name = "手机")
    private String phone;

    /** 电话 */
    @Excel(name = "电话")
    private String mobile;

    /** 工作开始时间 */
    private String workingStart;

    /** 工作结束时间 */
    private String workingEnd;

    /** 账号 */
    @Excel(name = "账号")
    private String accountNo;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 状态 */
    @Excel(name = "状态",readConverterExp = "01=-有效，02-无效")
    private String status;

    /** 状态 */
    @Excel(name = "变更标志")
    private String updateFlag;

    private String placeTypeName;

    private String orgFlag;

    private String providerCode;

}

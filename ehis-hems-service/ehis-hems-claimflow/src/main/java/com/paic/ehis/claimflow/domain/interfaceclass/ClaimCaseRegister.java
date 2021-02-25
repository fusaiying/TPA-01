package com.paic.ehis.claimflow.domain.interfaceclass;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 案件申请人信息对象 claim_case_register
 * 
 * @author sino
 * @date 2021-02-24
 */
@Data
public class ClaimCaseRegister extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 案件申请ID */
    private Long rgtId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 与被保人关系 */
    @Excel(name = "与被保人关系")
    private String relationIns;

    /** 申请人姓名 */
    @Excel(name = "申请人姓名")
    private String rgtName;

    /** 申请人性别 */
    @Excel(name = "申请人性别")
    private String rgtSex;

    /** 申请人证件类型 */
    @Excel(name = "申请人证件类型")
    private String rgtIdType;

    /** 申请人证件号 */
    @Excel(name = "申请人证件号")
    private String rgtIdNo;

    /** 申请人生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请人生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rgtBirthday;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String rgtMobile;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String rgtEmail;

    /** 电话号 */
    @Excel(name = "电话号")
    private String rgtPhone;

    /** 省 */
    @Excel(name = "省")
    private String rgtProvince;

    /** 市 */
    @Excel(name = "市")
    private String rgtCity;

    /** 区 */
    @Excel(name = "区")
    private String rgtDistrict;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String rgtAddress;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

}

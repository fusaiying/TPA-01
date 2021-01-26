package com.paic.ehis.base.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * base_doctor(医生信息)对象 base_doc_info
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseDocInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 医生编码 */
    private String docCode;

    /** 医生姓名 */
    @Excel(name = "医生姓名")
    private String docName;

    /** 前一个月日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dBefore2;

    /** 现在日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dNow2;

    /** 所属单位编码 */
    @Excel(name = "所属单位编码")
    private String supplierCode;

    /** 一级科室 */
    @Excel(name = "一级科室")
    private String fiestDept;

    /** 二级科室 */
    @Excel(name = "二级科室")
    private String secondDept;

    /** 就诊起始时间 */
    @Excel(name = "就诊起始时间")
    private String startTime;

    /** 就诊截止时间 */
    @Excel(name = "就诊截止时间")
    private String endTime;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String docPhone;

    /** 所属单位编码拼接医院名称+第一科室+第二科室 */
    @Excel(name = "所属服务机构")
    private String docDept;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 状态 */
    @Excel(name = "状态",readConverterExp = "Y=有效，N=无效")
    private String status;

    /** 备注 */
    @Excel(name = "remark")
    private String remark;

    /** 所属服务机构数组 */
    @Excel(name = "")
    private String comment;

    private List<String>  supplierList;

}

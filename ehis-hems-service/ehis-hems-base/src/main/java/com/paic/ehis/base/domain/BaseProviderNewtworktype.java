package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * base_provider_newtworktypet(医疗网络类型)对象 base_provider_newtworktype
 * 
 * @author sino
 * @date 2021-01-04
 */
@Data
public class BaseProviderNewtworktype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String supplierCode;

    private String providerCode;

    /** 网络类型编码 */
    @Excel(name = "网络类型编码")
    private String networktypeCode;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 当前选项 */
    @Excel(name = "当前选项")
    private String oldChoose;

    /** 新选项 */
    @Excel(name = "新选项")
    private String newChoose;

    /** 新选项 */
    @Excel(name = "新选项")
    private String newChooseStr;

    /** 新选项生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "新选项生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date newDate;


    @Excel(name = "网络类型名称")
    private String networktypeName;


    /** 状态 */
    @Excel(name = "状态")
    private String status;


    private Boolean newInputShow;


    private String isCheck;

}

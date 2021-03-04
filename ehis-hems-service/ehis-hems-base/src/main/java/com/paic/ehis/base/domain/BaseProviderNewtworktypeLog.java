package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * base_provider_newtworktype_log(医疗网络类型记录)对象 base_provider_newtworktype_log
 * 
 * @author sino
 * @date 2021-01-04
 */
@Data
public class BaseProviderNewtworktypeLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String supplierCode;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String providerCode;

    /** 网络类型编码 */
    @Excel(name = "网络类型编码")
    private String networktypeCode;

    /** 当前选项 */
    @Excel(name = "当前选项")
    private String oldChoose;

    /** 新选项 */
    @Excel(name = "新选项")
    private String newChoose;

    /** 新选项生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "新选项生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date newDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;


    /** 失效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date failureTime;

    private String networktypeName;

    private String newChooseName;
}

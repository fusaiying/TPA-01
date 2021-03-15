package com.paic.ehis.system.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * base_product_info(服务产品)对象 product_info
 * 
 * @author sino
 * @date 2021-01-15
 */
@Data
public class ProductInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 服务编码 */
    @Excel(name = "服务编码")
    private String serviceCode;

    /** 产品中文名称 */
    @Excel(name = "产品中文名称")
    private String productChname;

    /** 产品英文名称 */
    @Excel(name = "产品英文名称")
    private String productEnname;

    /** 对外产品中文名称 */
    @Excel(name = "对外产品中文名称")
    private String outProductChname;

    /** 对外产品英文名称 */
    @Excel(name = "对外产品英文名称")
    private String outProductEnname;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String productType;

    /** 产品期限信息 */
    @Excel(name = "产品期限信息")
    private String productTimeInfo;

    /** 产品期限日期 */
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "产品期限日期")
    private String productTimeTime;

    /** 产品期限日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "当前时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date nowDate;


    /** 产品期限日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "一个月前日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date beforeDate;

    /** 医护管家 */
    @Excel(name = "医护管家")
    private String steward;

    /** 业务状态 */
    @Excel(name = "业务状态")
    private String bussinessStatus;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String status;

}

package com.paic.ehis.base.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * base_product_check_info(服务产品审核信息)对象 product_check_info
 * 
 * @author sino
 * @date 2021-01-15
 */
@Data
public class ProductSupplierInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 审核结果 */
    @Excel(name = "服务编码")
    private String serviceCode;

    /** 审核意见 */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /** 数据状态 */
    @Excel(name = "优先级")
    private String priority;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String status;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String chname;

    @Excel(name = "所属地区")
    private String addressdetail;

    @Excel(name = "最小价格")
    private String minPrice;

    @Excel(name = "最小大价格")
    private String maxPrice;

    @Excel(name = "合约止期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

}

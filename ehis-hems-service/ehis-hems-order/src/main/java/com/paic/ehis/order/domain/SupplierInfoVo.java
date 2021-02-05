package com.paic.ehis.order.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * order_info(工单信息)对象 order_info
 * 
 * @author sino
 * @date 2021-01-26
 */
@Data
public class SupplierInfoVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商编码 */
    private String supplierCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private List<ContractInfos> contractInfo;

}

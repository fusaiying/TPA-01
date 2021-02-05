package com.paic.ehis.order.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class ContractInfos extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商编码 */
    private String supplierCode;

    /** 联系人 */
    private String contactName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;
}

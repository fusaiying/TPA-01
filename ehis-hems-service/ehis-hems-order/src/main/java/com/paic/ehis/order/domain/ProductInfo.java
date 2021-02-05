package com.paic.ehis.order.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class ProductInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品编码 */
    private String productCode;

    /** 产品名称 */
    private String productName;

}

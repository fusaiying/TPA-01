package com.paic.ehis.system.api.domain;

import lombok.Data;

import java.util.List;

@Data
public class ProductSupplierInfoVo {
    private String productCode;
    private String serviceCode;
    private List<ProductSupplierInfo> productSupplierInfos;
}

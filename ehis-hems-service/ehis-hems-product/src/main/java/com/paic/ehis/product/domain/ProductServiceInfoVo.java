package com.paic.ehis.product.domain;

import lombok.Data;

import java.util.List;

@Data
public class ProductServiceInfoVo {
    private String productCode;
    private List<ProductServiceInfo> productServiceInfos;
}

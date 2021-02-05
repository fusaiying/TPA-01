package com.paic.ehis.product.domain;

import lombok.Data;

import java.util.List;

@Data
public class ProductSaveInfoVo {
    private ProductInfo productInfoData;
    private List<BaseManualInfo> baseManualInfo;
    private ServicesAvailableData servicesAvailableData;
}

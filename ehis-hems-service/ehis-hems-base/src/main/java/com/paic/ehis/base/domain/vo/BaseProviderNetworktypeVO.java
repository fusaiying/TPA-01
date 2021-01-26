package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.BaseProviderNewtworktype;
import lombok.Data;

import java.util.List;

@Data
public class BaseProviderNetworktypeVO {
    private String providerCode;
    private List<BaseProviderNewtworktype> medicalTypeData;
                                           
}

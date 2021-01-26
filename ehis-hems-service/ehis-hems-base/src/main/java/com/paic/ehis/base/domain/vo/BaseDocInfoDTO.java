package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.BaseDocInfo;
import com.paic.ehis.base.domain.BaseProviderInfo;
import lombok.Data;

import java.util.List;

@Data
public class BaseDocInfoDTO {
    private static final long serialVersionUID = 1L;
    /** 医生编码 */
    private BaseDocInfo baseDocInfo;
    private List<BaseProviderInfo> baseProviderInfoList;
    private String [] supplierList;

}

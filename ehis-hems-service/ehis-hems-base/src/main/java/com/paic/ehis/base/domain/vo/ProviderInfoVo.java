package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.*;
import lombok.Data;

import java.util.List;

@Data
public class ProviderInfoVo {
    private BaseProviderInfo baseInfo;
    private BaseProviderApply reserveInfo;
    private com.paic.ehis.base.domain.vo.BaseProviderDepVo departmentInfo;
    private BaseProviderService serviceInfo;
    private com.paic.ehis.base.domain.vo.BaseBankVo closingInfo;
    private com.paic.ehis.base.domain.vo.BaseContactsVo contactInfo;
    private List<BaseProviderWorktime> baseProviderWorktime;
    private String checkResult;
    private String checkAdvice;
    private String providerCode;
    private String orgFlag;
    private String bussinessStatus;
}

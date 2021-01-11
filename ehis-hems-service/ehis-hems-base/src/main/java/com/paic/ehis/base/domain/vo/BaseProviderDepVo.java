package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.BaseProviderDep;
import lombok.Data;

import java.util.List;

/**
 * base_provider_dept(服务商科室)对象 base_provider_dep
 * 
 * @author sino
 * @date 2020-12-25
 */
@Data
public class BaseProviderDepVo
{
    private static final long serialVersionUID = 1L;

   /* *//** 服务商编码 *//*
    @Excel(name = "服务商编码")
    private String providercode;*/

    private List<BaseProviderDep> BaseProviderDeps;
}

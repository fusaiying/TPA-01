package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.ClaimProduct;

import java.util.List;

/**
 * 产品信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-14
 */
public interface ClaimProductMapper 
{
    /**
     * 查询产品信息
     * 
     * @param riskCode 产品信息ID
     * @return 产品信息
     */
    public ClaimProduct selectClaimProductById(String riskCode);

    public ClaimProduct selectClaimProductByIdOne(String riskCode);
}

package com.paic.ehis.finance.mapper;

import com.paic.ehis.finance.domain.ClaimCasePolicy;

import java.util.List;

/**
 * 案件保单关联Mapper接口
 * 
 * @author sino
 * @date 2021-01-14
 */
public interface ClaimCasePolicyMapper 
{

    public List<ClaimCasePolicy> selectClaimCasePolicyByRptNo(String rptNo);

}

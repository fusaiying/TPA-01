package com.paic.ehis.claimflow.service;


import com.paic.ehis.claimflow.domain.ClaimCaseCalRule;
import com.paic.ehis.claimflow.domain.vo.TheAdjustmentSubsidiaryOneVO;

import java.util.List;

/**
 * 案件赔付规则记录Service接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface IClaimCaseCalRuleService 
{

    public List<TheAdjustmentSubsidiaryOneVO> selectClaimCaseCalRuleListByRptNo(String rptNo);

    public int updateAdjustmentSubsidiaryNo(ClaimCaseCalRule claimCaseCalRule);

}

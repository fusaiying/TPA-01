package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseCalRule;
import com.paic.ehis.claimflow.domain.ClaimCaseCalRuleExcept;
import com.paic.ehis.claimflow.domain.vo.TheAdjustmentSubsidiaryOneVO;
import com.paic.ehis.claimflow.mapper.ClaimCaseCalRuleExceptMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseCalRuleMapper;
import com.paic.ehis.claimflow.service.IClaimCaseCalRuleService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案件赔付规则记录Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseCalRuleServiceImpl implements IClaimCaseCalRuleService
{
    @Autowired
    private ClaimCaseCalRuleMapper claimCaseCalRuleMapper;

    @Autowired
    private ClaimCaseCalRuleExceptMapper claimCaseCalRuleExceptMapper;


    @Override
    public List<TheAdjustmentSubsidiaryOneVO> selectClaimCaseCalRuleListByRptNo(String rptNo){

        return claimCaseCalRuleMapper.selectClaimCaseCalRuleListByRptNo(rptNo);
    }

    //不参与理算按钮-就是新增一条不参与理算的数据/将本身变为无效
    @Override
    public int updateAdjustmentSubsidiaryNo(ClaimCaseCalRule claimCaseCalRule){
        claimCaseCalRule.setStatus("N");
        claimCaseCalRule.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCalRule.setUpdateTime(DateUtils.getNowDate());
        claimCaseCalRuleMapper.updateClaimCaseCalRule(claimCaseCalRule);
        ClaimCaseCalRuleExcept claimCaseCalRuleExcept = new ClaimCaseCalRuleExcept();
        claimCaseCalRuleExcept.setRptNo(claimCaseCalRule.getRptNo());
        claimCaseCalRuleExcept.setRuleNo(claimCaseCalRule.getRuleNo());
        claimCaseCalRuleExcept.setStatus("Y");
        claimCaseCalRuleExcept.setCreateBy(SecurityUtils.getUsername());
        claimCaseCalRuleExcept.setCreateTime(DateUtils.getNowDate());

        return claimCaseCalRuleExceptMapper.insertClaimCaseCalRuleExcept(claimCaseCalRuleExcept);
    }

}

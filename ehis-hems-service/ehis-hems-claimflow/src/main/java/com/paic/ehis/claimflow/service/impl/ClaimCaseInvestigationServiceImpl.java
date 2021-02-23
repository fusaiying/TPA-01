package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseInvestigation;
import com.paic.ehis.claimflow.domain.vo.CaseInvestigationVO;
import com.paic.ehis.claimflow.mapper.ClaimCaseInvestigationMapper;
import com.paic.ehis.claimflow.service.IClaimCaseInvestigationService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案件调查任务Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ClaimCaseInvestigationServiceImpl implements IClaimCaseInvestigationService
{
    @Autowired
    private ClaimCaseInvestigationMapper claimCaseInvestigationMapper;

    /**
     * 查询案件调查任务
     * 
     * @param invNo 案件调查任务ID
     * @return 案件调查任务
     */
    @Override
    public ClaimCaseInvestigation selectClaimCaseInvestigationById(String invNo)
    {
        return claimCaseInvestigationMapper.selectClaimCaseInvestigationById(invNo);
    }

    /**
     * 查询案件调查任务
     *
     * @param rptNo 案件调查任务ID
     * @return 案件调查任务
     */
    @Override
    public CaseInvestigationVO selectClaimCaseInvestigationByRptNo(String rptNo) {
        return claimCaseInvestigationMapper.selectClaimCaseInvestigationByRptNo(rptNo);
    }

    /**
     * 查询案件调查任务列表
     * 
     * @param claimCaseInvestigation 案件调查任务
     * @return 案件调查任务
     */
    @Override
    public List<ClaimCaseInvestigation> selectClaimCaseInvestigationList(ClaimCaseInvestigation claimCaseInvestigation)
    {
        return claimCaseInvestigationMapper.selectClaimCaseInvestigationList(claimCaseInvestigation);
    }

    /**
     * 新增案件调查任务
     * 
     * @param claimCaseInvestigation 案件调查任务
     * @return 结果
     */
    @Override
    public ClaimCaseInvestigation insertClaimCaseInvestigation(ClaimCaseInvestigation claimCaseInvestigation)
    {
        int i;
        ClaimCaseInvestigation caseInvestigation = claimCaseInvestigationMapper.selectClaimCaseInvestigationByNew(claimCaseInvestigation.getRptNo());
        if (StringUtils.isNotNull(caseInvestigation)) {
            i = claimCaseInvestigationMapper.updateClaimCaseInvestigation(claimCaseInvestigation);
        }else{
            StringBuilder stringBuilder = new StringBuilder();
            String investigation = PubFun.createMySqlMaxNoUseCache("investigation", 10, 8);
            stringBuilder.append("DL").append(investigation);
            claimCaseInvestigation.setInvNo(stringBuilder.toString());
            claimCaseInvestigation.setInvDate(DateUtils.getNowDate());
            claimCaseInvestigation.setIsHistory("N");
            claimCaseInvestigation.setStatus("Y");
            claimCaseInvestigation.setCreateBy(SecurityUtils.getUsername());
            claimCaseInvestigation.setCreateTime(DateUtils.getNowDate());
            claimCaseInvestigation.setUpdateBy(SecurityUtils.getUsername());
            claimCaseInvestigation.setUpdateTime(DateUtils.getNowDate());
            i = claimCaseInvestigationMapper.insertClaimCaseInvestigation(claimCaseInvestigation);
        }
        return i==1? claimCaseInvestigation:null;
    }

    /**
     * 修改案件调查任务
     * 
     * @param claimCaseInvestigation 案件调查任务
     * @return 结果
     */
    @Override
    public int updateClaimCaseInvestigation(ClaimCaseInvestigation claimCaseInvestigation)
    {
        claimCaseInvestigation.setUpdateTime(DateUtils.getNowDate());
        return claimCaseInvestigationMapper.updateClaimCaseInvestigation(claimCaseInvestigation);
    }

    /**
     * 批量删除案件调查任务
     * 
     * @param invNos 需要删除的案件调查任务ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseInvestigationByIds(Long[] invNos)
    {
        return claimCaseInvestigationMapper.deleteClaimCaseInvestigationByIds(invNos);
    }

    /**
     * 删除案件调查任务信息
     * 
     * @param invNo 案件调查任务ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseInvestigationById(Long invNo)
    {
        return claimCaseInvestigationMapper.deleteClaimCaseInvestigationById(invNo);
    }
}

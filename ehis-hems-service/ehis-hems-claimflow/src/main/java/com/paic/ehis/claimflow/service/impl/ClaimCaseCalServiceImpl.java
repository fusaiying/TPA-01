package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.ClaimCaseCal;
import com.paic.ehis.claimflow.domain.vo.CalBillSummaryVo;
import com.paic.ehis.claimflow.domain.vo.CalConclusionVo;
import com.paic.ehis.claimflow.mapper.ClaimCaseCalMapper;
import com.paic.ehis.claimflow.service.IClaimCaseCalService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 案件赔付信息Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseCalServiceImpl implements IClaimCaseCalService
{
    @Autowired
    private ClaimCaseCalMapper claimCaseCalMapper;

    /**
     * 查询案件赔付信息
     * 
     * @param rptNo 案件赔付信息ID
     * @return 案件赔付信息
     */
    @Override
    public ClaimCaseCal selectClaimCaseCalByRptNo(String rptNo)
    {
        return claimCaseCalMapper.selectClaimCaseCalByRptNo(rptNo);
    }

    /**
     * 理算审核 默认查询案件汇总信息 列表
     *
     * @param claimCaseBill 案件赔付账单明细
     * @return 案件赔付账单明细集合
     */
    @Override
    public CalBillSummaryVo selectCaseCalBillSummary(ClaimCaseBill claimCaseBill) {
        return claimCaseCalMapper.selectClaimCaseBillSummaryByRptNo(claimCaseBill.getRptNo());
    }

    /**
     * 理算审核  查询案件赔付结论信息及理算总值
     *
     * @param rptNo 案件赔付信息ID
     * @return 案件赔付信息
     */
    @Override
    public CalConclusionVo selectClaimCaseCalInformation(String rptNo) {
        //需要判断，是否申诉案件；若是，则需计算差额——暂未实现
        return claimCaseCalMapper.selectClaimCaseCalInformation(rptNo);
    }

    /**
     * 查询案件赔付信息列表
     * 
     * @param claimCaseCal 案件赔付信息
     * @return 案件赔付信息
     */
    @Override
    public List<ClaimCaseCal> selectClaimCaseCalList(ClaimCaseCal claimCaseCal)
    {
        return claimCaseCalMapper.selectClaimCaseCalList(claimCaseCal);
    }

    /**
     * 新增案件赔付信息
     * 
     * @param claimCaseCal 案件赔付信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseCal(ClaimCaseCal claimCaseCal)
    {
        claimCaseCal.setStatus("Y");
        claimCaseCal.setCreateBy(SecurityUtils.getUsername());
        claimCaseCal.setCreateTime(DateUtils.getNowDate());
        claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCal.setUpdateTime(DateUtils.getNowDate());
        return claimCaseCalMapper.insertClaimCaseCal(claimCaseCal);
    }

    /**
     * 修改案件赔付信息
     * 
     * @param claimCaseCal 案件赔付信息
     * @return 结果
     */
    @Transactional
    @Override
    public CalConclusionVo updateClaimCaseCal(ClaimCaseCal claimCaseCal)
    {
        claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCal.setUpdateTime(DateUtils.getNowDate());
        int i = claimCaseCalMapper.updateClaimCaseCal(claimCaseCal);
        if (1==i){
            return claimCaseCalMapper.selectClaimCaseCalInformation(claimCaseCal.getRptNo());
        }else {
            return null;
        }
    }

    /**
     * 批量删除案件赔付信息
     * 
     * @param calIds 需要删除的案件赔付信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalByIds(Long[] calIds)
    {
        return claimCaseCalMapper.deleteClaimCaseCalByIds(calIds);
    }

    /**
     * 删除案件赔付信息信息
     * 
     * @param calId 案件赔付信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalById(Long calId)
    {
        return claimCaseCalMapper.deleteClaimCaseCalById(calId);
    }
}

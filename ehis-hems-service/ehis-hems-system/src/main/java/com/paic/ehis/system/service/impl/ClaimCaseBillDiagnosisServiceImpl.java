package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseBillDiagnosis;
import com.paic.ehis.system.mapper.ClaimCaseBillDiagnosisMapper;
import com.paic.ehis.system.service.IClaimCaseBillDiagnosisService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件账单诊断信息Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ClaimCaseBillDiagnosisServiceImpl implements IClaimCaseBillDiagnosisService
{
    @Autowired
    private ClaimCaseBillDiagnosisMapper claimCaseBillDiagnosisMapper;

    /**
     * 查询案件账单诊断信息
     * 
     * @param digId 案件账单诊断信息ID
     * @return 案件账单诊断信息
     */
    @Override
    public ClaimCaseBillDiagnosis selectClaimCaseBillDiagnosisById(Long digId)
    {
        return claimCaseBillDiagnosisMapper.selectClaimCaseBillDiagnosisById(digId);
    }

    /**
     * 查询案件账单诊断信息列表
     * 
     * @param claimCaseBillDiagnosis 案件账单诊断信息
     * @return 案件账单诊断信息
     */
    @Override
    public List<ClaimCaseBillDiagnosis> selectClaimCaseBillDiagnosisList(ClaimCaseBillDiagnosis claimCaseBillDiagnosis)
    {
        return claimCaseBillDiagnosisMapper.selectClaimCaseBillDiagnosisList(claimCaseBillDiagnosis);
    }

    /**
     * 新增案件账单诊断信息
     * 
     * @param claimCaseBillDiagnosis 案件账单诊断信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseBillDiagnosis(ClaimCaseBillDiagnosis claimCaseBillDiagnosis)
    {
        claimCaseBillDiagnosis.setCreateTime(DateUtils.getNowDate());
        return claimCaseBillDiagnosisMapper.insertClaimCaseBillDiagnosis(claimCaseBillDiagnosis);
    }

    /**
     * 修改案件账单诊断信息
     * 
     * @param claimCaseBillDiagnosis 案件账单诊断信息
     * @return 结果
     */
    @Override
    public int updateClaimCaseBillDiagnosis(ClaimCaseBillDiagnosis claimCaseBillDiagnosis)
    {
        claimCaseBillDiagnosis.setUpdateTime(DateUtils.getNowDate());
        return claimCaseBillDiagnosisMapper.updateClaimCaseBillDiagnosis(claimCaseBillDiagnosis);
    }

    /**
     * 批量删除案件账单诊断信息
     * 
     * @param digIds 需要删除的案件账单诊断信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseBillDiagnosisByIds(Long[] digIds)
    {
        return claimCaseBillDiagnosisMapper.deleteClaimCaseBillDiagnosisByIds(digIds);
    }

    /**
     * 删除案件账单诊断信息信息
     * 
     * @param digId 案件账单诊断信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseBillDiagnosisById(Long digId)
    {
        return claimCaseBillDiagnosisMapper.deleteClaimCaseBillDiagnosisById(digId);
    }
}

package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseBillDiagnosis;

/**
 * 案件账单诊断信息Service接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface IClaimCaseBillDiagnosisService 
{
    /**
     * 查询案件账单诊断信息
     * 
     * @param digId 案件账单诊断信息ID
     * @return 案件账单诊断信息
     */
    public ClaimCaseBillDiagnosis selectClaimCaseBillDiagnosisById(Long digId);

    /**
     * 查询案件账单诊断信息列表
     * 
     * @param claimCaseBillDiagnosis 案件账单诊断信息
     * @return 案件账单诊断信息集合
     */
    public List<ClaimCaseBillDiagnosis> selectClaimCaseBillDiagnosisList(ClaimCaseBillDiagnosis claimCaseBillDiagnosis);

    /**
     * 新增案件账单诊断信息
     * 
     * @param claimCaseBillDiagnosis 案件账单诊断信息
     * @return 结果
     */
    public int insertClaimCaseBillDiagnosis(ClaimCaseBillDiagnosis claimCaseBillDiagnosis);

    /**
     * 修改案件账单诊断信息
     * 
     * @param claimCaseBillDiagnosis 案件账单诊断信息
     * @return 结果
     */
    public int updateClaimCaseBillDiagnosis(ClaimCaseBillDiagnosis claimCaseBillDiagnosis);

    /**
     * 批量删除案件账单诊断信息
     * 
     * @param digIds 需要删除的案件账单诊断信息ID
     * @return 结果
     */
    public int deleteClaimCaseBillDiagnosisByIds(Long[] digIds);

    /**
     * 删除案件账单诊断信息信息
     * 
     * @param digId 案件账单诊断信息ID
     * @return 结果
     */
    public int deleteClaimCaseBillDiagnosisById(Long digId);
}

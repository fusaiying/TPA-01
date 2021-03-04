package com.paic.ehis.claimmgt.mapper;

import com.paic.ehis.claimmgt.domain.ClaimCaseDist;

import java.util.List;

/**
 * 案件分配规则Mapper接口
 * 
 * @author sino
 * @date 2021-01-22
 */
public interface ClaimCaseDistMapper 
{
    /**
     * 查询案件分配规则
     * 
     * @param distId 案件分配规则ID
     * @return 案件分配规则
     */
    public ClaimCaseDist selectClaimCaseDistById(Long distId);

    /**
     * 查询案件分配规则列表
     * 
     * @param claimCaseDist 案件分配规则
     * @return 案件分配规则集合
     */
    public List<ClaimCaseDist> selectClaimCaseDistList(ClaimCaseDist claimCaseDist);
    public ClaimCaseDist selectClaimCaseDistListOne(ClaimCaseDist claimCaseDist);

    /**
     * 新增案件分配规则
     * 
     * @param claimCaseDist 案件分配规则
     * @return 结果
     */
    public int insertClaimCaseDist(ClaimCaseDist claimCaseDist);

    /**
     * 修改案件分配规则
     * 
     * @param claimCaseDist 案件分配规则
     * @return 结果
     */
    public int updateClaimCaseDist(ClaimCaseDist claimCaseDist);

    /**
     * 删除案件分配规则
     * 
     * @param distId 案件分配规则ID
     * @return 结果
     */
    public int deleteClaimCaseDistById(Long distId);

    /**
     * 批量删除案件分配规则
     * 
     * @param distIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseDistByIds(Long[] distIds);

    /**
     * 一键均分
     */
    public int updateClaimCaseAverage(ClaimCaseDist claimCaseDist);

    /**
     * 修改案件
     */
    int updateClaimCaseDistOne(ClaimCaseDist claimCaseDist);

    /**
     * 求出分配比例
     */
    ClaimCaseDist selectClaimCaseDistRate(Long userId,Long roleId);
    /**
     * 查询案件分配列表
     */
    List<ClaimCaseDist> selectClaimCaseDist(ClaimCaseDist claimCaseDist);
}

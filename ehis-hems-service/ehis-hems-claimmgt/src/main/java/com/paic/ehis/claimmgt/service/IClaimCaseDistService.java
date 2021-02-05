package com.paic.ehis.claimmgt.service;

import com.paic.ehis.claimmgt.domain.ClaimCaseDist;
//import com.paic.ehis.claimmgt.domain.dto.ClaimBatchDTO;

import java.util.List;

/**
 * 案件分配规则Service接口
 *
 * @author sino
 * @date 2021-01-22
 */
public interface IClaimCaseDistService
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
     * 批量删除案件分配规则
     *
     * @param distIds 需要删除的案件分配规则ID
     * @return 结果
     */
    public int deleteClaimCaseDistByIds(Long[] distIds);

    /**
     * 删除案件分配规则信息
     *
     * @param distId 案件分配规则ID
     * @return 结果
     */
    public int deleteClaimCaseDistById(Long distId);

    /**
     * 一键均分
     * @param claimCaseDist
     * @return
     */
    int updateClaimCaseAverage(ClaimCaseDist claimCaseDist);

    /**
     * 案件分配规则编辑超链接
     * @param claimCaseDist
     * @return
     */
    public int updateClaimCaseDistOne(ClaimCaseDist claimCaseDist);

    /**
     * 查询案件分配列表
     * @param claimCaseDist
     * @return
     */
    List<ClaimCaseDist> selectClaimCaseDist(ClaimCaseDist claimCaseDist);

    /**
     * 案件交单完成进入受理阶段分配操作人
     */
    //public  int updateCaseDist(ClaimBatchDTO claimBatchDTO);

    /**
     除受理阶段其余阶段分配操作人
     */
    //public int updateOtherCaseDist(String rptNo);
}

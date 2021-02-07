package com.paic.ehis.claimflow.mapper;


import com.paic.ehis.claimflow.domain.ClaimCaseProblem;

import java.util.List;

/**
 * 案件问题件Mapper接口
 *
 * @author sino
 * @date 2021-01-11
 */
public interface ClaimCaseProblemMapper
{
    /**
     * 查询案件问题件
     *
     * @param problemId 案件问题件ID
     * @return 案件问题件
     */
    public ClaimCaseProblem selectClaimCaseProblemById(Long problemId);

    /**
     * 查询案件问题件列表
     *
     * @param claimCaseProblem 案件问题件
     * @return 案件问题件集合
     */
    public List<ClaimCaseProblem> selectClaimCaseProblemList(ClaimCaseProblem claimCaseProblem);

    public List<ClaimCaseProblem> selectClaimCaseProblemList1(ClaimCaseProblem claimCaseProblem);

    public List<ClaimCaseProblem> selectClaimCaseProblemList2(ClaimCaseProblem claimCaseProblem);

    /**
     * 新增案件问题件
     *
     * @param claimCaseProblem 案件问题件
     * @return 结果
     */
    public int insertClaimCaseProblem(ClaimCaseProblem claimCaseProblem);

    /**
     * 修改案件问题件
     *
     * @param claimCaseProblem 案件问题件
     * @return 结果
     */
    public int updateClaimCaseProblem(ClaimCaseProblem claimCaseProblem);

    /**
     * 删除案件问题件
     *
     * @param problemId 案件问题件ID
     * @return 结果
     */
    public int deleteClaimCaseProblemById(Long problemId);

    /**
     * 批量删除案件问题件
     *
     * @param problemIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseProblemByIds(Long[] problemIds);

    /**
     * 查找历史问题件页面
     * @param claimCaseProblem
     * @return
     */
    List<ClaimCaseProblem> selectHistoricalProblem(ClaimCaseProblem claimCaseProblem);

    /**
     *
     * @param rptNo
     * @return
     */
    public List<ClaimCaseProblem> selectClaimCaseProblemByRptNo(String rptNo);
}
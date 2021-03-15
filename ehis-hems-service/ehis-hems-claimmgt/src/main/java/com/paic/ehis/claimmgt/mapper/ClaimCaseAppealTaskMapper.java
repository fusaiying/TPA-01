package com.paic.ehis.claimmgt.mapper;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealTask;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseAppealTaskVo;

import java.util.List;

/**
 * 案件申诉任务Mapper接口
 * 
 * @author sino
 * @date 2021-03-15
 */
public interface ClaimCaseAppealTaskMapper 
{
    /**
     * 查询案件申诉任务
     * 
     * @param taskId 案件申诉任务ID
     * @return 案件申诉任务
     */
    public ClaimCaseAppealTask selectClaimCaseAppealTaskById(Long taskId);

    /**
     * 查询案件申诉任务列表
     * 
     * @param claimCaseAppealTask 案件申诉任务
     * @return 案件申诉任务集合
     */
    public List<ClaimCaseAppealTask> selectClaimCaseAppealTaskList(ClaimCaseAppealTask claimCaseAppealTask);

    /**
     * 新增案件申诉任务
     * 
     * @param claimCaseAppealTask 案件申诉任务
     * @return 结果
     */
    public int insertClaimCaseAppealTask(ClaimCaseAppealTask claimCaseAppealTask);

    /**
     * 修改案件申诉任务
     * 
     * @param claimCaseAppealTask 案件申诉任务
     * @return 结果
     */
    public int updateClaimCaseAppealTask(ClaimCaseAppealTask claimCaseAppealTask);

    /**
     * 删除案件申诉任务
     * 
     * @param taskId 案件申诉任务ID
     * @return 结果
     */
    public int deleteClaimCaseAppealTaskById(Long taskId);

    /**
     * 批量删除案件申诉任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseAppealTaskByIds(Long[] taskIds);


    /**
     * 查询案件申诉任务列表
     *
     * @param vo 案件申诉任务
     * @return 案件申诉任务集合
     */
    public List<ClaimCaseAppealTaskVo> getAppealList(ClaimCaseAppealTaskVo vo);

}

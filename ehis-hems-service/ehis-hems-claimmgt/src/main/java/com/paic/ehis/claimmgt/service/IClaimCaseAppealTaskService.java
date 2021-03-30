package com.paic.ehis.claimmgt.service;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealTask;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseAppealTaskVo;

import java.util.List;

/**
 * 案件申诉任务Service接口
 * 
 * @author sino
 * @date 2021-03-15
 */
public interface IClaimCaseAppealTaskService 
{
    /**
     * 查询案件申诉任务
     * 
     * @param param 案件申诉实体类
     * @return 案件申诉任务
     */
    public ClaimCaseAppealTask selectClaimCaseAppealTaskByParam(ClaimCaseAppealTask param);

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
    public ClaimCaseAppealTask updateClaimCaseAppealTask(ClaimCaseAppealTask claimCaseAppealTask);

    /**
     * 批量删除案件申诉任务
     * 
     * @param taskIds 需要删除的案件申诉任务ID
     * @return 结果
     */
    public int deleteClaimCaseAppealTaskByIds(Long[] taskIds);

    /**
     * 删除案件申诉任务信息
     * 
     * @param taskId 案件申诉任务ID
     * @return 结果
     */
    public int deleteClaimCaseAppealTaskById(Long taskId);

    /**
     * 查询案件申诉任务列表
     *
     * @param vo 案件申诉任务
     * @return 案件申诉任务集合
     */
    public List<ClaimCaseAppealTaskVo> getAppealList(ClaimCaseAppealTaskVo vo);


    /**
     * 更新案件修正账单号
     *
     * @param claimCaseAppealTask 案件申诉任务
     */
    public int updateClaimCaseAppealBill(ClaimCaseAppealTask claimCaseAppealTask);

    public void clearClaimTableData(ClaimCaseAppealTask bean);

    public String getNewRptNo(String appealRptNo);
}

package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.WorkOrderAccept;

import java.util.List;


/**
 * 工单申请信息 Service接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface IWorkOrderAcceptService 
{
    /**
     * 查询工单申请信息 
     * 
     * @param workOrderNo 工单申请信息 ID
     * @return 工单申请信息 
     */
    public WorkOrderAccept selectWorkOrderAcceptById(String workOrderNo);

    /**
     * 查询工单申请信息 列表
     * 
     * @param workOrderAccept 工单申请信息 
     * @return 工单申请信息 集合
     */
    public List<WorkOrderAccept> selectWorkOrderAcceptList(WorkOrderAccept workOrderAccept);

    /**
     * 新增工单申请信息 
     * 
     * @param workOrderAccept 工单申请信息 
     * @return 结果
     */
    public int insertWorkOrderAccept(WorkOrderAccept workOrderAccept);

    /**
     * 修改工单申请信息 
     * 
     * @param workOrderAccept 工单申请信息 
     * @return 结果
     */
    public int updateWorkOrderAccept(WorkOrderAccept workOrderAccept);

    /**
     * 批量删除工单申请信息 
     * 
     * @param workOrderNos 需要删除的工单申请信息 ID
     * @return 结果
     */
    public int deleteWorkOrderAcceptByIds(String[] workOrderNos);

    /**
     * 删除工单申请信息 信息
     * 
     * @param workOrderNo 工单申请信息 ID
     * @return 结果
     */
    public int deleteWorkOrderAcceptById(String workOrderNo);
}

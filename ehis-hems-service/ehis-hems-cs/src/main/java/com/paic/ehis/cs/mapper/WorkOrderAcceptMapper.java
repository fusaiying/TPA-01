package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;

import java.util.List;


/**
 * 工单申请信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface WorkOrderAcceptMapper 
{
    /**
     * 查询工单申请信息 
     * 
     * @param workOrderNo 工单申请信息 ID
     * @return 工单申请信息 
     */
    public WorkOrderAccept selectWorkOrderAcceptById(String workOrderNo);

    /**
     * 计算处理时长
     * @param workOrderNo
     * @return
     */
    public WorkOrderAccept selectProcessingTime(String workOrderNo);

    /**
     * 查询工单是否挂起
     * @param workOrderAccept
     * @return
     */
    public WorkOrderAccept selectHangFlag(WorkOrderAccept workOrderAccept);

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
     * 工单挂起
     * @param complaintAcceptVo
     * @return
     */

    public int updateHangReason(ComplaintAcceptVo complaintAcceptVo);

    public int updateWorkOrderStatus(WorkOrderAccept workOrderAccept);

    /**
     * 删除工单申请信息 
     * 
     * @param workOrderNo 工单申请信息 ID
     * @return 结果
     */
    public int deleteWorkOrderAcceptById(String workOrderNo);

    /**
     * 批量删除工单申请信息 
     * 
     * @param workOrderNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkOrderAcceptByIds(String[] workOrderNos);
}

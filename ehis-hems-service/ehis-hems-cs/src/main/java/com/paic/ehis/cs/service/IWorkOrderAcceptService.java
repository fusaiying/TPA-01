package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;

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
     *基础受理保存
     *@param businessData
     * @return  结果
     */
    public int insertBasicServiceApplication(BasicServiceAppilcation businessData);

    /**
     * 增值服务受理处理
     * @param businessData
     * @return
     */
    public int insertIncrementServiceApplication(IncrementApplication businessData);











//    ##################################################################################################################
    /**
     * 查询工单申请信息 
     * 
     * @param workOrderNo 工单申请信息 ID
     * @return 工单申请信息 
     */
    public WorkOrderAccept selectWorkOrderAcceptById(String workOrderNo);

    //获取实时查询案件操作人和状态
    public WorkOrderAccept selectWorkOrderAcceptById1(String workOrderNo);

    //修改实时查询案件操作人和状态
    public WorkOrderAccept selectWorkOrderAcceptById2(String workOrderNo);

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

    /**
     * 工单挂起
     * @param complaintAcceptVo
     * @return
     */
   public  int updateHangReason(ComplaintAcceptVo complaintAcceptVo);

    /**
     * 查询工单是否挂起
     */
    WorkOrderAccept selectHangFlag(WorkOrderAccept workOrderAccept);
}

package com.paic.ehis.cs.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.mapper.WorkOrderAcceptMapper;
import com.paic.ehis.cs.service.IWorkOrderAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 工单申请信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-21
 */
@Service
public class WorkOrderAcceptServiceImpl implements IWorkOrderAcceptService
{
    @Autowired
    private WorkOrderAcceptMapper workOrderAcceptMapper;

    /**
     * 查询工单申请信息 
     * 
     * @param workOrderNo 工单申请信息 ID
     * @return 工单申请信息 
     */
    @Override
    public WorkOrderAccept selectWorkOrderAcceptById(String workOrderNo)
    {
        return workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
    }

    /**
     * 查询工单申请信息 列表
     * 
     * @param workOrderAccept 工单申请信息 
     * @return 工单申请信息 
     */
    @Override
    public List<WorkOrderAccept> selectWorkOrderAcceptList(WorkOrderAccept workOrderAccept)
    {
        return workOrderAcceptMapper.selectWorkOrderAcceptList(workOrderAccept);
    }

    /**
     * 新增工单申请信息 
     * 
     * @param workOrderAccept 工单申请信息 
     * @return 结果
     */
    @Override
    public int insertWorkOrderAccept(WorkOrderAccept workOrderAccept)
    {
        workOrderAccept.setCreateTime(DateUtils.getNowDate());
        return workOrderAcceptMapper.insertWorkOrderAccept(workOrderAccept);
    }

    /**
     * 修改工单申请信息 
     * 
     * @param workOrderAccept 工单申请信息 
     * @return 结果
     */
    @Override
    public int updateWorkOrderAccept(WorkOrderAccept workOrderAccept)
    {
        workOrderAccept.setUpdateTime(DateUtils.getNowDate());
        return workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);
    }

    /**
     * 批量删除工单申请信息 
     * 
     * @param workOrderNos 需要删除的工单申请信息 ID
     * @return 结果
     */
    @Override
    public int deleteWorkOrderAcceptByIds(String[] workOrderNos)
    {
        return workOrderAcceptMapper.deleteWorkOrderAcceptByIds(workOrderNos);
    }

    /**
     * 删除工单申请信息 信息
     * 
     * @param workOrderNo 工单申请信息 ID
     * @return 结果
     */
    @Override
    public int deleteWorkOrderAcceptById(String workOrderNo)
    {
        return workOrderAcceptMapper.deleteWorkOrderAcceptById(workOrderNo);
    }

    /**
     * 工单挂起
     * @param complaintAcceptVo
     * @return
     */
    @Override
    public int updateHangReason(ComplaintAcceptVo complaintAcceptVo) {
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        workOrderAccept.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        workOrderAccept.setHangReason(complaintAcceptVo.getHangReason());
        workOrderAccept.setHangFlag(complaintAcceptVo.getHangFlag());
        return workOrderAcceptMapper.updateHangReason(complaintAcceptVo);
    }
    /**
     * 查询工单是否挂起
     */
    @Override
    public WorkOrderAccept selectHangFlag(WorkOrderAccept workOrderAccept) {
        return workOrderAcceptMapper.selectHangFlag(workOrderAccept);
    }
}

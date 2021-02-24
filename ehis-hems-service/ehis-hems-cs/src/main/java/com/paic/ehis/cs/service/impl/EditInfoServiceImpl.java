package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.EditInfo;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IEditInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.soap.Detail;

/**
 * 修改信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-05
 */
@Service
public class EditInfoServiceImpl implements IEditInfoService
{
    @Autowired
    private EditInfoMapper editInfoMapper;
    @Autowired
    private FlowLogMapper flowLogMapper;
    @Autowired
    private DemandAcceptVoMapper demandAcceptVoMapper;
    @Autowired
    private ReservationAcceptVoMapper reservationAcceptVoMapper;
    @Autowired
    private ComplaintAcceptVoMapper complaintAcceptVoMapper;

    /**
     * 查询修改信息 
     * 
     * @param editId 修改信息 ID
     * @return 修改信息 
     */
    @Override
    public EditInfo selectEditInfoById(Long editId)
    {
        return editInfoMapper.selectEditInfoById(editId);
    }

    /**
     * 查询修改信息 列表
     * 
     * @param editInfo 修改信息 
     * @return 修改信息 
     */
    @Override
    public List<EditInfo> selectEditInfoList(EditInfo editInfo)
    {
        return editInfoMapper.selectEditInfoList(editInfo);
    }

    /**
     * 新增修改信息 
     * 
     * @param editInfo 修改信息 
     * @return 结果
     */
    @Override
    public int insertEditInfo(EditInfo editInfo)
    {
        return editInfoMapper.insertEditInfo(editInfo);
    }

    /**
     * 修改修改信息 
     * 
     * @param editInfo 修改信息 
     * @return 结果
     */
    @Override
    public int updateEditInfo(EditInfo editInfo)
    {
        return editInfoMapper.updateEditInfo(editInfo);
    }

    /**
     * 批量删除修改信息 
     * 
     * @param editIds 需要删除的修改信息 ID
     * @return 结果
     */
    @Override
    public int deleteEditInfoByIds(Long[] editIds)
    {
        return editInfoMapper.deleteEditInfoByIds(editIds);
    }

    /**
     * 删除修改信息 信息
     * 
     * @param editId 修改信息 ID
     * @return 结果
     */
    @Override
    public int deleteEditInfoById(Long editId)
    {
        return editInfoMapper.deleteEditInfoById(editId);
    }
    /**
     * 信息需求  取消页面提交按钮
     * @param demandAcceptVo
     * @return
     */
    @Override
    public int cancelSubmit(DemandAcceptVo demandAcceptVo) {


            EditInfo editInfo=new EditInfo();
        //随机生成流水号
        editInfo.setEditId(PubFun.createMySqlMaxNoUseCache("edit_id",10,6));
        editInfo.setWorkOrderId(demandAcceptVo.getWorkOrderNo());
        editInfo.setStatus("05");//05 取消状态
        editInfo.setCreatedBy(SecurityUtils.getUsername());
        editInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setUpdatedBy(SecurityUtils.getUsername());
        editInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setEditReason(demandAcceptVo.getEditReason());
        editInfo.setEditRemark(demandAcceptVo.getEditRemark());
        editInfoMapper.insertEditInfo(editInfo);


        //轨迹表生成数据
        FlowLog flowLog=new FlowLog();
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id",10,6));
        //flowLog.setWorkOrderNo();从前端获得
        flowLog.setStatus("05");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        flowLogMapper.insertFlowLog(flowLog);

        demandAcceptVo.setStatus("05");
        return demandAcceptVoMapper.updateCancelStatus(demandAcceptVo.getWorkOrderNo());
    }
    /**
     * 预约取消页面提交按钮
     */
    @Override
    public int orderCancelSubmit(ReservationAcceptVo reservationAcceptVo) {

        EditInfo editInfo=new EditInfo();
        //随机生成流水号
        editInfo.setEditId(PubFun.createMySqlMaxNoUseCache("edit_id",10,6));
        editInfo.setWorkOrderId(reservationAcceptVo.getWorkOrderNo());
        editInfo.setStatus("05");//05 取消状态
        editInfo.setCreatedBy(SecurityUtils.getUsername());
        editInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setUpdatedBy(SecurityUtils.getUsername());
        editInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setEditReason(reservationAcceptVo.getEditReason());
        editInfo.setEditRemark(reservationAcceptVo.getEditRemark());
        editInfoMapper.insertEditInfo(editInfo);

        //轨迹表生成数据
        FlowLog flowLog=new FlowLog();
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id",10,6));
        //flowLog.setWorkOrderNo();从前端获得
        flowLog.setStatus("05");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        flowLogMapper.insertFlowLog(flowLog);

        reservationAcceptVo.setStatus("05");
        return reservationAcceptVoMapper.updateOrderCancelStatus(reservationAcceptVo.getWorkOrderNo());
    }


    /**
     * 投诉取消提交
     * @param complaintAcceptVo
     * @return
     */
    @Override
    public int reservedCancelSubmit(ComplaintAcceptVo complaintAcceptVo) {
        //取消原因  取消说明
        EditInfo editInfo=new EditInfo();
        //随机生成流水号
        editInfo.setEditId(PubFun.createMySqlMaxNoUseCache("edit_id",10,6));
        editInfo.setWorkOrderId(complaintAcceptVo.getWorkOrderNo());
        editInfo.setStatus("05");//05 取消状态
        editInfo.setCreatedBy(SecurityUtils.getUsername());
        editInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setUpdatedBy(SecurityUtils.getUsername());
        editInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setEditReason(complaintAcceptVo.getEditReason());
        editInfo.setEditRemark(complaintAcceptVo.getEditRemark());
        editInfoMapper.insertEditInfo(editInfo);


        //轨迹表生成数据
        FlowLog flowLog=new FlowLog();
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id",10,6));
        flowLog.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        flowLog.setStatus("05");//05 取消状态
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));

        flowLogMapper.insertFlowLog(flowLog);

        //将主表状态改为已取消
        complaintAcceptVo.setStatus("05");
        return complaintAcceptVoMapper.updateReservedCancelStatus(complaintAcceptVo.getWorkOrderNo());
    }
}

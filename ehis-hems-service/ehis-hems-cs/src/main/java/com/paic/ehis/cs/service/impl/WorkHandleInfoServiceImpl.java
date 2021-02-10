package com.paic.ehis.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.FieldMap;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.*;
import com.paic.ehis.cs.mapper.FieldMapMapper;
import com.paic.ehis.cs.mapper.FlowLogMapper;
import com.paic.ehis.cs.mapper.WorkOrderAcceptMapper;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.cs.mapper.WorkHandleInfoMapper;
import com.paic.ehis.cs.domain.WorkHandleInfo;
import com.paic.ehis.cs.service.IWorkHandleInfoService;

/**
 * 工单处理信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-02
 */
@Service
public class WorkHandleInfoServiceImpl implements IWorkHandleInfoService 
{
    @Autowired
    private WorkHandleInfoMapper workHandleInfoMapper;
    @Autowired
    private WorkOrderAcceptMapper workOrderAcceptMapper;
    @Autowired
    private FieldMapMapper fieldMapMapper;
    @Autowired
    private FlowLogMapper flowLogMapper;

    /**
     * 查询工单处理信息 
     * 
     * @param handleId 工单处理信息 ID
     * @return 工单处理信息 
     */
    @Override
    public WorkHandleInfo selectWorkHandleInfoById(Long handleId)
    {
        return workHandleInfoMapper.selectWorkHandleInfoById(handleId);
    }

    /**
     * 查询工单处理信息 列表
     * 
     * @param workHandleInfo 工单处理信息 
     * @return 工单处理信息 
     */
    @Override
    public List<WorkHandleInfo> selectWorkHandleInfoList(WorkHandleInfo workHandleInfo)
    {
        return workHandleInfoMapper.selectWorkHandleInfoList(workHandleInfo);
    }

    /**
     * 新增工单处理信息 
     * 
     * @param workHandleInfo 工单处理信息 
     * @return 结果
     */
    @Override
    public int insertWorkHandleInfo(WorkHandleInfo workHandleInfo)
    {
        return workHandleInfoMapper.insertWorkHandleInfo(workHandleInfo);
    }

    /**
     * 修改工单处理信息 
     * 
     * @param workHandleInfo 工单处理信息 
     * @return 结果
     */
    @Override
    public int updateWorkHandleInfo(WorkHandleInfo workHandleInfo)
    {
        return workHandleInfoMapper.updateWorkHandleInfo(workHandleInfo);
    }

    /**
     * 批量删除工单处理信息 
     * 
     * @param handleIds 需要删除的工单处理信息 ID
     * @return 结果
     */
    @Override
    public int deleteWorkHandleInfoByIds(Long[] handleIds)
    {
        return workHandleInfoMapper.deleteWorkHandleInfoByIds(handleIds);
    }

    /**
     * 删除工单处理信息 信息
     * 
     * @param handleId 工单处理信息 ID
     * @return 结果
     */
    @Override
    public int deleteWorkHandleInfoById(Long handleId)
    {
        return workHandleInfoMapper.deleteWorkHandleInfoById(handleId);
    }
    /**
     * 修改服务处理页面
     * @param serviceProcessingVo
     * @return
     */
    @Override
    public int insertServiceInfo(ServiceProcessingVo serviceProcessingVo) {
        if(serviceProcessingVo.getSign()=="01"){
            WorkHandleInfo workHandleInfo=new WorkHandleInfo();
            workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id",10,6)));
            workHandleInfo.setHandleType("处理");
            workHandleInfo.setStatus("Y");
            workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
            workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
            workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setWorkOrderNo(serviceProcessingVo.getWorkOrderNo());
            workHandleInfo.setRemark(serviceProcessingVo.getRemark());
            List<FieldMap> KVMap=fieldMapMapper.selectKVMap("work_handle_info","ServiceProcessingVo");
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
                VoUtils voUtils=new VoUtils<ServiceProcessingVo>();
                workHandleInfo= (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo,map,serviceProcessingVo);}
            return workHandleInfoMapper.insertServiceProcessing(workHandleInfo);
        }else {
            FlowLog flowLog=new FlowLog();
            flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("handle_id",10,6));
            //flowLog.setWorkOrderNo();从前端获得
            flowLog.setStatus("03");
            flowLog.setCreatedBy(SecurityUtils.getUsername());
            flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setUpdatedBy(SecurityUtils.getUsername());
            flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setWorkOrderNo(serviceProcessingVo.getWorkOrderNo());
            flowLogMapper.updateFlowLog(flowLog);


            WorkHandleInfo workHandleInfo=new WorkHandleInfo();
            workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id",10,6)));
            workHandleInfo.setHandleType("处理");
            workHandleInfo.setStatus("Y");
            workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
            workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
            workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setWorkOrderNo(serviceProcessingVo.getWorkOrderNo());
            workHandleInfo.setRemark(serviceProcessingVo.getRemark());
            List<FieldMap> KVMap=fieldMapMapper.selectKVMap("work_handle_info","ServiceProcessingVo");
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
                VoUtils voUtils=new VoUtils<ServiceProcessingVo>();
                workHandleInfo= (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo,map,serviceProcessingVo);}

            return workHandleInfoMapper.insertServiceProcessing(workHandleInfo);

        }


    }
    /**
     *  协办处理页面 信息需求  服务处理
     * @param serviceProcessingVo
     * @return
     */
    @Override
    public int teamworkProcessing(ServiceProcessingVo serviceProcessingVo) {
        WorkHandleInfo workHandleInfo = new WorkHandleInfo();
        workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id", 10, 6)));
        workHandleInfo.setHandleType("处理");
        workHandleInfo.setStatus("Y");
        workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
        workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
        workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        workHandleInfo.setWorkOrderNo(serviceProcessingVo.getWorkOrderNo());
        workHandleInfo.setRemark(serviceProcessingVo.getRemark());
        List<FieldMap> KVMap = fieldMapMapper.selectKVMap("work_handle_info", "ServiceProcessingVo");
        for (FieldMap fieldMap : KVMap) {
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map = new HashMap<String, String>();
            map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
            VoUtils voUtils = new VoUtils<ServiceProcessingVo>();
            workHandleInfo = (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo, map, serviceProcessingVo);
        }
        return workHandleInfoMapper.insertServiceProcessing(workHandleInfo);
    }
    /**
     * 协办投诉处理
     * @param complaintDealVo
     * @return
     */
    @Override
    public int assistInComplaint(ComplaintDealVo complaintDealVo) {
        WorkHandleInfo workHandleInfo = new WorkHandleInfo();
        workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id", 10, 6)));
        workHandleInfo.setHandleType("处理");
        workHandleInfo.setStatus("Y");
        workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
        workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
        workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
        workHandleInfo.setRemark(complaintDealVo.getRemark());
        List<FieldMap> KVMap = fieldMapMapper.selectKVMap("work_handle_info", "ComplaintDealVo");
        for (FieldMap fieldMap : KVMap) {
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map = new HashMap<String, String>();
            map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
            VoUtils voUtils = new VoUtils<ComplaintDealVo>();
            workHandleInfo = (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo, map, complaintDealVo);
        }
        return workHandleInfoMapper.assistInComplaint(complaintDealVo);
    }

    /**
     * 预约处理
     * @param reservationDealVo
     * @return
     */
    @Override
    public int insertResevationDeal(ReservationDealVo reservationDealVo) {
        if(reservationDealVo.getSign()=="01"){
        WorkHandleInfo workHandleInfo=new WorkHandleInfo();
        workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id",10,6)));
        workHandleInfo.setHandleType("处理");
        workHandleInfo.setStatus("02");
        workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
        workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
        workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        workHandleInfo.setWorkOrderNo(reservationDealVo.getWorkOrderNo());
        workHandleInfo.setRemark(reservationDealVo.getRemark());
       //获取处理时长
        workOrderAcceptMapper.selectProcessingTime(reservationDealVo.getWorkOrderNo());

        List<FieldMap> KVMap=fieldMapMapper.selectKVMap("work_handle_info","ReservationDealVo");
        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
            VoUtils voUtils=new VoUtils<ReservationDealVo>();
            workHandleInfo= (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo,map,reservationDealVo);}
        return workHandleInfoMapper.insertServiceProcessing(workHandleInfo);}
        else {
            FlowLog flowLog=new FlowLog();
            flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("handle_id",10,6));
            //flowLog.setWorkOrderNo();从前端获得
            flowLog.setStatus("03");
            flowLog.setCreatedBy(SecurityUtils.getUsername());
            flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setUpdatedBy(SecurityUtils.getUsername());
            flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setWorkOrderNo(reservationDealVo.getWorkOrderNo());
            flowLogMapper.updateFlowLog(flowLog);


            WorkHandleInfo workHandleInfo=new WorkHandleInfo();
            workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id",10,6)));
            workHandleInfo.setHandleType("处理");
            workHandleInfo.setStatus("03");
            workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
            workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
            workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setWorkOrderNo(reservationDealVo.getWorkOrderNo());
            workHandleInfo.setRemark(reservationDealVo.getRemark());
            List<FieldMap> KVMap=fieldMapMapper.selectKVMap("work_handle_info","ReservationDealVo");
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
                VoUtils voUtils=new VoUtils<ReservationDealVo>();
                workHandleInfo= (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo,map,reservationDealVo);}

            return workHandleInfoMapper.insertServiceProcessing(workHandleInfo);

        }
    }

    /**
     * 查询工单业处理信息 预约  信息需求
     * @param acceptDTO
     * @return
     */
    @Override
    public List<ReservationAcceptVo> selectWorkOrder(AcceptDTO acceptDTO) {
        return null;
    }
}

package com.paic.ehis.cs.service.impl;


import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.QualityInspectionAccept;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.mapper.FlowLogMapper;
import com.paic.ehis.cs.mapper.QualityInspectionAcceptMapper;
import com.paic.ehis.cs.service.IQualityInspectionAcceptService;
import com.paic.ehis.cs.utils.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 质检受理服务实现 QualityInspectionAcceptServiceImpl
 * by Louis
 */
@Service
public class QualityInspectionAcceptServiceImpl implements IQualityInspectionAcceptService
{

    @Autowired
    private QualityInspectionAcceptMapper qualityInspectionAcceptMapper;

    @Autowired
    private FlowLogMapper flowLogMapper;

    @Override
    public List<AcceptVo> selectSendPoolData(WorkOrderQueryDTO demandQuery) {
        List<AcceptVo> list=qualityInspectionAcceptMapper.selectSendByVo(demandQuery);
        return list==null ? new ArrayList<AcceptVo>() : list;
    }

    @Override
    public List<AcceptVo> selectAcceptPoolData(WorkOrderQueryDTO workOrderQueryDTO) {
        List<AcceptVo> list=qualityInspectionAcceptMapper.selectAcceptByVo(workOrderQueryDTO);
        return list==null ? new ArrayList<AcceptVo>() : list;
    }

    @Transactional
    @Override
    public int insertAcceptVoBatch(String[] ids, Map<String, String> param){
        List<QualityInspectionAccept> list=new ArrayList<>();
        List<FlowLog> flowLogList=new ArrayList<>();
        FlowLog flowLog=null;
        QualityInspectionAccept qualityInspectionAccept=null;
        for (int i = 0; i < ids.length; i++) {
            flowLog=new FlowLog();
            //流转记录添加
            String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",32,20);
            flowLog.setFlowId(flow_id);
            flowLog.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            flowLog.setUpdatedTime(DateUtils.getNowDate());
            flowLog.setWorkOrderNo(ids[i]);
            flowLog.setLinkCode(param.get("linkCode"));
            flowLog.setOperateCode(param.get("operateCode"));
            flowLogList.add(flowLog);

            qualityInspectionAccept=new QualityInspectionAccept();
            qualityInspectionAccept.setWorkOrderNo(ids[i]);
            qualityInspectionAccept.setStatus(param.get("status"));
            qualityInspectionAccept.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            qualityInspectionAccept.setCreatedTime(DateUtils.getNowDate());
            qualityInspectionAccept.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            qualityInspectionAccept.setUpdatedTime(DateUtils.getNowDate());
            list.add(qualityInspectionAccept);
        }
        //记录操作轨迹
        flowLogMapper.insertBatch(flowLogList);
        return qualityInspectionAcceptMapper.insertAcceptBatch(list);
    }

    @Transactional
    @Override
    public int inspectionHandleStatusByIds(String[] ids, Map<String, String> param) {
        WorkOrderQueryDTO workOrderQueryDTO=new WorkOrderQueryDTO();
        List<String> idList= Arrays.asList(ids);
        workOrderQueryDTO.setAcceptStatus(param.get("status"));
        workOrderQueryDTO.setWorkOrderNoList(idList);
        workOrderQueryDTO.setUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
        workOrderQueryDTO.setUpdateTime(DateUtils.getNowDate());

        List<FlowLog> flowLogList=new ArrayList<>();
        FlowLog flowLog=null;
        for (int i = 0; i < ids.length; i++) {
            flowLog=new FlowLog();
            //流转记录添加
            String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",32,20);
            flowLog.setFlowId(flow_id);
            flowLog.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            flowLog.setUpdatedTime(DateUtils.getNowDate());
            flowLog.setWorkOrderNo(ids[i]);
//            flowLog.setSubId(ids[i]);

            flowLog.setLinkCode(param.get("linkCode"));
            flowLog.setOperateCode(param.get("operateCode"));
            flowLogList.add(flowLog);
        }
        //记录操作轨迹
        flowLogMapper.insertBatch(flowLogList);
        //表状态更新
        return qualityInspectionAcceptMapper.updateAcceptByVoDTO(workOrderQueryDTO);
    }

    @Override
    public List<AcceptVo> getAcceptVoByIds(String[] ids) {
        List<String> idList= Arrays.asList(ids);
        WorkOrderQueryDTO workOrderQueryDTO=new WorkOrderQueryDTO();
        workOrderQueryDTO.setWorkOrderNoList(idList);
        List<AcceptVo> list=qualityInspectionAcceptMapper.selectAcceptByVo(workOrderQueryDTO);
        return list==null ? new ArrayList<AcceptVo>() : list;
    }

}


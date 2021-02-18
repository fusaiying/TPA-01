package com.paic.ehis.cs.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.mapper.FlowLogMapper;
import com.paic.ehis.cs.mapper.WorkOrderAcceptMapper;
import com.paic.ehis.cs.service.IWorkOrderAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.paic.ehis.common.core.utils.PubFun.createMySqlMaxNoUseCache;

/**
 * 费用项信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class WorkOrderAcceptServiceImpl implements IWorkOrderAcceptService
{
    @Autowired
    private WorkOrderAcceptMapper workOrderAcceptMapper;

    @Autowired
    private FlowLogMapper flowLogMapper;

    @Override
    public List<AcceptVo> selectAcceptPoolData(WorkOrderQueryDTO workOrderQueryDTO) {
        return workOrderAcceptMapper.selectAcceptByVo(workOrderQueryDTO);
    }

    @Transactional
    @Override
    public int inspectionHandleStatusByIds(String[] ids, Map<String,String> param) {
        WorkOrderQueryDTO workOrderQueryDTO=new WorkOrderQueryDTO();
        List<String> idList= Arrays.asList(ids);
        workOrderQueryDTO.setAcceptStatus(param.get("status"));
        workOrderQueryDTO.setWorkOrderNoList(idList);
        workOrderQueryDTO.setUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
        workOrderQueryDTO.setUpdateTime(DateUtils.getNowDate());
        //流转记录添加
        String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",32,20);
        List<FlowLog> flowLogList=new ArrayList<>();
        FlowLog flowLog=null;
        for (int i = 0; i < ids.length; i++) {
            flowLog=new FlowLog();
            flowLog.setFlowId(flow_id);
            flowLog.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            flowLog.setUpdatedTime(DateUtils.getNowDate());
            flowLog.setWorkOrderNo(ids[i]);
            flowLog.setLinkCode(param.get("linkCode"));
            flowLog.setOperateCode(param.get("operateCode"));
            flowLogList.add(flowLog);
        }

        //详情表更新
        workOrderAcceptMapper.updateAcceptDetailByVoDTO(workOrderQueryDTO);
        //记录操作轨迹
        flowLogMapper.insertBatch(flowLogList);
        //表状态更新
        return workOrderAcceptMapper.updateAcceptByVoDTO(workOrderQueryDTO);
    }

    @Override
    public List<AcceptVo> getAcceptVoByIds(String[] ids) {
        List<String> idList= Arrays.asList(ids);
        WorkOrderQueryDTO workOrderQueryDTO=new WorkOrderQueryDTO();
        workOrderQueryDTO.setWorkOrderNoList(idList);
        return workOrderAcceptMapper.selectAcceptByVo(workOrderQueryDTO);
    }

}

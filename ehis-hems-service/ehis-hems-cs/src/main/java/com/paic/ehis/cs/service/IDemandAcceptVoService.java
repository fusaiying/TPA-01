package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.WorkOrderVo;


import java.util.List;

public interface IDemandAcceptVoService {
    /**
     * 信息需求公共池
     * @param workOrderQueryDTO
     * @return
     */
    public List<DemandAcceptVo> selectDemandAcceptList(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 信息需求个人池
     * @param workOrderQueryDTO
     * @return
     */
    public List<DemandAcceptVo> selectDemandAcceptList2(WorkOrderQueryDTO workOrderQueryDTO);

    public DemandAcceptVo selectDemandAcceptVo(String workOrderNo);

    public int updateStatus(String workOrderNo);

    public int updateStatusM(String[] workOrderNos);

    public int insertServiceInfo(DemandAcceptVo demandAcceptVo);

    public int updateServiceInfo(DemandAcceptVo demandAcceptVo);

    /**
     * 协办工作池
     * @param workOrderQueryDTO
     * @return
     */
    List<DemandAcceptVo> selectAssist(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 工单查询
     * @param acceptDTO
     * @return
     */
    List<WorkOrderVo> selectWorkOrder(AcceptDTO acceptDTO);

}
package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.WorkOrderVo;


import java.util.List;

public interface IDemandAcceptVoService {
    public List<DemandAcceptVo> selectDemandAcceptList(AcceptDTO acceptDTO);

    public List<DemandAcceptVo> selectDemandAcceptList2(AcceptDTO acceptDTO);

    public DemandAcceptVo selectDemandAcceptVo(String workOrderNo);

    public int updateStatus(String workOrderNo);

    public int updateStatusM(String[] workOrderNos);

    public int insertServiceInfo(DemandAcceptVo demandAcceptVo);

    public int updateServiceInfo(DemandAcceptVo demandAcceptVo);

    /**
     * 协办工作池
     * @param acceptDTO
     * @return
     */
    List<DemandAcceptVo> selectAssist(AcceptDTO acceptDTO);

    /**
     * 工单查询
     * @param acceptDTO
     * @return
     */
    List<WorkOrderVo> selectWorkOrder(AcceptDTO acceptDTO);
}
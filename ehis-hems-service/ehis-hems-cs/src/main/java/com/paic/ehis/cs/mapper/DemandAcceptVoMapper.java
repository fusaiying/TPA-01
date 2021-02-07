package com.paic.ehis.cs.mapper;


import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;

import java.util.List;

public interface DemandAcceptVoMapper {
    public List<DemandAcceptVo> selectDemandAcceptVoList(AcceptDTO acceptDTO);

    public List<DemandAcceptVo> selectDemandAcceptVoList2(AcceptDTO acceptDTO);

    public DemandAcceptVo selectDemandAcceptVoById(String workOrderNo);

    public int updateStatus(String workOrderNo);

    public int updateCancelStatus(String workOrderNo);

    public int updateStatusM(String[] workOrderNos);

    //插入工单表
    public int insertWorkOrderAccept(WorkOrderAccept WorkOrderAccept);
    //插入详细表
    public int insertAcceptDetailInfo(AcceptDetailInfo acceptDetailInfo);
    //插入人的表
    public int insertPersonInfo(PersonInfo personInfo);
    //插入轨迹表
    public int insertFlowLog(FlowLog flowLog);




}

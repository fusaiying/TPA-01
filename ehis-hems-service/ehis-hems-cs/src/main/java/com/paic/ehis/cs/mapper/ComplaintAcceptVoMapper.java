package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;

import java.util.List;

public interface ComplaintAcceptVoMapper {

    /** 投诉主页面查询 */
    public List<DemandAcceptVo> selectComplaintAcceptVoList(AcceptDTO acceptDTO);
    public List<DemandAcceptVo> selectComplaintAcceptVoListOne(AcceptDTO acceptDTO);

    public ComplaintAcceptVo selectComplaintAcceptVo(String workOrderNo);

    //插入工单表
    public int insertWorkOrderAccept(WorkOrderAccept WorkOrderAccept);
    //插入详细表
    public int insertAcceptDetailInfo(AcceptDetailInfo acceptDetailInfo);
    //插入人的表
    public int insertPersonInfo(PersonInfo personInfo);
    //插入轨迹表
    public int insertFlowLog(FlowLog flowLog);

}

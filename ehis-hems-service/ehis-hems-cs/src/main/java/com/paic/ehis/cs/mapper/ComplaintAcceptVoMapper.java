package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.Level3;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface ComplaintAcceptVoMapper {

    /** 投诉主页面查询 */
    public List<ComplaintAcceptVo> selectComplaintAcceptVoList(AcceptDTO acceptDTO);
    public List<ComplaintAcceptVo> selectComplaintAcceptVoListOne(AcceptDTO acceptDTO);

    public ComplaintAcceptVo selectComplaintAcceptVo(String workOrderNo);

    //插入工单表
    public int insertWorkOrderAccept(WorkOrderAccept WorkOrderAccept);
    //插入详细表
    public int insertAcceptDetailInfo(AcceptDetailInfo acceptDetailInfo);
    //插入人的表
    public int insertPersonInfo(PersonInfo personInfo);
    //插入轨迹表
    public int insertFlowLog(FlowLog flowLog);

    /**
     * 投诉取消提交将状态改为已取消
     * @param workOrderNo
     * @return
     */
    public int updateReservedCancelStatus(String workOrderNo);

    /**
     * 查询一级投诉分类
     * @return
     */
    public List<Level3> selectLevel1();
    /**
     * 查询2级投诉分类
     * @return
     */
    public List<Level3> selectLevel2(@Param("parentCode") String parentCode);

    public List<CodeDict> selectAllByLevelType(@Param("codeType") String codeType);
}

package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;

import java.util.List;

public interface ReservationAcceptVoMapper {

    public ReservationAcceptVo selectReservationAcceptVo(String workOrderNo);

    /*公共池*/
    public List<ReservationAcceptVo> selectReservationAcceptVoList(AcceptDTO acceptDTO);

    /*个人池*/
    public List<ReservationAcceptVo> selectReservationAcceptVoList2(AcceptDTO acceptDTO);

    /*获取*/
    public int updateStatusM(String[] workOrderNos);
    public int updateOrderCancelStatus(String workOrderNo);
}

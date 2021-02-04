package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IReservationAcceptVoService {

    /*公共池*/
    public List<ReservationAcceptVo> selectReservationAcceptVoList(AcceptDTO acceptDTO);

    /*个人池*/
    public List<ReservationAcceptVo> selectReservationAcceptVoList2(AcceptDTO acceptDTO);

    /*获取*/
    public int updateStatusM(String[] workOrderNos);

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    int insertReservationAcceptVo(ReservationAcceptVo reservationAcceptVo);
}

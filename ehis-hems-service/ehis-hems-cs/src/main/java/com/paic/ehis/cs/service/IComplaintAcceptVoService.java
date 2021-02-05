package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;

import java.util.List;

public interface IComplaintAcceptVoService {
    List<DemandAcceptVo> selectComplaintAcceptVoList(AcceptDTO acceptDTO);

    List<DemandAcceptVo> selectComplaintAcceptVoListOne(AcceptDTO acceptDTO);
    /**
     * 投诉新增页面
     * @param complaintAcceptVo
     * @return
     */
   public int insertComplaintInfo(ComplaintAcceptVo complaintAcceptVo);
}

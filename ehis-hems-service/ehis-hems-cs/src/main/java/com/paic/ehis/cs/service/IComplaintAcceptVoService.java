package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IComplaintAcceptVoService {
    List<DemandAcceptVo> selectComplaintAcceptVoList(AcceptDTO acceptDTO);

    List<DemandAcceptVo> selectComplaintAcceptVoListOne(AcceptDTO acceptDTO);

    public ComplaintAcceptVo selectComplaintAcceptVo(String workOrderNo);
    /**
     * 投诉新增页面
     * @param complaintAcceptVo
     * @return
     */
   public int insertComplaintInfo(ComplaintAcceptVo complaintAcceptVo);

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    int updateComplaintAcceptVo(ComplaintAcceptVo complaintAcceptVo);
}

package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ServiceInfoVo;

import java.util.List;

public interface IDemandAcceptVoService {
    public List<DemandAcceptVo> selectDemandAcceptList(DemandAcceptVo demandAcceptVo);

    public List<DemandAcceptVo> selectDemandAcceptList2(DemandAcceptVo demandAcceptVo);

    public int updateStatus(String workOrderNo);

    public int insertServiceInfo(DemandAcceptVo demandAcceptVo);
}
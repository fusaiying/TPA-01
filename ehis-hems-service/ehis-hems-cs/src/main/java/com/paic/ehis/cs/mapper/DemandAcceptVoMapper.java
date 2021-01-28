package com.paic.ehis.cs.mapper;


import com.paic.ehis.cs.domain.vo.DemandAcceptVo;

import java.util.List;

public interface DemandAcceptVoMapper {
    public List<DemandAcceptVo> selectDemandAcceptVoList(DemandAcceptVo demandAcceptVo);

    public List<DemandAcceptVo> selectDemandAcceptVoList2(DemandAcceptVo demandAcceptVo);

    public int updateStatus(String workOrderNo);


}

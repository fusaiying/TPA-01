package com.paic.ehis.system.service.impl;

import com.paic.ehis.system.domain.HmpServiceOrderAssign;
import com.paic.ehis.system.mapper.HmpServiceOrderAssignMapper;
import com.paic.ehis.system.service.IHmpServiceOrderAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HmpServiceOrderAssignServiceImpl implements IHmpServiceOrderAssignService{

    @Autowired
    private HmpServiceOrderAssignMapper hmpServiceOrderAssignMapper;

    @Override
    public HmpServiceOrderAssign selectHmpServiceOrderAssignByCode(String ordercode) {
        return hmpServiceOrderAssignMapper.selectHmpServiceOrderAssignByCode(ordercode);
    }

    @Override
    public int insertHmpServiceOrderAssign(HmpServiceOrderAssign hmpServiceOrderAssign) {
        return hmpServiceOrderAssignMapper.insertHmpServiceOrderAssign(hmpServiceOrderAssign);
    }

    @Override
    public int updateHmpServiceOrderAssign(HmpServiceOrderAssign hmpServiceOrderAssign) {
        return hmpServiceOrderAssignMapper.updateHmpServiceOrderAssign(hmpServiceOrderAssign);
    }
}

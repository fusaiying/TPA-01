package com.paic.ehis.system.service.impl;

import com.paic.ehis.system.domain.HmpServiceOrderResult;
import com.paic.ehis.system.mapper.HmpServiceOrderResultMapper;
import com.paic.ehis.system.service.IHmpServiceOrderResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HmpServiceOrderResultServiceImpl implements IHmpServiceOrderResultService {

    @Autowired
    private HmpServiceOrderResultMapper hmpServiceOrderResultMapper;

    @Override
    public int insertHmpServiceOrderResult(HmpServiceOrderResult hmpServiceOrderResult) {
        return hmpServiceOrderResultMapper.insertHmpServiceOrderResult(hmpServiceOrderResult);
    }

    @Override
    public HmpServiceOrderResult selectHmpServiceOrderResultByCode(String ordercode) {
        return hmpServiceOrderResultMapper.selectHmpServiceOrderResultByCode(ordercode);
    }

    @Override
    public int updateHmpServiceOrderResult(HmpServiceOrderResult hmpServiceOrderResult) {
        return hmpServiceOrderResultMapper.updateHmpServiceOrderResult(hmpServiceOrderResult);
    }
}

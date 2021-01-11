package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.mapper.HmpServConproMapper;
import com.paic.ehis.base.service.IHmpServConproService;
import com.paic.ehis.base.domain.HmpServConpro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HmpServConproServiceImpl implements IHmpServConproService {

    @Autowired
    private HmpServConproMapper hmpServConproMapper;

    @Override
    public int insertHmpServConpro(List<HmpServConpro> hmpServConpro) {
        return hmpServConproMapper.insertHmpServConpro(hmpServConpro);
    }

    @Override
    public List<HmpServConpro> selectHmpServConproByNo(String contactno) {
        return hmpServConproMapper.selectHmpServConproByNo(contactno);
    }

    @Override
    public int deleteHmpServConproByNo(String contactno) {
        return hmpServConproMapper.deleteHmpServConproByNo(contactno);
    }
}

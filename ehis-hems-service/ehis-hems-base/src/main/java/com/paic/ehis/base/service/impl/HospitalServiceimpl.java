package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.domain.BaseProviderDep;
import com.paic.ehis.base.domain.vo.HSDTO;
import com.paic.ehis.base.domain.vo.HSVO;
import com.paic.ehis.base.domain.vo.HospitalDTO;
import com.paic.ehis.base.domain.vo.HospitalVO;
import com.paic.ehis.base.mapper.BaseProviderDepMapper;
import com.paic.ehis.base.mapper.HospitalMapper;
import com.paic.ehis.base.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceimpl implements HospitalService {
    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    private BaseProviderDepMapper baseProviderDepMapper;

    @Override
    public List<HospitalVO> selectHospitalVOList(HospitalDTO hospitalDTO) {
        List<HospitalVO> list=hospitalMapper.selectHospitalVOList(hospitalDTO);
        for(HospitalVO hospitalVO:list){
            String providerCode=hospitalVO.getPartnerId();
            List<BaseProviderDep> baseProviderDeps=baseProviderDepMapper.selectBaseProviderDepByCode(providerCode);
            hospitalVO.setDeptList(baseProviderDeps);
        }
        return list ;
    }

    @Override
    public List<HSVO> selectHSVOList(HSDTO hSDTO) {
        return hospitalMapper.selectHSVOList(hSDTO);
    }

}

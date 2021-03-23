package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.domain.BaseHospitalForReservationVo;
import com.paic.ehis.base.domain.dto.BaseHospitalForReservationDTO;
import com.paic.ehis.base.mapper.BaseProviderInfoMapper;
import com.paic.ehis.base.service.IBaseToCsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseToCsServiceimpl implements IBaseToCsService {
    @Autowired
    private BaseProviderInfoMapper baseProviderInfoMapper;

    @Override
    public List<BaseHospitalForReservationVo> selectHospitalForReservation(BaseHospitalForReservationDTO baseHospitalForReservationDTO) {
        return baseProviderInfoMapper.selectHospitalForReservation(baseHospitalForReservationDTO);
    }

}

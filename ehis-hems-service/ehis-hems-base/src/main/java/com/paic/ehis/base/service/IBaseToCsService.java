package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseHospitalForReservationVo;
import com.paic.ehis.base.domain.dto.BaseHospitalForReservationDTO;

import java.util.List;

public interface IBaseToCsService {
    public List<BaseHospitalForReservationVo> selectHospitalForReservation(BaseHospitalForReservationDTO baseHospitalForReservationDTO);
}

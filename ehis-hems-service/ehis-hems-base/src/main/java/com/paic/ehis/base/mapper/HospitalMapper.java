package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.vo.HSDTO;
import com.paic.ehis.base.domain.vo.HSVO;
import com.paic.ehis.base.domain.vo.HospitalDTO;
import com.paic.ehis.base.domain.vo.HospitalVO;

import java.util.List;

public interface HospitalMapper {
    public List<HospitalVO> selectHospitalVOList(HospitalDTO hospitalDTO);
    public List<HSVO> selectHSVOList(HSDTO hSDTO);
}

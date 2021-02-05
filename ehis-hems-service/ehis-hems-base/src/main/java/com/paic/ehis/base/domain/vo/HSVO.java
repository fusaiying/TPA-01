package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.PreAuthHospitalCity;
import com.paic.ehis.base.domain.hospitalInfo;
import lombok.Data;

import java.util.List;

@Data
public class HSVO {

    private List<hospitalInfo> hospitalInfoList;

    private List<PreAuthHospitalCity> preAuthHospitalCityList;
}

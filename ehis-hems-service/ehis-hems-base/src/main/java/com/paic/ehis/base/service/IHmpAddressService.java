package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.HmpAddress;

import java.util.List;

public interface IHmpAddressService {
    /**
     * 根据名称查询编码
     * @param name
     * @return
     */
    List<HmpAddress> selectHmpAddressByName(String name);
}

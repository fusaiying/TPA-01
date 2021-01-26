package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.domain.HmpAddress;
import com.paic.ehis.base.mapper.HmpAddressMapper;
import com.paic.ehis.base.service.IHmpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HmpAddressImpl implements IHmpAddressService {

    @Autowired
    private HmpAddressMapper hmpAddressMapper;

    @Override
    public List<HmpAddress> selectHmpAddressByName(String name) {
        return hmpAddressMapper.selectHmpAddressByName(name);
    }
}

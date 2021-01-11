package com.paic.ehis.query.service.impl;

import com.paic.ehis.query.domain.CustomerInfo;
import com.paic.ehis.query.domain.vo.CustomerInfoQryWorkpoolPSchema;
import com.paic.ehis.query.mapper.CustomerInfoMapper;
import com.paic.ehis.query.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：impl
 *
 * @author 创建人 zsy
 * @version 1.0
 * @Date 创建时间 2020/9/24  15:29
 */
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Override
    public List<CustomerInfo> customerInfoQuery(CustomerInfoQryWorkpoolPSchema customerInfoQryWorkpoolPSchema) {
        return customerInfoMapper.getCustomerInfo(customerInfoQryWorkpoolPSchema);
    }
}

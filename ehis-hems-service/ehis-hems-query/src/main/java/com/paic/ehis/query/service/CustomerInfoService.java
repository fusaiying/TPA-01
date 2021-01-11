package com.paic.ehis.query.service;

import com.paic.ehis.query.domain.CustomerInfo;
import com.paic.ehis.query.domain.vo.CustomerInfoQryWorkpoolPSchema;

import java.util.List;

/**
 * 功能描述：service
 *
 * @author 创建人 zsy
 * @version 1.0
 * @Date 创建时间 2020/9/24  15:29
 */
public interface CustomerInfoService {
    List<CustomerInfo> customerInfoQuery(CustomerInfoQryWorkpoolPSchema customerInfoQryWorkpoolPSchema);
}

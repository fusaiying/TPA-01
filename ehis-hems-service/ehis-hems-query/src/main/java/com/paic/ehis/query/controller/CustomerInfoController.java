package com.paic.ehis.query.controller;

import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.query.domain.CustomerInfo;
import com.paic.ehis.query.domain.vo.CustomerInfoQryWorkpoolPSchema;
import com.paic.ehis.query.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述：客户信息查询
 *
 * @author 创建人 zsy
 * @version 1.0
 * @Date 创建时间 2020/9/24  14:09
 */
@RestController
@RequestMapping("/customerInfo")
public class CustomerInfoController  extends BaseController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @PreAuthorize("@ss.hasPermi('query:customerInfo:list')")
    @GetMapping("/list")
    public TableDataInfo customerInfoQuery(CustomerInfoQryWorkpoolPSchema customerInfoQryWorkpoolPSchema){
        startPage();
        List<CustomerInfo> list=customerInfoService.customerInfoQuery(customerInfoQryWorkpoolPSchema);
        return getDataTable(list);
    }

}

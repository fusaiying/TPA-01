package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.base.domain.BaseSupplierInfo;
import com.paic.ehis.base.service.IBaseSupplierInfoService;
import com.paic.ehis.base.service.SupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider1")
public class SupplierInfoController extends BaseController {
    @Autowired
    private SupplierInfoService supplierInfoService;
    @Autowired
    private IBaseSupplierInfoService baseSupplierInfoService;


    @PostMapping("/add")
    public AjaxResult add(@RequestBody BaseSupplierInfo baseSupplierInfo)
    {
        return AjaxResult.success(supplierInfoService.addBaseSupplierInfo(baseSupplierInfo));
    }
}

package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.cs.domain.BasicServiceAppilcation;
import com.paic.ehis.cs.service.IBasicServiceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baseServiceApplication")
public class BasicServiceApplicationController extends BaseController {
    @Autowired
    private IBasicServiceApplicationService basicServiceApplicationService;
    @PostMapping("/addBasicServiceApplication")
    public AjaxResult addBasicServiceApplication(BasicServiceAppilcation basicServiceAppilcation) {
        return toAjax(basicServiceApplicationService.insertBasicServiceApplication(basicServiceAppilcation));
    }
}

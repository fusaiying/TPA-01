package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.cs.domain.IncrementServiceApplication;
import com.paic.ehis.cs.service.IncrementServiceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incrementServiceApplication")
public class IncrementServiceApplicationController {
    @Autowired
    private IncrementServiceApplicationService incrementServiceApplicationService;

    @PostMapping("/addIncrementServiceApplication")
    public AjaxResult addIncrementServiceApplication(IncrementServiceApplication incrementServiceApplication) {
        return AjaxResult.success(incrementServiceApplicationService.addIncrementServiceApplication(incrementServiceApplication));
    }
}

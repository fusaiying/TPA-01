package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.service.IClaimCalDealService;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.domain.ClaimCaseCalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cal/deal")
public class ClaimCalDealController {

    @Autowired
    private IClaimCalDealService iClaimCalDealService;


    @GetMapping("/clear/{rptNo}")
    public AjaxResult clear(@PathVariable("rptNo") String rptNo)
    {
        iClaimCalDealService.clear(rptNo);

        return AjaxResult.success();
    }

    @GetMapping("/getBillInfo/{rptNo}")
    public AjaxResult getBillInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(iClaimCalDealService.selectClaimCaseBillInfo(rptNo));
    }

    @GetMapping("/getCasePolicyInfo/{rptNo}")
    public AjaxResult getCasePolicyInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(iClaimCalDealService.selectClaimCasePolicy(rptNo));
    }

    @PostMapping("/saveCaseCalInfo")
    public AjaxResult saveCaseCalInfo(@RequestBody ClaimCaseCalInfo claimCaseCalInfo){
        iClaimCalDealService.save(claimCaseCalInfo);

        return AjaxResult.success();
    }
}

package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.service.IClaimCalDealService;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.domain.ClaimCaseCalInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import com.paic.ehis.system.api.domain.dto.ClaimCaseCalItemDTO;
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

    @GetMapping("/getCaseInfo/{rptNo}")
    public AjaxResult getCaseInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(iClaimCalDealService.selectClaimCaseInfo(rptNo));
    }

    @GetMapping("/getCasePolicyInfo/{rptNo}")
    public AjaxResult getCasePolicyInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(iClaimCalDealService.selectClaimCasePolicy(rptNo));
    }

    @PostMapping("/saveCaseCalInfo")
    public AjaxResult saveCaseCalInfo(@RequestBody ClaimCaseCalculateInfo claimCaseCalculateInfo){
        iClaimCalDealService.save(claimCaseCalculateInfo);

        return AjaxResult.success();
    }

    @PostMapping("/getUsedValue")
    public AjaxResult getCaInfo(@RequestBody ClaimCaseCalItemDTO claimCaseCalItemDTO){
        iClaimCalDealService.getCaInfo(claimCaseCalItemDTO);

        return AjaxResult.success();
    }
}

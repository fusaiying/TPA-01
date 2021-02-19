package com.paic.ehis.claimcal.controller;

import com.paic.ehis.claimcal.service.IClaimCalService;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claim/calculate")
public class ClaimCalController {

    @Autowired
    private IClaimCalService claimCalService;

    @GetMapping("/{rptNo}")
    public AjaxResult Calculate(@PathVariable("rptNo") String rptNo){

        claimCalService.Clear(rptNo);

        claimCalService.Calculate(rptNo);

        return AjaxResult.success("计算成功！");
    }
}

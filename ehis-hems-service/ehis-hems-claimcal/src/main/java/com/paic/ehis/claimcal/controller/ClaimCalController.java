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

        try{
            claimCalService.Calculate(rptNo);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("理算失败，原因："+e.getMessage());
        }

        return AjaxResult.success("计算成功！");
    }
}

package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.dto.ClaimMoreDTO;
import com.paic.ehis.claimflow.service.ClaimMoreService;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/claimmore")
public class ClaimMoreController {

    @Autowired
    private ClaimMoreService claimMoreService;

    @GetMapping("/select")
    public AjaxResult selectClaimMore(ClaimMoreDTO claimMoreDTO){
        return AjaxResult.success(claimMoreService.selectClaimMore(claimMoreDTO));
    }
}

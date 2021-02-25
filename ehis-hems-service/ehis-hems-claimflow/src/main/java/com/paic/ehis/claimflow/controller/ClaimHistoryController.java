package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.dto.ClaimHistoryDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimHistory;
import com.paic.ehis.claimflow.service.ClaimHistoryService;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/claimhistory")
public class ClaimHistoryController {
    @Autowired
    private ClaimHistoryService claimHistoryService;

    @GetMapping("/select")
    public AjaxResult selectClaimHistory(ClaimHistoryDTO claimHistoryDTO){
        return AjaxResult.success(claimHistoryService.selectClaimHistory(claimHistoryDTO));
    }
}

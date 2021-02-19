package com.paic.ehis.system.api;

import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.domain.ClaimCaseCalInfo;
import com.paic.ehis.system.api.factory.RemoteClaimCalFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "remoteClaimCalService", value = ServiceNameConstants.CLAIM_FLOW_SERVICE, fallbackFactory = RemoteClaimCalFallbackFactory.class)
public interface RemoteClaimCalService {

    @GetMapping("/cal/deal/clear/{rptNo}")
    public AjaxResult Clear(@PathVariable("rptNo") String rptNo);

    @GetMapping("/cal/deal/getBillInfo/{rptNo}")
    public AjaxResult getBillInfo(@PathVariable("rptNo") String rptNo);

    @GetMapping("/cal/deal/getCasePolicyInfo/{rptNo}")
    public AjaxResult getCasePolicyInfo(@PathVariable("rptNo") String rptNo);

    @PostMapping("/cal/deal/saveCaseCalInfo")
    public AjaxResult saveCaseCalInfo(@RequestBody ClaimCaseCalInfo claimCaseCalInfo);
}

package com.paic.ehis.system.api;

import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(contextId = "CustomServiceService", value = ServiceNameConstants.PROVIDER_SERVICE)
public interface CustomServiceService {
    @GetMapping("/internal/batchAcceptVo/invalidDate")
    public AjaxResult batchAcceptVo(@PathVariable("invalidDate") String invalidDate);

    @GetMapping("/internal/batchAcceptVo/invalidDateMonth")
    public AjaxResult batchAcceptVomonth(@PathVariable("invalidDate") String invalidDate);

}

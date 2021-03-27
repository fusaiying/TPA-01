package com.paic.ehis.system.api;

import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(contextId = "CustomServiceService", value = ServiceNameConstants.CS_SERVICE)
public interface CustomServiceService {
    @GetMapping("/spotCheck/internal/batchAcceptVo/invalidDate/{invalidDate}")
    public AjaxResult batchAcceptVo(@PathVariable("invalidDate") String invalidDate);

    @GetMapping("/spotCheck/internal/batchAcceptVo/invalidDateMonth/{invalidDate}")
    public AjaxResult batchAcceptVomonth(@PathVariable("invalidDate") String invalidDate);

    @GetMapping("/internal/invalidAcceptDetailInfo/{invalidDate}")
    public AjaxResult invalidAcceptDetailInfo(@PathVariable("invalidDate") String invalidDate);

    }

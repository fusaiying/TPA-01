package com.paic.ehis.system.api;

import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(contextId = "CustomServiceService", value = ServiceNameConstants.CS_SERVICE)
public interface CustomServiceService {

    //信息需求一周批处理
    @GetMapping("/spotCheck/internal/batchAcceptVo/invalidDate/{invalidDate}")
    public AjaxResult batchAcceptVo(@PathVariable("invalidDate") String invalidDate);

    //信息需求一个月批处理
    @GetMapping("/spotCheck/internal/batchAcceptVo/invalidDateMonth/{invalidDate}")
    public AjaxResult batchAcceptVomonth(@PathVariable("invalidDate") String invalidDate);

    //预约12点批处理
    @GetMapping("/spotCheck/internal/invalidAcceptDetailInfo/{invalidDate}")
    public AjaxResult invalidAcceptDetailInfo(@PathVariable("invalidDate") String invalidDate);

    //投诉每周一凌晨四点批处理
    @GetMapping("/spotCheck/internal/batchAcceptVo/selectInvalidQiaMondayFour/{invalidDate}")
    public AjaxResult selectInvalidQiaMondayFour(@PathVariable("invalidDate") String invalidDate);

    }

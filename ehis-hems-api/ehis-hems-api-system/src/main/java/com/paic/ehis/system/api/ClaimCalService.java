package com.paic.ehis.system.api;


import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.domain.ClaimCasePolicy;
import com.paic.ehis.system.api.domain.ClaimProductFeeitem;
import com.paic.ehis.system.api.factory.ClaimCalServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 理算及福利规则接口
 */
@FeignClient(contextId = "ClaimCalService", value = ServiceNameConstants.CAL_SERVICE, fallbackFactory = ClaimCalServiceFallbackFactory.class)
public interface ClaimCalService {

    /**
     * 根据保单号查询费用项编码、费用项名称
     *
     * @param policys
     * @return
     */
    @PostMapping("/productFeeitem/policys")
    public List<ClaimProductFeeitem> selectFeeitemByPolicys(@RequestBody List<ClaimCasePolicy> policys);

    @GetMapping("/claim/calculate/{rptNo}")
    public AjaxResult Calculate(@PathVariable("rptNo") String rptNo);
}

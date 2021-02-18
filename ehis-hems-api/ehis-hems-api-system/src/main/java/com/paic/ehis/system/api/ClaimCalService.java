package com.paic.ehis.system.api;


import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.system.api.domain.ClaimProductFeeitem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 理算及福利规则接口
 */
@FeignClient(contextId = "ClaimCalService", value = ServiceNameConstants.CAL_SERVICE)
public interface ClaimCalService {

    /**
     * 根据保单号查询费用项编码、费用项名称
     *
     * @param policys
     * @return
     */
    @PostMapping("/productFeeitem/policys")
    public List<ClaimProductFeeitem> selectFeeitemByPolicys(@RequestBody List<String> policys);

}

package com.paic.ehis.system.api;


import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.domain.dto.ClaimFlowDTO;
import com.paic.ehis.system.api.factory.ClaimFlowServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * CLAIM_FLOW_SERVICE 提供跨服务调用
 * By Louis
 */
@FeignClient(contextId = "ClaimFlowService", value = ServiceNameConstants.CLAIM_FLOW_SERVICE, fallbackFactory = ClaimFlowServiceFallbackFactory.class)
public interface ClaimFlowService {

    /**
     * 通过条件获取保单列表
     *
     * @param claimFlowDTO
     * @return
     */
    @PostMapping(value = "/external_claimflow/selectPolicyList")
    public TableDataInfo selectPolicyList(@RequestBody ClaimFlowDTO claimFlowDTO);

    @PostMapping(value = "/external_claimflow/getPolicyInfo")
    public AjaxResult getPolicyInfoBy(@RequestBody ClaimFlowDTO claimFlowDTO);

}

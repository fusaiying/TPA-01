package com.paic.ehis.system.api.factory;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.ClaimFlowService;
import com.paic.ehis.system.api.domain.dto.ClaimFlowDTO;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * CLAIM_FLOW_SERVICE
 */
@Component
public class ClaimFlowServiceFallbackFactory implements FallbackFactory<ClaimFlowService> {
    private static final Logger log = LoggerFactory.getLogger(ClaimFlowServiceFallbackFactory.class);

    @Override
    public ClaimFlowService create(Throwable throwable) {
        log.error("调用CLAIMFLOW服务异常：{}",throwable.getMessage());
        return new ClaimFlowService() {
            @Override
            public TableDataInfo selectPolicyList(ClaimFlowDTO claimFlowDTO) {
                return null;
            }

            @Override
            public AjaxResult getPolicyInfoBy(ClaimFlowDTO claimFlowDTO) {
                return null;
            }

            @Override
            public AjaxResult queryClaimList(ClaimFlowDTO claimFlowDTO) {
                return null;
            }

            @Override
            public AjaxResult queryPolicyInfoToGCC(ClaimFlowDTO claimFlowDTO) {
                return null;
            }

            @Override
            public AjaxResult queryClaimListToGCC(ClaimFlowDTO claimFlowDTO) {
                return null;
            }

            @Override
            public AjaxResult queryClaimAcceptList(ClaimFlowDTO claimFlowDTO) {
                return null;
            }

            @Override
            public AjaxResult queryClaimBillDetail(ClaimFlowDTO claimFlowDTO) {
                return null;
            }
        };
    }
}

package com.paic.ehis.system.api.factory;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.ClaimCalService;
import com.paic.ehis.system.api.domain.ClaimCasePolicy;
import com.paic.ehis.system.api.domain.ClaimProductFeeitem;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 理算及福利规则降级处理
 */
@Component
public class ClaimCalServiceFallbackFactory implements FallbackFactory<ClaimCalService> {
    private static final Logger log = LoggerFactory.getLogger(com.paic.ehis.system.api.factory.ClaimCalServiceFallbackFactory.class);

    @Override
    public ClaimCalService create(Throwable throwable) {
        log.error("理算及福利规则调用失败:{}", throwable.getMessage());
        return new ClaimCalService() {
            @Override
            public List<ClaimProductFeeitem> selectFeeitemByPolicys(List<ClaimCasePolicy> policys) {
                return null;
            }

            @Override
            public AjaxResult Calculate(String rptNo) {
                return AjaxResult.error("案件：" + rptNo + "，理算计算失败，原因：" + throwable.getMessage());
            }
        };
    }
}

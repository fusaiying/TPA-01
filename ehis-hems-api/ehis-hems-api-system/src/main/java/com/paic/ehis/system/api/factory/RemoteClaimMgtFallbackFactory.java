package com.paic.ehis.system.api.factory;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.RemoteClaimCalService;
import com.paic.ehis.system.api.RemoteClaimMgtService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteClaimMgtFallbackFactory implements FallbackFactory<RemoteClaimMgtService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteClaimCalFallbackFactory.class);


    @Override
    public RemoteClaimMgtService create(Throwable throwable) {
        log.error("理赔管理处理服务调用失败:{}", throwable.getMessage());
        return new RemoteClaimMgtService() {
            @Override
            public AjaxResult getClaimCaseOperator(String operation, String roleCode, String organCode) {
                return AjaxResult.error("环节：" + operation + "，获取操作人失败，原因：" + throwable.getMessage());
            }
        };
    }
}

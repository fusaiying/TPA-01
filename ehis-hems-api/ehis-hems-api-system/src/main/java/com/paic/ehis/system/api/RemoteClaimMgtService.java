package com.paic.ehis.system.api;

import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.factory.RemoteClaimMgtFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(contextId = "RemoteClaimMgtService", value = ServiceNameConstants.CLAIM_MGT_SERVICE, fallbackFactory = RemoteClaimMgtFallbackFactory.class)
public interface RemoteClaimMgtService {

    /**
     * 获取理赔案件操作人
     * @param operation 流程节点
     * @param roleCode 角色编码
     * @param organCode 机构编码
     * @return
     */
    @GetMapping("/dist/getClaimCaseOperator")
    public AjaxResult getClaimCaseOperator(String operation, String roleCode, String organCode);
}

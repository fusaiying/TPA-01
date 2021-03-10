package com.paic.ehis.system.api;

import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.domain.BaseContractService;
import com.paic.ehis.system.api.domain.BaseProviderInfo;
import com.paic.ehis.system.api.domain.BaseProviderSettle;
import com.paic.ehis.system.api.domain.dto.RoleLoginInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户服务
 * 
 * @author admin
 */
@FeignClient(contextId = "LognToBService", value = ServiceNameConstants.ORDER_SERVICE)
public interface LognToBService
{
    /**
     * 查询供应商合约服务项目信息
     *
     * @param roleLoginInfo 合约服务项目
     * @return 结果
     */
    @PostMapping(value = "/bussinessInfo/getUserInfo")
    public AjaxResult getUserInfo(@RequestBody RoleLoginInfo roleLoginInfo);

    @PostMapping(value = "/info/getAutoMode")
    public void getAutoMode();


}

package com.paic.ehis.system.api;

import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.domain.BaseContractService;
import com.paic.ehis.system.api.domain.BaseProviderInfo;
import com.paic.ehis.system.api.domain.BaseProviderSettle;
import com.paic.ehis.system.api.factory.GetProviderInfoFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户服务
 * 
 * @author admin
 */
@FeignClient(contextId = "GetProviderInfoService", value = ServiceNameConstants.PROVIDER_SERVICE)
public interface GetProviderInfoService
{
    /**
     * 查询供应商合约服务项目信息
     *
     * @param baseContractService 合约服务项目
     * @return 结果
     */
    @PostMapping(value = "/service/selectBaseContractServiceInfo")
    public List<BaseContractService> selectBaseContractServiceInfo(@RequestBody BaseContractService baseContractService);

    /**
     * 通过用户名查询用户信息
     *
     * @param baseProviderInfo 用户名
     * @return 结果
     */
    @PostMapping(value = "/org/selectHospitalInfo")
    public List<BaseProviderInfo> selectOrgInfo(@RequestBody BaseProviderInfo baseProviderInfo);

    /**
     * 获取医院的结算信息
     *
     * @param baseProviderSettle
     * @return
     */
    @PostMapping(value = "/org/selectsettleInfoNew")
    public List<BaseProviderSettle> selectsettleInfoNew(@RequestBody BaseProviderSettle baseProviderSettle);
}

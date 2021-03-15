package com.paic.ehis.system.api;
import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.domain.*;
import com.paic.ehis.system.api.domain.dto.BaseManualInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public R<List<BaseProviderInfo>> selectOrgInfo(@RequestBody BaseProviderInfo baseProviderInfo);

    /**
     * 获取医院的结算信息
     *
     * @param baseProviderSettle
     * @return
     */
    @PostMapping(value = "/org/selectsettleInfoNew")
    public List<BaseProviderSettle> selectsettleInfoNew(@RequestBody BaseProviderSettle baseProviderSettle);

    /**
     * 获取服务手册信息
     *
     * @param baseManualInfo
     * @return
     */
    @PostMapping(value = "/manual/getlist")
    public TableDataInfo getlist(@RequestBody BaseManualInfo baseManualInfo);


    /**
     * 获取产品服务信息
     *
     * @return
     */
    @PostMapping(value = "/org/getServiceInfo")
    public List<BaseServiceInfo> getServiceInfo();


    @PostMapping(value = "/org/getSupplierInfo")
    public AjaxResult getSupplierInfo();

    //获取医院信息
    @PostMapping(value = "/org/getHospitalInfo")
    public List<HospitalInfoVo> getHospitalInfo();

    ////获取一级科室
    @PostMapping(value = "/org/getFirstDeptInfo")
    public List<FirstDeptInfoVo> getFirstDeptInfo();

    //获取二级科室
    @PostMapping(value = "/org/getSecondDeptInfo")
    public List<SecondDeptInfoVo> getSecondDeptInfo();

    //获取医院信息
    @PostMapping(value = "/org/getHospitalInfo1")
    public List<HospitalInfoVo> getHospitalInfo1(@RequestBody AddressInfo addressInfo);

    //合约到期后，每天晚上12:00，通过批处理的方式将合约的状态修改为失效
    @PostMapping("/supplier/batchTimeBaseSupplierContract")
    public AjaxResult batchTimeBaseSupplierContract(@RequestBody BaseSupplierContract baseSupplierContract);

    @PostMapping("/org/selectSupplier")
    public AjaxResult selectSupplier(@RequestBody ProductInfo productInfo);
}

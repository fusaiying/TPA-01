package com.paic.ehis.base.service.impl;

import java.util.Arrays;
import java.util.List;

import com.paic.ehis.base.domain.BaseProviderService;
import com.paic.ehis.base.mapper.BaseProviderServiceMapper;
import com.paic.ehis.base.service.IBaseProviderServiceService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * base_provider_service(服务商服务信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
public class BaseProviderServiceServiceImpl implements IBaseProviderServiceService
{
    @Autowired
    private BaseProviderServiceMapper baseProviderServiceMapper;

    /**
     * 查询base_provider_service(服务商服务信息)
     * 
     * @param providercode base_provider_service(服务商服务信息)ID
     * @return base_provider_service(服务商服务信息)
     */
    @Override
    public BaseProviderService selectBaseProviderServiceById(String providercode)
    {
        BaseProviderService baseProviderService =baseProviderServiceMapper.selectBaseProviderServiceById(providercode);
        if(StringUtils.isNotBlank(baseProviderService.getSpeciallanguageStr())){
            baseProviderService.setSpeciallanguage(Arrays.asList((baseProviderService.getSpeciallanguageStr().split(","))));
        }
        if(StringUtils.isNotBlank(baseProviderService.getNetworkHospitalTypeStr())){
            baseProviderService.setNetworkHospitalType(Arrays.asList((baseProviderService.getNetworkHospitalTypeStr().split(","))));
        }
        if(StringUtils.isNotBlank(baseProviderService.getServicelocatorStr())){
            baseProviderService.setServicelocator(Arrays.asList((baseProviderService.getServicelocatorStr().split(","))));
        }
        return baseProviderService;
    }

    /**
     * 查询base_provider_service(服务商服务信息)列表
     * 
     * @param baseProviderService base_provider_service(服务商服务信息)
     * @return base_provider_service(服务商服务信息)
     */
    @Override
    public List<BaseProviderService> selectBaseProviderServiceList(BaseProviderService baseProviderService)
    {
        return baseProviderServiceMapper.selectBaseProviderServiceList(baseProviderService);
    }

    /**
     * 新增base_provider_service(服务商服务信息)
     * 
     * @param baseProviderService base_provider_service(服务商服务信息)
     * @return 结果
     */
    @Override
    public int insertBaseProviderService(BaseProviderService baseProviderService)
    {
        baseProviderServiceMapper.deleteBaseProviderServiceById(baseProviderService.getProviderCode());
        baseProviderService.setCreateTime(DateUtils.getNowDate());
        List<String>  arr= baseProviderService.getNetworkHospitalType();
        List<String>  arr1= baseProviderService.getSpeciallanguage();
        List<String>  arr2= baseProviderService.getServicelocator();
        baseProviderService.setNetworkHospitalTypeStr(StringUtils.join(arr, ","));
        baseProviderService.setSpeciallanguageStr(StringUtils.join(arr1, ","));  // 数组转字符串(逗号分隔)(推荐)
        baseProviderService.setServicelocatorStr(StringUtils.join(arr2, ","));  // 数组转字符串(逗号分隔)(推荐)
        return baseProviderServiceMapper.insertBaseProviderService(baseProviderService);
    }

    /**
     * 修改base_provider_service(服务商服务信息)
     * 
     * @param baseProviderService base_provider_service(服务商服务信息)
     * @return 结果
     */
    @Override
    public int updateBaseProviderService(BaseProviderService baseProviderService)
    {
        baseProviderService.setUpdateTime(DateUtils.getNowDate());
        return baseProviderServiceMapper.updateBaseProviderService(baseProviderService);
    }

    /**
     * 批量删除base_provider_service(服务商服务信息)
     * 
     * @param providercodes 需要删除的base_provider_service(服务商服务信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderServiceByIds(String[] providercodes)
    {
        return baseProviderServiceMapper.deleteBaseProviderServiceByIds(providercodes);
    }

    /**
     * 删除base_provider_service(服务商服务信息)信息
     * 
     * @param providercode base_provider_service(服务商服务信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderServiceById(String providercode)
    {
        return baseProviderServiceMapper.deleteBaseProviderServiceById(providercode);
    }
}

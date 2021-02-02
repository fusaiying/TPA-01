package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.base.domain.BaseProviderService;
import com.paic.ehis.base.mapper.BaseProviderServiceMapper;
import com.paic.ehis.base.service.IBaseProviderServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * base_provider_service(服务商服务信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
@Transactional
public class BaseProviderServiceServiceImpl implements IBaseProviderServiceService
{
    @Autowired
    private BaseProviderServiceMapper baseProviderServiceMapper;


    /**
     * 查询base_provider_service(服务商服务信息)
     * 
     * @param baseProviderServiceinfo base_provider_service(服务商服务信息)ID
     * @return base_provider_service(服务商服务信息)
     */
    @Override
    public BaseProviderService selectBaseProviderServiceById(BaseProviderService baseProviderServiceinfo)
    {
        BaseProviderService baseProviderService = new BaseProviderService();
        if("01".equals(baseProviderServiceinfo.getOrgFlag())){
            baseProviderService=baseProviderServiceMapper.selectBaseProviderServiceById(baseProviderServiceinfo.getProviderCode());
        }else if("02".equals(baseProviderServiceinfo.getOrgFlag())){
            baseProviderService=baseProviderServiceMapper.selectBaseProviderServiceByIdNew(baseProviderServiceinfo.getProviderCode());
        }
        if(baseProviderService!=null){
            if(StringUtils.isNotBlank(baseProviderService.getSpeciallanguageStr())){
                baseProviderService.setSpeciallanguage(Arrays.asList((baseProviderService.getSpeciallanguageStr().split(","))));
            }else{
                baseProviderService.setSpeciallanguage(new ArrayList());
            }
            if(StringUtils.isNotBlank(baseProviderService.getNetworkHospitalTypeStr())){
                baseProviderService.setNetworkHospitalType(Arrays.asList((baseProviderService.getNetworkHospitalTypeStr().split(","))));
            }else{
                baseProviderService.setNetworkHospitalType(new ArrayList());
            }
            if(StringUtils.isNotBlank(baseProviderService.getServicelocatorStr())){
                baseProviderService.setServicelocator(Arrays.asList((baseProviderService.getServicelocatorStr().split(","))));
            }else{
                baseProviderService.setServicelocator(new ArrayList());
            }
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
        int i = 0;
        if("01".equals(baseProviderService.getOrgFlag())){
            BaseProviderService baseProviderServiceInfo = baseProviderServiceMapper.selectBaseProviderServiceById(baseProviderService.getProviderCode());
            if(baseProviderServiceInfo != null){
                baseProviderServiceMapper.updateBaseProviderServiceByCode(baseProviderService.getProviderCode());
            }
        }else if("02".equals(baseProviderService.getOrgFlag())){
            baseProviderServiceMapper.updateBaseProviderServiceByCodeNew(baseProviderService.getProviderCode());
        }
        baseProviderService.setCreateTime(DateUtils.getNowDate());
        List<String>  arr= baseProviderService.getNetworkHospitalType();
        List<String>  arr1= baseProviderService.getSpeciallanguage();
        List<String>  arr2= baseProviderService.getServicelocator();
        baseProviderService.setNetworkHospitalTypeStr(StringUtils.join(arr, ","));
        baseProviderService.setSpeciallanguageStr(StringUtils.join(arr1, ","));  // 数组转字符串(逗号分隔)(推荐)
        baseProviderService.setServicelocatorStr(StringUtils.join(arr2, ","));  // 数组转字符串(逗号分隔)(推荐)
        baseProviderService.setCreateTime(DateUtils.getNowDate());
        baseProviderService.setUpdateTime(DateUtils.getNowDate());
        baseProviderService.setCreateBy(SecurityUtils.getUsername());
        baseProviderService.setUpdateBy(SecurityUtils.getUsername());
        baseProviderService.setUpdateFlag("0");
        baseProviderService.setStatus("Y");
        baseProviderService.setSerialNo(PubFun.createMySqlMaxNoUseCache("serviceSer",12,12));
        if("01".equals(baseProviderService.getOrgFlag())){
            i = baseProviderServiceMapper.insertBaseProviderService(baseProviderService);
        }else if("02".equals(baseProviderService.getOrgFlag())){
            i = baseProviderServiceMapper.insertBaseProviderServiceNew(baseProviderService);        }
        return  i ;
    }

    /**
     * 新增base_provider_service(服务商服务信息)(正式表)
     *
     * @param providerCode base_provider_service(服务商服务信息)
     * @return 结果
     */
    @Override
    public int insertBaseProviderServiceNew(String providerCode)
    {
        //备份表中获取最新的数据
        BaseProviderService baseProviderServiceInfo = baseProviderServiceMapper.selectBaseProviderServiceById(providerCode);
        //从正式表中获取数据
        BaseProviderService baseProviderServiceInfoNew = baseProviderServiceMapper.selectBaseProviderServiceByIdNew(providerCode);
        if(baseProviderServiceInfoNew != null) {
            //修改正式表中数据状态
            baseProviderServiceMapper.updateBaseProviderServiceByCodeNew(providerCode);
        }
        //备份表的数据挪到正式表
        baseProviderServiceInfo.setCreateTime(DateUtils.getNowDate());
        return baseProviderServiceMapper.insertBaseProviderServiceNew(baseProviderServiceInfo);
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

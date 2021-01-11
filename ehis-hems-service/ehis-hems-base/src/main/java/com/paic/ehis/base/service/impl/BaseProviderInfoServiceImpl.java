package com.paic.ehis.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paic.ehis.base.domain.vo.*;
import com.paic.ehis.base.mapper.BaseProviderInfoMapper;
import com.paic.ehis.base.service.IBaseProviderInfoService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.base.domain.BaseProviderInfo;

/**
 * base_provider_info(服务商基本信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
public class BaseProviderInfoServiceImpl implements IBaseProviderInfoService
{
    @Autowired
    private BaseProviderInfoMapper baseProviderInfoMapper;

    /**
     * 查询base_provider_info(服务商基本信息)
     * 
     * @param providercode base_provider_info(服务商基本信息)ID
     * @return base_provider_info(服务商基本信息)
     */
    @Override
    public BaseProviderInfo selectBaseProviderInfoById(String providercode)
    {
        return baseProviderInfoMapper.selectBaseProviderInfoById(providercode);
    }

    /**
     * 查询base_provider_info(服务商基本信息)
     *
     * @param baseProviderInfo base_provider_info(服务商基本信息)ID
     * @return base_provider_info(服务商基本信息)
     */
    @Override
    public BaseProviderInfo selectBaseProviderInfo(BaseProviderInfo baseProviderInfo)
    {
        return baseProviderInfoMapper.selectBaseProviderInfo(baseProviderInfo);
    }


    /**
     * 查询base_provider_info(服务商基本信息)列表
     * 
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return base_provider_info(服务商基本信息)
     */
    @Override
    public List<BaseProviderInfo> selectBaseProviderInfoList(BaseProviderInfo baseProviderInfo)
    {
        return baseProviderInfoMapper.selectBaseProviderInfoList(baseProviderInfo);
    }

    @Override
    public ArrayList<AddressVO> getAddress() {
        String placetype = "01";
        List<Sn_base_addressDO> sn_base_addressList1 = baseProviderInfoMapper.selectAllByPlacetype(placetype);
        placetype = "02";
        List<Sn_base_addressDO> sn_base_addressList2 = baseProviderInfoMapper.selectAllByPlacetype(placetype);
        placetype = "03";
        List<Sn_base_addressDO> sn_base_addressList3 = baseProviderInfoMapper.selectAllByPlacetype(placetype);
        ArrayList<AddressVO> addressList = new ArrayList<AddressVO>();
        for (int i = 0; i < sn_base_addressList1.size(); i++) {
            AddressVO addressVO1 = new AddressVO();
            addressVO1.setValue(sn_base_addressList1.get(i).getPlacecode());
            addressVO1.setLabel(sn_base_addressList1.get(i).getPlacename());
            ArrayList<AddressVO> children1 = new ArrayList<AddressVO>();
            for (int j = 0; j < sn_base_addressList2.size(); j++) {
                if ((sn_base_addressList2.get(j).getUpplacename()).equals((sn_base_addressList1.get(i).getPlacecode()))) {
                    AddressVO addressVO2 = new AddressVO();
                    addressVO2.setValue(sn_base_addressList2.get(j).getPlacecode());
                    addressVO2.setLabel(sn_base_addressList2.get(j).getPlacename());
                    ArrayList<AddressVO> children2 = new ArrayList<AddressVO>();
                    for (int p = 0; p < sn_base_addressList3.size(); p++) {
                        if (sn_base_addressList3.get(p).getUpplacename().equals(sn_base_addressList2.get(j).getPlacecode())) {
                            AddressVO addressVO3 = new AddressVO();
                            addressVO3.setValue(sn_base_addressList3.get(p).getPlacecode());
                            addressVO3.setLabel(sn_base_addressList3.get(p).getPlacename());
                            children2.add(addressVO3);
                        }
                    }
                    addressVO2.setChildren(children2);
                    children1.add(addressVO2);
                }
            }
            addressVO1.setChildren(children1);
            addressList.add(addressVO1);
        }
        return addressList;
    }

    @Override
    public List<AddressInfo> getprovince()
    {
        return baseProviderInfoMapper.selectprovince();
    }


    @Override
    public List<AddressInfo> getplace(String placecode)
    {
        return baseProviderInfoMapper.selectcity(placecode);
    }


    /**
     * 获取省市区信息
     *
     * @return base_provider_info(服务商基本信息)
     */
    @Override
    public List<Adress> selectAdressList()
    {
        List address =new ArrayList<Adress>();
        List cityinfos =new ArrayList<City>();
        List<AddressInfo> Provinces =baseProviderInfoMapper.selectprovince();
        for(AddressInfo Province :Provinces){
            Adress adress = new Adress();
            adress.setProvincecode(Province.getPlacecode());
            adress.setProvincename(Province.getPlacename());
            List<AddressInfo> citys =baseProviderInfoMapper.selectcity(Province.getPlacecode());
            for(AddressInfo city :citys){
                City cityInfo = new City();
                cityInfo.setCitycode(city.getPlacecode());
                cityInfo.setCityname(city.getPlacename());
                List<AddressInfo> districts =baseProviderInfoMapper.selectcity(city.getPlacecode());
                cityInfo.setDistrict(districts);
                cityinfos.add(cityInfo);
            }
            adress.setCities(cityinfos);
            address.add(adress);
        }
        return address;
    }

    /**
     * 新增base_provider_info(服务商基本信息)
     * 
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return 结果
     */
    @Override
    public BaseProviderInfo insertBaseProviderInfo(BaseProviderInfo baseProviderInfo)
    {
        BaseProviderInfo baseProvider =new BaseProviderInfo();
        baseProviderInfo.setCreateTime(DateUtils.getNowDate());
        baseProviderInfo.setProviderCode("M"+ PubFun.createMySqlMaxNoUseCache("providercode",10,9));
        baseProviderInfo.setStatus("1");
        baseProviderInfoMapper.insertBaseProviderInfo(baseProviderInfo);
        baseProvider.setProviderCode(baseProviderInfo.getProviderCode());
        return baseProvider;
    }

    /**
     * 修改base_provider_info(服务商基本信息)
     * 
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return 结果
     */
    @Override
    public int updateBaseProviderInfo(BaseProviderInfo baseProviderInfo)
    {
        baseProviderInfo.setUpdateTime(DateUtils.getNowDate());
        return baseProviderInfoMapper.updateBaseProviderInfo(baseProviderInfo);
    }

    /**
     * 批量删除base_provider_info(服务商基本信息)
     * 
     * @param providercodes 需要删除的base_provider_info(服务商基本信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderInfoByIds(String[] providercodes)
    {
        return baseProviderInfoMapper.deleteBaseProviderInfoByIds(providercodes);
    }

    /**
     * 删除base_provider_info(服务商基本信息)信息
     * 
     * @param providercode base_provider_info(服务商基本信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderInfoById(String providercode)
    {
        return baseProviderInfoMapper.deleteBaseProviderInfoById(providercode);
    }
}

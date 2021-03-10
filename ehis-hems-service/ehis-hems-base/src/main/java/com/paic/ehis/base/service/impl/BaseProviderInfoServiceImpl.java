package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.domain.vo.AddressInfo;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseCheckInfo;
import com.paic.ehis.base.domain.BaseProviderInfo;
import com.paic.ehis.base.domain.vo.*;
import com.paic.ehis.base.mapper.BaseProviderInfoMapper;
import com.paic.ehis.base.service.*;
import com.paic.ehis.base.service.IBaseProviderInfoService;
import com.paic.ehis.system.api.domain.*;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * base_provider_info(服务商基本信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
@Slf4j
@Transactional
public class BaseProviderInfoServiceImpl implements IBaseProviderInfoService
{
    @Autowired
    private BaseProviderInfoMapper baseProviderInfoMapper;

    @Autowired
    private IBaseProviderServiceService baseProviderServiceService;

    @Autowired
    private IBaseProviderApplyService baseProviderApplyService;

    @Autowired
    private IBaseBankService baseBankService;

    @Autowired
    private IBaseContactsService baseContactsService;

    @Autowired
    private IBaseProviderDepService baseProviderDepService;

    @Autowired
    private IBaseSupplierContractService baseSupplierContractService;

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
     * @param ProviderInfo base_provider_info(服务商基本信息)ID
     * @return base_provider_info(服务商基本信息)
     */
    @Override
    public BaseProviderInfo selectBaseProviderInfo(BaseProviderInfo ProviderInfo)
    {
        BaseProviderInfo baseProviderInfo = null;
        if("01".equals(ProviderInfo.getOrgFlag())){
            baseProviderInfo = baseProviderInfoMapper.selectBaseProviderInfoById(ProviderInfo.getProviderCode());
        }else if("02".equals(ProviderInfo.getOrgFlag())){
            baseProviderInfo = baseProviderInfoMapper.selectBaseProviderInfoByIdNew(ProviderInfo.getProviderCode());
        }
        if(baseProviderInfo != null){
            if(StringUtils.isNotBlank(baseProviderInfo.getType2Str())){
                baseProviderInfo.setType2(Arrays.asList((baseProviderInfo.getType2Str().split(","))));
            }else{
                baseProviderInfo.setType2(new ArrayList());
            }
        }
        return baseProviderInfo;
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
    public List<BaseProviderInfo> selectBaseProviderInfoListNew(BaseProviderInfo baseProviderInfo)
    {
        return baseProviderInfoMapper.selectBaseProviderInfoListNew(baseProviderInfo);
    }

    @Override
    public List<BaseProviderInfo> selectProvideInfoList(BaseProviderInfo baseProviderInfo)
    {

        return baseProviderInfoMapper.selectProvideInfoList(baseProviderInfo);
    }


    /**
     * 查询base_provider_info(服务商基本信息)列表
     *
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return base_provider_info(服务商基本信息)
     */
    @Override
    public List<BaseProviderInfo> selectBaseProviderInfoCheckList(BaseProviderInfo baseProviderInfo)
    {
        return baseProviderInfoMapper.selectBaseProviderInfoCheckList(baseProviderInfo);
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
        /*if(StringUtils.isNotBlank(baseProviderInfo.getProviderCode())){
            //服务商编码不为为空时
            if("01".equals(baseProviderInfo.getOrgFlag())){
                baseProviderInfoMapper.updateBaseProviderInfoByCode(baseProviderInfo);
            }else if("02".equals(baseProviderInfo.getOrgFlag())){
                baseProviderInfoMapper.updateBaseProviderInfoByproviderCodeNew(baseProviderInfo.getProviderCode());
            }
            baseProviderInfo.setCreateTime(DateUtils.getNowDate());
            baseProviderInfo.setUpdateTime(DateUtils.getNowDate());
            baseProviderInfo.setCreateBy(SecurityUtils.getUsername());
            baseProviderInfo.setUpdateBy(SecurityUtils.getUsername());
            baseProviderInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("providerinfoSer",12,12));
            baseProviderInfo.setStatus("Y");
            baseProviderInfo.setUpdateFlag("0");
            if("01".equals(baseProviderInfo.getOrgFlag())){
                baseProviderInfo.setBussinessStatus("01");  //新建状态
                baseProviderInfoMapper.insertBaseProviderInfo(baseProviderInfo);
            }else if("02".equals(baseProviderInfo.getOrgFlag())){
                baseProviderInfo.setBussinessStatus("03");  //有效状态
                baseProviderInfoMapper.insertBaseProviderInfoNew(baseProviderInfo);
            }
        }else{*/ //服务商编码为空时新增服务商基本信息
            baseProviderInfo.setCreateTime(DateUtils.getNowDate());
            baseProviderInfo.setUpdateTime(DateUtils.getNowDate());
            baseProviderInfo.setCreateBy(SecurityUtils.getUsername());
            baseProviderInfo.setUpdateBy(SecurityUtils.getUsername());
            baseProviderInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("providerinfoSer",12,12));
           if("01".equals(baseProviderInfo.getOrgFlag())){
               baseProviderInfo.setProviderCode("H"+ PubFun.createMySqlMaxNoUseCache("hospitalCode",10,9));
           }else if("02".equals(baseProviderInfo.getOrgFlag())){
               baseProviderInfo.setProviderCode("M"+ PubFun.createMySqlMaxNoUseCache("otherOrgCode",10,9));
           }

            baseProviderInfo.setStatus("Y");
            baseProviderInfo.setUpdateFlag("0");
            if("01".equals(baseProviderInfo.getOrgFlag())){
                baseProviderInfo.setBussinessStatus("01");  //新建状态
                //查询省的中文名称
                String provincesName = baseProviderInfoMapper.selectName(baseProviderInfo.getProvince());
                provincesName = provincesName.substring(0,2);
                if("内蒙".equals(provincesName)){
                    provincesName = "内蒙古";
                }
                if("黑龙".equals(provincesName)){
                    provincesName = "黑龙江";
                }
                String provincesNameen = getPinYinHeadChar(provincesName);
                //获取流水号
                String provincesNo = baseProviderInfoMapper.selectNum(baseProviderInfo.getProvince());
                if(provincesNo !=null && provincesNo !=""){
                    provincesNo = String.format("%0" + 5 + "d", Integer.parseInt(provincesNo) + 1);
                    baseProviderInfo.setClaimHospitalCode(provincesNameen+provincesNo);
                }else{
                    baseProviderInfo.setClaimHospitalCode(provincesNameen+"00001");
                }
                baseProviderInfo.setType2Str(StringUtils.join(baseProviderInfo.getType2(), ","));
               //baseProviderInfoMapper.updateBaseProviderInfoByCode(baseProviderInfo);
                baseProviderInfoMapper.insertBaseProviderInfo(baseProviderInfo);
            }else if("02".equals(baseProviderInfo.getOrgFlag())){
                baseProviderInfo.setBussinessStatus("03");  //有效状态
                //baseProviderInfoMapper.updateBaseProviderInfoByproviderCodeNew(baseProviderInfo.getProviderCode());
                baseProviderInfoMapper.insertBaseProviderInfoNew(baseProviderInfo);
            }
            baseProvider.setProviderCode(baseProviderInfo.getProviderCode());
        //}

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
        //服务商编码不为为空时
        int count = 0;
        String province = baseProviderInfoMapper.selectName1(baseProviderInfo.getProviderCode());
        if("01".equals(baseProviderInfo.getOrgFlag())){
            baseProviderInfoMapper.updateBaseProviderInfoByCode(baseProviderInfo);
        }else if("02".equals(baseProviderInfo.getOrgFlag())){
            baseProviderInfoMapper.updateBaseProviderInfoByproviderCodeNew(baseProviderInfo.getProviderCode());
        }
        baseProviderInfo.setCreateTime(DateUtils.getNowDate());
        baseProviderInfo.setUpdateTime(DateUtils.getNowDate());
        baseProviderInfo.setCreateBy(SecurityUtils.getUsername());
        baseProviderInfo.setUpdateBy(SecurityUtils.getUsername());
        /*BaseProviderInfo baseProviderInfoNew = baseProviderInfoMapper.selectBaseProviderInfoByIdNew(baseProviderInfo.getProviderCode());
        if(baseProviderInfoNew != null){
            baseProviderInfoMapper.updateBaseProviderInfoByproviderCodeNew(baseProviderInfo.getProviderCode());
        }*/
        baseProviderInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("providerinfoSer",12,12));
        baseProviderInfo.setStatus("Y");
        baseProviderInfo.setUpdateFlag("0");
        if("01".equals(baseProviderInfo.getOrgFlag())){
            //通过编码查询省的信息
            if(!province.equals(baseProviderInfo.getProvince())){
                //查询省的中文名称
                String provincesName = baseProviderInfoMapper.selectName(baseProviderInfo.getProvince());
                provincesName = provincesName.substring(0,2);
                if("内蒙".equals(provincesName)){
                    provincesName = "内蒙古";
                }
                if("黑龙".equals(provincesName)){
                    provincesName = "黑龙江";
                }
                String provincesNameen = getPinYinHeadChar(provincesName);
                //获取流水号
                String provincesNo = baseProviderInfoMapper.selectNum(baseProviderInfo.getProvince());
                if(provincesNo !=null && provincesNo !=""){
                    provincesNo = String.format("%0" + 5 + "d", Integer.parseInt(provincesNo) + 1);
                    baseProviderInfo.setClaimHospitalCode(provincesNameen+provincesNo);
                }else{
                    baseProviderInfo.setClaimHospitalCode(provincesNameen+"00001");
                }
            }
            baseProviderInfo.setType2Str(StringUtils.join(baseProviderInfo.getType2(), ","));
            baseProviderInfo.setBussinessStatus("01");  //新建状态
            count = baseProviderInfoMapper.insertBaseProviderInfo(baseProviderInfo);
        }else if("02".equals(baseProviderInfo.getOrgFlag())){
            baseProviderInfo.setBussinessStatus("03");  //有效状态
            count =baseProviderInfoMapper.insertBaseProviderInfoNew(baseProviderInfo);
        }
        return count;
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


    @Override
    public int insertCheckInfo(ProviderInfoVo providerInfoVo)
    {
        //基础信息保存并提交审核
        int i =0;
        BaseProviderInfo baseProviderInfo = providerInfoVo.getBaseInfo();
        String province = baseProviderInfoMapper.selectName1(baseProviderInfo.getProviderCode());
        if("01".equals(baseProviderInfo.getOrgFlag())){
            baseProviderInfoMapper.updateBaseProviderInfoByCode(baseProviderInfo);
            /*BaseProviderInfo baseProviderInfoNew = baseProviderInfoMapper.selectBaseProviderInfoByIdNew(baseProviderInfo.getProviderCode());
            if(baseProviderInfoNew != null){
                baseProviderInfoMapper.updateBaseProviderInfoByproviderCodeNew(baseProviderInfo.getProviderCode());
            }*/
            baseProviderInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("providerinfoSer",12,12));
            baseProviderInfo.setStatus("Y");
                //查询省的中文名称
                String provincesName = baseProviderInfoMapper.selectName(baseProviderInfo.getProvince());
                provincesName = provincesName.substring(0,2);
                if("内蒙".equals(provincesName)){
                    provincesName = "内蒙古";
                }
                if("黑龙".equals(provincesName)){
                    provincesName = "黑龙江";
                }
                String provincesNameen = getPinYinHeadChar(provincesName);
                //获取流水号
                String provincesNo = baseProviderInfoMapper.selectNum(baseProviderInfo.getProvince());
                if(provincesNo !=null && provincesNo !=""){
                    provincesNo = String.format("%0" + 5 + "d", Integer.parseInt(provincesNo) + 1);
                    baseProviderInfo.setClaimHospitalCode(provincesNameen+provincesNo);
                }else{
                    baseProviderInfo.setClaimHospitalCode(provincesNameen+"00001");
                }

            baseProviderInfo.setCreateTime(DateUtils.getNowDate());
            baseProviderInfo.setBussinessStatus("02");
            baseProviderInfo.setUpdateFlag("0");
            i = baseProviderInfoMapper.insertBaseProviderInfo(baseProviderInfo);
        }else if("02".equals(baseProviderInfo.getOrgFlag())){
            baseProviderInfoMapper.updateBaseProviderInfoByproviderCodeNew(baseProviderInfo.getProviderCode());
            baseProviderInfo.setCreateTime(DateUtils.getNowDate());
            baseProviderInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("providerinfoSer",12,12));
            baseProviderInfo.setStatus("Y");
            baseProviderInfo.setBussinessStatus("03");
            baseProviderInfo.setUpdateFlag("0");
            i = baseProviderInfoMapper.insertBaseProviderInfoNew(baseProviderInfo);
        }

        //服务信息保存并提交审核
        if(providerInfoVo.getServiceInfo() !=null){
            baseProviderServiceService.insertBaseProviderService(providerInfoVo.getServiceInfo());
        }
        //申请信息保存并提交审核
        if(providerInfoVo.getReserveInfo() !=null){
            baseProviderApplyService.insertBaseProviderApply(providerInfoVo.getReserveInfo());
        }
        //账户信息的保存
        if(providerInfoVo.getClosingInfo() != null){
            baseBankService.insertBaseBank(providerInfoVo.getClosingInfo());
        }
        //联系人信息保存
        if(providerInfoVo.getContactInfo() != null){
            baseContactsService.insertBaseContacts(providerInfoVo.getContactInfo());
        }
        //科室信息保存
        if(providerInfoVo.getDepartmentInfo() !=null){
            baseProviderDepService.insertBaseProviderDep(providerInfoVo.getDepartmentInfo());
        }
        return  i;
    }

    @Override
    public int insertChecDatak(ProviderInfoVo providerInfoVo)
    {

        String providerCode = providerInfoVo.getProviderCode();
        if("01".equals(providerInfoVo.getCheckResult())){//审核通过
            //备份表中的数据修改状态
            baseProviderInfoMapper.updateBaseProviderInfoByproviderCode(providerCode);
            //数据挪到正式表中
            this.insertBaseProviderInfoNew(providerCode);
            baseProviderServiceService.insertBaseProviderServiceNew(providerCode);
            baseProviderApplyService.insertBaseProviderApplyNew(providerCode);
            baseBankService.insertBaseBankNew(providerCode);
            baseContactsService.insertBaseContactsNew(providerCode);
            baseProviderDepService.insertBaseProviderDepNew(providerCode);
            baseSupplierContractService.insertBaseSupplierContractNew(providerCode);
        }else if("02".equals(providerInfoVo.getCheckResult())){ //审核拒绝，审核状态改成新建状态
            //备份表中的数据修改状态
            baseProviderInfoMapper.updateBaseProviderInfoStatus(providerCode);
        }
        //审核表里增加数据
        BaseCheckInfo baseCheckInfo = new BaseCheckInfo();
        baseCheckInfo.setCheckAdvice(providerInfoVo.getCheckAdvice());
        baseCheckInfo.setCheckResult(providerInfoVo.getCheckResult());
        baseCheckInfo.setProviderCode(providerCode);

        baseCheckInfo.setCreateTime(DateUtils.getNowDate());
        baseCheckInfo.setUpdateTime(DateUtils.getNowDate());
        baseCheckInfo.setCreateBy(SecurityUtils.getUsername());
        baseCheckInfo.setUpdateBy(SecurityUtils.getUsername());
        baseCheckInfo.setStatus("Y");
        //baseCheckInfo.setCreateBy();   //操作人信息
        baseCheckInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("BaseCheckSer",12,12));
        int i=baseProviderInfoMapper.insertCheckInfo(baseCheckInfo);
        return  i;
    }


    public int insertBaseProviderInfoNew(String providerCode)
    {
        BaseProviderInfo baseProviderInfo = baseProviderInfoMapper.selectBaseProviderInfoById(providerCode);
        BaseProviderInfo baseProviderInfoNew = baseProviderInfoMapper.selectBaseProviderInfoByIdNew(providerCode);

        if(baseProviderInfoNew != null){
            baseProviderInfoMapper.updateBaseProviderInfoByproviderCodeNew(providerCode);
        }
        return baseProviderInfoMapper.insertBaseProviderInfoNew(baseProviderInfo);
    }


    @Override
    public List<BaseCheckInfo> selectBaseProviderCheckList(String providerCode)
    {
        return baseProviderInfoMapper.selectBaseProviderCheckList(providerCode);
    }


    @Override
    public List<BaseProviderInfo> selectBaseProviderInfoByNames(BaseProviderInfo baseProviderInfo)
    {
        return baseProviderInfoMapper.selectBaseProviderInfo(baseProviderInfo);
    }

    @Override
    public List<BaseProviderInfo> selectBaseProviderInfoByNamesNew(BaseProviderInfo baseProviderInfo)
    {
        return baseProviderInfoMapper.selectBaseProviderInfoNew(baseProviderInfo);
    }


    @Override
    public  List<BaseProviderInfo> selectBaseProviderInfos(BaseProviderInfo baseProviderInfo) {
        return baseProviderInfoMapper.selectBaseProviderInfos(baseProviderInfo);
    }

    @Override
    public  List<BaseProviderInfo> selectHospitalInfo(BaseProviderInfo baseProviderInfo) {
        return baseProviderInfoMapper.selectHospitalInfo(baseProviderInfo);
    }
    @Override
    public  List<BaseProviderInfo> selectBaseProviderBackInfos(BaseProviderInfo baseProviderInfo) {
        return baseProviderInfoMapper.selectBaseProviderBackInfos(baseProviderInfo);
    }


    /**
    * 得到中文首字母（清华大学 -> QHDX）
    * @param str 需要转化的中文字符串
    * @return 大写首字母缩写的字符串
    */
public  String getPinYinHeadChar(String str) {
    StringBuilder convert = new StringBuilder();
    for (int j = 0; j < str.length(); j++) {
        char word = str.charAt(j);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
        if (pinyinArray != null) {
            convert.append(pinyinArray[0].charAt(0));
        } else {
            convert.append(word);
        }
    }
    return convert.toString().toUpperCase();
}


    /**
     *查询服务项目列表
     */
    @Override
    public List<BaseServiceInfo> getServiceInfo(){
        return baseProviderInfoMapper.getServiceInfo();
    }

    /* 获取供应商的以及联系人信息*/
    @Override
    public List<SupplierInfoVo> getSupplierInfo(){

        log.info("********获取工单中服务chanchan供应商的人信息********");
        List<SupplierInfoVo> supplierInfos= baseProviderInfoMapper.getSupplierInfo();
        return supplierInfos;
    }

    //获取医院信息
    @Override
    public List<HospitalInfoVo> getHospitalInfo(){
        return baseProviderInfoMapper.getHospitalInfo();
    }

    //获取一级科室
    @Override
    public List<FirstDeptInfoVo> getFirstDeptInfo(){
        return baseProviderInfoMapper.getFirstDeptInfo();
    }

    //获取二级科室
    @Override
    public List<SecondDeptInfoVo> getSecondDeptInfo(){
        return baseProviderInfoMapper.getSecondDeptInfo();
    }

    @Override
    public List<HospitalInfoVo> getHospitalInfo1(com.paic.ehis.system.api.domain.AddressInfo addressInfo){
        return baseProviderInfoMapper.getHospitalInfo1(addressInfo);
    }
}

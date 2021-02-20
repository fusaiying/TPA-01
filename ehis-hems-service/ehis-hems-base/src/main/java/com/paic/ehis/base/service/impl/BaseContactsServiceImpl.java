package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.base.utility.PinYinUtils;
import com.paic.ehis.base.domain.BaseContacts;
import com.paic.ehis.base.domain.vo.BaseContactsVo;
import com.paic.ehis.base.mapper.BaseContactsMapper;
import com.paic.ehis.base.service.IBaseContactsService;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * base_contacts（联系人信息）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
public class BaseContactsServiceImpl implements IBaseContactsService {
    @Autowired
    private BaseContactsMapper baseContactsMapper;


    /**
     * 查询base_contacts（联系人信息）
     * 
     * @param serialno base_contacts（联系人信息）ID
     * @return base_contacts（联系人信息）
     */
    @Override
    public BaseContacts selectBaseContactsById(String serialno)
    {
        return baseContactsMapper.selectBaseContactsById(serialno);
    }

    /**
     * 查询base_contacts（联系人信息）列表
     * 
     * @param baseContacts base_contacts（联系人信息）
     * @return base_contacts（联系人信息）
     */
    @Override
    public List<BaseContacts> selectBaseContactsList(BaseContacts baseContacts)
    {
        return baseContactsMapper.selectBaseContactsList(baseContacts);
    }

    @Override
    public int insertBaseContacts(BaseContacts baseContacts) {
        return 0;
    }

    /**
     * 新增base_contacts（联系人信息）
     * 
     * @param baseContactsVo base_contacts（联系人信息）
     * @return 结果
     */
    @Override
    public int insertBaseContacts(BaseContactsVo baseContactsVo)
    {
        int count = 0;
        int i = 0;
        //List<BaseContacts> baseContactsinfos = baseContactsMapper.selectBaseContactsByCode(baseContactsVo.get(0).getSupplierCode());
        if("01".equals(baseContactsVo.getOrgFlag())){
            baseContactsMapper.updateBaseContactsByCode(baseContactsVo.getProviderCode());
        }else if("02".equals(baseContactsVo.getOrgFlag())){
            baseContactsMapper.updateBaseContactsByCodeNew(baseContactsVo.getProviderCode());
        }
        if(!baseContactsVo.getContacts().isEmpty()){
            for(BaseContacts baseContact :baseContactsVo.getContacts()){
                baseContact.setCreateTime(DateUtils.getNowDate());
                baseContact.setUpdateTime(DateUtils.getNowDate());
                baseContact.setCreateBy(SecurityUtils.getUsername());
                baseContact.setUpdateBy(SecurityUtils.getUsername());
                baseContact.setSupplierCode(baseContactsVo.getProviderCode());
                if("02".equals(baseContact.getPlaceType())){
                    baseContact.setRole("PA"+PubFun.createMySqlMaxNoUseCache("accountNoSer",10,8));
                    baseContact.setPassword(ToPinyin(baseContact.getName())+"123456");
                }
                baseContact.setSerialNo(PubFun.createMySqlMaxNoUseCache("contactsSer",12,12));
                baseContact.setProviderCode(baseContactsVo.getProviderCode());
                baseContact.setUpdateFlag("0");
                baseContact.setStatus("Y");
                if("01".equals(baseContactsVo.getOrgFlag())){
                    i = baseContactsMapper.insertBaseContacts(baseContact);
                }else if("02".equals(baseContactsVo.getOrgFlag())){
                    i = baseContactsMapper.insertBaseContactsNew(baseContact);
                }
                count += i;
            }
        }
        return count;
    }

    @Override
    public int addBaseContacts(List<BaseContacts> baseContactsList) {
       /* int count = 0;
        if (!baseContactsVo.isEmpty()) {
            baseContactsMapper.updateBaseContactsStatus(baseContactsVo.get(0).getSupplierCode());
            for (BaseContacts baseContact : baseContactsVo) {
                baseContact.setCreateTime(DateUtils.getNowDate());
                baseContact.setStatus("Y");
                baseContact.setSerialNo(PubFun.createMySqlMaxNoUseCache("derialno", 10, 9));
                int i = baseContactsMapper.insertBaseContacts(baseContact);
                count += i;
            }
        } else {
            for (BaseContacts baseContact : baseContactsVo) {
                baseContact.setCreateTime(DateUtils.getNowDate());
                baseContact.setStatus("Y");
                baseContact.setSerialNo(PubFun.createMySqlMaxNoUseCache("derialno", 10, 9));
                int i = baseContactsMapper.insertBaseContacts(baseContact);
                count += i;
            }
        }
        return count;*/
        int count = 0;
        if (!baseContactsList.isEmpty()) {
            baseContactsMapper.updateBaseContactsStatus(baseContactsList.get(0).getSupplierCode());
            for (BaseContacts baseContacts : baseContactsList) {
                if ("01".equals(baseContacts.getPlaceType())){
                    String name=baseContacts.getName();
                    baseContacts.setPassword(PinYinUtils.toPinYin(name)+"123456");
                }
                baseContacts.setCreateTime(DateUtils.getNowDate());
                baseContacts.setStatus("Y");
                baseContacts.setSerialNo(PubFun.createMySqlMaxNoUseCache("contactsSer",12,12));
                int i = baseContactsMapper.insertBaseContactsNew(baseContacts);
                count += i;
            }
        } else {
            for (BaseContacts baseContacts : baseContactsList) {
                if ("01".equals(baseContacts.getPlaceType())){
                    String name=baseContacts.getName();
                    baseContacts.setPassword(this.getPinYinHeadChar(name)+"123456");
                }
                baseContacts.setCreateTime(DateUtils.getNowDate());
                baseContacts.setStatus("Y");
                baseContacts.setSerialNo(PubFun.createMySqlMaxNoUseCache("contactsSer",12,12));
                int i = baseContactsMapper.insertBaseContactsNew(baseContacts);
                count += i;
            }
        }
        return count;
    }

    @Override
    public int updateBaseContactsStatus(String supplierCode) {
        return baseContactsMapper.updateBaseContactsStatus(supplierCode);
    }


    /**
     * 新增base_contacts（联系人信息）
     *
     * @param providerCode base_contacts（联系人信息）
     * @return 结果
     */
    @Override
    public int insertBaseContactsNew(String providerCode)
    {
        int count = 0;
        List<BaseContacts> baseContactsinfos = baseContactsMapper.selectBaseContactsByCode(providerCode);
        List<BaseContacts> baseContactsinfosNew = baseContactsMapper.selectBaseContactsByCodeNew(providerCode);
        if(!baseContactsinfosNew.isEmpty()){ //存在则修改已存在的数据
            baseContactsMapper.updateBaseContactsByCodeNew(providerCode);
        }
        for(BaseContacts baseContact :baseContactsinfos){
            baseContact.setCreateTime(DateUtils.getNowDate());
            int i =baseContactsMapper.insertBaseContactsNew(baseContact);
            count += i;
        }
        return count;
    }



    /**
     * 批量删除base_contacts（联系人信息）
     * 
     * @param serialnos 需要删除的base_contacts（联系人信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseContactsByIds(String[] serialnos)
    {
        return baseContactsMapper.deleteBaseContactsByIds(serialnos);
    }

    /**
     * 删除base_contacts（联系人信息）信息
     * 
     * @param serialno base_contacts（联系人信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseContactsById(String serialno)
    {
        return baseContactsMapper.deleteBaseContactsById(serialno);
    }

    /**
     * 查询base_contacts（联系人信息）
     *
     * @param baseContacts base_contacts（联系人信息）
     * @return base_contacts（联系人信息）
     */
    @Override
    public List<BaseContacts> selectBaseContactsByCode(BaseContacts baseContacts)
    {
        List<BaseContacts> BaseContactsinfos = new ArrayList();
        if("01".equals(baseContacts.getOrgFlag())){
            BaseContactsinfos = baseContactsMapper.selectBaseContactsByCode(baseContacts.getProviderCode());
        }else if("02".equals(baseContacts.getOrgFlag())){
            BaseContactsinfos = baseContactsMapper.selectBaseContactsByCodeNew(baseContacts.getProviderCode());
        }
        return BaseContactsinfos;
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
     * 汉字转为拼音
     * @param chinese
     * @return
     */
    public static String ToPinyin(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }


}

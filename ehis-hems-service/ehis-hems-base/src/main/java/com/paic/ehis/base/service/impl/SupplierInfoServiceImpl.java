package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.base.base.utility.PinYinUtils;
import com.paic.ehis.base.domain.*;
import com.paic.ehis.base.mapper.*;
import com.paic.ehis.base.service.SupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class SupplierInfoServiceImpl implements SupplierInfoService {
    @Autowired
    private BaseSupplierInfoMapper baseSupplierInfoMapper;
    @Autowired
    private BaseBankMapper baseBankMapper;
    @Autowired
    private BaseContactsMapper baseContactsMapper;
    @Autowired
    private BaseSupplierOutletsMapper baseSupplierOutletsMapper;
    @Autowired
    private BaseSupplierReceipMapper baseSupplierReceipMapper;
    @Autowired
    private PrivateHmpProviderMapper privateHmpProviderMapper;
    @Autowired
    private SecurityUtils securityUtils;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseSupplierInfo addBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo) {
        String serialNo = baseSupplierInfo.getSerialNo();
        String username = securityUtils.getUsername();
        Date nowDate = DateUtils.getNowDate();
        if (StringUtils.isEmpty(serialNo)) {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            serialNo = "PR" + date.format(formatter) + PubFun.createMySqlMaxNoUseCache("BaseSupplierInfo", 0, 4);
            baseSupplierInfo.setSerialNo(serialNo);
            baseSupplierInfo.setCreateBy(username);
            baseSupplierInfo.setCreateTime(nowDate);
            baseSupplierInfo.setUpdateBy(username);
            baseSupplierInfo.setUpdateTime(nowDate);
            baseSupplierInfo.setStatus("Y");
            baseSupplierInfoMapper.insertBaseSupplierInfo(baseSupplierInfo);
            String supplierCode = serialNo;
            List<BaseSupplierReceip> baseSupplierReceipList=baseSupplierInfo.getBaseSupplierReceipList();
            if (!baseSupplierReceipList.isEmpty()) {
                baseSupplierInfo.getBaseSupplierReceipList().forEach(item -> {
                    item.setSerialNo(PubFun.createMySqlMaxNoUseCache("derialno", 10, 9));
                    item.setStatus("Y");
                    item.setSupplierCode(supplierCode);
                    item.setCreateBy(username);
                    item.setCreateTime(nowDate);
                    item.setUpdateBy(username);
                    item.setUpdateTime(nowDate);
                });
                privateHmpProviderMapper.insertReceiptAll(baseSupplierReceipList);
            }

            List<BaseBank> baseBankList = baseSupplierInfo.getBaseBankList();
            if (!baseBankList.isEmpty()) {
                baseSupplierInfo.getBaseBankList().forEach(item -> {
                    item.setSerialNo(PubFun.createMySqlMaxNoUseCache("bankSer",12,12));
                    item.setStatus("Y");
                    item.setCreateTime(nowDate);
                    item.setUpdateTime(nowDate);
                    item.setProviderCode(supplierCode);
                });
                privateHmpProviderMapper.insertBankAll(baseBankList);
            }

            List<BaseContacts> baseContactsList = baseSupplierInfo.getBaseContactsList();
            if (!baseContactsList.isEmpty()) {
            baseSupplierInfo.getBaseContactsList().forEach(item -> {
                if ("01".equals(item.getPlaceType())){
                    String name=item.getName();
                    item.setPassword(PinYinUtils.toPinYin(name)+"123456");
                }
                item.setSerialNo(PubFun.createMySqlMaxNoUseCache("contactsSer",12,12));
                item.setSupplierType("01");
                item.setStatus("Y");
                item.setSupplierCode(supplierCode);
                item.setCreateBy(username);
                item.setCreateTime(nowDate);
                item.setUpdateBy(username);
                item.setUpdateTime(nowDate);
            });
            privateHmpProviderMapper.insertContactsAll(baseContactsList);
            }

        }else {
            baseSupplierInfoMapper.updateBaseSupplierInfo(baseSupplierInfo);
            String providerCode = baseSupplierInfo.getSerialNo();
            List<BaseSupplierReceip> baseSupplierReceipList = baseSupplierInfo.getBaseSupplierReceipList();
            baseSupplierReceipMapper.updatebaseSupplierReceipStatus(providerCode);
            if (!baseSupplierReceipList.isEmpty()) {
                baseSupplierInfo.getBaseSupplierReceipList().forEach(item -> {
                    item.setSupplierCode(providerCode);
                    item.setSerialNo(PubFun.createMySqlMaxNoUseCache("derialno", 10, 9));
                    item.setStatus("Y");
                    item.setCreateBy(username);
                    item.setCreateTime(nowDate);
                    item.setUpdateBy(username);
                    item.setUpdateTime(nowDate);
                });
                privateHmpProviderMapper.insertReceiptAll(baseSupplierReceipList);
            }

            List<BaseBank> baseBankList = baseSupplierInfo.getBaseBankList();
            baseBankMapper.updatebaseBankStatus(providerCode);
            if (!baseBankList.isEmpty()) {
                baseSupplierInfo.getBaseBankList().forEach(item -> {
                    item.setSerialNo(PubFun.createMySqlMaxNoUseCache("bankSer",12,12));
                    item.setProviderCode(providerCode);
                    item.setStatus("Y");
                    item.setCreateTime(nowDate);
                    item.setUpdateTime(nowDate);
                });
                privateHmpProviderMapper.insertBankAll(baseBankList);
            }

            List<BaseContacts> baseContactsList = baseSupplierInfo.getBaseContactsList();
            baseContactsMapper.updateBaseContactsStatus(providerCode);
            if (!baseContactsList.isEmpty()) {
                baseSupplierInfo.getBaseContactsList().forEach(item -> {
                    if ("01".equals(item.getPlaceType())) {
                        String name = item.getName();
                        item.setPassword(PinYinUtils.toPinYin(name) + "123456");
                    }
                    item.setSupplierCode(providerCode);
                    item.setStatus("Y");
                    item.setSerialNo(PubFun.createMySqlMaxNoUseCache("contactsSer",12,12));
                    item.setSupplierType("01");
                    item.setCreateBy(username);
                    item.setCreateTime(nowDate);
                    item.setUpdateBy(username);
                    item.setUpdateTime(nowDate);
                });
                privateHmpProviderMapper.insertContactsAll(baseContactsList);
            }
        }
        return baseSupplierInfo;
    }
}




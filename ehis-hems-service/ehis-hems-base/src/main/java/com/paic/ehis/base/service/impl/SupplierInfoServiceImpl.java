package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.domain.*;
import com.paic.ehis.base.mapper.*;
import com.paic.ehis.base.service.SupplierInfoService;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
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



    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo) {
        String servcomNo = baseSupplierInfo.getServcomNo();
        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();
        //如果 servcomNo 为空代表新增，不为空代表修改
        if (StringUtils.isEmpty(servcomNo)) {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            servcomNo ="PR" +date.format(formatter)+PubFun.createMySqlMaxNoUseCache("BaseSupplierInfo", 0, 4);
            baseSupplierInfo.setServcomNo(servcomNo);
            baseSupplierInfo.setCreateBy(username);
            baseSupplierInfo.setCreateTime(nowDate);
            baseSupplierInfo.setUpdateBy(username);
            baseSupplierInfo.setUpdateTime(nowDate);
            baseSupplierInfoMapper.insertBaseSupplierInfo(baseSupplierInfo);
        } else {
            baseBankMapper.deleteBaseBankById(servcomNo);
            baseContactsMapper.deleteBaseContactsById(servcomNo);
            baseSupplierOutletsMapper.deleteBaseSupplierOutletsById(servcomNo);
            baseSupplierReceipMapper.deleteBaseSupplierReceipById(servcomNo);
            baseSupplierInfoMapper.selectBaseSupplierInfoById(servcomNo);
            if (baseSupplierInfo == null) {
                return 0;
            }
            baseSupplierInfo.setServcomNo(servcomNo);
            baseSupplierInfo.setUpdateBy(username);
            baseSupplierInfo.setUpdateTime(nowDate);
            baseSupplierInfoMapper.updateBaseSupplierInfo(baseSupplierInfo);
        }
        String finalServcomno = servcomNo;
        List<BaseBank> baseBankList = baseSupplierInfo.getBaseBankList();
        if (!baseBankList.isEmpty()) {
            baseSupplierInfo.getBaseBankList().forEach(item -> {
                item.setId(finalServcomno);
                item.setCreateBy(username);
                item.setCreateTime(nowDate);
                item.setUpdateBy(username);
                item.setUpdateTime(nowDate);
            });
            privateHmpProviderMapper.insertBankAll(baseBankList);
        }
        List<BaseContacts> baseContactsList = baseSupplierInfo.getBaseContactsList();
        if (!baseContactsList.isEmpty()) {
            baseSupplierInfo.getBaseContactsList().forEach(item -> {
                item.setSerialNo(PubFun.createMySqlMaxNoUseCache("suppliercontacts", 0, 12));
                item.setSupplierType("01");
                item.setSupplierCode(finalServcomno);
                item.setCreateBy(username);
                item.setCreateTime(nowDate);
                item.setUpdateBy(username);
                item.setUpdateTime(nowDate);
            });
            privateHmpProviderMapper.insertContactsAll(baseContactsList);
        }
        List<BaseSupplierReceip> baseSupplierReceipList = baseSupplierInfo.getBaseSupplierReceipList();
        if (!baseSupplierReceipList.isEmpty()) {
            baseSupplierInfo.getBaseSupplierReceipList().forEach(item -> {
                item.setSupplierCode(finalServcomno);
                item.setCreateBy(username);
                item.setCreateTime(nowDate);
                item.setUpdateBy(username);
                item.setUpdateTime(nowDate);
            });
            privateHmpProviderMapper.insertReceiptAll(baseSupplierReceipList);
        }
        List<BaseSupplierOutlets> baseSupplierOutletsList = baseSupplierInfo.getBaseSupplierOutletsList();
        if (!baseSupplierOutletsList.isEmpty()) {
            baseSupplierInfo.getBaseSupplierOutletsList().forEach(item -> {
                item.setServcomNo(finalServcomno);
                item.setCreateBy(username);
                item.setCreateTime(nowDate);
                item.setUpdateBy(username);
                item.setUpdateTime(nowDate);
            });
            privateHmpProviderMapper.insertOutletsAll(baseSupplierOutletsList);
        }
        return 1;
    }
}

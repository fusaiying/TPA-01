package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.base.utility.Dateutils;
import com.paic.ehis.base.domain.*;
import com.paic.ehis.base.mapper.*;
import com.paic.ehis.base.service.IBaseSupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * base_supplier_info（供应商基础信息）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-31
 */
@Service
public class BaseSupplierInfoServiceImpl implements IBaseSupplierInfoService
{
    @Autowired
    private BaseSupplierInfoMapper baseSupplierInfoMapper;
    @Autowired
    private BaseBankMapper baseBankMapper;
    @Autowired
    private BaseContactsMapper baseContactsMapper;
    @Autowired
    private BaseSupplierReceipMapper baseSupplierReceipMapper;
    @Autowired
    private BaseSupplierOutletsMapper baseSupplierOutletsMapper;

    /**
     * 查询base_supplier_info（供应商基础信息）
     * 
     * @param servcomNo base_supplier_info（供应商基础信息）ID
     * @return base_supplier_info（供应商基础信息）
     */
    @Override
    public BaseSupplierInfo selectBaseSupplierInfoById(String servcomNo)
    {

        BaseSupplierInfo baseSupplierInfo = baseSupplierInfoMapper.selectBaseSupplierInfoById(servcomNo);
        List<String> str = new ArrayList<String>();
        str.add(baseSupplierInfo.getProvince());
        str.add(baseSupplierInfo.getCity());
        str.add(baseSupplierInfo.getDistrict());
        baseSupplierInfo.setDetail(str.toArray());
        return baseSupplierInfo;
    }

    /**
     * 查询base_supplier_info（供应商基础信息）列表
     * 
     * @param baseSupplierInfo base_supplier_info（供应商基础信息）
     * @return base_supplier_info（供应商基础信息）
     */
    @Override
    public List<BaseSupplierInfo> selectBaseSupplierInfoList(BaseSupplierInfo baseSupplierInfo) throws Exception
    {   if (StringUtils.isNull(baseSupplierInfo.getChname())&&
            StringUtils.isNull(baseSupplierInfo.getEnname())&&
            StringUtils.isNull(baseSupplierInfo.getServcomType())&&
            StringUtils.isNull(baseSupplierInfo.getBussinessStatus())&&
            StringUtils.isNull(baseSupplierInfo.getSerialNo())&&
            StringUtils.isNull(baseSupplierInfo.getProvince())&&
            StringUtils.isNull(baseSupplierInfo.getCity())&&
            StringUtils.isNull(baseSupplierInfo.getDetail()))
        {
        Map map = Dateutils.getCurrontTime1();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        baseSupplierInfo.setdBefore1(sdf.parse(String.valueOf(map.get("defaultStartDate"))));
        baseSupplierInfo.setdNow1(sdf.parse(String.valueOf(map.get("defaultEndDate"))));
        return baseSupplierInfoMapper.selectBaseSupplierInfoList(baseSupplierInfo);
    }else {
        return baseSupplierInfoMapper.selectBaseSupplierInfoList2(baseSupplierInfo);
    }
    }

    @Override
    public List<BaseSupplierInfo> selectBaseSupplierInfoList1(BaseSupplierInfo baseSupplierInfo) {
        return baseSupplierInfoMapper.selectBaseSupplierInfoList1(baseSupplierInfo);
    }

    @Override
    public List<BaseSupplierInfo> selectBaseSupplierInfoList2(BaseSupplierInfo baseSupplierInfo) {
        return baseSupplierInfoMapper.selectBaseSupplierInfoList2(baseSupplierInfo);
    }

    /**
     * 新增base_supplier_info（供应商基础信息）
     * 
     * @param baseSupplierInfo base_supplier_info（供应商基础信息）
     * @return 结果
     */
    @Override
    public BaseSupplierInfo insertBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo)
    {   String serialNo = baseSupplierInfo.getSerialNo();
        String username = SecurityUtils.getUsername();
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
        }else{
            baseSupplierInfo.setUpdateTime(DateUtils.getNowDate());
             baseSupplierInfoMapper.updateBaseSupplierInfo(baseSupplierInfo);
        }
        return baseSupplierInfo;
    }

    /**
     * 修改base_supplier_info（供应商基础信息）
     * 
     * @param baseSupplierInfo base_supplier_info（供应商基础信息）
     * @return 结果
     */
    @Override
    public int updateBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo)
    {
        baseSupplierInfo.setUpdateTime(DateUtils.getNowDate());
        return baseSupplierInfoMapper.updateBaseSupplierInfo(baseSupplierInfo);
    }



    /**
     * 批量删除base_supplier_info（供应商基础信息）
     * 
     * @param servcomNos 需要删除的base_supplier_info（供应商基础信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierInfoByIds(String[] servcomNos)
    {
        return baseSupplierInfoMapper.deleteBaseSupplierInfoByIds(servcomNos);
    }

    /**
     * 删除base_supplier_info（供应商基础信息）信息
     * 
     * @param servcomNo base_supplier_info（供应商基础信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierInfoById(String servcomNo)
    {
        return baseSupplierInfoMapper.deleteBaseSupplierInfoById(servcomNo);
    }

    // 获取所有
    @Override
    public List<BaseSupplierInfo> getAllBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo) {
        return baseSupplierInfoMapper.selectBaseSupplierInfoList(baseSupplierInfo);
    }

}

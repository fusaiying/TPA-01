package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.base.utility.Dateutils;
import com.paic.ehis.base.domain.BaseSupplierContractBak;
import com.paic.ehis.base.mapper.BaseSupplierContractBakMapper;
import com.paic.ehis.base.mapper.BaseSupplierContractMapper;
import com.paic.ehis.base.service.IBaseSupplierContractBakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * base_supplier_contract_bak（供应商合约）Service业务层处理
 * 
 * @author sino
 * @date 2021-01-08
 */
@Service
public class BaseSupplierContractBakServiceImpl implements IBaseSupplierContractBakService 
{
    @Autowired
    private BaseSupplierContractBakMapper baseSupplierContractBakMapper;

    @Autowired
    private BaseSupplierContractMapper baseSupplierContractMapper;
    /**
     * 查询base_supplier_contract_bak（供应商合约）
     * 
     * @param serialNo base_supplier_contract_bak（供应商合约）ID
     * @return base_supplier_contract_bak（供应商合约）
     */
    @Override
    public BaseSupplierContractBak selectBaseSupplierContractBakById(String serialNo)
    {
        return baseSupplierContractBakMapper.selectBaseSupplierContractBakById(serialNo);
    }

    /**
     * 查询base_supplier_contract_bak（供应商合约）列表
     * 
     * @param baseSupplierContractBak base_supplier_contract_bak（供应商合约）
     * @return base_supplier_contract_bak（供应商合约）
     */
    @Override
    public List<BaseSupplierContractBak> selectBaseSupplierContractBakList(BaseSupplierContractBak baseSupplierContractBak)
    {
        return baseSupplierContractBakMapper.selectBaseSupplierContractBakList(baseSupplierContractBak);
    }


    /**若服务商下已签订合约，合约列表中服务商对应的合约编码、合约名称、合约起止日期均有值，
     *且当服务商下存在多条合约信息时仅显示该服务商下合约终止日期最晚的一条合约信息（即服务商下创建时间最晚的一条合约信息）
     **/
    @Override
    public  List<BaseSupplierContractBak> selectBaseSupplierBakLast(String providercode) {
        return baseSupplierContractBakMapper.selectBaseSupplierBakLast(providercode);
    }

    /*
     *    服务商合约管理主查询页面需默认显示截止当前时间合约签约时间在三个月内且合约状态为“有效”的数据
     */
    @Override
    public List<BaseSupplierContractBak> selectBaseSupplierBakMonth(BaseSupplierContractBak baseSupplierContractBak) throws Exception {
        Map map = Dateutils.getCurrontTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        baseSupplierContractBak.setDBefore(sdf.parse(String.valueOf(map.get("defaultStartDate"))));
        baseSupplierContractBak.setDNow(sdf.parse(String.valueOf(map.get("defaultEndDate"))));
        return baseSupplierContractBakMapper.selectBaseSupplierBakMonth(baseSupplierContractBak);
    }

    //根据服务机构id查询合约信息
    @Override
    public List<BaseSupplierContractBak> selectBaseproviderBakCode(String providerCode) {
        return baseSupplierContractBakMapper.selectBaseproviderBakCode(providerCode);
    }



    /**
     * 新增base_supplier_contract_bak（供应商合约）
     * 
     * @param baseSupplierContractBak base_supplier_contract_bak（供应商合约）
     * @return 结果
     */
    @Override
    public BaseSupplierContractBak insertBaseSupplierContractBak(BaseSupplierContractBak baseSupplierContractBak)
    {
        baseSupplierContractBak.setCreateBy(SecurityUtils.getUsername());
        baseSupplierContractBak.setCreateTime(new Date());
        baseSupplierContractBak.setUpdateBy(SecurityUtils.getUsername());
        baseSupplierContractBak.setUpdateTime(new Date());
        baseSupplierContractBak.setStatus("Y");
        baseSupplierContractBak.setSerialNo(PubFun.createMySqlMaxNoUseCache("BaseSupplierContractBakSerialNo", 0, 11));
//        String contractNo = "SPC" + PubFun.createMySqlMaxNoUseCache("BaseSupplierContract", 0, 9);
//        baseSupplierContractBak.setContractNo(contractNo);

        String contractNo = "SPC" + PubFun.createMySqlMaxNoUseCache("BaseSupplierContract", 0, 10);
        //生成合约名称
        String name = this.generatorContractName(contractNo,"contract_type",baseSupplierContractBak.getContractType());
        baseSupplierContractBak.setContractName(name);
        baseSupplierContractBak.setContractNo(contractNo);

        baseSupplierContractBakMapper.insertBaseSupplierContractBak(baseSupplierContractBak);
        return baseSupplierContractBak;
    }

    /**
     * 修改base_supplier_contract_bak（供应商合约）
     * 
     * @param baseSupplierContractBak base_supplier_contract_bak（供应商合约）
     * @return 结果
     */
    @Override
    public BaseSupplierContractBak updateBaseSupplierContractBak(BaseSupplierContractBak baseSupplierContractBak)
    {

        String contractNo = baseSupplierContractBak.getContractNo();
        //生成合约名称
        String name = this.generatorContractName(contractNo,"contract_type",baseSupplierContractBak.getContractType());
        baseSupplierContractBak.setContractName(name);
        baseSupplierContractBak.setCreateBy(SecurityUtils.getUsername());
        baseSupplierContractBak.setCreateTime(new Date());
        baseSupplierContractBak.setUpdateBy(SecurityUtils.getUsername());
        baseSupplierContractBak.setUpdateTime(new Date());
        baseSupplierContractBak.setStatus("Y");
//            String contractNo = "SPC" + PubFun.createMySqlMaxNoUseCache("BaseSupplierContract", 0, 9);
//            baseSupplierContractBak.setContractNo(contractNo);
//            baseSupplierContractBak.setSerialNo(PubFun.createMySqlMaxNoUseCache("BaseSupplierContractBakSerialNo", 0, 11));
        int result = baseSupplierContractBakMapper.insertBaseSupplierContractBak(baseSupplierContractBak);
        return baseSupplierContractBak;
    }

    /**
     *修改服务机构历史合约
     */
    public int updateHistory(BaseSupplierContractBak baseSupplierContractBak)
    {
        baseSupplierContractBak.setUpdateBy(SecurityUtils.getUsername());
        baseSupplierContractBak.setUpdateTime(new Date());
        return  baseSupplierContractBakMapper.updateHistory(baseSupplierContractBak);
    }


    /**
     * 批量删除base_supplier_contract_bak（供应商合约）
     * 
     * @param serialNos 需要删除的base_supplier_contract_bak（供应商合约）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierContractBakByIds(String[] serialNos)
    {
        return baseSupplierContractBakMapper.deleteBaseSupplierContractBakByIds(serialNos);
    }

    /**
     * 删除base_supplier_contract_bak（供应商合约）信息
     * 
     * @param serialNo base_supplier_contract_bak（供应商合约）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierContractBakById(String serialNo)
    {
        return baseSupplierContractBakMapper.deleteBaseSupplierContractBakById(serialNo);
    }

    private String generatorContractName(String id, String dictType, String dictValue){

        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("dictType",dictType);
        map.put("dictValue",dictValue);
        return baseSupplierContractMapper.generatorContractName(map);
    }
}

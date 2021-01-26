package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseSupplierContractBak;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * base_supplier_contract_bak（供应商合约）Mapper接口
 * 
 * @author sino
 * @date 2021-01-08
 */
@Component
public interface BaseSupplierContractBakMapper 
{
    /**
     * 查询base_supplier_contract_bak（供应商合约）
     * 
     * @param serialNo base_supplier_contract_bak（供应商合约）ID
     * @return base_supplier_contract_bak（供应商合约）
     */
    public BaseSupplierContractBak selectBaseSupplierContractBakById(String serialNo);

    /**
     * 查询base_supplier_contract_bak（供应商合约）列表
     * 
     * @param baseSupplierContractBak base_supplier_contract_bak（供应商合约）
     * @return base_supplier_contract_bak（供应商合约）集合
     */
    public List<BaseSupplierContractBak> selectBaseSupplierContractBakList(BaseSupplierContractBak baseSupplierContractBak);


    /**若服务商下已签订合约，合约列表中服务商对应的合约编码、合约名称、合约起止日期均有值，
     *且当服务商下存在多条合约信息时仅显示该服务商下合约终止日期最晚的一条合约信息（即服务商下创建时间最晚的一条合约信息）
     **/
    public  List<BaseSupplierContractBak> selectBaseSupplierBakLast(String providercode);


    //供应商合约管理主查询页面需默认显示截止当前时间合约签约时间在三个月内且合约状态为“有效”的数据
    public  List<BaseSupplierContractBak> selectBaseSupplierBakMonth(BaseSupplierContractBak baseSupplierContractBak);

    //根据服务机构id查询合约信息
    public List<BaseSupplierContractBak> selectBaseproviderBakCode(String providerCode);

    /**
     * 新增base_supplier_contract_bak（供应商合约）
     * 
     * @param baseSupplierContractBak base_supplier_contract_bak（供应商合约）
     * @return 结果
     */
    public int insertBaseSupplierContractBak(BaseSupplierContractBak baseSupplierContractBak);

    /**
     * 修改base_supplier_contract_bak（供应商合约）
     * 
     * @param baseSupplierContractBak base_supplier_contract_bak（供应商合约）
     * @return 结果
     */
    public int updateBaseSupplierContractBak(BaseSupplierContractBak baseSupplierContractBak);

    /**
     * 删除base_supplier_contract_bak（供应商合约）
     * 
     * @param serialNo base_supplier_contract_bak（供应商合约）ID
     * @return 结果
     */
    public int deleteBaseSupplierContractBakById(String serialNo);

    /**
     * 批量删除base_supplier_contract_bak（供应商合约）
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseSupplierContractBakByIds(String[] serialNos);
}

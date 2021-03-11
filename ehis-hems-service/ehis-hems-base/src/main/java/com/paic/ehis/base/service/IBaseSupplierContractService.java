package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseSupplierContract;

import java.util.List;

/**
 * base_supplier_contract（供应商合约）Service接口
 * 
 * @author sino
 * @date 2020-12-31
 */
public interface IBaseSupplierContractService 
{
    /**
     * 查询base_supplier_contract（供应商合约）
     * 
     * @param contractNo base_supplier_contract（供应商合约）ID
     * @return base_supplier_contract（供应商合约）
     */
    public BaseSupplierContract selectBaseSupplierContractById(String contractNo);

    /**
     * 查询base_supplier_contract（供应商合约）列表
     * 
     * @param baseSupplierContract base_supplier_contract（供应商合约）
     * @return base_supplier_contract（供应商合约）集合
     */
    public List<BaseSupplierContract> selectBaseSupplierContractList(BaseSupplierContract baseSupplierContract);


    /**
     * 若供应商下已签订合约，合约列表中供应商对应的合约编码、合约名称、合约起止日期均有值，
     * 且当供应商下存在多条合约信息时仅显示该供应商下合约终止日期最晚的一条合约信息（即供应商下创建时间最晚的一条合约信息）
     */
    public  List<BaseSupplierContract> selectBaseSupplierLast(String servcomno);

    /**
     * 供应商合约管理主查询页面需默认显示截止当前时间合约签约时间在三个月内且合约状态为“有效”的数据
     */
    public List<BaseSupplierContract> selectBaseSupplierMonth(BaseSupplierContract baseSupplierContract) throws Exception;


    /**
     * 根据服务机构id查询合约信息
     */
    public List<BaseSupplierContract> selectBaseproviderCode(String providerCode);


    /**
     * 新增base_supplier_contract（供应商合约）
     * 
     * @param baseSupplierContract base_supplier_contract（供应商合约）
     * @return 结果
     */
    public BaseSupplierContract insertBaseSupplierContract(BaseSupplierContract baseSupplierContract);

    public int insertBaseSupplierContractNew(String providerCode);

    /**
     * 修改base_supplier_contract（供应商合约）
     * 
     * @param baseSupplierContract base_supplier_contract（供应商合约）
     * @return 结果
     */
    public BaseSupplierContract updateBaseSupplierContract(BaseSupplierContract baseSupplierContract);

    /**
     * 批量删除base_supplier_contract（供应商合约）
     * 
     * @param contractNos 需要删除的base_supplier_contract（供应商合约）ID
     * @return 结果
     */
    public int deleteBaseSupplierContractByIds(String[] contractNos);

    /**
     * 删除base_supplier_contract（供应商合约）信息
     * 
     * @param contractNo base_supplier_contract（供应商合约）ID
     * @return 结果
     */
    public int deleteBaseSupplierContractById(String contractNo);

    /**
     * 合约到期后，每天晚上12:00，通过批处理的方式将合约的状态修改为失效
     */
    public List<BaseSupplierContract> batchTimeBaseSupplierContract (BaseSupplierContract baseSupplierContract);

}

package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseContractAnnex;

import java.util.List;

/**
 * base_contract_annex（合约附件）Service接口
 * 
 * @author sino
 * @date 2020-12-31
 */
public interface IBaseContractAnnexService 
{
    /**
     * 查询base_contract_annex（合约附件）
     * 
     * @param contractNo base_contract_annex（合约附件）ID
     * @return base_contract_annex（合约附件）
     */
    public BaseContractAnnex selectBaseContractAnnexById(String contractNo);

    /**
     * 查询base_contract_annex（合约附件）列表
     * 
     * @param baseContractAnnex base_contract_annex（合约附件）
     * @return base_contract_annex（合约附件）集合
     */
    public List<BaseContractAnnex> selectBaseContractAnnexList(BaseContractAnnex baseContractAnnex);

    /**
     * 新增base_contract_annex（合约附件）
     * 
     * @param baseContractAnnex base_contract_annex（合约附件）
     * @return 结果
     */
    public int insertBaseContractAnnex(BaseContractAnnex baseContractAnnex);

    /**
     * 修改base_contract_annex（合约附件）
     * 
     * @param baseContractAnnex base_contract_annex（合约附件）
     * @return 结果
     */
    public int updateBaseContractAnnex(BaseContractAnnex baseContractAnnex);

    /**
     * 批量删除base_contract_annex（合约附件）
     * 
     * @param contractNos 需要删除的base_contract_annex（合约附件）ID
     * @return 结果
     */
    public int deleteBaseContractAnnexByIds(String[] contractNos);

    /**
     * 删除base_contract_annex（合约附件）信息
     * 
     * @param contractNo base_contract_annex（合约附件）ID
     * @return 结果
     */
    public int deleteBaseContractAnnexById(String contractNo);
}

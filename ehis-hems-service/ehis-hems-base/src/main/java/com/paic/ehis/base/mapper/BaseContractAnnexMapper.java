package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseContractAnnex;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
/**
 * base_contract_annex（合约附件）Mapper接口
 * 
 * @author sino
 * @date 2020-12-31
 */
public interface BaseContractAnnexMapper 
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
     * 删除base_contract_annex（合约附件）
     * 
     * @param contractNo base_contract_annex（合约附件）ID
     * @return 结果
     */
    public int deleteBaseContractAnnexById(String contractNo);

    /**
     * 批量删除base_contract_annex（合约附件）
     * 
     * @param contractNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseContractAnnexByIds(String[] contractNos);
}

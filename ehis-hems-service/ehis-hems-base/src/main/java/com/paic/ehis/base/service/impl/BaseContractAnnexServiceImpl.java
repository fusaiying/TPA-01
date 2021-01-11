package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.service.IBaseContractAnnexService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseContractAnnex;
import com.paic.ehis.base.mapper.BaseContractAnnexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base_contract_annex（合约附件）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-31
 */
@Service
public class BaseContractAnnexServiceImpl implements IBaseContractAnnexService
{
    @Autowired
    private BaseContractAnnexMapper baseContractAnnexMapper;

    /**
     * 查询base_contract_annex（合约附件）
     * 
     * @param contractNo base_contract_annex（合约附件）ID
     * @return base_contract_annex（合约附件）
     */
    @Override
    public BaseContractAnnex selectBaseContractAnnexById(String contractNo)
    {
        return baseContractAnnexMapper.selectBaseContractAnnexById(contractNo);
    }

    /**
     * 查询base_contract_annex（合约附件）列表
     * 
     * @param baseContractAnnex base_contract_annex（合约附件）
     * @return base_contract_annex（合约附件）
     */
    @Override
    public List<BaseContractAnnex> selectBaseContractAnnexList(BaseContractAnnex baseContractAnnex)
    {
        return baseContractAnnexMapper.selectBaseContractAnnexList(baseContractAnnex);
    }

    /**
     * 新增base_contract_annex（合约附件）
     * 
     * @param baseContractAnnex base_contract_annex（合约附件）
     * @return 结果
     */
    @Override
    public int insertBaseContractAnnex(BaseContractAnnex baseContractAnnex)
    {
        baseContractAnnex.setCreateTime(DateUtils.getNowDate());
        return baseContractAnnexMapper.insertBaseContractAnnex(baseContractAnnex);
    }

    /**
     * 修改base_contract_annex（合约附件）
     * 
     * @param baseContractAnnex base_contract_annex（合约附件）
     * @return 结果
     */
    @Override
    public int updateBaseContractAnnex(BaseContractAnnex baseContractAnnex)
    {
        baseContractAnnex.setUpdateTime(DateUtils.getNowDate());
        return baseContractAnnexMapper.updateBaseContractAnnex(baseContractAnnex);
    }

    /**
     * 批量删除base_contract_annex（合约附件）
     * 
     * @param contractNos 需要删除的base_contract_annex（合约附件）ID
     * @return 结果
     */
    @Override
    public int deleteBaseContractAnnexByIds(String[] contractNos)
    {
        return baseContractAnnexMapper.deleteBaseContractAnnexByIds(contractNos);
    }

    /**
     * 删除base_contract_annex（合约附件）信息
     * 
     * @param contractNo base_contract_annex（合约附件）ID
     * @return 结果
     */
    @Override
    public int deleteBaseContractAnnexById(String contractNo)
    {
        return baseContractAnnexMapper.deleteBaseContractAnnexById(contractNo);
    }
}

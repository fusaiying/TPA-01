package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseBank;
import com.paic.ehis.base.domain.vo.BaseBankVo;

import java.util.List;


/**
 * base_bank（银行信息）Service接口
 * 
 * @author sino
 * @date 2020-12-28
 */
public interface IBaseBankService 
{
    /**
     * 查询base_bank（银行信息）
     * 
     * @param id base_bank（银行信息）ID
     * @return base_bank（银行信息）
     */
    public BaseBank selectBaseBankById(String id);


    /**
     * 查询base_bank（银行信息）
     *
     * @param providerCode base_bank（银行信息）ID
     * @return base_bank（银行信息）
     */
    public BaseBankVo selectBaseBankByCode(String providerCode);

    /**
     * 查询base_bank（银行信息）列表
     * 
     * @param baseBank base_bank（银行信息）
     * @return base_bank（银行信息）集合
     */
    public List<BaseBank> selectBaseBankList(BaseBank baseBank);

    /**
     * 新增base_bank（银行信息）
     * 
     * @param baseBank base_bank（银行信息）
     * @return 结果
     */
    public int insertBaseBank(BaseBankVo baseBank);

    /**
     * 修改base_bank（银行信息）
     * 
     * @param baseBank base_bank（银行信息）
     * @return 结果
     */
    public int updateBaseBank(BaseBank baseBank);

    /**
     * 批量删除base_bank（银行信息）
     * 
     * @param ids 需要删除的base_bank（银行信息）ID
     * @return 结果
     */
    public int deleteBaseBankByIds(String[] ids);

    /**
     * 删除base_bank（银行信息）信息
     * 
     * @param id base_bank（银行信息）ID
     * @return 结果
     */
    public int deleteBaseBankById(String id);
}

package com.paic.ehis.base.service.impl;

import java.util.List;

import com.paic.ehis.base.domain.BaseBank;
import com.paic.ehis.base.domain.BaseProviderSettle;
import com.paic.ehis.base.domain.vo.BaseBankVo;
import com.paic.ehis.base.mapper.BaseBankMapper;
import com.paic.ehis.base.mapper.BaseProviderSettleMapper;
import com.paic.ehis.base.service.IBaseBankService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * base_bank（银行信息）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-28
 */
@Service
public class BaseBankServiceImpl implements IBaseBankService
{
    @Autowired
    private BaseBankMapper baseBankMapper;

    @Autowired
    private BaseProviderSettleMapper baseProviderSettleMapper;

    /**
     * 查询base_bank（银行信息）
     * 
     * @param id base_bank（银行信息）ID
     * @return base_bank（银行信息）
     */
    @Override
    public BaseBank selectBaseBankById(String id)
    {
        return baseBankMapper.selectBaseBankById(id);
    }



    @Override
    public BaseBankVo selectBaseBankByCode(String providerCode)
    {

        BaseBankVo baseBankVo = new BaseBankVo();
        baseBankVo.setBaseProviderSettle(baseProviderSettleMapper.selectBaseProviderSettleById(providerCode).get(0));
        baseBankVo.setBaseBankVo(baseBankMapper.selectBaseBankByCode(providerCode));
        return baseBankVo;
    }

    /**
     * 查询base_bank（银行信息）列表
     * 
     * @param baseBank base_bank（银行信息）
     * @return base_bank（银行信息）
     */
    @Override
    public List<BaseBank> selectBaseBankList(BaseBank baseBank)
    {
        return baseBankMapper.selectBaseBankList(baseBank);
    }

    /**
     * 新增base_bank（银行信息）
     * 
     * @param baseBanks base_bank（银行信息）
     * @return 结果
     */
    @Override
    public int insertBaseBank(BaseBankVo baseBanks)
    {
        int count=0;
        BaseProviderSettle baseProviderSettle  = baseBanks.getBaseProviderSettle();
        baseProviderSettleMapper.deleteBaseProviderSettleById(baseProviderSettle.getProviderCode());
        baseBankMapper.deleteBaseBankByCode(baseProviderSettle.getProviderCode());
        baseProviderSettle.setStatus("Y");
        baseProviderSettleMapper.insertBaseProviderSettle(baseProviderSettle);
        for(BaseBank baseBank : baseBanks.getBaseBankVo()){
            baseBank.setCreateTime(DateUtils.getNowDate());
            baseBank.setStatus("Y");
            int i = baseBankMapper.insertBaseBank(baseBank);
            count += i;
        }

        return count;
    }

    /**
     * 修改base_bank（银行信息）
     * 
     * @param baseBank base_bank（银行信息）
     * @return 结果
     */
    @Override
    public int updateBaseBank(BaseBank baseBank)
    {
        baseBank.setUpdateTime(DateUtils.getNowDate());
        return baseBankMapper.updateBaseBank(baseBank);
    }

    /**
     * 批量删除base_bank（银行信息）
     * 
     * @param ids 需要删除的base_bank（银行信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseBankByIds(String[] ids)
    {
        return baseBankMapper.deleteBaseBankByIds(ids);
    }

    /**
     * 删除base_bank（银行信息）信息
     * 
     * @param id base_bank（银行信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseBankById(String id)
    {
        return baseBankMapper.deleteBaseBankById(id);
    }
}

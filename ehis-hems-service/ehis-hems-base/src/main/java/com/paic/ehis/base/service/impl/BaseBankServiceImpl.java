package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseBank;
import com.paic.ehis.base.domain.BaseProviderSettle;
import com.paic.ehis.base.domain.vo.BaseBankVo;
import com.paic.ehis.base.mapper.BaseBankMapper;
import com.paic.ehis.base.mapper.BaseProviderSettleMapper;
import com.paic.ehis.base.service.IBaseBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * base_bank（银行信息）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-28
 */
@Service
@Transactional
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
    public BaseBankVo selectBaseBankByCode(BaseBankVo baseBankinfo)
    {
        BaseBankVo baseBankVo = new BaseBankVo();
        if("01".equals(baseBankinfo.getOrgFlag())){
            if(baseProviderSettleMapper.selectBaseProviderSettleById(baseBankinfo.getProviderCode())!=null){
                baseBankVo.setBaseProviderSettle(baseProviderSettleMapper.selectBaseProviderSettleById((baseBankinfo.getProviderCode())));
            }
            if(!baseBankMapper.selectBaseBankByCode(baseBankinfo.getProviderCode()).isEmpty()){
                baseBankVo.setBaseBankVo(baseBankMapper.selectBaseBankByCode(baseBankinfo.getProviderCode()));
            }
        }else if("02".equals(baseBankinfo.getOrgFlag())){
            if(baseProviderSettleMapper.selectBaseProviderSettleByIdNew(baseBankinfo.getProviderCode())!=null){
                baseBankVo.setBaseProviderSettle(baseProviderSettleMapper.selectBaseProviderSettleByIdNew((baseBankinfo.getProviderCode())));
            }
            if(!baseBankMapper.selectBaseBankByCodeNew(baseBankinfo.getProviderCode()).isEmpty()){
                baseBankVo.setBaseBankVo(baseBankMapper.selectBaseBankByCodeNew(baseBankinfo.getProviderCode()));
            }
        }

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

    @Override
    public List<BaseBank> selectBaseBankList1(BaseBank baseBank) {
        return baseBankMapper.selectBaseBankList1(baseBank);
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
        int i =0;
        BaseProviderSettle baseProviderSettle  = baseBanks.getBaseProviderSettle();
        if("01".equals(baseBanks.getOrgFlag())){
            baseProviderSettleMapper.updateBaseProviderSettleByCode(baseBanks.getProviderCode());
        }else if("02".equals(baseBanks.getOrgFlag())){
            baseProviderSettleMapper.updateBaseProviderSettleByCodeNew(baseBanks.getProviderCode());
        }
        baseProviderSettle.setSerialNo(PubFun.createMySqlMaxNoUseCache("settleSer",12,12));
        baseProviderSettle.setCreateTime(DateUtils.getNowDate());
        baseProviderSettle.setUpdateTime(DateUtils.getNowDate());
        baseProviderSettle.setCreateBy(SecurityUtils.getUsername());
        baseProviderSettle.setUpdateBy(SecurityUtils.getUsername());
        baseProviderSettle.setStatus("Y");
        baseProviderSettle.setUpdateFlag("0");
        baseProviderSettle.setProviderCode(baseBanks.getProviderCode());
        if("01".equals(baseBanks.getOrgFlag())){
            i = baseProviderSettleMapper.insertBaseProviderSettle(baseProviderSettle);
            baseBankMapper.updateBaseBankByCode(baseBanks.getProviderCode());
        }else if("02".equals(baseBanks.getOrgFlag())){
            i = baseProviderSettleMapper.insertBaseProviderSettleNew(baseProviderSettle);
            baseBankMapper.updateBaseBankByCodeNew(baseBanks.getProviderCode());
        }

        count += i;
        //List<BaseBank> baseBankinfos = baseBankMapper.selectBaseBankByCode(baseProviderSettle.getProviderCode());


        if(!baseBanks.getBaseBankVo().isEmpty()){
            for(BaseBank baseBank : baseBanks.getBaseBankVo()){
                baseBank.setSerialNo(PubFun.createMySqlMaxNoUseCache("bankSer",12,12));
                baseBank.setProviderCode(baseBanks.getProviderCode());
                baseBank.setUpdateFlag("0");
                baseBank.setStatus("Y");
                baseBank.setCreateTime(DateUtils.getNowDate());
                baseBank.setUpdateTime(DateUtils.getNowDate());
                baseBank.setCreateBy(SecurityUtils.getUsername());
                baseBank.setUpdateBy(SecurityUtils.getUsername());
                if("01".equals(baseBanks.getOrgFlag())){
                    baseBankMapper.insertBaseBank1(baseBank);
                }else if("02".equals(baseBanks.getOrgFlag())){
                    baseBankMapper.insertBaseBankNew(baseBank);
                }

            }
        } else {
            count = 1;
        }


        return count;
    }
    @Override
    public int addBaseBank(List<BaseBank> baseBankList) {
        int count = 0;
        if (!baseBankList.isEmpty()) {
            baseBankMapper.updatebaseBankStatus(baseBankList.get(0).getProviderCode());
            for (BaseBank baseBank : baseBankList) {
                baseBank.setCreateTime(DateUtils.getNowDate());
                baseBank.setStatus("Y");
                baseBank.setSerialNo(PubFun.createMySqlMaxNoUseCache("bankSer",12,12));
                int i = baseBankMapper.insertBaseBank(baseBank);
                count += i;
            }
        } else {
            for (BaseBank baseBank : baseBankList) {
                baseBank.setCreateTime(DateUtils.getNowDate());
                baseBank.setStatus("Y");
                baseBank.setSerialNo(PubFun.createMySqlMaxNoUseCache("bankSer",12,12));
                int i = baseBankMapper.insertBaseBank(baseBank);
                count += i;
            }
        }
        return count;
    }


    /**
     * 新增base_bank（银行信息）
     *
     * @param providerCode base_bank（银行信息）
     * @return 结果
     */
    @Override
    public int insertBaseBankNew(String providerCode)
    {
        int count=0;
        BaseProviderSettle baseProviderSettleinfo = baseProviderSettleMapper.selectBaseProviderSettleById(providerCode);
        BaseProviderSettle baseProviderSettleinfoNew = baseProviderSettleMapper.selectBaseProviderSettleByIdNew(providerCode);
        if(baseProviderSettleinfoNew != null){ //存在则修改已存在的数据
            baseProviderSettleMapper.updateBaseProviderSettleByCodeNew(providerCode);
        }
        baseProviderSettleinfo.setCreateTime(DateUtils.getNowDate());
        baseProviderSettleMapper.insertBaseProviderSettleNew(baseProviderSettleinfo);

        List<BaseBank> BaseBankinfos = baseBankMapper.selectBaseBankByCode(providerCode);
        List<BaseBank> BaseBankinfosNew = baseBankMapper.selectBaseBankByCodeNew(providerCode);
        if(!BaseBankinfosNew.isEmpty()){
            baseBankMapper.updateBaseBankByCodeNew(providerCode);
        }

        for(BaseBank baseBank : BaseBankinfos){
            baseBank.setCreateTime(DateUtils.getNowDate());
            int i = baseBankMapper.insertBaseBankNew(baseBank);
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

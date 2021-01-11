package com.paic.ehis.base.service.impl;

import java.util.List;

import com.paic.ehis.base.mapper.BaseProviderDepMapper;
import com.paic.ehis.base.service.IBaseProviderDepService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.base.domain.BaseProviderDep;

/**
 * base_provider_dept(服务商科室)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
public class BaseProviderDepServiceImpl implements IBaseProviderDepService
{
    @Autowired
    private BaseProviderDepMapper baseProviderDepMapper;

    /**
     * 查询base_provider_dept(服务商科室)
     * 
     * @param id base_provider_dept(服务商科室)ID
     * @return base_provider_dept(服务商科室)
     */
    @Override
    public BaseProviderDep selectBaseProviderDepById(String id)
    {
        return baseProviderDepMapper.selectBaseProviderDepById(id);
    }

    /**
     * 查询base_provider_dept(服务商科室)列表
     * 
     * @param baseProviderDep base_provider_dept(服务商科室)
     * @return base_provider_dept(服务商科室)
     */
    @Override
    public List<BaseProviderDep> selectBaseProviderDepList(BaseProviderDep baseProviderDep)
    {
        return baseProviderDepMapper.selectBaseProviderDepList(baseProviderDep);
    }

    /**
     * 新增base_provider_dept(服务商科室)
     * 
     * @param baseProviderDepVo base_provider_dept(服务商科室)
     * @return 结果
     */
    @Override
    public int insertBaseProviderDep(List<BaseProviderDep> baseProviderDepVo)
    {
        //String providercode = baseProviderDepVo.getProvidercode();
       // List<BaseProviderDep> baseProviderDeps= baseProviderDepVo.getBaseProviderDeps();
        baseProviderDepMapper.deleteBaseProviderDepByCode(baseProviderDepVo.get(0).getProviderCode());
        int count = 0;
        for(BaseProviderDep baseProviderDep :baseProviderDepVo){
            baseProviderDep.setCreateTime(DateUtils.getNowDate());
            baseProviderDep.setId(PubFun.createMySqlMaxNoUseCache("depid",10,9));
            //baseProviderDep.setProvidercode(providercode);
            int i =baseProviderDepMapper.insertBaseProviderDep(baseProviderDep);
            count += i;
        }

        return count;
    }

    /**
     * 修改base_provider_dept(服务商科室)
     * 
     * @param baseProviderDep base_provider_dept(服务商科室)
     * @return 结果
     */
    @Override
    public int updateBaseProviderDep(BaseProviderDep baseProviderDep)
    {
        baseProviderDep.setUpdateTime(DateUtils.getNowDate());
        return baseProviderDepMapper.updateBaseProviderDep(baseProviderDep);
    }

    /**
     * 批量删除base_provider_dept(服务商科室)
     * 
     * @param ids 需要删除的base_provider_dept(服务商科室)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderDepByIds(String[] ids)
    {
        return baseProviderDepMapper.deleteBaseProviderDepByIds(ids);
    }

    /**
     * 删除base_provider_dept(服务商科室)信息
     * 
     * @param id base_provider_dept(服务商科室)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderDepById(String id)
    {
        return baseProviderDepMapper.deleteBaseProviderDepById(id);
    }


    /**
     * 查询base_provider_dept(服务商科室)
     *
     * @param providercode base_provider_dept(服务商科室)
     * @return base_provider_dept(服务商科室)
     */
    @Override
    public List<BaseProviderDep> selectBaseProviderDepByCode(String providercode)
    {
        return baseProviderDepMapper.selectBaseProviderDepByCode(providercode);
    }
}

package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.base.domain.BaseProviderDep;
import com.paic.ehis.base.domain.vo.BaseProviderDepVo;
import com.paic.ehis.base.mapper.BaseProviderDepMapper;
import com.paic.ehis.base.service.IBaseProviderDepService;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * base_provider_dept(服务商科室)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
@Transactional
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
        List<BaseProviderDep> baseProviderDepinfo = new ArrayList();
        if("01".equals(baseProviderDep.getOrgFlag())){
            baseProviderDepinfo = baseProviderDepMapper.selectBaseProviderDepList(baseProviderDep);
        }else if("02".equals(baseProviderDep.getOrgFlag())){
            baseProviderDepinfo = baseProviderDepMapper.selectBaseProviderDepByCodeNew(baseProviderDep.getProviderCode());
        }

        return baseProviderDepinfo;
    }

    /**
     * 新增base_provider_dept(服务商科室)
     * 
     * @param baseProviderDepVo base_provider_dept(服务商科室)
     * @return 结果
     */
    @Override
    public int insertBaseProviderDep(BaseProviderDepVo baseProviderDepVo)
    {
        //List<BaseProviderDep> baseProviderDepinfos =baseProviderDepMapper.selectBaseProviderDepByCode(baseProviderDepVo.get(0).getProviderCode());
        if("01".equals(baseProviderDepVo.getOrgFlag())){
            baseProviderDepMapper.updateBaseProviderDepByCode(baseProviderDepVo.getProviderCode());
        }else if("02".equals(baseProviderDepVo.getOrgFlag())){
            baseProviderDepMapper.updateBaseProviderDepByCodeNew(baseProviderDepVo.getProviderCode());

        }
        int count = 0;
        int i =0;
        if(!baseProviderDepVo.getForm().isEmpty()){
            for(BaseProviderDep baseProviderDep :baseProviderDepVo.getForm()){
                baseProviderDep.setCreateTime(DateUtils.getNowDate());
                baseProviderDep.setUpdateTime(DateUtils.getNowDate());
                baseProviderDep.setCreateBy(SecurityUtils.getUsername());
                baseProviderDep.setUpdateBy(SecurityUtils.getUsername());
                baseProviderDep.setUpdateFlag("0");
                baseProviderDep.setStatus("Y");
                baseProviderDep.setProviderCode(baseProviderDepVo.getProviderCode());
                baseProviderDep.setSerialNo(PubFun.createMySqlMaxNoUseCache("depSer",10,9));
                if("01".equals(baseProviderDepVo.getOrgFlag())){
                    i =baseProviderDepMapper.insertBaseProviderDep(baseProviderDep);
                }else if("02".equals(baseProviderDepVo.getOrgFlag())){
                    i =baseProviderDepMapper.insertBaseProviderDepNew(baseProviderDep);
                }
                count += i;
            }
        }else{
            count = 1;
        }
        return count;
    }


    /**
     * 新增base_provider_dept(服务商科室)
     *
     * @param providerCode base_provider_dept(服务商科室)
     * @return 结果
     */
    @Override
    public int insertBaseProviderDepNew(String providerCode)
    {

        List<BaseProviderDep> baseProviderDepinfos =baseProviderDepMapper.selectBaseProviderDepByCode(providerCode);
        List<BaseProviderDep> baseProviderDepinfosNew =baseProviderDepMapper.selectBaseProviderDepByCodeNew(providerCode);
        int count = 0;
        if(!baseProviderDepinfosNew.isEmpty()){
            baseProviderDepMapper.updateBaseProviderDepByCodeNew(providerCode);
        }
        if(!baseProviderDepinfos.isEmpty()){
            for(BaseProviderDep baseProviderDep :baseProviderDepinfos){
                baseProviderDep.setCreateTime(DateUtils.getNowDate());
                int i =baseProviderDepMapper.insertBaseProviderDepNew(baseProviderDep);
                count += i;
            }
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
     * @param baseProviderDep base_provider_dept(服务商科室)
     * @return base_provider_dept(服务商科室)
     */
    @Override
    public List<BaseProviderDep> selectBaseProviderDepByCode(BaseProviderDep baseProviderDep)
    {
        List<BaseProviderDep> baseProviderDepinfo = new ArrayList();
        if("01".equals(baseProviderDep.getOrgFlag())){
            baseProviderDepinfo = baseProviderDepMapper.selectBaseProviderDepList(baseProviderDep);
        }else if("02".equals(baseProviderDep.getOrgFlag())){
            baseProviderDepinfo = baseProviderDepMapper.selectBaseProviderDepByCodeNew(baseProviderDep.getProviderCode());
        }

        return baseProviderDepinfo;
    }
}

package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.HmpPersonSrevTouser;
import com.paic.ehis.system.mapper.HmpPersonSrevTouserMapper;
import com.paic.ehis.system.service.IHmpPersonSrevTouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 就诊人服务与员工关联Service业务层处理
 * 
 * @author sino
 * @date 2020-11-12
 */
@Service
public class HmpPersonSrevTouserServiceImpl implements IHmpPersonSrevTouserService
{
    @Autowired
    private HmpPersonSrevTouserMapper hmpPersonSrevTouserMapper;

    /**
     * 查询就诊人服务与员工关联
     * 
     * @param personcode 就诊人服务与员工关联ID
     * @return 就诊人服务与员工关联
     */
    @Override
    public HmpPersonSrevTouser selectHmpPersonSrevTouserById(String personcode)
    {
        return hmpPersonSrevTouserMapper.selectHmpPersonSrevTouserById(personcode);
    }

    /**
     * 查询就诊人服务与员工关联列表
     * 
     * @param hmpPersonSrevTouser 就诊人服务与员工关联
     * @return 就诊人服务与员工关联
     */
    @Override
    public List<HmpPersonSrevTouser> selectHmpPersonSrevTouserList(HmpPersonSrevTouser hmpPersonSrevTouser)
    {
        return hmpPersonSrevTouserMapper.selectHmpPersonSrevTouserList(hmpPersonSrevTouser);
    }

    /**
     * 新增就诊人服务与员工关联
     * 
     * @param hmpPersonSrevTouser 就诊人服务与员工关联
     * @return 结果
     */
    @Override
    public int insertHmpPersonSrevTouser(HmpPersonSrevTouser hmpPersonSrevTouser)
    {
        return hmpPersonSrevTouserMapper.insertHmpPersonSrevTouser(hmpPersonSrevTouser);
    }

    /**
     * 修改就诊人服务与员工关联
     * 
     * @param hmpPersonSrevTouser 就诊人服务与员工关联
     * @return 结果
     */
    @Override
    public int updateHmpPersonSrevTouser(HmpPersonSrevTouser hmpPersonSrevTouser)
    {
        return hmpPersonSrevTouserMapper.updateHmpPersonSrevTouser(hmpPersonSrevTouser);
    }

    /**
     * 批量删除就诊人服务与员工关联
     * 
     * @param personcodes 需要删除的就诊人服务与员工关联ID
     * @return 结果
     */
    @Override
    public int deleteHmpPersonSrevTouserByIds(String[] personcodes)
    {
        return hmpPersonSrevTouserMapper.deleteHmpPersonSrevTouserByIds(personcodes);
    }

    /**
     * 删除就诊人服务与员工关联信息
     * 
     * @param personcode 就诊人服务与员工关联ID
     * @return 结果
     */
    @Override
    public int deleteHmpPersonSrevTouserById(String personcode)
    {
        return hmpPersonSrevTouserMapper.deleteHmpPersonSrevTouserById(personcode);
    }
}

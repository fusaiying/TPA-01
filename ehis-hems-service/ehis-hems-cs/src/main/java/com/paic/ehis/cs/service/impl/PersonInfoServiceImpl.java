package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.mapper.PersonInfoMapper;
import com.paic.ehis.cs.service.IPersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 人员信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-21
 */
@Service
public class PersonInfoServiceImpl implements IPersonInfoService
{
    @Autowired
    private PersonInfoMapper personInfoMapper;

    /**
     * 查询人员信息 
     * 
     * @param personId 人员信息 ID
     * @return 人员信息 
     */
    @Override
    public PersonInfo selectPersonInfoById(String personId)
    {
        return personInfoMapper.selectPersonInfoById(personId);
    }

    /**
     * 查询人员信息 列表
     * 
     * @param personInfo 人员信息 
     * @return 人员信息 
     */
    @Override
    public List<PersonInfo> selectPersonInfoList(PersonInfo personInfo)
    {
        return personInfoMapper.selectPersonInfoList(personInfo);
    }

    /**
     * 新增人员信息 
     * 
     * @param personInfo 人员信息 
     * @return 结果
     */
    @Override
    public int insertPersonInfo(PersonInfo personInfo)
    {
        return personInfoMapper.insertPersonInfo(personInfo);
    }

    /**
     * 修改人员信息 
     * 
     * @param personInfo 人员信息 
     * @return 结果
     */
    @Override
    public int updatePersonInfo(PersonInfo personInfo)
    {
        return personInfoMapper.updatePersonInfo(personInfo);
    }

    /**
     * 批量删除人员信息 
     * 
     * @param personIds 需要删除的人员信息 ID
     * @return 结果
     */
    @Override
    public int deletePersonInfoByIds(String[] personIds)
    {
        return personInfoMapper.deletePersonInfoByIds(personIds);
    }

    /**
     * 删除人员信息 信息
     * 
     * @param personId 人员信息 ID
     * @return 结果
     */
    @Override
    public int deletePersonInfoById(String personId)
    {
        return personInfoMapper.deletePersonInfoById(personId);
    }
}

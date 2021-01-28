package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.PersonInfo;

import java.util.List;


/**
 * 人员信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface PersonInfoMapper 
{
    /**
     * 查询人员信息 
     * 
     * @param personId 人员信息 ID
     * @return 人员信息 
     */
    public PersonInfo selectPersonInfoById(String personId);

    /**
     * 查询人员信息 列表
     * 
     * @param personInfo 人员信息 
     * @return 人员信息 集合
     */
    public List<PersonInfo> selectPersonInfoList(PersonInfo personInfo);

    /**
     * 新增人员信息 
     * 
     * @param personInfo 人员信息 
     * @return 结果
     */
    public int insertPersonInfo(PersonInfo personInfo);

    /**
     * 修改人员信息 
     * 
     * @param personInfo 人员信息 
     * @return 结果
     */
    public int updatePersonInfo(PersonInfo personInfo);

    /**
     * 删除人员信息 
     * 
     * @param personId 人员信息 ID
     * @return 结果
     */
    public int deletePersonInfoById(String personId);

    /**
     * 批量删除人员信息 
     * 
     * @param personIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePersonInfoByIds(String[] personIds);
}

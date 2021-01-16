package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.HmpPersonSrevTouser;

/**
 * 就诊人服务与员工关联Service接口
 * 
 * @author sino
 * @date 2020-11-12
 */
public interface IHmpPersonSrevTouserService 
{
    /**
     * 查询就诊人服务与员工关联
     * 
     * @param personcode 就诊人服务与员工关联ID
     * @return 就诊人服务与员工关联
     */
    public HmpPersonSrevTouser selectHmpPersonSrevTouserById(String personcode);

    /**
     * 查询就诊人服务与员工关联列表
     * 
     * @param hmpPersonSrevTouser 就诊人服务与员工关联
     * @return 就诊人服务与员工关联集合
     */
    public List<HmpPersonSrevTouser> selectHmpPersonSrevTouserList(HmpPersonSrevTouser hmpPersonSrevTouser);

    /**
     * 新增就诊人服务与员工关联
     * 
     * @param hmpPersonSrevTouser 就诊人服务与员工关联
     * @return 结果
     */
    public int insertHmpPersonSrevTouser(HmpPersonSrevTouser hmpPersonSrevTouser);

    /**
     * 修改就诊人服务与员工关联
     * 
     * @param hmpPersonSrevTouser 就诊人服务与员工关联
     * @return 结果
     */
    public int updateHmpPersonSrevTouser(HmpPersonSrevTouser hmpPersonSrevTouser);

    /**
     * 批量删除就诊人服务与员工关联
     * 
     * @param personcodes 需要删除的就诊人服务与员工关联ID
     * @return 结果
     */
    public int deleteHmpPersonSrevTouserByIds(String[] personcodes);

    /**
     * 删除就诊人服务与员工关联信息
     * 
     * @param personcode 就诊人服务与员工关联ID
     * @return 结果
     */
    public int deleteHmpPersonSrevTouserById(String personcode);
}

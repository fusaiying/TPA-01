package com.paic.ehis.system.mapper;

import java.util.List;

import com.paic.ehis.system.api.domain.SysUser;
import com.paic.ehis.system.domain.HmpPersonService;

/**
 * 就诊人服务信息Mapper接口
 * 
 * @author sino
 * @date 2020-11-11
 */
public interface HmpPersonServiceMapper 
{
    /**
     * 查询就诊人服务信息
     * 
     * @param personcode 就诊人服务信息ID
     * @return 就诊人服务信息
     */
    public HmpPersonService selectHmpPersonServiceById(String personcode);

    /**
     * 查询就诊人服务信息列表
     * 
     * @param hmpPersonService 就诊人服务信息
     * @return 就诊人服务信息集合
     */
    public List<HmpPersonService> selectHmpPersonServiceList(HmpPersonService hmpPersonService);

    /**
     * 新增就诊人服务信息
     * 
     * @param hmpPersonService 就诊人服务信息
     * @return 结果
     */
    public int insertHmpPersonService(HmpPersonService hmpPersonService);

    /**
     * 修改就诊人服务信息
     * 
     * @param hmpPersonService 就诊人服务信息
     * @return 结果
     */
    public int updateHmpPersonService(HmpPersonService hmpPersonService);

    /**
     * 删除就诊人服务信息
     * 
     * @param personcode 就诊人服务信息ID
     * @return 结果
     */
    public int deleteHmpPersonServiceById(String personcode);

    /**
     * 批量删除就诊人服务信息
     * 
     * @param personcodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpPersonServiceByIds(String[] personcodes);

    /**
     * 查询我的就诊人服务列表
     *
     * @param sysUser sysUser
     * @return 就诊人服务信息
     */
    public List<HmpPersonService> selectHmpPersonServiceListByOnwer(SysUser sysUser);

    /**
     * 查询就诊人服务信息编码转汉语
     *
     * @param personcode 就诊人服务信息ID
     * @return 就诊人服务信息
     */
    public HmpPersonService selectHmpPersonServiceAndChineseById(String personcode);
}

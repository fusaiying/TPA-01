package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.HmpPersonServiceMapper;
import com.paic.ehis.system.domain.HmpPersonService;
import com.paic.ehis.system.service.IHmpPersonServiceService;

/**
 * 就诊人服务信息Service业务层处理
 * 
 * @author sino
 * @date 2020-11-11
 */
@Service
public class HmpPersonServiceServiceImpl implements IHmpPersonServiceService 
{
    @Autowired
    private HmpPersonServiceMapper hmpPersonServiceMapper;

    /**
     * 查询就诊人服务信息
     * 
     * @param personcode 就诊人服务信息ID
     * @return 就诊人服务信息
     */
    @Override
    public HmpPersonService selectHmpPersonServiceById(String personcode)
    {
        return hmpPersonServiceMapper.selectHmpPersonServiceById(personcode);
    }

    /**
     * 查询就诊人服务信息列表
     * 
     * @param hmpPersonService 就诊人服务信息
     * @return 就诊人服务信息
     */
    @Override
    public List<HmpPersonService> selectHmpPersonServiceList(HmpPersonService hmpPersonService)
    {
        return hmpPersonServiceMapper.selectHmpPersonServiceList(hmpPersonService);
    }

    /**
     * 新增就诊人服务信息
     * 
     * @param hmpPersonService 就诊人服务信息
     * @return 结果
     */
    @Override
    public int insertHmpPersonService(HmpPersonService hmpPersonService)
    {
        return hmpPersonServiceMapper.insertHmpPersonService(hmpPersonService);
    }

    /**
     * 修改就诊人服务信息
     * 
     * @param hmpPersonService 就诊人服务信息
     * @return 结果
     */
    @Override
    public int updateHmpPersonService(HmpPersonService hmpPersonService)
    {
        return hmpPersonServiceMapper.updateHmpPersonService(hmpPersonService);
    }

    /**
     * 批量删除就诊人服务信息
     * 
     * @param personcodes 需要删除的就诊人服务信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpPersonServiceByIds(String[] personcodes)
    {
        return hmpPersonServiceMapper.deleteHmpPersonServiceByIds(personcodes);
    }

    /**
     * 删除就诊人服务信息信息
     * 
     * @param personcode 就诊人服务信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpPersonServiceById(String personcode)
    {
        return hmpPersonServiceMapper.deleteHmpPersonServiceById(personcode);
    }

    /**
     * 查询我的就诊人服务列表
     *
     * @param sysUser sysUser
     * @return 就诊人服务信息
     */
    @Override
    public List<HmpPersonService> selectHmpPersonServiceListByOnwer(SysUser sysUser) {
        return hmpPersonServiceMapper.selectHmpPersonServiceListByOnwer(sysUser);
    }

    /**
     * 查询就诊人服务信息编码转汉语
     *
     * @param personcode 就诊人服务信息ID
     * @return 就诊人服务信息
     */
    public HmpPersonService selectHmpPersonServiceAndChineseById(String personcode){
        return hmpPersonServiceMapper.selectHmpPersonServiceAndChineseById(personcode);
    }
}

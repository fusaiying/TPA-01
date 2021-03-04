package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseServiceInfo;
import com.paic.ehis.base.mapper.BaseServiceInfoMapper;
import com.paic.ehis.base.service.IBaseServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base_service_info（服务项目）Service业务层处理
 *
 * @author sino
 * @date 2020-12-28
 */
@Service
public class BaseServiceInfoServiceImpl implements IBaseServiceInfoService
{
    @Autowired
    private BaseServiceInfoMapper baseServiceInfoMapper;

    /**
     * 查询base_service_info（服务项目）
     *
     * @param servicecode base_service_info（服务项目）ID
     * @return base_service_info（服务项目）
     */
    @Override
    public BaseServiceInfo selectBaseServiceInfoById(String servicecode)
    {
        return baseServiceInfoMapper.selectBaseServiceInfoById(servicecode);
    }

    /**
     * 查询base_service_info（服务项目）列表
     *
     * @param baseServiceInfo base_service_info（服务项目）
     * @return base_service_info（服务项目）
     */
    @Override
    public List<BaseServiceInfo> selectBaseServiceInfoList(BaseServiceInfo baseServiceInfo)
    {
        return baseServiceInfoMapper.selectBaseServiceInfoList(baseServiceInfo);
    }

    /**
     * 新增base_service_info（服务项目）
     *
     * @param baseServiceInfo base_service_info（服务项目）
     * @return 结果
     */
    @Override
    public int insertBaseServiceInfo(BaseServiceInfo baseServiceInfo)
    {
        baseServiceInfo.setCreateTime(DateUtils.getNowDate());
        return baseServiceInfoMapper.insertBaseServiceInfo(baseServiceInfo);
    }

    /**
     * 修改base_service_info（服务项目）
     *
     * @param baseServiceInfo base_service_info（服务项目）
     * @return 结果
     */
    @Override
    public int updateBaseServiceInfo(BaseServiceInfo baseServiceInfo)
    {
        baseServiceInfo.setUpdateTime(DateUtils.getNowDate());
        return baseServiceInfoMapper.updateBaseServiceInfo(baseServiceInfo);
    }

    /**
     * 批量删除base_service_info（服务项目）
     *
     * @param servicecodes 需要删除的base_service_info（服务项目）ID
     * @return 结果
     */
    @Override
    public int deleteBaseServiceInfoByIds(String[] servicecodes)
    {
        return baseServiceInfoMapper.deleteBaseServiceInfoByIds(servicecodes);
    }

    /**
     * 删除base_service_info（服务项目）信息
     *
     * @param servicecode base_service_info（服务项目）ID
     * @return 结果
     */
    @Override
    public int deleteBaseServiceInfoById(String servicecode)
    {
        return baseServiceInfoMapper.deleteBaseServiceInfoById(servicecode);
    }

    /**
     * 根据服务名称查询服务
     *
     * @param servicename
     * @return
     */
    @Override
    public List<BaseServiceInfo> selectServiceByName(String servicename) {
        return baseServiceInfoMapper.selectServiceByName(servicename);
    }

    /**
     * 更改服务项目状态为注销（'03'）
     *
     * @param servicecode
     * @return
     */
    @Override
    public int updateBaseServiceInfoById(String servicecode) {
        return baseServiceInfoMapper.updateBaseServiceInfoById(servicecode);
    }

    /**
     * 获取二级编码下的当前最大编码
     *
     * @param serverCode
     * @return
     */
    @Override
    public String selectServerMaxNo(String serverCode) {
        String maxno = baseServiceInfoMapper.selectServerMaxNo(serverCode);
        return maxno;
    }



    /**
     *@Description:  查询所有服务项目
     *@Author: houjiawei
     *@date: 2021/1/11 15:11
     */
    @Override
    public List<BaseServiceInfo> selectBaseServiceInfoAllList(BaseServiceInfo baseServiceInfo) {
        return baseServiceInfoMapper.selectBaseServiceInfoAllList(baseServiceInfo);
    }

    /**
     * 根据服务项目编码修改有效的服务项目
     *
     * @param baseServiceInfo
     * @return
     */
    @Override
    public int updateBaseServiceInfoByCodeAndState(BaseServiceInfo baseServiceInfo) {
        return baseServiceInfoMapper.updateBaseServiceInfoByCodeAndState(baseServiceInfo);
    }

    /**
     * 根据别名查询服务项目
     *
     * @param alias
     * @return
     */
    @Override
    public List<BaseServiceInfo> selectBaseServiceInfoByAlias(String alias) {
        return baseServiceInfoMapper.selectBaseServiceInfoByAlias(alias);
    }

    /**
     * 根据原名称查询服务项目
     *
     * @param originalName
     * @return
     */
    @Override
    public List<BaseServiceInfo> selectBaseServiceInfoByorName(String originalName) {
        return baseServiceInfoMapper.selectBaseServiceInfoByorName(originalName);
    }
}

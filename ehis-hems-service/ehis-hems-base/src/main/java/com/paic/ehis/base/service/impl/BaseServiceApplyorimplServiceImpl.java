package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseServiceApplyorimpl;
import com.paic.ehis.base.mapper.BaseServiceApplyorimplMapper;
import com.paic.ehis.base.service.IBaseServiceApplyorimplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * base_service_applyorimpl（服务项目申请或实施信息显示配置）Service业务层处理
 *
 * @author sino
 * @date 2020-12-28
 */
@Service
public class BaseServiceApplyorimplServiceImpl implements IBaseServiceApplyorimplService
{
    @Autowired
    private BaseServiceApplyorimplMapper baseServiceApplyorimplMapper;

    /**
     * 查询base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param servicecode base_service_applyorimpl（服务项目申请或实施信息显示配置）ID
     * @return base_service_applyorimpl（服务项目申请或实施信息显示配置）
     */
    @Override
    public List<BaseServiceApplyorimpl> selectBaseServiceApplyorimplById(String servicecode)
    {
        return baseServiceApplyorimplMapper.selectBaseServiceApplyorimplById(servicecode);
    }

    /**
     * 查询base_service_applyorimpl（服务项目申请或实施信息显示配置）列表
     *
     * @param baseServiceApplyorimpl base_service_applyorimpl（服务项目申请或实施信息显示配置）
     * @return base_service_applyorimpl（服务项目申请或实施信息显示配置）
     */
    @Override
    public List<BaseServiceApplyorimpl> selectBaseServiceApplyorimplList(BaseServiceApplyorimpl baseServiceApplyorimpl)
    {
        return baseServiceApplyorimplMapper.selectBaseServiceApplyorimplList(baseServiceApplyorimpl);
    }

    /**
     * 新增base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param baseServiceApplyorimpl base_service_applyorimpl（服务项目申请或实施信息显示配置）
     * @return 结果
     */
    @Override
    public int insertBaseServiceApplyorimpl(BaseServiceApplyorimpl baseServiceApplyorimpl)
    {
        baseServiceApplyorimpl.setCreateTime(DateUtils.getNowDate());
        return baseServiceApplyorimplMapper.insertBaseServiceApplyorimpl(baseServiceApplyorimpl);
    }

    /**
     * 修改base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param baseServiceApplyorimpl base_service_applyorimpl（服务项目申请或实施信息显示配置）
     * @return 结果
     */
    @Override
    public int updateBaseServiceApplyorimpl(BaseServiceApplyorimpl baseServiceApplyorimpl)
    {
        baseServiceApplyorimpl.setUpdateTime(DateUtils.getNowDate());
        return baseServiceApplyorimplMapper.updateBaseServiceApplyorimpl(baseServiceApplyorimpl);
    }

    /**
     * 批量删除base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param servicecodes 需要删除的base_service_applyorimpl（服务项目申请或实施信息显示配置）ID
     * @return 结果
     */
    @Override
    public int deleteBaseServiceApplyorimplByIds(String[] servicecodes)
    {
        return baseServiceApplyorimplMapper.deleteBaseServiceApplyorimplByIds(servicecodes);
    }

    /**
     * 删除base_service_applyorimpl（服务项目申请或实施信息显示配置）信息
     *
     * @param servicecode base_service_applyorimpl（服务项目申请或实施信息显示配置）ID
     * @return 结果
     */
    @Override
    public int deleteBaseServiceApplyorimplById(String servicecode)
    {
        return baseServiceApplyorimplMapper.deleteBaseServiceApplyorimplById(servicecode);
    }

    /**
     * 根据服务项目编码更新状态为不可用
     *
     * @param serviceCode
     * @return
     */
    @Override
    public int updateBaseServiceApplyorimplById(String serviceCode) {
        return baseServiceApplyorimplMapper.updateBaseServiceApplyorimplById(serviceCode);
    }

    @Override
    public int updateFieldNewServiceCode(String servicecode, String newServiceCode) {
        return baseServiceApplyorimplMapper.updateFieldNewServiceCode(servicecode,newServiceCode);
    }
}

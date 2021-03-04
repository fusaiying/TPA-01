package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseServiceApplyorimpl;
import com.paic.ehis.base.domain.BaseServiceProcess;
import com.paic.ehis.base.domain.vo.ServiceProcess;
import com.paic.ehis.base.mapper.BaseServiceApplyorimplMapper;
import com.paic.ehis.base.mapper.BaseServiceProcessMapper;
import com.paic.ehis.base.service.IBaseServiceProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base_service_process（服务项目流程配置）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-28
 */
@Service
public class BaseServiceProcessServiceImpl implements IBaseServiceProcessService
{
    @Autowired
    private BaseServiceProcessMapper baseServiceProcessMapper;
    @Autowired
    private BaseServiceApplyorimplMapper baseServiceApplyorimplMapper;

    /**
     * 查询base_service_process（服务项目流程配置）
     * 
     * @param servicecode base_service_process（服务项目流程配置）ID
     * @return base_service_process（服务项目流程配置）
     */
    @Override
    public List<BaseServiceProcess> selectBaseServiceProcessById(String servicecode)
    {
        return baseServiceProcessMapper.selectBaseServiceProcessById(servicecode);
    }

    /**
     * 查询base_service_process（服务项目流程配置）列表
     * 
     * @param baseServiceProcess base_service_process（服务项目流程配置）
     * @return base_service_process（服务项目流程配置）
     */
    @Override
    public List<BaseServiceProcess> selectBaseServiceProcessList(BaseServiceProcess baseServiceProcess)
    {
        return baseServiceProcessMapper.selectBaseServiceProcessList(baseServiceProcess);
    }

    /**
     * 新增base_service_process（服务项目流程配置）
     * 
     * @param baseServiceProcess base_service_process（服务项目流程配置）
     * @return 结果
     */
    @Override
    public int insertBaseServiceProcess(BaseServiceProcess baseServiceProcess)
    {
        baseServiceProcess.setCreateTime(DateUtils.getNowDate());
        return baseServiceProcessMapper.insertBaseServiceProcess(baseServiceProcess);
    }

    /**
     * 修改base_service_process（服务项目流程配置）
     * 
     * @param baseServiceProcess base_service_process（服务项目流程配置）
     * @return 结果
     */
    @Override
    public int updateBaseServiceProcess(BaseServiceProcess baseServiceProcess)
    {
        baseServiceProcess.setUpdateTime(DateUtils.getNowDate());
        return baseServiceProcessMapper.updateBaseServiceProcess(baseServiceProcess);
    }

    /**
     * 批量删除base_service_process（服务项目流程配置）
     * 
     * @param servicecodes 需要删除的base_service_process（服务项目流程配置）ID
     * @return 结果
     */
    @Override
    public int deleteBaseServiceProcessByIds(String[] servicecodes)
    {
        return baseServiceProcessMapper.deleteBaseServiceProcessByIds(servicecodes);
    }

    /**
     * 删除base_service_process（服务项目流程配置）信息
     * 
     * @param servicecode base_service_process（服务项目流程配置）ID
     * @return 结果
     */
    @Override
    public int deleteBaseServiceProcessById(String servicecode)
    {
        return baseServiceProcessMapper.deleteBaseServiceProcessById(servicecode);
    }

    /**
     * 查询服务项目配置信息
     *
     * @param servicecode
     * @return
     */
    @Override
    public ServiceProcess getServiceProcess(String servicecode) {
        ServiceProcess serviceProcess = new ServiceProcess();
        List<BaseServiceProcess> processList = baseServiceProcessMapper.selectBaseServiceProcessById(servicecode);
        if (processList.size() > 0){
            System.out.println(servicecode + "已配置流程");
            serviceProcess.setProcessList(processList);
        }
        List<BaseServiceApplyorimpl> fieldList = baseServiceApplyorimplMapper.selectBaseServiceApplyorimplById(servicecode);
        if (fieldList.size() > 0){
            System.out.println(servicecode + "已配置业务字段");
            serviceProcess.setFieldList(fieldList);
        }
        return serviceProcess;
    }

    /**
     * 根据服务项目编码修改状态
     *
     * @param servicecode
     * @return
     */
    @Override
    public int updateBaseServiceProcessById(String servicecode) {
        return baseServiceProcessMapper.updateBaseServiceProcessById(servicecode);
    }

    /**
     * 更新服务项目编码（status = '01'）
     *
     * @param servicecode
     * @param newServiceCode
     * @return
     */
    @Override
    public int updateProcessNewServiceCode(String servicecode, String newServiceCode) {
        return baseServiceProcessMapper.updateProcessNewServiceCode(servicecode,newServiceCode);
    }
}

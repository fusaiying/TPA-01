package com.paic.ehis.system.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.system.domain.SysOrganInfo;
import com.paic.ehis.system.mapper.SysOrganInfoMapper;
import com.paic.ehis.system.service.ISysOrganInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构信息Service业务层处理
 * 
 * @author sino
 * @date 2021-02-19
 */
@Service
public class SysOrganInfoServiceImpl implements ISysOrganInfoService
{
    @Autowired
    private SysOrganInfoMapper sysOrganInfoMapper;

    /**
     * 查询机构信息
     * 
     * @param organId 机构信息ID
     * @return 机构信息
     */
    @Override
    public SysOrganInfo selectSysOrganInfoById(Long organId)
    {
        return sysOrganInfoMapper.selectSysOrganInfoById(organId);
    }

    /**
     * 查询机构信息列表
     * 
     * @param sysOrganInfo 机构信息
     * @return 机构信息
     */
    @Override
    public List<SysOrganInfo> selectSysOrganInfoList(SysOrganInfo sysOrganInfo)
    {
        return sysOrganInfoMapper.selectSysOrganInfoList(sysOrganInfo);
    }

    /**
     * 新增机构信息
     * 
     * @param sysOrganInfo 机构信息
     * @return 结果
     */
    @Override
    public int insertSysOrganInfo(SysOrganInfo sysOrganInfo)
    {
        sysOrganInfo.setCreateTime(DateUtils.getNowDate());
        return sysOrganInfoMapper.insertSysOrganInfo(sysOrganInfo);
    }

    /**
     * 修改机构信息
     * 
     * @param sysOrganInfo 机构信息
     * @return 结果
     */
    @Override
    public int updateSysOrganInfo(SysOrganInfo sysOrganInfo)
    {
        sysOrganInfo.setUpdateTime(DateUtils.getNowDate());
        return sysOrganInfoMapper.updateSysOrganInfo(sysOrganInfo);
    }

    /**
     * 批量删除机构信息
     * 
     * @param organIds 需要删除的机构信息ID
     * @return 结果
     */
    @Override
    public int deleteSysOrganInfoByIds(Long[] organIds)
    {
        return sysOrganInfoMapper.deleteSysOrganInfoByIds(organIds);
    }

    /**
     * 删除机构信息信息
     * 
     * @param organId 机构信息ID
     * @return 结果
     */
    @Override
    public int deleteSysOrganInfoById(Long organId)
    {
        return sysOrganInfoMapper.deleteSysOrganInfoById(organId);
    }

    /**
     * 根据机构编码集合查询机构信息
     *
     * @param organCodes
     * @return
     */
    @Override
    public List<SysOrganInfo> selectOrganInfoByOrganCodes(List<String> organCodes) {
        return sysOrganInfoMapper.selectOrganInfoByOrganCodes(organCodes);
    }
}

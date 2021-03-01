package com.paic.ehis.system.service;

import com.paic.ehis.system.domain.SysOrganInfo;
import com.paic.ehis.system.domain.dto.OrganListDTO;

import java.util.List;

/**
 * 机构信息Service接口
 * 
 * @author sino
 * @date 2021-02-19
 */
public interface ISysOrganInfoService 
{
    /**
     * 查询机构信息
     * 
     * @param organId 机构信息ID
     * @return 机构信息
     */
    public SysOrganInfo selectSysOrganInfoById(Long organId);

    /**
     * 查询机构信息列表
     * 
     * @param sysOrganInfo 机构信息
     * @return 机构信息集合
     */
    public List<SysOrganInfo> selectSysOrganInfoList(SysOrganInfo sysOrganInfo);

    /**
     * 新增机构信息
     * 
     * @param sysOrganInfo 机构信息
     * @return 结果
     */
    public int insertSysOrganInfo(SysOrganInfo sysOrganInfo);

    /**
     * 修改机构信息
     * 
     * @param sysOrganInfo 机构信息
     * @return 结果
     */
    public int updateSysOrganInfo(SysOrganInfo sysOrganInfo);

    /**
     * 批量删除机构信息
     * 
     * @param organIds 需要删除的机构信息ID
     * @return 结果
     */
    public int deleteSysOrganInfoByIds(Long[] organIds);

    /**
     * 删除机构信息信息
     * 
     * @param organId 机构信息ID
     * @return 结果
     */
    public int deleteSysOrganInfoById(Long organId);

    /**
     * 根据机构编码集合查询机构信息
     *
     * @param organCodes
     * @return
     */
    public List<SysOrganInfo> selectOrganInfoByOrganCodes(List<String> organCodes);

    /**
     * 根据当前机构编码查询下属机构清单
     * @param organListDTO
     * @return
     */
    public List<SysOrganInfo> selectOrganListByUpOrganCode(OrganListDTO organListDTO);
}

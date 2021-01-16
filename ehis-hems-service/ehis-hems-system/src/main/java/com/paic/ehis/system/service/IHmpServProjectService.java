package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.HmpServProject;

/**
 * 服务项目Service接口
 * 
 * @author sino
 * @date 2020-11-09
 */
public interface IHmpServProjectService 
{
    /**
     * 查询服务项目
     * 
     * @param projectcode 服务项目ID
     * @return 服务项目
     */
    public HmpServProject selectHmpServProjectById(String projectcode);

    /**
     * 查询服务项目列表
     * 
     * @param hmpServProject 服务项目
     * @return 服务项目集合
     */
    public List<HmpServProject> selectHmpServProjectList(HmpServProject hmpServProject);

    /**
     * 新增服务项目
     * 
     * @param hmpServProject 服务项目
     * @return 结果
     */
    public int insertHmpServProject(HmpServProject hmpServProject);

    /**
     * 修改服务项目
     * 
     * @param hmpServProject 服务项目
     * @return 结果
     */
    public int updateHmpServProject(HmpServProject hmpServProject);

    /**
     * 批量删除服务项目
     * 
     * @param projectcodes 需要删除的服务项目ID
     * @return 结果
     */
    public int deleteHmpServProjectByIds(String[] projectcodes);

    /**
     * 删除服务项目信息
     * 
     * @param projectcode 服务项目ID
     * @return 结果
     */
    public int deleteHmpServProjectById(String projectcode);

    /**
     * 查询服务项目列表
     *
     * @param projectCode 服务项目
     * @return 服务项目集合
     */
    public List<HmpServProject> selectHmpServProjectListByProjectCode(String projectCode);

}

package com.paic.ehis.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.HmpServProjectMapper;
import com.paic.ehis.system.domain.HmpServProject;
import com.paic.ehis.system.service.IHmpServProjectService;

/**
 * 服务项目Service业务层处理
 * 
 * @author sino
 * @date 2020-11-09
 */
@Service
public class HmpServProjectServiceImpl implements IHmpServProjectService 
{
    @Autowired
    private HmpServProjectMapper hmpServProjectMapper;

    /**
     * 查询服务项目
     * 
     * @param projectcode 服务项目ID
     * @return 服务项目
     */
    @Override
    public HmpServProject selectHmpServProjectById(String projectcode)
    {
        return hmpServProjectMapper.selectHmpServProjectById(projectcode);
    }

    /**
     * 查询服务项目列表
     * 
     * @param hmpServProject 服务项目
     * @return 服务项目
     */
    @Override
    public List<HmpServProject> selectHmpServProjectList(HmpServProject hmpServProject)
    {
        return hmpServProjectMapper.selectHmpServProjectList(hmpServProject);
    }

    /**
     * 新增服务项目
     * 
     * @param hmpServProject 服务项目
     * @return 结果
     */
    @Override
    public int insertHmpServProject(HmpServProject hmpServProject)
    {
        return hmpServProjectMapper.insertHmpServProject(hmpServProject);
    }

    /**
     * 修改服务项目
     * 
     * @param hmpServProject 服务项目
     * @return 结果
     */
    @Override
    public int updateHmpServProject(HmpServProject hmpServProject)
    {
        return hmpServProjectMapper.updateHmpServProject(hmpServProject);
    }

    /**
     * 批量删除服务项目
     * 
     * @param projectcodes 需要删除的服务项目ID
     * @return 结果
     */
    @Override
    public int deleteHmpServProjectByIds(String[] projectcodes)
    {
        return hmpServProjectMapper.deleteHmpServProjectByIds(projectcodes);
    }

    /**
     * 删除服务项目信息
     * 
     * @param projectcode 服务项目ID
     * @return 结果
     */
    @Override
    public int deleteHmpServProjectById(String projectcode)
    {
        return hmpServProjectMapper.deleteHmpServProjectById(projectcode);
    }

    /**
     * 查询服务项目列表
     *
     * @param projectCode 服务项目
     * @return 服务项目集合
     */
    public List<HmpServProject> selectHmpServProjectListByProjectCode(String projectCode)
    {
        return hmpServProjectMapper.selectHmpServProjectListByProjectCode(projectCode);
    }
}

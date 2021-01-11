package com.paic.ehis.product.service.impl;

import java.util.List;
import com.paic.ehis.product.domain.HmpServOpera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.product.mapper.HmpServProjectMapper;
import com.paic.ehis.product.domain.HmpServProject;
import com.paic.ehis.product.service.IHmpServProjectService;

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
    @Override
    public List<HmpServProject> selectHmpServProjectListByProjectCode(String projectCode)
    {
        return hmpServProjectMapper.selectHmpServProjectListByProjectCode(projectCode);
    }

    /**
     * 查询服务项目 用于下拉
     * 应用于供应商合约
     * @return
     */
    @Override
    public List<HmpServProject> selectHmpServProjectOptions() {
        return hmpServProjectMapper.selectHmpServProjectOptions();
    }

    @Override
    public int updateHmpServOperaById(HmpServProject project) {
        return hmpServProjectMapper.updateHmpServOperaById(project);
    }

    /**
     * 查询服务项目的供应商
     *
     * @param projectCode 服务项目编码
     * @return 供应商集合
     */
    @Override
    public List<HmpServOpera> queryServiceOrgByProjectCode(String projectCode){
        return hmpServProjectMapper.queryServiceOrgByProjectCode(projectCode);
    }

    /**
     * 查询产品所属服务项目
     *
     * @param productNo 产品编码
     * @return 服务项目
     */
    @Override
    public HmpServProject selectHmpServProjectByProductNo(String productNo){
        return hmpServProjectMapper.selectHmpServProjectByProductNo(productNo);
    }

    /**
     * 查询供应商信息
     *
     * @param servComNo 供应商编码
     * @return 供应商
     */
    @Override
    public HmpServOpera selectHmpServOperaById(String servComNo){
        return hmpServProjectMapper.selectHmpServOperaById(servComNo);
    }
}

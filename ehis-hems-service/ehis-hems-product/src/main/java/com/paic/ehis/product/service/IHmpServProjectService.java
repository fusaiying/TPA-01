package com.paic.ehis.product.service;

import java.util.List;
import com.paic.ehis.product.domain.HmpServOpera;
import com.paic.ehis.product.domain.HmpServProject;

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

    /**
     * 查询服务项目的供应商
     *
     * @param projectCode 服务项目编码
     * @return 供应商集合
     */
    public List<HmpServOpera> queryServiceOrgByProjectCode(String projectCode);

    /**
     * 查询产品所属服务项目
     *
     * @param productNo 产品编码
     * @return 服务项目
     */
    public HmpServProject selectHmpServProjectByProductNo(String productNo);

    /**
     * 查询供应商信息
     *
     * @param servComNo 供应商编码
     * @return 供应商
     */
    public HmpServOpera selectHmpServOperaById(String servComNo);
    /**
     * 查询服务项目  用于下拉
     * 应用于供应商合约
     * @return
     */
    public List<HmpServProject> selectHmpServProjectOptions();



    /**
     * 根据服务项目编码和节点编码 修改服务项目
     * @param project
     * @return
     */
    int updateHmpServOperaById(HmpServProject project);

}

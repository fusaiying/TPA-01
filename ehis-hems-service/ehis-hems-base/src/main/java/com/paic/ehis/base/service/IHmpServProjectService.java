package com.paic.ehis.base.service;


import com.paic.ehis.base.domain.HmpServProject;

/**
 * 服务项目Service接口
 * 
 * @author sino
 * @date 2020-11-09
 */
public interface IHmpServProjectService 
{
    /**
     * 根据服务项目编码查询服务项目
     * @return
     */
    public HmpServProject selectHmpServProjectNameByCode(String projectCode);

    /**
     * 根据服务名称获取服务项目
     * @param projectname
     * @return
     */
    public HmpServProject selectHmpServProjectCodeByName(String projectname);

}

package com.paic.ehis.product.service;

import java.util.List;
import com.paic.ehis.product.domain.Hmpkgsmsconfiginfo;

/**
 * 产品包短信配置表Service接口
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
public interface IHmpkgsmsconfiginfoService 
{
    /**
     * 查询产品包短信配置表
     * 
     * @param serialno 产品包短信配置表ID
     * @return 产品包短信配置表
     */
    public Hmpkgsmsconfiginfo selectHmpkgsmsconfiginfoById(Long serialno);

    /**
     * 查询产品包短信配置表列表
     * 
     * @param hmpkgsmsconfiginfo 产品包短信配置表
     * @return 产品包短信配置表集合
     */
    public List<Hmpkgsmsconfiginfo> selectHmpkgsmsconfiginfoList(Hmpkgsmsconfiginfo hmpkgsmsconfiginfo);

    /**
     * 新增产品包短信配置表
     * 
     * @param hmpkgsmsconfiginfo 产品包短信配置表
     * @return 结果
     */
    public int insertHmpkgsmsconfiginfo(Hmpkgsmsconfiginfo hmpkgsmsconfiginfo);

    /**
     * 修改产品包短信配置表
     * 
     * @param hmpkgsmsconfiginfo 产品包短信配置表
     * @return 结果
     */
    public int updateHmpkgsmsconfiginfo(Hmpkgsmsconfiginfo hmpkgsmsconfiginfo);

    /**
     * 批量删除产品包短信配置表
     * 
     * @param serialnos 需要删除的产品包短信配置表ID
     * @return 结果
     */
    public int deleteHmpkgsmsconfiginfoByIds(Long[] serialnos);

    /**
     * 删除产品包短信配置表信息
     * 
     * @param serialno 产品包短信配置表ID
     * @return 结果
     */
    public int deleteHmpkgsmsconfiginfoById(Long serialno);
}

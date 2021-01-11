package com.paic.ehis.product.mapper;

import java.util.List;
import com.paic.ehis.product.domain.Hmpkgsmsconfiginfo;

/**
 * 产品包短信配置表Mapper接口
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
public interface HmpkgsmsconfiginfoMapper 
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
     * 删除产品包短信配置表
     * 
     * @param serialno 产品包短信配置表ID
     * @return 结果
     */
    public int deleteHmpkgsmsconfiginfoById(Long serialno);

    /**
     * 批量删除产品包短信配置表
     * 
     * @param serialnos 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpkgsmsconfiginfoByIds(Long[] serialnos);
}

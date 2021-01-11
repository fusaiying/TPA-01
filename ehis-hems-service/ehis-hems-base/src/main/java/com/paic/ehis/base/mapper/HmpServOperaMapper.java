package com.paic.ehis.base.mapper;

import java.util.List;

import com.paic.ehis.base.domain.HmpComWebSite;
import com.paic.ehis.base.domain.HmpServOpera;
import com.paic.ehis.base.domain.NetworkVO;

/**
 * 供应商管理1Mapper接口
 * 
 * @author sino
 * @date 2020-09-21
 */
public interface HmpServOperaMapper
{
    /**
     * 查询供应商管理1
     * 
     * @param servcomno 供应商管理1ID
     * @return 供应商管理1
     */
    public HmpServOpera selectHmpServOperaById(String servcomno);

    /**
     * 查询供应商管理1列表
     * 
     * @param hmpServOpera 供应商管理1
     * @return 供应商管理1集合
     */
    public List<HmpServOpera> selectHmpServOperaList(HmpServOpera hmpServOpera);

    /**
     * 新增供应商管理1
     * 
     * @param hmpServOpera 供应商管理1
     * @return 结果
     */
    public int insertHmpServOpera(HmpServOpera hmpServOpera);

    /**
     * 修改供应商管理1
     * 
     * @param hmpServOpera 供应商管理1
     * @return 结果
     */
    public int updateHmpServOpera(HmpServOpera hmpServOpera);

    /**
     * 删除供应商管理1
     * 
     * @param servcomno 供应商管理1ID
     * @return 结果
     */
    public int deleteHmpServOpera(String servcomno);

    /**
     * 批量删除供应商管理1
     * 
     * @param servcomnos 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpServOperaByIds(String[] servcomnos);

    /**
     * 根据条件查询供应商网络
     * @param hmpServOpera
     * @return
     */
    public List<NetworkVO> selectProviderNetwork(HmpServOpera hmpServOpera);

    /**
     * 根据供应商编码查询服务网点配置对象
     * @param hmpComWebSite
     * @return
     */
    public List<HmpComWebSite> selectHmpComWebSiteByNo(HmpComWebSite hmpComWebSite);

    /**
     * 根据服务网点编码删除服务网点配置对象
     * @param websitecode
     * @return
     */
    public int deleteHmpComWebSiteByIds(String[] websitecode);

    /**
     * 根据服务网点对象 修改 服务网点配置对象
     * @param hmpComWebSite
     * @return
     */
    public int updateHmpComWebSiteById(HmpComWebSite hmpComWebSite);

    /**
     * 新增 服务网点配置对象
     * @param hmpComWebSite
     * @return
     */
    public int insertHmpComWebSite(HmpComWebSite hmpComWebSite);

    // ======== 网点导入 ==========

    /**
     * 根据供应商编码查询服务网点配置对象 （解析码值后）
     * @param hmpComWebSite
     * @return
     */
    public List<HmpComWebSite> selectHmpComWebSiteByNoTrans(HmpComWebSite hmpComWebSite);

    /**
     * 批量新增服务网点配置对象
     * @param hmpComWebSites
     * @return
     */
    public int insertHmpComWebSiteList(List<HmpComWebSite> hmpComWebSites);

    // ======= 医生网络-首页 ======


    // ========= 服务实施-查询及处理 ============

    /**
     * 根据网点编码查询网点名称
     * @param websitecode
     * @return
     */
    HmpComWebSite selectHmpComWebSiteByCode(String websitecode);
}

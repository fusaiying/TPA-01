package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.HmpComWebSite;
import com.paic.ehis.system.domain.HmpProdServCom;
import com.paic.ehis.system.domain.SysDictData;

/**
 * 产品服务机构配置Service接口
 * 
 * @author 李碧辉
 * @date 2020-09-23
 */
public interface IHmpProdServComService
{
    /**
     * 查询产品服务机构配置
     * 
     * @param productno 产品服务机构配置ID
     * @return 产品服务机构配置
     */
    public HmpProdServCom selectHmpProdServComById(String productno);

    /**
     * 查询产品服务机构配置列表
     * 
     * @param hmpProdServCom 产品服务机构配置
     * @return 产品服务机构配置集合
     */
    public List<HmpProdServCom> selectHmpProdServComList(HmpProdServCom hmpProdServCom);

    /**
     * 新增产品服务机构配置
     * 
     * @param hmpProdServCom 产品服务机构配置
     * @return 结果
     */
    public int insertHmpProdServCom(HmpProdServCom hmpProdServCom);

    /**
     * 修改产品服务机构配置
     * 
     * @param hmpProdServCom 产品服务机构配置
     * @return 结果
     */
    public int updateHmpProdServCom(HmpProdServCom hmpProdServCom);

    /**
     * 批量删除产品服务机构配置
     * 
     * @param productnos 需要删除的产品服务机构配置ID
     * @return 结果
     */
    public int deleteHmpProdServComByIds(String[] productnos);

    /**
     * 删除产品服务机构配置信息
     * 
     * @param productno 产品服务机构配置ID
     * @return 结果
     */
    public int deleteHmpProdServComById(String productno);

    /**
     * 查询服务机构
     *
     * @param hmpProdServCom 服务机构明细
     * @return 结果
     */
    public List<SysDictData> selectSerivceComNo(HmpProdServCom hmpProdServCom);

    /**
     * 查询服务网点
     *
     * @param hmpProdServCom 产品
     * @return 结果
     */
    public List<HmpComWebSite> selectServiceNetWorkList(HmpProdServCom hmpProdServCom);

    /**
     * 批量新增产品服务机构配置
     *
     * @param prodServComs 产品服务机构配置
     * @return 结果
     */
    public int betchInsertHmpProdServCom(List<HmpProdServCom> prodServComs);

    /**
     * 根据产品id获取服务机构
     *
     * @param productNo 产品编码
     * @return 结果
     */
    public List<HmpProdServCom> queryServiceOrg(String productNo);

    /**
     * 根据产品id获取服务网点
     *
     * @param productNo 产品编码
     * @return 结果
     */
    public List<HmpComWebSite> queryServiceNetWork(String productNo);
}

package com.paic.ehis.product.service.impl;

import com.paic.ehis.common.core.utils.StringUtils;

import com.paic.ehis.product.domain.HmpComWebSite;
import com.paic.ehis.product.domain.HmpProdServCom;
import com.paic.ehis.product.domain.SysDictData;
import com.paic.ehis.product.mapper.HmpProdServComMapper;
import com.paic.ehis.product.service.IHmpProdServComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品服务机构配置Service业务层处理
 * 
 * @author 李碧辉
 * @date 2020-09-23
 */
@Service
public class HmpProdServComServiceImpl implements IHmpProdServComService
{
    @Autowired
    private HmpProdServComMapper hmpProdServComMapper;

    /**
     * 查询产品服务机构配置
     * 
     * @param productno 产品服务机构配置ID
     * @return 产品服务机构配置
     */
    @Override
    public HmpProdServCom selectHmpProdServComById(String productno)
    {
        return hmpProdServComMapper.selectHmpProdServComById(productno);
    }

    /**
     * 查询产品服务机构配置列表
     * 
     * @param hmpProdServCom 产品服务机构配置
     * @return 产品服务机构配置
     */
    @Override
    public List<HmpProdServCom> selectHmpProdServComList(HmpProdServCom hmpProdServCom)
    {
        return hmpProdServComMapper.selectHmpProdServComList(hmpProdServCom);
    }

    /**
     * 新增产品服务机构配置
     * 
     * @param hmpProdServCom 产品服务机构配置
     * @return 结果
     */
    @Override
    public int insertHmpProdServCom(HmpProdServCom hmpProdServCom)
    {
        return hmpProdServComMapper.insertHmpProdServCom(hmpProdServCom);
    }

    /**
     * 修改产品服务机构配置
     * 
     * @param hmpProdServCom 产品服务机构配置
     * @return 结果
     */
    @Override
    public int updateHmpProdServCom(HmpProdServCom hmpProdServCom)
    {
        return hmpProdServComMapper.updateHmpProdServCom(hmpProdServCom);
    }

    /**
     * 批量删除产品服务机构配置
     * 
     * @param productnos 需要删除的产品服务机构配置ID
     * @return 结果
     */
    @Override
    public int deleteHmpProdServComByIds(String[] productnos)
    {
        return hmpProdServComMapper.deleteHmpProdServComByIds(productnos);
    }

    /**
     * 删除产品服务机构配置信息
     * 
     * @param productno 产品服务机构配置ID
     * @return 结果
     */
    @Override
    public int deleteHmpProdServComById(String productno)
    {
        return hmpProdServComMapper.deleteHmpProdServComById(productno);
    }

    /**
     * 查询产品服务机构下拉
     *
     * @param hmpProdServCom 产品服务机构
     * @return 结果
     */
    @Override
    public List<SysDictData> selectSerivceComNo(HmpProdServCom hmpProdServCom) {
        List<SysDictData> dictDatas = hmpProdServComMapper.selectSerivceComNo(hmpProdServCom);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }


    /**
     * 查询服务网点
     *
     * @param hmpProdServCom 产品
     * @return 结果
     */
    @Override
    public List<HmpComWebSite> selectServiceNetWorkList(HmpProdServCom hmpProdServCom) {
        return hmpProdServComMapper.selectServiceNetWorkList(hmpProdServCom);
    }

    /**
     * 批量添加产品服务机构
     *
     * @param productServiceComSelects 产品集
     * @return 结果
     */
    @Override
    public int betchInsertHmpProdServCom(List<HmpProdServCom> productServiceComSelects) {
        return hmpProdServComMapper.betchInsertHmpProdServCom(productServiceComSelects);
    }

    /**
     * 根据产品id获取服务机构
     *
     * @param productNo 产品编码
     * @return 结果
     */
    @Override
    public List<HmpProdServCom> queryServiceOrg(String productNo){
        return hmpProdServComMapper.queryServiceOrg(productNo);
    }

    /**
     * 根据产品id获取服务网点
     *
     * @param productNo 产品编码
     * @return 结果
     */
    @Override
    public List<HmpComWebSite> queryServiceNetWork(String productNo){
        return hmpProdServComMapper.queryServiceNetWork(productNo);
    }
}

package com.paic.ehis.product.service;


import com.paic.ehis.product.domain.HpProductInformation;
import com.paic.ehis.product.domain.SysDictData;

import java.util.List;

/**
 * 产品定义Service接口
 *
 * @author sino
 * @date 2020-09-19
 */
public interface IProductDefinitionService {
    /**
     * 查询产品定义
     *
     * @param productid 产品定义ID
     * @return 产品定义
     */
    public HpProductInformation selectHpProductInformationById(Long productid);

    /**
     * 查询产品定义列表
     *
     * @param hpProductInformation 产品定义
     * @return 产品定义集合
     */
    public List<HpProductInformation> selectHpProductInformationList(HpProductInformation hpProductInformation);

    /**
     * 新增产品定义
     *
     * @param hpProductInformation 产品定义
     * @return 结果
     */
    public int insertHpProductInformation(HpProductInformation hpProductInformation);

    /**
     * 修改产品定义
     *
     * @param hpProductInformation 产品定义
     * @return 结果
     */
    public int updateHpProductInformation(HpProductInformation hpProductInformation);

    /**
     * 批量删除产品定义
     *
     * @param productids 需要删除的产品定义ID
     * @return 结果
     */
    public int deleteHpProductInformationByIds(Long[] productids);

    /**
     * 删除产品定义信息
     *
     * @param productid 产品定义ID
     * @return 结果
     */
    public int deleteHpProductInformationById(Long productid);

    /**
     * 查询服务类型
     *
     * @param hpProductInformation 产品
     * @return 结果
     */
    public List<SysDictData> selectServiceType(HpProductInformation hpProductInformation);

    /**
     * 查询产品类别
     *
     * @param hpProductInformation 产品
     * @return 结果
     */
    public List<SysDictData> selectProductClass(HpProductInformation hpProductInformation);

    /**
     * 查询产品细类
     *
     * @param hpProductInformation 产品
     * @return 结果
     */
    public List<SysDictData> selectProductSubClass(HpProductInformation hpProductInformation);
}

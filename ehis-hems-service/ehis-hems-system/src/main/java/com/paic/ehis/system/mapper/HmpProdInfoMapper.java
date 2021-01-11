package com.paic.ehis.system.mapper;

import java.util.List;

import com.paic.ehis.system.domain.HmpProdInfo;
import com.paic.ehis.system.domain.HmpProdSalCom;
import com.paic.ehis.system.domain.SysDictData;

/**
 * 产品定义Mapper接口
 * 
 * @author sino
 * @date 2020-09-19
 */
public interface HmpProdInfoMapper
{
    /**
     * 查询产品定义
     * 
     * @param productNo 产品定义ID
     * @return 产品定义
     */
    public HmpProdInfo selectHmpProdInfoById(String productNo);

    /**
     * 查询服务类型
     *
     * @param hmpProdInfo 产品
     * @return 结果
     */
    public List<SysDictData> selectServiceType(HmpProdInfo hmpProdInfo);

    /**
     * 查询产品类别
     *
     * @param hmpProdInfo 产品
     * @return 结果
     */
    public List<SysDictData> selectProductClass(HmpProdInfo hmpProdInfo);

    /**
     * 查询产品细类
     *
     * @param hmpProdInfo 产品
     * @return 结果
     */
    public List<SysDictData> selectProductSubClass(HmpProdInfo hmpProdInfo);

    /**
     * 查询产品定义列表
     * 
     * @param hmpProdInfo 产品定义
     * @return 产品定义集合
     */
    public List<HmpProdInfo> selectHmpProdInfoList(HmpProdInfo hmpProdInfo);

    /**
     * 新增产品定义
     * 
     * @param hmpProdInfo 产品定义
     * @return 结果
     */
    public int insertHmpProdInfo(HmpProdInfo hmpProdInfo);

    /**
     * 修改产品定义
     * 
     * @param hmpProdInfo 产品定义
     * @return 结果
     */
    public int updateHmpProdInfo(HmpProdInfo hmpProdInfo);

    /**
     * 删除产品定义
     * 
     * @param productNo 产品定义ID
     * @return 结果
     */
    public int deleteHmpProdInfoById(String productNo);

    /**
     * 批量删除产品定义
     * 
     * @param productNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpProdInfoByIds(String[] productNos);

    /**
     * 查询所有省份
     *
     * @param hmpProdSalCom 产品销售地区
     * @return 结果
     */
    public List<SysDictData> selectProvinceAll(HmpProdSalCom hmpProdSalCom);

    /**
     * 查询城市
     *
     * @param hmpProdSalCom 产品销售地区
     * @return 结果
     */
    public List<SysDictData> selectCityByProvince(HmpProdSalCom hmpProdSalCom);

    /**
     * 查询地区
     *
     * @param hmpProdSalCom 产品销售地区
     * @return 结果
     */
    public List<SysDictData> selectDistrictByCity(HmpProdSalCom hmpProdSalCom);

    /**
     * 批量删除产品服务地区
     *
     * @param productNo 需要删除地区的产品编码
     * @return 结果
     */
    public int deleteHmpProdSalComByProductNo(String productNo);

    /**
     * 批量插入产品服务地区
     *
     * @param prodSalComs 产品服务地区
     * @return 结果
     */
    public int betchInsertHmpProdSalCom(List<HmpProdSalCom> prodSalComs);

    /**
     * 查询产品的地区
     *
     * @param productNo 产品编码
     * @return 结果
     */
    public List<HmpProdSalCom> queryServiceCity(String productNo);
}

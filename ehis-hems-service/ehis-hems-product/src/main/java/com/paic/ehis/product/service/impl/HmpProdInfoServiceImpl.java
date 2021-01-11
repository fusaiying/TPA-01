package com.paic.ehis.product.service.impl;

import com.paic.ehis.common.core.utils.StringUtils;

import com.paic.ehis.product.domain.HmpProdInfo;
import com.paic.ehis.product.domain.HmpProdSalCom;
import com.paic.ehis.product.domain.SysDictData;
import com.paic.ehis.product.mapper.HmpProdInfoMapper;
import com.paic.ehis.product.service.IHmpProdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品定义Service业务层处理
 *
 * @author sino
 * @date 2020-09-19
 */
@Service
public class HmpProdInfoServiceImpl implements IHmpProdInfoService {

    @Autowired
    private HmpProdInfoMapper hmpProdInfoMapper;

    /**
     * 查询产品定义
     *
     * @param productno 产品定义ID
     * @return 产品定义
     */
    @Override
    public HmpProdInfo selectHmpProdInfoById(String productno)
    {
        return hmpProdInfoMapper.selectHmpProdInfoById(productno);
    }

    /**
     * 查询产品定义列表
     *
     * @param hmpProdInfo 产品定义
     * @return 产品定义
     */
    @Override
    public List<HmpProdInfo> selectHmpProdInfoList(HmpProdInfo hmpProdInfo)
    {
        return hmpProdInfoMapper.selectHmpProdInfoList(hmpProdInfo);
    }

    /**
     * 根据产品名称查询产品
     *
     * @param hmpProdInfo 产品定义
     * @return 产品集合
     */
    @Override
    public List<HmpProdInfo> selectHmpProdByName(HmpProdInfo hmpProdInfo){
        return hmpProdInfoMapper.selectHmpProdByName(hmpProdInfo);
    }

    /**
     * 查询服务类型
     *
     * @param hmpProdInfo 产品
     * @return 结果
     */
    @Override
    public List<SysDictData> selectServiceType(HmpProdInfo hmpProdInfo) {
        List<SysDictData> dictDatas = hmpProdInfoMapper.selectServiceType(hmpProdInfo);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 查询产品类别
     *
     * @param hmpProdInfo 产品
     * @return 结果
     */
    @Override
    public List<SysDictData> selectProductClass(HmpProdInfo hmpProdInfo) {
        List<SysDictData> dictDatas = hmpProdInfoMapper.selectProductClass(hmpProdInfo);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 查询产品细类
     *
     * @param hmpProdInfo 产品
     * @return 结果
     */
    @Override
    public List<SysDictData> selectProductSubClass(HmpProdInfo hmpProdInfo) {
        List<SysDictData> dictDatas = hmpProdInfoMapper.selectProductSubClass(hmpProdInfo);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 查询所有省份
     *
     * @param hmpProdSalCom 产品销售地区
     * @return 结果
     */
    @Override
    public List<SysDictData> selectProvinceAll(HmpProdSalCom hmpProdSalCom) {
        List<SysDictData> dictDatas = hmpProdInfoMapper.selectProvinceAll(hmpProdSalCom);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 查询城市
     *
     * @param hmpProdSalCom 产品销售地区
     * @return 结果
     */
    @Override
    public List<SysDictData> selectCityByProvince(HmpProdSalCom hmpProdSalCom) {
        List<SysDictData> dictDatas = hmpProdInfoMapper.selectCityByProvince(hmpProdSalCom);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 查询地区
     *
     * @param hmpProdSalCom 产品销售地区
     * @return 结果
     */
    @Override
    public List<SysDictData> selectDistrictByCity(HmpProdSalCom hmpProdSalCom) {
        List<SysDictData> dictDatas = hmpProdInfoMapper.selectDistrictByCity(hmpProdSalCom);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 新增产品定义
     *
     * @param hmpProdInfo 产品定义
     * @return 结果
     */
    @Override
    public int insertHmpProdInfo(HmpProdInfo hmpProdInfo)
    {
        return hmpProdInfoMapper.insertHmpProdInfo(hmpProdInfo);
    }

    /**
     * 修改产品定义
     *
     * @param hmpProdInfo 产品定义
     * @return 结果
     */
    @Override
    public int updateHmpProdInfo(HmpProdInfo hmpProdInfo)
    {
        return hmpProdInfoMapper.updateHmpProdInfo(hmpProdInfo);
    }

    /**
     * 批量删除产品定义
     *
     * @param productNos 需要删除的产品定义ID
     * @return 结果
     */
    @Override
    public int deleteHmpProdInfoByIds(String[] productNos)
    {
        return hmpProdInfoMapper.deleteHmpProdInfoByIds(productNos);
    }

    /**
     * 删除产品定义信息
     *
     * @param productno 产品定义ID
     * @return 结果
     */
    @Override
    public int deleteHmpProdInfoById(String productno)
    {
        return hmpProdInfoMapper.deleteHmpProdInfoById(productno);
    }

    /**
     * 批量删除产品服务地区
     *
     * @param productNo 需要删除地区的产品编码
     * @return 结果
     */
    @Override
    public int deleteHmpProdSalComByProductNo(String productNo){
        return hmpProdInfoMapper.deleteHmpProdSalComByProductNo(productNo);
    }

    /**
     * 批量插入产品服务地区
     *
     * @param productSalComs 产品服务地区
     * @return 结果
     */
    @Override
    public int betchInsertHmpProdSalCom(List<HmpProdSalCom> productSalComs){
        return hmpProdInfoMapper.betchInsertHmpProdSalCom(productSalComs);
    }

    /**
     * 查询产品的地区
     *
     * @param productNo 产品编码
     * @return 结果
     */
    @Override
    public List<HmpProdSalCom> queryServiceCity(String productNo){
        return hmpProdInfoMapper.queryServiceCity(productNo);
    }

    @Override
    public List<HmpProdInfo> selectProductDict() {
        return hmpProdInfoMapper.selectProductDict();
    }
}

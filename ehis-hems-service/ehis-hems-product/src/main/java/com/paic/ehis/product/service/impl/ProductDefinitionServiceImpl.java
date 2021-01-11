package com.paic.ehis.product.service.impl;

import com.paic.ehis.common.core.utils.StringUtils;

import com.paic.ehis.product.domain.HpProductInformation;
import com.paic.ehis.product.domain.SysDictData;
import com.paic.ehis.product.mapper.HpProductInformationMapper;
import com.paic.ehis.product.service.IProductDefinitionService;
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
public class ProductDefinitionServiceImpl implements IProductDefinitionService {

    @Autowired
    private HpProductInformationMapper hpProductInformationMapper;

    /**
     * 查询产品定义
     *
     * @param productid 产品定义ID
     * @return 产品定义
     */
    @Override
    public HpProductInformation selectHpProductInformationById(Long productid)
    {
        return hpProductInformationMapper.selectHpProductInformationById(productid);
    }

    /**
     * 查询产品定义列表
     *
     * @param hpProductInformation 产品定义
     * @return 产品定义
     */
    @Override
    public List<HpProductInformation> selectHpProductInformationList(HpProductInformation hpProductInformation)
    {
        return hpProductInformationMapper.selectHpProductInformationList(hpProductInformation);
    }

    /**
     * 查询服务类型
     *
     * @param hpProductInformation 产品
     * @return 结果
     */
    @Override
    public List<SysDictData> selectServiceType(HpProductInformation hpProductInformation) {
        List<SysDictData> dictDatas = hpProductInformationMapper.selectServiceType(hpProductInformation);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 查询产品类别
     *
     * @param hpProductInformation 产品
     * @return 结果
     */
    @Override
    public List<SysDictData> selectProductClass(HpProductInformation hpProductInformation) {
        List<SysDictData> dictDatas = hpProductInformationMapper.selectProductClass(hpProductInformation);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 查询产品细类
     *
     * @param hpProductInformation 产品
     * @return 结果
     */
    @Override
    public List<SysDictData> selectProductSubClass(HpProductInformation hpProductInformation) {
        List<SysDictData> dictDatas = hpProductInformationMapper.selectProductSubClass(hpProductInformation);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 新增产品定义
     *
     * @param hpProductInformation 产品定义
     * @return 结果
     */
    @Override
    public int insertHpProductInformation(HpProductInformation hpProductInformation)
    {
        return hpProductInformationMapper.insertHpProductInformation(hpProductInformation);
    }

    /**
     * 修改产品定义
     *
     * @param hpProductInformation 产品定义
     * @return 结果
     */
    @Override
    public int updateHpProductInformation(HpProductInformation hpProductInformation)
    {
        return hpProductInformationMapper.updateHpProductInformation(hpProductInformation);
    }

    /**
     * 批量删除产品定义
     *
     * @param productids 需要删除的产品定义ID
     * @return 结果
     */
    @Override
    public int deleteHpProductInformationByIds(Long[] productids)
    {
        return hpProductInformationMapper.deleteHpProductInformationByIds(productids);
    }

    /**
     * 删除产品定义信息
     *
     * @param productid 产品定义ID
     * @return 结果
     */
    @Override
    public int deleteHpProductInformationById(Long productid)
    {
        return hpProductInformationMapper.deleteHpProductInformationById(productid);
    }
}

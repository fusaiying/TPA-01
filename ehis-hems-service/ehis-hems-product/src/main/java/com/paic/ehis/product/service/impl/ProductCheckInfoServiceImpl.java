package com.paic.ehis.product.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.product.mapper.ProductCheckInfoMapper;
import com.paic.ehis.product.domain.ProductCheckInfo;
import com.paic.ehis.product.service.IProductCheckInfoService;


/**
 * base_product_check_info(服务产品审核信息)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ProductCheckInfoServiceImpl implements IProductCheckInfoService 
{
    @Autowired
    private ProductCheckInfoMapper productCheckInfoMapper;

    /**
     * 查询base_product_check_info(服务产品审核信息)
     * 
     * @param serialNo base_product_check_info(服务产品审核信息)ID
     * @return base_product_check_info(服务产品审核信息)
     */
    @Override
    public ProductCheckInfo selectProductCheckInfoById(String serialNo)
    {
        return productCheckInfoMapper.selectProductCheckInfoById(serialNo);
    }

    /**
     * 查询base_product_check_info(服务产品审核信息)列表
     * 
     * @param productCheckInfo base_product_check_info(服务产品审核信息)
     * @return base_product_check_info(服务产品审核信息)
     */
    @Override
    public List<ProductCheckInfo> selectProductCheckInfoList(ProductCheckInfo productCheckInfo)
    {
        return productCheckInfoMapper.selectProductCheckInfoList(productCheckInfo);
    }

    /**
     * 新增base_product_check_info(服务产品审核信息)
     * 
     * @param productCheckInfo base_product_check_info(服务产品审核信息)
     * @return 结果
     */
    @Override
    public int insertProductCheckInfo(ProductCheckInfo productCheckInfo)
    {
        productCheckInfo.setCreateTime(DateUtils.getNowDate());
        return productCheckInfoMapper.insertProductCheckInfo(productCheckInfo);
    }

    /**
     * 修改base_product_check_info(服务产品审核信息)
     * 
     * @param productCheckInfo base_product_check_info(服务产品审核信息)
     * @return 结果
     */
    @Override
    public int updateProductCheckInfo(ProductCheckInfo productCheckInfo)
    {
        productCheckInfo.setUpdateTime(DateUtils.getNowDate());
        return productCheckInfoMapper.updateProductCheckInfo(productCheckInfo);
    }

    /**
     * 批量删除base_product_check_info(服务产品审核信息)
     * 
     * @param serialNos 需要删除的base_product_check_info(服务产品审核信息)ID
     * @return 结果
     */
    @Override
    public int deleteProductCheckInfoByIds(String[] serialNos)
    {
        return productCheckInfoMapper.deleteProductCheckInfoByIds(serialNos);
    }

    /**
     * 删除base_product_check_info(服务产品审核信息)信息
     * 
     * @param serialNo base_product_check_info(服务产品审核信息)ID
     * @return 结果
     */
    @Override
    public int deleteProductCheckInfoById(String serialNo)
    {
        return productCheckInfoMapper.deleteProductCheckInfoById(serialNo);
    }
}

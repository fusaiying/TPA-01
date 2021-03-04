package com.paic.ehis.product.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.product.mapper.ProductManagerLogMapper;
import com.paic.ehis.product.domain.ProductManagerLog;
import com.paic.ehis.product.service.IProductManagerLogService;

/**
 * base_product_manager_log(服务产品管理记录)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ProductManagerLogServiceImpl implements IProductManagerLogService 
{
    @Autowired
    private ProductManagerLogMapper productManagerLogMapper;

    /**
     * 查询base_product_manager_log(服务产品管理记录)
     * 
     * @param serialNo base_product_manager_log(服务产品管理记录)ID
     * @return base_product_manager_log(服务产品管理记录)
     */
    @Override
    public ProductManagerLog selectProductManagerLogById(String serialNo)
    {
        return productManagerLogMapper.selectProductManagerLogById(serialNo);
    }

    /**
     * 查询base_product_manager_log(服务产品管理记录)列表
     * 
     * @param productManagerLog base_product_manager_log(服务产品管理记录)
     * @return base_product_manager_log(服务产品管理记录)
     */
    @Override
    public List<ProductManagerLog> selectProductManagerLogList(ProductManagerLog productManagerLog)
    {
        return productManagerLogMapper.selectProductManagerLogList(productManagerLog);
    }

    /**
     * 新增base_product_manager_log(服务产品管理记录)
     * 
     * @param productManagerLog base_product_manager_log(服务产品管理记录)
     * @return 结果
     */
    @Override
    public int insertProductManagerLog(ProductManagerLog productManagerLog)
    {
        productManagerLog.setCreateTime(DateUtils.getNowDate());
        return productManagerLogMapper.insertProductManagerLog(productManagerLog);
    }

    /**
     * 修改base_product_manager_log(服务产品管理记录)
     * 
     * @param productManagerLog base_product_manager_log(服务产品管理记录)
     * @return 结果
     */
    @Override
    public int updateProductManagerLog(ProductManagerLog productManagerLog)
    {
        productManagerLog.setUpdateTime(DateUtils.getNowDate());
        return productManagerLogMapper.updateProductManagerLog(productManagerLog);
    }

    /**
     * 批量删除base_product_manager_log(服务产品管理记录)
     * 
     * @param serialNos 需要删除的base_product_manager_log(服务产品管理记录)ID
     * @return 结果
     */
    @Override
    public int deleteProductManagerLogByIds(String[] serialNos)
    {
        return productManagerLogMapper.deleteProductManagerLogByIds(serialNos);
    }

    /**
     * 删除base_product_manager_log(服务产品管理记录)信息
     * 
     * @param serialNo base_product_manager_log(服务产品管理记录)ID
     * @return 结果
     */
    @Override
    public int deleteProductManagerLogById(String serialNo)
    {
        return productManagerLogMapper.deleteProductManagerLogById(serialNo);
    }
}

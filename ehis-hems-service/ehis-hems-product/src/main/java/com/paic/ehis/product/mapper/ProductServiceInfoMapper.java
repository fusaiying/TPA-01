package com.paic.ehis.product.mapper;

import java.util.List;

import com.paic.ehis.product.domain.ProductInfo;
import com.paic.ehis.product.domain.ProductServiceInfo;

/**
 * base_product_service_info(服务产品项目关联)Mapper接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface ProductServiceInfoMapper 
{
    /**
     * 查询base_product_service_info(服务产品项目关联)
     * 
     * @param serialNo base_product_service_info(服务产品项目关联)ID
     * @return base_product_service_info(服务产品项目关联)
     */
    public ProductServiceInfo selectProductServiceInfoById(String serialNo);

    /**
     * 查询base_product_service_info(服务产品项目关联)列表
     * 
     * @param productServiceInfo base_product_service_info(服务产品项目关联)
     * @return base_product_service_info(服务产品项目关联)集合
     */
    public List<ProductServiceInfo> selectProductServiceInfoList(ProductServiceInfo productServiceInfo);

    /**
     * 新增base_product_service_info(服务产品项目关联)
     * 
     * @param productServiceInfo base_product_service_info(服务产品项目关联)
     * @return 结果
     */
    public int insertProductServiceInfo(ProductServiceInfo productServiceInfo);

    /**
     * 新增base_product_service_info(服务产品项目关联)批量插入数据
     */
    public int insertProductServiceInfoNew(List<ProductServiceInfo> productServiceInfo);

    /**
     * 修改base_product_service_info(服务产品项目关联)
     * 
     * @param productServiceInfo base_product_service_info(服务产品项目关联)
     * @return 结果
     */
    public int updateProductServiceInfo(ProductServiceInfo productServiceInfo);

    /**
     * 删除base_product_service_info(服务产品项目关联)
     * 
     * @param serialNo base_product_service_info(服务产品项目关联)ID
     * @return 结果
     */
    public int deleteProductServiceInfoById(String serialNo);

    /**
     * 批量删除base_product_service_info(服务产品项目关联)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductServiceInfoByIds(String[] serialNos);

    /**
     * 修改数据状态
     */
    public int updateStaus(String productCode);
}

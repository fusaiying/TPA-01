package com.paic.ehis.product.service;

import java.util.List;

import com.paic.ehis.product.domain.ProductInfo;
import com.paic.ehis.product.domain.ProductSaveInfoVo;
import com.paic.ehis.product.domain.ProductServiceInfo;
import com.paic.ehis.product.domain.ProductServiceInfoVo;

/**
 * base_product_service_info(服务产品项目关联)Service接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface IProductServiceInfoService 
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
     * @param productServiceInfoVo base_product_service_info(服务产品项目关联)
     * @return 结果
     */
    public int insertProductServiceInfo(ProductServiceInfoVo productServiceInfoVo);

    /**
     * 修改base_product_service_info(服务产品项目关联)
     * 
     * @param productServiceInfo base_product_service_info(服务产品项目关联)
     * @return 结果
     */
    public int updateProductServiceInfo(ProductServiceInfo productServiceInfo);

    /**
     * 批量删除base_product_service_info(服务产品项目关联)
     * 
     * @param serialNos 需要删除的base_product_service_info(服务产品项目关联)ID
     * @return 结果
     */
    public int deleteProductServiceInfoByIds(String[] serialNos);

    /**
     * 删除base_product_service_info(服务产品项目关联)信息
     * 
     * @param serialNo base_product_service_info(服务产品项目关联)ID
     * @return 结果
     */
    public int deleteProductServiceInfoById(String serialNo);

    public int saveProductInfo(ProductSaveInfoVo productSaveInfoVo);

    public int checkProductInfo(ProductSaveInfoVo productSaveInfoVo);
}

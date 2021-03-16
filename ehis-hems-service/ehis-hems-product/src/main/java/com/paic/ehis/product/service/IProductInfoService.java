package com.paic.ehis.product.service;

import java.util.List;

import com.paic.ehis.product.domain.*;

/**
 * base_product_info(服务产品)Service接口
 * 
 * @author sino
 * @date 2021-01-15
 */

public interface IProductInfoService 
{
    /**
     * 查询base_product_info(服务产品)
     * 
     * @param productInfo base_product_info(服务产品)ID
     * @return base_product_info(服务产品)
     */
    public ProductInfo selectProductInfoById(ProductInfo productInfo);

    /**
     * 查询base_product_info(服务产品)列表
     * 
     * @param productInfo base_product_info(服务产品)
     * @return base_product_info(服务产品)集合
     */
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo) throws Exception;

    /**
     *查询base_product_info(服务产品)列表(无参)
     * @return base_product_info(服务产品)集合
     */
    public List<ProductInfo> selectProductInfoListNull() throws Exception;

    /**
     * 新增base_product_info(服务产品)
     * 
     * @param productInfo base_product_info(服务产品)
     * @return 结果
     */
    public int insertProductInfo(ProductInfo productInfo);


    /**
     * 新增base_product_info(服务产品)
     *
     * @param productInfo base_product_info(服务产品)
     * @return 结果
     */
    public ProductInfo insertInfo(ProductInfo productInfo);

    /**
     * 修改base_product_info(服务产品)
     * 
     * @param productInfo base_product_info(服务产品)
     * @return 结果
     */
    public int updateProductInfo(ProductInfo productInfo);

    /**
     * 批量删除base_product_info(服务产品)
     * 
     * @param serialNos 需要删除的base_product_info(服务产品)ID
     * @return 结果
     */
    public int deleteProductInfoByIds(String[] serialNos);

    /**
     * 删除base_product_info(服务产品)信息
     * 
     * @param serialNo base_product_info(服务产品)ID
     * @return 结果
     */
    public int deleteProductInfoById(String serialNo);


    /**
     *查询服务项目列表
     */
    public List<BaseServiceInfo> getServiceInfo();


    /**
     *产品审核列表查询
     */
    public List<ProductInfo> selectCheckList(ProductInfo productInfo);

    /**
     *保存审核信息
     */
    public int insertCheckInfo(ProductCheckInfo productCheckInfo);

    /**
     *管理界面查询
     */
    public List<ProductInfo> mangerList(ProductInfo productInfo);

    /**
     *
     * @return
     */
    public List<ProductInfo> mangerListNull() throws Exception;

    /**
     *产品下线
     */
    public int insertMangerInfo(ProductManagerLog ProductManagerLog);


    /**
     *字段验重
     */
    public List<ProductInfo> checkField(ProductInfo productInfo);


    /**
     *供应商信息查询接口
     */
    public List<ProductSupplierInfo> selectSupplier(ProductInfo productInfo);


    /**
     *供应商信息查询接口
     */
    public List<ProductSupplierInfo> selectProductSupplier(ProductInfo productInfo);

    /**
     *供应商信息查询接口
     */
    public int insertSupplier(com.paic.ehis.system.api.domain.ProductSupplierInfoVo productSupplierInfoVo);

    /**
     *已经配置的供应商信息置为失效
     */
    public int updateSupplierStatus(com.paic.ehis.system.api.domain.ProductSupplierInfoVo productSupplierInfoVo);
}

package com.paic.ehis.product.mapper;

import java.util.List;

import com.paic.ehis.product.domain.*;

/**
 * base_product_info(服务产品)Mapper接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface ProductInfoMapper 
{
    /**
     * 查询base_product_info(服务产品)
     * 
     * @param productCode base_product_info(服务产品)ID
     * @return base_product_info(服务产品)
     */
    public ProductInfo selectProductInfoById(String productCode);

    /**
     * 查询base_product_info(服务产品)列表
     * 
     * @param productInfo base_product_info(服务产品)
     * @return base_product_info(服务产品)集合
     */
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo);

    public List<ProductInfo> selectDefaultListist(ProductInfo productInfo);

    /**
     * 新增base_product_info(服务产品)
     * 
     * @param productInfo base_product_info(服务产品)
     * @return 结果
     */
    public int insertProductInfo(ProductInfo productInfo);

    /**
     * 修改base_product_info(服务产品)
     * 
     * @param productInfo base_product_info(服务产品)
     * @return 结果
     */
    public int updateProductInfo(ProductInfo productInfo);

    /**
     * 删除base_product_info(服务产品)
     * 
     * @param serialNo base_product_info(服务产品)ID
     * @return 结果
     */
    public int deleteProductInfoById(String serialNo);

    /**
     * 批量删除base_product_info(服务产品)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductInfoByIds(String[] serialNos);


    public List<BaseServiceInfo> getServiceInfo();

    //修改产品定义表的状态
    public int updateStatus( String productCode);

    //修改产品定义表为发布状态
    public int updateProStatus1( String productCode);

    //修改产品定义表为发布状态
    public int updateProStatus4( String productCode);

    //修改产品定义表为退回状态
    public int updateProStatus2( String productCode);

    public List<ProductInfo> selectCheckList(ProductInfo productInfo);

    public List<ProductInfo> mangerList(ProductInfo productInfo);

    //修改产品定义表为下线状态
    public int updateProStatus3( ProductManagerLog productManagerLog);

    //验重
    public List<ProductInfo> checkField( ProductInfo productInfo);

    public List<ProductSupplierInfo> selectSupplier(ProductInfo productInfo);

    public List<ProductSupplierInfo> selectProductSupplier(ProductInfo productInfo);

    public int updatesupplierInfo(ProductSupplierInfoVo productSupplierInfoVo);

    public int insertSupplier(List<ProductSupplierInfo> productSupplierInfo);

    public List<ProductInfo> selectMangerListNull(ProductInfo productInfo);
}

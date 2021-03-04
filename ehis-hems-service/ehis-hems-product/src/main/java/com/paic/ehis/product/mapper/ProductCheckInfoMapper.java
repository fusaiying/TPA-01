package com.paic.ehis.product.mapper;

import java.util.List;
import com.paic.ehis.product.domain.ProductCheckInfo;

/**
 * base_product_check_info(服务产品审核信息)Mapper接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface ProductCheckInfoMapper 
{
    /**
     * 查询base_product_check_info(服务产品审核信息)
     * 
     * @param serialNo base_product_check_info(服务产品审核信息)ID
     * @return base_product_check_info(服务产品审核信息)
     */
    public ProductCheckInfo selectProductCheckInfoById(String serialNo);

    /**
     * 查询base_product_check_info(服务产品审核信息)列表
     * 
     * @param productCheckInfo base_product_check_info(服务产品审核信息)
     * @return base_product_check_info(服务产品审核信息)集合
     */
    public List<ProductCheckInfo> selectProductCheckInfoList(ProductCheckInfo productCheckInfo);

    /**
     * 新增base_product_check_info(服务产品审核信息)
     * 
     * @param productCheckInfo base_product_check_info(服务产品审核信息)
     * @return 结果
     */
    public int insertProductCheckInfo(ProductCheckInfo productCheckInfo);

    /**
     * 修改base_product_check_info(服务产品审核信息)
     * 
     * @param productCheckInfo base_product_check_info(服务产品审核信息)
     * @return 结果
     */
    public int updateProductCheckInfo(ProductCheckInfo productCheckInfo);

    /**
     * 删除base_product_check_info(服务产品审核信息)
     * 
     * @param serialNo base_product_check_info(服务产品审核信息)ID
     * @return 结果
     */
    public int deleteProductCheckInfoById(String serialNo);

    /**
     * 批量删除base_product_check_info(服务产品审核信息)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductCheckInfoByIds(String[] serialNos);
}

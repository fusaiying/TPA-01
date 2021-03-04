package com.paic.ehis.product.mapper;

import java.util.List;
import com.paic.ehis.product.domain.ProductManagerLog;

/**
 * base_product_manager_log(服务产品管理记录)Mapper接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface ProductManagerLogMapper 
{
    /**
     * 查询base_product_manager_log(服务产品管理记录)
     * 
     * @param serialNo base_product_manager_log(服务产品管理记录)ID
     * @return base_product_manager_log(服务产品管理记录)
     */
    public ProductManagerLog selectProductManagerLogById(String serialNo);

    /**
     * 查询base_product_manager_log(服务产品管理记录)列表
     * 
     * @param productManagerLog base_product_manager_log(服务产品管理记录)
     * @return base_product_manager_log(服务产品管理记录)集合
     */
    public List<ProductManagerLog> selectProductManagerLogList(ProductManagerLog productManagerLog);

    /**
     * 新增base_product_manager_log(服务产品管理记录)
     * 
     * @param productManagerLog base_product_manager_log(服务产品管理记录)
     * @return 结果
     */
    public int insertProductManagerLog(ProductManagerLog productManagerLog);

    /**
     * 修改base_product_manager_log(服务产品管理记录)
     * 
     * @param productManagerLog base_product_manager_log(服务产品管理记录)
     * @return 结果
     */
    public int updateProductManagerLog(ProductManagerLog productManagerLog);

    /**
     * 删除base_product_manager_log(服务产品管理记录)
     * 
     * @param serialNo base_product_manager_log(服务产品管理记录)ID
     * @return 结果
     */
    public int deleteProductManagerLogById(String serialNo);

    /**
     * 批量删除base_product_manager_log(服务产品管理记录)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductManagerLogByIds(String[] serialNos);

    //修改轨迹表中的状态
    public int updateStatus(String productCode);
}

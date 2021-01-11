package com.paic.ehis.product.service;

import java.util.List;
import com.paic.ehis.product.domain.productPackageExamine;

/**
 * 产品包审核Service接口
 * 
 * @author sino
 * @date 2020-09-22
 */
public interface IproductPackageExamineService 
{
    /**
     * 查询产品包审核
     * 
     * @param productpackageno 产品包审核ID
     * @return 产品包审核
     */
    public productPackageExamine selectproductPackageExamineById(String productpackageno);

    /**
     * 查询产品包审核列表
     * 
     * @param productPackageExamine 产品包审核
     * @return 产品包审核集合
     */
    public List<productPackageExamine> selectproductPackageExamineList(productPackageExamine productPackageExamine);

    /**
     * 新增产品包审核
     * 
     * @param productPackageExamine 产品包审核
     * @return 结果
     */
    public int insertproductPackageExamine(productPackageExamine productPackageExamine);

    /**
     * 修改产品包审核
     * 
     * @param productPackageExamine 产品包审核
     * @return 结果
     */
    public int updateproductPackageExamine(productPackageExamine productPackageExamine);

    /**
     * 批量删除产品包审核
     * 
     * @param productpackagenos 需要删除的产品包审核ID
     * @return 结果
     */
    public int deleteproductPackageExamineByIds(String[] productpackagenos);

    /**
     * 删除产品包审核信息
     * 
     * @param productpackageno 产品包审核ID
     * @return 结果
     */
    public int deleteproductPackageExamineById(String productpackageno);
}

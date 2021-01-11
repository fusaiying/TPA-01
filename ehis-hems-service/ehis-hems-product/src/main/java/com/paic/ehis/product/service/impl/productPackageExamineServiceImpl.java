package com.paic.ehis.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.product.mapper.productPackageExamineMapper;
import com.paic.ehis.product.domain.productPackageExamine;
import com.paic.ehis.product.service.IproductPackageExamineService;

/**
 * 产品包审核Service业务层处理
 * 
 * @author sino
 * @date 2020-09-22
 */
@Service
public class productPackageExamineServiceImpl implements IproductPackageExamineService 
{
    @Autowired
    private productPackageExamineMapper productPackageExamineMapper;

    /**
     * 查询产品包审核
     * 
     * @param productpackageno 产品包审核ID
     * @return 产品包审核
     */
    @Override
    public productPackageExamine selectproductPackageExamineById(String productpackageno)
    {
        return productPackageExamineMapper.selectproductPackageExamineById(productpackageno);
    }

    /**
     * 查询产品包审核列表
     * 
     * @param productPackageExamine 产品包审核
     * @return 产品包审核
     */
    @Override
    public List<productPackageExamine> selectproductPackageExamineList(productPackageExamine productPackageExamine)
    {
        return productPackageExamineMapper.selectproductPackageExamineList(productPackageExamine);
    }

    /**
     * 新增产品包审核
     * 
     * @param productPackageExamine 产品包审核
     * @return 结果
     */
    @Override
    public int insertproductPackageExamine(productPackageExamine productPackageExamine)
    {
        return productPackageExamineMapper.insertproductPackageExamine(productPackageExamine);
    }

    /**
     * 修改产品包审核
     * 
     * @param productPackageExamine 产品包审核
     * @return 结果
     */
    @Override
    public int updateproductPackageExamine(productPackageExamine productPackageExamine)
    {
        return productPackageExamineMapper.updateproductPackageExamine(productPackageExamine);
    }

    /**
     * 批量删除产品包审核
     * 
     * @param productpackagenos 需要删除的产品包审核ID
     * @return 结果
     */
    @Override
    public int deleteproductPackageExamineByIds(String[] productpackagenos)
    {
        return productPackageExamineMapper.deleteproductPackageExamineByIds(productpackagenos);
    }

    /**
     * 删除产品包审核信息
     * 
     * @param productpackageno 产品包审核ID
     * @return 结果
     */
    @Override
    public int deleteproductPackageExamineById(String productpackageno)
    {
        return productPackageExamineMapper.deleteproductPackageExamineById(productpackageno);
    }
}

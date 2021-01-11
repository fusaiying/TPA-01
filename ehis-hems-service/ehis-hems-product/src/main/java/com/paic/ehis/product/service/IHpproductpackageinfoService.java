package com.paic.ehis.product.service;

import java.util.List;
import com.paic.ehis.product.domain.Hpproductpackageinfo;

/**
 * 产品包信息Service接口
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
public interface IHpproductpackageinfoService 
{
    /**
     * 查询产品包信息
     * 
     * @param productpackageno 产品包信息ID
     * @return 产品包信息
     */
    public Hpproductpackageinfo selectHpproductpackageinfoById(String productpackageno);

    /**
     * 查询产品包信息列表
     * 
     * @param hpproductpackageinfo 产品包信息
     * @return 产品包信息集合
     */
    public List<Hpproductpackageinfo> selectHpproductpackageinfoList(Hpproductpackageinfo hpproductpackageinfo);

    /**
     * 新增产品包信息
     * 
     * @param hpproductpackageinfo 产品包信息
     * @return 结果
     */
    public int insertHpproductpackageinfo(Hpproductpackageinfo hpproductpackageinfo);

    /**
     * 修改产品包信息
     * 
     * @param hpproductpackageinfo 产品包信息
     * @return 结果
     */
    public int updateHpproductpackageinfo(Hpproductpackageinfo hpproductpackageinfo);

    /**
     * 批量删除产品包信息
     * 
     * @param productpackagenos 需要删除的产品包信息ID
     * @return 结果
     */
    public int deleteHpproductpackageinfoByIds(String[] productpackagenos);

    /**
     * 删除产品包信息信息
     * 
     * @param productpackageno 产品包信息ID
     * @return 结果
     */
    public int deleteHpproductpackageinfoById(String productpackageno);
}

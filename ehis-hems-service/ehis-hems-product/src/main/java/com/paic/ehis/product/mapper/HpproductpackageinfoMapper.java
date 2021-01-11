package com.paic.ehis.product.mapper;

import java.util.List;
import com.paic.ehis.product.domain.Hpproductpackageinfo;

/**
 * 产品包信息Mapper接口
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
public interface HpproductpackageinfoMapper 
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
     * 删除产品包信息
     * 
     * @param productpackageno 产品包信息ID
     * @return 结果
     */
    public int deleteHpproductpackageinfoById(String productpackageno);

    /**
     * 批量删除产品包信息
     * 
     * @param productpackagenos 需要删除的数据ID
     * @return 结果
     */
    public int deleteHpproductpackageinfoByIds(String[] productpackagenos);
}

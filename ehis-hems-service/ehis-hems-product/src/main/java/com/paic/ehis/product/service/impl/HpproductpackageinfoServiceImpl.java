package com.paic.ehis.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.product.mapper.HpproductpackageinfoMapper;
import com.paic.ehis.product.domain.Hpproductpackageinfo;
import com.paic.ehis.product.service.IHpproductpackageinfoService;

/**
 * 产品包信息Service业务层处理
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
@Service
public class HpproductpackageinfoServiceImpl implements IHpproductpackageinfoService 
{
    @Autowired
    private HpproductpackageinfoMapper hpproductpackageinfoMapper;

    /**
     * 查询产品包信息
     * 
     * @param productpackageno 产品包信息ID
     * @return 产品包信息
     */
    @Override
    public Hpproductpackageinfo selectHpproductpackageinfoById(String productpackageno)
    {
        return hpproductpackageinfoMapper.selectHpproductpackageinfoById(productpackageno);
    }

    /**
     * 查询产品包信息列表
     * 
     * @param hpproductpackageinfo 产品包信息
     * @return 产品包信息
     */
    @Override
    public List<Hpproductpackageinfo> selectHpproductpackageinfoList(Hpproductpackageinfo hpproductpackageinfo)
    {
        return hpproductpackageinfoMapper.selectHpproductpackageinfoList(hpproductpackageinfo);
    }

    /**
     * 新增产品包信息
     * 
     * @param hpproductpackageinfo 产品包信息
     * @return 结果
     */
    @Override
    public int insertHpproductpackageinfo(Hpproductpackageinfo hpproductpackageinfo)
    {
        return hpproductpackageinfoMapper.insertHpproductpackageinfo(hpproductpackageinfo);
    }

    /**
     * 修改产品包信息
     * 
     * @param hpproductpackageinfo 产品包信息
     * @return 结果
     */
    @Override
    public int updateHpproductpackageinfo(Hpproductpackageinfo hpproductpackageinfo)
    {
        return hpproductpackageinfoMapper.updateHpproductpackageinfo(hpproductpackageinfo);
    }

    /**
     * 批量删除产品包信息
     * 
     * @param productpackagenos 需要删除的产品包信息ID
     * @return 结果
     */
    @Override
    public int deleteHpproductpackageinfoByIds(String[] productpackagenos)
    {
        return hpproductpackageinfoMapper.deleteHpproductpackageinfoByIds(productpackagenos);
    }

    /**
     * 删除产品包信息信息
     * 
     * @param productpackageno 产品包信息ID
     * @return 结果
     */
    @Override
    public int deleteHpproductpackageinfoById(String productpackageno)
    {
        return hpproductpackageinfoMapper.deleteHpproductpackageinfoById(productpackageno);
    }
}

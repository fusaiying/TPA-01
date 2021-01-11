package com.paic.ehis.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.product.mapper.HmpkgsmsconfiginfoMapper;
import com.paic.ehis.product.domain.Hmpkgsmsconfiginfo;
import com.paic.ehis.product.service.IHmpkgsmsconfiginfoService;

/**
 * 产品包短信配置表Service业务层处理
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
@Service
public class HmpkgsmsconfiginfoServiceImpl implements IHmpkgsmsconfiginfoService 
{
    @Autowired
    private HmpkgsmsconfiginfoMapper hmpkgsmsconfiginfoMapper;

    /**
     * 查询产品包短信配置表
     * 
     * @param serialno 产品包短信配置表ID
     * @return 产品包短信配置表
     */
    @Override
    public Hmpkgsmsconfiginfo selectHmpkgsmsconfiginfoById(Long serialno)
    {
        return hmpkgsmsconfiginfoMapper.selectHmpkgsmsconfiginfoById(serialno);
    }

    /**
     * 查询产品包短信配置表列表
     * 
     * @param hmpkgsmsconfiginfo 产品包短信配置表
     * @return 产品包短信配置表
     */
    @Override
    public List<Hmpkgsmsconfiginfo> selectHmpkgsmsconfiginfoList(Hmpkgsmsconfiginfo hmpkgsmsconfiginfo)
    {
        return hmpkgsmsconfiginfoMapper.selectHmpkgsmsconfiginfoList(hmpkgsmsconfiginfo);
    }

    /**
     * 新增产品包短信配置表
     * 
     * @param hmpkgsmsconfiginfo 产品包短信配置表
     * @return 结果
     */
    @Override
    public int insertHmpkgsmsconfiginfo(Hmpkgsmsconfiginfo hmpkgsmsconfiginfo)
    {
        return hmpkgsmsconfiginfoMapper.insertHmpkgsmsconfiginfo(hmpkgsmsconfiginfo);
    }

    /**
     * 修改产品包短信配置表
     * 
     * @param hmpkgsmsconfiginfo 产品包短信配置表
     * @return 结果
     */
    @Override
    public int updateHmpkgsmsconfiginfo(Hmpkgsmsconfiginfo hmpkgsmsconfiginfo)
    {
        return hmpkgsmsconfiginfoMapper.updateHmpkgsmsconfiginfo(hmpkgsmsconfiginfo);
    }

    /**
     * 批量删除产品包短信配置表
     * 
     * @param serialnos 需要删除的产品包短信配置表ID
     * @return 结果
     */
    @Override
    public int deleteHmpkgsmsconfiginfoByIds(Long[] serialnos)
    {
        return hmpkgsmsconfiginfoMapper.deleteHmpkgsmsconfiginfoByIds(serialnos);
    }

    /**
     * 删除产品包短信配置表信息
     * 
     * @param serialno 产品包短信配置表ID
     * @return 结果
     */
    @Override
    public int deleteHmpkgsmsconfiginfoById(Long serialno)
    {
        return hmpkgsmsconfiginfoMapper.deleteHmpkgsmsconfiginfoById(serialno);
    }
}

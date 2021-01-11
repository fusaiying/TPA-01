package com.paic.ehis.base.service.impl;

import java.util.List;

import com.paic.ehis.base.domain.NetworkVO;
import com.paic.ehis.base.mapper.HmpServOperaMapper;
import com.paic.ehis.base.service.IHmpServOperaService;
import com.paic.ehis.common.core.exception.CustomException;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.base.domain.HmpComWebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.base.domain.HmpServOpera;

/**
 * 供应商管理1Service业务层处理
 * 
 * @author sino
 * @date 2020-09-21
 */
@Service
public class HmpServOperaServiceImpl implements IHmpServOperaService
{
    @Autowired
    private HmpServOperaMapper hmpServOperaMapper;

    /**
     * 查询供应商管理1
     * 
     * @param servcomno 供应商管理1ID
     * @return 供应商管理1
     */
    @Override
    public HmpServOpera selectHmpServOperaById(String servcomno)
    {
        HmpServOpera hmpServOpera = hmpServOperaMapper.selectHmpServOperaById(servcomno);
        // 设置地区数组
        String[] arr = {hmpServOpera.getProvince(), hmpServOpera.getCity(), hmpServOpera.getDistrict()};
        hmpServOpera.setSelectedOptions(arr);
        return hmpServOpera;
    }

    /**
     * 查询供应商管理1列表
     * 
     * @param hmpServOpera 供应商管理1
     * @return 供应商管理1
     */
    @Override
    public List<HmpServOpera> selectHmpServOperaList(HmpServOpera hmpServOpera)
    {
        return hmpServOperaMapper.selectHmpServOperaList(hmpServOpera);
    }

    /**
     * 新增供应商管理1
     * 
     * @param hmpServOpera 供应商管理1
     * @return 结果
     */
    @Override
    public int insertHmpServOpera(HmpServOpera hmpServOpera)
    {
        return hmpServOperaMapper.insertHmpServOpera(hmpServOpera);
    }

    /**
     * 修改供应商管理1
     * 
     * @param hmpServOpera 供应商管理1
     * @return 结果
     */
    @Override
    public int updateHmpServOpera(HmpServOpera hmpServOpera)
    {
        return hmpServOperaMapper.updateHmpServOpera(hmpServOpera);
    }

    /**
     * 批量删除供应商管理1
     * 
     * @param servcomnos 需要删除的供应商管理1ID
     * @return 结果
     */
    @Override
    public int deleteHmpServOperaByIds(String[] servcomnos)
    {
        return hmpServOperaMapper.deleteHmpServOperaByIds(servcomnos);
    }

    /**
     * 删除供应商管理1信息
     * 
     * @param servcomno 供应商管理1ID
     * @return 结果
     */
    @Override
    public int deleteHmpServOperaById(String servcomno)
    {
        return hmpServOperaMapper.deleteHmpServOpera(servcomno);
    }

    @Override
    public List<NetworkVO> selectProviderNetwork(HmpServOpera hmpServOpera) {
        return hmpServOperaMapper.selectProviderNetwork(hmpServOpera);
    }

    @Override
    public List<HmpComWebSite> selectHmpComWebSiteByNo(HmpComWebSite hmpComWebSite) {
        return hmpServOperaMapper.selectHmpComWebSiteByNo(hmpComWebSite);
    }

    @Override
    public int deleteHmpComWebSiteByIds(String[] websitecode) {
        return hmpServOperaMapper.deleteHmpComWebSiteByIds(websitecode);
    }

    @Override
    public int updateHmpComWebSiteById(HmpComWebSite hmpComWebSite) {
        return hmpServOperaMapper.updateHmpComWebSiteById(hmpComWebSite);
    }

    @Override
    public int insertHmpComWebSite(HmpComWebSite hmpComWebSite) {
        return hmpServOperaMapper.insertHmpComWebSite(hmpComWebSite);
    }

    // ======== 网点导入 ==========
    @Override
    public List<HmpComWebSite> selectHmpComWebSiteByNoTrans(HmpComWebSite hmpComWebSite) {
        return hmpServOperaMapper.selectHmpComWebSiteByNoTrans(hmpComWebSite);
    }

    @Override
    public int insertHmpComWebSiteList(List<HmpComWebSite> hmpComWebSites) {
        if(StringUtils.isNull(hmpComWebSites) || hmpComWebSites.size() == 0) {
            throw new CustomException("导入失败-网点导入数据不能为空");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        return hmpServOperaMapper.insertHmpComWebSiteList(hmpComWebSites);
    }

    @Override
    public HmpComWebSite selectHmpComWebSiteByCode(String websitecode) {
        return hmpServOperaMapper.selectHmpComWebSiteByCode(websitecode);
    }


}

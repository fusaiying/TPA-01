package com.paic.ehis.system.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.HmpServiceOrderApplicationMapper;
import com.paic.ehis.system.domain.HmpServiceOrderApplication;
import com.paic.ehis.system.service.IHmpServiceOrderApplicationService;

/**
 * 服务申请信息Service业务层处理
 * 
 * @author sino
 * @date 2020-11-17
 */
@Service
public class HmpServiceOrderApplicationServiceImpl implements IHmpServiceOrderApplicationService 
{
    @Autowired
    private HmpServiceOrderApplicationMapper hmpServiceOrderApplicationMapper;

    /**
     * 查询服务申请信息
     * 
     * @param applicationcode 服务申请信息ID
     * @return 服务申请信息
     */
    @Override
    public HmpServiceOrderApplication selectHmpServiceOrderApplicationById(String applicationcode)
    {
        return hmpServiceOrderApplicationMapper.selectHmpServiceOrderApplicationById(applicationcode);
    }

    /**
     * 查询服务申请信息列表
     * 
     * @param hmpServiceOrderApplication 服务申请信息
     * @return 服务申请信息
     */
    @Override
    public List<HmpServiceOrderApplication> selectHmpServiceOrderApplicationList(HmpServiceOrderApplication hmpServiceOrderApplication)
    {
        return hmpServiceOrderApplicationMapper.selectHmpServiceOrderApplicationList(hmpServiceOrderApplication);
    }

    /**
     * 新增服务申请信息
     * 
     * @param hmpServiceOrderApplication 服务申请信息
     * @return 结果
     */
    @Override
    public int insertHmpServiceOrderApplication(HmpServiceOrderApplication hmpServiceOrderApplication)
    {
        hmpServiceOrderApplication.setCreateTime(DateUtils.getNowDate());
        return hmpServiceOrderApplicationMapper.insertHmpServiceOrderApplication(hmpServiceOrderApplication);
    }

    /**
     * 修改服务申请信息
     * 
     * @param hmpServiceOrderApplication 服务申请信息
     * @return 结果
     */
    @Override
    public int updateHmpServiceOrderApplication(HmpServiceOrderApplication hmpServiceOrderApplication)
    {
        hmpServiceOrderApplication.setUpdateTime(DateUtils.getNowDate());
        return hmpServiceOrderApplicationMapper.updateHmpServiceOrderApplication(hmpServiceOrderApplication);
    }

    /**
     * 批量删除服务申请信息
     * 
     * @param applicationcodes 需要删除的服务申请信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpServiceOrderApplicationByIds(String[] applicationcodes)
    {
        return hmpServiceOrderApplicationMapper.deleteHmpServiceOrderApplicationByIds(applicationcodes);
    }

    /**
     * 删除服务申请信息信息
     * 
     * @param applicationcode 服务申请信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpServiceOrderApplicationById(String applicationcode)
    {
        return hmpServiceOrderApplicationMapper.deleteHmpServiceOrderApplicationById(applicationcode);
    }

    /**
     * 根据工单编号查询出服务申请信息
     * @param ordercode
     * @return
     */
    @Override
    public HmpServiceOrderApplication selectHmpServiceOrderAppByOrdercode(String ordercode) {
        return hmpServiceOrderApplicationMapper.selectHmpServiceOrderAppByOrdercode(ordercode);
    }
}

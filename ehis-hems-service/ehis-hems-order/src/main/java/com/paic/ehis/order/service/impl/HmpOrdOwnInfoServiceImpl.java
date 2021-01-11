package com.paic.ehis.order.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.order.mapper.HmpOrdOwnInfoMapper;
import com.paic.ehis.order.domain.HmpOrdOwnInfo;
import com.paic.ehis.order.service.IHmpOrdOwnInfoService;

/**
 * 个人订单Service业务层处理
 * 
 * @author sino
 * @date 2020-09-24
 */
@Service
public class HmpOrdOwnInfoServiceImpl implements IHmpOrdOwnInfoService
{
    @Autowired
    private HmpOrdOwnInfoMapper hmpordowninfoMapper;

    /**
     * 查询个人订单
     * 
     * @param personalorderno 个人订单ID
     * @return 个人订单
     */
    @Override
    public HmpOrdOwnInfo selectHmpOrdOwnInfoById(String personalorderno)
    {
        return hmpordowninfoMapper.selectHmpOrdOwnInfoById(personalorderno);
    }

    /**
     * 查询个人订单列表
     * 
     * @param hmpordowninfo 个人订单
     * @return 个人订单
     */
    @Override
    public List<HmpOrdOwnInfo> selectHmpOrdOwnInfoList(HmpOrdOwnInfo hmpordowninfo)
    {
        return hmpordowninfoMapper.selectHmpOrdOwnInfoList(hmpordowninfo);
    }

    /**
     * 新增个人订单
     * 
     * @param hmpordowninfo 个人订单
     * @return 结果
     */
    @Override
    public int insertHmpOrdOwnInfo(HmpOrdOwnInfo hmpordowninfo)
    {
        return hmpordowninfoMapper.insertHmpOrdOwnInfo(hmpordowninfo);
    }

    /**
     * 修改个人订单
     * 
     * @param hmpordowninfo 个人订单
     * @return 结果
     */
    @Override
    public int updateHmpOrdOwnInfo(HmpOrdOwnInfo hmpordowninfo)
    {
        return hmpordowninfoMapper.updateHmpOrdOwnInfo(hmpordowninfo);
    }

    /**
     * 批量删除个人订单
     * 
     * @param appnos 需要删除的个人订单ID
     * @return 结果
     */
    @Override
    public int deleteHmpOrdOwnInfoByIds(String[] appnos)
    {
        return hmpordowninfoMapper.deleteHmpOrdOwnInfoByIds(appnos);
    }

    /**
     * 删除个人订单信息
     * 
     * @param appno 个人订单ID
     * @return 结果
     */
    @Override
    public int deleteHmpOrdOwnInfoById(String appno)
    {
        return hmpordowninfoMapper.deleteHmpOrdOwnInfoById(appno);
    }
}

package com.paic.ehis.cs.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.mapper.AcceptDetailInfoMapper;
import com.paic.ehis.cs.service.IAcceptDetailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 受理详情 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-22
 */
@Service
public class AcceptDetailInfoServiceImpl implements IAcceptDetailInfoService
{
    @Autowired
    private AcceptDetailInfoMapper acceptDetailInfoMapper;

    /**
     * 查询受理详情 
     * 
     * @param workOrderNo 受理详情 ID
     * @return 受理详情 
     */
    @Override
    public AcceptDetailInfo selectAcceptDetailInfoById(String workOrderNo)
    {
        return acceptDetailInfoMapper.selectAcceptDetailInfoById(workOrderNo);
    }

    /**
     * 查询受理详情 列表
     * 
     * @param acceptDetailInfo 受理详情 
     * @return 受理详情 
     */
    @Override
    public List<AcceptDetailInfo> selectAcceptDetailInfoList(AcceptDetailInfo acceptDetailInfo)
    {
        return acceptDetailInfoMapper.selectAcceptDetailInfoList(acceptDetailInfo);
    }



    /**
     * 新增受理详情 
     * 
     * @param acceptDetailInfo 受理详情 
     * @return 结果
     */
    @Override
    public int insertAcceptDetailInfo(AcceptDetailInfo acceptDetailInfo)
    {
        acceptDetailInfo.setCreateTime(DateUtils.getNowDate());
        return acceptDetailInfoMapper.insertAcceptDetailInfo(acceptDetailInfo);
    }

    /**
     * 修改受理详情 
     * 
     * @param acceptDetailInfo 受理详情 
     * @return 结果
     */
    @Override
    public int updateAcceptDetailInfo(AcceptDetailInfo acceptDetailInfo)
    {
        acceptDetailInfo.setUpdateTime(DateUtils.getNowDate());
        return acceptDetailInfoMapper.updateAcceptDetailInfo(acceptDetailInfo);
    }

    /**
     * 批量删除受理详情 
     * 
     * @param workOrderNos 需要删除的受理详情 ID
     * @return 结果
     */
    @Override
    public int deleteAcceptDetailInfoByIds(String[] workOrderNos)
    {
        return acceptDetailInfoMapper.deleteAcceptDetailInfoByIds(workOrderNos);
    }

    /**
     * 删除受理详情 信息
     * 
     * @param workOrderNo 受理详情 ID
     * @return 结果
     */
    @Override
    public int deleteAcceptDetailInfoById(String workOrderNo)
    {
        return acceptDetailInfoMapper.deleteAcceptDetailInfoById(workOrderNo);
    }
}

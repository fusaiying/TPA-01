package com.paic.ehis.order.service.impl;

import com.paic.ehis.order.domain.BaseBalanceDetail;
import com.paic.ehis.order.domain.BaseBalanceInfo;
import com.paic.ehis.order.mapper.BaseBalanceDetailMapper;
import com.paic.ehis.order.service.IBaseBalanceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base_balance_detail(服务结算明细信息)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-21
 */
@Service
public class BaseBalanceDetailServiceImpl implements IBaseBalanceDetailService 
{
    @Autowired
    private BaseBalanceDetailMapper baseBalanceDetailMapper;

    /**
     * 查询base_balance_detail(服务结算明细信息)
     * 
     * @param serialNo base_balance_detail(服务结算明细信息)ID
     * @return base_balance_detail(服务结算明细信息)
     */
    @Override
    public BaseBalanceDetail selectBaseBalanceDetailById(Long serialNo)
    {
        return baseBalanceDetailMapper.selectBaseBalanceDetailById(serialNo);
    }

    /**
     * 查询base_balance_detail(服务结算明细信息)列表
     * 
     * @param baseBalanceDetail base_balance_detail(服务结算明细信息)
     * @return base_balance_detail(服务结算明细信息)
     */
    @Override
    public List<BaseBalanceDetail> selectBaseBalanceDetailList(BaseBalanceDetail baseBalanceDetail)
    {
        return baseBalanceDetailMapper.selectBaseBalanceDetailList(baseBalanceDetail);
    }

    /**
     * 新增base_balance_detail(服务结算明细信息)
     * 
     * @param baseBalanceDetail base_balance_detail(服务结算明细信息)
     * @return 结果
     */
    @Override
    public int insertBaseBalanceDetail(BaseBalanceDetail baseBalanceDetail)
    {
        return baseBalanceDetailMapper.insertBaseBalanceDetail(baseBalanceDetail);
    }

    /**
     * 修改base_balance_detail(服务结算明细信息)
     * 
     * @param baseBalanceDetail base_balance_detail(服务结算明细信息)
     * @return 结果
     */
    @Override
    public int updateBaseBalanceDetail(BaseBalanceDetail baseBalanceDetail)
    {
        return baseBalanceDetailMapper.updateBaseBalanceDetail(baseBalanceDetail);
    }

    /**
     * 批量删除base_balance_detail(服务结算明细信息)
     * 
     * @param serialNos 需要删除的base_balance_detail(服务结算明细信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseBalanceDetailByIds(Long[] serialNos)
    {
        return baseBalanceDetailMapper.deleteBaseBalanceDetailByIds(serialNos);
    }

    /**
     * 删除base_balance_detail(服务结算明细信息)信息
     * 
     * @param serialNo base_balance_detail(服务结算明细信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseBalanceDetailById(Long serialNo)
    {
        return baseBalanceDetailMapper.deleteBaseBalanceDetailById(serialNo);
    }

    /**
     * 查询base_balance_detail(服务结算明细信息)列表
     *
     * @param baseBalanceDetail base_balance_detail(服务结算明细信息)
     * @return base_balance_detail(服务结算明细信息)
     */
    @Override
    public List<BaseBalanceDetail> selectBaseBalanceDetailList2(BaseBalanceDetail baseBalanceDetail)
    {
        return baseBalanceDetailMapper.selectBaseBalanceDetailList2(baseBalanceDetail);
    }

    /**
     * 按case结算明细列表
     *
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return base_balance_detail(服务结算明细信息)集合
     */
    @Override
    public List<BaseBalanceDetail> selectDetailList(BaseBalanceInfo baseBalanceInfo)
    {
        return baseBalanceDetailMapper.selectDetailList(baseBalanceInfo);
    }
}

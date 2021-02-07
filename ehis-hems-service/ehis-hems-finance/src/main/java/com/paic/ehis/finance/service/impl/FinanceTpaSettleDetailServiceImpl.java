package com.paic.ehis.finance.service.impl;


import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.finance.domain.FinanceTpaSettleDetail;
import com.paic.ehis.finance.mapper.FinanceTpaSettleDetailMapper;
import com.paic.ehis.finance.service.IFinanceTpaSettleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TPA服务费结算明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-30
 */
@Service
public class FinanceTpaSettleDetailServiceImpl implements IFinanceTpaSettleDetailService
{
    @Autowired
    private FinanceTpaSettleDetailMapper financeTpaSettleDetailMapper;

    /**
     * 查询TPA服务费结算明细
     * 
     * @param detailId TPA服务费结算明细ID
     * @return TPA服务费结算明细
     */
    @Override
    public FinanceTpaSettleDetail selectFinanceTpaSettleDetailById(Long detailId)
    {
        return financeTpaSettleDetailMapper.selectFinanceTpaSettleDetailById(detailId);
    }

    /**
     * 查询TPA服务费结算明细列表
     * 
     * @param financeTpaSettleDetail TPA服务费结算明细
     * @return TPA服务费结算明细
     */
    @Override
    public List<FinanceTpaSettleDetail> selectFinanceTpaSettleDetailList(FinanceTpaSettleDetail financeTpaSettleDetail)
    {
        return financeTpaSettleDetailMapper.selectFinanceTpaSettleDetailList(financeTpaSettleDetail);
    }

    /**
     * 新增TPA服务费结算明细
     * 
     * @param financeTpaSettleDetail TPA服务费结算明细
     * @return 结果
     */
    @Override
    public int insertFinanceTpaSettleDetail(FinanceTpaSettleDetail financeTpaSettleDetail)
    {
        financeTpaSettleDetail.setCreateTime(DateUtils.getNowDate());
        return financeTpaSettleDetailMapper.insertFinanceTpaSettleDetail(financeTpaSettleDetail);
    }

    /**
     * 修改TPA服务费结算明细
     * 
     * @param financeTpaSettleDetail TPA服务费结算明细
     * @return 结果
     */
    @Override
    public int updateFinanceTpaSettleDetail(FinanceTpaSettleDetail financeTpaSettleDetail)
    {
        financeTpaSettleDetail.setUpdateTime(DateUtils.getNowDate());
        return financeTpaSettleDetailMapper.updateFinanceTpaSettleDetail(financeTpaSettleDetail);
    }

    /**
     * 批量删除TPA服务费结算明细
     * 
     * @param detailIds 需要删除的TPA服务费结算明细ID
     * @return 结果
     */
    @Override
    public int deleteFinanceTpaSettleDetailByIds(Long[] detailIds)
    {
        return financeTpaSettleDetailMapper.deleteFinanceTpaSettleDetailByIds(detailIds);
    }

    /**
     * 删除TPA服务费结算明细信息
     * 
     * @param detailId TPA服务费结算明细ID
     * @return 结果
     */
    @Override
    public int deleteFinanceTpaSettleDetailById(Long detailId)
    {
        return financeTpaSettleDetailMapper.deleteFinanceTpaSettleDetailById(detailId);
    }
}

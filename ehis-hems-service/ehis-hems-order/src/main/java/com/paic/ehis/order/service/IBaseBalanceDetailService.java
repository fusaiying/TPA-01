package com.paic.ehis.order.service;

import com.paic.ehis.order.domain.BaseBalanceDetail;
import com.paic.ehis.order.domain.BaseBalanceInfo;

import java.util.List;

/**
 * base_balance_detail(服务结算明细信息)Service接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface IBaseBalanceDetailService 
{
    /**
     * 查询base_balance_detail(服务结算明细信息)
     * 
     * @param serialNo base_balance_detail(服务结算明细信息)ID
     * @return base_balance_detail(服务结算明细信息)
     */
    public BaseBalanceDetail selectBaseBalanceDetailById(Long serialNo);

    /**
     * 查询base_balance_detail(服务结算明细信息)列表
     * 
     * @param baseBalanceDetail base_balance_detail(服务结算明细信息)
     * @return base_balance_detail(服务结算明细信息)集合
     */
    public List<BaseBalanceDetail> selectBaseBalanceDetailList(BaseBalanceDetail baseBalanceDetail);

    /**
     * 新增base_balance_detail(服务结算明细信息)
     * 
     * @param baseBalanceDetail base_balance_detail(服务结算明细信息)
     * @return 结果
     */
    public int insertBaseBalanceDetail(BaseBalanceDetail baseBalanceDetail);

    /**
     * 修改base_balance_detail(服务结算明细信息)
     * 
     * @param baseBalanceDetail base_balance_detail(服务结算明细信息)
     * @return 结果
     */
    public int updateBaseBalanceDetail(BaseBalanceDetail baseBalanceDetail);

    /**
     * 批量删除base_balance_detail(服务结算明细信息)
     * 
     * @param serialNos 需要删除的base_balance_detail(服务结算明细信息)ID
     * @return 结果
     */
    public int deleteBaseBalanceDetailByIds(Long[] serialNos);

    /**
     * 删除base_balance_detail(服务结算明细信息)信息
     * 
     * @param serialNo base_balance_detail(服务结算明细信息)ID
     * @return 结果
     */
    public int deleteBaseBalanceDetailById(Long serialNo);

    /**
     * 查询base_balance_detail(服务结算明细信息)列表
     *
     * @param baseBalanceDetail base_balance_detail(服务结算明细信息)
     * @return base_balance_detail(服务结算明细信息)集合
     */
    public List<BaseBalanceDetail> selectBaseBalanceDetailList2(BaseBalanceDetail baseBalanceDetail);

    /**
     * 按case结算明细列表
     *
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return base_balance_detail(服务结算明细信息)集合
     */
    public List<BaseBalanceDetail> selectDetailList(BaseBalanceInfo baseBalanceInfo);

}

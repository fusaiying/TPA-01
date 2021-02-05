package com.paic.ehis.order.service;

import com.paic.ehis.order.domain.ServiceBalanceDetail;
import com.paic.ehis.order.domain.ServiceBalanceInfo;
import com.paic.ehis.order.domain.vo.ServiceBalanceDetailCaseVO;
import com.paic.ehis.order.domain.vo.ServiceBalanceDetailContVO;
import com.paic.ehis.order.domain.vo.ServiceBalanceDetailPriceVO;

import java.util.List;

/**
 * service_balance_detail(服务结算明细信息)Service接口
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
public interface IServiceBalanceDetailService 
{
    /**
     * 查询service_balance_detail(服务结算明细信息)
     * 
     * @param serialNo service_balance_detail(服务结算明细信息)ID
     * @return service_balance_detail(服务结算明细信息)
     */
    public ServiceBalanceDetail selectServiceBalanceDetailById(Long serialNo);

    /**
     * 查询service_balance_detail(服务结算明细信息)列表
     * 
     * @param serviceBalanceDetail service_balance_detail(服务结算明细信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    public List<ServiceBalanceDetail> selectServiceBalanceDetailList(ServiceBalanceDetail serviceBalanceDetail);

    /**
     * 新增service_balance_detail(服务结算明细信息)
     * 
     * @param serviceBalanceDetail service_balance_detail(服务结算明细信息)
     * @return 结果
     */
    public int insertServiceBalanceDetail(ServiceBalanceDetail serviceBalanceDetail);

    /**
     * 修改service_balance_detail(服务结算明细信息)
     * 
     * @param serviceBalanceDetail service_balance_detail(服务结算明细信息)
     * @return 结果
     */
    public int updateServiceBalanceDetail(ServiceBalanceDetail serviceBalanceDetail);

    /**
     * 批量删除service_balance_detail(服务结算明细信息)
     * 
     * @param serialNos 需要删除的service_balance_detail(服务结算明细信息)ID
     * @return 结果
     */
    public int deleteServiceBalanceDetailByIds(Long[] serialNos);

    /**
     * 删除service_balance_detail(服务结算明细信息)信息
     * 
     * @param serialNo service_balance_detail(服务结算明细信息)ID
     * @return 结果
     */
    public int deleteServiceBalanceDetailById(Long serialNo);

    /**
     * 查询service_balance_detail(服务结算明细信息)列表
     *
     * @param serviceBalanceDetail service_balance_detail(服务结算明细信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    public List<ServiceBalanceDetail> selectServiceBalanceDetailList2(ServiceBalanceDetail serviceBalanceDetail);

    /**
     * 查询service_balance_detail(服务结算明细信息)列表
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    public List<ServiceBalanceDetail> selectDetailList(ServiceBalanceInfo serviceBalanceInfo);

    /**
     * 查询service_balance_detail(服务结算明细信息)列表-按case
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    public List<ServiceBalanceDetailCaseVO> selectServiceBalanceDetailList_case(ServiceBalanceInfo serviceBalanceInfo);

    /**
     * 查询service_balance_detail(服务结算明细信息)列表-按人头
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    public List<ServiceBalanceDetailContVO> selectServiceBalanceDetailList_cont(ServiceBalanceInfo serviceBalanceInfo);

    /**
     * 查询service_balance_detail(服务结算明细信息)列表-按一口价
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    public List<ServiceBalanceDetailPriceVO> selectServiceBalanceDetailList_price(ServiceBalanceInfo serviceBalanceInfo);

}

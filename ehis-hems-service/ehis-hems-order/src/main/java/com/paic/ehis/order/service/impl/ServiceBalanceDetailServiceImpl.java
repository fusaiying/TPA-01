package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.order.domain.ServiceBalanceDetail;
import com.paic.ehis.order.domain.ServiceBalanceInfo;
import com.paic.ehis.order.domain.vo.ServiceBalanceDetailCaseVO;
import com.paic.ehis.order.domain.vo.ServiceBalanceDetailContVO;
import com.paic.ehis.order.domain.vo.ServiceBalanceDetailPriceVO;
import com.paic.ehis.order.mapper.ServiceBalanceDetailMapper;
import com.paic.ehis.order.service.IServiceBalanceDetailService;
import com.paic.ehis.order.service.enumeration.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service_balance_detail(服务结算明细信息)Service业务层处理
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
@Service
public class ServiceBalanceDetailServiceImpl implements IServiceBalanceDetailService
{
    @Autowired
    private ServiceBalanceDetailMapper serviceBalanceDetailMapper;

    /**
     * 查询service_balance_detail(服务结算明细信息)
     * 
     * @param serialNo service_balance_detail(服务结算明细信息)ID
     * @return service_balance_detail(服务结算明细信息)
     */
    @Override
    public ServiceBalanceDetail selectServiceBalanceDetailById(Long serialNo)
    {
        return serviceBalanceDetailMapper.selectServiceBalanceDetailById(serialNo);
    }

    /**
     * 查询service_balance_detail(服务结算明细信息)列表
     * 
     * @param serviceBalanceDetail service_balance_detail(服务结算明细信息)
     * @return service_balance_detail(服务结算明细信息)
     */
    @Override
    public List<ServiceBalanceDetail> selectServiceBalanceDetailList(ServiceBalanceDetail serviceBalanceDetail)
    {
        return serviceBalanceDetailMapper.selectServiceBalanceDetailList(serviceBalanceDetail);
    }

    /**
     * 新增service_balance_detail(服务结算明细信息)
     * 
     * @param serviceBalanceDetail service_balance_detail(服务结算明细信息)
     * @return 结果
     */
    @Override
    public int insertServiceBalanceDetail(ServiceBalanceDetail serviceBalanceDetail)
    {
        serviceBalanceDetail.setCreateTime(DateUtils.getNowDate());
        serviceBalanceDetail.setUpdateTime(DateUtils.getNowDate());
        serviceBalanceDetail.setCreateBy(SecurityUtils.getUsername());
        serviceBalanceDetail.setUpdateBy(SecurityUtils.getUsername());
        serviceBalanceDetail.setStatus(StatusEnum.VALID.getCode());
        return serviceBalanceDetailMapper.insertServiceBalanceDetail(serviceBalanceDetail);
    }

    /**
     * 修改service_balance_detail(服务结算明细信息)
     * 
     * @param serviceBalanceDetail service_balance_detail(服务结算明细信息)
     * @return 结果
     */
    @Override
    public int updateServiceBalanceDetail(ServiceBalanceDetail serviceBalanceDetail)
    {
        serviceBalanceDetail.setUpdateTime(DateUtils.getNowDate());
        serviceBalanceDetail.setUpdateBy(SecurityUtils.getUsername());
        return serviceBalanceDetailMapper.updateServiceBalanceDetail(serviceBalanceDetail);
    }

    /**
     * 批量删除service_balance_detail(服务结算明细信息)
     * 
     * @param serialNos 需要删除的service_balance_detail(服务结算明细信息)ID
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceDetailByIds(Long[] serialNos)
    {
        return serviceBalanceDetailMapper.deleteServiceBalanceDetailByIds(serialNos);
    }

    /**
     * 删除service_balance_detail(服务结算明细信息)信息
     * 
     * @param serialNo service_balance_detail(服务结算明细信息)ID
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceDetailById(Long serialNo)
    {
        return serviceBalanceDetailMapper.deleteServiceBalanceDetailById(serialNo);
    }

    /**
     * 查询service_balance_detail(服务结算明细信息)列表
     *
     * @param serviceBalanceDetail service_balance_detail(服务结算明细信息)
     * @return service_balance_detail(服务结算明细信息)
     */
    @Override
    public List<ServiceBalanceDetail> selectServiceBalanceDetailList2(ServiceBalanceDetail serviceBalanceDetail)
    {
        return serviceBalanceDetailMapper.selectServiceBalanceDetailList2(serviceBalanceDetail);
    }

    /**
     * 查询service_balance_detail(服务结算明细信息)列表
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    @Override
    public List<ServiceBalanceDetail> selectDetailList(ServiceBalanceInfo serviceBalanceInfo)
    {
        return serviceBalanceDetailMapper.selectDetailList(serviceBalanceInfo);
    }

    /**
     * 查询service_balance_detail(服务结算明细信息)列表-按case
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    @Override
    public List<ServiceBalanceDetailCaseVO> selectServiceBalanceDetailList_case(ServiceBalanceInfo serviceBalanceInfo) {
        return serviceBalanceDetailMapper.selectServiceBalanceDetailList_case(serviceBalanceInfo);
    }

    /**
     * 查询service_balance_detail(服务结算明细信息)列表-按人头
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    @Override
    public List<ServiceBalanceDetailContVO> selectServiceBalanceDetailList_cont(ServiceBalanceInfo serviceBalanceInfo) {
        return serviceBalanceDetailMapper.selectServiceBalanceDetailList_cont(serviceBalanceInfo);
    }

    /**
     * 查询service_balance_detail(服务结算明细信息)列表-按一口价
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_detail(服务结算明细信息)集合
     */
    @Override
    public List<ServiceBalanceDetailPriceVO> selectServiceBalanceDetailList_price(ServiceBalanceInfo serviceBalanceInfo) {
        return serviceBalanceDetailMapper.selectServiceBalanceDetailList_price(serviceBalanceInfo);
    }
}

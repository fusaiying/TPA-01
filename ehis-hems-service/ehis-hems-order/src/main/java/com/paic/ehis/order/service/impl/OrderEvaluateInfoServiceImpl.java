package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.order.domain.OrderEvaluateInfo;
import com.paic.ehis.order.mapper.OrderEvaluateInfoMapper;
import com.paic.ehis.order.service.IOrderEvaluateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * order_evaluate_info(服务评价信息)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-26
 */
@Service
public class OrderEvaluateInfoServiceImpl implements IOrderEvaluateInfoService 
{
    @Autowired
    private OrderEvaluateInfoMapper orderEvaluateInfoMapper;

    /**
     * 查询order_evaluate_info(服务评价信息)
     * 
     * @param serialNo order_evaluate_info(服务评价信息)ID
     * @return order_evaluate_info(服务评价信息)
     */
    @Override
    public OrderEvaluateInfo selectOrderEvaluateInfoById(String serialNo)
    {
        return orderEvaluateInfoMapper.selectOrderEvaluateInfoById(serialNo);
    }

    /**
     * 查询order_evaluate_info(服务评价信息)列表
     * 
     * @param orderEvaluateInfo order_evaluate_info(服务评价信息)
     * @return order_evaluate_info(服务评价信息)
     */
    @Override
    public List<OrderEvaluateInfo> selectOrderEvaluateInfoList(OrderEvaluateInfo orderEvaluateInfo)
    {
        return orderEvaluateInfoMapper.selectOrderEvaluateInfoListNew(orderEvaluateInfo);
    }

    /**
     * 新增order_evaluate_info(服务评价信息)
     * 
     * @param orderEvaluateInfo order_evaluate_info(服务评价信息)
     * @return 结果
     */
    @Override
    public int insertOrderEvaluateInfo(OrderEvaluateInfo orderEvaluateInfo)
    {
        orderEvaluateInfo.setCreateTime(DateUtils.getNowDate());
        return orderEvaluateInfoMapper.insertOrderEvaluateInfo(orderEvaluateInfo);
    }

    /**
     * 修改order_evaluate_info(服务评价信息)
     * 
     * @param orderEvaluateInfo order_evaluate_info(服务评价信息)
     * @return 结果
     */
    @Override
    public int updateOrderEvaluateInfo(OrderEvaluateInfo orderEvaluateInfo)
    {
        orderEvaluateInfo.setUpdateTime(DateUtils.getNowDate());
        return orderEvaluateInfoMapper.updateOrderEvaluateInfo(orderEvaluateInfo);
    }

    /**
     * 批量删除order_evaluate_info(服务评价信息)
     * 
     * @param serialNos 需要删除的order_evaluate_info(服务评价信息)ID
     * @return 结果
     */
    @Override
    public int deleteOrderEvaluateInfoByIds(String[] serialNos)
    {
        return orderEvaluateInfoMapper.deleteOrderEvaluateInfoByIds(serialNos);
    }

    /**
     * 删除order_evaluate_info(服务评价信息)信息
     * 
     * @param serialNo order_evaluate_info(服务评价信息)ID
     * @return 结果
     */
    @Override
    public int deleteOrderEvaluateInfoById(String serialNo)
    {
        return orderEvaluateInfoMapper.deleteOrderEvaluateInfoById(serialNo);
    }
}

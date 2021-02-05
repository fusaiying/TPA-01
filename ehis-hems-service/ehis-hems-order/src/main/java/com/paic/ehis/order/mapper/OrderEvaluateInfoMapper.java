package com.paic.ehis.order.mapper;

import com.paic.ehis.order.domain.OrderEvaluateInfo;

import java.util.List;

/**
 * order_evaluate_info(服务评价信息)Mapper接口
 * 
 * @author sino
 * @date 2021-01-26
 */
public interface OrderEvaluateInfoMapper 
{
    /**
     * 查询order_evaluate_info(服务评价信息)
     * 
     * @param serialNo order_evaluate_info(服务评价信息)ID
     * @return order_evaluate_info(服务评价信息)
     */
    public OrderEvaluateInfo selectOrderEvaluateInfoById(String serialNo);

    /**
     * 查询order_evaluate_info(服务评价信息)列表
     * 
     * @param orderEvaluateInfo order_evaluate_info(服务评价信息)
     * @return order_evaluate_info(服务评价信息)集合
     */
    public List<OrderEvaluateInfo> selectOrderEvaluateInfoList(OrderEvaluateInfo orderEvaluateInfo);

    public List<OrderEvaluateInfo> selectOrderEvaluateInfoListNew(OrderEvaluateInfo orderEvaluateInfo);

    /**
     * 新增order_evaluate_info(服务评价信息)
     * 
     * @param orderEvaluateInfo order_evaluate_info(服务评价信息)
     * @return 结果
     */
    public int insertOrderEvaluateInfo(OrderEvaluateInfo orderEvaluateInfo);

    /**
     * 修改order_evaluate_info(服务评价信息)
     * 
     * @param orderEvaluateInfo order_evaluate_info(服务评价信息)
     * @return 结果
     */
    public int updateOrderEvaluateInfo(OrderEvaluateInfo orderEvaluateInfo);

    /**
     * 删除order_evaluate_info(服务评价信息)
     * 
     * @param serialNo order_evaluate_info(服务评价信息)ID
     * @return 结果
     */
    public int deleteOrderEvaluateInfoById(String serialNo);

    /**
     * 批量删除order_evaluate_info(服务评价信息)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderEvaluateInfoByIds(String[] serialNos);
}

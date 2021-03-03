package com.paic.ehis.order.mapper;

import com.paic.ehis.order.domain.*;

import java.util.List;

/**
 * order_info(工单信息)Mapper接口
 * 
 * @author sino
 * @date 2021-01-26
 */
public interface OrderBussinessInfoMapper
{
    /**
     * 查询order_info(工单信息)
     * 
     * @param role order_info(工单信息)ID
     * @return order_info(工单信息)
     */
    public RoleLoginInfo checkpassWord(String role);

    /**
     * 获取用户信息
     */
    public RoleLoginInfo getUser(RoleLoginInfo roleLoginInfo);

    /**
     * 变更用户是否第一次登录
     */
    public int updateIsLogin(RoleLoginInfo roleLoginInfo);

    /**
     * 变更用户的密码
     */
    public int updatePassword(RoleLoginInfo roleLoginInfo);


    /**
     * 查询接单总数
     */
    public int getReceivedTimes(String role);

    /**
     * 查询已完成接单
     */
    public int getCompletionTimes(String role);

    /**
     * 查询进行中接单
     */
    public int getProgressTimes(String role);

    /**
     * 查询不同节点状态下的工单列表
     */
    public List<OrderInfo> getOrderList(RoleLoginInfo roleLoginInfo);


    public OrderInfo getOrderDetail(RoleLoginInfo roleLoginInfo);

    public String getPlacename(String placecode);

    /**
     * 接单操作
     */
    public int receivingOrder(RoleLoginInfo roleLoginInfo);

    /**
     * 实施操作
     */
    public int implementtOrder(OrderInfo orderInfo);

    public List<Sn_base_addressDO> selectProvinceByPlacetype(AddressInfo addressInfo);

    public List<Sn_base_addressDO> selectCityByPlacetype(AddressInfo addressInfo);

    public List<Sn_base_addressDO> selectDistrictByPlacetype(AddressInfo addressInfo);

    public int insertOrderTrack(OrderTrack orderTrack);

    public List<OrderTrack> getDealList(OrderInfo orderInfo);
}

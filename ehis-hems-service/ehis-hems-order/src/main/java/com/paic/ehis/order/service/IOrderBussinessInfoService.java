package com.paic.ehis.order.service;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.order.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * order_info(工单信息)Service接口
 * 
 * @author sino
 * @date 2021-01-26
 */
public interface IOrderBussinessInfoService
{
    /**
     * 验证登录账号和密码
     * 
     * @param roleLoginInfo
     */
    public AjaxResult checkpassWord(RoleLoginInfo roleLoginInfo);

    /**
     * 获取用户信息
     *
     * @param roleLoginInfo
     */
    public RoleLoginInfo getUser(RoleLoginInfo roleLoginInfo);

    /**
     * 变更用户密码
     *
     * @param roleLoginInfo
     */
    public AjaxResult updatePassword(RoleLoginInfo roleLoginInfo);


    /**
     * 查询接单人拥有的接单数量
     *
     * @param roleLoginInfo
     */
    public OrderNumInfo getOrderNum(RoleLoginInfo roleLoginInfo);


    /**
     * 查询不同节点状态下的工单列表
     */
    public List<OrderInfo> getOrderList(RoleLoginInfo roleLoginInfo);


    /**
     * 查询不同节点状态下的工单列表
     */
    public OrderInfo getOrderDetail(RoleLoginInfo roleLoginInfo);


    /**
     * 接单操作
     getHosptialInfo */
    public int receivingOrder(RoleLoginInfo roleLoginInfo);


    /**
     * 实施操作
     */
    public int implementtOrder(OrderInfo orderInfo);


    public List<HospitalInfoVo> getHosptialInfo(AddressInfo addressInfo);


    public List<AddressVO> getAddress(AddressInfo addressInfo);

    public List<OrderTrack> getDealList(OrderInfo orderInfo);



}

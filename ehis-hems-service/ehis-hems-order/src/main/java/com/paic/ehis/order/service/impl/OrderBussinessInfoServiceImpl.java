package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.order.domain.*;
import com.paic.ehis.order.mapper.OrderBussinessInfoMapper;
import com.paic.ehis.order.mapper.OrderInfoMapper;
import com.paic.ehis.order.service.IOrderBussinessInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * order_info(工单信息)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-26
 */
@Service
@Slf4j
public class OrderBussinessInfoServiceImpl implements IOrderBussinessInfoService
{
    @Autowired
    private OrderBussinessInfoMapper orderBussinessInfoMapper;

    /**
     * 查询order_info(工单信息)
     * 
     * @param roleLoginInfo order_info(工单信息)ID
     * @return order_info(工单信息)
     */
    @Override
    public AjaxResult checkpassWord(RoleLoginInfo roleLoginInfo)
    {
        String role = roleLoginInfo.getRole();
        String password = roleLoginInfo.getPassword();
        //根据用户名查询密码
        RoleLoginInfo info = orderBussinessInfoMapper.checkpassWord(role);
        if(info != null){
            if(!password.equals(info.getPassword())){
                return AjaxResult.error("操作失败","用户名或密码不正确");
            }
        }else{
            return AjaxResult.error("操作失败","登录用户不存在");
        }
        return AjaxResult.success();
    }


    /**
     * 查询接单人拥有的接单数量
     *
     */
    @Override
    public OrderNumInfo getOrderNum(String role){
        OrderNumInfo orderNumInfo = new OrderNumInfo();
        //根据角色查询接单总数
        orderNumInfo.setReceivedTimes(orderBussinessInfoMapper.getReceivedTimes(role));
        //根据角色查询已完成接单
        orderNumInfo.setCompletionTimes(orderBussinessInfoMapper.getCompletionTimes(role));
        //根据角色查询进行中接单
        orderNumInfo.setProgressTimes(orderBussinessInfoMapper.getProgressTimes(role));
        return orderNumInfo;
    }


    /**
     * 查询不同节点状态下的工单列表
     */
    @Override
    public List<OrderInfo> getOrderList(RoleLoginInfo roleLoginInfo){
        return orderBussinessInfoMapper.getOrderList(roleLoginInfo);
    }


    /**
     * 查询不同节点状态下的工单列表
     */
    @Override
    public int receivingOrder(RoleLoginInfo roleLoginInfo){
        return orderBussinessInfoMapper.receivingOrder(roleLoginInfo);
    }


    /**
     * 实施操作
     */
    @Override
    public int implementtOrder(OrderInfo orderInfo){
        return orderBussinessInfoMapper.implementtOrder(orderInfo);
    }
}

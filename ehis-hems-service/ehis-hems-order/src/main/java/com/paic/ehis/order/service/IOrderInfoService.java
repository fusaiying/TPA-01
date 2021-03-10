package com.paic.ehis.order.service;

import com.paic.ehis.order.domain.*;
import com.paic.ehis.system.api.domain.HospitalInfoVo;

import java.util.List;

/**
 * order_info(工单信息)Service接口
 * 
 * @author sino
 * @date 2021-01-26
 */
public interface IOrderInfoService 
{
    /**
     * 查询order_info(工单信息)
     * 
     * @param orderInfo order_info(工单信息)ID
     * @return order_info(工单信息)
     */
    public OrderInfo selectOrderInfoById(OrderInfo orderInfo);

    /**
     * 查询order_info(工单信息)列表
     * 
     * @param orderInfo order_info(工单信息)
     * @return order_info(工单信息)集合
     */
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo);

    /**
     * 新增order_info(工单信息)
     * 
     * @param orderInfo order_info(工单信息)
     * @return 结果
     */
    public int insertOrderInfo(OrderInfo orderInfo);

    /**
     * 修改order_info(工单信息)
     * 
     * @param orderInfo order_info(工单信息)
     * @return 结果
     */
    public int updateOrderInfo(OrderInfo orderInfo);

    /**
     * 批量删除order_info(工单信息)
     * 
     * @param serialNos 需要删除的order_info(工单信息)ID
     * @return 结果
     */
    public int deleteOrderInfoByIds(String[] serialNos);

    /**
     * 删除order_info(工单信息)信息
     * 
     * @param serialNo order_info(工单信息)ID
     * @return 结果
     */
    public int deleteOrderInfoById(String serialNo);

    /* 获取供应商的以及联系人信息*/
    public List<SupplierInfoVo> getSupplierInfo();

    /* 获取产品下拉框*/
    public List<ProductInfo> getProductInfo();

    /* 获取医院以及科室信息*/
    public List<HospitalInfoVo> getHospitalInfoVo();

    /**
     * 查询服务-供应商-联系人联动关系
     */
    public List<SupplierInfoVo> getAllProSuppInfo(OrderInfo orderInfo);

    /**
     * 取消工单
     */
    public int cancalOrder(OrderInfo orderInfo);

   /* *//**
     * 根据产品查询服务信息
     *//*
    public List<ServcieInfoVo> getServiceBypro(OrderInfo orderInfo);*/

    //供应商超时进行自动分配
    public int getAutoMode();

    /*审核特药通过*/
    public int checkPass(OrderInfo orderInfo);

}

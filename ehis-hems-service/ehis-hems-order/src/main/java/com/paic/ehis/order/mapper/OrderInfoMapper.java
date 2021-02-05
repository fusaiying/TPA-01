package com.paic.ehis.order.mapper;

import com.paic.ehis.order.domain.*;

import java.util.List;

/**
 * order_info(工单信息)Mapper接口
 * 
 * @author sino
 * @date 2021-01-26
 */
public interface OrderInfoMapper 
{
    /**
     * 查询order_info(工单信息)
     * 
     * @param serialNo order_info(工单信息)ID
     * @return order_info(工单信息)
     */
    public OrderInfo selectOrderInfoById(String serialNo);

    public OrderInfo selectOrderInfoByIdNew(OrderInfo orderInfo);

    /**
     * 查询order_info(工单信息)列表
     * 
     * @param orderInfo order_info(工单信息)
     * @return order_info(工单信息)集合
     */
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo);


    /**
     * 查询order_info(工单信息)列表
     *
     * @param orderInfo order_info(工单信息)
     * @return order_info(工单信息)集合
     */
    public List<OrderInfo> selectOrderInfoListNew(OrderInfo orderInfo);

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
     * 删除order_info(工单信息)
     * 
     * @param serialNo order_info(工单信息)ID
     * @return 结果
     */
    public int deleteOrderInfoById(String serialNo);

    /**
     * 批量删除order_info(工单信息)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderInfoByIds(String[] serialNos);

    //获取供应商信息
    public List<SupplierInfoVo> getSupplierInfo();


    //获取联系人信息
    public List<ContractInfos> getContractInfo();

    //获取产品下拉框
    public List<ProductInfo> getProductInfo();

    //获取医院信息
    public List<HospitalInfoVo> getHospitalInfo();

    //获取一级科室
    public List<FirstDeptInfoVo> getFirstDeptInfo();

    //获取产耳机科室
    public List<SecondDeptInfoVo> getSecondDeptInfo();

    //根据服务编码查询
    public List<SupplierInfoVo> getSupplierInfoByService(OrderInfo orderInfo);

    //取消工单
    public int cancalOrder(OrderInfo orderInfo);

    //设置超时状态
    public int updateOverTime(OrderInfo orderInfo);

    public List<Ordertaking> getOrdertaking(OrderInfo orderInfo);

}

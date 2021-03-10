package com.paic.ehis.order.mapper;

import com.paic.ehis.order.domain.*;
import com.paic.ehis.system.api.domain.HospitalInfoVo;
import com.paic.ehis.system.api.domain.FirstDeptInfoVo;
import com.paic.ehis.system.api.domain.SecondDeptInfoVo;

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
     * @param orderCode order_info(工单信息)ID
     * @return order_info(工单信息)
     */
    public List<OrderInfo> selectOrderInfoById();

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
     * @param orderCode order_info(工单信息)ID
     * @return 结果
     */
    public int deleteOrderInfoById(String orderCode);

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

    //获取医院信息
    public List<HospitalInfoVo> getHospitalInfo1(AddressInfo addressInfo);

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

    //设置PC端的超时超时状态
    public int updateOverTimeToPC(OrderInfo orderInfo);

    //获取轮询中数据不为超时的数据
    public List<Ordertaking> getOrdertaking(OrderInfo orderInfo);

    //获取所有可预约的供应商
    public List<Ordertaking> getOrdertakingList(OrderInfo orderInfo);

    public int checkPass(OrderInfo orderInfo);
}

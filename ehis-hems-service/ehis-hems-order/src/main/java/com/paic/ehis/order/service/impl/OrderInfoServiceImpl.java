package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.order.domain.*;
import com.paic.ehis.order.mapper.OrderInfoMapper;
import com.paic.ehis.order.service.IOrderInfoService;
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
public class OrderInfoServiceImpl implements IOrderInfoService 
{
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 查询order_info(工单信息)
     * 
     * @param orderInfo order_info(工单信息)ID
     * @return order_info(工单信息)
     */
    @Override
    public OrderInfo selectOrderInfoById(OrderInfo orderInfo)
    {

        OrderInfo orderinfo = orderInfoMapper.selectOrderInfoByIdNew(orderInfo);
        if(!StringUtils.isBlank(orderinfo.getInpatientArea())){
            orderinfo.setInpatientAreaList(Arrays.asList((orderinfo.getInpatientArea().split(","))));
        }else{
            orderinfo.setInpatientAreaList(new ArrayList<>());
        }
        if(!StringUtils.isBlank(orderinfo.getVisitingArea())){
            orderinfo.setVisitingAreaList(Arrays.asList((orderinfo.getVisitingArea().split(","))));
        }else{
            orderinfo.setVisitingAreaList(new ArrayList<>());
        }
        if(!StringUtils.isBlank(orderinfo.getExpectedArea())){
            orderinfo.setExpectedAreaList(Arrays.asList((orderinfo.getExpectedArea().split(","))));
        }else{
            orderinfo.setExpectedAreaList(new ArrayList<>());
        }
        if(!StringUtils.isBlank(orderinfo.getCity())){
            orderinfo.setCityList(Arrays.asList((orderinfo.getCity().split(","))));
        }else{
            orderinfo.setCityList(new ArrayList<>());
        }
        return orderinfo;
    }

    /**
     * 查询order_info(工单信息)列表
     * 
     * @param orderInfo order_info(工单信息)
     * @return order_info(工单信息)
     */
    @Override
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo)
    {
        List<OrderInfo> orderInfos = orderInfoMapper.selectOrderInfoListNew(orderInfo);
        return orderInfos;
    }

    /**
     * 新增order_info(工单信息)
     * 
     * @param orderInfo order_info(工单信息)
     * @return 结果
     */
    @Override
    public int insertOrderInfo(OrderInfo orderInfo)
    {
        orderInfo.setCreateTime(DateUtils.getNowDate());
        return orderInfoMapper.insertOrderInfo(orderInfo);
    }

    /**
     * 修改order_info(工单信息)
     * 
     * @param orderInfo order_info(工单信息)
     * @return 结果
     */
    @Override
    public int updateOrderInfo(OrderInfo orderInfo)
    {
        orderInfo.setUpdateTime(DateUtils.getNowDate());
        orderInfo.setUpdateBy(SecurityUtils.getUsername());
        orderInfo.setVisitingArea(StringUtils.join(orderInfo.getVisitingAreaList(), ","));
        orderInfo.setInpatientArea(StringUtils.join(orderInfo.getInpatientAreaList(), ","));
        orderInfo.setExpectedArea(StringUtils.join(orderInfo.getExpectedAreaList(), ","));
        orderInfo.setCity(StringUtils.join(orderInfo.getCityList(), ","));
        return orderInfoMapper.updateOrderInfo(orderInfo);
    }

    /**
     * 批量删除order_info(工单信息)
     * 
     * @param serialNos 需要删除的order_info(工单信息)ID
     * @return 结果
     */
    @Override
    public int deleteOrderInfoByIds(String[] serialNos)
    {
        return orderInfoMapper.deleteOrderInfoByIds(serialNos);
    }

    /**
     * 删除order_info(工单信息)信息
     * 
     * @param serialNo order_info(工单信息)ID
     * @return 结果
     */
    @Override
    public int deleteOrderInfoById(String serialNo)
    {
        return orderInfoMapper.deleteOrderInfoById(serialNo);
    }

    /* 获取供应商的以及联系人信息*/
    @Override
    public List<SupplierInfoVo> getSupplierInfo(){

        log.info("********获取工单中服务chanchan供应商的人信息********");
        List<SupplierInfoVo> supplierInfos= orderInfoMapper.getSupplierInfo();
        /*List<ContractInfos> contractInfos= orderInfoMapper.getContractInfo();
        if(!supplierInfos.isEmpty()){
            for(SupplierInfoVo supplierInfoVo : supplierInfos){
                List<ContractInfos> contractInfoList = new ArrayList<>();
                for(ContractInfos ContractInfo:contractInfos){
                    if(ContractInfo.getSupplierCode().equals(supplierInfoVo.getSupplierCode())){
                        contractInfoList.add(ContractInfo);
                    }
                    supplierInfoVo.setContractInfo(contractInfoList);
                }
            }
        }*/
       return supplierInfos;
    }


    /* 获取产品下拉框*/
    @Override
    public List<ProductInfo> getProductInfo(){
        return orderInfoMapper.getProductInfo();
    }


    /* 获取医院以及科室信息*/
    @Override
    public List<HospitalInfoVo> getHospitalInfoVo(){
        log.info("********获取工单中医院以及科室信息********");
        //获取医院信息
        List<HospitalInfoVo> hospitalInfos= orderInfoMapper.getHospitalInfo();
        //获取一级科室
        List<FirstDeptInfoVo> firstDeptInfos= orderInfoMapper.getFirstDeptInfo();
        //获取二级科室
        List<SecondDeptInfoVo> secondDeptInfos= orderInfoMapper.getSecondDeptInfo();
        List<HospitalInfoVo> hospitalInfoList = new ArrayList();
        if(!hospitalInfos.isEmpty()){
            for(HospitalInfoVo hospitalInfo : hospitalInfos){
                List<FirstDeptInfoVo> firstDeptInfoList = new ArrayList();
                for(FirstDeptInfoVo firstDeptInfo :firstDeptInfos) {
                    if(hospitalInfo.getHospitalCode().equals(firstDeptInfo.getHospitalCode())){
                        firstDeptInfoList.add(firstDeptInfo);
                    }
                    List<SecondDeptInfoVo> secondDeptInfoList = new ArrayList();
                    for(SecondDeptInfoVo SecondDeptInfo :secondDeptInfos ){
                        if(SecondDeptInfo.getFirstDeptName().equals(firstDeptInfo.getFirstDeptName())){
                            secondDeptInfoList.add(SecondDeptInfo);
                        }
                        firstDeptInfo.setSecondDeptInfos(secondDeptInfoList);
                    }
                    hospitalInfo.setFirstdeptInfos(firstDeptInfoList);
                }
                hospitalInfoList.add(hospitalInfo);
            }
        }
        return hospitalInfoList;
    }

    @Override
    public List<SupplierInfoVo> getAllProSuppInfo(OrderInfo orderInfo)
    {
        log.info("********获取工单中服务-供应商-联系人的信息********");
        List<SupplierInfoVo> supplierInfos= orderInfoMapper.getSupplierInfoByService(orderInfo);
        List<ContractInfos> contractInfos= orderInfoMapper.getContractInfo();
        if(!supplierInfos.isEmpty()){
            for(SupplierInfoVo supplierInfoVo : supplierInfos){
                List<ContractInfos> contractInfoList = new ArrayList<>();
                for(ContractInfos ContractInfo:contractInfos){
                    if(ContractInfo.getSupplierCode().equals(supplierInfoVo.getSupplierCode())){
                        contractInfoList.add(ContractInfo);
                    }
                    supplierInfoVo.setContractInfo(contractInfoList);
                }
            }
        }
        return supplierInfos;
    }

    /**
     * 取消工单
     */
    @Override
    public int cancalOrder(OrderInfo orderInfo){
        return orderInfoMapper.cancalOrder(orderInfo);
    }

    /**
     * 根据产品查询服务信息
     *//*
    @Override
    public List<ServcieInfoVo> getServiceBypro(OrderInfo orderInfo){
        return orderInfoMapper.getServiceBypro(orderInfo);
    }*/


    //供应商超时进行自动分配
    @Override
    public int getAutoMode(){
        //1、获取所有待接单接单的工单信息
       //OrderInfo order = orderInfoMapper.selectOrderInfoById(orderInfo.getOrderCode());
        List<OrderInfo> orders = orderInfoMapper.selectOrderInfoById();
        for(OrderInfo order :orders){
            if(order.getMillisecond() < 0){
                //改变原先的供应商状态，重新分配供应商
                orderInfoMapper.updateOverTime(order);//设置超时状态
                //2、根据产品编码和服务编码查询可提供服务的供应商信息，可预约次数等信息
                List<Ordertaking> Ordertakings = orderInfoMapper.getOrdertaking(order);
                //3、提取可提供服务的供应商信息
        /*if(!Ordertakings.isEmpty()){
            for(Ordertaking ordertaking :Ordertakings){
                if(Integer.valueOf(ordertaking.getAvailablenum() )>0){ //
                    OrdertakingList.add(ordertaking);
                  *//*  flag = true;
                    order.setSupplierCode(ordertaking.getSupplierCode());
                    break;*//*
                }
            }*/
                if(!Ordertakings.isEmpty()){
                    //存在可预约的供应商，则开始分配供应商
                    order.setSupplierCode(Ordertakings.get(0).getSupplierCode());
                    order.setCreateTime(DateUtils.getNowDate());
                    order.setUpdateTime(DateUtils.getNowDate());
                    order.setCreateBy(SecurityUtils.getUsername());
                    order.setUpdateBy(SecurityUtils.getUsername());
                    order.setUpdateFlag("0");
                    order.setNodeStatus("01"); //节点状态待接单
                    order.setBussinessStatus("01"); //业务状态待处理
                    order.setStatus("Y");
                    order.setSerialNo(PubFun.createMySqlMaxNoUseCache("OrderInfoSer",12,12));
                    orderInfoMapper.insertOrderInfo(order);//重新分配的供应商信息
                }else{
                    //次数是否满足三次循环，不满足，进行新的循环，重新开始分配供应商
                    if(order.getForNum()+1<=3){
                        order.setForNum(order.getForNum()+1);
                        //查询所有可提供该服务的供应商,并将之前的数据删除
                        List<Ordertaking> OrdertakingList = orderInfoMapper.getOrdertakingList(order);
                        if(!OrdertakingList.isEmpty()){
                            order.setSupplierCode(OrdertakingList.get(0).getSupplierCode());
                            order.setCreateTime(DateUtils.getNowDate());
                            order.setUpdateTime(DateUtils.getNowDate());
                            order.setCreateBy(SecurityUtils.getUsername());
                            order.setUpdateBy(SecurityUtils.getUsername());
                            order.setUpdateFlag("0");
                            order.setNodeStatus("01"); //节点状态待接单
                            order.setBussinessStatus("01"); //业务状态待处理
                            order.setStatus("Y");
                            order.setSerialNo(PubFun.createMySqlMaxNoUseCache("OrderInfoSer",12,12));
                            orderInfoMapper.deleteOrderInfoById(order.getOrderCode());
                            orderInfoMapper.insertOrderInfo(order);//重新分配的供应商信息
                        }else{
                            orderInfoMapper.updateOverTimeToPC(order);
                        }

                    }else{
                        //进入到PC端的超时工单
                        orderInfoMapper.updateOverTimeToPC(order);
                    }
                }
            }

        }
        return 1;
    }


    /*审核特药通过*/
    @Override
    public int checkPass(OrderInfo orderInfo){
        return orderInfoMapper.checkPass(orderInfo);
    }
}

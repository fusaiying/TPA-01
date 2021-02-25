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

    @Autowired
    private OrderInfoMapper orderInfoMapper;

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


    /* 获取医院以及科室信息*/
    @Override
    public List<HospitalInfoVo> getHosptialInfo(AddressInfo addressInfo){
        log.info("********获取工单中医院以及科室信息********");
        //获取医院信息
        List<HospitalInfoVo> hospitalInfos= orderInfoMapper.getHospitalInfo1(addressInfo);
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
    public List<AddressVO> getAddress(AddressInfo addressInfo){
        List<Sn_base_addressDO> sn_base_addressList1 = orderBussinessInfoMapper.selectProvinceByPlacetype(addressInfo);
        List<Sn_base_addressDO> sn_base_addressList2 = orderBussinessInfoMapper.selectCityByPlacetype(addressInfo);
        List<Sn_base_addressDO> sn_base_addressList3 = orderBussinessInfoMapper.selectDistrictByPlacetype(addressInfo);
        ArrayList<AddressVO> addressList = new ArrayList<AddressVO>();
        for (int i = 0; i < sn_base_addressList1.size(); i++) {
            AddressVO addressVO1 = new AddressVO();
            addressVO1.setValue(sn_base_addressList1.get(i).getPlacecode());
            addressVO1.setLabel(sn_base_addressList1.get(i).getPlacename());
            ArrayList<AddressVO> children1 = new ArrayList<AddressVO>();
            for (int j = 0; j < sn_base_addressList2.size(); j++) {
                if ((sn_base_addressList2.get(j).getPlacecode().substring(0,2)).equals((sn_base_addressList1.get(i).getPlacecode().substring(0,2)))) {
                    AddressVO addressVO2 = new AddressVO();
                    addressVO2.setValue(sn_base_addressList2.get(j).getPlacecode());
                    addressVO2.setLabel(sn_base_addressList2.get(j).getPlacename());
                    ArrayList<AddressVO> children2 = new ArrayList<AddressVO>();
                    for (int p = 0; p < sn_base_addressList3.size(); p++) {
                        if (sn_base_addressList3.get(p).getPlacecode().substring(0,4).equals(sn_base_addressList2.get(j).getPlacecode().substring(0,4))) {
                            AddressVO addressVO3 = new AddressVO();
                            addressVO3.setValue(sn_base_addressList3.get(p).getPlacecode());
                            addressVO3.setLabel(sn_base_addressList3.get(p).getPlacename());
                            children2.add(addressVO3);
                        }
                    }
                    addressVO2.setChildren(children2);
                    children1.add(addressVO2);
                }
            }
            addressVO1.setChildren(children1);
            addressList.add(addressVO1);
        }
        return addressList;
    }
}

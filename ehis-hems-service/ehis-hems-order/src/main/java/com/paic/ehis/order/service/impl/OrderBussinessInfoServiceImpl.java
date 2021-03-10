package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.*;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.order.domain.*;
import com.paic.ehis.order.mapper.OrderBussinessInfoMapper;
import com.paic.ehis.order.mapper.OrderInfoMapper;
import com.paic.ehis.order.service.IOrderBussinessInfoService;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.domain.HospitalInfoVo;
import com.paic.ehis.system.api.domain.FirstDeptInfoVo;
import com.paic.ehis.system.api.domain.SecondDeptInfoVo;
import com.paic.ehis.system.api.domain.AddressInfo;
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

    @Autowired
    private GetProviderInfoService getProviderInfoService;

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
        RSAHelper rsaHelper=new RSAHelper();
        rsaHelper.initKey("MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==",2048);
        password=rsaHelper.decrypt(password);
        //根据用户名查询密码
        RoleLoginInfo info = orderBussinessInfoMapper.checkpassWord(role);
        if(info != null){
            if (!SecurityUtils.matchesPassword(password, info.getPassword())) {
                return AjaxResult.error("操作失败","用户名或密码不正确");
            }
        }else{
            return AjaxResult.error("操作失败","登录用户不存在");
        }
        return AjaxResult.success(info);
    }

    @Override
    public RoleLoginInfo getUser(RoleLoginInfo roleLoginInfo){

        RoleLoginInfo info = orderBussinessInfoMapper.getUser(roleLoginInfo);
        return info;
    }


    /*变更用户密码*/
    @Override
    public AjaxResult updatePassword(RoleLoginInfo roleLoginInfo){
        String role = roleLoginInfo.getRole();
        String password = roleLoginInfo.getPassword();
        String newPassword = roleLoginInfo.getNewPassword();
        //根据用户名查询密码
        RoleLoginInfo info = orderBussinessInfoMapper.checkpassWord(role);
        RSAHelper rsaHelper=new RSAHelper();
        rsaHelper.initKey("MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==",2048);
        password=rsaHelper.decrypt(password);
        newPassword=rsaHelper.decrypt(newPassword);
        if(info != null){
            if (!SecurityUtils.matchesPassword(password, info.getPassword())) {
                return AjaxResult.error("操作失败","用户名或密码不正确");
            }
        }else{
            return AjaxResult.error("操作失败","登录用户不存在");
        }
        if(password.equals(newPassword)){
            return AjaxResult.error("操作失败","新密码不能与旧密码一致");
        }
        roleLoginInfo.setNewPassword(SecurityUtils.encryptPassword(newPassword));
        orderBussinessInfoMapper.updatePassword(roleLoginInfo);
        orderBussinessInfoMapper.updateIsLogin(roleLoginInfo);
        return AjaxResult.success();
    }

    /**
     * 查询接单人拥有的接单数量
     *
     */
    @Override
    public OrderNumInfo getOrderNum(RoleLoginInfo roleLoginInfo){
        OrderNumInfo orderNumInfo = new OrderNumInfo();
        //根据角色查询接单总数
        orderNumInfo.setReceivedTimes(orderBussinessInfoMapper.getReceivedTimes(roleLoginInfo.getRole()));
        //根据角色查询已完成接单
        orderNumInfo.setCompletionTimes(orderBussinessInfoMapper.getCompletionTimes(roleLoginInfo.getRole()));
        //根据角色查询进行中接单
        orderNumInfo.setProgressTimes(orderBussinessInfoMapper.getProgressTimes(roleLoginInfo.getRole()));
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
    public OrderInfo getOrderDetail(RoleLoginInfo roleLoginInfo){
        OrderInfo orderInfo =orderBussinessInfoMapper.getOrderDetail(roleLoginInfo);
        String inpatientArea = orderInfo.getInpatientArea();
        String province = "";
        String city ="";
        String district ="";
        if(inpatientArea!=null && inpatientArea!=""){
            if(Arrays.asList((inpatientArea.split(","))).get(0)!=""&& Arrays.asList((inpatientArea.split(","))).get(0)!=null){
                province = orderBussinessInfoMapper.getPlacename(Arrays.asList((inpatientArea.split(","))).get(0));
            }
            if(Arrays.asList((inpatientArea.split(","))).get(0)!=""&& Arrays.asList((inpatientArea.split(","))).get(0)!=null){
                city = orderBussinessInfoMapper.getPlacename(Arrays.asList((inpatientArea.split(","))).get(1));
            }
            if(Arrays.asList((inpatientArea.split(","))).get(0)!=""&& Arrays.asList((inpatientArea.split(","))).get(0)!=null){
                district = orderBussinessInfoMapper.getPlacename(Arrays.asList((inpatientArea.split(","))).get(2));
            }

            orderInfo.setInpatientArea(province+"-"+city+"-"+district);
        }
        return orderInfo;
    }


    /**
     * 查询不同节点状态下的工单列表
     */
    @Override
    public int receivingOrder(RoleLoginInfo roleLoginInfo){
        roleLoginInfo.setReceivingTime(DateUtils.getNowDate());
        //数据落入轨迹表
        OrderTrack orderTrack =new OrderTrack();
        orderTrack.setNodeStatus(roleLoginInfo.getNodeStatus());
        orderTrack.setCreateTime(DateUtils.getNowDate());
        orderTrack.setUpdateTime(DateUtils.getNowDate());
        orderTrack.setCreateBy(SecurityUtils.getUsername());
        orderTrack.setUpdateBy(SecurityUtils.getUsername());
        orderTrack.setOrderCode(roleLoginInfo.getOrderCode());
        orderTrack.setSerialNo(PubFun.createMySqlMaxNoUseCache("OrderInfoSer",20,20));
        orderBussinessInfoMapper.insertOrderTrack(orderTrack);
        return orderBussinessInfoMapper.receivingOrder(roleLoginInfo);
    }


    /**
     * 实施操作
     */
    @Override
    public int implementtOrder(OrderInfo orderInfo){
        OrderTrack orderTrack =new OrderTrack();
        if("01".equals(orderInfo.getDetailStatus()) || "02".equals(orderInfo.getDetailStatus())){
            orderInfo.setApplyResultTime(DateUtils.getNowDate());
        }
        if("02".equals(orderInfo.getDetailStatus())){
            orderInfo.setOrderCompleteTime(DateUtils.getNowDate());
            orderTrack.setReason(orderInfo.getFailureReason());
        }
        if(orderInfo.getSettlePrice() !=null && orderInfo.getSettlePrice() !=""){
            orderInfo.setOrderCompleteTime(DateUtils.getNowDate());
        }
        //数据落入轨迹表
        orderTrack.setDetailStatus(orderInfo.getDetailStatus());
        if(orderInfo.getNodeStatus() =="" || orderInfo.getNodeStatus() ==null){
            orderTrack.setNodeStatus("02");
        }else{
            orderTrack.setNodeStatus(orderInfo.getNodeStatus());
            orderInfo.setBussinessStatus("02");
        }
        if("04".equals(orderInfo.getDetailStatus())){
            orderTrack.setReason(orderInfo.getNoVisitReason());
        }else if("06".equals(orderInfo.getDetailStatus())){
            orderTrack.setReason(orderInfo.getRefuseReason());
        }
        orderInfo.setUpdateTime(DateUtils.getNowDate());
        orderTrack.setRemarkB(orderInfo.getRemark());
        orderTrack.setCreateTime(DateUtils.getNowDate());
        orderTrack.setUpdateTime(DateUtils.getNowDate());
        orderTrack.setCreateBy(SecurityUtils.getUsername());
        orderTrack.setUpdateBy(SecurityUtils.getUsername());
        orderTrack.setOrderCode(orderInfo.getOrderCode());
        orderTrack.setSerialNo(PubFun.createMySqlMaxNoUseCache("OrderInfoSer",20,20));
        orderBussinessInfoMapper.insertOrderTrack(orderTrack);
        return orderBussinessInfoMapper.implementtOrder(orderInfo);

    }


    /* 获取医院以及科室信息*/
    @Override
    public List<HospitalInfoVo> getHosptialInfo(AddressInfo addressInfo){
        log.info("********获取工单中医院以及科室信息********");
        //获取医院信息
        List<HospitalInfoVo> hospitalInfos= getProviderInfoService.getHospitalInfo1(addressInfo);
        //获取一级科室
        List<FirstDeptInfoVo> firstDeptInfos= getProviderInfoService.getFirstDeptInfo();
        //获取二级科室
        List<SecondDeptInfoVo> secondDeptInfos= getProviderInfoService.getSecondDeptInfo();
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
                        if(SecondDeptInfo.getFirstDeptName().equals(firstDeptInfo.getDeptName())){
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

    @Override
    public List<OrderTrack> getDealList(OrderInfo orderInfo){
        return orderBussinessInfoMapper.getDealList(orderInfo);
    }
}

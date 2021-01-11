package com.paic.ehis.system.controller;


import com.paic.ehis.system.domain.HmpServiceOrder;
import com.paic.ehis.system.domain.HmpServiceOrderApplication;
import com.paic.ehis.system.domain.HmpServiceOrderAssign;
import com.paic.ehis.system.domain.HmpServiceOrderResult;
import com.paic.ehis.system.domain.vo.ServiceInfoVo;
import com.paic.ehis.system.service.IHmpServiceOrderApplicationService;
import com.paic.ehis.system.service.IHmpServiceOrderAssignService;
import com.paic.ehis.system.service.IHmpServiceOrderResultService;
import com.paic.ehis.system.service.IHmpServiceOrderService;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 产品定义Controller
 *
 * @author zkrbailz
 * @date 2020-11-17
 */
@RestController
@RequestMapping("/servicedeal")
public class HmpServiceDealController extends BaseController {

    @Autowired
    private IHmpServiceOrderService hmpServiceOrderService;

    @Autowired
    private IHmpServiceOrderApplicationService hmpServiceOrderApplicationService;

    @Autowired
    private IHmpServiceOrderAssignService hmpServiceOrderAssignService;

    @Autowired
    private IHmpServiceOrderResultService hmpServiceOrderResultService;

    @GetMapping("/serviceInfo/{ordercode}")
    public AjaxResult serviceInfo(@PathVariable("ordercode") String ordercode) {
        // 根据工单编号查询出服务工单信息
        HmpServiceOrder hmpServiceOrder = hmpServiceOrderService.selectHmpServiceOrderByCode(ordercode);
        // 根据工单编号查询出服务申请信息
        HmpServiceOrderApplication hmpServiceOrderApplication = hmpServiceOrderApplicationService.selectHmpServiceOrderAppByOrdercode(ordercode);
        // 根据工单编号查询出服务分诊
        HmpServiceOrderAssign hmpServiceOrderAssign = hmpServiceOrderAssignService.selectHmpServiceOrderAssignByCode(ordercode);
        // 根据工单编号查询出实施结果信息
        HmpServiceOrderResult hmpServiceOrderResult = hmpServiceOrderResultService.selectHmpServiceOrderResultByCode(ordercode);
        // 返回信息
        ServiceInfoVo serviceInfoVo = new ServiceInfoVo();
        serviceInfoVo.setHmpServiceOrder(hmpServiceOrder);
        serviceInfoVo.setHmpServiceOrderApplication(hmpServiceOrderApplication);
        serviceInfoVo.setHmpServiceOrderAssign(hmpServiceOrderAssign);
        serviceInfoVo.setHmpServiceOrderResult(hmpServiceOrderResult);
        // 返回结果集
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("serviceInfoVo", serviceInfoVo);
        return ajaxResult;
    }

    /**
     * 新增分诊信息
     *
     * @param hmpServiceOrderAssign
     * @return
     */
    @PostMapping("/addtrialinfo")
    public AjaxResult addTrialInfo(@RequestBody HmpServiceOrderAssign hmpServiceOrderAssign) {
        // 根据工单编码来进行新增或者修改
        // 生成主键
        String assigncode = "AS" + PubFun.createMySqlMaxNoUseCache("AssignCode", 0, 8);
        // 设置主键
        hmpServiceOrderAssign.setAssigncode(assigncode);
        // 设置创建人
        hmpServiceOrderAssign.setCreateby(SecurityUtils.getUsername());
        // 设置创建时间
        hmpServiceOrderAssign.setCreatetime(new Date());
        // 新增就诊信息
        int insertHmpServiceOrderAssign = hmpServiceOrderAssignService.insertHmpServiceOrderAssign(hmpServiceOrderAssign);
        if (insertHmpServiceOrderAssign > 0) {
            if (hmpServiceOrderAssign.getStatus().equals("02")) {
                // 新增成功的同时 将服务工单表的状态置为 02-已分诊
                String ordercode = hmpServiceOrderAssign.getOrdercode();
                // 创建服务工单对象
                HmpServiceOrder hmpServiceOrder = new HmpServiceOrder();
                hmpServiceOrder.setOrderstatus("02");
                hmpServiceOrder.setOrdercode(ordercode);
                hmpServiceOrder.setUpdateBy(SecurityUtils.getUsername());
                hmpServiceOrder.setUpdateTime(new Date());
                hmpServiceOrderService.updateHmpServiceOrderStatus(hmpServiceOrder);
            }
            return AjaxResult.success("新增成功");
        } else {
            return AjaxResult.error("新增失败");
        }
    }

    /**
     * 修改分诊信息
     *
     * @param hmpServiceOrderAssign
     * @return
     */
    @PutMapping("/updatetrialinfo")
    public AjaxResult updateTrialInfo(@RequestBody HmpServiceOrderAssign hmpServiceOrderAssign) {
        // 设置更新人
        hmpServiceOrderAssign.setUpdateby(SecurityUtils.getUsername());
        // 设置更新时间
        hmpServiceOrderAssign.setUpdatetime(new Date());
        // 修改
        int updateHmpServiceOrderAssign = hmpServiceOrderAssignService.updateHmpServiceOrderAssign(hmpServiceOrderAssign);

        if (updateHmpServiceOrderAssign > 0) {
            // 如果状态等于02 已完成
            if (hmpServiceOrderAssign.getStatus().equals("02")) {
                // 修改成功的同时 将服务工单表的状态置为 02-已分诊
                String ordercode = hmpServiceOrderAssign.getOrdercode();
                // 创建服务工单对象
                HmpServiceOrder hmpServiceOrder = new HmpServiceOrder();
                hmpServiceOrder.setOrderstatus("02");
                hmpServiceOrder.setOrdercode(ordercode);
                hmpServiceOrder.setUpdateBy(SecurityUtils.getUsername());
                hmpServiceOrder.setUpdateTime(new Date());
                hmpServiceOrderService.updateHmpServiceOrderStatus(hmpServiceOrder);
            }
            return AjaxResult.success("修改成功");
        } else {
            return AjaxResult.error("修改失败");
        }
    }

    /**
     * 新增实施结果信息
     *
     * @param hmpServiceOrderResult
     * @return
     */
    @PostMapping("/addServiceInfo")
    public AjaxResult addServiceInfo(@RequestBody HmpServiceOrderResult hmpServiceOrderResult) {
        // 设置结果编码
        String resultCode = "RS" + PubFun.createMySqlMaxNoUseCache("ResultCode", 0, 8);
        hmpServiceOrderResult.setResultcode(resultCode);
        hmpServiceOrderResult.setCreateby(SecurityUtils.getUsername());
        hmpServiceOrderResult.setCreatetime(new Date());
        int insertHmpServiceOrderResult = hmpServiceOrderResultService.insertHmpServiceOrderResult(hmpServiceOrderResult);
        if (insertHmpServiceOrderResult > 0) {
            // 如果实施状态为02的是时候 再讲服务工单的状态置位03
            if (hmpServiceOrderResult.getStatus().equals("02")) {
                // 新增成功的同时 将服务工单表的状态置为 03-已分诊
                String ordercode = hmpServiceOrderResult.getOrdercode();
                // 创建服务工单对象
                HmpServiceOrder hmpServiceOrder = new HmpServiceOrder();
                hmpServiceOrder.setOrderstatus("03");
                hmpServiceOrder.setOrdercode(ordercode);
                hmpServiceOrder.setUpdateBy(SecurityUtils.getUsername());
                hmpServiceOrder.setUpdateTime(new Date());
                hmpServiceOrderService.updateHmpServiceOrderStatus(hmpServiceOrder);
            }
            return AjaxResult.success("新增成功");
        } else {
            return AjaxResult.error("新增失败");
        }
    }

    /**
     * 更新服务实施信息
     * @param hmpServiceOrderResult
     * @return
     */
    @PutMapping("/updateserviceinfo")
    public AjaxResult updateServiceInfo(@RequestBody HmpServiceOrderResult hmpServiceOrderResult) {
        // 设置更新人
        hmpServiceOrderResult.setUpdateby(SecurityUtils.getUsername());
        // 设置更新时间
        hmpServiceOrderResult.setUpdatetime(new Date());
        // 更新操作
        int updateHmpServiceOrderResult = hmpServiceOrderResultService.updateHmpServiceOrderResult(hmpServiceOrderResult);
        if(updateHmpServiceOrderResult > 0) {
            // 如果状态等于02 已完成
            if (hmpServiceOrderResult.getStatus().equals("02")) {
                // 修改成功的同时 将服务工单表的状态置为 03-已实施
                String ordercode = hmpServiceOrderResult.getOrdercode();
                // 创建服务工单对象
                HmpServiceOrder hmpServiceOrder = new HmpServiceOrder();
                hmpServiceOrder.setOrderstatus("03");
                hmpServiceOrder.setOrdercode(ordercode);
                hmpServiceOrder.setUpdateBy(SecurityUtils.getUsername());
                hmpServiceOrder.setUpdateTime(new Date());
                hmpServiceOrderService.updateHmpServiceOrderStatus(hmpServiceOrder);
            }
            return AjaxResult.success("修改成功");
        } else {
            return AjaxResult.error("修改失败");
        }
    }

}



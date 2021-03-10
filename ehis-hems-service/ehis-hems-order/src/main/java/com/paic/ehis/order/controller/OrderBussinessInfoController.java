package com.paic.ehis.order.controller;

import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.order.domain.*;
import com.paic.ehis.order.service.ICustomerInfoService;
import com.paic.ehis.order.service.IOrderBussinessInfoService;
import com.paic.ehis.order.service.IOrderEvaluateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.paic.ehis.system.api.domain.AddressInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * order_info(工单信息)Controller
 *
 * @author sino
 * @date 2021-01-26
 */
@RestController
@RequestMapping("/bussinessInfo")
public class OrderBussinessInfoController extends BaseController
{
    @Autowired
    private IOrderBussinessInfoService orderBussinessInfoService;

    @Autowired
    private ICustomerInfoService customerInfoService;

    @Autowired
    private IOrderEvaluateInfoService orderEvaluateInfoService;

    /**
     * 验证B端登录的用户信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getUserInfo")
    public AjaxResult getUserInfo(@RequestBody RoleLoginInfo roleLoginInfo)
    {
        return orderBussinessInfoService.checkpassWord(roleLoginInfo);
    }


    /**
     * 获取B端登录的用户信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getUser")
    public AjaxResult getUser(@RequestBody RoleLoginInfo roleLoginInfo)
    {
        return AjaxResult.success(orderBussinessInfoService.getUser(roleLoginInfo));
    }

    /**
     * 变更B端登录密码接口
     */
    @PostMapping("/updatePassword")
    public AjaxResult updatePassword(@RequestBody RoleLoginInfo roleLoginInfo)
    {
        return orderBussinessInfoService.updatePassword(roleLoginInfo);
    }
    /**
     * 查询接单人拥有的接单数量
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getOrderNum")
    public AjaxResult getOrderNum(@RequestBody RoleLoginInfo roleLoginInfo)
    {
        return AjaxResult.success(orderBussinessInfoService.getOrderNum(roleLoginInfo));
    }

    /**
     * 查询不同节点状态下的工单列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getOrderList")
    public TableDataInfo getOrderList(@RequestBody RoleLoginInfo roleLoginInfo)
    {
        startPage(roleLoginInfo);
        return getDataTable(orderBussinessInfoService.getOrderList(roleLoginInfo));
    }

    @PostMapping("/getOrderDetail")
    public AjaxResult getOrderDetail(@RequestBody RoleLoginInfo roleLoginInfo)
    {
        return AjaxResult.success(orderBussinessInfoService.getOrderDetail(roleLoginInfo));
    }


    /**
     * 接单操作
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/receivingOrder")
    public AjaxResult receivingOrder(@RequestBody RoleLoginInfo roleLoginInfo)
    {
        return toAjax(orderBussinessInfoService.receivingOrder(roleLoginInfo));
    }

    /**
     * 实施操作
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/implementtOrder")
    public AjaxResult implementtOrder(@RequestBody OrderInfo orderInfo)
    {
        return toAjax(orderBussinessInfoService.implementtOrder(orderInfo));
    }


    /**
     * 根据省市区查询医院，并且获取一级二级科室
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getHosptialInfo")
    public AjaxResult getHosptialInfo(@RequestBody AddressInfo addressInfo)
    {
        return AjaxResult.success(orderBussinessInfoService.getHosptialInfo(addressInfo));
    }

    /**
     * 获取供应商下的省市区数据getUserInfo
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getAddress")
    public AjaxResult getAddress(@RequestBody AddressInfo addressInfo)
    {
        return AjaxResult.success(orderBussinessInfoService.getAddress(addressInfo));
    }

    /*获取订单处理列表*/
    @PostMapping("/getDealList")
    public AjaxResult getDealList(@RequestBody OrderInfo orderInfo)
    {
        return AjaxResult.success(orderBussinessInfoService.getDealList(orderInfo));
    }
}

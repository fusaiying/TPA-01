package com.paic.ehis.order.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.order.domain.OrderEvaluateInfo;
import com.paic.ehis.order.domain.OrderInfo;
import com.paic.ehis.order.service.ICustomerInfoService;
import com.paic.ehis.order.service.IOrderEvaluateInfoService;
import com.paic.ehis.order.service.IOrderInfoService;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.LognToBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/info")
public class OrderInfoController extends BaseController
{
    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private ICustomerInfoService customerInfoService;

    @Autowired
    private IOrderEvaluateInfoService orderEvaluateInfoService;

    @Autowired
    private GetProviderInfoService getProviderInfoService;

    /**
     * 查询order_info(工单信息)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody  OrderInfo orderInfo)
    {
        startPage(orderInfo);
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        return getDataTable(list);
    }

    /**
     * 查询order_info(工单信息)列表(近三个月)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/list3Months")
    public TableDataInfo list3Months() throws Exception
    {
        List<OrderInfo> list = orderInfoService.selectOrderInfoList3Months();
        return getDataTable(list);
    }

    /**
     * 导出order_info(工单信息)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "order_info(工单信息)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderInfo orderInfo) throws IOException
    {
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        ExcelUtil<OrderInfo> util = new ExcelUtil<OrderInfo>(OrderInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取order_info(工单信息)服务详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/queryinfo")
    public AjaxResult getInfo(@RequestBody OrderInfo orderInfo)
    {
        return AjaxResult.success(orderInfoService.selectOrderInfoById(orderInfo));
    }

    /**
     * 新增order_info(工单信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "order_info(工单信息)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderInfo orderInfo)
    {
        return toAjax(orderInfoService.insertOrderInfo(orderInfo));
    }

    /**
     * 修改order_info(工单信息)分配按钮
     */
    //@PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "order_info(工单信息)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderInfo orderInfo)
    {
        return toAjax(orderInfoService.updateOrderInfo(orderInfo));
    }

    /**
     * 删除order_info(工单信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "order_info(工单信息)", businessType = BusinessType.DELETE)
    @DeleteMapping("/{serialNos}")
    public AjaxResult remove(@PathVariable String[] serialNos)
    {
        return toAjax(orderInfoService.deleteOrderInfoByIds(serialNos));
    }

    /**
     * 查询供应商信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getSupplierInfo")
    public AjaxResult getSupplierInfo()
    {
        return getProviderInfoService.getSupplierInfo();
    }


    /**
     * 查询产品
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getProductInfo")
    public AjaxResult getProductInfo()
    {
        return AjaxResult.success(orderInfoService.getProductInfo());
    }

    /**
     * 查询医院以及科室接口
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getHosptialInfo")
    public AjaxResult getHosptialInfo()
    {
        return AjaxResult.success(orderInfoService.getHospitalInfoVo());
    }

    /**
     * 查询客户信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getCustomerInfo")
    public AjaxResult getCustomerInfo(@RequestBody OrderInfo orderInfo)
    {
        return AjaxResult.success(customerInfoService.selectCustomerInfoById(orderInfo.getOrderCode()));
    }


    /**
     * 获取供应商联动信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getAllProSuppInfo")
    public AjaxResult getAllProSuppInfo(@RequestBody OrderInfo orderInfo)
    {
        return AjaxResult.success(orderInfoService.getAllProSuppInfo(orderInfo));
    }


    /**
     * 查询order_evaluate_info(服务评价信息)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getEvaluateInfo")
    public TableDataInfo getEvaluateInfo(@RequestBody OrderEvaluateInfo orderEvaluateInfo)
    {
        startPage(orderEvaluateInfo);
        List<OrderEvaluateInfo> list = orderEvaluateInfoService.selectOrderEvaluateInfoList(orderEvaluateInfo);
        return getDataTable(list);
    }

    /**
     * 取消工单
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/cancalOrder")
    public AjaxResult cancalOrder(@RequestBody OrderInfo orderInfo)
    {
        return toAjax(orderInfoService.cancalOrder(orderInfo));
    }

    /**
     * 根据产品查询服务信息
     *//*
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getServiceBypro")
    public AjaxResult getServiceBypro(@RequestBody OrderInfo orderInfo)
    {
        return AjaxResult.success(orderInfoService.getServiceBypro(orderInfo));
    }*/


    //B端接口,第一个供应商超时后进行自动分配功能
    //@PreAuthorize("@ss.hasPermi('system:info:edit')")
    @PostMapping("/getAutoMode")
    public void getAutoMode()
    {
        orderInfoService.getAutoMode();
    }

    /*审核特药通过*/
    @PostMapping("/checkPass")
    public AjaxResult checkPass(@RequestBody OrderInfo orderInfo)
    {
        return toAjax(orderInfoService.checkPass(orderInfo));
    }
}

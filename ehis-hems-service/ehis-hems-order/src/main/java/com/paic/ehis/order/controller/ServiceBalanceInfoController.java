package com.paic.ehis.order.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.order.domain.ServiceBalanceInfo;
import com.paic.ehis.order.service.IServiceBalanceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * service_balance_info(服务结算基本信息)Controller
 * 
 * @author chenhaiming
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/balance")
public class ServiceBalanceInfoController extends BaseController
{
    @Autowired
    private IServiceBalanceInfoService serviceBalanceInfoService;

    /**
     * 查询service_balance_info(服务结算基本信息)列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ServiceBalanceInfo serviceBalanceInfo)
    {
        startPage(serviceBalanceInfo);
        List<ServiceBalanceInfo> list = serviceBalanceInfoService.selectServiceBalanceInfoList(serviceBalanceInfo);
        return getDataTable(list);
    }

    /**
     * 导出service_balance_info(服务结算基本信息)列表
     */
    @Log(title = "service_balance_info(服务结算基本信息)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceBalanceInfo serviceBalanceInfo) throws IOException
    {
        List<ServiceBalanceInfo> list = serviceBalanceInfoService.selectServiceBalanceInfoList(serviceBalanceInfo);
        ExcelUtil<ServiceBalanceInfo> util = new ExcelUtil<ServiceBalanceInfo>(ServiceBalanceInfo.class);
        util.exportExcel(response, list, "balance");
    }

    /**
     * 获取service_balance_info(服务结算基本信息)详细信息
     */
    @GetMapping(value = "/{serialNo}")
    public AjaxResult getInfo(@PathVariable("serialNo") Long serialNo)
    {
        return AjaxResult.success(serviceBalanceInfoService.selectServiceBalanceInfoById(serialNo));
    }

    /**
     * 新增service_balance_info(服务结算基本信息)
     */
    @Log(title = "service_balance_info(服务结算基本信息)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceBalanceInfo serviceBalanceInfo)
    {
        int num = serviceBalanceInfoService.insertServiceBalanceInfo(serviceBalanceInfo);
        if (num > 0) {
            return AjaxResult.success(serviceBalanceInfo);
        }
        return AjaxResult.error("新增失败");
    }

    /**
     * 修改service_balance_info(服务结算基本信息)
     */
    @Log(title = "service_balance_info(服务结算基本信息)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceBalanceInfo serviceBalanceInfo)
    {
        return toAjax(serviceBalanceInfoService.updateServiceBalanceInfo(serviceBalanceInfo));
    }

    /**
     * 查询service_balance_info(服务结算基本信息)列表
     */
    @PostMapping("/list2")
    public TableDataInfo list2(@RequestBody ServiceBalanceInfo serviceBalanceInfo)
    {
        startPage(serviceBalanceInfo);
        List<ServiceBalanceInfo> list = serviceBalanceInfoService.selectServiceBalanceInfoList2(serviceBalanceInfo);
        return getDataTable(list);
    }

    /**
     * 更新service_balance_info(服务结算基本信息)
     */
	@PostMapping("/update")
    public AjaxResult update(@RequestBody ServiceBalanceInfo serviceBalanceInfo)
    {
        if (serviceBalanceInfo.getParams().get("type") == null) {
            return AjaxResult.error("参数错误！");
        }
        return serviceBalanceInfoService.updateServiceBalanceInfoByTaskNo(serviceBalanceInfo);
    }

}

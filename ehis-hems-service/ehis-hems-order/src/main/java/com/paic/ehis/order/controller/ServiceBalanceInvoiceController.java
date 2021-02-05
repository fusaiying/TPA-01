package com.paic.ehis.order.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.order.domain.ServiceBalanceInvoice;
import com.paic.ehis.order.service.IServiceBalanceInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * service_balance_invoice(服务结算发票信息)Controller
 * 
 * @author chenhaiming
 * @date 2021-01-27
 */
@RestController
@RequestMapping("/balanceInvoice")
public class ServiceBalanceInvoiceController extends BaseController
{
    @Autowired
    private IServiceBalanceInvoiceService serviceBalanceInvoiceService;

    /**
     * 查询service_balance_invoice(服务结算发票信息)列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ServiceBalanceInvoice serviceBalanceInvoice)
    {
        startPage(serviceBalanceInvoice);
        List<ServiceBalanceInvoice> list = serviceBalanceInvoiceService.selectServiceBalanceInvoiceList(serviceBalanceInvoice);
        return getDataTable(list);
    }

    /**
     * 导出service_balance_invoice(服务结算发票信息)列表
     */
    @Log(title = "service_balance_invoice(服务结算发票信息)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceBalanceInvoice serviceBalanceInvoice) throws IOException
    {
        List<ServiceBalanceInvoice> list = serviceBalanceInvoiceService.selectServiceBalanceInvoiceList(serviceBalanceInvoice);
        ExcelUtil<ServiceBalanceInvoice> util = new ExcelUtil<ServiceBalanceInvoice>(ServiceBalanceInvoice.class);
        util.exportExcel(response, list, "invoice");
    }

    /**
     * 获取service_balance_invoice(服务结算发票信息)详细信息
     */
    @GetMapping(value = "/{serialNo}")
    public AjaxResult getInfo(@PathVariable("serialNo") Long serialNo)
    {
        return AjaxResult.success(serviceBalanceInvoiceService.selectServiceBalanceInvoiceById(serialNo));
    }

    /**
     * 新增service_balance_invoice(服务结算发票信息)
     */
    @Log(title = "service_balance_invoice(服务结算发票信息)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceBalanceInvoice serviceBalanceInvoice)
    {
        return toAjax(serviceBalanceInvoiceService.insertServiceBalanceInvoice(serviceBalanceInvoice));
    }

    /**
     * 修改service_balance_invoice(服务结算发票信息)
     */
    @Log(title = "service_balance_invoice(服务结算发票信息)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceBalanceInvoice serviceBalanceInvoice)
    {
        return toAjax(serviceBalanceInvoiceService.updateServiceBalanceInvoice(serviceBalanceInvoice));
    }

    /**
     * 删除service_balance_invoice(服务结算发票信息)
     */
    @Log(title = "service_balance_invoice(服务结算发票信息)", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serialNos}")
    public AjaxResult remove(@PathVariable Long[] serialNos)
    {
        return toAjax(serviceBalanceInvoiceService.deleteServiceBalanceInvoiceByIds(serialNos));
    }
}

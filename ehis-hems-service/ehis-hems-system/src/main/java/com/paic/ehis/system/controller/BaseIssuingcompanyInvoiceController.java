package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.BaseIssuingcompanyInvoice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.system.service.IBaseIssuingcompanyInvoiceService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 出单公司开票信息 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/invoice")
public class BaseIssuingcompanyInvoiceController extends BaseController
{
    @Autowired
    private IBaseIssuingcompanyInvoiceService baseIssuingcompanyInvoiceService;

    /**
     * 查询出单公司开票信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseIssuingcompanyInvoice baseIssuingcompanyInvoice)
    {
        startPage();
        List<BaseIssuingcompanyInvoice> list = baseIssuingcompanyInvoiceService.selectBaseIssuingcompanyInvoiceList(baseIssuingcompanyInvoice);
        return getDataTable(list);
    }

    /**
     * 导出出单公司开票信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:export')")
    @Log(title = "出单公司开票信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseIssuingcompanyInvoice baseIssuingcompanyInvoice) throws IOException
    {
        List<BaseIssuingcompanyInvoice> list = baseIssuingcompanyInvoiceService.selectBaseIssuingcompanyInvoiceList(baseIssuingcompanyInvoice);
        ExcelUtil<BaseIssuingcompanyInvoice> util = new ExcelUtil<BaseIssuingcompanyInvoice>(BaseIssuingcompanyInvoice.class);
        util.exportExcel(response, list, "invoice");
    }

    /**
     * 获取出单公司开票信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:query')")
    @GetMapping(value = "/{companyCode}")
    public AjaxResult getInfo(@PathVariable("companyCode") String companyCode)
    {
        return AjaxResult.success(baseIssuingcompanyInvoiceService.selectBaseIssuingcompanyInvoiceById(companyCode));
    }

    /**
     * 新增出单公司开票信息 
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:add')")
    @Log(title = "出单公司开票信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseIssuingcompanyInvoice baseIssuingcompanyInvoice)
    {
        return toAjax(baseIssuingcompanyInvoiceService.insertBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice));
    }

    /**
     * 修改出单公司开票信息 
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:edit')")
    @Log(title = "出单公司开票信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseIssuingcompanyInvoice baseIssuingcompanyInvoice)
    {
        return toAjax(baseIssuingcompanyInvoiceService.updateBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice));
    }

    /**
     * 删除出单公司开票信息 
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:remove')")
    @Log(title = "出单公司开票信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{companyCodes}")
    public AjaxResult remove(@PathVariable String[] companyCodes)
    {
        return toAjax(baseIssuingcompanyInvoiceService.deleteBaseIssuingcompanyInvoiceByIds(companyCodes));
    }
}

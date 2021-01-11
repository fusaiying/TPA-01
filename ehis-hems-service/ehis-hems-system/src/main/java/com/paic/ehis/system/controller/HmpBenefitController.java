package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.HmpBenefitCustomer;
import com.paic.ehis.system.service.IHmpBenefitCustomerService;
import com.paic.ehis.system.domain.*;
import com.paic.ehis.system.service.*;
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
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 客户权益查询Controller
 * 
 * @author paic.ehis
 * @date 2020-11-23
 */
@RestController
@RequestMapping("/benefit")
public class HmpBenefitController extends BaseController
{
    @Autowired
    private IHmpBenefitCustomerService hmpBenefitCustomerService;

    /**
     * 查询客户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmpBenefitCustomer hmpBenefitCustomer)
    {
        startPage();
        List<HmpBenefitCustomer> list = hmpBenefitCustomerService.selectHmpBenefitCustomerList(hmpBenefitCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:export')")
    @Log(title = "客户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmpBenefitCustomer hmpBenefitCustomer) throws IOException
    {
        List<HmpBenefitCustomer> list = hmpBenefitCustomerService.selectHmpBenefitCustomerList(hmpBenefitCustomer);
        ExcelUtil<HmpBenefitCustomer> util = new ExcelUtil<HmpBenefitCustomer>(HmpBenefitCustomer.class);
        util.exportExcel(response, list, "benefit");
    }

    /**
     * 获取客户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:query')")
    @GetMapping(value = "/{customerid}")
    public AjaxResult getInfo(@PathVariable("customerid") String customerid)
    {
        return AjaxResult.success(hmpBenefitCustomerService.selectHmpBenefitCustomerById(customerid));
    }

    /**
     * 新增客户信息
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:add')")
    @Log(title = "客户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmpBenefitCustomer hmpBenefitCustomer)
    {
        return toAjax(hmpBenefitCustomerService.insertHmpBenefitCustomer(hmpBenefitCustomer));
    }

    /**
     * 修改客户信息
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:edit')")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmpBenefitCustomer hmpBenefitCustomer)
    {
        return toAjax(hmpBenefitCustomerService.updateHmpBenefitCustomer(hmpBenefitCustomer));
    }

    /**
     * 删除客户信息
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:remove')")
    @Log(title = "客户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{customerids}")
    public AjaxResult remove(@PathVariable String[] customerids)
    {
        return toAjax(hmpBenefitCustomerService.deleteHmpBenefitCustomerByIds(customerids));
    }

}

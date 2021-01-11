package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.BaseIssuingcompany;
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
import com.paic.ehis.system.service.IBaseIssuingcompanyService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 出单公司信息 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/issuingcompany")
public class BaseIssuingcompanyController extends BaseController
{
    @Autowired
    private IBaseIssuingcompanyService baseIssuingcompanyService;

    /**
     * 查询出单公司信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseIssuingcompany baseIssuingcompany)
    {
        startPage();
        List<BaseIssuingcompany> list = baseIssuingcompanyService.selectBaseIssuingcompanyList(baseIssuingcompany);
        return getDataTable(list);
    }

    /**
     * 导出出单公司信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:export')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseIssuingcompany baseIssuingcompany) throws IOException
    {
        List<BaseIssuingcompany> list = baseIssuingcompanyService.selectBaseIssuingcompanyList(baseIssuingcompany);
        ExcelUtil<BaseIssuingcompany> util = new ExcelUtil<BaseIssuingcompany>(BaseIssuingcompany.class);
        util.exportExcel(response, list, "issuingcompany");
    }

    /**
     * 获取出单公司信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:query')")
    @GetMapping(value = "/{companyCode}")
    public AjaxResult getInfo(@PathVariable("companyCode") String companyCode)
    {
        return AjaxResult.success(baseIssuingcompanyService.selectBaseIssuingcompanyById(companyCode));
    }

    /**
     * 新增出单公司信息 
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:add')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseIssuingcompany baseIssuingcompany)
    {
        return toAjax(baseIssuingcompanyService.insertBaseIssuingcompany(baseIssuingcompany));
    }

    /**
     * 修改出单公司信息 
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:edit')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseIssuingcompany baseIssuingcompany)
    {
        return toAjax(baseIssuingcompanyService.updateBaseIssuingcompany(baseIssuingcompany));
    }

    /**
     * 删除出单公司信息 
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:remove')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{companyCodes}")
    public AjaxResult remove(@PathVariable String[] companyCodes)
    {
        return toAjax(baseIssuingcompanyService.deleteBaseIssuingcompanyByIds(companyCodes));
    }
}

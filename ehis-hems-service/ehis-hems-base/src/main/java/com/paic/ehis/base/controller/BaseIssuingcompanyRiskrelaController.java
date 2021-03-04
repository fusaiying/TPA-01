package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseIssuingcompanyRiskrela;
import com.paic.ehis.base.service.IBaseIssuingcompanyRiskrelaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 出单公司产品关联 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/riskrela")
public class BaseIssuingcompanyRiskrelaController extends BaseController
{
    @Autowired
    private IBaseIssuingcompanyRiskrelaService baseIssuingcompanyRiskrelaService;

    /**
     * 查询出单公司产品关联 列表
     */
    //@PreAuthorize("@ss.hasPermi('system:riskrela:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela)
    {
        startPage();
        List<BaseIssuingcompanyRiskrela> list = baseIssuingcompanyRiskrelaService.selectBaseIssuingcompanyRiskrelaList(baseIssuingcompanyRiskrela);
        return getDataTable(list);
    }

    /**
     * 导出出单公司产品关联 列表
     */
    //@PreAuthorize("@ss.hasPermi('system:riskrela:export')")
    @Log(title = "出单公司产品关联 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela) throws IOException
    {
        List<BaseIssuingcompanyRiskrela> list = baseIssuingcompanyRiskrelaService.selectBaseIssuingcompanyRiskrelaList(baseIssuingcompanyRiskrela);
        ExcelUtil<BaseIssuingcompanyRiskrela> util = new ExcelUtil<BaseIssuingcompanyRiskrela>(BaseIssuingcompanyRiskrela.class);
        util.exportExcel(response, list, "riskrela");
    }

    /**
     * 获取出单公司产品关联 详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:riskrela:query')")
    @GetMapping(value = "/{companyCode}")
    public AjaxResult getInfo(@PathVariable("companyCode") String companyCode)
    {
        return AjaxResult.success(baseIssuingcompanyRiskrelaService.selectBaseIssuingcompanyRiskrelaById(companyCode));
    }

    /**
     * 新增出单公司产品关联 
     */
    //@PreAuthorize("@ss.hasPermi('system:riskrela:add')")
    @Log(title = "出单公司产品关联 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela)
    {
        return toAjax(baseIssuingcompanyRiskrelaService.insertBaseIssuingcompanyRiskrela(baseIssuingcompanyRiskrela));
    }

    /**
     * 修改出单公司产品关联 
     */
    //@PreAuthorize("@ss.hasPermi('system:riskrela:edit')")
    @Log(title = "出单公司产品关联 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela)
    {
        return toAjax(baseIssuingcompanyRiskrelaService.updateBaseIssuingcompanyRiskrela(baseIssuingcompanyRiskrela));
    }

    /**
     * 删除出单公司产品关联 
     */
    //@PreAuthorize("@ss.hasPermi('system:riskrela:remove')")
    @Log(title = "出单公司产品关联 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{companyCodes}")
    public AjaxResult remove(@PathVariable String[] companyCodes)
    {
        return toAjax(baseIssuingcompanyRiskrelaService.deleteBaseIssuingcompanyRiskrelaByIds(companyCodes));
    }
}

package com.paic.ehis.product.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

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
import com.paic.ehis.product.domain.Hmpkgsmsconfiginfo;
import com.paic.ehis.product.service.IHmpkgsmsconfiginfoService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 产品包短信配置表Controller
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/hmpkgsmsconfiginfo")
public class HmpkgsmsconfiginfoController extends BaseController
{
    @Autowired
    private IHmpkgsmsconfiginfoService hmpkgsmsconfiginfoService;

    /**
     * 查询产品包短信配置表列表
     */
    @PreAuthorize("@ss.hasPermi('product:hmpkgsmsconfiginfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Hmpkgsmsconfiginfo hmpkgsmsconfiginfo)
    {
        startPage();
        List<Hmpkgsmsconfiginfo> list = hmpkgsmsconfiginfoService.selectHmpkgsmsconfiginfoList(hmpkgsmsconfiginfo);
        return getDataTable(list);
    }

    /**
     * 导出产品包短信配置表列表
     */
    @PreAuthorize("@ss.hasPermi('product:hmpkgsmsconfiginfo:export')")
    @Log(title = "产品包短信配置表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Hmpkgsmsconfiginfo hmpkgsmsconfiginfo) throws IOException
    {
        List<Hmpkgsmsconfiginfo> list = hmpkgsmsconfiginfoService.selectHmpkgsmsconfiginfoList(hmpkgsmsconfiginfo);
        ExcelUtil<Hmpkgsmsconfiginfo> util = new ExcelUtil<Hmpkgsmsconfiginfo>(Hmpkgsmsconfiginfo.class);
        util.exportExcel(response, list, "hmpkgsmsconfiginfo");
    }

    /**
     * 获取产品包短信配置表详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:hmpkgsmsconfiginfo:query')")
    @GetMapping(value = "/{serialno}")
    public AjaxResult getInfo(@PathVariable("serialno") Long serialno)
    {
        return AjaxResult.success(hmpkgsmsconfiginfoService.selectHmpkgsmsconfiginfoById(serialno));
    }

    /**
     * 新增产品包短信配置表
     */
    @PreAuthorize("@ss.hasPermi('product:hmpkgsmsconfiginfo:add')")
    @Log(title = "产品包短信配置表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Hmpkgsmsconfiginfo hmpkgsmsconfiginfo)
    {
        return toAjax(hmpkgsmsconfiginfoService.insertHmpkgsmsconfiginfo(hmpkgsmsconfiginfo));
    }

    /**
     * 修改产品包短信配置表
     */
    @PreAuthorize("@ss.hasPermi('product:hmpkgsmsconfiginfo:edit')")
    @Log(title = "产品包短信配置表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Hmpkgsmsconfiginfo hmpkgsmsconfiginfo)
    {
        return toAjax(hmpkgsmsconfiginfoService.updateHmpkgsmsconfiginfo(hmpkgsmsconfiginfo));
    }

    /**
     * 删除产品包短信配置表
     */
    @PreAuthorize("@ss.hasPermi('product:hmpkgsmsconfiginfo:remove')")
    @Log(title = "产品包短信配置表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serialnos}")
    public AjaxResult remove(@PathVariable Long[] serialnos)
    {
        return toAjax(hmpkgsmsconfiginfoService.deleteHmpkgsmsconfiginfoByIds(serialnos));
    }
}

package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.BaseIssuingcompanyAttachfile;
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
import com.paic.ehis.system.service.IBaseIssuingcompanyAttachfileService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 出单公司附件信息 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/attachfile")
public class BaseIssuingcompanyAttachfileController extends BaseController
{
    @Autowired
    private IBaseIssuingcompanyAttachfileService baseIssuingcompanyAttachfileService;

    /**
     * 查询出单公司附件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachfile:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile)
    {
        startPage();
        List<BaseIssuingcompanyAttachfile> list = baseIssuingcompanyAttachfileService.selectBaseIssuingcompanyAttachfileList(baseIssuingcompanyAttachfile);
        return getDataTable(list);
    }

    /**
     * 导出出单公司附件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachfile:export')")
    @Log(title = "出单公司附件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile) throws IOException
    {
        List<BaseIssuingcompanyAttachfile> list = baseIssuingcompanyAttachfileService.selectBaseIssuingcompanyAttachfileList(baseIssuingcompanyAttachfile);
        ExcelUtil<BaseIssuingcompanyAttachfile> util = new ExcelUtil<BaseIssuingcompanyAttachfile>(BaseIssuingcompanyAttachfile.class);
        util.exportExcel(response, list, "attachfile");
    }

    /**
     * 获取出单公司附件信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:attachfile:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(baseIssuingcompanyAttachfileService.selectBaseIssuingcompanyAttachfileById(id));
    }

    /**
     * 新增出单公司附件信息 
     */
    @PreAuthorize("@ss.hasPermi('system:attachfile:add')")
    @Log(title = "出单公司附件信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile)
    {
        return toAjax(baseIssuingcompanyAttachfileService.insertBaseIssuingcompanyAttachfile(baseIssuingcompanyAttachfile));
    }

    /**
     * 修改出单公司附件信息 
     */
    @PreAuthorize("@ss.hasPermi('system:attachfile:edit')")
    @Log(title = "出单公司附件信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile)
    {
        return toAjax(baseIssuingcompanyAttachfileService.updateBaseIssuingcompanyAttachfile(baseIssuingcompanyAttachfile));
    }

    /**
     * 删除出单公司附件信息 
     */
    @PreAuthorize("@ss.hasPermi('system:attachfile:remove')")
    @Log(title = "出单公司附件信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(baseIssuingcompanyAttachfileService.deleteBaseIssuingcompanyAttachfileByIds(ids));
    }
}

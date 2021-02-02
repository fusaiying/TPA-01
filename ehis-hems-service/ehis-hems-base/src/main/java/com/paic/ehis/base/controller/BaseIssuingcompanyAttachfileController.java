package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseIssuingcompanyAttachfile;
import com.paic.ehis.base.service.IBaseIssuingcompanyAttachfileService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(baseIssuingcompanyAttachfileService.selectBaseIssuingcompanyAttachfileById(id));
    }

    /**
     * 新增出单公司附件信息 
     */
    @Log(title = "出单公司附件信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile)
    {
        return toAjax(baseIssuingcompanyAttachfileService.insertBaseIssuingcompanyAttachfile(baseIssuingcompanyAttachfile));
    }

    /**
     * 修改出单公司附件信息 
     */
    @Log(title = "出单公司附件信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile)
    {
        return toAjax(baseIssuingcompanyAttachfileService.updateBaseIssuingcompanyAttachfile(baseIssuingcompanyAttachfile));
    }

    /**
     * 删除出单公司附件信息 
     */
    @Log(title = "出单公司附件信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(baseIssuingcompanyAttachfileService.deleteBaseIssuingcompanyAttachfileByIds(ids));
    }
}

package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.FieldMap;
import com.paic.ehis.cs.service.IFieldMapService;
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
 * 受理信息映射 Controller
 * 
 * @author sino
 * @date 2021-01-22
 */
@RestController
@RequestMapping("/map")
public class FieldMapController extends BaseController
{
    @Autowired
    private IFieldMapService fieldMapService;

    /**
     * 查询受理信息映射 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:map:list')")
    @GetMapping("/list")
    public TableDataInfo list(FieldMap fieldMap)
    {
        startPage();
        List<FieldMap> list = fieldMapService.selectFieldMapList(fieldMap);
        return getDataTable(list);
    }

    /**
     * 导出受理信息映射 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:map:export')")
    @Log(title = "受理信息映射 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FieldMap fieldMap) throws IOException
    {
        List<FieldMap> list = fieldMapService.selectFieldMapList(fieldMap);
        ExcelUtil<FieldMap> util = new ExcelUtil<FieldMap>(FieldMap.class);
        util.exportExcel(response, list, "map");
    }

    /**
     * 获取受理信息映射 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:map:query')")
    @GetMapping(value = "/{sourceName}")
    public AjaxResult getInfo(@PathVariable("sourceName") String sourceName)
    {
        return AjaxResult.success(fieldMapService.selectFieldMapById(sourceName));
    }

    /**
     * 新增受理信息映射 
     */
//    @PreAuthorize("@ss.hasPermi('system:map:add')")
    @Log(title = "受理信息映射 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FieldMap fieldMap)
    {
        return toAjax(fieldMapService.insertFieldMap(fieldMap));
    }

    /**
     * 修改受理信息映射 
     */
//    @PreAuthorize("@ss.hasPermi('system:map:edit')")
    @Log(title = "受理信息映射 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FieldMap fieldMap)
    {
        return toAjax(fieldMapService.updateFieldMap(fieldMap));
    }

    /**
     * 删除受理信息映射 
     */
//    @PreAuthorize("@ss.hasPermi('system:map:remove')")
    @Log(title = "受理信息映射 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sourceNames}")
    public AjaxResult remove(@PathVariable String[] sourceNames)
    {
        return toAjax(fieldMapService.deleteFieldMapByIds(sourceNames));
    }
}

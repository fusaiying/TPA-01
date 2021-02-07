package com.paic.ehis.cs.controller;

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
import com.paic.ehis.cs.domain.CollaborativeFrom;
import com.paic.ehis.cs.service.ICollaborativeFromService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 协办信息 Controller
 * 
 * @author sino
 * @date 2021-02-05
 */
@RestController
@RequestMapping("/from")
public class CollaborativeFromController extends BaseController
{
    @Autowired
    private ICollaborativeFromService collaborativeFromService;

    /**
     * 查询协办信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:from:list')")
    @GetMapping("/list")
    public TableDataInfo list(CollaborativeFrom collaborativeFrom)
    {
        startPage();
        List<CollaborativeFrom> list = collaborativeFromService.selectCollaborativeFromList(collaborativeFrom);
        return getDataTable(list);
    }

    /**
     * 导出协办信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:from:export')")
    @Log(title = "协办信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CollaborativeFrom collaborativeFrom) throws IOException
    {
        List<CollaborativeFrom> list = collaborativeFromService.selectCollaborativeFromList(collaborativeFrom);
        ExcelUtil<CollaborativeFrom> util = new ExcelUtil<CollaborativeFrom>(CollaborativeFrom.class);
        util.exportExcel(response, list, "from");
    }

    /**
     * 获取协办信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:from:query')")
    @GetMapping(value = "/{collaborativeId}")
    public AjaxResult getInfo(@PathVariable("collaborativeId") Long collaborativeId)
    {
        return AjaxResult.success(collaborativeFromService.selectCollaborativeFromById(collaborativeId));
    }

    /**
     * 新增协办信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:from:add')")
    @Log(title = "协办信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CollaborativeFrom collaborativeFrom)
    {
        return toAjax(collaborativeFromService.insertCollaborativeFrom(collaborativeFrom));
    }

    /**
     * 修改协办信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:from:edit')")
    @Log(title = "协办信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CollaborativeFrom collaborativeFrom)
    {
        return toAjax(collaborativeFromService.updateCollaborativeFrom(collaborativeFrom));
    }

    /**
     * 删除协办信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:from:remove')")
    @Log(title = "协办信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{collaborativeIds}")
    public AjaxResult remove(@PathVariable Long[] collaborativeIds)
    {
        return toAjax(collaborativeFromService.deleteCollaborativeFromByIds(collaborativeIds));
    }
}

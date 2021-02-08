package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.service.IWorkOrderAcceptService;
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
 * 工单申请信息 Controller
 * 
 * @author sino
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/accept")
public class WorkOrderAcceptController extends BaseController
{
    @Autowired
    private IWorkOrderAcceptService workOrderAcceptService;

    /**
     * 查询工单申请信息 列表
     */
//    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:accept:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkOrderAccept workOrderAccept)
    {
        startPage();
        List<WorkOrderAccept> list = workOrderAcceptService.selectWorkOrderAcceptList(workOrderAccept);
        return getDataTable(list);
    }

    /**
     * 导出工单申请信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:export')")
    @Log(title = "工单申请信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkOrderAccept workOrderAccept) throws IOException
    {
        List<WorkOrderAccept> list = workOrderAcceptService.selectWorkOrderAcceptList(workOrderAccept);
        ExcelUtil<WorkOrderAccept> util = new ExcelUtil<WorkOrderAccept>(WorkOrderAccept.class);
        util.exportExcel(response, list, "accept");
    }

    /**
     * 获取工单申请信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:query')")
    @GetMapping(value = "/{workOrderNo}")
    public AjaxResult getInfo(@PathVariable("workOrderNo") String workOrderNo)
    {
        return AjaxResult.success(workOrderAcceptService.selectWorkOrderAcceptById(workOrderNo));
    }

    /**
     * 新增工单申请信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:add')")
    @Log(title = "工单申请信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkOrderAccept workOrderAccept)
    {
        return toAjax(workOrderAcceptService.insertWorkOrderAccept(workOrderAccept));
    }

    /**
     * 修改工单申请信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:edit')")
    @Log(title = "工单申请信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkOrderAccept workOrderAccept)
    {
        return toAjax(workOrderAcceptService.updateWorkOrderAccept(workOrderAccept));
    }

    /**
     * 删除工单申请信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:remove')")
    @Log(title = "工单申请信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workOrderNos}")
    public AjaxResult remove(@PathVariable String[] workOrderNos)
    {
        return toAjax(workOrderAcceptService.deleteWorkOrderAcceptByIds(workOrderNos));
    }
}

package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.service.IFlowLogService;
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
 * 业务流转信息 Controller
 * 
 * @author sino
 * @date 2021-01-29
 */
@RestController
@RequestMapping("/FlowLog")
public class FlowLogController extends BaseController
{
    @Autowired
    private IFlowLogService flowLogService;

    /**
     * 查询业务流转信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:list')")
    @GetMapping("/searchFlowLog")
    public TableDataInfo list(FlowLog flowLog)
    {
        startPage();
        List<FlowLog> list = flowLogService.selectFlowLogList(flowLog);
        return getDataTable(list);
    }

    /**
     * 导出业务流转信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:export')")
    @Log(title = "业务流转信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FlowLog flowLog) throws IOException
    {
        List<FlowLog> list = flowLogService.selectFlowLogList(flowLog);
        ExcelUtil<FlowLog> util = new ExcelUtil<FlowLog>(FlowLog.class);
        util.exportExcel(response, list, "log");
    }

    /**
     * 获取业务流转信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:log:query')")
    @GetMapping(value = "/{flowId}")
    public AjaxResult getInfo(@PathVariable("flowId") String flowId)
    {
        return AjaxResult.success(flowLogService.selectFlowLogById(flowId));
    }

    /**
     * 新增业务流转信息 
     */
    @PreAuthorize("@ss.hasPermi('system:log:add')")
    @Log(title = "业务流转信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FlowLog flowLog)
    {
        return toAjax(flowLogService.insertFlowLog(flowLog));
    }

    /**
     * 修改业务流转信息 
     */
    @PreAuthorize("@ss.hasPermi('system:log:edit')")
    @Log(title = "业务流转信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FlowLog flowLog)
    {
        return toAjax(flowLogService.updateFlowLog(flowLog));
    }

    /**
     * 删除业务流转信息 
     */
    @PreAuthorize("@ss.hasPermi('system:log:remove')")
    @Log(title = "业务流转信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{flowIds}")
    public AjaxResult remove(@PathVariable String[] flowIds)
    {
        return toAjax(flowLogService.deleteFlowLogByIds(flowIds));
    }
}

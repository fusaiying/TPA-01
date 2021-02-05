package com.paic.ehis.finance.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.finance.domain.FinanceAdvanceSettleTask;
import com.paic.ehis.finance.service.IFinanceAdvanceSettleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 代垫费结算任务Controller
 * 
 * @author sino
 * @date 2021-01-30
 */
@RestController
@RequestMapping("/task")
public class FinanceAdvanceSettleTaskController extends BaseController
{
    @Autowired
    private IFinanceAdvanceSettleTaskService financeAdvanceSettleTaskService;

    /**
     * 查询代垫费结算任务列表
     */
   // @PreAuthorize("@ss.hasPermi('system:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinanceAdvanceSettleTask financeAdvanceSettleTask)
    {
        startPage();
        List<FinanceAdvanceSettleTask> list = financeAdvanceSettleTaskService.selectFinanceAdvanceSettleTaskList(financeAdvanceSettleTask);
        return getDataTable(list);
    }

    /**
     * 导出代垫费结算任务列表
     */
   // @PreAuthorize("@ss.hasPermi('system:task:export')")
    @Log(title = "代垫费结算任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinanceAdvanceSettleTask financeAdvanceSettleTask) throws IOException
    {
        List<FinanceAdvanceSettleTask> list = financeAdvanceSettleTaskService.selectFinanceAdvanceSettleTaskList(financeAdvanceSettleTask);
        ExcelUtil<FinanceAdvanceSettleTask> util = new ExcelUtil<FinanceAdvanceSettleTask>(FinanceAdvanceSettleTask.class);
        util.exportExcel(response, list, "task");
    }

    /**
     * 获取代垫费结算任务详细信息
     */
   // @PreAuthorize("@ss.hasPermi('system:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(financeAdvanceSettleTaskService.selectFinanceAdvanceSettleTaskById(taskId));
    }

    /**
     * 新增代垫费结算任务
     */
   // @PreAuthorize("@ss.hasPermi('system:task:add')")
    @Log(title = "代垫费结算任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinanceAdvanceSettleTask financeAdvanceSettleTask)
    {
        return toAjax(financeAdvanceSettleTaskService.insertFinanceAdvanceSettleTask(financeAdvanceSettleTask));
    }

    /**
     * 修改代垫费结算任务
     */
   // @PreAuthorize("@ss.hasPermi('system:task:edit')")
    @Log(title = "代垫费结算任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinanceAdvanceSettleTask financeAdvanceSettleTask)
    {
        return toAjax(financeAdvanceSettleTaskService.updateFinanceAdvanceSettleTask(financeAdvanceSettleTask));
    }

    /**
     * 删除代垫费结算任务
     */
   // @PreAuthorize("@ss.hasPermi('system:task:remove')")
    @Log(title = "代垫费结算任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(financeAdvanceSettleTaskService.deleteFinanceAdvanceSettleTaskByIds(taskIds));
    }
}

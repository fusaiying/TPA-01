package com.paic.ehis.claimmgt.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealTask;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseAppealTaskVo;
import com.paic.ehis.claimmgt.service.IClaimCaseAppealTaskService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
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
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件申诉任务Controller
 * 
 * @author sino
 * @date 2021-03-15
 */
@RestController
@RequestMapping("/claimAppeal")
public class ClaimCaseAppealTaskController extends BaseController
{
    @Autowired
    private IClaimCaseAppealTaskService claimCaseAppealTaskService;

    /**
     * 查询案件申诉任务列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ClaimCaseAppealTaskVo vo)
    {
        startPage(vo);
        List<ClaimCaseAppealTaskVo> list = claimCaseAppealTaskService.getAppealList(vo);
        return getDataTable(list);
    }

    /**
     * 导出案件申诉任务列表
     */
    //@PreAuthorize(hasPermi = "system:task:export")
    @Log(title = "案件申诉任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseAppealTask claimCaseAppealTask) throws IOException
    {
        List<ClaimCaseAppealTask> list = claimCaseAppealTaskService.selectClaimCaseAppealTaskList(claimCaseAppealTask);
        ExcelUtil<ClaimCaseAppealTask> util = new ExcelUtil<ClaimCaseAppealTask>(ClaimCaseAppealTask.class);
        util.exportExcel(response, list, "task");
    }

    /**
     * 获取案件申诉任务详细信息
     */
    //@PreAuthorize(hasPermi = "system:task:query")
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(@RequestBody ClaimCaseAppealTask param)
    {
        return AjaxResult.success(claimCaseAppealTaskService.selectClaimCaseAppealTaskByParam(param));
    }

    /**
     * 新增案件申诉任务
     */
    //@PreAuthorize(hasPermi = "system:task:add")
    @Log(title = "案件申诉任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseAppealTask claimCaseAppealTask)
    {
        return toAjax(claimCaseAppealTaskService.insertClaimCaseAppealTask(claimCaseAppealTask));
    }

    /**
     * 修改案件申诉任务
     */
    //@PreAuthorize(hasPermi = "system:task:edit")
    @Log(title = "案件申诉任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseAppealTask claimCaseAppealTask)
    {
        claimCaseAppealTask = claimCaseAppealTaskService.updateClaimCaseAppealTask(claimCaseAppealTask);
        if(claimCaseAppealTask.getDealType().equalsIgnoreCase("audit")) {
            if (claimCaseAppealTask.getIsAgree().equals("01")) {
                claimCaseAppealTaskService.updateClaimCaseAppealBill(claimCaseAppealTask);
            }
        }
        return AjaxResult.success(claimCaseAppealTask);
    }

    /**
     * 删除案件申诉任务
     */
    //@PreAuthorize(hasPermi = "system:task:remove")
    @Log(title = "案件申诉任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(claimCaseAppealTaskService.deleteClaimCaseAppealTaskByIds(taskIds));
    }
}

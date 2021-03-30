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
    public AjaxResult getInfo(ClaimCaseAppealTask param)
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

        //初审确认  同意 / 不同意  （申诉完成 / 申诉退回）
        if(claimCaseAppealTask.getDealType().equalsIgnoreCase("audit")) {
            if(claimCaseAppealTask.getIsAgree().equals("01")) {
                /**
                 初审决定选择同意，点击初审确认后，
                 此时申诉案件报案号为原报案号-1（流水），
                 并且流转至理赔审核岗原审核人工作池，
                 同时将原案件的批次信息、就诊人信息、申请人信息、收款人信息、受理信息、账单信息、赔付明细（重新匹配理算）、
                 赔付备注（类型为申诉案件）带入申诉案件中，需要重新进行赔付计算，重新下发赔付结论。
                 */
                String newRptNo = claimCaseAppealTaskService.getNewRptNo(claimCaseAppealTask.getAppealRptNo());
                claimCaseAppealTask.setNewRptNo(newRptNo);
                claimCaseAppealTaskService.clearClaimTableData(claimCaseAppealTask);
            }
        }
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

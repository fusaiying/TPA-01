package com.paic.ehis.claimflow.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.claimflow.domain.ClaimCaseRecord;
import com.paic.ehis.claimflow.service.IClaimCaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件操作记录 Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/caseRecord")
public class ClaimCaseRecordController extends BaseController
{
    @Autowired
    private IClaimCaseRecordService claimCaseRecordService;

    /**
     * 查询案件操作记录 列表
     */
   // @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseRecord claimCaseRecord)
    {
        startPage();
        List<ClaimCaseRecord> list = claimCaseRecordService.selectClaimCaseRecordList(claimCaseRecord);
        return getDataTable(list);
    }

    /**
     * 导出案件操作记录 列表
     */
   // @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "案件操作记录 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseRecord claimCaseRecord) throws IOException
    {
        List<ClaimCaseRecord> list = claimCaseRecordService.selectClaimCaseRecordList(claimCaseRecord);
        ExcelUtil<ClaimCaseRecord> util = new ExcelUtil<ClaimCaseRecord>(ClaimCaseRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取案件操作记录 详细信息
     */
  //  @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(claimCaseRecordService.selectClaimCaseRecordById(recordId));
    }

    /**
     * 新增案件操作记录 
     */
   // @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "案件操作记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseRecord claimCaseRecord)
    {
        return toAjax(claimCaseRecordService.insertClaimCaseRecord(claimCaseRecord));
    }

    /**
     * 修改案件操作记录 
     */
   // @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "案件操作记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseRecord claimCaseRecord)
    {
        return toAjax(claimCaseRecordService.updateClaimCaseRecord(claimCaseRecord));
    }

    @PutMapping("/updatehistoryFlag")
    public AjaxResult edit(String repNo)
    {
        return toAjax(claimCaseRecordService.updatehistoryFlag(repNo));
    }


    /**
     * 删除案件操作记录 
     */
   // @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "案件操作记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(claimCaseRecordService.deleteClaimCaseRecordByIds(recordIds));
    }

    /**
     * 工作流查看
     * @param claimCaseRecord
     * @return
     */
    @PostMapping("/selectWorkflow")
    public AjaxResult selectWorkflow(@RequestBody ClaimCaseRecord claimCaseRecord)
    {
        return AjaxResult.success(claimCaseRecordService.selectWorkflow(claimCaseRecord));
    }
    /**
     * 工作流查看
     * @param claimCaseRecord
     * @return
     */
    @PostMapping("/selectWorkflowByRptNo")
    public AjaxResult selectWorkflowByRptNo(@RequestBody ClaimCaseRecord claimCaseRecord)
    {
        return AjaxResult.success(claimCaseRecordService.selectWorkflowByRptNo(claimCaseRecord));
    }
}

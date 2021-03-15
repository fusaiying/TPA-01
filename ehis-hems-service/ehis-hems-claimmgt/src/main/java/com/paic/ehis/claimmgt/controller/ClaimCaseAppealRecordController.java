package com.paic.ehis.claimmgt.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealRecord;
import com.paic.ehis.claimmgt.service.IClaimCaseAppealRecordService;
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
 * 案件申诉任务记录Controller
 * 
 * @author sino
 * @date 2021-03-15
 */
@RestController
@RequestMapping("/record")
public class ClaimCaseAppealRecordController extends BaseController
{
    @Autowired
    private IClaimCaseAppealRecordService claimCaseAppealRecordService;

    /**
     * 查询案件申诉任务记录列表
     */
    //@PreAuthorize(hasPermi = "system:record:list")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseAppealRecord claimCaseAppealRecord)
    {
        startPage();
        List<ClaimCaseAppealRecord> list = claimCaseAppealRecordService.selectClaimCaseAppealRecordList(claimCaseAppealRecord);
        return getDataTable(list);
    }

    /**
     * 导出案件申诉任务记录列表
     */
    //@PreAuthorize(hasPermi = "system:record:export")
    @Log(title = "案件申诉任务记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseAppealRecord claimCaseAppealRecord) throws IOException
    {
        List<ClaimCaseAppealRecord> list = claimCaseAppealRecordService.selectClaimCaseAppealRecordList(claimCaseAppealRecord);
        ExcelUtil<ClaimCaseAppealRecord> util = new ExcelUtil<ClaimCaseAppealRecord>(ClaimCaseAppealRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取案件申诉任务记录详细信息
     */
    //@PreAuthorize(hasPermi = "system:record:query")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(claimCaseAppealRecordService.selectClaimCaseAppealRecordById(recordId));
    }

    /**
     * 新增案件申诉任务记录
     */
    //@PreAuthorize(hasPermi = "system:record:add")
    @Log(title = "案件申诉任务记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseAppealRecord claimCaseAppealRecord)
    {
        return toAjax(claimCaseAppealRecordService.insertClaimCaseAppealRecord(claimCaseAppealRecord));
    }

    /**
     * 修改案件申诉任务记录
     */
    //@PreAuthorize(hasPermi = "system:record:edit")
    @Log(title = "案件申诉任务记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseAppealRecord claimCaseAppealRecord)
    {
        return toAjax(claimCaseAppealRecordService.updateClaimCaseAppealRecord(claimCaseAppealRecord));
    }

    /**
     * 删除案件申诉任务记录
     */
    //@PreAuthorize(hasPermi = "system:record:remove")
    @Log(title = "案件申诉任务记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(claimCaseAppealRecordService.deleteClaimCaseAppealRecordByIds(recordIds));
    }
}

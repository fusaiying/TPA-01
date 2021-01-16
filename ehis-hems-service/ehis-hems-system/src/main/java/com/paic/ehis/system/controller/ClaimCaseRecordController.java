package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCaseRecord;
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
import com.paic.ehis.system.service.IClaimCaseRecordService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

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
    @PreAuthorize("@ss.hasPermi('system:record:list')")
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
    @PreAuthorize("@ss.hasPermi('system:record:export')")
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
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(claimCaseRecordService.selectClaimCaseRecordById(recordId));
    }

    /**
     * 新增案件操作记录 
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "案件操作记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseRecord claimCaseRecord)
    {
        return toAjax(claimCaseRecordService.insertClaimCaseRecord(claimCaseRecord));
    }

    /**
     * 修改案件操作记录 
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "案件操作记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseRecord claimCaseRecord)
    {
        return toAjax(claimCaseRecordService.updateClaimCaseRecord(claimCaseRecord));
    }

    /**
     * 删除案件操作记录 
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "案件操作记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(claimCaseRecordService.deleteClaimCaseRecordByIds(recordIds));
    }
}

package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimBatchRecord;
import com.paic.ehis.system.service.IClaimBatchRecordService;
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
 * 理赔批次流程记录 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/record")
public class ClaimBatchRecordController extends BaseController
{
    @Autowired
    private IClaimBatchRecordService claimBatchRecordService;

    /**
     * 查询理赔批次流程记录 列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimBatchRecord claimBatchRecord)
    {
        startPage();
        List<ClaimBatchRecord> list = claimBatchRecordService.selectClaimBatchRecordList(claimBatchRecord);
        return getDataTable(list);
    }

    /**
     * 导出理赔批次流程记录 列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimBatchRecord claimBatchRecord) throws IOException
    {
        List<ClaimBatchRecord> list = claimBatchRecordService.selectClaimBatchRecordList(claimBatchRecord);
        ExcelUtil<ClaimBatchRecord> util = new ExcelUtil<ClaimBatchRecord>(ClaimBatchRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取理赔批次流程记录 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(claimBatchRecordService.selectClaimBatchRecordById(recordId));
    }

    /**
     * 新增理赔批次流程记录 
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimBatchRecord claimBatchRecord)
    {
        return toAjax(claimBatchRecordService.insertClaimBatchRecord(claimBatchRecord));
    }

    /**
     * 修改理赔批次流程记录 
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimBatchRecord claimBatchRecord)
    {
        return toAjax(claimBatchRecordService.updateClaimBatchRecord(claimBatchRecord));
    }

    /**
     * 删除理赔批次流程记录 
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(claimBatchRecordService.deleteClaimBatchRecordByIds(recordIds));
    }
}

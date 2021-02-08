package com.paic.ehis.finance.controller;


import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;

import com.paic.ehis.finance.domain.FinanceSettleRecord;
import com.paic.ehis.finance.service.IFinanceSettleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 结算操作记录Controller
 * 
 * @author sino
 * @date 2021-01-30
 */
@RestController
@RequestMapping("/settleRecord")
public class FinanceSettleRecordController extends BaseController
{
    @Autowired
    private IFinanceSettleRecordService financeSettleRecordService;

    /**
     * 查询结算操作记录列表
     */
   // @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinanceSettleRecord financeSettleRecord)
    {
        startPage();
        List<FinanceSettleRecord> list = financeSettleRecordService.selectFinanceSettleRecordList(financeSettleRecord);
        return getDataTable(list);
    }

    /**
     * 导出结算操作记录列表
     */
   // @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "结算操作记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinanceSettleRecord financeSettleRecord) throws IOException
    {
        List<FinanceSettleRecord> list = financeSettleRecordService.selectFinanceSettleRecordList(financeSettleRecord);
        ExcelUtil<FinanceSettleRecord> util = new ExcelUtil<FinanceSettleRecord>(FinanceSettleRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取结算操作记录详细信息
     */
   // @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(financeSettleRecordService.selectFinanceSettleRecordById(recordId));
    }

    /**
     * 新增结算操作记录
     */
   // @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "结算操作记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinanceSettleRecord financeSettleRecord)
    {
        return toAjax(financeSettleRecordService.insertFinanceSettleRecord(financeSettleRecord));
    }

    /**
     * 修改结算操作记录
     */
   // @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "结算操作记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinanceSettleRecord financeSettleRecord)
    {
        return toAjax(financeSettleRecordService.updateFinanceSettleRecord(financeSettleRecord));
    }

    /**
     * 删除结算操作记录
     */
   // @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "结算操作记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(financeSettleRecordService.deleteFinanceSettleRecordByIds(recordIds));
    }
}

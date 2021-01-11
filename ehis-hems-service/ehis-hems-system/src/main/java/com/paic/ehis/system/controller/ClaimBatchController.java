package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimBatch;
import com.paic.ehis.system.service.IClaimBatchService;
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
 * 理赔批次 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/batch")
public class ClaimBatchController extends BaseController
{
    @Autowired
    private IClaimBatchService claimBatchService;

    /**
     * 查询理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimBatch claimBatch)
    {
        startPage();
        List<ClaimBatch> list = claimBatchService.selectClaimBatchList(claimBatch);
        return getDataTable(list);
    }

    /**
     * 导出理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimBatch claimBatch) throws IOException
    {
        List<ClaimBatch> list = claimBatchService.selectClaimBatchList(claimBatch);
        ExcelUtil<ClaimBatch> util = new ExcelUtil<ClaimBatch>(ClaimBatch.class);
        util.exportExcel(response, list, "batch");
    }

    /**
     * 获取理赔批次 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:batch:query')")
    @GetMapping(value = "/{batchNo}")
    public AjaxResult getInfo(@PathVariable("batchNo") String batchNo)
    {
        return AjaxResult.success(claimBatchService.selectClaimBatchById(batchNo));
    }

    /**
     * 新增理赔批次 
     */
    @PreAuthorize("@ss.hasPermi('system:batch:add')")
    @Log(title = "理赔批次 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimBatch claimBatch)
    {
        return toAjax(claimBatchService.insertClaimBatch(claimBatch));
    }

    /**
     * 修改理赔批次 
     */
    @PreAuthorize("@ss.hasPermi('system:batch:edit')")
    @Log(title = "理赔批次 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimBatch claimBatch)
    {
        return toAjax(claimBatchService.updateClaimBatch(claimBatch));
    }

    /**
     * 删除理赔批次 
     */
    @PreAuthorize("@ss.hasPermi('system:batch:remove')")
    @Log(title = "理赔批次 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{batchNos}")
    public AjaxResult remove(@PathVariable String[] batchNos)
    {
        return toAjax(claimBatchService.deleteClaimBatchByIds(batchNos));
    }
}

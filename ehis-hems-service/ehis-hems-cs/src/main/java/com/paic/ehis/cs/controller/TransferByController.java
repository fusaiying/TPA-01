package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.TransferBy;
import com.paic.ehis.cs.service.ITransferByService;
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
 * 转办信息 Controller
 * 
 * @author sino
 * @date 2021-02-07
 */
@RestController
@RequestMapping("/by")
public class TransferByController extends BaseController
{
    @Autowired
    private ITransferByService transferByService;

    /**
     * 查询转办信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:by:list')")
    @GetMapping("/list")
    public TableDataInfo list(TransferBy transferBy)
    {
        startPage();
        List<TransferBy> list = transferByService.selectTransferByList(transferBy);
        return getDataTable(list);
    }

    /**
     * 导出转办信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:by:export')")
    @Log(title = "转办信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TransferBy transferBy) throws IOException
    {
        List<TransferBy> list = transferByService.selectTransferByList(transferBy);
        ExcelUtil<TransferBy> util = new ExcelUtil<TransferBy>(TransferBy.class);
        util.exportExcel(response, list, "by");
    }

    /**
     * 获取转办信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:by:query')")
    @GetMapping(value = "/{transferId}")
    public AjaxResult getInfo(@PathVariable("transferId") Long transferId)
    {
        return AjaxResult.success(transferByService.selectTransferByById(transferId));
    }

    /**
     * 新增转办信息 
     */
    @PreAuthorize("@ss.hasPermi('system:by:add')")
    @Log(title = "转办信息 ", businessType = BusinessType.INSERT)
    @PostMapping("/transferSubmit")
    public AjaxResult add(@RequestBody TransferBy transferBy)
    {
        return toAjax(transferByService.insertTransferBy(transferBy));
    }

    /**
     * 修改转办信息 
     */
    @PreAuthorize("@ss.hasPermi('system:by:edit')")
    @Log(title = "转办信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferBy transferBy)
    {
        return toAjax(transferByService.updateTransferBy(transferBy));
    }

    /**
     * 删除转办信息 
     */
    @PreAuthorize("@ss.hasPermi('system:by:remove')")
    @Log(title = "转办信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{transferIds}")
    public AjaxResult remove(@PathVariable Long[] transferIds)
    {
        return toAjax(transferByService.deleteTransferByByIds(transferIds));
    }
}

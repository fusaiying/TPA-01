package com.paic.ehis.finance.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.finance.domain.FinanceCollectionInfo;
import com.paic.ehis.finance.service.IFinanceCollectionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 实收信息明细Controller
 * 
 * @author sino
 * @date 2021-01-30
 */
@RestController
@RequestMapping("/CollectionInfo")
public class FinanceCollectionInfoController extends BaseController
{
    @Autowired
    private IFinanceCollectionInfoService financeCollectionInfoService;

    /**
     * 查询实收信息明细列表
     */
  //  @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinanceCollectionInfo financeCollectionInfo)
    {
        startPage();
        List<FinanceCollectionInfo> list = financeCollectionInfoService.selectFinanceCollectionInfoList(financeCollectionInfo);
        return getDataTable(list);
    }

    /**
     * 导出实收信息明细列表
     */
  //  @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "实收信息明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinanceCollectionInfo financeCollectionInfo) throws IOException
    {
        List<FinanceCollectionInfo> list = financeCollectionInfoService.selectFinanceCollectionInfoList(financeCollectionInfo);
        ExcelUtil<FinanceCollectionInfo> util = new ExcelUtil<FinanceCollectionInfo>(FinanceCollectionInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取实收信息明细详细信息
     */
  //  @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{collectionId}")
    public AjaxResult getInfo(@PathVariable("collectionId") Long collectionId)
    {
        return AjaxResult.success(financeCollectionInfoService.selectFinanceCollectionInfoById(collectionId));
    }

    /**
     * 新增实收信息明细
     */
  //  @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "实收信息明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinanceCollectionInfo financeCollectionInfo)
    {
        return toAjax(financeCollectionInfoService.insertFinanceCollectionInfo(financeCollectionInfo));
    }

    /**
     * 修改实收信息明细
     */
  //  @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "实收信息明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinanceCollectionInfo financeCollectionInfo)
    {
        return toAjax(financeCollectionInfoService.updateFinanceCollectionInfo(financeCollectionInfo));
    }

    /**
     * 删除实收信息明细
     */
  //  @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "实收信息明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{collectionIds}")
    public AjaxResult remove(@PathVariable Long[] collectionIds)
    {
        return toAjax(financeCollectionInfoService.deleteFinanceCollectionInfoByIds(collectionIds));
    }
}

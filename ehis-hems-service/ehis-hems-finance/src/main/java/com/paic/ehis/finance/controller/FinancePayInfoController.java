package com.paic.ehis.finance.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.finance.domain.FinancePayInfo;
import com.paic.ehis.finance.domain.dto.TransferfailedDTO;
import com.paic.ehis.finance.domain.vo.TransferfailedVo;
import com.paic.ehis.finance.service.IFinancePayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 支付信息Controller
 * 
 * @author sino
 * @date 2021-02-01
 */
@RestController
@RequestMapping("/payInfo")
public class FinancePayInfoController extends BaseController
{
    @Autowired
    private IFinancePayInfoService financePayInfoService;

    /**
     * 查询支付信息列表
     */
   // @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancePayInfo financePayInfo)
    {
        startPage();
        List<FinancePayInfo> list = financePayInfoService.selectFinancePayInfoList(financePayInfo);
        return getDataTable(list);
    }

    /**
     * 导出支付信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "支付信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancePayInfo financePayInfo) throws IOException
    {
        List<FinancePayInfo> list = financePayInfoService.selectFinancePayInfoList(financePayInfo);
        ExcelUtil<FinancePayInfo> util = new ExcelUtil<FinancePayInfo>(FinancePayInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取支付信息详细信息
     */
   // @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{payId}")
    public AjaxResult getInfo(@PathVariable("payId") Long payId)
    {
        return AjaxResult.success(financePayInfoService.selectFinancePayInfoById(payId));
    }

    /**
     * 新增支付信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "支付信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancePayInfo financePayInfo)
    {
        return toAjax(financePayInfoService.insertFinancePayInfo(financePayInfo));
    }

    /**
     * 修改支付信息
     */
   // @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "支付信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancePayInfo financePayInfo)
    {
        return toAjax(financePayInfoService.updateFinancePayInfo(financePayInfo));
    }

    /**
     * 删除支付信息
     */
   // @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "支付信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{payIds}")
    public AjaxResult remove(@PathVariable Long[] payIds)
    {
        return toAjax(financePayInfoService.deleteFinancePayInfoByIds(payIds));
    }

    /**
     * 查询支付信息列表
     */
   // @PreAuthorize("@ss.hasPermi('system:info:TransferFailedList')")
    @PostMapping("/TransferFailedList")
    public TableDataInfo TransferFailedList(@RequestBody TransferfailedDTO transferfailedDTO)
    {
        startPage();
        List<TransferfailedVo> list = financePayInfoService.selectTransferfailedList(transferfailedDTO);
        return getDataTable(list);
    }
}

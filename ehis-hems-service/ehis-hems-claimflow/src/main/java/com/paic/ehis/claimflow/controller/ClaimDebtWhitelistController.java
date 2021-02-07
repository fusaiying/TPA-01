package com.paic.ehis.claimflow.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.claimflow.domain.ClaimDebtWhitelist;
import com.paic.ehis.claimflow.service.IClaimDebtWhitelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件追讨白名单Controller
 * 
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/whitelist")
public class ClaimDebtWhitelistController extends BaseController
{
    @Autowired
    private IClaimDebtWhitelistService claimDebtWhitelistService;

    /**
     * 查询案件追讨白名单列表
     */
    //@PreAuthorize("@ss.hasPermi('system:whitelist:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimDebtWhitelist claimDebtWhitelist)
    {
        startPage();
        List<ClaimDebtWhitelist> list = claimDebtWhitelistService.selectClaimDebtWhitelistList(claimDebtWhitelist);
        return getDataTable(list);
    }

    /**
     * 导出案件追讨白名单列表
     */
   // @PreAuthorize("@ss.hasPermi('system:whitelist:export')")
    @Log(title = "案件追讨白名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimDebtWhitelist claimDebtWhitelist) throws IOException
    {
        List<ClaimDebtWhitelist> list = claimDebtWhitelistService.selectClaimDebtWhitelistList(claimDebtWhitelist);
        ExcelUtil<ClaimDebtWhitelist> util = new ExcelUtil<ClaimDebtWhitelist>(ClaimDebtWhitelist.class);
        util.exportExcel(response, list, "whitelist");
    }

    /**
     * 获取案件追讨白名单详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:whitelist:query')")
    @GetMapping(value = "/{debtWhitelistId}")
    public AjaxResult getInfo(@PathVariable("debtWhitelistId") Long debtWhitelistId)
    {
        return AjaxResult.success(claimDebtWhitelistService.selectClaimDebtWhitelistById(debtWhitelistId));
    }

    /**
     * 白名单维护界面初始化或未录入任何查询条件，点击查询按钮时，默认查询状态不为失效，追缴通知为是的白名单信息
     */
   // @PreAuthorize("@ss.hasPermi('system:whitelist:list1')")
    @GetMapping("/list1")
    public TableDataInfo list1(ClaimDebtWhitelist claimDebtWhitelist)
    {
        startPage();
        List<ClaimDebtWhitelist> list = claimDebtWhitelistService.selectClaimDebtWhitelistList1(claimDebtWhitelist);
        return getDataTable(list);
    }
    /**
     * 新增案件追讨白名单
     */
   // @PreAuthorize("@ss.hasPermi('system:whitelist:add')")
    @Log(title = "案件追讨白名单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ClaimDebtWhitelist claimDebtWhitelist)
    {
        return toAjax(claimDebtWhitelistService.insertClaimDebtWhitelist(claimDebtWhitelist));
    }

    /**
     * 修改案件追讨白名单
     */
   // @PreAuthorize("@ss.hasPermi('system:whitelist:edit')")
    @Log(title = "案件追讨白名单", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody ClaimDebtWhitelist claimDebtWhitelist)
    {
        return toAjax(claimDebtWhitelistService.updateClaimDebtWhitelist(claimDebtWhitelist));
    }

    /**
     * 批量删除案件追讨白名单
     */
   // @PreAuthorize("@ss.hasPermi('system:whitelist:remove')")
    @Log(title = "案件追讨白名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{debtWhitelistIds}")
    public AjaxResult remove(@PathVariable Long[] debtWhitelistIds)
    {
        return toAjax(claimDebtWhitelistService.deleteClaimDebtWhitelistByIds(debtWhitelistIds));
    }
    /**
     * 删除案件追讨白名单
     */
   // @PreAuthorize("@ss.hasPermi('system:whitelist:removeone')")
    @Log(title = "案件追讨白名单", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{debtWhitelistId}")
    public AjaxResult removeone(@PathVariable Long debtWhitelistId)
    {
        return toAjax(claimDebtWhitelistService.deleteClaimDebtWhitelistById(debtWhitelistId));
    }

    /*根据报案号查询欠款信息*/
    //@PreAuthorize("@ss.hasPermi('system:whitelist:ResidualList')")
    @GetMapping("/residualList")
    public AjaxResult residualList(String rptNo)
    {
        return AjaxResult.success(claimDebtWhitelistService.selectResidualList(rptNo));
    }
}

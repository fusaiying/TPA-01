package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCasePayee;
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
import com.paic.ehis.system.service.IClaimCasePayeeService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件领款人信息Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/payee")
public class ClaimCasePayeeController extends BaseController
{
    @Autowired
    private IClaimCasePayeeService claimCasePayeeService;

    /**
     * 查询案件领款人信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:payee:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCasePayee claimCasePayee)
    {
        startPage();
        List<ClaimCasePayee> list = claimCasePayeeService.selectClaimCasePayeeList(claimCasePayee);
        return getDataTable(list);
    }

    /**
     * 导出案件领款人信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:payee:export')")
    @Log(title = "案件领款人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCasePayee claimCasePayee) throws IOException
    {
        List<ClaimCasePayee> list = claimCasePayeeService.selectClaimCasePayeeList(claimCasePayee);
        ExcelUtil<ClaimCasePayee> util = new ExcelUtil<ClaimCasePayee>(ClaimCasePayee.class);
        util.exportExcel(response, list, "payee");
    }

    /**
     * 获取案件领款人信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:payee:query')")
    @GetMapping(value = "/{payeeId}")
    public AjaxResult getInfo(@PathVariable("payeeId") Long payeeId)
    {
        return AjaxResult.success(claimCasePayeeService.selectClaimCasePayeeById(payeeId));
    }

    /**
     * 新增案件领款人信息
     */
    @PreAuthorize("@ss.hasPermi('system:payee:add')")
    @Log(title = "案件领款人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCasePayee claimCasePayee)
    {
        return toAjax(claimCasePayeeService.insertClaimCasePayee(claimCasePayee));
    }

    /**
     * 修改案件领款人信息
     */
    @PreAuthorize("@ss.hasPermi('system:payee:edit')")
    @Log(title = "案件领款人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCasePayee claimCasePayee)
    {
        return toAjax(claimCasePayeeService.updateClaimCasePayee(claimCasePayee));
    }

    /**
     * 无效化案件领款人信息
     */
    @PreAuthorize("@ss.hasPermi('system:payee:remove')")
    @Log(title = "无效化案件领款人信息", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/rptNo")
    public AjaxResult Invalidation(@RequestBody String rptNo)
    {
        return toAjax(claimCasePayeeService.deleteClaimCasePayeeByRptNo(rptNo));
    }
    /**
     * 删除案件领款人信息
     */
    @PreAuthorize("@ss.hasPermi('system:payee:remove')")
    @Log(title = "案件领款人信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{payeeIds}")
    public AjaxResult remove(@PathVariable Long[] payeeIds)
    {
        return toAjax(claimCasePayeeService.deleteClaimCasePayeeByIds(payeeIds));
    }

    /**
     * 查询案件领款人信息列表,根据报案号直插最近的一条
     */
    @PreAuthorize("@ss.hasPermi('system:payee:list')")
    @GetMapping("/selectOne")
    public AjaxResult selectOne(ClaimCasePayee claimCasePayee)
    {
        return AjaxResult.success(claimCasePayeeService.selectClaimCasePayeeselectOne(claimCasePayee));
    }
}

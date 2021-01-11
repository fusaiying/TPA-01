package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.service.IClaimCaseService;
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
import com.paic.ehis.system.domain.ClaimCase;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件信息 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/case")
public class ClaimCaseController extends BaseController
{
    @Autowired
    private IClaimCaseService claimCaseService;

    /**
     * 查询案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCase claimCase)
    {
        startPage();
        List<ClaimCase> list = claimCaseService.selectClaimCaseList(claimCase);
        return getDataTable(list);
    }

    /**
     * 导出案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCase claimCase) throws IOException
    {
        List<ClaimCase> list = claimCaseService.selectClaimCaseList(claimCase);
        ExcelUtil<ClaimCase> util = new ExcelUtil<ClaimCase>(ClaimCase.class);
        util.exportExcel(response, list, "case");
    }

    /**
     * 获取案件信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseService.selectClaimCaseById(rptNo));
    }

    /**
     * 新增案件信息 
     */
    @PreAuthorize("@ss.hasPermi('system:case:add')")
    @Log(title = "案件信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCase claimCase)
    {
        return toAjax(claimCaseService.insertClaimCase(claimCase));
    }

    /**
     * 修改案件信息 
     */
    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCase claimCase)
    {
        return toAjax(claimCaseService.updateClaimCase(claimCase));
    }

    /**
     * 删除案件信息 
     */
    @PreAuthorize("@ss.hasPermi('system:case:remove')")
    @Log(title = "案件信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rptNos}")
    public AjaxResult remove(@PathVariable String[] rptNos)
    {
        return toAjax(claimCaseService.deleteClaimCaseByIds(rptNos));
    }
}

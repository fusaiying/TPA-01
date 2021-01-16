package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCaseInsured;
import com.paic.ehis.system.domain.dto.InsuredAndPolicy;
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
import com.paic.ehis.system.service.IClaimCaseInsuredService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件被保人信息Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/insured")
public class ClaimCaseInsuredController extends BaseController
{
    @Autowired
    private IClaimCaseInsuredService claimCaseInsuredService;

    /**
     * 查询案件被保人信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:insured:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseInsured claimCaseInsured)
    {
        startPage();
        List<ClaimCaseInsured> list = claimCaseInsuredService.selectClaimCaseInsuredList(claimCaseInsured);
        return getDataTable(list);
    }

    /**
     * 导出案件被保人信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:insured:export')")
    @Log(title = "案件被保人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseInsured claimCaseInsured) throws IOException
    {
        List<ClaimCaseInsured> list = claimCaseInsuredService.selectClaimCaseInsuredList(claimCaseInsured);
        ExcelUtil<ClaimCaseInsured> util = new ExcelUtil<ClaimCaseInsured>(ClaimCaseInsured.class);
        util.exportExcel(response, list, "insured");
    }

    /**
     * 获取案件被保人信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:insured:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseInsuredService.selectClaimCaseInsuredById(rptNo));
    }

    /**
     * 新增案件被保人信息
     */
    @PreAuthorize("@ss.hasPermi('system:insured:add')")
    @Log(title = "案件被保人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseInsured claimCaseInsured)
    {
        return toAjax(claimCaseInsuredService.insertClaimCaseInsured(claimCaseInsured));
    }

    /**
     * 修改案件被保人信息
     */
    @PreAuthorize("@ss.hasPermi('system:insured:edit')")
    @Log(title = "案件被保人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseInsured claimCaseInsured)
    {
        return toAjax(claimCaseInsuredService.updateClaimCaseInsured(claimCaseInsured));
    }

    /**
     * 删除案件被保人信息
     */
    @PreAuthorize("@ss.hasPermi('system:insured:remove')")
    @Log(title = "案件被保人信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rptNos}")
    public AjaxResult remove(@PathVariable String[] rptNos)
    {
        return toAjax(claimCaseInsuredService.deleteClaimCaseInsuredByIds(rptNos));
    }

    /**受理
     * 新增案件被保人信息
     */
    @PreAuthorize("@ss.hasPermi('system:insured:add')")
    @Log(title = "案件被保人信息", businessType = BusinessType.INSERT)
    @PostMapping("/addInsuredAndPolicy")
    public AjaxResult addInsuredAndPolicy(@RequestBody InsuredAndPolicy insuredAndPolicy)
    {
        return toAjax(claimCaseInsuredService.insertClaimCaseInsuredAndPolicy(insuredAndPolicy));
    }
}

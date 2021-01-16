package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCaseBillDiagnosis;
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
import com.paic.ehis.system.service.IClaimCaseBillDiagnosisService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件账单诊断信息Controller
 * 
 * @author sino
 * @date 2021-01-15
 */
@RestController
@RequestMapping("/diagnosis")
public class ClaimCaseBillDiagnosisController extends BaseController
{
    @Autowired
    private IClaimCaseBillDiagnosisService claimCaseBillDiagnosisService;

    /**
     * 查询案件账单诊断信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:diagnosis:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseBillDiagnosis claimCaseBillDiagnosis)
    {
        startPage();
        List<ClaimCaseBillDiagnosis> list = claimCaseBillDiagnosisService.selectClaimCaseBillDiagnosisList(claimCaseBillDiagnosis);
        return getDataTable(list);
    }

    /**
     * 导出案件账单诊断信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:diagnosis:export')")
    @Log(title = "案件账单诊断信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseBillDiagnosis claimCaseBillDiagnosis) throws IOException
    {
        List<ClaimCaseBillDiagnosis> list = claimCaseBillDiagnosisService.selectClaimCaseBillDiagnosisList(claimCaseBillDiagnosis);
        ExcelUtil<ClaimCaseBillDiagnosis> util = new ExcelUtil<ClaimCaseBillDiagnosis>(ClaimCaseBillDiagnosis.class);
        util.exportExcel(response, list, "diagnosis");
    }

    /**
     * 获取案件账单诊断信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:diagnosis:query')")
    @GetMapping(value = "/{digId}")
    public AjaxResult getInfo(@PathVariable("digId") Long digId)
    {
        return AjaxResult.success(claimCaseBillDiagnosisService.selectClaimCaseBillDiagnosisById(digId));
    }

    /**
     * 新增案件账单诊断信息
     */
    @PreAuthorize("@ss.hasPermi('system:diagnosis:add')")
    @Log(title = "案件账单诊断信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseBillDiagnosis claimCaseBillDiagnosis)
    {
        return toAjax(claimCaseBillDiagnosisService.insertClaimCaseBillDiagnosis(claimCaseBillDiagnosis));
    }

    /**
     * 修改案件账单诊断信息
     */
    @PreAuthorize("@ss.hasPermi('system:diagnosis:edit')")
    @Log(title = "案件账单诊断信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseBillDiagnosis claimCaseBillDiagnosis)
    {
        return toAjax(claimCaseBillDiagnosisService.updateClaimCaseBillDiagnosis(claimCaseBillDiagnosis));
    }

    /**
     * 删除案件账单诊断信息
     */
    @PreAuthorize("@ss.hasPermi('system:diagnosis:remove')")
    @Log(title = "案件账单诊断信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{digIds}")
    public AjaxResult remove(@PathVariable Long[] digIds)
    {
        return toAjax(claimCaseBillDiagnosisService.deleteClaimCaseBillDiagnosisByIds(digIds));
    }
}

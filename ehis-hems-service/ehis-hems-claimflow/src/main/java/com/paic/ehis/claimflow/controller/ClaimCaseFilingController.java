package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseFiling;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseFilingDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingInformationVO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingListVO;
import com.paic.ehis.claimflow.service.IClaimCaseFilingService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件归档Controller
 * 
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/casefiling")
public class ClaimCaseFilingController extends BaseController {
    @Autowired
    private IClaimCaseFilingService claimCaseFilingService;

    /**
     * 查询案件归档列表
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ClaimCaseFiling claimCaseFiling) {
        startPage(claimCaseFiling);
        List<ClaimCaseFiling> list = claimCaseFilingService.selectClaimCaseFilingList(claimCaseFiling);
        return getDataTable(list);
    }

    /**
     * 查询案件归档主页面
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:list')")
    @PostMapping("/caseFilingList")
    public TableDataInfo caseFilingList(@RequestBody ClaimCaseFilingDTO claimCaseFilingDTO) {
        startPage(claimCaseFilingDTO);
        List<ClaimCaseFilingListVO> list = claimCaseFilingService.selectCaseClaimCaseFilingList(claimCaseFilingDTO);
        return getDataTable(list);
    }

    /**
     * 改变是否销毁状态
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:edit')")
    @Log(title = "案件归档", businessType = BusinessType.UPDATE)
    @PostMapping("/editDestroy")
    public AjaxResult editDestroy(@RequestBody ClaimCaseFilingListVO claimCaseFilingListVO) {
        claimCaseFilingService.updateClaimCaseFilingDestroy(claimCaseFilingListVO);

        return AjaxResult.success(claimCaseFilingListVO);
    }

    /**
     * 导出案件归档列表
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:export')")
    @Log(title = "案件归档", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseFiling claimCaseFiling) throws IOException {
        List<ClaimCaseFiling> list = claimCaseFilingService.selectClaimCaseFilingList(claimCaseFiling);
        ExcelUtil<ClaimCaseFiling> util = new ExcelUtil<ClaimCaseFiling>(ClaimCaseFiling.class);
        util.exportExcel(response, list, "filing");
    }

    /**
     * 获取案件归档详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:query')")
    @GetMapping(value = "/{batchNo}")
    public AjaxResult getInfo(@PathVariable("batchNo") String batchNo) {
        return AjaxResult.success(claimCaseFilingService.selectClaimCaseFilingById(batchNo));
    }

    /**
     * 新增案件归档
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:add')")
    @Log(title = "案件归档", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ClaimCaseFiling claimCaseFiling) {
        return toAjax(claimCaseFilingService.insertClaimCaseFiling(claimCaseFiling));
    }

    /**
     * 修改案件归档
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:edit')")
    @Log(title = "案件归档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseFiling claimCaseFiling) {
        return toAjax(claimCaseFilingService.updateClaimCaseFiling(claimCaseFiling));
    }

    /**
     * 编辑按钮
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:edit')")
    @Log(title = "案件归档", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseFiling")
    public AjaxResult editCaseFiling(@RequestBody ClaimCaseFilingListVO claimCaseFilingListVO) {
        claimCaseFilingService.updateClaimCaseFilingEdit(claimCaseFilingListVO);
        return AjaxResult.success();
    }

    /**
     * 删除案件归档
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:remove')")
    @Log(title = "案件归档", businessType = BusinessType.DELETE)
    @DeleteMapping("/{batchNos}")
    public AjaxResult remove(@PathVariable String[] batchNos) {
        return toAjax(claimCaseFilingService.deleteClaimCaseFilingByIds(batchNos));
    }

    /**
     * 查询案件归档详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:list')")
    @PostMapping("/getInfoCaseFilingList")
    public TableDataInfo getInfoCaseFilingList(@RequestBody ClaimCaseFilingListVO claimCaseFilingListVO) {
        startPage(claimCaseFilingListVO);
        List<ClaimCaseFilingInformationVO> list = claimCaseFilingService.selectCaseClaimCaseFilingInfo(claimCaseFilingListVO);
        return getDataTable(list);
    }

    /**
     * 保存案件归档详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:casefiling:add')")
    @Log(title = "案件归档", businessType = BusinessType.INSERT)
    @PostMapping("/updateCaseFilingInfo")
    public AjaxResult updateCaseFilingInfo(@RequestBody List<ClaimCaseFilingInformationVO> claimCaseFilingInformationVO) {
        return AjaxResult.success(claimCaseFilingService.updateClaimCaseFilingInfo(claimCaseFilingInformationVO));
    }

}

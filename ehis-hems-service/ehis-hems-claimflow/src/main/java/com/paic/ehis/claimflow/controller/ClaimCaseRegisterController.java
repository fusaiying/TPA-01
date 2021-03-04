package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseRegister;
import com.paic.ehis.claimflow.service.IClaimCaseRegisterService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件申请人信息Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/register")
public class ClaimCaseRegisterController extends BaseController
{
    @Autowired
    private IClaimCaseRegisterService claimCaseRegisterService;

    /**
     * 查询案件申请人信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:register:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseRegister claimCaseRegister)
    {
        startPage();
        List<ClaimCaseRegister> list = claimCaseRegisterService.selectClaimCaseRegisterList(claimCaseRegister);
        return getDataTable(list);
    }

    /**
     * 导出案件申请人信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:register:export')")
    @Log(title = "案件申请人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseRegister claimCaseRegister) throws IOException
    {
        List<ClaimCaseRegister> list = claimCaseRegisterService.selectClaimCaseRegisterList(claimCaseRegister);
        ExcelUtil<ClaimCaseRegister> util = new ExcelUtil<ClaimCaseRegister>(ClaimCaseRegister.class);
        util.exportExcel(response, list, "register");
    }

    /**
     * 受理处理页面 申请人信息查询
     * 获取案件申请人信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:register:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseRegisterService.selectClaimCaseRegisterById(rptNo));
    }

    /**
     * 新增案件申请人信息
     */
//    @PreAuthorize("@ss.hasPermi('system:register:add')")
    @Log(title = "案件申请人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseRegister claimCaseRegister)
    {
        return toAjax(claimCaseRegisterService.insertClaimCaseRegister(claimCaseRegister));
    }

    /**
     * 修改案件申请人信息
     */
//    @PreAuthorize("@ss.hasPermi('system:register:edit')")
    @Log(title = "案件申请人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseRegister claimCaseRegister)
    {
        return toAjax(claimCaseRegisterService.updateClaimCaseRegister(claimCaseRegister));
    }

    /**
     * 删除案件申请人信息
     */
//    @PreAuthorize("@ss.hasPermi('system:register:remove')")
    @Log(title = "案件申请人信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rptNos}")
    public AjaxResult remove(@PathVariable String[] rptNos)
    {
        return toAjax(claimCaseRegisterService.deleteClaimCaseRegisterByIds(rptNos));
    }
}

package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.ClaimCasePayee;
import com.paic.ehis.claimflow.domain.dto.HospitalInquiryCodeDTO;
import com.paic.ehis.claimflow.service.IClaimCaseBillService;
import com.paic.ehis.claimflow.service.IClaimCasePayeeService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.domain.BaseProviderInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件领款人信息Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/payee")
public class ClaimCasePayeeController extends BaseController {
    @Autowired
    private IClaimCasePayeeService claimCasePayeeService;

    @Autowired
    private GetProviderInfoService getProviderInfoService;

    @Autowired
    private IClaimCaseBillService iClaimCaseBillService;

    /**
     * 查询案件领款人信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCasePayee claimCasePayee) {
        startPage();
        List<ClaimCasePayee> list = claimCasePayeeService.selectClaimCasePayeeList(claimCasePayee);
        return getDataTable(list);
    }

    /**
     * 导出案件领款人信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:export')")
    @Log(title = "案件领款人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCasePayee claimCasePayee) throws IOException {
        List<ClaimCasePayee> list = claimCasePayeeService.selectClaimCasePayeeList(claimCasePayee);
        ExcelUtil<ClaimCasePayee> util = new ExcelUtil<ClaimCasePayee>(ClaimCasePayee.class);
        util.exportExcel(response, list, "payee");
    }

    /**
     * 获取案件领款人信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:query')")
    @GetMapping(value = "/{payeeId}")
    public AjaxResult getInfo(@PathVariable("payeeId") Long payeeId) {
        return AjaxResult.success(claimCasePayeeService.selectClaimCasePayeeById(payeeId));
    }

    /**
     * 新增案件领款人信息
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:add')")
    @Log(title = "案件领款人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCasePayee claimCasePayee) {
        return toAjax(claimCasePayeeService.insertClaimCasePayee(claimCasePayee));
    }

    /**
     * 修改案件领款人信息
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:edit')")
    @Log(title = "案件领款人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCasePayee claimCasePayee) {
        return toAjax(claimCasePayeeService.updateClaimCasePayee(claimCasePayee));
    }

    /**
     * 无效化案件领款人信息
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:remove')")
    @Log(title = "无效化案件领款人信息", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/rptNo")
    public AjaxResult Invalidation(@RequestBody String rptNo) {
        return toAjax(claimCasePayeeService.deleteClaimCasePayeeByRptNo(rptNo));
    }

    /**
     * 删除案件领款人信息
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:remove')")
    @Log(title = "案件领款人信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{payeeIds}")
    public AjaxResult remove(@PathVariable Long[] payeeIds) {
        return toAjax(claimCasePayeeService.deleteClaimCasePayeeByIds(payeeIds));
    }

    /**
     * 查询案件领款人信息列表,根据报案号只查最近的一条
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:list')")
    @GetMapping("/selectOne")
    public AjaxResult selectOne(ClaimCasePayee claimCasePayee) {
        return AjaxResult.success(claimCasePayeeService.selectClaimCasePayeeselectOne(claimCasePayee));
    }

    /**
     * 获取案件领款人信息详细信息by-rpt_no
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:query')")
    @GetMapping(value = "/s/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo) {
        return AjaxResult.success(claimCasePayeeService.selectClaimCasePayeeByRptNo(rptNo));
    }


    //“医院”列表账户数据
    /**
     * 获取案件领款人信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:payee:query')")
    @PostMapping("/getInfohospitalCode")
    public AjaxResult getInfohospitalCode(HospitalInquiryCodeDTO hospitalInquiryCodeDTO) {
        List<BaseProviderInfo> tableDataInfo =null;
        BaseProviderInfo baseProviderInfo = new BaseProviderInfo();//调用医院接口需要的实体类
        ClaimCaseBill claimCaseBill = iClaimCaseBillService.selectClaimCaseBillListByRptNo(hospitalInquiryCodeDTO.getRptNo());//查询就诊医院编码
        if (claimCaseBill!=null){
            String hospitalCode = claimCaseBill.getHospitalCode();//得到就诊医院编码
            baseProviderInfo.setProviderCode(hospitalCode);
            tableDataInfo = getProviderInfoService.selectOrgInfo(baseProviderInfo);//调用医院插叙接口
        }
        return AjaxResult.success(tableDataInfo);
    }
}

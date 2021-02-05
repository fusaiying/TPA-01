package com.paic.ehis.finance.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.finance.domain.dto.ClaimCasePayDTO;
import com.paic.ehis.finance.domain.vo.ClaimCaseForeignPayInfoVO;
import com.paic.ehis.finance.domain.vo.ClaimCaseForeignPayVO;
import com.paic.ehis.finance.domain.vo.ClaimCasePayInfoVO;
import com.paic.ehis.finance.domain.vo.ClaimCasePayVO;
import com.paic.ehis.finance.service.IClaimCasePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 对公支付
 */
@RestController
@RequestMapping("/pay")
public class ClaimCasePayController extends BaseController {

    @Autowired
    private IClaimCasePayService claimCasePayService;

    /**
     * 对公支付页面初始化
     *
     * @return
     */
    @GetMapping("/initList")
    public TableDataInfo initBatchList(){
        startPage();
        // 查询 该机构下批次案件状态不全部为已支付的
        List<Map<String,Object>> initList = claimCasePayService.selectInitList();
        return getDataTable(initList);
    }

    /**
     * 对公支付 - 查询按钮
     *
     * @param claimCasePayDTO
     * @return
     */
    @PostMapping("/list")
    public TableDataInfo getBatchList(@RequestBody ClaimCasePayDTO claimCasePayDTO){
        startPage(claimCasePayDTO);
        List<Map<String,Object>> batchList = claimCasePayService.selectBatchList(claimCasePayDTO);
        return getDataTable(batchList);
    }

    /**
     * 根据批次号获取案件信息
     *
     * @return
     */
    @GetMapping(value = "/{batchNo}")
    public ClaimCasePayVO getCaseInfo(@PathVariable("batchNo") String batchNo){
        return claimCasePayService.getCaseInfo(batchNo);
    }

    /**
     * 对公支付-清单导出案件信息系
     *
     * @param response
     * @param claimCasePayDTO
     * @throws IOException
     */
    @Log(title = "对公支付-案件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCasePayDTO claimCasePayDTO) throws IOException
    {
        List<ClaimCasePayInfoVO> list = claimCasePayService.getCaseInfo(claimCasePayDTO.getBatchNo()).getCaseInfoList();
        ExcelUtil<ClaimCasePayInfoVO> util = new ExcelUtil<ClaimCasePayInfoVO>(ClaimCasePayInfoVO.class);
        util.exportExcel(response, list, "pay");
    }

    /**
     * 对公支付-回退按钮
     *
     * @param rptNo
     * @return
     */
    @GetMapping("/rollback")
    public AjaxResult rollback(String rptNo){
        return claimCasePayService.rollback(rptNo);
    }

    /**
     * 案件借款
     *
     * @param
     * @return
     */
    @PostMapping("/borrowingCase")
    public AjaxResult borrowingCase(@RequestBody ClaimCaseForeignPayVO claimCasePayVO){
        return AjaxResult.success(claimCasePayService.borrowingCase(claimCasePayVO));
    }

    /**
     * 对公支付-确认支付按钮
     *
     * @param claimCasePayVO
     * @return
     */
    @PostMapping("/confirmPayment")
    public AjaxResult confirmPayment(@RequestBody ClaimCaseForeignPayVO claimCasePayVO){

        return AjaxResult.success(claimCasePayService.confirmPayment(claimCasePayVO));
    }

    /**
     * 对公外币支付-页面初始化
     *
     * @return
     */
    @GetMapping("/initForeignList")
    public TableDataInfo initForeignList(){
        startPage();
        // 查询 该机构下批次案件状态不全部为已支付的
        List<Map<String,Object>> initList = claimCasePayService.selectInitForeignList();
        return getDataTable(initList);
    }

    /**
     * 对公外币支付-查询按钮
     *
     * @return
     */
    @PostMapping("/foreignList")
    public TableDataInfo foreignList(@RequestBody ClaimCasePayDTO claimCasePayDTO){
        startPage(claimCasePayDTO);
        List<Map<String,Object>> batchList = claimCasePayService.selectForeignBatchList(claimCasePayDTO);
        return getDataTable(batchList);
    }

    /**
     * 根据批次号获取案件信息
     *
     * @param batchNo
     * @return
     */
    @GetMapping("/{foreignBatchNo}")
    public ClaimCaseForeignPayVO getForeignCase(@PathVariable("foreignBatchNo") String batchNo){
        return claimCasePayService.getForeignCase(batchNo);
    }

    /**
     * 对公外币支付-清单导出案件信息系
     *
     * @param response
     * @param claimCasePayDTO
     * @throws IOException
     */
    @Log(title = "对公外币支付-案件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/exportForeign")
    public void exportForeign(HttpServletResponse response, ClaimCasePayDTO claimCasePayDTO) throws IOException
    {
        List<ClaimCaseForeignPayInfoVO> list = claimCasePayService.getForeignCase(claimCasePayDTO.getBatchNo()).getCaseInfoList();
        ExcelUtil<ClaimCaseForeignPayInfoVO> util = new ExcelUtil<ClaimCaseForeignPayInfoVO>(ClaimCaseForeignPayInfoVO.class);
        util.exportExcel(response, list, "foreign");
    }



}

package com.paic.ehis.finance.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.finance.domain.FinanceAdvanceSettleDetail;
import com.paic.ehis.finance.domain.dto.FinanceAdvanceSettleDTO;
import com.paic.ehis.finance.domain.vo.FinanceAdvanceSettleVO;
import com.paic.ehis.finance.service.IFinanceAdvanceSettleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 代垫费结算明细Controller
 * 
 * @author sino
 * @date 2021-01-30
 */
@RestController
@RequestMapping("/settleDetail")
public class FinanceAdvanceSettleDetailController extends BaseController
{
    @Autowired
    private IFinanceAdvanceSettleDetailService financeAdvanceSettleDetailService;

    /**
     * 查询代垫费结算明细列表
     */
  //  @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinanceAdvanceSettleDetail financeAdvanceSettleDetail)
    {
        startPage();
        List<FinanceAdvanceSettleDetail> list = financeAdvanceSettleDetailService.selectFinanceAdvanceSettleDetailList(financeAdvanceSettleDetail);
        return getDataTable(list);
    }

    /**
     * 导出代垫费结算明细列表
     */
  //  @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "代垫费结算明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinanceAdvanceSettleDetail financeAdvanceSettleDetail) throws IOException
    {
        List<FinanceAdvanceSettleDetail> list = financeAdvanceSettleDetailService.selectFinanceAdvanceSettleDetailList(financeAdvanceSettleDetail);
        ExcelUtil<FinanceAdvanceSettleDetail> util = new ExcelUtil<FinanceAdvanceSettleDetail>(FinanceAdvanceSettleDetail.class);
        util.exportExcel(response, list, "detail");
    }

    /**
     * 获取代垫费结算明细详细信息
     */
  //  @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return AjaxResult.success(financeAdvanceSettleDetailService.selectFinanceAdvanceSettleDetailById(detailId));
    }

    /**
     * 新增代垫费结算明细
     */
  // @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "代垫费结算明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinanceAdvanceSettleDetail financeAdvanceSettleDetail)
    {
        return toAjax(financeAdvanceSettleDetailService.insertFinanceAdvanceSettleDetail(financeAdvanceSettleDetail));
    }

    /**
     * 修改代垫费结算明细
     */
   // @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "代垫费结算明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinanceAdvanceSettleDetail financeAdvanceSettleDetail)
    {
        return toAjax(financeAdvanceSettleDetailService.updateFinanceAdvanceSettleDetail(financeAdvanceSettleDetail));
    }

    /**
     * 删除代垫费结算明细
     */
   // @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "代垫费结算明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailIds}")
    public AjaxResult remove(@PathVariable Long[] detailIds)
    {
        return toAjax(financeAdvanceSettleDetailService.deleteFinanceAdvanceSettleDetailByIds(detailIds));
    }


    /**
     * 查询代垫费结算明细列表
     */
   // @PreAuthorize("@ss.hasPermi('system:detail:FinanceAdvanceSettleVOList')")
    @PostMapping("/FinanceAdvanceSettleVOList")
    public TableDataInfo FinanceAdvanceSettleVOList(@RequestBody FinanceAdvanceSettleDTO financeAdvanceSettleDTO)
    {
        startPage(financeAdvanceSettleDTO);
        List<FinanceAdvanceSettleVO> list = financeAdvanceSettleDetailService.selectFinanceAdvanceSettleVOList(financeAdvanceSettleDTO);
        return getDataTable(list);
    }

    /** 根据结算任务号查询代垫费案件结算明细*/
   // @PreAuthorize("@ss.hasPermi('system:detail:settleTaskNo')")
    @PostMapping(value = "/getInfoList")
    public TableDataInfo selectFinanceAdvanceSettleVOInfo(@RequestBody FinanceAdvanceSettleDTO dto)
    {
        startPage();
        List<FinanceAdvanceSettleVO> lista = financeAdvanceSettleDetailService.selectFinanceAdvanceSettleVOInfo(dto.getSettleTaskNo());
        return getDataTable(lista);
    }

    /*删除按钮修改状态为无效*/
    @DeleteMapping("/deleteFinanceInfo/{settleTaskNo}")
    public AjaxResult deleteFinanceInfo(@PathVariable String settleTaskNo)
    {
        return toAjax(financeAdvanceSettleDetailService.deletefinanceinfo(settleTaskNo));
    }

    /**任务确认环节确认按钮，将结算状态由待确认改为待核销(待结算)*/
    @PutMapping("/updateSettleStatus1/{settleTaskNo}")
    public AjaxResult updateSettleStatus1(@RequestBody String settleTaskNo)
    {
        return toAjax(financeAdvanceSettleDetailService.updateSettleStatus1(settleTaskNo));
    }

    /**核销按钮将结算状态由待核销改为已结算*/
    @PutMapping("/updateSettleStatus2/{settleTaskNos}")
    public AjaxResult updateSettleStatus2(@RequestBody String[] settleTaskNos)
    {
        return toAjax(financeAdvanceSettleDetailService.updateSettleStatus2(settleTaskNos));
    }

  /*  *//**发起垫付款任务*//*
    @PreAuthorize("@ss.hasPermi('system:task:list')")
    @GetMapping("/InitiateAdvancePaymentTask")
    public TableDataInfo InitiateAdvancePaymentTask(FinanceAdvanceSettleDTO financeAdvanceSettleDTO)
    {
        List<FinanceAdvanceSettleVO> financeAdvanceSettleVOS = financeAdvanceSettleDetailService.InitiateAdvancePaymentTask(financeAdvanceSettleDTO);
        return getDataTable(financeAdvanceSettleVOS);
    }
*/
}

package com.paic.ehis.finance.controller;


import com.paic.ehis.common.core.utils.poi.ExcelUtils;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import com.paic.ehis.finance.domain.FinanceCollectionInfo;
import com.paic.ehis.finance.domain.FinanceTpaSettleTask;
import com.paic.ehis.finance.domain.TpaSettleDetailInfo;
import com.paic.ehis.finance.domain.TpaSettleInfo;
import com.paic.ehis.finance.domain.dto.TpaSettleDTO;
import com.paic.ehis.finance.service.IFinanceTpaSettleTaskService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TPA服务费结算任务Controller
 *
 * @author sino
 * @date 2021-01-30
 */
@RestController
@RequestMapping("/tpaTask")
public class FinanceTpaSettleTaskController extends BaseController
{
    @Autowired
    private IFinanceTpaSettleTaskService financeTpaSettleTaskService;

    /**
     * 查询TPA服务费结算任务列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinanceTpaSettleTask financeTpaSettleTask)
    {
        startPage();
        List<FinanceTpaSettleTask> list = financeTpaSettleTaskService.selectFinanceTpaSettleTaskList(financeTpaSettleTask);
        return getDataTable(list);
    }

    /**
     * 任务发起
     *
     * @param tpaSettleDTO TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:list')")
    @GetMapping("/tpaTaskInitiated")
    public TableDataInfo tpaTaskInitiated(TpaSettleDTO tpaSettleDTO)
    {
        List<TpaSettleInfo> tpaSettleInfos = financeTpaSettleTaskService.tpaTaskInitiated(tpaSettleDTO);
        return getDataTable(tpaSettleInfos);
    }


    /**
     * 多页面 查询TPA服务费结算任务 列表
     *
     * @param tpaSettleDTO TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:list')")
    @GetMapping("/tpaList")
    public TableDataInfo tpaList(TpaSettleDTO tpaSettleDTO)
    {
        List<FinanceTpaSettleTask> list = financeTpaSettleTaskService.selectTpaSettleTaskList(tpaSettleDTO);
        return getDataTable(list);
    }

    /**
     * 查询子页面载入
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:list')")
    @GetMapping("/taskViewDetail")
    public TableDataInfo selectFinanceTpaSettleTaskViewDetail(FinanceTpaSettleTask financeTpaSettleTask)
    {
        List<TpaSettleInfo> tpaSettleInfos = financeTpaSettleTaskService.selectFinanceTpaSettleTaskViewDetail(financeTpaSettleTask);
        return getDataTable(tpaSettleInfos);
    }

    /**
     * 导出TPA服务费结算任务列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:export')")
    @Log(title = "TPA服务费结算任务", businessType = BusinessType.EXPORT)
    @PostMapping("/exportInitiate")
    public void exportInitiate(HttpServletResponse response, FinanceTpaSettleTask financeTpaSettleTask) throws IOException
    {
        List<TpaSettleInfo> tpaSettleInfos = financeTpaSettleTaskService.selectFinanceTpaSettleTaskViewDetail(financeTpaSettleTask);
        ArrayList<String[]> strings = new ArrayList<>();
        try {
            List<List<String>> data1 = new ArrayList<List<String>>();
            if ("01".equals(tpaSettleInfos.get(0).getSettlementType())){
                String[] header1 = { "结算任务号", "出单公司","险种","总人数","服务费/人", "服务费总金额CNY","备注"};
                strings.add(header1);
            }
            if ("02".equals(tpaSettleInfos.get(0).getSettlementType())){
                String[] header1 = { "结算任务号", "出单公司","险种","总保费","保费比例", "服务费总金额CNY","备注"};
                strings.add(header1);
            }
            for (int i = 0; i < tpaSettleInfos.size(); i++) {
                List rowData = new ArrayList();
                rowData.add(tpaSettleInfos.get(i).getSettleTaskNo());
                rowData.add(tpaSettleInfos.get(i).getCompanyName());
                rowData.add(tpaSettleInfos.get(i).getRiskName());
                if ("01".equals(tpaSettleInfos.get(0).getSettlementType())){
                    rowData.add(tpaSettleInfos.get(i).getTotalPeople());
                }
                if ("02".equals(tpaSettleInfos.get(0).getSettlementType())){
                    rowData.add(tpaSettleInfos.get(i).getSumPerm());
                }
                rowData.add(tpaSettleInfos.get(i).getSettlementValue());
                rowData.add(tpaSettleInfos.get(i).getServiceSettleAmount());
                rowData.add(tpaSettleInfos.get(i).getRemark());
                data1.add(rowData);
            }
            List<TpaSettleDetailInfo> detailInfos = tpaSettleInfos.get(0).getDetailInfos();
            List<List<String>> data2 = new ArrayList<List<String>>();

            if ("01".equals(tpaSettleInfos.get(0).getSettlementType())){
                String[] header2 = { "结算任务号", "保单号","分单号","投保人","被保人","险种", "生效日期","服务费","备注" };
                strings.add(header2);
            }
            if ("02".equals(tpaSettleInfos.get(0).getSettlementType())){
                String[] header2 = { "结算任务号", "保单号","分单号","投保人","被保人","险种", "生效日期","保费比例","保费","服务费","备注" };
                strings.add(header2);
            }
            for (int i = 0; i < detailInfos.size(); i++) {
                List rowData = new ArrayList();
                rowData.add(detailInfos.get(i).getSettleTaskNo());
                rowData.add(detailInfos.get(i).getPolicyNo());
                rowData.add(detailInfos.get(i).getPolicyItemNo());
                rowData.add(detailInfos.get(i).getAppName());
                rowData.add(detailInfos.get(i).getName());
                rowData.add(detailInfos.get(i).getRiskName());
                rowData.add(detailInfos.get(i).getValidStartDate());
                if ("02".equals(tpaSettleInfos.get(0).getSettlementType())) {
                    rowData.add(detailInfos.get(i).getPremiumRatio());
                    rowData.add(detailInfos.get(i).getPrem());
                }
                rowData.add(detailInfos.get(i).getServiceAmount());
                rowData.add(detailInfos.get(i).getRemark());
                data2.add(rowData);
            }
            ExcelUtils eeu = new ExcelUtils();
            SXSSFWorkbook workbook = new SXSSFWorkbook();
            String[] sheetTitle = new String[]{"TPA服务费结算信息","TPA服务费结算明细信息"};
            eeu.exportExcel(response,workbook,sheetTitle, strings, data1,data2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 导入TPA服务费结算任务列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:import')")
    @Log(title = "TPA服务费结算任务", businessType = BusinessType.IMPORT)
    @PostMapping("/importInitiate")
    public AjaxResult excelImport(MultipartFile file){

        return toAjax(financeTpaSettleTaskService.importTpaSettleTask(file));
   }

    /**
     * 根据companyCode查询收款核销列表
     *
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:list')")
    @GetMapping("/tpaReceiptColList")
    public TableDataInfo selectTpaTaskReceiptColList(TpaSettleDTO tpaSettleDTO) {
        List<FinanceCollectionInfo> financeCollectionInfos = financeTpaSettleTaskService.selectTpaTaskReceiptColList(tpaSettleDTO);
        return getDataTable(financeCollectionInfos);
    }



    /**
     * 获取TPA服务费结算任务详细信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(financeTpaSettleTaskService.selectFinanceTpaSettleTaskById(taskId));
    }

    /**
     * 新增TPA服务费结算任务
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:add')")
    @Log(title = "TPA服务费结算任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinanceTpaSettleTask financeTpaSettleTask)
    {
        return toAjax(financeTpaSettleTaskService.insertFinanceTpaSettleTask(financeTpaSettleTask));
    }

    /**
     * 修改TPA服务费结算任务         接口冲突,如有冲突请告知——flint
     */
    /*@PreAuthorize("@ss.hasPermi('system:task:edit')")
    @Log(title = "TPA服务费结算任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinanceTpaSettleTask financeTpaSettleTask)
    {
        return toAjax(financeTpaSettleTaskService.updateFinanceTpaSettleTask(financeTpaSettleTask));
    }*/

    /**
     * 获取TPA服务费结算任务详细信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:query')")
    @PutMapping(value = "/{settleTaskNo}")
    public AjaxResult getInfo(@PathVariable("settleTaskNo") String settleTaskNo)
    {
        return AjaxResult.success(financeTpaSettleTaskService.confirmTpaSettleTask(settleTaskNo));
    }

    /**
     * 页面删除TPA服务费结算任务
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:remove')")
    @Log(title = "TPA服务费结算任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult remove(@RequestBody FinanceTpaSettleTask financeTpaSettleTask)
    {
        return toAjax(financeTpaSettleTaskService.modifyTaskSettle(financeTpaSettleTask));
    }

    /**
     * 删除TPA服务费结算任务
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:task:remove')")
    @Log(title = "TPA服务费结算任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(financeTpaSettleTaskService.deleteFinanceTpaSettleTaskByIds(taskIds));
    }
}

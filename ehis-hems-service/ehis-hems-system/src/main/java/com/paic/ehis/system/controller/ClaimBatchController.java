package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimBatch;
import com.paic.ehis.system.domain.dto.BatchDTO;
import com.paic.ehis.system.domain.dto.BatchRecordDTO;
import com.paic.ehis.system.domain.dto.ClaimBatchDTO;
import com.paic.ehis.system.domain.vo.BatchVo;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.page.PageDomain;
import com.paic.ehis.common.core.web.page.TableSupport;
import com.paic.ehis.system.service.IClaimBatchRecordService;
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
import com.paic.ehis.system.service.IClaimBatchService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 理赔批次 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/batch")
public class ClaimBatchController extends BaseController
{
    @Autowired
    private IClaimBatchService claimBatchService;
    @Autowired
    private IClaimBatchRecordService claimBatchRecordService;

    /**
     * 查询理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimBatch claimBatch)
    {
        TableSupport.setSort("desc");
        TableSupport.setOrderByColumn("submit_date");
        startSortPage();
        List<ClaimBatch> list = claimBatchService.selectClaimBatchList(claimBatch);
        return getDataTable(list);
    }

    /**
     * 查询已退回理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/backToList")
    public TableDataInfo backToList(BatchDTO batchDTO)
    {
        TableSupport.setSort("desc");
        TableSupport.setOrderByColumn("submit_date");
        startSortPage();
        List<BatchVo> list = claimBatchService.selectBackToBatchList(batchDTO);
        return getDataTable(list);
    }
    /**
     * 查询已处理理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/dealWithList")
    public TableDataInfo dealWithList(BatchDTO batchDTO)
    {
        TableSupport.setSort("desc");
        TableSupport.setOrderByColumn("submit_date");
        startSortPage();
        List<BatchVo> list = claimBatchService.selectDealWithBatchList(batchDTO);
        return getDataTable(list);
    }

    /**
     * 导出理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimBatch claimBatch) throws IOException
    {
        List<ClaimBatch> list = claimBatchService.selectClaimBatchList(claimBatch);
        ExcelUtil<ClaimBatch> util = new ExcelUtil<ClaimBatch>(ClaimBatch.class);
        util.exportExcel(response, list, "batch");
    }

    /**
     * 导出交单工作池已退回理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportReturnedPool")
    public void exportReturnedPool(HttpServletResponse response,BatchDTO batchDTO) throws IOException
    {
        List<BatchVo> list = claimBatchService.selectBackToBatchList(batchDTO);
        for (BatchVo batchVo : list) {
            batchVo.setHospitalname(StringUtils.nvl(batchVo.getChname1(),"")+"|"+StringUtils.nvl(batchVo.getEnname1(),""));
            batchVo.setCurrency(batchVo.getBatchtotal()+" "+batchVo.getCurrency());
        }
        ExcelUtil<BatchVo> util = new ExcelUtil<BatchVo>(BatchVo.class);
        util.exportExcel(response, list, "交单工作池已退回理赔批次表");
    }
    /**
     * 导出交单工作池已处理理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportProcessedPool")
    public void exportProcessedPool(HttpServletResponse response, BatchDTO batchDTO) throws IOException
    {
        List<BatchVo> list = claimBatchService.selectDealWithBatchList(batchDTO);
            for (BatchVo batchVo : list) {
                batchVo.setHospitalname(StringUtils.nvl(batchVo.getChname1(), "") + "|" + StringUtils.nvl(batchVo.getEnname1(), ""));
                batchVo.setCurrency(batchVo.getBatchtotal() + " " + batchVo.getCurrency());
            }
            ExcelUtil<BatchVo> util = new ExcelUtil<BatchVo>(BatchVo.class);
            util.exportExcel(response, list, "交单工作池已处理理赔批次表");
    }
    /**
     * 导出交单复核已处理理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportPersonalProcessed")
    public void exportPersonalProcessed(HttpServletResponse response) throws IOException
    {
        List<BatchVo> batchVoList = claimBatchService.selectUntreatedPersonalList();
            for (BatchVo batchVo : batchVoList) {
                batchVo.setHospitalname(StringUtils.nvl(batchVo.getChname1(), "") + "|" + StringUtils.nvl(batchVo.getEnname1(), ""));
                batchVo.setCurrency(batchVo.getBatchtotal() + " " + batchVo.getCurrency());
            }
            ExcelUtil<BatchVo> util = new ExcelUtil<BatchVo>(BatchVo.class);
            util.exportExcel(response, batchVoList, "交单复核已处理理赔批次表");
    }
    /**
     * 导出交单复核待处理理赔批次 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportPersonalUntreated")
    public void exportPersonalUntreated(HttpServletResponse response) throws IOException
    {
        List<BatchVo> batchVoList = claimBatchService.selectProcessedPersonalList();
            for (BatchVo batchVo : batchVoList) {
                batchVo.setHospitalname(StringUtils.nvl(batchVo.getChname1(), "") + "|" + StringUtils.nvl(batchVo.getEnname1(), ""));
                batchVo.setCurrency(batchVo.getBatchtotal() + " " + batchVo.getCurrency());
            }
            ExcelUtil<BatchVo> util = new ExcelUtil<BatchVo>(BatchVo.class);
            util.exportExcel(response, batchVoList, "交单复核待处理理赔批次表");
    }
    /**
     * 查询直结复核理赔批次公共池 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/publicList")
    public TableDataInfo reviewPublicList(BatchRecordDTO batchRecordDTO)
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setIsAsc("desc");
        pageDomain.setOrderByColumn("submit_date");
        startPage();
        List<BatchVo> batchVoList = claimBatchService.selectReviewPublicList(batchRecordDTO);
        return getDataTable(batchVoList);
    }
    /**
     * 查询直结复核理赔批次待处理个人池 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/untreatedList")
    public TableDataInfo untreatedPersonalList()
    {
        TableSupport.setSort("desc");
        TableSupport.setOrderByColumn("submit_date");
        startSortPage();
        List<BatchVo> batchVoList = claimBatchService.selectUntreatedPersonalList();
        return getDataTable(batchVoList);
    }
    /**
     * 查询直结复核理赔批次已处理个人池 列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/processedList")
    public TableDataInfo processedPersonalList()
    {
        TableSupport.setSort("desc");
        TableSupport.setOrderByColumn("submit_date");
        startSortPage();
        List<BatchVo> batchVoList = claimBatchService.selectProcessedPersonalList();
        return getDataTable(batchVoList);
    }
    /**
     * 从直结复核公共池获取到未处理个人池 ——修改UpdateBy，赋予当前操作人值
     */
    @PreAuthorize("@ss.hasPermi('system:batch:edit')")
    @Log(title = "获取理赔批次至个人池 ", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/batchnoes")
    public AjaxResult ObtainToPersonalPool(@RequestBody String[] batchnoes)
    {
        return toAjax(claimBatchRecordService.updateClaimBatchByReview(batchnoes));
    }
    /**
     * 未处理个人池的案件无效化  ——将batchstatus="05"
     */
    @PreAuthorize("@ss.hasPermi('system:batch:edit')")
    @Log(title = "无效化处理理赔批次 ", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/{batchno}")
    public AjaxResult neutralize(@RequestBody String batchno)
    {
        int i = claimBatchService.updateClaimBatchstatus(batchno);
        return toAjax(i);
    }

    /**
     * 获取理赔批次 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:batch:query')")
    @GetMapping(value = "/{batchNo}")
    public AjaxResult getInfo(@PathVariable("batchNo") String batchNo)
    {
        return AjaxResult.success(claimBatchService.selectClaimBatchById(batchNo));
    }

    /**
     * 新增理赔批次 
     */
    @PreAuthorize("@ss.hasPermi('system:batch:add')")
    @Log(title = "理赔批次 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimBatch claimBatch)
    {
        return toAjax(claimBatchService.insertClaimBatch(claimBatch));
    }
/*

    @PreAuthorize("@ss.hasPermi('system:batch:edit')")
    @Log(title = "理赔批次 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimBatch claimBatch)
    {
        return toAjax(claimBatchService.updateClaimBatch(claimBatch));
    }
*/

    /**
     *
     * @param claimBatchDTO
     * @return msg
     */
    @PreAuthorize("@ss.hasPermi('system:batch:edit')")
    @Log(title = "理赔批次 ", businessType = BusinessType.UPDATE)
    @PostMapping("/updateClaimBatch")
    public AjaxResult updateClaimBatch(@RequestBody ClaimBatchDTO claimBatchDTO)//
    {
        return toAjax(claimBatchService.updateClaimBatch(claimBatchDTO));
    }

    /**
     * 删除理赔批次 
     */
    @PreAuthorize("@ss.hasPermi('system:batch:remove')")
    @Log(title = "理赔批次 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{batchNos}")
    public AjaxResult remove(@PathVariable String[] batchNos)
    {
        return toAjax(claimBatchService.deleteClaimBatchByIds(batchNos));
    }
}

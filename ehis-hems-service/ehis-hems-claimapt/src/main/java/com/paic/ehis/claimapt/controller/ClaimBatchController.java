package com.paic.ehis.claimapt.controller;


import com.paic.ehis.claimapt.domain.ClaimBatch;
import com.paic.ehis.claimapt.domain.ClaimCaseStanding;
import com.paic.ehis.claimapt.domain.DTO.BatchDTO;
import com.paic.ehis.claimapt.domain.DTO.BatchRecordDTO;
import com.paic.ehis.claimapt.domain.DTO.ClaimBatchDTO;
import com.paic.ehis.claimapt.domain.Vo.BatchVo;
import com.paic.ehis.claimapt.service.IClaimBatchRecordService;
import com.paic.ehis.claimapt.service.IClaimBatchService;
import com.paic.ehis.claimapt.service.IClaimCaseStandingService;
import com.paic.ehis.common.core.utils.StringUtils;
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
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 理赔批次 Controller
 *
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/batch")
public class ClaimBatchController extends BaseController {
    @Autowired
    private IClaimBatchService claimBatchService;
    @Autowired
    private IClaimBatchRecordService claimBatchRecordService;
    @Autowired
    private IClaimCaseStandingService iClaimCaseStandingService;

    /**
     * 查询理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:list')")
    @PostMapping("/list")
    public TableDataInfo list(ClaimBatch claimBatch) {
        if (StringUtils.isNotEmpty(claimBatch.getOrderByColumn())) {
            switch (claimBatch.getOrderByColumn()) {
                case "batchno":
                    claimBatch.setOrderByColumn("batch_no");
                    break;
                case "submitdate":
                    claimBatch.setOrderByColumn("submit_date");
                    break;
                case "updateTime":
                    claimBatch.setOrderByColumn(StringUtils.humpToLine(claimBatch.getOrderByColumn()));
            }
        } else {
            claimBatch.setIsAsc("desc");
            claimBatch.setOrderByColumn("submit_date");
        }
        startPage(claimBatch);
        List<ClaimBatch> list = claimBatchService.selectClaimBatchList(claimBatch);
        return getDataTable(list);
    }

    /**
     * 查询待处理理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:list')")
    @PostMapping("/pendingList")
    public TableDataInfo PendingList(@RequestBody BatchDTO batchDTO)
    {
        if (StringUtils.isNotEmpty(batchDTO.getOrderByColumn())) {
            switch (batchDTO.getOrderByColumn()) {
                case "batchno":
                    batchDTO.setOrderByColumn("batch_no");
                    break;
                case "submitdate":
                    batchDTO.setOrderByColumn("submit_date");
                    break;
                case "updateTime":
                    batchDTO.setOrderByColumn(StringUtils.humpToLine(batchDTO.getOrderByColumn()));
            }
        }else {
            batchDTO.setIsAsc("desc");
            batchDTO.setOrderByColumn("submit_date");
        }
        startPage(batchDTO);
        List<BatchVo> list = claimBatchService.selectPendingBatchList(batchDTO);
        return getDataTable(list);
    }

    /**
     * 查询已退回理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:list')")
    @PostMapping("/backToList")
    public TableDataInfo backToList(@RequestBody BatchDTO batchDTO) {
        if (StringUtils.isNotEmpty(batchDTO.getOrderByColumn())) {
            switch (batchDTO.getOrderByColumn()) {
                case "batchno":
                    batchDTO.setOrderByColumn("batch_no");
                    break;
                case "submitdate":
                    batchDTO.setOrderByColumn("submit_date");
                    break;
                case "updateTime":
                    batchDTO.setOrderByColumn(StringUtils.humpToLine(batchDTO.getOrderByColumn()));
            }
        } else {
            batchDTO.setIsAsc("desc");
            batchDTO.setOrderByColumn("submit_date");
        }
        startPage(batchDTO);
        List<BatchVo> list = claimBatchService.selectBackToBatchList(batchDTO);
        return getDataTable(list);
    }

    /**
     * 查询已处理理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:list')")
    @PostMapping("/dealWithList")
    public TableDataInfo dealWithList(@RequestBody BatchDTO batchDTO) {
        if (StringUtils.isNotEmpty(batchDTO.getOrderByColumn())) {
            switch (batchDTO.getOrderByColumn()) {
                case "batchno":
                    batchDTO.setOrderByColumn("batch_no");
                    break;
                case "submitdate":
                    batchDTO.setOrderByColumn("submit_date");
                    break;
                case "updateTime":
                    batchDTO.setOrderByColumn(StringUtils.humpToLine(batchDTO.getOrderByColumn()));
            }
        } else {
            batchDTO.setIsAsc("desc");
            batchDTO.setOrderByColumn("submit_date");
        }
        startPage(batchDTO);
        List<BatchVo> list = claimBatchService.selectDealWithBatchList(batchDTO);
        return getDataTable(list);
    }

    /**
     * 导出理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimBatch claimBatch) throws IOException {
        List<ClaimBatch> list = claimBatchService.selectClaimBatchList(claimBatch);
        ExcelUtil<ClaimBatch> util = new ExcelUtil<ClaimBatch>(ClaimBatch.class);
        util.exportExcel(response, list, "batch");
    }

    /**
     * 导出交单工作池已退回理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportReturnedPool")
    public void exportReturnedPool(HttpServletResponse response, BatchDTO batchDTO) throws IOException {
//        String hospitalname = URLDecoder.decode(batchDTO.getHospitalname(),"utf-8");
//        batchDTO.setHospitalname(hospitalname);
        System.out.println("我叒导出已退回Excel了！！！");
        List<BatchVo> list = claimBatchService.selectBackToBatchList(batchDTO);
        for (BatchVo batchVo : list) {
            batchVo.setHospitalname(StringUtils.nvl(batchVo.getChname1(), "") + "|" + StringUtils.nvl(batchVo.getEnname1(), ""));
            batchVo.setCurrency(batchVo.getBatchtotal() + " " + batchVo.getCurrency());
        }
        ExcelUtil<BatchVo> util = new ExcelUtil<BatchVo>(BatchVo.class);
        util.exportExcel(response, list, "交单工作池已退回理赔批次表");
    }

    /**
     * 导出交单工作池已处理理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportProcessedPool")
    public void exportProcessedPool(HttpServletResponse response, BatchDTO batchDTO) throws IOException {
//        String hospitalname = URLDecoder.decode(batchDTO.getHospitalname(),"utf-8");
//        batchDTO.setHospitalname(hospitalname);
        System.out.println("我叒导出已处理Excel了！！！");
        List<BatchVo> list = claimBatchService.selectDealWithBatchList(batchDTO);
        for (BatchVo batchVo : list) {
            batchVo.setHospitalname(StringUtils.nvl(batchVo.getChname1(), "") + "|" + StringUtils.nvl(batchVo.getEnname1(), ""));
            batchVo.setCurrency(batchVo.getBatchtotal() + " " + batchVo.getCurrency());
        }
        ExcelUtil<BatchVo> util = new ExcelUtil<BatchVo>(BatchVo.class);
        util.exportExcel(response, list, "交单工作池已处理理赔批次表");
    }

    /**
     * 导出交单复核待处理理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportPersonalProcessed")
    public void exportPersonalProcessed(HttpServletResponse response, BatchDTO batchDTO) throws IOException {
        List<BatchVo> batchVoList = claimBatchService.selectUntreatedPersonalList(batchDTO);
        for (BatchVo batchVo : batchVoList) {
            batchVo.setHospitalname(StringUtils.nvl(batchVo.getChname1(), "") + "|" + StringUtils.nvl(batchVo.getEnname1(), ""));
            batchVo.setCurrency(batchVo.getBatchtotal() + " " + batchVo.getCurrency());
        }
        ExcelUtil<BatchVo> util = new ExcelUtil<BatchVo>(BatchVo.class);
        util.exportExcel(response, batchVoList, "交单复核待处理理赔批次表");
    }

    /**
     * 导出交单复核已处理理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:export')")
    @Log(title = "理赔批次 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportPersonalUntreated")
    public void exportPersonalUntreated(HttpServletResponse response, BatchRecordDTO batchRecordDTO) throws IOException {
        List<BatchVo> batchVoList = claimBatchService.selectProcessedPersonalList(batchRecordDTO);
        for (BatchVo batchVo : batchVoList) {
            batchVo.setHospitalname(StringUtils.nvl(batchVo.getChname1(), "") + "|" + StringUtils.nvl(batchVo.getEnname1(), ""));
            batchVo.setCurrency(batchVo.getBatchtotal() + " " + batchVo.getCurrency());
        }
        ExcelUtil<BatchVo> util = new ExcelUtil<BatchVo>(BatchVo.class);
        util.exportExcel(response, batchVoList, "交单复核已处理理赔批次表");
    }

    /**
     * 查询直结复核理赔批次公共池 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:list')")
    @PostMapping("/publicList")
    public TableDataInfo reviewPublicList(@RequestBody BatchDTO batchDTO) {
        /*if (StringUtils.isNotEmpty(batchDTO.getOrderByColumn())) {
            switch (batchDTO.getOrderByColumn()) {
                case "batchno":
                    batchDTO.setOrderByColumn("batch_no");
                    break;
                case "submitdate":
                    batchDTO.setOrderByColumn("submit_date");
                    break;
                case "updateTime":
                    batchDTO.setOrderByColumn(StringUtils.humpToLine(batchDTO.getOrderByColumn()));
            }
        } else {
            batchDTO.setIsAsc("desc");
            batchDTO.setOrderByColumn("submit_date");
        }
        startPage(batchDTO);*/
        List<BatchVo> batchVoList = claimBatchService.selectReviewPublicList(batchDTO);
        return getDataTable(batchVoList);
    }

    /**
     * 查询直结复核理赔批次待处理个人池 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:list')")
    @GetMapping("/untreatedList")
    public TableDataInfo untreatedPersonalList(BatchDTO batchDTO) {
        if (StringUtils.isNotEmpty(batchDTO.getOrderByColumn())) {
            switch (batchDTO.getOrderByColumn()) {
                case "batchno":
                    batchDTO.setOrderByColumn("batch_no");
                    break;
                case "submitdate":
                    batchDTO.setOrderByColumn("submit_date");
                    break;
                case "updateTime":
                    batchDTO.setOrderByColumn(StringUtils.humpToLine(batchDTO.getOrderByColumn()));
            }
        } else {
            batchDTO.setIsAsc("desc");
            batchDTO.setOrderByColumn("submit_date");
        }
        startPage(batchDTO);
        List<BatchVo> batchVoList = claimBatchService.selectUntreatedPersonalList(batchDTO);
        return getDataTable(batchVoList);
    }

    /**
     * 查询直结复核理赔批次已处理个人池 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:list')")
    @GetMapping("/processedList")
    public TableDataInfo processedPersonalList(BatchRecordDTO batchRecordDTO) {
        if (StringUtils.isNotEmpty(batchRecordDTO.getOrderByColumn())) {
            switch (batchRecordDTO.getOrderByColumn()) {
                case "batchno":
                    batchRecordDTO.setOrderByColumn("batch_no");
                    break;
                case "submitdate":
                    batchRecordDTO.setOrderByColumn("submit_date");
                    break;
                case "updateTime":
                    batchRecordDTO.setOrderByColumn(StringUtils.humpToLine(batchRecordDTO.getOrderByColumn()));
            }
        } else {
            batchRecordDTO.setIsAsc("desc");
            batchRecordDTO.setOrderByColumn("submit_date");
        }
        startPage(batchRecordDTO);
        List<BatchVo> batchVoList = claimBatchService.selectProcessedPersonalList(batchRecordDTO);
        return getDataTable(batchVoList);
    }

    /**
     * 从直结复核公共池获取到未处理个人池 ——修改UpdateBy，赋予当前操作人值
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:edit')")
    @Log(title = "获取理赔批次至个人池 ", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/batchnoes")
    public AjaxResult ObtainToPersonalPool(@RequestBody String[] batchnoes) {
        return toAjax(claimBatchRecordService.updateClaimBatchByReview(batchnoes));
    }

    /**
     * 未处理个人池的案件无效化  ——将batchstatus="05"
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:edit')")
    @Log(title = "无效化处理理赔批次 ", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/{batchno}")
    public AjaxResult neutralize(@RequestBody String batchno) {
        int i = claimBatchService.updateClaimBatchstatus(batchno);
        return toAjax(i);
    }

    /**
     * 受理处理页面
     * 获取理赔批次 详细信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:query')")
    @GetMapping(value = "/{batchNo}")
    public AjaxResult getInfo(@PathVariable("batchNo") String batchNo) {
        return AjaxResult.success(claimBatchService.selectClaimBatchById(batchNo));
    }

    /**
     * 新增理赔批次
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:add')")
    @Log(title = "理赔批次 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimBatch claimBatch) {
        return toAjax(claimBatchService.insertClaimBatch(claimBatch));
    }

    /**
     * 机构交单复核
     *
     * @param claimBatchDTO
     * @return msg
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:edit')")
    @Log(title = "理赔批次 ", businessType = BusinessType.UPDATE)
    @PostMapping("/updateClaimBatch")
    public AjaxResult updateClaimBatch(@RequestBody ClaimBatchDTO claimBatchDTO)//
    {
        return toAjax(claimBatchService.updateClaimBatch(claimBatchDTO));
    }

    /**
     * 删除理赔批次
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:remove')")
    @Log(title = "理赔批次 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{batchNos}")
    public AjaxResult remove(@PathVariable String[] batchNos) {
        return toAjax(claimBatchService.deleteClaimBatchByIds(batchNos));
    }

    //受理调用医院查询接口

    /**
     * 获取理赔批次 详细信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:query')")
    @GetMapping(value = "/getInfoQueryTheHospital")
    public TableDataInfo getInfoQueryTheHospital(@PathVariable("batchNo") String batchNo) {
        ClaimBatch claimBatch = claimBatchService.selectClaimBatchById(batchNo);
        return getDataTable(null);
    }

    //根据证件类型和证件号去查被保人信息表policy_insured
    @GetMapping(value = "/selectClaimCaseStandingByIdidType")
    public AjaxResult selectClaimCaseStandingByIdidType(ClaimCaseStanding claimCaseStanding) {

        List<String> str=new ArrayList<>();
        //先查tpa--policy_insured
        //返回被保人姓名
        List<ClaimCaseStanding> claimCaseStandings = iClaimCaseStandingService.selectClaimCaseStandingByIdidType(claimCaseStanding);
        //在去查核心被保人信息
        //返回被保人姓名
        if (claimCaseStandings.size()!=0){
            for (ClaimCaseStanding claimCaseStandingList:claimCaseStandings){
                str.add(claimCaseStandingList.getName());
            }
        }else if (claimCaseStandings.size()==0){//若只有核心健康险数据
            str=null;
        }
        //若是都没有就返回空值

        return AjaxResult.success(str);
    }

    /**
     * 查询待处理理赔批次 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:batch:export')")
    @PostMapping("/exportPendingList")
    public void ExportPendingList(HttpServletResponse response ,BatchDTO batchDTO)throws IOException
    {
        System.out.println("我叒导出已处理Excel了！！！");
        List<BatchVo> list = claimBatchService.selectPendingBatchList(batchDTO);
        for (BatchVo batchVo : list) {
            batchVo.setHospitalname(StringUtils.nvl(batchVo.getChname1(), "") + "|" + StringUtils.nvl(batchVo.getEnname1(), ""));
            batchVo.setCurrency(batchVo.getBatchtotal() + " " + batchVo.getCurrency());
        }
        ExcelUtil<BatchVo> util = new ExcelUtil<BatchVo>(BatchVo.class);
        util.exportExcel(response, list, "交单工作池处理中理赔批次表");
    }

}

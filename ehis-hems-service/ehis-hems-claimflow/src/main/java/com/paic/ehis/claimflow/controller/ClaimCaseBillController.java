package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.ClaimCase;
import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.ClaimCaseRecord;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseDTO;
import com.paic.ehis.claimflow.domain.vo.BillAccomplishVo;
import com.paic.ehis.claimflow.domain.vo.BillProcessingVo;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseBillInfoVO;
import com.paic.ehis.claimflow.service.IClaimCaseBillService;
import com.paic.ehis.claimflow.service.IClaimCaseRecordService;
import com.paic.ehis.claimflow.service.IClaimCaseService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.core.web.page.TableSupport;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件账单明细Controller
 * 
 * @author sino
 * @date 2021-01-15
 */
@RestController
@RequestMapping("/bill")
public class ClaimCaseBillController extends BaseController
{
    @Autowired
    private IClaimCaseBillService claimCaseBillService;
    @Autowired
    private IClaimCaseRecordService claimCaseRecordService;
    @Autowired
    private IClaimCaseService claimCaseService;

    /**
     * 查询案件账单明细列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseBill claimCaseBill)
    {
        startPage();
        claimCaseBill.setStatus("Y");
        List<ClaimCaseBill> claimCaseBills = claimCaseBillService.selectClaimCaseBillList(claimCaseBill);
        return getDataTable(claimCaseBills);
    }

    /**
     * 导出案件信息-处理中
     */
    @Log(title = "案件录入明细-处理中", businessType = BusinessType.EXPORT)
    @PostMapping("/exportProcess")
    public void exportProcess(HttpServletResponse response, ClaimCaseDTO claimCaseDTO) throws IOException
    {
        claimCaseDTO.setName(new String(claimCaseDTO.getName().getBytes(),"UTF-8"));
        List<BillProcessingVo> list = claimCaseBillService.selectProcessingList(claimCaseDTO);
        ExcelUtil<BillProcessingVo> util = new ExcelUtil<BillProcessingVo>(BillProcessingVo.class);
        util.exportExcel(response, list, "bill");
    }

    /**
     * 导出案件信息-已处理
     */
    @Log(title = "案件录入明细-已处理", businessType = BusinessType.EXPORT)
    @PostMapping("/exportAccomplish")
    public void exportAccomplish(HttpServletResponse response, ClaimCaseDTO claimCaseDTO) throws IOException
    {
        claimCaseDTO.setName(new String(claimCaseDTO.getName().getBytes(),"UTF-8"));
        List<BillAccomplishVo> list = claimCaseBillService.selectAccomplishList(claimCaseDTO);
        ExcelUtil<BillAccomplishVo> util = new ExcelUtil<BillAccomplishVo>(BillAccomplishVo.class);
        util.exportExcel(response, list, "bill");
    }

    /**
     * 获取案件账单明细详细信息
     */
    @GetMapping(value = "/{billId}")
    public AjaxResult getInfo(@PathVariable("billId") Long billId)
    {
        return AjaxResult.success(claimCaseBillService.selectClaimCaseBillById(billId));
    }

    /**
     * 新增案件账单明细
     */
    @Log(title = "案件账单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseBillInfoVO claimCaseBill)
    {
        // 判断是新增还是编辑
        if (!"".equals(claimCaseBill.getBill().getBillId()) && claimCaseBill.getBill().getBillId() != null){
            claimCaseBillService.updateClaimCaseBill(claimCaseBill);
        }
        // 新增
        return toAjax(claimCaseBillService.insertClaimCaseBill(claimCaseBill));
    }

    /**
     * 删除案件账单明细
     */
    @Log(title = "案件账单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{billId}")
    public AjaxResult remove(@PathVariable Long billId)
    {
        ClaimCaseBillInfoVO claimCaseBillInfo = new ClaimCaseBillInfoVO();
        ClaimCaseBill claimCaseBill = new ClaimCaseBill();
        claimCaseBill.setBillId(billId);
        claimCaseBillInfo.setBill(claimCaseBill);
        return toAjax(claimCaseBillService.updateClaimCaseBill(claimCaseBillInfo));
    }

    /**
     * 查询处理中工作池
     *
     * @param claimCaseDTO
     * @return
     */
    @PostMapping("/processingList")
    public TableDataInfo processingList(@RequestBody ClaimCaseDTO claimCaseDTO){
        TableSupport.setSort("desc");
        TableSupport.setOrderByColumn("rpt_no");
        startPage(claimCaseDTO);
        List<BillProcessingVo> processingList = claimCaseBillService.selectProcessingList(claimCaseDTO);
        return getDataTable(processingList);
    }

    /**
     * 查询已处理工作池
     *
     * @param claimCaseDTO
     * @return
     */
    @PostMapping("/accomplishList")
    public TableDataInfo accomplishList(@RequestBody ClaimCaseDTO claimCaseDTO){
        TableSupport.setOrderByColumn("rpt_no");
        TableSupport.setSort("desc");
        startPage(claimCaseDTO);
        List<BillAccomplishVo> accomplishList = claimCaseBillService.selectAccomplishList(claimCaseDTO);
        return getDataTable(accomplishList);
    }

    /**
     * 录入完毕，进入下一节点
     */
    @GetMapping("changeStatus")
    public AjaxResult changeStatus(String rptNo){
        String username = SecurityUtils.getUsername();
        ClaimCaseRecord claimCaseRecord1 = new ClaimCaseRecord();
        claimCaseRecord1.setRptNo(rptNo);
        claimCaseRecord1.setOperator(username);
        claimCaseRecord1.setOperation("06");
        claimCaseRecord1.setUpdateBy(username);
        claimCaseRecord1.setHistoryFlag("Y");
        // 06轨迹关掉
        claimCaseRecordService.updateClaimCaseRecordByRptNoAndOperator(claimCaseRecord1);
        ClaimCaseRecord claimCaseRecord2 = new ClaimCaseRecord();
        claimCaseRecord2.setRptNo(rptNo);
        claimCaseRecord2.setOperation("07");
        claimCaseRecord2.setHistoryFlag("N");
        claimCaseRecord2.setStatus("Y");
        claimCaseRecord2.setCreateBy(username);
        claimCaseRecord2.setCreateTime(DateUtils.getNowDate());
        // 07轨迹开始
        claimCaseRecordService.insertClaimCaseRecord(claimCaseRecord2);
        // 案件信息表更新
        ClaimCase claimCase = new ClaimCase();
        claimCase.setRptNo(rptNo);
        claimCase.setCaseStatus("07");
        return toAjax(claimCaseService.updateClaimCase(claimCase));
    }
}

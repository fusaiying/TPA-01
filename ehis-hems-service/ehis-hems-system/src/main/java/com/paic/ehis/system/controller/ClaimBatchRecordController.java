package com.paic.ehis.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimBatch;
import com.paic.ehis.system.domain.ClaimBatchRecord;
import com.paic.ehis.system.domain.StandingAndBatck;
import com.paic.ehis.system.domain.vo.ClaimCaseStandingVo;
import com.paic.ehis.system.service.IClaimBatchService;
import com.paic.ehis.system.service.IClaimCaseStandingService;
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
import com.paic.ehis.system.service.IClaimBatchRecordService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 理赔批次流程记录 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/record")
public class ClaimBatchRecordController extends BaseController
{
    @Autowired
    private IClaimBatchRecordService claimBatchRecordService;

    @Autowired
    private IClaimCaseStandingService iClaimCaseStandingService;

    @Autowired
    private IClaimBatchService iClaimBatchService;

    /**
     * 查询理赔批次流程记录 列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimBatchRecord claimBatchRecord)
    {
        startPage();
        List<ClaimBatchRecord> list = claimBatchRecordService.selectClaimBatchRecordList(claimBatchRecord);
        return getDataTable(list);
    }

    /**
     * 导出理赔批次流程记录 列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimBatchRecord claimBatchRecord) throws IOException
    {
        List<ClaimBatchRecord> list = claimBatchRecordService.selectClaimBatchRecordList(claimBatchRecord);
        ExcelUtil<ClaimBatchRecord> util = new ExcelUtil<ClaimBatchRecord>(ClaimBatchRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取理赔批次流程记录 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(claimBatchRecordService.selectClaimBatchRecordById(recordId));
    }

    /**
     * 新增理赔批次流程记录 
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimBatchRecord claimBatchRecord)
    {
        return toAjax(claimBatchRecordService.insertClaimBatchRecord(claimBatchRecord));
    }

    /**
     * 修改理赔批次流程记录 
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimBatchRecord claimBatchRecord)
    {
        return toAjax(claimBatchRecordService.updateClaimBatchRecord(claimBatchRecord));
    }

    /**
     * 删除理赔批次流程记录 
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(claimBatchRecordService.deleteClaimBatchRecordByIds(recordIds));
    }

    /**********************************************/
    /**理赔类型-事后-保存
     * 理赔批次和报案台账信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.INSERT)
    @PostMapping("/addBatchAndStanding")
    public AjaxResult addBatchAndStanding(@RequestBody StandingAndBatck standingAndBatck)
    {
        String batchno = standingAndBatck.getClaimBatch().getBatchno();//获取批次号
        //将批次号赋值给standingAndBatck里面list集合的批次号属性
        List<ClaimCaseStandingVo> standingData1 = standingAndBatck.getStandingData();
        for (ClaimCaseStandingVo claimCaseStandingVoBatchno : standingData1){
            claimCaseStandingVoBatchno.setBatchno(batchno);
        }
        //得到所有批次号均已经改变的List<ClaimCaseStandingVo>集合
        standingAndBatck.setStandingData(standingData1);
        //还原传进来的standingAndBatck

        //List集合，里面的参数是对象ClaimCaseStandingVo
        if(standingAndBatck.getClaimBatch().getBatchno() == null || standingAndBatck.getClaimBatch().getBatchno() == ""){
            //第一次点击保存，添加理赔批次信息
            return AjaxResult.success(iClaimBatchService.insertSysClaimBatch(standingAndBatck));
        }else {
            StandingAndBatck standingAndBatck1 = iClaimBatchService.updateSysClaimBatch(standingAndBatck);

            //第二次点击保存，改值理赔批次信息，添加报案台账信息
            List<ClaimCaseStandingVo> standingData = standingAndBatck1.getStandingData();

            ArrayList<ClaimCaseStandingVo> ClaimCaseStandingVos = new ArrayList<>();
            if(standingData!=null || standingData.size()!=0) {//null == list || list.size() ==0
                for (ClaimCaseStandingVo claimCaseStandingVo : standingData) {
                    if (claimCaseStandingVo.getRptno() == null || claimCaseStandingVo.getRptno() == "") {
                        ClaimCaseStandingVos.add(iClaimCaseStandingService.insertSysClaimCaseStanding(claimCaseStandingVo));
                    } else {
                        ClaimCaseStandingVos.add(iClaimCaseStandingService.updateSysClaimCaseStanding(claimCaseStandingVo));
                    }
                    standingAndBatck1.setStandingData(ClaimCaseStandingVos);
                }
            }
            return AjaxResult.success(standingAndBatck1);
        }
    }

    /**理赔类型-事后-提交
     * 理赔批次和报案台账信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.UPDATE)
    @PostMapping("/addBatchAndStandingPresent")
    public AjaxResult addBatchAndStandingPresent(@RequestBody StandingAndBatck standingAndBatck)
    {
        String batchno = standingAndBatck.getClaimBatch().getBatchno();//获取批次号
        //将批次号赋值给standingAndBatck里面list集合的批次号属性
        List<ClaimCaseStandingVo> standingData1 = standingAndBatck.getStandingData();
        for (ClaimCaseStandingVo claimCaseStandingVoBatchno : standingData1){
            claimCaseStandingVoBatchno.setBatchno(batchno);
        }
        //得到所有批次号均已经改变的List<ClaimCaseStandingVo>集合
        standingAndBatck.setStandingData(standingData1);
        //还原传进来的standingAndBatck

        //点击提交，改值理赔批次信息，添加报案台账信息
        StandingAndBatck standingAndBatck1 = iClaimBatchService.updateSysClaimBatchPresent(standingAndBatck);

        List<ClaimCaseStandingVo> standingData = standingAndBatck1.getStandingData();

        ArrayList<ClaimCaseStandingVo> ClaimCaseStandingVos = new ArrayList<>();

        for (ClaimCaseStandingVo claimCaseStandingVo:standingData){
            if (claimCaseStandingVo.getRptno()==null || claimCaseStandingVo.getRptno() == ""){
                ClaimCaseStandingVos.add(iClaimCaseStandingService.insertSysClaimCaseStanding(claimCaseStandingVo));
            }else {
                ClaimCaseStandingVos.add(iClaimCaseStandingService.updateSysClaimCaseStanding(claimCaseStandingVo));
            }
            standingAndBatck.setStandingData(ClaimCaseStandingVos);
        }
        return AjaxResult.success(standingAndBatck);
    }

    /**理赔类型-直结
     * 理赔批次和报案台账信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.INSERT)
    @PostMapping("/addBatch")
    public AjaxResult addBatch(@RequestBody ClaimBatch claimBatch)
    {
        if(claimBatch.getBatchno() == null || claimBatch.getBatchno() == ""){
            return AjaxResult.success(iClaimBatchService.insertSysClaimBatchTwo(claimBatch));
        }else {
            return AjaxResult.success(iClaimBatchService.updateClaimBatchTwo(claimBatch));
        }
    }

    /**
     *
     * @param batchno
     * @return
     */
    //通过batchno批次号查询sys_claim_batch_record表得到RecordByBatchnoDto输出流
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @PostMapping("/selectRecordByBatchno")
    public AjaxResult selectRecordByBatchno(String batchno)
    {
        if(batchno!=null || batchno!=""){
            List<ClaimBatchRecord> batchnoList = claimBatchRecordService.selectSysClaimBatchRecordListByBatchno(batchno);
            return AjaxResult.success(batchnoList);
        }else {
            return AjaxResult.success(123);
        }
    }
}

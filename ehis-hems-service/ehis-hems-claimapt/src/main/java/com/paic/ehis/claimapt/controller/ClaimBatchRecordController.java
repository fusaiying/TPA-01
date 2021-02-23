package com.paic.ehis.claimapt.controller;


import com.paic.ehis.claimapt.domain.ClaimBatch;
import com.paic.ehis.claimapt.domain.ClaimBatchRecord;
import com.paic.ehis.claimapt.domain.ClaimCaseStanding;
import com.paic.ehis.claimapt.domain.StandingAndBatck;
import com.paic.ehis.claimapt.domain.Vo.ClaimCaseStandingVo;
import com.paic.ehis.claimapt.service.IClaimBatchRecordService;
import com.paic.ehis.claimapt.service.IClaimBatchService;
import com.paic.ehis.claimapt.service.IClaimCaseRecordService;
import com.paic.ehis.claimapt.service.IClaimCaseStandingService;
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
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private IClaimCaseRecordService iClaimCaseRecordService;

    /**
     * 查询理赔批次流程记录 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:list')")
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
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:export')")
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
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(claimBatchRecordService.selectClaimBatchRecordById(recordId));
    }

    /**
     * 新增理赔批次流程记录 
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:add')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimBatchRecord claimBatchRecord)
    {
        return toAjax(claimBatchRecordService.insertClaimBatchRecord(claimBatchRecord));
    }

    /**
     * 修改理赔批次流程记录 
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:edit')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimBatchRecord claimBatchRecord)
    {
        return toAjax(claimBatchRecordService.updateClaimBatchRecord(claimBatchRecord));
    }

    /**
     * 删除理赔批次流程记录 
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:remove')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(claimBatchRecordService.deleteClaimBatchRecordByIds(recordIds));
    }

    /**********************************************/
    /**机构交单-事后-保存
     * 理赔批次和报案台账信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:add')")
    @Log(title = "理赔批次流程记录 ", businessType = BusinessType.INSERT)
    @PostMapping("/addBatchAndStanding")
    public AjaxResult addBatchAndStanding(@RequestBody StandingAndBatck standingAndBatck)
    {
        if (standingAndBatck.getClaimBatch().getCasenum() < standingAndBatck.getStandingData().size()){
            iClaimCaseStandingService.updateClaimCaseStandingByBatchno(standingAndBatck.getClaimBatch().getBatchno());
            List<ClaimCaseStandingVo> claimCaseStandingVos1 = iClaimCaseStandingService.selectClaimCaseStandingByBatchno(standingAndBatck.getClaimBatch());
            standingAndBatck.setStandingData(claimCaseStandingVos1);
            return AjaxResult.success(standingAndBatck);
        } else {

            String batchno = standingAndBatck.getClaimBatch().getBatchno();//获取批次号
            //将批次号赋值给standingAndBatck里面list集合的批次号属性
            List<ClaimCaseStandingVo> standingData1 = standingAndBatck.getStandingData();
            for (ClaimCaseStandingVo claimCaseStandingVoBatchno : standingData1) {
                claimCaseStandingVoBatchno.setBatchno(batchno);
            }
            //得到所有批次号均已经改变的List<ClaimCaseStandingVo>集合
            standingAndBatck.setStandingData(standingData1);
            //还原传进来的standingAndBatck

            //List集合，里面的参数是对象ClaimCaseStandingVo
            if (standingAndBatck.getClaimBatch().getBatchno() == null || standingAndBatck.getClaimBatch().getBatchno() == "") {
                //第一次点击保存，添加理赔批次信息
                return AjaxResult.success(iClaimBatchService.insertSysClaimBatch(standingAndBatck));
            } else {
                StandingAndBatck standingAndBatck1 = iClaimBatchService.updateSysClaimBatch(standingAndBatck);

                //第二次点击保存，改值理赔批次信息，添加报案台账信息
                List<ClaimCaseStandingVo> standingData = standingAndBatck1.getStandingData();

//            ArrayList<ClaimCaseStandingVo> claimCaseStandingVos = new ArrayList<>();
                if (standingData != null || standingData.size() != 0) {//null == list || list.size() ==0
                    for (ClaimCaseStandingVo claimCaseStandingVo : standingData) {
//                    claimCaseStandingVo.setBatchno(batchno);
                        if (claimCaseStandingVo.getStandingId() == null || claimCaseStandingVo.getStandingId() == "") {
//                        claimCaseStandingVos.add(iClaimCaseStandingService.insertSysClaimCaseStanding(claimCaseStandingVo));
                            iClaimCaseStandingService.insertSysClaimCaseStanding(claimCaseStandingVo);
                        } else {
//                        claimCaseStandingVos.add(iClaimCaseStandingService.updateSysClaimCaseStanding(claimCaseStandingVo));
                            iClaimCaseStandingService.updateSysClaimCaseStanding(claimCaseStandingVo);
                        }
//                    standingAndBatck1.setStandingData(claimCaseStandingVos);
                    }
                    List<ClaimCaseStandingVo> claimCaseStandingVos1 = iClaimCaseStandingService.selectClaimCaseStandingByBatchno(standingAndBatck.getClaimBatch());
                    standingAndBatck1.setStandingData(claimCaseStandingVos1);
                }

                //判断传过来得台账信息数目与案件数目是否一致，》=则无操作，《=则先查询排序，然后将最后得台账信息置为无效
                return AjaxResult.success(standingAndBatck1);
            }
        }
    }

    /**机构交单-事后-提交
     * 理赔批次和报案台账信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:add')")
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

//        ArrayList<ClaimCaseStandingVo> claimCaseStandingVos = new ArrayList<>();

        for (ClaimCaseStandingVo claimCaseStandingVo:standingData){
            if (claimCaseStandingVo.getStandingId() == null || claimCaseStandingVo.getStandingId() == "") {
//                        claimCaseStandingVos.add(iClaimCaseStandingService.insertSysClaimCaseStanding(claimCaseStandingVo));
                iClaimCaseStandingService.insertSysClaimCaseStanding(claimCaseStandingVo);
            } else {
//                        claimCaseStandingVos.add(iClaimCaseStandingService.updateSysClaimCaseStanding(claimCaseStandingVo));
                iClaimCaseStandingService.updateSysClaimCaseStanding(claimCaseStandingVo);
            }
            //standingAndBatck.setStandingData(claimCaseStandingVos);

            //点击提交时，应该生成相对应得案件批次信息表-案件信息表和案件信息轨迹表一一对应
            iClaimCaseRecordService.insertClaimCaseRecordAndBatchRecord(claimCaseStandingVo);
        }

//        standingData.size();//传过来得台账
//        standingAndBatck.getClaimBatch().getCasenum();//案件数量

        //得改参数累接受得
        List<ClaimCaseStandingVo> claimCaseStandingVos1 = iClaimCaseStandingService.selectClaimCaseStandingByBatchno(standingAndBatck.getClaimBatch());
        standingAndBatck1.setStandingData(claimCaseStandingVos1);

        return AjaxResult.success(standingAndBatck);
    }

    /**机构交单-直结-新增
     * 理赔批次和报案台账信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:add')")
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
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:record:list')")
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

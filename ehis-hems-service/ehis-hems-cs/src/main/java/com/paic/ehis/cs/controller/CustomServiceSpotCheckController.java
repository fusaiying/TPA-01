package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.AttachmentInfo;
import com.paic.ehis.cs.domain.QualityInspectionHandle;
import com.paic.ehis.cs.domain.QualityInspectionItem;
import com.paic.ehis.cs.domain.dto.*;
import com.paic.ehis.cs.domain.vo.*;
import com.paic.ehis.cs.service.*;
import com.paic.ehis.cs.utils.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工单质检
 * by Louis
 */
@RestController
@RequestMapping("/spotCheck")
public class CustomServiceSpotCheckController extends BaseController {

    @Autowired
    private IQualityInspectionAcceptService qualityInspectionAcceptService;

    @Autowired
    private IQualityInspectionHandleService qualityInspectionHandleService;
    @Autowired
    private IQualityInspectionItemService qualityInspectionItemService;
    @Autowired
    private IAttachmentInfoService attachmentInfoService;
    @Autowired
    private IComplaintAcceptVoService iComplaintAcceptVoService;

    /**
     * 发送质检工作池：数据来源
     * @param workOrderQueryDTO
     * @return
     */
    @GetMapping("/internal/inspectionSendData")
    public TableDataInfo selectSendPoolDataBy(WorkOrderQueryDTO workOrderQueryDTO) {
        //1.分页处理
        startPage();
       //2.工单状态处理； 04-已完成,已取消，不合格，合格的工单
        //3.业务类型为： 01-信息需求和03-投诉的才可以质检
        List<String> businessTypeList = new ArrayList<>();
        businessTypeList.add(CodeEnum.BUSINESS_TYPE_01.getCode());
        businessTypeList.add(CodeEnum.BUSINESS_TYPE_03.getCode());
        workOrderQueryDTO.setBusinessTypeList(businessTypeList);
        logger.debug("可发起质检工作池入参: {}", workOrderQueryDTO);
        List<AcceptVo> list = qualityInspectionAcceptService.selectSendPoolDataTwo(workOrderQueryDTO);
        logger.debug("可发起质检工作池结果:{}", list);
        return getDataTable(list);
    }


    /**
     * 待质检公共池：数据来源
     * @param workOrderQueryDTO
     * @return
     */
    @GetMapping("/internal/inspectionPublicData")
    public TableDataInfo selectPublicPoolDataBy(WorkOrderQueryDTO workOrderQueryDTO)
    {
        //1.分页处理
        startPage();
        //2.工单状态处理； 01-待质检
        workOrderQueryDTO.setAcceptStatus(CodeEnum.INSPECTION_STATE_01.getCode());
        logger.debug("待质检公共池查询入参：{}",workOrderQueryDTO);
        List<AcceptVo> list = qualityInspectionAcceptService.selectAcceptPoolData(workOrderQueryDTO);
        logger.debug("待质检公共池查询结果：{}",list);
        return getDataTable(list);
    }

    /**
     * 质检中个人池：数据来源
     * @param workOrderQueryDTO
     * @return
     */
    @GetMapping("/internal/inspectionPersonData")
    public TableDataInfo selectPersonPoolDataBy(WorkOrderQueryDTO workOrderQueryDTO)
    {
        //1.分页处理
        startPage();
        //2.工单状态设置； 02-质检中
        workOrderQueryDTO.setAcceptStatus(CodeEnum.INSPECTION_STATE_02.getCode());
        //3.当前操作人设置；
        workOrderQueryDTO.setUpdateBy(String.valueOf(SecurityUtils.getUsername()));
        logger.debug("待质检个人池查询入参：{}",workOrderQueryDTO);
        List<AcceptVo> list = qualityInspectionAcceptService.selectAcceptPoolData(workOrderQueryDTO);
        logger.debug("待质检个人池查询结果：{}",list);
        return getDataTable(list);
    }

    /**
     * 发起质检操作
     * @param sendIds
     * @return
     */
    @Transactional
    @Log(title = "发起质检", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/internal/sendIds")
    public AjaxResult inspectionSendByIds(@RequestBody String[] sendIds)
    {
        Map<String,String> param=new HashMap<>();
        //操作后主流程状态
        param.put("status",CodeEnum.INSPECTION_STATE_01.getCode());
        //操作前主流程状态
        param.put("linkCode",CodeEnum.ORDER_STATE_04.getCode());//已完成的可质检
        //操作按钮代码
        param.put("operateCode",CodeEnum.ACTION_TYPE_13.getCode());
        return toAjax(qualityInspectionAcceptService.insertAcceptVoBatch(sendIds,param));
    }

    /**
     * 获取待质检操作
     * @param getIds
     * @return
     */
    @Transactional
    @Log(title = "获取待质检", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/internal/getIds")
    public AjaxResult inspectionGetByIds(@RequestBody String[] getIds)
    {
        Map<String,String> param=new HashMap<>();
        //操作后主流程状态
        param.put("status",CodeEnum.INSPECTION_STATE_02.getCode());
        //操作前主流程状态
        param.put("linkCode",CodeEnum.LINK_CODE_08.getCode());
        //操作按钮代码
        param.put("operateCode",CodeEnum.ACTION_TYPE_02.getCode());
        return toAjax(qualityInspectionAcceptService.inspectionHandleStatusByIds(getIds,param));
    }

    /**
     * 根据条件获取受理对象
     * @param workOrderQueryDTO
     * @return
     */
    @GetMapping("/internal/getAcceptInfo")
    public AjaxResult getAcceptInfo(WorkOrderQueryDTO workOrderQueryDTO)
    {
        return AjaxResult.success(qualityInspectionAcceptService.getOneAcceptInfo(workOrderQueryDTO));
    }

    /**
     * 根据条件获取附件列表信息
     * @param workOrderQueryDTO
     * @return
     */
    @GetMapping("/internal/getAttachmentList")
    public AjaxResult getAttachmentList(WorkOrderQueryDTO workOrderQueryDTO){
        AttachmentInfo attachmentInfo=new AttachmentInfo();
        attachmentInfo.setBusinessNo(workOrderQueryDTO.getWorkOrderNo());
        attachmentInfo.setBusinessType(workOrderQueryDTO.getBusinessType());
        List<AttachmentInfo> attachmentInfoList=attachmentInfoService.selectAttachmentInfoList(attachmentInfo);
        if(attachmentInfoList==null){
            attachmentInfoList=new ArrayList<>();
        }
        return AjaxResult.success(attachmentInfoList);
    }


    /**
     * 新增质检处理
     * @param qualityInspectionHandle
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:handle:add')")
    @Log(title = "质检处理 ", businessType = BusinessType.INSERT)
    @PostMapping("/insertHandle")
    public AjaxResult insertHandle(@RequestBody QualityInspectionHandle qualityInspectionHandle)
    {
        Map<String,String> param=new HashMap<>();
        //操作后主流程状态
        param.put("status",CodeEnum.CONFIRM_STATE_01.getCode());
        return toAjax(qualityInspectionHandleService.insertHandle(qualityInspectionHandle));
    }

    //************************************************************************************

    /**
     * 新增质检项目
     * @param qualityVo
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:handle:add')")
    @Log(title = "质检处理 ", businessType = BusinessType.INSERT)
    @PostMapping("/insertItem")
    public AjaxResult insertItem(@RequestBody QualityVo qualityVo)
    {
        return toAjax(qualityInspectionItemService.insertItem(qualityVo));
    }
    @Log(title = "质检处理 ", businessType = BusinessType.INSERT)
    @PostMapping("/insertItem2")
    public AjaxResult insertItem2(@RequestBody QualityVo qualityVo)
    {
        return toAjax(qualityInspectionItemService.insertItem2(qualityVo));
    }
    //************************************************************************************
    /**
     * 质检差错查询反显数据
     */
//    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityInspectionItem qualityInspectionItem)
    {
        startPage();
        List<QualityInspectionItem> list = qualityInspectionItemService.selectItemInspectionById(qualityInspectionItem);
        return getDataTable(list);
    }

    @GetMapping("/selectHandleStatus")
    public AjaxResult selectHandleStatus(QualityVo qualityVo){
        return AjaxResult.success(qualityInspectionHandleService.selectHandleStatus(qualityVo));
    }

    //************************************************************************************
    //质检差错提交接口
    /**
     * 质检差错   修改是否申述等字段
     */
    @PutMapping("/updateQualityHandle")
    public AjaxResult updateQualityHandle(@RequestBody QualityInspectionHandle qualityInspectionHandle)
    {
        return toAjax(qualityInspectionHandleService.updateQualityHandle(qualityInspectionHandle));
    }
    /**
     * 质检差错修改item表字段是否时效内响应客户等
     */
    @PutMapping("/updateQualityItem")
    public AjaxResult updateQualityItem( @RequestBody String[] sendIds)
    {
        Map<String,String> param=new HashMap<>();

        //操作前主流程状态
        param.put("linkCode",CodeEnum.LINK_CODE_10.getCode());
        //param.put("inspectionId",);
        //操作按钮代码
        param.put("operateCode",CodeEnum.ACTION_TYPE_05.getCode());
        return toAjax(qualityInspectionItemService.updateQualityItem(sendIds,param));
    }

//************************************************************************************
    /**
     *质检差错确认工作池查询
     */
    @GetMapping("/internal/selectHandle")
    public TableDataInfo selectHandle(WorkOrderQueryDTO workOrderQueryDTO)
    {
        List<AcceptVo> list = qualityInspectionHandleService.selectHandle(workOrderQueryDTO);
        return getDataTable(list);
    }
    //************************************************************************************
        /**
         *工单查询
         */
    @GetMapping("/internal/selectWorkOrder")
    public TableDataInfo selectWorkOrder(WorkOrderQueryDTO workOrderQueryDTO) {
        //1.分页处理
        startPage();
        logger.debug("工单查询工作池入参: {}", workOrderQueryDTO);
        List<AcceptVo> list = qualityInspectionAcceptService.selectSendPoolData(workOrderQueryDTO);
        logger.debug("工单查询工作池结果:{}", list);
        return getDataTable(list);

    }


    //************************************************************************************
    /**
     *工单获取
     */
    @GetMapping("/internal/updateGetWorkOrder")
    public AjaxResult updateGetWorkOrder(@RequestParam("workOrderNo") String workOrderNo) {
        return AjaxResult.success(qualityInspectionAcceptService.updateSendByVoById(workOrderNo));
    }

    //************************************************************************************
    /**
     *工单修改判断弹框
     */
    @GetMapping("/internal/updateGetWorkOrder1")
    public AjaxResult updateGetWorkOrder1(@RequestParam("workOrderNo") String workOrderNo) {
        return AjaxResult.success(qualityInspectionAcceptService.updateSendByVoByIdById1(workOrderNo));
    }

    //************************************************
        /*
        工单查询清单导出
         */
    @Log(title = "工单信息清单导出", businessType = BusinessType.EXPORT)
    @PostMapping("/internal/selectWorkOrder/export")
    public void export(HttpServletResponse response, WorkOrderQueryDTO workOrderQueryDTO) throws IOException
    {
        List<AcceptVo> list = qualityInspectionAcceptService.selectSendPoolData(workOrderQueryDTO);
        ExcelUtil<AcceptVo> util = new ExcelUtil<AcceptVo>(AcceptVo.class);
        util.exportExcel(response, list, "WorkOrder");
    }

    //************************************************
        /*
        质检查询清单导出
         */
    @Log(title = "质检查询清单导出", businessType = BusinessType.EXPORT)
    @PostMapping("/internal/selectWorkOrder/exportOne")
    public void exportOne(HttpServletResponse response, QualityDTO qualityDTO) throws IOException
    {
        List<QualityAcceptVo> list = qualityInspectionAcceptService.selectQualityVo(qualityDTO);
        ExcelUtil<QualityAcceptVo> util = new ExcelUtil<QualityAcceptVo>(QualityAcceptVo.class);
        util.exportExcel(response, list, "WorkOrderOne");
    }

    //************************************************
        /*
        质检差错清单导出
         */
    @Log(title = "质检差错清单导出", businessType = BusinessType.EXPORT)
    @PostMapping("/internal/selectWorkOrder/exportTwo")
    public void exportTwo(HttpServletResponse response, QualityInspectionDTO qualityInspectionDTO) throws IOException
    {
        String bus=qualityInspectionDTO.getBusinessType();
        if(null!=bus) {
            List<QualityInspectionHandleVo> list = qualityInspectionAcceptService.selectQualityFlagVO(qualityInspectionDTO);
        ExcelUtil<QualityInspectionHandleVo> util = new ExcelUtil<QualityInspectionHandleVo>(QualityInspectionHandleVo.class);
        util.exportExcel(response, list, "WorkOrderTwo");
    }
    }


    //************************************************

    /**
     * 质检查询工作池
     * @param qualityDTO
     * @return
     */
    @GetMapping("/internal/selectQualityVo")
    public TableDataInfo selectQualityVo(QualityDTO qualityDTO)
    {
        startPage();
        List<QualityAcceptVo> list = qualityInspectionAcceptService.selectQualityVo(qualityDTO);
        return getDataTable(list);
    }

    /**
     * 质检差错确认池查询
     * @param qualityInspectionDTO
     * @return
     */
    @GetMapping("/internal/selectQualityFlagVO")
    public TableDataInfo selectQualityFlagVo(QualityInspectionDTO qualityInspectionDTO){
        startPage();
        qualityInspectionDTO.setUpdatedBy(SecurityUtils.getUsername());
        List<QualityInspectionHandleVo> list = qualityInspectionAcceptService.selectQualityFlagVO(qualityInspectionDTO);
        return getDataTable(list);
    }
//    //查询质检处理信息，申诉信息
//    @GetMapping("/internal/getHandleInfoList")
//    public TableDataInfo getHandleInfoList(HandleProcessInfoDTO handleProcessInfoDTO) {
//        List<HandleProcessInfoVO> list = qualityInspectionHandleService.getHandleInfoList(handleProcessInfoDTO);
//        return getDataTable(list);
//    }
//    //保存质检处理详情页全部信息
//    @PostMapping("/internal/insertHandleInfo")
//    public AjaxResult insertHandleInfo(HandleDTO handleDTO) {
//        return AjaxResult.success(qualityInspectionHandleService.insertHandleInfo(handleDTO));
//    }

    /**
     * 根据条件获取质检项目信息及处理信息
     * @param qualityInspectionDTO
     * @return
     */
    @PostMapping("/internal/getQualityHandleInfo")
    public AjaxResult getQualityHandleInfo(@RequestBody QualityInspectionDTO qualityInspectionDTO){
        qualityInspectionDTO.setCodeType("cs_inspection_item_demand");
        if(CodeEnum.BUSINESS_TYPE_03.getCode().equals(qualityInspectionDTO.getBusinessType())){
            qualityInspectionDTO.setCodeType("cs_inspection_item_complaint");
        }
        return AjaxResult.success(qualityInspectionHandleService.getQualityHandleInfo(qualityInspectionDTO));
    }

    /**
     * 质检数据保存
     * @param qualityInspectionDTO
     * @return
     */
    @Transactional
    @PostMapping("/internal/insetQualityHandleInfo")
    public AjaxResult insertHandleInfo(@RequestBody QualityInspectionDTO qualityInspectionDTO){
        qualityInspectionDTO.setCreatedBy(SecurityUtils.getUsername());
        qualityInspectionDTO.setCreatedTime(DateUtils.getNowDate());
        qualityInspectionDTO.setUpdatedBy(SecurityUtils.getUsername());
        qualityInspectionDTO.setUpdatedTime(DateUtils.getNowDate());
        return toAjax(qualityInspectionHandleService.insertHandleInfo(qualityInspectionDTO));
    }

    /**
     * 质检差错修改数据保存
     * @param qualityInspectionDTO
     * @return
     */
    @Transactional
    @PostMapping("/internal/insetQualityHandleInfo1")
    public AjaxResult insertHandleInfo1(@RequestBody QualityInspectionDTO qualityInspectionDTO){
        qualityInspectionDTO.setCreatedBy(SecurityUtils.getUsername());
        qualityInspectionDTO.setCreatedTime(DateUtils.getNowDate());
        qualityInspectionDTO.setUpdatedBy(SecurityUtils.getUsername());
        qualityInspectionDTO.setUpdatedTime(DateUtils.getNowDate());
        return toAjax(qualityInspectionHandleService.insertHandleInfo1(qualityInspectionDTO));
    }


    //信息需求抽检批处理
    @GetMapping("/internal/batchAcceptVo/invalidDate/{invalidDate}")
    public AjaxResult batchAcceptVo(@PathVariable("invalidDate") String invalidDate){
        Map<String,String> param=new HashMap<>();
        //操作后主流程状态
        param.put("status",CodeEnum.INSPECTION_STATE_01.getCode());
        //操作前主流程状态
        param.put("linkCode",CodeEnum.ORDER_STATE_04.getCode());//已完成的可质检
        //操作按钮代码
        param.put("operateCode",CodeEnum.ACTION_TYPE_13.getCode());
        try{
            qualityInspectionAcceptService.batchAcceptVo(invalidDate,param);
        }catch(RuntimeException e){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("执行成功");
    }

    //信息需求一个月抽检批处理
    @GetMapping("/internal/batchAcceptVo/invalidDateMonth/{invalidDate}")
    public AjaxResult batchAcceptVomonth(@PathVariable("invalidDate") String invalidDate){
        Map<String,String> param=new HashMap<>();
        //操作后主流程状态
        param.put("status",CodeEnum.INSPECTION_STATE_01.getCode());
        //操作前主流程状态
        param.put("linkCode",CodeEnum.ORDER_STATE_04.getCode());//已完成的可质检
        //操作按钮代码
        param.put("operateCode",CodeEnum.ACTION_TYPE_13.getCode());
        try{
            qualityInspectionAcceptService.batchAcceptVomonth(invalidDate,param);
        }catch(RuntimeException e){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("执行成功");
    }

    //预约12点批处理
    @GetMapping("/internal/invalidAcceptDetailInfo/{invalidDate}")
    public AjaxResult invalidAcceptDetailInfo(@PathVariable("invalidDate") String invalidDate){
        try{
            qualityInspectionAcceptService.invalidAcceptDetailInfo(invalidDate);
        }catch(RuntimeException e){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("执行成功");
    }

    //服务处理回显接口
    @GetMapping("/getAttachmentList/selectServiceProcess/{workOrderNo}")
    public AjaxResult  selectServiceProcess(String workOrderNo){
        return AjaxResult.success(iComplaintAcceptVoService.selectServiceProcess(workOrderNo));
    }

    //投诉一周抽检批处理
    @GetMapping("/internal/batchAcceptVo/selectInvalidQiaMondayFour/{invalidDate}")
    public AjaxResult selectInvalidQiaMondayFour(@PathVariable("invalidDate") String invalidDate){
        Map<String,String> param=new HashMap<>();
        //操作后主流程状态
        param.put("status",CodeEnum.INSPECTION_STATE_01.getCode());
        //操作前主流程状态
        param.put("linkCode",CodeEnum.ORDER_STATE_04.getCode());//已完成的可质检
        //操作按钮代码
        param.put("operateCode",CodeEnum.ACTION_TYPE_13.getCode());
        try{
            qualityInspectionAcceptService.selectInvalidQiaMondayFour(invalidDate,param);
        }catch(RuntimeException e){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("执行成功");
    }
}

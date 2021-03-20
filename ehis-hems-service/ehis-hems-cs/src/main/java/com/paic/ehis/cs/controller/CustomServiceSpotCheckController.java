package com.paic.ehis.cs.controller;

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
    private IDemandAcceptVoService iDemandAcceptVoService;

    /**
     * 发送质检工作池：数据来源
     * @param workOrderQueryDTO
     * @return
     */
    @GetMapping("/internal/inspectionSendData")
    public TableDataInfo selectSendPoolDataBy(WorkOrderQueryDTO workOrderQueryDTO)
    {
        //1.分页处理
        startPage();
        //2.工单状态处理； 04-已完成的工单且没有被质检过
        workOrderQueryDTO.setAcceptStatus(CodeEnum.ORDER_STATE_04.getCode());
        //3.业务类型为： 01-信息需求和03-投诉的才可以质检
        List<String> businessTypeList=new ArrayList<>();
        businessTypeList.add(CodeEnum.BUSINESS_TYPE_01.getCode());
        businessTypeList.add(CodeEnum.BUSINESS_TYPE_03.getCode());
        workOrderQueryDTO.setBusinessTypeList(businessTypeList);
        logger.debug("可发起质检工作池入参: {}",workOrderQueryDTO);
        List<AcceptVo> list = qualityInspectionAcceptService.selectSendPoolData(workOrderQueryDTO);
//        for (int i = 0; i < list.size(); i++) {
//            if(i==0){
//                list.get(i).setIsRedWord(true);
//            }else{
//                list.get(i).setIsRedWord(false);
//            }
//        }
        logger.debug("可发起质检工作池结果:{}",list);
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
        param.put("linkCode",CodeEnum.ORDER_STATE_04.getCode());
        //操作按钮代码
        param.put("operateCode",CodeEnum.OPERATE_CODE_16.getCode());
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
        param.put("operateCode",CodeEnum.OPERATE_CODE_17.getCode());
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
        param.put("operateCode",CodeEnum.OPERATE_CODE_20.getCode());
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
    public AjaxResult updateGetWorkOrder(String workOrderNo) {
        return AjaxResult.success(qualityInspectionAcceptService.updateSendByVoById(workOrderNo));
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
    public void exportTwo(HttpServletResponse response, QualityFlagDTO qualityFlagDTO) throws IOException
    {
        List<QualityFlagVO> list = qualityInspectionAcceptService.selectQualityFlagVO(qualityFlagDTO);
        ExcelUtil<QualityFlagVO> util = new ExcelUtil<QualityFlagVO>(QualityFlagVO.class);
        util.exportExcel(response, list, "WorkOrderTwo");
    }


    //************************************************
        /*
        质检查询
         */
    @GetMapping("/internal/selectQualityVo")
    public TableDataInfo selectQualityVo(QualityDTO qualityDTO)
    {
        startPage();
        List<QualityAcceptVo> list = qualityInspectionAcceptService.selectQualityVo(qualityDTO);
        return getDataTable(list);
    }
    //获取质检确认池
    @GetMapping("/internal/selectQualityFlagVO")
    public TableDataInfo selectQualityFlagVo(QualityFlagDTO qualityFlagDTO){
        startPage();
        List<QualityFlagVO> list = qualityInspectionAcceptService.selectQualityFlagVO(qualityFlagDTO);
        return getDataTable(list);
    }
    //查询质检处理信息，申诉信息
    @GetMapping("/internal/getHandleInfoList")
    public TableDataInfo getHandleInfoList(HandleProcessInfoDTO handleProcessInfoDTO) {
        List<HandleProcessInfoVO> list = qualityInspectionHandleService.getHandleInfoList(handleProcessInfoDTO);
        return getDataTable(list);
    }
    //保存质检处理详情页全部信息
    @PostMapping("/internal/insertHandleInfo")
    public AjaxResult insertHandleInfo(HandleDTO handleDTO) {
        return AjaxResult.success(qualityInspectionHandleService.insertHandleInfo(handleDTO));
    }

    //信息需求失效批处理
    @GetMapping("/internal/batchAcceptVo/invalidDate")
    public AjaxResult batchAcceptVo(@PathVariable("invalidDate") String invalidDate){
        try{
            qualityInspectionAcceptService.batchAcceptVo(invalidDate);
        }catch(RuntimeException e){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("执行成功");
    }

}

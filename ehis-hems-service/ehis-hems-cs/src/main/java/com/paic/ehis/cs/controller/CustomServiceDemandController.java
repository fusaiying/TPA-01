package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.CollaborativeFrom;
import com.paic.ehis.cs.domain.EditInfo;
import com.paic.ehis.cs.domain.WorkHandleInfo;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ServiceProcessingVo;
import com.paic.ehis.cs.service.ICollaborativeFromService;
import com.paic.ehis.cs.service.IDemandAcceptVoService;
import com.paic.ehis.cs.service.IEditInfoService;
import com.paic.ehis.cs.service.IWorkHandleInfoService;
import com.paic.ehis.cs.utils.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 信息需求
 */
@RestController
@RequestMapping("/demand")
public class CustomServiceDemandController extends BaseController {
    @Autowired
    private IDemandAcceptVoService iDemandAcceptVoService;
    @Autowired
    private IWorkHandleInfoService iWorkHandleInfoService;
    @Autowired
    private ICollaborativeFromService iCollaborativeFromService;
    @Autowired
    private IEditInfoService iEditInfoService;

    /**
     * 信息需求公共池查询方法
     * @param workOrderQueryDTO
     * @return
     */
    @GetMapping("/internal/listAndPublicPool")
    public TableDataInfo listAndPublicPool(WorkOrderQueryDTO workOrderQueryDTO) {
        startPage();
        logger.info("信息需求公共池查询条件：{}",workOrderQueryDTO);
        List<DemandAcceptVo> list = iDemandAcceptVoService.selectDemandAcceptList(workOrderQueryDTO);
        logger.info("信息需求公共池查询结果：{}",list);
        return getDataTable(list);
    }

    /**
     * 信息需求个人池查询方法
     * @param workOrderQueryDTO
     * @return
     */
    //    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPersonalPool")
    public TableDataInfo listAndPersonalPool(WorkOrderQueryDTO workOrderQueryDTO) {
        startPage();
        workOrderQueryDTO.setUpdateBy(SecurityUtils.getUsername());
        logger.info("信息需求个人池查询条件：{}",workOrderQueryDTO);
        List<DemandAcceptVo> list = iDemandAcceptVoService.selectDemandAcceptList2(workOrderQueryDTO);
        logger.info("信息需求个人池查询结果：{}",list);
        return getDataTable(list);
    }

    /**
     * 个人工单详情查询方法
     */
    @GetMapping("/internal/PersonalPool")
    public AjaxResult listAndPool(@RequestParam("workOrderNo") String workOrderNo) {
//        startPage();
//        workOrderQueryDTO.setUpdateBy(SecurityUtils.getUsername());
        logger.info("工单详情查询条件：{}",workOrderNo);
        DemandAcceptVo demandAcceptVo = iDemandAcceptVoService.selectDemandAcceptList3(workOrderNo);
        logger.info("工单详情查询结果：{}",demandAcceptVo);
        return AjaxResult.success(demandAcceptVo);
    }


    /**
     * 根据工单号获取信息需求受理信息
     * @param workOrderNo
     * @return
     */
    @GetMapping("/accept")//信息需求受理
    public AjaxResult selectDemandAcceptVo(@RequestParam("workOrderNo") String workOrderNo) {
        DemandAcceptVo demandAcceptVo = iDemandAcceptVoService.selectDemandAcceptVo(workOrderNo);
        return AjaxResult.success(demandAcceptVo);
    }

    //    @PreAuthorize("@ss.hasPermi('system:customService::huoqu')")
    @Log(title = "获取 ", businessType = BusinessType.UPDATE)
    @PutMapping("/obtain")
    public AjaxResult edit(@RequestBody String workOrderNo) {
        return AjaxResult.success(iDemandAcceptVoService.updateStatus(workOrderNo));
    }

    //    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "获取 ", businessType = BusinessType.UPDATE)
    @PutMapping("/many")
    public AjaxResult edit(@RequestBody String[] workOrderNos) {
        return AjaxResult.success(iDemandAcceptVoService.updateStatusM(workOrderNos));
    }


    //    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "增加 ", businessType = BusinessType.INSERT)
    @PutMapping("/serviceAdd")
    @Transactional
    public AjaxResult serviceAdd(@Validated @RequestBody DemandAcceptVo demandAcceptVo) {
        demandAcceptVo.setContactsPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10));
        demandAcceptVo.setCallPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10));
        demandAcceptVo.setBusinessType(CodeEnum.BUSINESS_TYPE_01.getCode());
        String workOrderNo = "9900000000" + PubFun.createMySqlMaxNoUseCache("cs_work_order_no", 10, 6);
        demandAcceptVo.setWorkOrderNo(workOrderNo);
        return iDemandAcceptVoService.insertServiceInfo(demandAcceptVo) > 0 ?  AjaxResult.success(workOrderNo) : AjaxResult.error();
    }


    //    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "修改受理 ", businessType = BusinessType.UPDATE)
    @PutMapping("/serviceUpdate")
    public AjaxResult serviceUpdate(@Validated @RequestBody DemandAcceptVo demandAcceptVo) {
        return toAjax(iDemandAcceptVoService.updateServiceInfo(demandAcceptVo));
    }

    /**
     * 信息需求处理页面
     *
     * @param serviceProcessingVo
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "获取 ", businessType = BusinessType.INSERT)
    @PutMapping("/insertServiceProcessing")
    public AjaxResult insertServiceProcessing(@Validated @RequestBody ServiceProcessingVo serviceProcessingVo) {
        if (serviceProcessingVo.getSign().equals("01")) {
            return toAjax(iWorkHandleInfoService.insertServiceInfo(serviceProcessingVo));
        } else {
            return toAjax(iWorkHandleInfoService.insertSaveServiceInfo(serviceProcessingVo));
        }

    }

    /**
     * 增加协办信息
     *
     * @param demandAcceptVo
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "获取 ", businessType = BusinessType.INSERT)
    @PutMapping("/insertTeamwork")
    public AjaxResult insertTeamwork(@Validated @RequestBody DemandAcceptVo demandAcceptVo) {
        iCollaborativeFromService.insertTeamwork(demandAcceptVo);
        return AjaxResult.success();
    }

    /**
     * 信息需求取消页面提交按钮
     */
//        @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "提交 ", businessType = BusinessType.INSERT)
    @PutMapping("/cancelSubmit")
    public AjaxResult cancelSubmit(@Validated @RequestBody DemandAcceptVo demandAcceptVo) {
        return toAjax(iEditInfoService.cancelSubmit(demandAcceptVo));
    }

    /**
     * 查询协办工作池
     */
//        @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/selectAssist")
    public TableDataInfo selectAssist(WorkOrderQueryDTO workOrderQueryDTO) {
        startPage();
        workOrderQueryDTO.setUpdateBy(SecurityUtils.getUsername());
        List<DemandAcceptVo> list = iDemandAcceptVoService.selectAssist(workOrderQueryDTO);
        return getDataTable(list);
    }

    /**
     * 协办处理页面 信息需求  服务处理
     *
     * @param serviceProcessingVo
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "获取 ", businessType = BusinessType.INSERT)
    @PutMapping("/teamworkProcessing")
    public AjaxResult teamworkProcessing(@Validated @RequestBody ServiceProcessingVo serviceProcessingVo) {
        return toAjax(iWorkHandleInfoService.teamworkProcessing(serviceProcessingVo));
    }

    /**
     * 征求意见信息信息需求处理意见
     *
     * @param demandAcceptVo
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "获取 ", businessType = BusinessType.INSERT)
    @PutMapping("/insertConsultationDemand")
    public AjaxResult insertConsultationDemand(@Validated @RequestBody DemandAcceptVo demandAcceptVo) {
        return toAjax(iCollaborativeFromService.insertConsultationDemand(demandAcceptVo));
    }

    /**
     * 征求意见投诉需求处理意见
     *
     * @param complaintDealVo
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "获取 ", businessType = BusinessType.INSERT)
    @PutMapping("/insertConsultationDemandOne")
    public AjaxResult insertConsultationDemandOne(@Validated @RequestBody ComplaintDealVo complaintDealVo) {
        return toAjax(iCollaborativeFromService.insertConsultationDemandOne(complaintDealVo));
    }


}

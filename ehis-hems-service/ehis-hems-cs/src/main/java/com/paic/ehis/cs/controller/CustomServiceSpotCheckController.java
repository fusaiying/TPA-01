package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.AttachmentInfo;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.service.IAttachmentInfoService;
import com.paic.ehis.cs.service.IQualityInspectionAcceptService;
import com.paic.ehis.cs.utils.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    private IAttachmentInfoService attachmentInfoService;

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
        workOrderQueryDTO.setUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
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

}

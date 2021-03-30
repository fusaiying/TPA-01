package com.paic.ehis.cs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.*;
import com.paic.ehis.cs.domain.vo.*;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.*;
import com.paic.ehis.cs.utils.CodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.cs.mapper.QualityInspectionHandleMapper;
import com.paic.ehis.cs.domain.QualityInspectionHandle;
import org.springframework.transaction.annotation.Transactional;

/**
 * 质检处理 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-25
 */
@Service
public class QualityInspectionHandleServiceImpl implements IQualityInspectionHandleService
{
    @Autowired
    private QualityInspectionHandleMapper qualityInspectionHandleMapper;
    @Autowired
    private QualityInspectionItemMapper qualityInspectionItemMapper;
    @Autowired
    private QualityInspectionAcceptMapper qualityInspectionAcceptMapper;
    @Autowired
    private IDemandAcceptVoService iDemandAcceptVoService;
    @Autowired
    private IComplaintAcceptVoService iComplaintAcceptVoService;
    @Autowired
    private IAcceptDetailInfoService iAcceptDetailInfoService;
    @Autowired
    private IAttachmentInfoService iAttachmentInfoService;
    @Autowired
    private IQualityInspectionItemService iQualityInspectionItemService;
    @Autowired
    private FlowLogMapper flowLogMapper;
    /**
     * 查询质检处理 
     * 
     * @param inspectionId 质检处理 ID
     * @return 质检处理 
     */
    @Override
    public QualityInspectionHandle selectQualityInspectionHandleById(String inspectionId)
    {
        return qualityInspectionHandleMapper.selectQualityInspectionHandleById(inspectionId);
    }

    /**
     * 查询质检处理 列表
     * 
     * @param qualityInspectionHandle 质检处理 
     * @return 质检处理 
     */
    @Override
    public List<QualityInspectionHandle> selectQualityInspectionHandleList(QualityInspectionHandle qualityInspectionHandle)
    {
        return qualityInspectionHandleMapper.selectQualityInspectionHandleList(qualityInspectionHandle);
    }

    /**
     * 新增质检处理 
     * 
     * @param qualityInspectionHandle 质检处理 
     * @return 结果
     */
    @Override
    public int insertQualityInspectionHandle(QualityInspectionHandle qualityInspectionHandle)
    {
        return qualityInspectionHandleMapper.insertQualityInspectionHandle(qualityInspectionHandle);
    }

    /**
     * 修改质检处理 
     * 
     * @param qualityInspectionHandle 质检处理 
     * @return 结果
     */
    @Override
    public int updateQualityInspectionHandle(QualityInspectionHandle qualityInspectionHandle)
    {
        return qualityInspectionHandleMapper.updateQualityInspectionHandle(qualityInspectionHandle);
    }

    /**
     * 批量删除质检处理 
     * 
     * @param inspectionIds 需要删除的质检处理 ID
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionHandleByIds(String[] inspectionIds)
    {
        return qualityInspectionHandleMapper.deleteQualityInspectionHandleByIds(inspectionIds);
    }

    /**
     * 删除质检处理 信息
     * 
     * @param inspectionId 质检处理 ID
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionHandleById(String inspectionId)
    {
        return qualityInspectionHandleMapper.deleteQualityInspectionHandleById(inspectionId);
    }

    /**
     * 新增质检处理
     * @param qualityInspectionHandle
     * @return
     */
    @Override
    public int insertHandle(QualityInspectionHandle qualityInspectionHandle) {
        //从前端获取质检编号
        qualityInspectionHandle.getInspectionId();
        return qualityInspectionHandleMapper.insertQualityInspectionHandle(qualityInspectionHandle);
    }
    /**
     *  质检差错   修改是否申述等字段
     * @param qualityInspectionHandle
     * @return
     */
    @Override
    public int updateQualityHandle(QualityInspectionHandle qualityInspectionHandle) {
        //前端获取质检号
        qualityInspectionHandle.setInspectionId(qualityInspectionHandle.getInspectionId());
        //修改相关内容
        qualityInspectionHandle.setAppealFlag(qualityInspectionHandle.getAppealFlag());
        qualityInspectionHandle.setAppealReason(qualityInspectionHandle.getAppealReason());
        qualityInspectionHandle.setScore(qualityInspectionHandle.getScore());
        qualityInspectionHandle.setStatus(qualityInspectionHandle.getStatus());
        return qualityInspectionHandleMapper.updateQualityInspectionHandle(qualityInspectionHandle);
    }

    /**
     *质检差错确认工作池查询
     */
    @Override
    public List<AcceptVo> selectHandle(WorkOrderQueryDTO workOrderQueryDTO) {

        return qualityInspectionHandleMapper.selectHandle(workOrderQueryDTO);
    }

    @Override
    public List<HandleProcessInfoVO> getHandleInfoList(HandleProcessInfoDTO handleProcessInfoDTO) {
        return qualityInspectionHandleMapper.getHandleInfoList(handleProcessInfoDTO);
    }

    @Override
    public int insertHandleInfo(HandleDTO handleDTO) {
        iDemandAcceptVoService.insertServiceInfo(handleDTO.getDemandAcceptVo());
        iComplaintAcceptVoService.complaintSaveHandling(handleDTO.getComplaintDealVo());
        iAttachmentInfoService.insertAttachmentInfo(handleDTO.getAttachmentInfo());
        iQualityInspectionItemService.insertItem(handleDTO.getQualityVo());
        return 1;
    }

    @Override
    public String selectHandleStatus(QualityVo qualityVo) {
        return qualityInspectionHandleMapper.selectHandleStatus(qualityVo);
    }

    @Override
    public QualityInspectionHandleVo getQualityHandleInfo(QualityInspectionDTO qualityInspectionDTO) {
        /**
         * 质检项目集合
         */
        List<QualityInspectionItemVo> qualityInspectionItemVos=qualityInspectionHandleMapper.getQualityHandleItemInfo(qualityInspectionDTO);
        QualityInspectionHandleVo qualityHandleInfo = null;
        if(qualityInspectionItemVos!=null && !qualityInspectionItemVos.isEmpty()){
            String inspectionHandlerId = qualityInspectionItemVos.get(0).getInspectionHandlerId();
            qualityInspectionDTO.setInspectionHandlerId(inspectionHandlerId);
            qualityHandleInfo = qualityInspectionHandleMapper.getQualityHandleInfoById(qualityInspectionDTO);
        }
        if(qualityHandleInfo==null){
            qualityHandleInfo=new QualityInspectionHandleVo();
            qualityHandleInfo.setStatus("04");
        }
        qualityHandleInfo.setInspectionId(qualityInspectionDTO.getInspectionId());
        qualityHandleInfo.setItems(qualityInspectionItemVos);
        return qualityHandleInfo;
    }

    @Transactional
    @Override
    public int insertHandleInfo(QualityInspectionDTO qualityInspectionDTO) {

        Map<String,String> param = new HashMap<>();

        List<FlowLog> flowLogList = new ArrayList<>();
        FlowLog flowLog = new FlowLog();

        //主要针对确认只是更新处理表
        if(StringUtils.isNotEmpty(qualityInspectionDTO.getInspectionHandlerId())){
            //投诉并且申诉是，调用受理和处理的修改操作
            if(CodeEnum.BUSINESS_TYPE_03.getCode().equals(qualityInspectionDTO.getBusinessType()) && "01".equals(qualityInspectionDTO.getAppealFlag())){
                ComplaintAcceptVo complaintAcceptVo=qualityInspectionDTO.getComplaintAcceptVo();
                complaintAcceptVo.setWorkOrderNo(qualityInspectionDTO.getWorkOrderNo());
                iComplaintAcceptVoService.updateComplaintAcceptVo(complaintAcceptVo);
                ComplaintDealVo complaintDealVo= qualityInspectionDTO.getComplaintDealVo();
                complaintDealVo.setWorkOrderNo(qualityInspectionDTO.getWorkOrderNo());
                iComplaintAcceptVoService.complaintHandling(complaintDealVo);
            }
            qualityInspectionHandleMapper.updateHandleInfoById(qualityInspectionDTO);
        }else {
            List<QualityInspectionItemVo> inspectionItemVos = qualityInspectionDTO.getItems();
            if (inspectionItemVos != null && !inspectionItemVos.isEmpty()) {
                //作废状态
                qualityInspectionDTO.setItemStatus("N");
                //作废历史数据
                qualityInspectionItemMapper.updateQualityInspectionItemById(qualityInspectionDTO);
                //结案更新质检结果和状态
                if (CodeEnum.CONFIRM_STATE_03.getCode().equals(qualityInspectionDTO.getStatus())) {
                    //合格
                    String result = CodeEnum.INSPECTION_RESULT_01.getCode();
                    for (int i = 0; i < inspectionItemVos.size(); i++) {
                        if ("01".equals(inspectionItemVos.get(i).getItemValue())) {
                            //不合格
                            result = CodeEnum.INSPECTION_RESULT_02.getCode();
                            break;
                        }
                    }
                    WorkOrderQueryDTO workOrderQueryDTO = new WorkOrderQueryDTO();
                    workOrderQueryDTO.setResult(result);
                    workOrderQueryDTO.setAcceptStatus(CodeEnum.INSPECTION_STATE_03.getCode());
                    workOrderQueryDTO.setUpdateTime(DateUtils.getNowDate());
                    workOrderQueryDTO.setUpdateBy(SecurityUtils.getUsername());
                    workOrderQueryDTO.setInspectionId(qualityInspectionDTO.getInspectionId());
                    workOrderQueryDTO.setStatus(CodeEnum.INSPECTION_STATE_02.getCode());
                    qualityInspectionAcceptMapper.updateAcceptByVoDTO(workOrderQueryDTO);

                    //操作后主流程状态
                    param.put("status", CodeEnum.LINK_CODE_09.getCode());
                    //操作前主流程状态
                    param.put("linkCode", CodeEnum.LINK_CODE_09.getCode());
                    //操作按钮代码
                    param.put("operateCode", CodeEnum.ACTION_TYPE_18.getCode());

                }
                String inspectionHandlerId = PubFun.createMySqlMaxNoUseCache("inspection_handler_id", 10, 10);
                qualityInspectionDTO.setInspectionHandlerId(inspectionHandlerId);
                qualityInspectionHandleMapper.insertHandleInfo(qualityInspectionDTO);
                qualityInspectionItemMapper.insertHandleItemInfoBatch(qualityInspectionDTO);
            }
        }
        //操作流程轨迹记录
        String id = qualityInspectionDTO.getWorkOrderNo();
        //流转记录添加
        String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20);
        if(CodeEnum.BUSINESS_TYPE_03.getCode().equals(qualityInspectionDTO.getBusinessType()) && "01".equals(qualityInspectionDTO.getStatus())){
            //操作后主流程状态 案件退回修改
            param.put("status",CodeEnum.LINK_CODE_09.getCode());
            //操作前主流程状态
            param.put("linkCode",CodeEnum.LINK_CODE_09.getCode());
            //操作按钮代码
            param.put("operateCode",CodeEnum.ACTION_TYPE_17.getCode());
        }else if ("02".equals(qualityInspectionDTO.getStatus())){
            //操作后主流程状态 案件退回修改后 差错提交
            param.put("status",CodeEnum.LINK_CODE_09.getCode());
            //操作前主流程状态
            param.put("linkCode",CodeEnum.LINK_CODE_09.getCode());
            //操作按钮代码
            param.put("operateCode",CodeEnum.ACTION_TYPE_19.getCode());
            qualityInspectionHandleMapper.updateHandleInfoById(qualityInspectionDTO);
        }else if(CodeEnum.BUSINESS_TYPE_01.getCode().equals(qualityInspectionDTO.getBusinessType()) && "01".equals(qualityInspectionDTO.getStatus())){
            //操作后主流程状态 案件退回修改后 案件复核
            param.put("status",CodeEnum.LINK_CODE_09.getCode());
            //操作前主流程状态
            param.put("linkCode",CodeEnum.LINK_CODE_09.getCode());
            //操作按钮代码
            param.put("operateCode",CodeEnum.ACTION_TYPE_20.getCode());
            qualityInspectionHandleMapper.updateHandleInfoById(qualityInspectionDTO);
        }else {
            //操作后主流程状态 结案
            param.put("status",CodeEnum.LINK_CODE_09.getCode());
            //操作前主流程状态
            param.put("linkCode",CodeEnum.LINK_CODE_09.getCode());
            //操作按钮代码
            param.put("operateCode",CodeEnum.ACTION_TYPE_18.getCode());
            qualityInspectionHandleMapper.updateHandleInfoById(qualityInspectionDTO);
        }
        flowLog.setFlowId(flow_id);
        flowLog.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
        flowLog.setCreatedTime(DateUtils.getNowDate());
        flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
        flowLog.setUpdatedTime(DateUtils.getNowDate());
        flowLog.setWorkOrderNo(id);
        //操作后主流程状态
        //flowLog.setStatus(param.get("status"));
        flowLog.setLinkCode(param.get("linkCode"));
        flowLog.setOperateCode(param.get("operateCode"));
        flowLogList.add(flowLog);

        return flowLogMapper.insertBatch(flowLogList);

    }

    //质检差错修改提交
    @Transactional
    @Override
    public int insertHandleInfo1(QualityInspectionDTO qualityInspectionDTO) {

        Map<String,String> param = new HashMap<>();

        List<FlowLog> flowLogList = new ArrayList<>();
        FlowLog flowLog = new FlowLog();

        //主要针对确认只是更新处理表
        if(StringUtils.isNotEmpty(qualityInspectionDTO.getInspectionHandlerId())){
            //投诉并且申诉是，调用受理和处理的修改操作
            if(CodeEnum.BUSINESS_TYPE_03.getCode().equals(qualityInspectionDTO.getBusinessType()) && "01".equals(qualityInspectionDTO.getAppealFlag())){
                ComplaintAcceptVo complaintAcceptVo=qualityInspectionDTO.getComplaintAcceptVo();
                complaintAcceptVo.setWorkOrderNo(qualityInspectionDTO.getWorkOrderNo());
                ComplaintDealVo complaintDealVo= qualityInspectionDTO.getComplaintDealVo();
                complaintDealVo.setWorkOrderNo(qualityInspectionDTO.getWorkOrderNo());
                iComplaintAcceptVoService.updateComplaintAcceptVoProcess(complaintAcceptVo,complaintDealVo);
            }
            qualityInspectionHandleMapper.updateHandleInfoById(qualityInspectionDTO);
        }else {
            List<QualityInspectionItemVo> inspectionItemVos = qualityInspectionDTO.getItems();
            if (inspectionItemVos != null && !inspectionItemVos.isEmpty()) {
                //作废状态
                qualityInspectionDTO.setItemStatus("N");
                //作废历史数据
                qualityInspectionItemMapper.updateQualityInspectionItemById(qualityInspectionDTO);
                //结案更新质检结果和状态
                if (CodeEnum.CONFIRM_STATE_03.getCode().equals(qualityInspectionDTO.getStatus())) {
                    //合格
                    String result = CodeEnum.INSPECTION_RESULT_01.getCode();
                    for (int i = 0; i < inspectionItemVos.size(); i++) {
                        if ("01".equals(inspectionItemVos.get(i).getItemValue())) {
                            //不合格
                            result = CodeEnum.INSPECTION_RESULT_02.getCode();
                            break;
                        }
                    }
                    WorkOrderQueryDTO workOrderQueryDTO = new WorkOrderQueryDTO();
                    workOrderQueryDTO.setResult(result);
                    workOrderQueryDTO.setAcceptStatus(CodeEnum.INSPECTION_STATE_03.getCode());
                    workOrderQueryDTO.setUpdateTime(DateUtils.getNowDate());
                    workOrderQueryDTO.setUpdateBy(SecurityUtils.getUsername());
                    workOrderQueryDTO.setInspectionId(qualityInspectionDTO.getInspectionId());
                    workOrderQueryDTO.setStatus(CodeEnum.INSPECTION_STATE_02.getCode());
                    qualityInspectionAcceptMapper.updateAcceptByVoDTO(workOrderQueryDTO);

                    //操作后主流程状态
                    param.put("status", CodeEnum.LINK_CODE_09.getCode());
                    //操作前主流程状态
                    param.put("linkCode", CodeEnum.LINK_CODE_09.getCode());
                    //操作按钮代码
                    param.put("operateCode", CodeEnum.ACTION_TYPE_18.getCode());

                }
                String inspectionHandlerId = PubFun.createMySqlMaxNoUseCache("inspection_handler_id", 10, 10);
                qualityInspectionDTO.setInspectionHandlerId(inspectionHandlerId);
                qualityInspectionHandleMapper.insertHandleInfo(qualityInspectionDTO);
                qualityInspectionItemMapper.insertHandleItemInfoBatch(qualityInspectionDTO);
            }
        }
        //操作流程轨迹记录
        String id = qualityInspectionDTO.getWorkOrderNo();
        //流转记录添加
        String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20);
        if(CodeEnum.BUSINESS_TYPE_03.getCode().equals(qualityInspectionDTO.getBusinessType()) && "01".equals(qualityInspectionDTO.getStatus())){
            //操作后主流程状态 案件退回修改
            param.put("status",CodeEnum.LINK_CODE_09.getCode());
            //操作前主流程状态
            param.put("linkCode",CodeEnum.LINK_CODE_09.getCode());
            //操作按钮代码
            param.put("operateCode",CodeEnum.ACTION_TYPE_17.getCode());
        }else if ("02".equals(qualityInspectionDTO.getStatus())){
            //操作后主流程状态 案件退回修改后 差错提交
            param.put("status",CodeEnum.LINK_CODE_09.getCode());
            //操作前主流程状态
            param.put("linkCode",CodeEnum.LINK_CODE_09.getCode());
            //操作按钮代码
            param.put("operateCode",CodeEnum.ACTION_TYPE_19.getCode());
            qualityInspectionHandleMapper.updateHandleInfoById(qualityInspectionDTO);
        }else if(CodeEnum.BUSINESS_TYPE_01.getCode().equals(qualityInspectionDTO.getBusinessType()) && "01".equals(qualityInspectionDTO.getStatus())){
            //操作后主流程状态 案件退回修改后 案件复核
            param.put("status",CodeEnum.LINK_CODE_09.getCode());
            //操作前主流程状态
            param.put("linkCode",CodeEnum.LINK_CODE_09.getCode());
            //操作按钮代码
            param.put("operateCode",CodeEnum.ACTION_TYPE_20.getCode());
            qualityInspectionHandleMapper.updateHandleInfoById(qualityInspectionDTO);
        }else {
            //操作后主流程状态 结案
            param.put("status",CodeEnum.LINK_CODE_09.getCode());
            //操作前主流程状态
            param.put("linkCode",CodeEnum.LINK_CODE_09.getCode());
            //操作按钮代码
            param.put("operateCode",CodeEnum.ACTION_TYPE_18.getCode());
            qualityInspectionHandleMapper.updateHandleInfoById(qualityInspectionDTO);
        }
        flowLog.setFlowId(flow_id);
        flowLog.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
        flowLog.setCreatedTime(DateUtils.getNowDate());
        flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
        flowLog.setUpdatedTime(DateUtils.getNowDate());
        flowLog.setWorkOrderNo(id);
        //操作后主流程状态
        flowLog.setLinkCode(param.get("linkCode"));
        flowLog.setOperateCode(param.get("operateCode"));
        flowLogList.add(flowLog);

        return flowLogMapper.insertBatch(flowLogList);

    }
}

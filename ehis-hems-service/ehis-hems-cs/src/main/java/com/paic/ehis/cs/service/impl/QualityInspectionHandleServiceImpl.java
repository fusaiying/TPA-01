package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.QualityInspectionItem;
import com.paic.ehis.cs.domain.dto.HandleDTO;
import com.paic.ehis.cs.domain.dto.HandleProcessInfoDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.*;
import com.paic.ehis.cs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.cs.mapper.QualityInspectionHandleMapper;
import com.paic.ehis.cs.domain.QualityInspectionHandle;

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
    private IDemandAcceptVoService iDemandAcceptVoService;
    @Autowired
    private IComplaintAcceptVoService iComplaintAcceptVoService;
    @Autowired
    private IAcceptDetailInfoService iAcceptDetailInfoService;
    @Autowired
    private IAttachmentInfoService iAttachmentInfoService;
    @Autowired
    private IQualityInspectionItemService iQualityInspectionItemService;
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
}

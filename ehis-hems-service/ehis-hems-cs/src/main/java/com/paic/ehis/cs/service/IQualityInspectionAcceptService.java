package com.paic.ehis.cs.service;


import com.paic.ehis.cs.domain.AttachmentInfo;
import com.paic.ehis.cs.domain.dto.QualityDTO;
import com.paic.ehis.cs.domain.dto.QualityFlagDTO;
import com.paic.ehis.cs.domain.dto.QualityInspectionDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 质检受理服务 IQualityInspectionAcceptService
 * by Louis
 */
public interface IQualityInspectionAcceptService
{
    /**
     * 发起质检工作池
     * @param workOrderQueryDTO
     * @return
     */
    public List<AcceptVo> selectSendPoolData(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 工单获取
     */
    public AcceptVo updateSendByVoById(String workOrderNo);

    /**
     * 工单修改
     */
    public AcceptVo updateSendByVoByIdById1(String workOrderNo);

    /**
     * 质检工作池
     * @param workOrderQueryDTO
     * @return
     */
    public List<AcceptVo> selectAcceptPoolData(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 发起质检操作
     * @param ids
     * @param param
     * @return
     */
    public int insertAcceptVoBatch(String[] ids, Map<String,String> param);

    /**
     * 通过工单号更新质检状态
     * @param ids
     * @param param
     * @return
     */
    public int inspectionHandleStatusByIds(String[] ids, Map<String,String> param);

    /**
     * 通过工单号获取对象信息
     * @param ids
     * @return
     */
    public List<AcceptVo> getAcceptVoByIds(String[] ids);

    /**
     * 获取单个工单受理对象
     * @param workOrderQueryDTO
     * @return
     */
    public AcceptVo getOneAcceptInfo(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 质检查询
     * @param qualityDTO
     * @return
     */
    List<QualityAcceptVo> selectQualityVo(QualityDTO qualityDTO);

//    /**
//     * 根据条件查询工单附件列表
//     * @param workOrderQueryDTO
//     * @return
//     */
//    public List<AttachmentInfo> getAttachmentList(WorkOrderQueryDTO workOrderQueryDTO);

    //一周批处理
    List<AcceptVo> batchAcceptVo(String invalidDateStar);
    //一个月批处理
    List<AcceptVo> batchAcceptVomonth(String invalidDateStar);
    List<QualityInspectionHandleVo> selectQualityFlagVO(QualityInspectionDTO qualityInspectionDTO);

}

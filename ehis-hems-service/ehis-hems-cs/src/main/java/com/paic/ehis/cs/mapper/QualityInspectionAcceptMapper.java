package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.QualityInspectionAccept;
import com.paic.ehis.cs.domain.dto.QualityDTO;
import com.paic.ehis.cs.domain.dto.QualityFlagDTO;
import com.paic.ehis.cs.domain.dto.QualityInspectionDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.*;
import feign.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 工单质检 QualityInspectionAcceptMapper
 * by Louis
 */
@Repository
public interface QualityInspectionAcceptMapper {
    /**
     * 工单查询工作池查询
     */
    public List<AcceptVo> selectSendByVo(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 质检发起工作池查询
     */
    public List<AcceptVo> selectSendByVoTwo(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 可质检工单根据工单号查询是处理中的数据
     */
    public AcceptVo selectSendByVoById1(String workOrderNo);


    /**
     * 可质检工单根据工单号查询非处理中的最新数据
     */
    public AcceptVo selectSendByVoById2(String workOrderNo);



    /**
     * 质检查询
     * @param qualityDTO
     * @return
     */
     public List<QualityAcceptVo> selectQualityVo(QualityDTO qualityDTO);
     public List<QualityAcceptVo> selectQualityVo2(QualityDTO qualityDTO);

     List<QualityInspectionHandleVo> selectQualityFlagVO(QualityInspectionDTO qualityInspectionDTO);
    /**
     * 质检受理工作池查询
     */
    public List<AcceptVo> selectAcceptByVo(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 批量写入质检信息
     * @param qualityInspectionAcceptList
     * @return
     */
    public int insertAcceptBatch(List<QualityInspectionAccept> qualityInspectionAcceptList);

    /**
     * 更新质检状态
     * @param workOrderQueryDTO
     * @return
     */
    public int updateAcceptByVoDTO(WorkOrderQueryDTO workOrderQueryDTO);

    public AcceptVo getAcceptInfo(WorkOrderQueryDTO workOrderQueryDTO);

    public AcceptDetailInfo getAcceptDetailInfo(WorkOrderQueryDTO workOrderQueryDTO);

    /**将操作人分组查询所有工单*/
    public List<AcceptVo> getWorkOrderCountByUserId(WorkOrderQueryDTO workOrderQueryDTO);

    //查询符合一个月抽检规则的数据
    public List<AcceptVo> getWorkOrderCountByUserIdMonth(WorkOrderQueryDTO workOrderQueryDTO);

    public List<AcceptVo> selectInvalidAcceptDetailInfo(WorkOrderQueryDTO workOrderQueryDTO);

    public List<AcceptVo> selectInvalidQiaMondayFour(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 投诉质检清单导出
     * @param qualityDTO
     * @return
     */
    public List<ComplaintQualityInspectionVO> selectQualityVoCom(QualityDTO qualityDTO);

    /**
     * 信息需求质检清单
     * @param qualityDTO
     * @return
     */
    public List<InformationNeedsQuality> selectQualityVoComInformation(QualityDTO qualityDTO);

    /**
     * 投诉/信息差错率统计
     * @param qualityDTO
     * @return
     */
    public List<ComplaintErrorRateVO> selectError(QualityDTO qualityDTO);
}

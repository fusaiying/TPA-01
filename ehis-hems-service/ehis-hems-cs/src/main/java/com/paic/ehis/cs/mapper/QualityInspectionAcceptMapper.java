package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.QualityInspectionAccept;
import com.paic.ehis.cs.domain.dto.QualityDTO;
import com.paic.ehis.cs.domain.dto.QualityFlagDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.QualityAcceptVo;
import com.paic.ehis.cs.domain.vo.QualityFlagVO;
import com.paic.ehis.cs.domain.vo.QualityVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 工单质检 QualityInspectionAcceptMapper
 * by Louis
 */
@Repository
public interface QualityInspectionAcceptMapper {
    /**
     * 质检发起工作池查询
     */
    public List<AcceptVo> selectSendByVo(WorkOrderQueryDTO workOrderQueryDTO);

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

     List<QualityFlagVO> selectQualityFlagVO(QualityFlagDTO qualityFlagDTO);
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

}

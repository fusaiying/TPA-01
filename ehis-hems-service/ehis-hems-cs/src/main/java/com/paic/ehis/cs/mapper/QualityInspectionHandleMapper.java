package com.paic.ehis.cs.mapper;

import java.util.List;
import com.paic.ehis.cs.domain.QualityInspectionHandle;
import com.paic.ehis.cs.domain.dto.HandleProcessInfoDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.HandleProcessInfoVO;
import com.paic.ehis.cs.domain.vo.QualityVo;

/**
 * 质检处理 Mapper接口
 * 
 * @author sino
 * @date 2021-02-25
 */
public interface QualityInspectionHandleMapper 
{
    /**
     * 查询质检处理 
     * 
     * @param inspectionId 质检处理 ID
     * @return 质检处理 
     */
    public QualityInspectionHandle selectQualityInspectionHandleById(String inspectionId);

    /**
     * 查询质检处理 列表
     * 
     * @param qualityInspectionHandle 质检处理 
     * @return 质检处理 集合
     */
    public List<QualityInspectionHandle> selectQualityInspectionHandleList(QualityInspectionHandle qualityInspectionHandle);

    /**
     * 新增质检处理 
     * 
     * @param qualityInspectionHandle 质检处理
     * @return 结果
     */
    public int insertQualityInspectionHandle(QualityInspectionHandle qualityInspectionHandle);

    /**
     * 修改质检处理 
     * 
     * @param qualityInspectionHandle 质检处理 
     * @return 结果
     */
    public int updateQualityInspectionHandle(QualityInspectionHandle qualityInspectionHandle);

    /**
     * 删除质检处理 
     * 
     * @param inspectionId 质检处理 ID
     * @return 结果
     */
    public int deleteQualityInspectionHandleById(String inspectionId);

    /**
     * 批量删除质检处理 
     * 
     * @param inspectionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteQualityInspectionHandleByIds(String[] inspectionIds);

    public List<AcceptVo> selectHandle(WorkOrderQueryDTO workOrderQueryDTO);

    List<HandleProcessInfoVO> getHandleInfoList(HandleProcessInfoDTO handleProcessInfoDTO);

    String selectHandleStatus(QualityVo qualityVo);
}

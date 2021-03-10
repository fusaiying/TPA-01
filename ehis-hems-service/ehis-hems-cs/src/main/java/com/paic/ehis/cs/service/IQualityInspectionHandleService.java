package com.paic.ehis.cs.service;

import java.util.List;
import com.paic.ehis.cs.domain.QualityInspectionHandle;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;

/**
 * 质检处理 Service接口
 * 
 * @author sino
 * @date 2021-02-25
 */
public interface IQualityInspectionHandleService 
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
     * 批量删除质检处理 
     * 
     * @param inspectionIds 需要删除的质检处理 ID
     * @return 结果
     */
    public int deleteQualityInspectionHandleByIds(String[] inspectionIds);

    /**
     * 删除质检处理 信息
     * 
     * @param inspectionId 质检处理 ID
     * @return 结果
     */
    public int deleteQualityInspectionHandleById(String inspectionId);






    /**
     * 新增质检处理
     * @param qualityInspectionHandle
     * @return
     */
    public int insertHandle(QualityInspectionHandle qualityInspectionHandle);

    /**
     *  质检差错   修改是否申述等字段
     * @param qualityInspectionHandle
     * @return
     */
    int updateQualityHandle(QualityInspectionHandle qualityInspectionHandle);

    /**
     *质检差错确认工作池查询
     */
    List<AcceptVo> selectHandle(WorkOrderQueryDTO workOrderQueryDTO);
}

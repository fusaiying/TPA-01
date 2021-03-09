package com.paic.ehis.cs.mapper;

import java.util.List;

import com.paic.ehis.cs.domain.QualityInspectionAccept;
import com.paic.ehis.cs.domain.QualityInspectionItem;
import com.paic.ehis.cs.domain.dto.QualityInspectionDTO;

/**
 * 质检项目 Mapper接口
 * 
 * @author sino
 * @date 2021-02-25
 */
public interface QualityInspectionItemMapper 
{
    /**
     * 查询质检项目 
     * 
     * @param itemId 质检项目 ID
     * @return 质检项目 
     */
    public QualityInspectionItem selectQualityInspectionItemById(String itemId);
    /**
     * 质检差错查询反显数据
     * @param
     * @return
     */
    public List<QualityInspectionItem> selectItemInspectionById(String inspectionId);

    /**
     * 查询质检项目 列表
     * 
     * @param qualityInspectionItem 质检项目 
     * @return 质检项目 集合
     */
    public List<QualityInspectionItem> selectQualityInspectionItemList(QualityInspectionItem qualityInspectionItem);

    /**
     * 新增质检项目 
     * 
     * @param qualityInspectionItem 质检项目 
     * @return 结果
     */
    public int insertQualityInspectionItem(QualityInspectionItem qualityInspectionItem);


    /**
     * 批量新增  质检处理信息
     * @param qualityInspectionItemList
     * @return
     */
    public int insertExtDocList(List<QualityInspectionItem> qualityInspectionItemList);

    /**
     * 批量修改质检差错确认
     * @param qualityInspectionItemList
     * @return
     */
    public int updateExtDocList(List<QualityInspectionItem> qualityInspectionItemList);

    /**
     * 修改质检项目 
     * 
     * @param qualityInspectionItem 质检项目 
     * @return 结果
     */
    public int updateQualityInspectionItem(QualityInspectionItem qualityInspectionItem);

    /**
     * 删除质检项目 
     * 
     * @param itemId 质检项目 ID
     * @return 结果
     */
    public int deleteQualityInspectionItemById(String itemId);

    /**
     * 批量删除质检项目 
     * 
     * @param itemIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteQualityInspectionItemByIds(String[] itemIds);
}

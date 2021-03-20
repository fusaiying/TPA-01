package com.paic.ehis.cs.service;

import java.util.List;
import java.util.Map;

import com.paic.ehis.cs.domain.QualityInspectionItem;
import com.paic.ehis.cs.domain.vo.QualityVo;

/**
 * 质检项目 Service接口
 * 
 * @author sino
 * @date 2021-02-25
 */
public interface IQualityInspectionItemService 
{
    /**
     * 查询质检项目 
     * 
     * @param itemId 质检项目 ID
     * @return 质检项目 
     */
    public QualityInspectionItem selectQualityInspectionItemById(String itemId);

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
     * 修改质检项目 
     * 
     * @param qualityInspectionItem 质检项目 
     * @return 结果
     */
    public int updateQualityInspectionItem(QualityInspectionItem qualityInspectionItem);

    /**
     * 批量删除质检项目 
     * 
     * @param itemIds 需要删除的质检项目 ID
     * @return 结果
     */
    public int deleteQualityInspectionItemByIds(String[] itemIds);

    /**
     * 删除质检项目 信息
     * 
     * @param itemId 质检项目 ID
     * @return 结果
     */
    public int deleteQualityInspectionItemById(String itemId);
    /**
     * 新增质检项目
     * @param qualityVo
     * @return
     */
    int insertItem(QualityVo qualityVo);
    int insertItem2(QualityVo qualityVo);
    /**
     * 质检差错查询反显数据
     * @param qualityInspectionItem
     * @return
     */
    List<QualityInspectionItem> selectItemInspectionById(QualityInspectionItem qualityInspectionItem);

    /**
     * 质检差错修改item表字段是否时效内响应客户等
     */
    int updateQualityItem(String[] ids, Map<String,String> param);
}

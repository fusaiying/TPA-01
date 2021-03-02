package com.paic.ehis.cs.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.QualityInspectionAccept;
import com.paic.ehis.cs.domain.dto.QualityInspectionDTO;
import com.paic.ehis.cs.mapper.FlowLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.cs.mapper.QualityInspectionItemMapper;
import com.paic.ehis.cs.domain.QualityInspectionItem;
import com.paic.ehis.cs.service.IQualityInspectionItemService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 质检项目 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-25
 */
@Service
public class QualityInspectionItemServiceImpl implements IQualityInspectionItemService 
{
    @Autowired
    private QualityInspectionItemMapper qualityInspectionItemMapper;
    @Autowired
    private FlowLogMapper flowLogMapper;

    /**
     * 查询质检项目 
     * 
     * @param itemId 质检项目 ID
     * @return 质检项目 
     */
    @Override
    public QualityInspectionItem selectQualityInspectionItemById(String itemId)
    {
        return qualityInspectionItemMapper.selectQualityInspectionItemById(itemId);
    }

    /**
     * 查询质检项目 列表
     * 
     * @param qualityInspectionItem 质检项目 
     * @return 质检项目 
     */
    @Override
    public List<QualityInspectionItem> selectQualityInspectionItemList(QualityInspectionItem qualityInspectionItem)
    {
        return qualityInspectionItemMapper.selectQualityInspectionItemList(qualityInspectionItem);
    }

    /**
     * 新增质检项目 
     * 
     * @param qualityInspectionItem 质检项目 
     * @return 结果
     */
    @Override
    public int insertQualityInspectionItem(QualityInspectionItem qualityInspectionItem)
    {
        return qualityInspectionItemMapper.insertQualityInspectionItem(qualityInspectionItem);
    }

    /**
     * 修改质检项目 
     * 
     * @param qualityInspectionItem 质检项目 
     * @return 结果
     */
    @Override
    public int updateQualityInspectionItem(QualityInspectionItem qualityInspectionItem)
    {
        return qualityInspectionItemMapper.updateQualityInspectionItem(qualityInspectionItem);
    }

    /**
     * 批量删除质检项目 
     * 
     * @param itemIds 需要删除的质检项目 ID
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionItemByIds(String[] itemIds)
    {
        return qualityInspectionItemMapper.deleteQualityInspectionItemByIds(itemIds);
    }

    /**
     * 删除质检项目 信息
     * 
     * @param itemId 质检项目 ID
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionItemById(String itemId)
    {
        return qualityInspectionItemMapper.deleteQualityInspectionItemById(itemId);
    }
    /**
     * 新增质检项目
     * @param
     * @return
     */
    @Transactional
    @Override
    public int insertHandle(String[] ids, Map<String, String> param) {
        List<QualityInspectionItem> list=new ArrayList<>();
        QualityInspectionItem qualityInspectionItem=null;
        //从前端获取质检编号

        List<FlowLog> flowLogList=new ArrayList<>();
        FlowLog flowLog=null;
        for (int i = 0; i < ids.length; i++) {
            flowLog=new FlowLog();
            //流转记录添加
            String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",32,20);
            flowLog.setFlowId(flow_id);
            flowLog.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            flowLog.setUpdatedTime(DateUtils.getNowDate());
            flowLog.setWorkOrderNo(ids[i]);
//            flowLog.setSubId(ids[i]);

            flowLog.setLinkCode(param.get("linkCode"));
            flowLog.setOperateCode(param.get("operateCode"));
            flowLogList.add(flowLog);

            qualityInspectionItem=new QualityInspectionItem();
            qualityInspectionItem.setItemId(ids[i]);
            qualityInspectionItem.setStatus("01");
            qualityInspectionItem.setUpdatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            qualityInspectionItem.setUpdatedTime(DateUtils.getNowDate());
            qualityInspectionItem.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
            qualityInspectionItem.setCreatedTime(DateUtils.getNowDate());

            list.add(qualityInspectionItem);
        }
        //记录操作轨迹
        flowLogMapper.insertBatch(flowLogList);
        return qualityInspectionItemMapper.insertExtDocList(list);
    }

    /**
     * 质检差错查询反显数据
     * @param qualityInspectionItem
     * @return
     */
    @Override
    public List<QualityInspectionItem> selectItemInspectionById(QualityInspectionItem qualityInspectionItem) {


        List<QualityInspectionItem> qualityInspectionItems=qualityInspectionItemMapper.selectItemInspectionById(qualityInspectionItem.getInspectionId());
        for (QualityInspectionItem qq:qualityInspectionItems
             ) {
            qq.setItemType(qualityInspectionItem.getItemType());
            qq.setItemKey(qualityInspectionItem.getItemKey());
            qq.setItemRemark(qualityInspectionItem.getItemRemark());
            qq.setValue(qualityInspectionItem.getValue());
            qq.setStatus(qualityInspectionItem.getStatus());
        }

        return qualityInspectionItems;
    }

    /**
     * 质检差错修改item表字段是否时效内响应客户等
     */
    @Override
    public int updateQualityItem(QualityInspectionItem qualityInspectionItem) {
        return 0;
    }
}

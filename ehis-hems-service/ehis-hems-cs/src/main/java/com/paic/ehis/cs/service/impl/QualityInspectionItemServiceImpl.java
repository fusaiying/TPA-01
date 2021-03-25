package com.paic.ehis.cs.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.QualityInspectionHandle;
import com.paic.ehis.cs.domain.vo.QualityVo;
import com.paic.ehis.cs.mapper.FlowLogMapper;
import com.paic.ehis.cs.utils.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.cs.mapper.QualityInspectionItemMapper;
import com.paic.ehis.cs.domain.QualityInspectionItem;
import com.paic.ehis.cs.service.IQualityInspectionItemService;
import org.springframework.transaction.annotation.Propagation;
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
    //质检项目新增
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int insertItem(QualityVo qualityVo) {
        //从前端获取质检编号
        Map<String,String> param=new HashMap<>();
        //操作前主流程状态
        param.put("linkCode", CodeEnum.LINK_CODE_09.getCode());
        //操作按钮代码
        param.put("operateCode",CodeEnum.ACTION_TYPE_01.getCode());
        FlowLog flowLog=new FlowLog();
        //流转记录添加
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20));
        flowLog.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
        flowLog.setCreatedTime(DateUtils.getNowDate());
        flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
        flowLog.setUpdatedTime(DateUtils.getNowDate());
        flowLog.setLinkCode(param.get("linkCode"));
        flowLog.setOperateCode(param.get("operateCode"));
        //记录操作轨迹
        flowLogMapper.insertFlowLog(flowLog);
        String username = SecurityUtils.getUsername();
        Date time = DateUtils.getNowDate();
        String inspectionId = PubFun.createMySqlMaxNoUseCache("inspection_id", 10, 10);
        List<QualityInspectionItem> itemList =qualityVo.getItemList();
        QualityInspectionItem qualityInspectionItem = qualityInspectionItemMapper.selectHandleInspectionId(qualityVo);
        if (!itemList.isEmpty()) {
            //如果库里根据工单号找不到inspectionId就新增找到了就修改
            if(qualityInspectionItem.getInspectionId() == null){
                qualityVo.getItemList().forEach( item ->{
                    item.setStatus("02");
                    item.setInspectionId(inspectionId);
//                    item.setItemId(PubFun.createMySqlMaxNoUseCache("item_id", 10, 10));
                    item.setCreatedBy(username);
                    item.setUpdatedBy(username);
                    item.setUpdatedTime(time);
                    item.setCreatedTime(time);
                });
                qualityInspectionItemMapper.insertExtDocList(itemList);
            }else {
                qualityVo.getItemList().forEach( item ->{
                    item.setStatus("03");
                    item.setInspectionId(qualityInspectionItem.getInspectionId());
                    item.setUpdatedBy(username);
                    item.setUpdatedTime(time);
                    qualityInspectionItemMapper.updateQualityInspectionItem(item);
                });

            }
        }
        //如果
        if (qualityVo.getStatus()==null) {
            qualityVo.setStatus("01");
            qualityVo.setInspectionId(inspectionId);
            qualityVo.setCreatedBy(username);
            qualityVo.setCreatedTime(time);
            qualityVo.setUpdatedBy(username);
            qualityVo.setUpdatedTime(time);
            qualityInspectionItemMapper.insertQualityVO(qualityVo);
        }else{
            qualityVo.setStatus("02");
            qualityVo.setInspectionId(qualityInspectionItem.getInspectionId());
            qualityVo.setUpdatedBy(username);
            qualityVo.setUpdatedTime(time);
            qualityInspectionItemMapper.updateQualityVO(qualityVo);
        }
        qualityVo.setInspectionId(qualityInspectionItem.getInspectionId());
        qualityVo.setStatus("03");
        qualityVo.setUpdatedBy(username);
        qualityVo.setUpdatedTime(time);
        qualityInspectionItemMapper.updateInspectionAccept(qualityVo);
        return 1 ;
    }
    //提交案件退回，修改
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override

    public int insertItem2(QualityVo qualityVo) {
        //从前端获取质检编号
        Map<String,String> param=new HashMap<>();
        //操作前主流程状态
        param.put("linkCode", CodeEnum.LINK_CODE_09.getCode());
        //操作按钮代码
        param.put("operateCode",CodeEnum.ACTION_TYPE_17.getCode());
        FlowLog flowLog=new FlowLog();
        //流转记录添加
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20));
        flowLog.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
        flowLog.setCreatedTime(DateUtils.getNowDate());
        flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
        flowLog.setUpdatedTime(DateUtils.getNowDate());
        flowLog.setLinkCode(param.get("linkCode"));
        flowLog.setOperateCode(param.get("operateCode"));
        //记录操作轨迹
        flowLogMapper.insertFlowLog(flowLog);
        String username = SecurityUtils.getUsername();
        Date time = DateUtils.getNowDate();
        String inspectionId = PubFun.createMySqlMaxNoUseCache("inspection_id", 10, 10);
        List<QualityInspectionItem> itemList =qualityVo.getItemList();
        QualityInspectionItem qualityInspectionItem = qualityInspectionItemMapper.selectHandleInspectionId(qualityVo);
        if (!itemList.isEmpty()) {
            //如果库里根据工单号找不到inspectionId就新增找到了就修改
            if(qualityInspectionItem == null){
                qualityVo.getItemList().forEach( item ->{
                    item.setStatus("02");
                    item.setInspectionId(inspectionId);
//                    item.setItemId(PubFun.createMySqlMaxNoUseCache("item_id", 10, 10));
                    item.setCreatedBy(username);
                    item.setUpdatedBy(username);
                    item.setUpdatedTime(time);
                    item.setCreatedTime(time);
                });
                qualityInspectionItemMapper.insertExtDocList(itemList);
            }else {
                qualityVo.getItemList().forEach( item ->{
                    item.setStatus("03");
                    item.setInspectionId(qualityInspectionItem.getInspectionId());
                    item.setUpdatedBy(username);
                    item.setUpdatedTime(time);
                    qualityInspectionItemMapper.updateQualityInspectionItem(item);
                });
            }
        }
        //如果
        if (qualityInspectionItem.getInspectionId()==null) {
            qualityVo.setStatus("01");
            qualityVo.setInspectionId(inspectionId);
            qualityVo.setCreatedBy(username);
            qualityVo.setCreatedTime(time);
            qualityVo.setUpdatedBy(username);
            qualityVo.setUpdatedTime(time);
            qualityInspectionItemMapper.insertQualityVO(qualityVo);
        }else{
            qualityVo.setStatus("02");
            qualityVo.setInspectionId(qualityInspectionItem.getInspectionId());
            qualityVo.setUpdatedBy(username);
            qualityVo.setUpdatedTime(time);
            qualityInspectionItemMapper.updateQualityVO(qualityVo);
        }
        qualityVo.setInspectionId(qualityInspectionItem.getInspectionId());
        qualityVo.setStatus("02");
        qualityVo.setUpdatedBy(username);
        qualityVo.setUpdatedTime(time);
        qualityInspectionItemMapper.updateInspectionAccept(qualityVo);
        return 1 ;
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
    public int updateQualityItem(String[] ids, Map<String,String> param) {
        List<QualityInspectionItem> list=new ArrayList<>();
        QualityInspectionItem qualityInspectionItem=new QualityInspectionItem();

        List<FlowLog> flowLogList=new ArrayList<>();
        FlowLog flowLog=null;
        for (int i = 0; i < ids.length; i++) {
            flowLog=new FlowLog();
            //流转记录添加
            String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20);
            flowLog.setFlowId(flow_id);
            flowLog.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setUpdatedTime(DateUtils.getNowDate());
            flowLog.setWorkOrderNo(ids[i]);
//            flowLog.setSubId(ids[i]);

            flowLog.setLinkCode(param.get("linkCode"));
            flowLog.setOperateCode(param.get("operateCode"));
            flowLogList.add(flowLog);
            //记录操作轨迹
            flowLogMapper.insertBatch(flowLogList);

            //随机生成主键流水编号
//            qualityInspectionItem.setItemId(PubFun.createMySqlMaxNoUseCache("item_id",32,20));
            qualityInspectionItem.setStatus("02");
            qualityInspectionItem.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
            qualityInspectionItem.setUpdatedTime(DateUtils.getNowDate());
            qualityInspectionItem.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
            qualityInspectionItem.setCreatedTime(DateUtils.getNowDate());

            list.add(qualityInspectionItem);
        }

        return qualityInspectionItemMapper.updateExtDocList(list);
    }
}

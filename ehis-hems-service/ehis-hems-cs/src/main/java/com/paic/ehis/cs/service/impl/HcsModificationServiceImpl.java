package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.cs.domain.HcsModification;
import com.paic.ehis.cs.domain.vo.ModifyServiceApplication;
import com.paic.ehis.cs.mapper.HcsModificationMapper;
import com.paic.ehis.cs.service.IHcsModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * HCS预约修改 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-21
 */
@Service
public class HcsModificationServiceImpl implements IHcsModificationService
{
    @Autowired
    private HcsModificationMapper hcsModificationMapper;

    /**
     * 查询HCS预约修改 
     * 
     * @param workOrderNo HCS预约修改 ID
     * @return HCS预约修改 
     */
    @Override
    public HcsModification selectHcsModificationById(String workOrderNo)
    {
        return hcsModificationMapper.selectHcsModificationById(workOrderNo);
    }

    /**
     * 查询HCS预约修改 列表
     * 
     * @param hcsModification HCS预约修改 
     * @return HCS预约修改 
     */
    @Override
    public List<HcsModification> selectHcsModificationList(HcsModification hcsModification)
    {
        return hcsModificationMapper.selectHcsModificationList(hcsModification);
    }

    /**
     * 新增HCS预约修改 
     * 
     * @param hcsModification HCS预约修改 
     * @return 结果
     */
    @Override
    public int insertHcsModification(HcsModification hcsModification)
    {
        return hcsModificationMapper.insertHcsModification(hcsModification);
    }

    /**
     * 修改HCS预约修改 
     * 
     * @param hcsModification HCS预约修改 
     * @return 结果
     */
    @Override
    public int updateHcsModification(HcsModification hcsModification)
    {
        // TODO 要同步状态

        return hcsModificationMapper.updateHcsModification(hcsModification);
    }

    /**
     * 批量删除HCS预约修改 
     * 
     * @param workOrderNos 需要删除的HCS预约修改 ID
     * @return 结果
     */
    @Override
    public int deleteHcsModificationByIds(String[] workOrderNos)
    {
        return hcsModificationMapper.deleteHcsModificationByIds(workOrderNos);
    }

    /**
     * 删除HCS预约修改 信息
     * 
     * @param workOrderNo HCS预约修改 ID
     * @return 结果
     */
    @Override
    public int deleteHcsModificationById(String workOrderNo)
    {
        return hcsModificationMapper.deleteHcsModificationById(workOrderNo);
    }

    /**
     * HCS预约修改，外部系统接入；
     * @param modifyServiceApplication
     * @return
     */
    @Override
    public int addModifyServiceAccept(ModifyServiceApplication modifyServiceApplication) {
        //ModifyServiceApplication 转数据库对象
        HcsModification hcsModification=new HcsModification();
        hcsModification.setAlterType(modifyServiceApplication.getType());
        hcsModification.setAlterId(modifyServiceApplication.getModifySeqNo());
        hcsModification.setModifyPriority(modifyServiceApplication.getModifyPriority());
        hcsModification.setWorkOrderNo(modifyServiceApplication.getWorkOrderNo());
        hcsModification.setAlterContent(modifyServiceApplication.getModifyContent());
        hcsModification.setOtherNo(modifyServiceApplication.getApplicationCaseNo());
        //未处理的
        hcsModification.setStatus("Y");
        hcsModification.setCreatedBy("interface");
        hcsModification.setCreatedTime(DateUtils.getNowDate());
        hcsModification.setUpdatedBy("interface");
        hcsModification.setUpdatedTime(DateUtils.getNowDate());
        //操作类型 type modifyService;completeModifyService外部激活
        // 如果是 completeModifyService 要对案件进行激活处理操作 TODO
        /**
         * 在操作是 completeModifyService外部激活的情况下 激活到最近一次操作人的个人池里面吗？？？
         * 如果是激活 加 激活轨迹
         */
        


        return hcsModificationMapper.insertHcsModification(hcsModification);
    }
}

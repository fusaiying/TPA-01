package com.paic.ehis.base.service.impl;

import java.util.Arrays;
import java.util.List;

import com.paic.ehis.base.domain.BaseProviderApply;
import com.paic.ehis.base.domain.BaseProviderWorktime;
import com.paic.ehis.base.mapper.BaseProviderApplyMapper;
import com.paic.ehis.base.mapper.BaseProviderWorktimeMapper;
import com.paic.ehis.base.service.IBaseProviderApplyService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * base_provider_apply(服务商预约信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
@Transactional
public class BaseProviderApplyServiceImpl implements IBaseProviderApplyService
{
    @Autowired
    private BaseProviderApplyMapper baseProviderApplyMapper;

    @Autowired
    private BaseProviderWorktimeMapper baseProviderWorktimeMapper;

    /**
     * 查询base_provider_apply(服务商预约信息)
     * 
     * @param providercode base_provider_apply(服务商预约信息)ID
     * @return base_provider_apply(服务商预约信息)
     */
    @Override
    public BaseProviderApply selectBaseProviderApplyById(String providercode)
    {
        BaseProviderApply baseProviderApply = baseProviderApplyMapper.selectBaseProviderApplyById(providercode);
        if(baseProviderApply != null){
            baseProviderApply.setOutpatientearly(Arrays.asList((baseProviderApply.getOutpatientearlyStr().split(","))));
            baseProviderApply.setSendWay(Arrays.asList((baseProviderApply.getSendWayStr().split(","))));
            baseProviderApply.setSendType(Arrays.asList((baseProviderApply.getSendTypeStr().split(","))));
            List<BaseProviderWorktime> baseProviderWorktimes = baseProviderWorktimeMapper.selectBaseProviderWorktimeBycode(providercode);
            for(BaseProviderWorktime baseProviderWorktime : baseProviderWorktimes) {
                if("work".equals(baseProviderWorktime.getTimeNode())){
                    baseProviderApply.setWorkendtime(baseProviderWorktime.getEndTime());
                    baseProviderApply.setWorkstarttime(baseProviderWorktime.getStartTime());
                    baseProviderApply.setWorkremark(baseProviderWorktime.getNodeRemark());
                }
                if("saturday".equals(baseProviderWorktime.getTimeNode())){
                    baseProviderApply.setSaturdayendtime(baseProviderWorktime.getEndTime());
                    baseProviderApply.setSaturdaystarttime(baseProviderWorktime.getStartTime());
                    baseProviderApply.setSaturdayremark(baseProviderWorktime.getNodeRemark());
                }
                if("sunday".equals(baseProviderWorktime.getTimeNode())){
                    baseProviderApply.setSundayendtime(baseProviderWorktime.getEndTime());
                    baseProviderApply.setSundaystarttime(baseProviderWorktime.getStartTime());
                    baseProviderApply.setSundayremark(baseProviderWorktime.getNodeRemark());
                }
                if("emergencycall".equals(baseProviderWorktime.getTimeNode())){
                    baseProviderApply.setEmergencycallendtime(baseProviderWorktime.getEndTime());
                    baseProviderApply.setEmergencycallstarttime(baseProviderWorktime.getStartTime());
                    baseProviderApply.setEmergencycallremark(baseProviderWorktime.getNodeRemark());
                }
                if("nightemergency".equals(baseProviderWorktime.getTimeNode())){
                    baseProviderApply.setNightemergencyendtime(baseProviderWorktime.getEndTime());
                    baseProviderApply.setNightemergencystarttime(baseProviderWorktime.getStartTime());
                    baseProviderApply.setNightemergencyremark(baseProviderWorktime.getNodeRemark());
                }
            }
        }
        return baseProviderApply;
    }

    /**
     * 查询base_provider_apply(服务商预约信息)列表
     * 
     * @param baseProviderApply base_provider_apply(服务商预约信息)
     * @return base_provider_apply(服务商预约信息)
     */
    @Override
    public List<BaseProviderApply> selectBaseProviderApplyList(BaseProviderApply baseProviderApply)
    {
        return baseProviderApplyMapper.selectBaseProviderApplyList(baseProviderApply);
    }

    /**
     * 新增base_provider_apply(服务商预约信息)
     * 
     * @param baseProviderApply base_provider_apply(服务商预约信息)
     * @return 结果
     */
    @Override
    public int insertBaseProviderApply(BaseProviderApply baseProviderApply)
    {
        baseProviderApplyMapper.deleteBaseProviderApplyById(baseProviderApply.getProviderCode());
        baseProviderWorktimeMapper.deleteBaseProviderWorktimeById(baseProviderApply.getProviderCode());
        baseProviderApply.setCreateTime(DateUtils.getNowDate());
        baseProviderApply.setOutpatientearlyStr(StringUtils.join(baseProviderApply.getOutpatientearly(), ","));
        baseProviderApply.setSendTypeStr(StringUtils.join(baseProviderApply.getSendType(), ","));
        baseProviderApply.setSendWayStr(StringUtils.join(baseProviderApply.getSendWay(), ","));
        int i = baseProviderApplyMapper.insertBaseProviderApply(baseProviderApply);  //保存预约信息
        if(baseProviderApply.getWorkstarttime()!=null){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getWorkendtime());
            baseProviderWorktime.setStartTime(baseProviderApply.getWorkstarttime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getWorkremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setTimeNode("work");
            baseProviderWorktime.setId(PubFun.createMySqlMaxNoUseCache("worktimeid",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
        }
        if(baseProviderApply.getSaturdaystarttime()!=null){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getSaturdayendtime());
            baseProviderWorktime.setStartTime(baseProviderApply.getSundaystarttime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getSaturdayremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setTimeNode("saturday");
            baseProviderWorktime.setId(PubFun.createMySqlMaxNoUseCache("worktimeid",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
        }
        if(baseProviderApply.getSundaystarttime()!=null){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getSundayendtime());
            baseProviderWorktime.setStartTime(baseProviderApply.getSundayendtime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getSundayremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setTimeNode("sunday");
            baseProviderWorktime.setId(PubFun.createMySqlMaxNoUseCache("worktimeid",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
        }
        if(baseProviderApply.getEmergencycallstarttime()!=null){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getEmergencycallendtime());
            baseProviderWorktime.setStartTime(baseProviderApply.getEmergencycallstarttime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getEmergencycallremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setTimeNode("emergencycall");
            baseProviderWorktime.setId(PubFun.createMySqlMaxNoUseCache("worktimeid",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
        }
        if(baseProviderApply.getNightemergencystarttime()!=null){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getNightemergencystarttime());
            baseProviderWorktime.setStartTime(baseProviderApply.getNightemergencystarttime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getNightemergencyremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setTimeNode("nightemergency");
            baseProviderWorktime.setId(PubFun.createMySqlMaxNoUseCache("worktimeid",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
        }
        return i;
    }

    /**
     * 修改base_provider_apply(服务商预约信息)
     * 
     * @param baseProviderApply base_provider_apply(服务商预约信息)
     * @return 结果
     */
    @Override
    public int updateBaseProviderApply(BaseProviderApply baseProviderApply)
    {
        baseProviderApply.setUpdateTime(DateUtils.getNowDate());
        return baseProviderApplyMapper.updateBaseProviderApply(baseProviderApply);
    }

    /**
     * 批量删除base_provider_apply(服务商预约信息)
     * 
     * @param providercodes 需要删除的base_provider_apply(服务商预约信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderApplyByIds(String[] providercodes)
    {
        return baseProviderApplyMapper.deleteBaseProviderApplyByIds(providercodes);
    }

    /**
     * 删除base_provider_apply(服务商预约信息)信息
     * 
     * @param providercode base_provider_apply(服务商预约信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderApplyById(String providercode)
    {
        return baseProviderApplyMapper.deleteBaseProviderApplyById(providercode);
    }
}

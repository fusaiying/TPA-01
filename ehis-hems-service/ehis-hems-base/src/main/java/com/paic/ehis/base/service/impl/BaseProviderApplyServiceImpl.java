package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseProviderApply;
import com.paic.ehis.base.domain.BaseProviderWorktime;
import com.paic.ehis.base.mapper.BaseProviderApplyMapper;
import com.paic.ehis.base.mapper.BaseProviderWorktimeMapper;
import com.paic.ehis.base.service.IBaseProviderApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @param baseProviderApplyinfo base_provider_apply(服务商预约信息)ID
     * @return base_provider_apply(服务商预约信息)
     */
    @Override
    public BaseProviderApply selectBaseProviderApplyById(BaseProviderApply baseProviderApplyinfo)
    {
        BaseProviderApply baseProviderApply =new BaseProviderApply();
        if("01".equals(baseProviderApplyinfo.getOrgFlag())){
            baseProviderApply = baseProviderApplyMapper.selectBaseProviderApplyById(baseProviderApplyinfo.getProviderCode());
        }else if("02".equals(baseProviderApplyinfo.getOrgFlag())){
            baseProviderApply = baseProviderApplyMapper.selectBaseProviderApplyByIdNew(baseProviderApplyinfo.getProviderCode());
        }
        if(baseProviderApply != null){
            if(StringUtils.isNotBlank(baseProviderApply.getOutpatientearlyStr())){
                baseProviderApply.setOutpatientearly(Arrays.asList((baseProviderApply.getOutpatientearlyStr().split(","))));
            }else{
                baseProviderApply.setOutpatientearly(new ArrayList());
            }
            if(StringUtils.isNotBlank(baseProviderApply.getSendWayStr())){
                baseProviderApply.setSendWay(Arrays.asList((baseProviderApply.getSendWayStr().split(","))));
            }else{
                baseProviderApply.setSendWay(new ArrayList());
            }
            if(StringUtils.isNotBlank(baseProviderApply.getSendTypeStr())){
                baseProviderApply.setSendType(Arrays.asList((baseProviderApply.getSendTypeStr().split(","))));
            }else{
                baseProviderApply.setSendType(new ArrayList());
            }
            List<BaseProviderWorktime> baseProviderWorktimes = new ArrayList();
            if("01".equals(baseProviderApplyinfo.getOrgFlag())){
                baseProviderWorktimes = baseProviderWorktimeMapper.selectBaseProviderWorktimeBycode(baseProviderApplyinfo.getProviderCode());
            }else if("02".equals(baseProviderApplyinfo.getOrgFlag())){
                baseProviderWorktimes = baseProviderWorktimeMapper.selectBaseProviderWorktimeBycodeNew(baseProviderApplyinfo.getProviderCode());
            }

            if(!baseProviderWorktimes.isEmpty()){
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
        if("01".equals(baseProviderApply.getOrgFlag())){
            BaseProviderApply baseProviderApplyInfo = baseProviderApplyMapper.selectBaseProviderApplyById(baseProviderApply.getProviderCode());
            if(baseProviderApplyInfo !=null){
                baseProviderApplyMapper.updateBaseProviderApplyByCode(baseProviderApply.getProviderCode());
            }
        }else if("02".equals(baseProviderApply.getOrgFlag())){
            baseProviderApplyMapper.updateBaseProviderApplyByCodeNew(baseProviderApply.getProviderCode());
        }
        if("01".equals(baseProviderApply.getOrgFlag())){
            List<BaseProviderWorktime> baseProviderWorktimeInfos = baseProviderWorktimeMapper.selectBaseProviderWorktimeBycode(baseProviderApply.getProviderCode());
            if(!baseProviderWorktimeInfos.isEmpty()){
                baseProviderWorktimeMapper.updateBaseProviderWorktimeByCode(baseProviderApply.getProviderCode());
            }
        }else if("02".equals(baseProviderApply.getOrgFlag())){
            baseProviderWorktimeMapper.updateBaseProviderWorktimeByCodeNew(baseProviderApply.getProviderCode());
        }

        baseProviderApply.setCreateTime(DateUtils.getNowDate());
        baseProviderApply.setUpdateTime(DateUtils.getNowDate());
        baseProviderApply.setCreateBy(SecurityUtils.getUsername());
        baseProviderApply.setUpdateBy(SecurityUtils.getUsername());
        baseProviderApply.setSerialNo(PubFun.createMySqlMaxNoUseCache("providerApplySer",12,12));
        baseProviderApply.setUpdateFlag("0");
        baseProviderApply.setStatus("Y");
        baseProviderApply.setOutpatientearlyStr(StringUtils.join(baseProviderApply.getOutpatientearly(), ","));
        baseProviderApply.setSendTypeStr(StringUtils.join(baseProviderApply.getSendType(), ","));
        baseProviderApply.setSendWayStr(StringUtils.join(baseProviderApply.getSendWay(), ","));
        int i = 0;
        if("01".equals(baseProviderApply.getOrgFlag())){
            i= baseProviderApplyMapper.insertBaseProviderApply(baseProviderApply);  //保存预约信息
        }else if("02".equals(baseProviderApply.getOrgFlag())){
            i=baseProviderApplyMapper.insertBaseProviderApplyNew(baseProviderApply);  //保存预约信息
        }
        if(baseProviderApply.getWorkstarttime()!=null || baseProviderApply.getWorkendtime()!=null || baseProviderApply.getWorkremark()!=null){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getWorkendtime());
            baseProviderWorktime.setStartTime(baseProviderApply.getWorkstarttime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getWorkremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setUpdateTime(DateUtils.getNowDate());
            baseProviderWorktime.setCreateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setUpdateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setTimeNode("work");
            baseProviderWorktime.setUpdateFlag("0");
            baseProviderWorktime.setStatus("Y");
            baseProviderWorktime.setSerialNo(PubFun.createMySqlMaxNoUseCache("worktimeSer",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            if("01".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
            }else if("02".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktimeNew(baseProviderWorktime);
            }
        }
        if(baseProviderApply.getSaturdaystarttime()!=null || baseProviderApply.getSaturdayendtime()!=null ||baseProviderApply.getSaturdayremark()!=null){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getSaturdayendtime());
            baseProviderWorktime.setStartTime(baseProviderApply.getSundaystarttime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getSaturdayremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setUpdateTime(DateUtils.getNowDate());
            baseProviderWorktime.setCreateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setUpdateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setTimeNode("saturday");
            baseProviderWorktime.setUpdateFlag("0");
            baseProviderWorktime.setStatus("Y");
            baseProviderWorktime.setSerialNo(PubFun.createMySqlMaxNoUseCache("worktimeSer",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            if("01".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
            }else if("02".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktimeNew(baseProviderWorktime);
            }
        }
        if(baseProviderApply.getSundaystarttime()!=null || baseProviderApply.getSundayendtime()!=null  || baseProviderApply.getSundayremark()!=null ){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getSundayendtime());
            baseProviderWorktime.setStartTime(baseProviderApply.getSundayendtime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getSundayremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setUpdateTime(DateUtils.getNowDate());
            baseProviderWorktime.setCreateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setUpdateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setTimeNode("sunday");
            baseProviderWorktime.setUpdateFlag("0");
            baseProviderWorktime.setStatus("Y");
            baseProviderWorktime.setSerialNo(PubFun.createMySqlMaxNoUseCache("worktimeSer",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            if("01".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
            }else if("02".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktimeNew(baseProviderWorktime);
            }
        }
        if(baseProviderApply.getEmergencycallstarttime()!=null || baseProviderApply.getEmergencycallendtime()!=null || baseProviderApply.getEmergencycallremark()!=null){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getEmergencycallendtime());
            baseProviderWorktime.setStartTime(baseProviderApply.getEmergencycallstarttime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getEmergencycallremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setUpdateTime(DateUtils.getNowDate());
            baseProviderWorktime.setCreateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setUpdateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setTimeNode("emergencycall");
            baseProviderWorktime.setUpdateFlag("0");
            baseProviderWorktime.setStatus("Y");
            baseProviderWorktime.setSerialNo(PubFun.createMySqlMaxNoUseCache("worktimeSer",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            if("01".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
            }else if("02".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktimeNew(baseProviderWorktime);
            }
        }
        if(baseProviderApply.getNightemergencystarttime()!=null || baseProviderApply.getNightemergencyendtime()!=null ||baseProviderApply.getNightemergencyremark()!=null){
            BaseProviderWorktime baseProviderWorktime = new BaseProviderWorktime();
            baseProviderWorktime.setEndTime(baseProviderApply.getNightemergencyendtime());
            baseProviderWorktime.setStartTime(baseProviderApply.getNightemergencystarttime());
            baseProviderWorktime.setNodeRemark(baseProviderApply.getNightemergencyremark());
            baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
            baseProviderWorktime.setUpdateTime(DateUtils.getNowDate());
            baseProviderWorktime.setCreateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setUpdateBy(SecurityUtils.getUsername());
            baseProviderWorktime.setTimeNode("nightemergency");
            baseProviderWorktime.setUpdateFlag("0");
            baseProviderWorktime.setStatus("Y");
            baseProviderWorktime.setSerialNo(PubFun.createMySqlMaxNoUseCache("worktimeSer",10,9));
            baseProviderWorktime.setProviderCode(baseProviderApply.getProviderCode());
            if("01".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
            }else if("02".equals(baseProviderApply.getOrgFlag())){
                baseProviderWorktimeMapper.insertBaseProviderWorktimeNew(baseProviderWorktime);
            }
        }
        return i;
    }


    /**
     * 新增base_provider_apply(服务商预约信息)
     *
     * @param providerCode base_provider_apply(服务商预约信息)
     * @return 结果
     */
    @Override
    public int insertBaseProviderApplyNew(String providerCode)
    {
        //备份表数据
        BaseProviderApply baseProviderApplyInfo = baseProviderApplyMapper.selectBaseProviderApplyById(providerCode);
        BaseProviderApply baseProviderApplyInfoNew = baseProviderApplyMapper.selectBaseProviderApplyByIdNew(providerCode);
        if(baseProviderApplyInfoNew !=null){
            baseProviderApplyMapper.updateBaseProviderApplyByCodeNew(providerCode);
        }
        List<BaseProviderWorktime> baseProviderWorktimeInfos = baseProviderWorktimeMapper.selectBaseProviderWorktimeBycode(providerCode);
        List<BaseProviderWorktime> baseProviderWorktimeInfosNew = baseProviderWorktimeMapper.selectBaseProviderWorktimeBycodeNew(providerCode);
        if(!baseProviderWorktimeInfosNew.isEmpty()){
            baseProviderWorktimeMapper.updateBaseProviderWorktimeByCodeNew(providerCode);
        }
        baseProviderApplyInfo.setCreateTime(DateUtils.getNowDate());
        int i = baseProviderApplyMapper.insertBaseProviderApplyNew(baseProviderApplyInfo);  //保存预约信息

       for(BaseProviderWorktime BaseProviderWorktime:baseProviderWorktimeInfos){
           BaseProviderWorktime.setCreateTime(DateUtils.getNowDate());
           baseProviderWorktimeMapper.insertBaseProviderWorktimeNew(BaseProviderWorktime);
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

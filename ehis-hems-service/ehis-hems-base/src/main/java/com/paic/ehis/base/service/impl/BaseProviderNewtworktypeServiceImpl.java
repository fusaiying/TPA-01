package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseProviderNewtworktype;
import com.paic.ehis.base.domain.BaseProviderNewtworktypeLog;
import com.paic.ehis.base.domain.vo.BaseProviderNetworktypeVO;
import com.paic.ehis.base.mapper.BaseProviderNewtworktypeLogMapper;
import com.paic.ehis.base.mapper.BaseProviderNewtworktypeMapper;
import com.paic.ehis.base.service.IBaseProviderNewtworktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * base_provider_newtworktypet(医疗网络类型)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-04
 */
@Service
@Transactional
public class BaseProviderNewtworktypeServiceImpl implements IBaseProviderNewtworktypeService 
{
    @Autowired
    private BaseProviderNewtworktypeMapper baseProviderNewtworktypeMapper;

    @Autowired
    private BaseProviderNewtworktypeLogMapper baseProviderNewtworktypeLogMapper;

    /**
     * 查询base_provider_newtworktypet(医疗网络类型)
     * 
     * @param serNo base_provider_newtworktypet(医疗网络类型)ID
     * @return base_provider_newtworktypet(医疗网络类型)
     */
    @Override
    public BaseProviderNewtworktype selectBaseProviderNewtworktypeById(String serNo)
    {
        return baseProviderNewtworktypeMapper.selectBaseProviderNewtworktypeById(serNo);
    }

    /**
     * 查询base_provider_newtworktypet(医疗网络类型)列表
     * 
     * @param baseProviderNewtworktype base_provider_newtworktypet(医疗网络类型)
     * @return base_provider_newtworktypet(医疗网络类型)
     */
    @Override
    public List<BaseProviderNewtworktype> selectBaseProviderNewtworktypeList(BaseProviderNewtworktype baseProviderNewtworktype)
    {
        baseProviderNewtworktype.setSupplierCode(baseProviderNewtworktype.getProviderCode());
        List<BaseProviderNewtworktype>  baseProviderNewtworktypes= baseProviderNewtworktypeMapper.selectBaseProviderNewtworktypeList(baseProviderNewtworktype);
        /*List<BaseProviderNewtworktype>  baseProviderNewtworktypeinfos = new ArrayList<BaseProviderNewtworktype>();
        for(BaseProviderNewtworktype baseProviderNewtwork :baseProviderNewtworktypes){
           if(StringUtils.isNotBlank(baseProviderNewtwork.getNewChooseStr())){
               baseProviderNewtwork.setNewChoose(Arrays.asList((baseProviderNewtwork.getNewChooseStr().split(","))));
           }else{
               baseProviderNewtwork.setNewChoose(new ArrayList());
           }
            baseProviderNewtworktypeinfos.add(baseProviderNewtwork);
       }*/
        return baseProviderNewtworktypes;
    }

    /**
     * 新增base_provider_newtworktypet(医疗网络类型)
     * 
     * @param baseProviderNewtworktype base_provider_newtworktypet(医疗网络类型)
     * @return 结果
     */
    @Override
    public int insertBaseProviderNewtworktype(BaseProviderNewtworktype baseProviderNewtworktype)
    {
        baseProviderNewtworktype.setCreateTime(DateUtils.getNowDate());
        return baseProviderNewtworktypeMapper.insertBaseProviderNewtworktype(baseProviderNewtworktype);
    }

    /**
     * 修改base_provider_newtworktypet(医疗网络类型)
     * 
     * @param baseProviderNetworktypeVO base_provider_newtworktypet(医疗网络类型)
     * @return 结果
     */
    @Override
    public int updateBaseProviderNewtworktype(BaseProviderNetworktypeVO baseProviderNetworktypeVO)
    {
        int count = 0;
        List<String> networktypenames = baseProviderNewtworktypeMapper.getCodes(baseProviderNetworktypeVO.getProviderCode());
        for(BaseProviderNewtworktype baseProviderNewtworktype :baseProviderNetworktypeVO.getMedicalTypeData()){
            BaseProviderNewtworktypeLog baseProviderNewtworktypeLog = new BaseProviderNewtworktypeLog();
            String oldChoose= baseProviderNewtworktype.getOldChoose();
            baseProviderNewtworktype.setOldChoose(baseProviderNewtworktype.getNewChoose());
            baseProviderNewtworktype.setNewChoose(oldChoose);
            baseProviderNewtworktype.setSupplierCode(baseProviderNetworktypeVO.getProviderCode());
            if(!networktypenames.isEmpty()){
                if(networktypenames.contains(String.valueOf(baseProviderNewtworktype.getNetworktypeCode()))){
                    baseProviderNewtworktype.setUpdateTime(DateUtils.getNowDate());
                    //修改当前选项
                    //List<String>  arr2= baseProviderNewtworktype.getNewChoose();
                    //baseProviderNewtworktype.setNewChooseStr(StringUtils.join(arr2, ","));
                    baseProviderNewtworktypeMapper.updateBaseProviderNewtworktype(baseProviderNewtworktype);
                    baseProviderNewtworktypeLog.setFailureTime(DateUtils.getNowDate());
                }else{  //新增数据记录
                    baseProviderNewtworktype.setSerialNo(PubFun.createMySqlMaxNoUseCache("newtworktypeSer",12,12));
                    baseProviderNewtworktype.setCreateBy(SecurityUtils.getUsername());
                    baseProviderNewtworktype.setUpdateBy(SecurityUtils.getUsername());
                    baseProviderNewtworktype.setCreateTime(DateUtils.getNowDate());
                    baseProviderNewtworktype.setUpdateTime(DateUtils.getNowDate());
                    baseProviderNewtworktypeMapper.insertBaseProviderNewtworktype(baseProviderNewtworktype);
                }
            }else{//新增数据记录
                baseProviderNewtworktype.setSerialNo(PubFun.createMySqlMaxNoUseCache("newtworktypeSer",12,12));
                baseProviderNewtworktype.setCreateBy(SecurityUtils.getUsername());
                baseProviderNewtworktype.setUpdateBy(SecurityUtils.getUsername());
                baseProviderNewtworktype.setCreateTime(DateUtils.getNowDate());
                baseProviderNewtworktype.setUpdateTime(DateUtils.getNowDate());
                baseProviderNewtworktypeMapper.insertBaseProviderNewtworktype(baseProviderNewtworktype);
            }

            //保存修改记录
            baseProviderNewtworktypeLog.setSerialNo(PubFun.createMySqlMaxNoUseCache("Ser",12,12));
            baseProviderNewtworktypeLog.setNewChoose(baseProviderNewtworktype.getOldChoose());
            baseProviderNewtworktypeLog.setNewDate(baseProviderNewtworktype.getNewDate());
            baseProviderNewtworktypeLog.setSupplierCode(baseProviderNetworktypeVO.getProviderCode());
            baseProviderNewtworktypeLog.setStatus("Y");
            baseProviderNewtworktypeLog.setNetworktypeCode(baseProviderNewtworktype.getNetworktypeCode());
            baseProviderNewtworktypeLog.setCreateBy(SecurityUtils.getUsername());
            baseProviderNewtworktypeLog.setUpdateBy(SecurityUtils.getUsername());
            baseProviderNewtworktypeLog.setCreateTime(DateUtils.getNowDate());
            baseProviderNewtworktypeLog.setUpdateTime(DateUtils.getNowDate());
            int i = baseProviderNewtworktypeLogMapper.insertBaseProviderNewtworktypeLog(baseProviderNewtworktypeLog);
            count += i;
        }
        return count;
    }

    /**
     * 批量删除base_provider_newtworktypet(医疗网络类型)
     * 
     * @param serNos 需要删除的base_provider_newtworktypet(医疗网络类型)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypeByIds(String[] serNos)
    {
        return baseProviderNewtworktypeMapper.deleteBaseProviderNewtworktypeByIds(serNos);
    }

    /**
     * 删除base_provider_newtworktypet(医疗网络类型)信息
     * 
     * @param serNo base_provider_newtworktypet(医疗网络类型)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypeById(String serNo)
    {
        return baseProviderNewtworktypeMapper.deleteBaseProviderNewtworktypeById(serNo);
    }
}

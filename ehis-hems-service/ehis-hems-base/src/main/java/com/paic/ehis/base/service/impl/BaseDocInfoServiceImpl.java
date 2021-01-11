package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.service.IBaseDocInfoService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.base.domain.BaseDocInfo;
import com.paic.ehis.base.mapper.BaseDocInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base_doctor(医生信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-31
 */
@Service
public class BaseDocInfoServiceImpl implements IBaseDocInfoService
{
    @Autowired
    private BaseDocInfoMapper baseDocInfoMapper;

    /**
     * 查询base_doctor(医生信息)
     * 
     * @param docCode base_doctor(医生信息)ID
     * @return base_doctor(医生信息)
     */
    @Override
    public BaseDocInfo selectBaseDocInfoById(String docCode)
    {
        return baseDocInfoMapper.selectBaseDocInfoById(docCode);
    }

    /**
     * 查询base_doctor(医生信息)列表
     * 
     * @param baseDocInfo base_doctor(医生信息)
     * @return base_doctor(医生信息)
     */
    @Override
    public List<BaseDocInfo> selectBaseDocInfoList(BaseDocInfo baseDocInfo)
    {
        return baseDocInfoMapper.selectBaseDocInfoList(baseDocInfo);
    }

    /**
     * 新增base_doctor(医生信息)
     * 
     * @param baseDocInfo base_doctor(医生信息)
     * @return 结果
     */
    @Override
    public int insertBaseDocInfo(BaseDocInfo baseDocInfo)
    { String docCode = baseDocInfo.getDocCode();
        //判断医生编码为空就是新增，不为空就是修改
        if (StringUtils.isEmpty(docCode)) {
            docCode = "D" + PubFun.createMySqlMaxNoUseCache("BaseDocInfo", 0, 9);
            baseDocInfo.setDocCode(docCode);
            baseDocInfo.setCreateTime(DateUtils.getNowDate());
            return baseDocInfoMapper.insertBaseDocInfo(baseDocInfo);
        }
        else {
            baseDocInfoMapper.deleteBaseDocInfoById(docCode);
            if(docCode == null){  return 0; }
            baseDocInfo.setDocCode(docCode);
            baseDocInfo.setUpdateTime(DateUtils.getNowDate());
            baseDocInfoMapper.updateBaseDocInfo(baseDocInfo);
        }
        return 1;
    }

    /**
     * 修改base_doctor(医生信息)
     * 
     * @param baseDocInfo base_doctor(医生信息)
     * @return 结果
     */
    @Override
    public int updateBaseDocInfo(BaseDocInfo baseDocInfo)
    {
        baseDocInfo.setUpdateTime(DateUtils.getNowDate());
        return baseDocInfoMapper.updateBaseDocInfo(baseDocInfo);
    }

    /**
     * 批量删除base_doctor(医生信息)
     * 
     * @param docCodes 需要删除的base_doctor(医生信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseDocInfoByIds(String[] docCodes)
    {
        return baseDocInfoMapper.deleteBaseDocInfoByIds(docCodes);
    }

    /**
     * 删除base_doctor(医生信息)信息
     * 
     * @param docCode base_doctor(医生信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseDocInfoById(String docCode)
    {
        return baseDocInfoMapper.deleteBaseDocInfoById(docCode);
    }
}

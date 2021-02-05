package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.EditInfo;
import com.paic.ehis.cs.mapper.EditInfoMapper;
import com.paic.ehis.cs.service.IEditInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-05
 */
@Service
public class EditInfoServiceImpl implements IEditInfoService
{
    @Autowired
    private EditInfoMapper editInfoMapper;

    /**
     * 查询修改信息 
     * 
     * @param editId 修改信息 ID
     * @return 修改信息 
     */
    @Override
    public EditInfo selectEditInfoById(Long editId)
    {
        return editInfoMapper.selectEditInfoById(editId);
    }

    /**
     * 查询修改信息 列表
     * 
     * @param editInfo 修改信息 
     * @return 修改信息 
     */
    @Override
    public List<EditInfo> selectEditInfoList(EditInfo editInfo)
    {
        return editInfoMapper.selectEditInfoList(editInfo);
    }

    /**
     * 新增修改信息 
     * 
     * @param editInfo 修改信息 
     * @return 结果
     */
    @Override
    public int insertEditInfo(EditInfo editInfo)
    {
        return editInfoMapper.insertEditInfo(editInfo);
    }

    /**
     * 修改修改信息 
     * 
     * @param editInfo 修改信息 
     * @return 结果
     */
    @Override
    public int updateEditInfo(EditInfo editInfo)
    {
        return editInfoMapper.updateEditInfo(editInfo);
    }

    /**
     * 批量删除修改信息 
     * 
     * @param editIds 需要删除的修改信息 ID
     * @return 结果
     */
    @Override
    public int deleteEditInfoByIds(Long[] editIds)
    {
        return editInfoMapper.deleteEditInfoByIds(editIds);
    }

    /**
     * 删除修改信息 信息
     * 
     * @param editId 修改信息 ID
     * @return 结果
     */
    @Override
    public int deleteEditInfoById(Long editId)
    {
        return editInfoMapper.deleteEditInfoById(editId);
    }
}

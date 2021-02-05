package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.EditDetail;
import com.paic.ehis.cs.mapper.EditDetailMapper;
import com.paic.ehis.cs.service.IEditDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改明细 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-05
 */
@Service
public class EditDetailServiceImpl implements IEditDetailService
{
    @Autowired
    private EditDetailMapper editDetailMapper;

    /**
     * 查询修改明细 
     * 
     * @param detailId 修改明细 ID
     * @return 修改明细 
     */
    @Override
    public EditDetail selectEditDetailById(String detailId)
    {
        return editDetailMapper.selectEditDetailById(detailId);
    }

    /**
     * 查询修改明细 列表
     * 
     * @param editDetail 修改明细 
     * @return 修改明细 
     */
    @Override
    public List<EditDetail> selectEditDetailList(EditDetail editDetail)
    {
        return editDetailMapper.selectEditDetailList(editDetail);
    }

    /**
     * 新增修改明细 
     * 
     * @param editDetail 修改明细 
     * @return 结果
     */
    @Override
    public int insertEditDetail(EditDetail editDetail)
    {
        return editDetailMapper.insertEditDetail(editDetail);
    }

    /**
     * 修改修改明细 
     * 
     * @param editDetail 修改明细 
     * @return 结果
     */
    @Override
    public int updateEditDetail(EditDetail editDetail)
    {
        return editDetailMapper.updateEditDetail(editDetail);
    }

    /**
     * 批量删除修改明细 
     * 
     * @param detailIds 需要删除的修改明细 ID
     * @return 结果
     */
    @Override
    public int deleteEditDetailByIds(String[] detailIds)
    {
        return editDetailMapper.deleteEditDetailByIds(detailIds);
    }

    /**
     * 删除修改明细 信息
     * 
     * @param detailId 修改明细 ID
     * @return 结果
     */
    @Override
    public int deleteEditDetailById(String detailId)
    {
        return editDetailMapper.deleteEditDetailById(detailId);
    }
}

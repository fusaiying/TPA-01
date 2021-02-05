package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.EditDetail;

import java.util.List;


/**
 * 修改明细 Mapper接口
 * 
 * @author sino
 * @date 2021-02-05
 */
public interface EditDetailMapper 
{
    /**
     * 查询修改明细 
     * 
     * @param detailId 修改明细 ID
     * @return 修改明细 
     */
    public EditDetail selectEditDetailById(String detailId);

    public EditDetail selectEdit(String workOrderNo);

    /**
     * 查询修改明细 列表
     * 
     * @param editDetail 修改明细 
     * @return 修改明细 集合
     */
    public List<EditDetail> selectEditDetailList(EditDetail editDetail);

    /**
     * 新增修改明细 
     * 
     * @param editDetail 修改明细 
     * @return 结果
     */
    public int insertEditDetail(EditDetail editDetail);

    /**
     * 修改修改明细 
     * 
     * @param editDetail 修改明细 
     * @return 结果
     */
    public int updateEditDetail(EditDetail editDetail);

    /**
     * 删除修改明细 
     * 
     * @param detailId 修改明细 ID
     * @return 结果
     */
    public int deleteEditDetailById(String detailId);

    /**
     * 批量删除修改明细 
     * 
     * @param detailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEditDetailByIds(String[] detailIds);
}

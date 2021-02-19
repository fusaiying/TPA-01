package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.EditDetail;
import com.paic.ehis.cs.domain.FlowLog;

import java.util.List;


/**
 * 修改明细 Service接口
 * 
 * @author sino
 * @date 2021-02-05
 */
public interface IEditDetailService 
{
    /**
     * 查询修改明细 
     * 
     * @param detailId 修改明细 ID
     * @return 修改明细 
     */
    public EditDetail selectEditDetailById(String detailId);

    public List<EditDetail> selectEdit(FlowLog flowLog);

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
     * 批量删除修改明细 
     * 
     * @param detailIds 需要删除的修改明细 ID
     * @return 结果
     */
    public int deleteEditDetailByIds(String[] detailIds);

    /**
     * 删除修改明细 信息
     * 
     * @param detailId 修改明细 ID
     * @return 结果
     */
    public int deleteEditDetailById(String detailId);
}

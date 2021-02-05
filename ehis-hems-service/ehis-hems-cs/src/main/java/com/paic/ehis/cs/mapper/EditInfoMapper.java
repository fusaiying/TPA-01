package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.EditInfo;

import java.util.List;

/**
 * 修改信息 Mapper接口
 * 
 * @author sino
 * @date 2021-02-05
 */
public interface EditInfoMapper 
{
    /**
     * 查询修改信息 
     * 
     * @param editId 修改信息 ID
     * @return 修改信息 
     */
    public EditInfo selectEditInfoById(Long editId);

    /**
     * 查询修改信息 列表
     * 
     * @param editInfo 修改信息 
     * @return 修改信息 集合
     */
    public List<EditInfo> selectEditInfoList(EditInfo editInfo);

    /**
     * 新增修改信息 
     * 
     * @param editInfo 修改信息 
     * @return 结果
     */
    public int insertEditInfo(EditInfo editInfo);

    /**
     * 修改修改信息 
     * 
     * @param editInfo 修改信息 
     * @return 结果
     */
    public int updateEditInfo(EditInfo editInfo);

    /**
     * 删除修改信息 
     * 
     * @param editId 修改信息 ID
     * @return 结果
     */
    public int deleteEditInfoById(Long editId);

    /**
     * 批量删除修改信息 
     * 
     * @param editIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEditInfoByIds(Long[] editIds);
}

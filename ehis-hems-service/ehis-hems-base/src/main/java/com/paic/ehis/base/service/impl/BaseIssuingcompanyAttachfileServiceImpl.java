package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseIssuingcompanyAttachfile;
import com.paic.ehis.base.mapper.BaseIssuingcompanyAttachfileMapper;
import com.paic.ehis.base.service.IBaseIssuingcompanyAttachfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 出单公司附件信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIssuingcompanyAttachfileServiceImpl implements IBaseIssuingcompanyAttachfileService 
{
    @Autowired
    private BaseIssuingcompanyAttachfileMapper baseIssuingcompanyAttachfileMapper;

    /**
     * 查询出单公司附件信息 
     * 
     * @param id 出单公司附件信息 ID
     * @return 出单公司附件信息 
     */
    @Override
    public BaseIssuingcompanyAttachfile selectBaseIssuingcompanyAttachfileById(Long id)
    {
        return baseIssuingcompanyAttachfileMapper.selectBaseIssuingcompanyAttachfileById(id);
    }

    /**
     * 查询出单公司附件信息 列表
     * 
     * @param baseIssuingcompanyAttachfile 出单公司附件信息 
     * @return 出单公司附件信息 
     */
    @Override
    public List<BaseIssuingcompanyAttachfile> selectBaseIssuingcompanyAttachfileList(BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile)
    {
        return baseIssuingcompanyAttachfileMapper.selectBaseIssuingcompanyAttachfileList(baseIssuingcompanyAttachfile);
    }

    /**
     * 新增出单公司附件信息 
     * 
     * @param baseIssuingcompanyAttachfile 出单公司附件信息 
     * @return 结果
     */
    @Override
    public int insertBaseIssuingcompanyAttachfile(BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile)
    {
        baseIssuingcompanyAttachfile.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompanyAttachfile.setStatus("Y");
        return baseIssuingcompanyAttachfileMapper.insertBaseIssuingcompanyAttachfile(baseIssuingcompanyAttachfile);
    }

    /**
     * 修改出单公司附件信息 
     * 
     * @param baseIssuingcompanyAttachfile 出单公司附件信息 
     * @return 结果
     */
    @Override
    public int updateBaseIssuingcompanyAttachfile(BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile)
    {
        baseIssuingcompanyAttachfile.setUpdateTime(DateUtils.getNowDate());
        return baseIssuingcompanyAttachfileMapper.updateBaseIssuingcompanyAttachfile(baseIssuingcompanyAttachfile);
    }

    /**
     * 批量删除出单公司附件信息 
     * 
     * @param ids 需要删除的出单公司附件信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyAttachfileByIds(Long[] ids)
    {
        return baseIssuingcompanyAttachfileMapper.deleteBaseIssuingcompanyAttachfileByIds(ids);
    }

    /**
     * 删除出单公司附件信息 信息
     * 
     * @param id 出单公司附件信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyAttachfileById(Long id)
    {
        return baseIssuingcompanyAttachfileMapper.deleteBaseIssuingcompanyAttachfileById(id);
    }
}

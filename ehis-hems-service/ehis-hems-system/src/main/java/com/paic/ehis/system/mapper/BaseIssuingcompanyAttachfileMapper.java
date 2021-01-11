package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.BaseIssuingcompanyAttachfile;

/**
 * 出单公司附件信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface BaseIssuingcompanyAttachfileMapper 
{
    /**
     * 查询出单公司附件信息 
     * 
     * @param id 出单公司附件信息 ID
     * @return 出单公司附件信息 
     */
    public BaseIssuingcompanyAttachfile selectBaseIssuingcompanyAttachfileById(Long id);

    /**
     * 查询出单公司附件信息 列表
     * 
     * @param baseIssuingcompanyAttachfile 出单公司附件信息 
     * @return 出单公司附件信息 集合
     */
    public List<BaseIssuingcompanyAttachfile> selectBaseIssuingcompanyAttachfileList(BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile);

    /**
     * 新增出单公司附件信息 
     * 
     * @param baseIssuingcompanyAttachfile 出单公司附件信息 
     * @return 结果
     */
    public int insertBaseIssuingcompanyAttachfile(BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile);

    /**
     * 修改出单公司附件信息 
     * 
     * @param baseIssuingcompanyAttachfile 出单公司附件信息 
     * @return 结果
     */
    public int updateBaseIssuingcompanyAttachfile(BaseIssuingcompanyAttachfile baseIssuingcompanyAttachfile);

    /**
     * 删除出单公司附件信息 
     * 
     * @param id 出单公司附件信息 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyAttachfileById(Long id);

    /**
     * 批量删除出单公司附件信息 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyAttachfileByIds(Long[] ids);
}

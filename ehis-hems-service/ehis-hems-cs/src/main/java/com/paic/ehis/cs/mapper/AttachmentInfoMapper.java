package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.AttachmentInfo;

import java.util.List;

/**
 * 附件信息 Mapper接口
 * 
 * @author sino
 * @date 2021-02-20
 */
public interface AttachmentInfoMapper 
{
    /**
     * 查询附件信息 
     * 
     * @param attachmentId 附件信息 ID
     * @return 附件信息 
     */
    public AttachmentInfo selectAttachmentInfoById(String attachmentId);

    /**
     * 查询附件信息 列表
     * 
     * @param attachmentInfo 附件信息 
     * @return 附件信息 集合
     */
    public List<AttachmentInfo> selectAttachmentInfoList(AttachmentInfo attachmentInfo);

    /**
     * 新增附件信息 
     * 
     * @param attachmentInfo 附件信息 
     * @return 结果
     */
    public int insertAttachmentInfo(AttachmentInfo attachmentInfo);

    /**
     * 修改附件信息 
     * 
     * @param attachmentInfo 附件信息 
     * @return 结果
     */
    public int updateAttachmentInfo(AttachmentInfo attachmentInfo);

    /**
     * 删除附件信息 
     * 
     * @param attachmentId 附件信息 ID
     * @return 结果
     */
    public int deleteAttachmentInfoById(String attachmentId);

    /**
     * 批量删除附件信息 
     * 
     * @param attachmentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAttachmentInfoByIds(String[] attachmentIds);
}

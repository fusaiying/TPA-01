package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.AttachmentInfo;
import com.paic.ehis.cs.mapper.AttachmentInfoMapper;
import com.paic.ehis.cs.service.IAttachmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 附件信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-20
 */
@Service
public class AttachmentInfoServiceImpl implements IAttachmentInfoService
{
    @Autowired
    private AttachmentInfoMapper attachmentInfoMapper;

    /**
     * 查询附件信息 
     * 
     * @param attachmentId 附件信息 ID
     * @return 附件信息 
     */
    @Override
    public AttachmentInfo selectAttachmentInfoById(String attachmentId)
    {
        return attachmentInfoMapper.selectAttachmentInfoById(attachmentId);
    }

    /**
     * 查询附件信息 列表
     * 
     * @param attachmentInfo 附件信息 
     * @return 附件信息 
     */
    @Override
    public List<AttachmentInfo> selectAttachmentInfoList(AttachmentInfo attachmentInfo)
    {
        return attachmentInfoMapper.selectAttachmentInfoList(attachmentInfo);
    }

    /**
     * 新增附件信息 
     * 
     * @param attachmentInfo 附件信息 
     * @return 结果
     */
    @Override
    public int insertAttachmentInfo(AttachmentInfo attachmentInfo)
    {
        return attachmentInfoMapper.insertAttachmentInfo(attachmentInfo);
    }

    /**
     * 修改附件信息 
     * 
     * @param attachmentInfo 附件信息 
     * @return 结果
     */
    @Override
    public int updateAttachmentInfo(AttachmentInfo attachmentInfo)
    {
        return attachmentInfoMapper.updateAttachmentInfo(attachmentInfo);
    }

    /**
     * 批量删除附件信息 
     * 
     * @param attachmentIds 需要删除的附件信息 ID
     * @return 结果
     */
    @Override
    public int deleteAttachmentInfoByIds(String[] attachmentIds)
    {
        return attachmentInfoMapper.deleteAttachmentInfoByIds(attachmentIds);
    }

    /**
     * 删除附件信息 信息
     * 
     * @param attachmentId 附件信息 ID
     * @return 结果
     */
    @Override
    public int deleteAttachmentInfoById(String attachmentId)
    {
        return attachmentInfoMapper.deleteAttachmentInfoById(attachmentId);
    }
}

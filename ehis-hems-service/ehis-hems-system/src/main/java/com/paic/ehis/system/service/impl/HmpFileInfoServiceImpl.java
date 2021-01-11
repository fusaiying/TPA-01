package com.paic.ehis.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.HmpFileInfoMapper;
import com.paic.ehis.system.domain.HmpFileInfo;
import com.paic.ehis.system.service.IHmpFileInfoService;

/**
 * 文件信息Service业务层处理
 * 
 * @author sino
 * @date 2020-10-16
 */
@Service
public class HmpFileInfoServiceImpl implements IHmpFileInfoService
{
    @Autowired
    private HmpFileInfoMapper hmpFileInfoMapper;

    /**
     * 查询文件信息
     * 
     * @param fileCode 文件信息ID
     * @return 文件信息
     */
    @Override
    public HmpFileInfo selectHmpFileInfoById(String fileCode)
    {
        return hmpFileInfoMapper.selectHmpFileInfoById(fileCode);
    }

    /**
     * 查询文件信息列表
     * 
     * @param hmpFileInfo 文件信息
     * @return 文件信息
     */
    @Override
    public List<HmpFileInfo> selectHmpFileInfoList(HmpFileInfo hmpFileInfo)
    {
        return hmpFileInfoMapper.selectHmpFileInfoList(hmpFileInfo);
    }

    /**
     * 新增文件信息
     * 
     * @param hmpFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int insertHmpFileInfo(HmpFileInfo hmpFileInfo)
    {
        return hmpFileInfoMapper.insertHmpFileInfo(hmpFileInfo);
    }

    /**
     * 修改文件信息
     * 
     * @param hmpFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int updateHmpFileInfo(HmpFileInfo hmpFileInfo)
    {
        return hmpFileInfoMapper.updateHmpFileInfo(hmpFileInfo);
    }

    /**
     * 批量删除文件信息
     * 
     * @param fileCodes 需要删除的文件信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpFileInfoByIds(String[] fileCodes)
    {
        return hmpFileInfoMapper.deleteHmpFileInfoByIds(fileCodes);
    }

    /**
     * 删除文件信息信息
     * 
     * @param fileCode 文件信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpFileInfoById(String fileCode)
    {
        return hmpFileInfoMapper.deleteHmpFileInfoById(fileCode);
    }
}

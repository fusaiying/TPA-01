package com.paic.ehis.system.service;

import java.util.List;
import com.paic.ehis.system.domain.HmpFileInfo;

/**
 * 文件信息Service接口
 * 
 * @author sino
 * @date 2020-10-16
 */
public interface IHmpFileInfoService
{
    /**
     * 查询文件信息
     * 
     * @param fileCode 文件信息ID
     * @return 文件信息
     */
    public HmpFileInfo selectHmpFileInfoById(String fileCode);

    /**
     * 查询文件信息列表
     * 
     * @param hmpFileInfo 文件信息
     * @return 文件信息集合
     */
    public List<HmpFileInfo> selectHmpFileInfoList(HmpFileInfo hmpFileInfo);

    /**
     * 新增文件信息
     * 
     * @param hmpFileInfo 文件信息
     * @return 结果
     */
    public int insertHmpFileInfo(HmpFileInfo hmpFileInfo);

    /**
     * 修改文件信息
     * 
     * @param hmpFileInfo 文件信息
     * @return 结果
     */
    public int updateHmpFileInfo(HmpFileInfo hmpFileInfo);

    /**
     * 批量删除文件信息
     * 
     * @param fileCodes 需要删除的文件信息ID
     * @return 结果
     */
    public int deleteHmpFileInfoByIds(String[] fileCodes);

    /**
     * 删除文件信息信息
     * 
     * @param fileCode 文件信息ID
     * @return 结果
     */
    public int deleteHmpFileInfoById(String fileCode);
}

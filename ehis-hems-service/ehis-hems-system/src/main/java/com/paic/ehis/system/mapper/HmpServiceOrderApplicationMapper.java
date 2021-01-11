package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.HmpServiceOrderApplication;

/**
 * 服务申请信息Mapper接口
 * 
 * @author sino
 * @date 2020-11-17
 */
public interface HmpServiceOrderApplicationMapper 
{
    /**
     * 查询服务申请信息
     * 
     * @param applicationcode 服务申请信息ID
     * @return 服务申请信息
     */
    public HmpServiceOrderApplication selectHmpServiceOrderApplicationById(String applicationcode);

    /**
     * 查询服务申请信息列表
     * 
     * @param hmpServiceOrderApplication 服务申请信息
     * @return 服务申请信息集合
     */
    public List<HmpServiceOrderApplication> selectHmpServiceOrderApplicationList(HmpServiceOrderApplication hmpServiceOrderApplication);

    /**
     * 新增服务申请信息
     * 
     * @param hmpServiceOrderApplication 服务申请信息
     * @return 结果
     */
    public int insertHmpServiceOrderApplication(HmpServiceOrderApplication hmpServiceOrderApplication);

    /**
     * 修改服务申请信息
     * 
     * @param hmpServiceOrderApplication 服务申请信息
     * @return 结果
     */
    public int updateHmpServiceOrderApplication(HmpServiceOrderApplication hmpServiceOrderApplication);

    /**
     * 删除服务申请信息
     * 
     * @param applicationcode 服务申请信息ID
     * @return 结果
     */
    public int deleteHmpServiceOrderApplicationById(String applicationcode);

    /**
     * 批量删除服务申请信息
     * 
     * @param applicationcodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpServiceOrderApplicationByIds(String[] applicationcodes);

    /**
     * 根据工单编号查询出服务申请信息
     * @param ordercode
     * @return
     */
    HmpServiceOrderApplication selectHmpServiceOrderAppByOrdercode(String ordercode);
}

package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.CodeEnum;

import java.util.List;


/**
 * 业务码 Mapper接口
 * 
 * @author sino
 * @date 2021-02-23
 */
public interface CodeEnumMapper 
{
    /**
     * 查询业务码 
     * 
     * @param codeId 业务码 ID
     * @return 业务码 
     */
    public CodeEnum selectCodeEnumById(Long codeId);

    /**
     * 查询业务码 列表
     * 
     * @param codeEnum 业务码 
     * @return 业务码 集合
     */
    public List<CodeEnum> selectCodeEnumList(CodeEnum codeEnum);

    public List<CodeEnum> selectComplaintBusiness(CodeEnum codeEnum);

    public List<CodeEnum> selectInsuranceSource(CodeEnum codeEnum);

    /**
     * 新增业务码 
     * 
     * @param codeEnum 业务码 
     * @return 结果
     */
    public int insertCodeEnum(CodeEnum codeEnum);

    /**
     * 修改业务码 
     * 
     * @param codeEnum 业务码 
     * @return 结果
     */
    public int updateCodeEnum(CodeEnum codeEnum);

    /**
     * 删除业务码 
     * 
     * @param codeId 业务码 ID
     * @return 结果
     */
    public int deleteCodeEnumById(Long codeId);

    /**
     * 批量删除业务码 
     * 
     * @param codeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCodeEnumByIds(Long[] codeIds);
}

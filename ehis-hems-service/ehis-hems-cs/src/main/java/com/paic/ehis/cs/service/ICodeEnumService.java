package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.CodeEnum;
import com.paic.ehis.cs.domain.dto.CodeEnumDTO;
import com.paic.ehis.cs.domain.vo.CodeEnumVo;

import java.util.List;


/**
 * 业务码 Service接口
 * 
 * @author sino
 * @date 2021-02-23
 */
public interface ICodeEnumService 
{

    public List<CodeEnumVo> updateCodeEnumVo(List<CodeEnumVo> list);

    public List<CodeEnumVo> selectCodeEnumVo(CodeEnumDTO codeEnumDTO);
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
     * 批量删除业务码 
     * 
     * @param codeIds 需要删除的业务码 ID
     * @return 结果
     */
    public int deleteCodeEnumByIds(Long[] codeIds);

    /**
     * 删除业务码 信息
     * 
     * @param codeId 业务码 ID
     * @return 结果
     */
    public int deleteCodeEnumById(Long codeId);


}

package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.CodeDict;
import com.paic.ehis.cs.domain.dto.CodeDictDTO;
import com.paic.ehis.cs.domain.vo.CodeDictVo;

import java.util.List;


/**
 * 业务码 Service接口
 * 
 * @author sino
 * @date 2021-02-27
 */
public interface ICodeDictService 
{
    /**
     * 查询业务码 
     * 
     * @param codeType 业务码 ID
     * @return 业务码 
     */
    public CodeDict selectCodeDictById(String codeType);

    public List<CodeDict> selectClassifyLevel2(CodeDict codeDict);

    public List<CodeDict> selectReasonLevel2(CodeDict codeDict);

    public List<CodeDict> selectReasonLevel3(CodeDict codeDict);

    public List<CodeDictVo> updateCodeDictVo(List<CodeDictVo> list);

    public List<CodeDictVo> selectCodeEnumVo(CodeDictDTO codeEnumDTO);

    /**
     * 查询业务码 列表
     * 
     * @param codeDict 业务码 
     * @return 业务码 集合
     */
    public List<CodeDict> selectCodeDictList(CodeDict codeDict);

    public List<CodeDictVo> selectCodeDictVoList(CodeDictDTO codeDictDTO);

    /**
     * 新增业务码 
     * 
     * @param codeDict 业务码 
     * @return 结果
     */
    public int insertCodeDict(CodeDict codeDict);

    /**
     * 修改业务码 
     * 
     * @param codeDict 业务码 
     * @return 结果
     */
    public int updateCodeDict(CodeDict codeDict);

    /**
     * 批量删除业务码 
     * 
     * @param codeTypes 需要删除的业务码 ID
     * @return 结果
     */
    public int deleteCodeDictByIds(String[] codeTypes);

    /**
     * 删除业务码 信息
     * 
     * @param codeType 业务码 ID
     * @return 结果
     */
    public int deleteCodeDictById(String codeType);
}

package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.CodeDict;
import com.paic.ehis.cs.domain.dto.CodeDictDTO;
import com.paic.ehis.cs.domain.vo.CodeDictVo;
import com.paic.ehis.cs.domain.vo.CodeEnumVo1;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 业务码 Mapper接口
 * 
 * @author sino
 * @date 2021-02-27
 */
@Component
public interface CodeDictMapper 
{
    /**
     * 查询业务码 
     * 
     * @param codeType 业务码 ID
     * @return 业务码 
     */
    public CodeDict selectCodeDictById(String codeType);

    public List<CodeDictVo> selectCodeDictVoList(CodeDictDTO codeDictDTO);

    /**
     * 查询业务码 列表
     * 
     * @param codeDict 业务码 
     * @return 业务码 集合
     */
    public List<CodeDict> selectCodeDictList(CodeDict codeDict);

    public List<CodeDict> selectComplaintBusiness(CodeDict codeDict);

    public List<CodeDict> selectInsuranceSource(CodeDict codeDict);

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
     * 删除业务码 
     * 
     * @param codeType 业务码 ID
     * @return 结果
     */
    public int deleteCodeDictById(String codeType);

    /**
     * 批量删除业务码 
     * 
     * @param codeTypes 需要删除的数据ID
     * @return 结果
     */
    public int deleteCodeDictByIds(String[] codeTypes);


    public List<CodeDict> selectInCodeDict(CodeEnumVo1 codeEnumVo1);

    public int insertCodeDictList(List<CodeDict> list);

    public int updateCodeDictList(List<CodeDict> list);
}

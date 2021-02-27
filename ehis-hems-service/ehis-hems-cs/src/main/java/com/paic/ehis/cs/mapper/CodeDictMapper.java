package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.CodeDict;
import com.paic.ehis.cs.domain.dto.CodeDictDTO;
import com.paic.ehis.cs.domain.vo.CodeDictVo;

import java.util.List;


/**
 * 业务码 Mapper接口
 * 
 * @author sino
 * @date 2021-02-23
 */
public interface CodeDictMapper
{

    public List<CodeDictVo> selectCodeEnumVoList(CodeDictDTO codeDictDTO);

    public int insertCodeEnumInfo(List<CodeDict> list);

    public int updateCodeEnumInfo(List<CodeDict> list);

}

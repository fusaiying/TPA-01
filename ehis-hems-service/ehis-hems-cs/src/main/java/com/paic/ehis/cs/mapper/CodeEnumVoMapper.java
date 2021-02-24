package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.CodeEnum;
import com.paic.ehis.cs.domain.dto.CodeEnumDTO;
import com.paic.ehis.cs.domain.vo.CodeEnumVo;

import java.util.List;

public interface CodeEnumVoMapper {

    public List<CodeEnumVo> selectCodeEnumVoList(CodeEnumDTO codeEnumDTO);

    public int insertCodeEnumInfo(List<CodeEnum> list);

    public int updateCodeEnumInfo(List<CodeEnum> list);
}

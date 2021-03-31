package com.paic.ehis.cs.service.impl;

import com.paic.ehis.cs.domain.CodeDict;
import com.paic.ehis.cs.domain.dto.CodeDictDTO;
import com.paic.ehis.cs.domain.vo.CodeDictVo;
import com.paic.ehis.cs.domain.vo.CodeEnumVo1;
import com.paic.ehis.cs.mapper.CodeDictMapper;
import com.paic.ehis.cs.service.ICodeDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 业务码 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-27
 */
@Service
public class CodeDictServiceImpl implements ICodeDictService
{
    @Autowired
    private CodeDictMapper codeDictMapper;



    /**
     * 查询业务码
     *
     * @param codeType 业务码 ID
     * @return 业务码
     */
    @Override
    public CodeDict selectCodeDictById(String codeType)
    {
        return codeDictMapper.selectCodeDictById(codeType);
    }

    @Override
    public List<CodeDict> selectClassifyLevel2(CodeDict codeDict) {
        codeDict.setCodeType("cs_classify_level2");
        List<CodeDict> codeDicts=codeDictMapper.selectNext(codeDict);
        return codeDicts;
    }

    @Override
    public List<CodeDict> selectReasonLevel2(CodeDict codeDict) {
        codeDict.setCodeType("cs_reason_level2");
        List<CodeDict> codeDicts=codeDictMapper.selectNext(codeDict);
        return codeDicts;
    }

    @Override
    public List<CodeDict> selectReasonLevel3(CodeDict codeDict) {
        codeDict.setCodeType("cs_reason_level3");
        List<CodeDict> codeDicts=codeDictMapper.selectNext(codeDict);
        return codeDicts;
    }

    @Override
    public List<CodeDictVo> updateCodeDictVo(List<CodeDictVo> list) {
        //一级
        List<CodeDict> parList = new ArrayList<>();
        //二级
        List<CodeDict> subList = new ArrayList<>();

        for (int i = 0; i < list.size() - 1; i++) {
            CodeDict parCodeEnum = new CodeDict();
            parCodeEnum.setCodeType("cs_complaint_business_item");
            parCodeEnum.setCode(list.get(i).getComplaintBusinessCode());
            parCodeEnum.setCodeName(list.get(i).getComplaintBusinessName());

            CodeDict subCodeEnum = new CodeDict();
            subCodeEnum.setCodeType("cs_insurance_source");
            subCodeEnum.setCode(list.get(i).getInsuranceSourceCode());
            subCodeEnum.setCodeName(list.get(i).getInsuranceSourceName());
            subCodeEnum.setParentCode(list.get(i).getComplaintBusinessCode());

            if (!parList.contains(parCodeEnum)) {
                parList.add(parCodeEnum);
            }
            if (!subList.contains(subCodeEnum)) {
                subList.add(subCodeEnum);
            }
        }

        CodeEnumVo1 codeEnumVo1 = new CodeEnumVo1();
        codeEnumVo1.setCodeType("cs_complaint_business_item");
        codeEnumVo1.setVoList(parList);
        List<CodeDict> codeEnumListUpdate = codeDictMapper.selectInCodeDict(codeEnumVo1);
        List<CodeDict> codeEnumListInsert = new ArrayList<>();

        for (int i = 0; i < parList.size(); i++) {
            CodeDict tempEnum = parList.get(i);
            if (codeEnumListUpdate != null) {
                for (int j = 0; j < codeEnumListUpdate.size(); j++) {
                    String inParCode = codeEnumListUpdate.get(j).getCode();
                    if (tempEnum.getCode().equals(inParCode)) {
                        codeEnumListUpdate.get(j).setCodeName(tempEnum.getCodeName());
                    } else {
                        codeEnumListInsert.add(tempEnum);
                    }
                }
            }
        }
        codeDictMapper.insertCodeDictList(codeEnumListInsert);
        codeDictMapper.updateCodeDictList(codeEnumListUpdate);

            CodeEnumVo1 codeEnumVo11 = new CodeEnumVo1();
            codeEnumVo11.setCodeType("cs_insurance_source");
            codeEnumVo11.setVoList(subList);
            List<CodeDict> codeEnumListUpdate1 = codeDictMapper.selectInCodeDict(codeEnumVo11);
            List<CodeDict> codeEnumListInsert1 = new ArrayList<>();
            for (int i = 0; i < subList.size(); i++) {
                CodeDict tempEnum = subList.get(i);
                if (codeEnumListUpdate1 != null) {
                    for (int j = 0; j < codeEnumListUpdate1.size(); j++) {
                        String inParCode = codeEnumListUpdate1.get(j).getCode();
                        if (tempEnum.getCode().equals(inParCode)) {
                            codeEnumListUpdate1.get(j).setCodeName(tempEnum.getCodeName());
                            codeEnumListUpdate1.get(j).setParentCode(tempEnum.getParentCode());
                        } else {
                            codeEnumListInsert1.add(tempEnum);
                        }
                    }
                }
            }

        codeDictMapper.insertCodeDictList(codeEnumListInsert1);
        codeDictMapper.updateCodeDictList(codeEnumListUpdate1);

        return list;
    }

    @Override
    public List<CodeDictVo> selectCodeEnumVo(CodeDictDTO codeEnumDTO) {
        codeEnumDTO.setInsuranceSourceType("cs_insurance_source");
        codeEnumDTO.setComplaintBusinessType("cs_complaint_business_item");
        return codeDictMapper.selectCodeDictVoList(codeEnumDTO);
    }

    /**
     * 查询业务码 列表
     *
     * @param codeDict 业务码
     * @return 业务码
     */
    @Override
    public List<CodeDict> selectCodeDictList(CodeDict codeDict)
    {
        return codeDictMapper.selectCodeDictList(codeDict);
    }

    /**
     * 查询业务码 列表
     *
     * @param codeDict 业务码
     * @return 业务码
     */
    @Override
    public List<CodeDict> selectCodeDictList2(CodeDict codeDict)
    {
        return codeDictMapper.selectCodeDictList2(codeDict);
    }

    @Override
    public List<CodeDictVo> selectCodeDictVoList(CodeDictDTO codeDictDTO)
    {
        return codeDictMapper.selectCodeDictVoList(codeDictDTO);
    }

    /**
     * 新增业务码 
     * 
     * @param codeDict 业务码 
     * @return 结果
     */
    @Override
    public int insertCodeDict(CodeDict codeDict)
    {
        return codeDictMapper.insertCodeDict(codeDict);
    }

    /**
     * 修改业务码
     *
     * @param codeDict 业务码
     * @return 结果
     */
    @Override
    public int updateCodeDict(CodeDict codeDict)
    {
        return codeDictMapper.updateCodeDict(codeDict);
    }

    /**
     * 批量删除业务码
     *
     * @param codeTypes 需要删除的业务码 ID
     * @return 结果
     */
    @Override
    public int deleteCodeDictByIds(String[] codeTypes)
    {
        return codeDictMapper.deleteCodeDictByIds(codeTypes);
    }

    /**
     * 删除业务码 信息
     *
     * @param codeType 业务码 ID
     * @return 结果
     */
    @Override
    public int deleteCodeDictById(String codeType)
    {
        return codeDictMapper.deleteCodeDictById(codeType);
    }
}

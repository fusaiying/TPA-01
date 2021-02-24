package com.paic.ehis.cs.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.paic.ehis.cs.domain.CodeEnum;
import com.paic.ehis.cs.domain.dto.CodeEnumDTO;
import com.paic.ehis.cs.domain.vo.CodeEnumVo;
import com.paic.ehis.cs.mapper.CodeEnumMapper;
import com.paic.ehis.cs.mapper.CodeEnumVoMapper;
import com.paic.ehis.cs.service.ICodeEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 业务码 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-23
 */
@Service
public class CodeEnumServiceImpl implements ICodeEnumService
{
    @Autowired
    private CodeEnumMapper codeEnumMapper;

    @Autowired
    private CodeEnumVoMapper codeEnumVoMapper;

    /**
     * 1.处理一级
     *      1.根据类型
     *
     * 2.处理二级
     */
    @Override
    public List<CodeEnumVo> updateCodeEnumVo(List<CodeEnumVo> list){
        CodeEnum codeEnum=new CodeEnum();
        codeEnum.setCodeType("cs_complaint_business_item");
        //库里的code值
        List codeEnum1 =codeEnumMapper.selectComplaintBusiness(codeEnum);
        List list1=new ArrayList();
        List list2=new ArrayList();
        for (int i=0;i<list.size()-1;i++){
            if (codeEnum1.contains(list.get(i).getComplaintBusinessCode())){
                list1.add(list.get(i));
            }else {
                list2.add(list.get(i));
            }
        }
        codeEnumVoMapper.updateCodeEnumInfo(list1);
        codeEnumVoMapper.insertCodeEnumInfo(list2);
        codeEnum.setCodeType("cs_insurance_source");
        //库里的code值
        List codeEnum2 =codeEnumMapper.selectComplaintBusiness(codeEnum);
        for (int i=0;i<list.size()-1;i++){
            if (codeEnum2.contains(list.get(i).getInsuranceSourceCode())){
                if (codeEnum1.contains(list.get(i).getInsuranceSourceCode())){
                    list1.add(list.get(i));
                }else {
                    list2.add(list.get(i));
                }
            }
        }
        codeEnumVoMapper.updateCodeEnumInfo(list1);
        codeEnumVoMapper.insertCodeEnumInfo(list2);
        CodeEnumDTO codeEnumDTO=new CodeEnumDTO();
        codeEnumDTO.setComplaintBusinessType("cs_complaint_business_item");
        return codeEnumVoMapper.selectCodeEnumVoList(codeEnumDTO);
    }

    public List<CodeEnumVo> selectCodeEnumVo(CodeEnumDTO codeEnumDTO){
        codeEnumDTO.setComplaintBusinessType("cs_complaint_business_item");
        return codeEnumVoMapper.selectCodeEnumVoList(codeEnumDTO);
    }



    /**
     * 查询业务码 
     * 
     * @param codeId 业务码 ID
     * @return 业务码 
     */
    @Override
    public CodeEnum selectCodeEnumById(Long codeId)
    {
        return codeEnumMapper.selectCodeEnumById(codeId);
    }

    /**
     * 查询业务码 列表
     * 
     * @param codeEnum 业务码 
     * @return 业务码 
     */
    @Override
    public List<CodeEnum> selectCodeEnumList(CodeEnum codeEnum)
    {
        return codeEnumMapper.selectCodeEnumList(codeEnum);
    }

    /**
     * 新增业务码 
     * 
     * @param codeEnum 业务码 
     * @return 结果
     */
    @Override
    public int insertCodeEnum(CodeEnum codeEnum)
    {
        return codeEnumMapper.insertCodeEnum(codeEnum);
    }

    /**
     * 修改业务码 
     * 
     * @param codeEnum 业务码 
     * @return 结果
     */
    @Override
    public int updateCodeEnum(CodeEnum codeEnum)
    {
        return codeEnumMapper.updateCodeEnum(codeEnum);
    }

    /**
     * 批量删除业务码 
     * 
     * @param codeIds 需要删除的业务码 ID
     * @return 结果
     */
    @Override
    public int deleteCodeEnumByIds(Long[] codeIds)
    {
        return codeEnumMapper.deleteCodeEnumByIds(codeIds);
    }

    /**
     * 删除业务码 信息
     * 
     * @param codeId 业务码 ID
     * @return 结果
     */
    @Override
    public int deleteCodeEnumById(Long codeId)
    {
        return codeEnumMapper.deleteCodeEnumById(codeId);
    }
}

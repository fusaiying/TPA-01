package com.paic.ehis.cs.service.impl;

import com.paic.ehis.cs.domain.CodeDict;
import com.paic.ehis.cs.domain.CodeDictTemporary;
import com.paic.ehis.cs.domain.dto.CodeDictDTO;
import com.paic.ehis.cs.domain.vo.CodeDictVo;
import com.paic.ehis.cs.domain.vo.CodeEnumVo1;
import com.paic.ehis.cs.mapper.CodeDictMapper;
import com.paic.ehis.cs.mapper.CodeDictTemporaryMapper;
import com.paic.ehis.cs.service.ICodeDictTemporaryService;
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
public class CodeDictTemporaryServiceImpl implements ICodeDictTemporaryService
{
    @Autowired
    private CodeDictTemporaryMapper codeDictTemporaryMapper;


    /**
     * 插入数据
     *
     * @return 业务码 
     */
    @Override
    public int insertCodeDictTemporary(CodeDictTemporary tCodeDictTemporary)
    {
        return codeDictTemporaryMapper.insertCodeDictTemporary(tCodeDictTemporary);
    }

    /*投保来源ID不能为空*/
    @Override
    public int updateInsuranceIDNotNUll(String batchno){
        return codeDictTemporaryMapper.updateInsuranceIDNotNUll(batchno);
    }

    /*投保来源名称不能为空*/
    @Override
    public int updateInsuranceNameNotNUll(String batchno){
        return codeDictTemporaryMapper.updateInsuranceNameNotNUll(batchno);
    }

    /*投保业务类别ID不能为空*/
    @Override
    public int updateBusinessIDNotNUll(String batchno){
        return codeDictTemporaryMapper.updateBusinessIDNotNUll(batchno);
    }

    /*投保业务类别名称不能为空*/
    @Override
    public int updateBusinessNameNotNUll(String batchno){
        return codeDictTemporaryMapper.updateBusinessNameNotNUll(batchno);
    }

    /*投保来源ID重复 ID相同*/
    @Override
    public int updateInsuranceIDRE(String batchno){
        return codeDictTemporaryMapper.updateInsuranceIDRE(batchno);
    }

    /*投保业务类别 ID相同  name不同 临时表数据对比*/
    @Override
    public int updateBusinessIDRE(String batchno){
        return codeDictTemporaryMapper.updateBusinessIDRE(batchno);
    }

    /*投保业务类别 ID相同  name不同 临时表和主表数据对比*/
    public int updateBusinessIDRE2(String batchno){
        return codeDictTemporaryMapper.updateBusinessIDRE2(batchno);
    }

    /*投保业务分类错误 则投保来源也错误*/
    @Override
    public int updateBusinessToIns(String batchno){
        return codeDictTemporaryMapper.updateBusinessToIns(batchno);
    }

    /*成功更新状态 变成01*/
    @Override
    public int updateSuccess(String batchno){
        return codeDictTemporaryMapper.updateSuccess(batchno);
    }

    /*失败更新状态 变更09*/
    @Override
    public int updateFailure(String batchno){
        return codeDictTemporaryMapper.updateFailure(batchno);
    }

    /*查询数据*/
    @Override
    public List<CodeDictTemporary> selectCodeDictTemporaryList(CodeDictTemporary tCodeDictTemporary){
        return codeDictTemporaryMapper.selectCodeDictTemporaryList(tCodeDictTemporary);
    }

}

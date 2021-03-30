package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.CodeDict;
import com.paic.ehis.cs.domain.CodeDictTemporary;
import com.paic.ehis.cs.domain.dto.CodeDictDTO;
import com.paic.ehis.cs.domain.vo.CodeDictVo;

import java.util.List;


/**
 * 业务码 Service接口
 * 
 * @author sino
 * @date 2021-02-27
 */
public interface ICodeDictTemporaryService
{
    /*插入数据*/
    public int insertCodeDictTemporary(CodeDictTemporary tCodeDictTemporary);

    /*投保来源ID不能为空*/
    public int updateInsuranceIDNotNUll(String batchno);

    /*投保来源名称不能为空*/
    public int updateInsuranceNameNotNUll(String batchno);

    /*投保业务类别ID不能为空*/
    public int updateBusinessIDNotNUll(String batchno);

    /*投保业务类别名称不能为空*/
    public int updateBusinessNameNotNUll(String batchno);

    /*投保来源ID重复 ID相同*/
    public int updateInsuranceIDRE(String batchno);

    /*投保业务类别 ID相同  name不同 临时表数据对比*/
    public int updateBusinessIDRE(String batchno);

    /*投保业务类别 ID相同  name不同 临时表和主表数据对比*/
    public int updateBusinessIDRE2(String batchno);

    /*投保业务分类错误 则投保来源也错误*/
    public int updateBusinessToIns(String batchno);

    /*成功更新状态 变成01*/
    public int updateSuccess(String batchno);

    /*失败更新状态 变更09*/
    public int updateFailure(String batchno);

    /*查询数据*/
    public List<CodeDictTemporary> selectCodeDictTemporaryList(CodeDictTemporary tCodeDictTemporary);
}

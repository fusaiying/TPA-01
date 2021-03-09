package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseIssuingcompany;
import com.paic.ehis.base.domain.dto.IssuingAndCompanyDTO;
import com.paic.ehis.base.domain.vo.IssuingBaseVO;
import com.paic.ehis.base.domain.vo.IssuingCompanyVo;

import java.util.List;

/**
 * 出单公司信息 Service接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface IBaseIssuingcompanyService 
{
    /**
     * 查询出单公司信息 
     * 
     * @param companyCode 出单公司信息 ID
     * @return 出单公司信息 
     */
    public IssuingBaseVO selectBaseIssuingcompanyById(String companyCode);

    /**
     * 查询出单公司信息 列表
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 出单公司信息 集合
     */
    public List<BaseIssuingcompany> selectBaseIssuingcompanyList(BaseIssuingcompany baseIssuingcompany);

    /**
     * 新增出单公司信息 
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 结果
     */
    public int insertBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany);

    /**
     * 修改出单公司信息 
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 结果
     */
    public int updateBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany);

    /**
     * 批量删除出单公司信息 
     * 
     * @param companyCodes 需要删除的出单公司信息 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyByIds(String[] companyCodes);

    /**
     * 删除出单公司信息 信息
     * 
     * @param companyCode 出单公司信息 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyById(String companyCode);

    /**
     * 新增+修改
     * 出单公司信息 + 开票信息
     * @param issuingAndCompanyDTO
     * @return
     */
    public IssuingCompanyVo insertBaseIssuingcompanyRest(IssuingAndCompanyDTO issuingAndCompanyDTO);

    /**
     * 出单公司简称校验
     * @param simplename
     * @return
     */
    public List<BaseIssuingcompany> selectBaseIssuingcompanyBySimplename(String simplename);

    /**
     * 出单公司校验
     * @param companyname
     * @return
     */
    public List<BaseIssuingcompany> selectBaseIssuingcompanyByCompanyname(String companyname);
}

package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.HmpContract;
import com.paic.ehis.base.domain.HmpServOpera;

import java.util.List;

/**
 * 供应商合约Mapper接口
 * 
 * @author sino
 * @date 2020-09-23
 */
public interface HmpContractMapper
{
    /**
     * 查询供应商合约
     * 
     * @param contractno 供应商合约ID
     * @return 供应商合约
     */
    public HmpContract selectHmpContractById(String contractno);

    /**
     * 查询供应商合约列表
     * 
     * @param hmpContract 供应商合约
     * @return 供应商合约集合
     */
    public List<HmpContract> selectHmpContractList(HmpContract hmpContract);

    /**
     * 新增供应商合约
     * 
     * @param hmpContract 供应商合约
     * @return 结果
     */
    public int insertHmpContract(HmpContract hmpContract);

    /**
     * 修改供应商合约
     * 
     * @param hmpContract 供应商合约
     * @return 结果
     */
    public int updateHmpContract(HmpContract hmpContract);

    /**
     * 删除供应商合约
     * 
     * @param contractno 供应商合约ID
     * @return 结果
     */
    public int deleteHmpContractById(String contractno);

    /**
     * 批量删除供应商合约
     * 
     * @param contractnos 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpContractByIds(String[] contractnos);

    /**
     * 查询合约对象编码
     * @return  合约对象编码和名称的map集合
     */
    public List<HmpServOpera> getContractObj();

    public List<HmpContract> getExpireContract(String nowday);

    /**
     * 根据合约名称查合约信息
     * @param contractName
     * @return
     */
    public HmpContract getContractByName(String contractName);
}

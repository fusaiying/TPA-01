package com.paic.ehis.base.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.paic.ehis.base.mapper.HmpContractMapper;
import com.paic.ehis.base.service.IHmpContractService;
import com.paic.ehis.base.domain.HmpServOpera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.base.domain.HmpContract;
import org.springframework.transaction.annotation.Transactional;

/**
 * 供应商合约Service业务层处理
 * 
 * @author sino
 * @date 2020-09-23
 */
@Service
public class HmpContractServiceImpl implements IHmpContractService
{
    @Autowired
    private HmpContractMapper hmpContractMapper;

    // 日期格式化 获取当前日期
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 查询供应商合约
     * 
     * @param contractno 供应商合约ID
     * @return 供应商合约
     */
    @Override
    public HmpContract selectHmpContractById(String contractno)
    {
        return hmpContractMapper.selectHmpContractById(contractno);
    }

    /**
     * 查询供应商合约列表
     * 
     * @param hmpContract 供应商合约
     * @return 供应商合约
     */
    @Override
    public List<HmpContract> selectHmpContractList(HmpContract hmpContract)
    {
        return hmpContractMapper.selectHmpContractList(hmpContract);
    }

    /**
     * 新增供应商合约
     * 
     * @param hmpContract 供应商合约
     * @return 结果
     */
    @Override
    public int insertHmpContract(HmpContract hmpContract)
    {
        return hmpContractMapper.insertHmpContract(hmpContract);
    }

    /**
     * 修改供应商合约
     * 
     * @param hmpContract 供应商合约
     * @return 结果
     */
    @Override
    public int updateHmpContract(HmpContract hmpContract)
    {
        return hmpContractMapper.updateHmpContract(hmpContract);
    }

    /**
     * 批量删除供应商合约
     * 
     * @param contractnos 需要删除的供应商合约ID
     * @return 结果
     */
    @Override
    public int deleteHmpContractByIds(String[] contractnos)
    {
        return hmpContractMapper.deleteHmpContractByIds(contractnos);
    }

    /**
     * 删除供应商合约信息
     * 
     * @param contractno 供应商合约ID
     * @return 结果
     */
    @Override
    public int deleteHmpContractById(String contractno)
    {
        return hmpContractMapper.deleteHmpContractById(contractno);
    }

    @Override
    public List<HmpServOpera> getContractObj() {
        return hmpContractMapper.getContractObj();
    }

    @Override
    @Transactional
    public List<HmpContract> getExpireContract() {
        // 获取今日日期
        Date day = new Date();
        String nowday = df.format(day);
        // 查询即将过期的合约
        List<HmpContract> result = hmpContractMapper.getExpireContract(nowday);
        result.forEach(e ->{
            HmpContract hmpContract = new HmpContract();
            System.out.println("e.con = " + e.getContractno());
            System.out.println("e = " + e.getContractname());
            // 提醒过后修改 alternatefield1 的值为当日日期
            hmpContract.setAlternatefield1(nowday);
            hmpContract.setContractno(e.getContractno());
            hmpContractMapper.updateHmpContract(hmpContract);
        });
        return result;
    }

    @Override
    public HmpContract getContractByName(String contractName) {
        return hmpContractMapper.getContractByName(contractName);
    }
}

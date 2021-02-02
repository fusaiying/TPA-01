package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.base.base.utility.Dateutils;
import com.paic.ehis.base.domain.BaseSupplierContract;
import com.paic.ehis.base.mapper.BaseContractServiceMapper;
import com.paic.ehis.base.mapper.BaseSupplierContractMapper;
import com.paic.ehis.base.service.IBaseSupplierContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * base_supplier_contract（供应商合约）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-31
 */
@Service
public class BaseSupplierContractServiceImpl implements IBaseSupplierContractService 
{
    @Autowired
    private BaseSupplierContractMapper baseSupplierContractMapper;

    @Autowired
    private BaseContractServiceMapper baseContractServiceMapper;

    /**
     * 查询base_supplier_contract（供应商合约）
     * 
     * @param contractNo base_supplier_contract（供应商合约）ID
     * @return base_supplier_contract（供应商合约）
     */
    @Override
    public BaseSupplierContract selectBaseSupplierContractById(String contractNo)
    {
        return baseSupplierContractMapper.selectBaseSupplierContractById(contractNo);
    }

    /**
     * 查询base_supplier_contract（供应商合约）列表
     * 
     * @param baseSupplierContract base_supplier_contract（供应商合约）
     * @return base_supplier_contract（供应商合约）
     */
    @Override
    public List<BaseSupplierContract> selectBaseSupplierContractList(BaseSupplierContract baseSupplierContract)
    {
        return baseSupplierContractMapper.selectBaseSupplierContractList(baseSupplierContract);
    }

    /*若供应商下已签订合约，合约列表中供应商对应的合约编码、合约名称、合约起止日期均有值，
     *   且当供应商下存在多条合约信息时仅显示该供应商下合约终止日期最晚的一条合约信息（即供应商下创建时间最晚的一条合约信息）
     */
    @Override
    public  List<BaseSupplierContract> selectBaseSupplierLast(String servcomno) {
        return baseSupplierContractMapper.selectBaseSupplierLast(servcomno);
    }

    /*
     * 供应商合约管理主查询页面需默认显示截止当前时间合约签约时间在三个月内且合约状态为“有效”的数据
     */
    @Override
    public List<BaseSupplierContract> selectBaseSupplierMonth(BaseSupplierContract baseSupplierContract) throws Exception {
    Map map = Dateutils.getCurrontTime();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    baseSupplierContract.setDBefore(sdf.parse(String.valueOf(map.get("defaultStartDate"))));
    baseSupplierContract.setDNow(sdf.parse(String.valueOf(map.get("defaultEndDate"))));
    return baseSupplierContractMapper.selectBaseSupplierMonth(baseSupplierContract);
    }

   /**
    * 根据服务机构id查询合约信息
    */
    @Override
    public List<BaseSupplierContract> selectBaseproviderCode(String providerCode) {
        return baseSupplierContractMapper.selectBaseproviderCode(providerCode);
    }


    /**
     * 新增base_supplier_contract（供应商合约/服务商合约）
     * 
     * @param baseSupplierContract base_supplier_contract（供应商合约）
     * @return 结果
     */
    @Override
    public BaseSupplierContract  insertBaseSupplierContract(BaseSupplierContract baseSupplierContract) {

        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();

        baseSupplierContract.setSerialNo(PubFun.createMySqlMaxNoUseCache("BaseSupplierContractSerialNo", 0, 11));
      //  baseSupplierContract.setBussinessStatus("01");//状态
        baseSupplierContract.setStatus("Y");
        baseSupplierContract.setCreateBy(username);
        baseSupplierContract.setUpdateBy(username);
        baseSupplierContract.setCreateTime(nowDate);
        baseSupplierContract.setUpdateTime(nowDate);

        String flag = baseSupplierContract.getFlag();
        if ("01".equals(flag)) {//判断是供应商合约编码
            String contractNo = "SPC" + PubFun.createMySqlMaxNoUseCache("BaseSupplierContract", 0, 7);
            baseSupplierContract.setContractNo(contractNo);
            baseSupplierContractMapper.insertBaseSupplierContract(baseSupplierContract);

            //需要将 供应商服务项目 临时数据更新挂在该合约下
            if(StringUtils.isNotBlank(baseSupplierContract.getConSerId())){
                Map<String,Object> map = new HashMap<>();
                map.put("preContractNo",baseSupplierContract.getConSerId());
                map.put("contractNo",contractNo);
                baseContractServiceMapper.updateBaseContractServiceByContractNo(map);
            }
            return baseSupplierContract;
        }
        else if("02".equals(flag)) {//判断是服务商合约编码
            baseSupplierContractMapper.insertBaseSupplierContract(baseSupplierContract);
        }
        return  baseSupplierContract;
    }

    @Override
    public int insertBaseSupplierContractNew(String providerCode){
        int count = 0;
        List<BaseSupplierContract> baseSupplierContracts = baseSupplierContractMapper.selectSupplierContractByCode(providerCode);
        List<BaseSupplierContract> baseSupplierContractsNew = baseSupplierContractMapper.selectBaseproviderCode(providerCode);
        if(baseSupplierContractsNew.isEmpty()){ //存在则修改已存在的数据
            baseSupplierContractMapper.updateBaseContactsByCodeNew(providerCode);
        }
        for(BaseSupplierContract baseSupplierContract :baseSupplierContracts){
            baseSupplierContract.setCreateTime(DateUtils.getNowDate());
            int i =baseSupplierContractMapper.insertBaseSupplierContract(baseSupplierContract);
            count += i;
        }
        return count;
    }
    /**
     * 修改base_supplier_contract（供应商合约）
     * 
     * @param baseSupplierContract base_supplier_contract（供应商合约）
     * @return 结果
     */
    @Override
    public int updateBaseSupplierContract(BaseSupplierContract baseSupplierContract)
    {
        baseSupplierContract.setUpdateBy(SecurityUtils.getUsername());
        baseSupplierContract.setUpdateTime(new Date());
        baseSupplierContract.setUpdateTime(DateUtils.getNowDate());
        return baseSupplierContractMapper.updateBaseSupplierContract(baseSupplierContract);
    }

    /**
     * 批量删除base_supplier_contract（供应商合约）
     * 
     * @param contractNos 需要删除的base_supplier_contract（供应商合约）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierContractByIds(String[] contractNos)
    {
        return baseSupplierContractMapper.deleteBaseSupplierContractByIds(contractNos);
    }

    /**
     * 删除base_supplier_contract（供应商合约）信息
     * 
     * @param contractNo base_supplier_contract（供应商合约）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierContractById(String contractNo)
    {
        return baseSupplierContractMapper.deleteBaseSupplierContractById(contractNo);
    }
}

package com.paic.ehis.finance.service.impl;

import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.common.core.exception.BaseException;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.system.api.domain.FinanceBorrowInfo;
import com.paic.ehis.finance.mapper.FinanceBorrowInfoMapper;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.domain.BaseProviderInfo;
import com.paic.ehis.finance.domain.ClaimBatch;
import com.paic.ehis.finance.domain.FinancePayInfo;
import com.paic.ehis.finance.domain.dto.TransferfailedDTO;
import com.paic.ehis.finance.domain.vo.TransferfailedVo;
import com.paic.ehis.finance.mapper.ClaimBatchMapper;
import com.paic.ehis.finance.mapper.FinancePayInfoMapper;
import com.paic.ehis.finance.service.IFinancePayInfoService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 支付信息Service业务层处理
 * 
 * @author sino
 * @date 2021-02-01
 */
@Service
public class FinancePayInfoServiceImpl implements IFinancePayInfoService 
{
    @Autowired
    private FinancePayInfoMapper financePayInfoMapper;

    @Autowired
    private GetProviderInfoService getProviderInfoService;

    @Autowired
    private ClaimBatchMapper claimBatchMapper;

    @Autowired
    private FinanceBorrowInfoMapper financeBorrowInfoMapper;

    /**
     * 查询支付信息
     * 
     * @param payId 支付信息ID
     * @return 支付信息
     */
    @Override
    public FinancePayInfo selectFinancePayInfoById(Long payId)
    {
        return financePayInfoMapper.selectFinancePayInfoById(payId);
    }

    /**
     * 查询支付信息列表
     * 
     * @param financePayInfo 支付信息
     * @return 支付信息
     */
    @Override
    public List<FinancePayInfo> selectFinancePayInfoList(FinancePayInfo financePayInfo)
    {
        return financePayInfoMapper.selectFinancePayInfoList(financePayInfo);
    }

    /**
     * 新增支付信息
     * 
     * @param financePayInfo 支付信息
     * @return 结果
     */
    @Override
    public int insertFinancePayInfo(FinancePayInfo financePayInfo)
    {
        financePayInfo.setCreateTime(DateUtils.getNowDate());
        return financePayInfoMapper.insertFinancePayInfo(financePayInfo);
    }

    /**
     * 修改支付信息
     * 
     * @param financePayInfo 支付信息
     * @return 结果
     */
    @Override
    public int updateFinancePayInfo(FinancePayInfo financePayInfo)
    {
        financePayInfo.setUpdateTime(DateUtils.getNowDate());
        return financePayInfoMapper.updateFinancePayInfo(financePayInfo);
    }

    /**
     * 批量删除支付信息
     * 
     * @param payIds 需要删除的支付信息ID
     * @return 结果
     */
    @Override
    public int deleteFinancePayInfoByIds(Long[] payIds)
    {
        return financePayInfoMapper.deleteFinancePayInfoByIds(payIds);
    }

    /**
     * 删除支付信息信息
     * 
     * @param payId 支付信息ID
     * @return 结果
     */
    @Override
    public int deleteFinancePayInfoById(Long payId)
    {
        return financePayInfoMapper.deleteFinancePayInfoById(payId);
    }

    /**
     * 查询转账失败处理工作池
     */
    @Override
    public List<TransferfailedVo> selectTransferfailedList(TransferfailedDTO transferfailedDTO) {
            List<TransferfailedVo> transferfailedVos = financePayInfoMapper.selectTransferfailedList(transferfailedDTO);
            List<TransferfailedVo> transferfailedVos1=new ArrayList<>();
            for(TransferfailedVo transferfailedVo1:transferfailedVos) {
                TransferfailedVo transferfailedVo=new TransferfailedVo();
                if(StringUtils.isNotEmpty(transferfailedVo1.getPayCurrency()) && transferfailedVo1.getPayCurrency().equals("CNY")){
                    if(null!=transferfailedVo1.getSumPayAmount()){
                        BigDecimal sumPayAmount= transferfailedVo1.getSumPayAmount();
                        transferfailedVo.setSumPayAmount(sumPayAmount);
                    }
                }else if (StringUtils.isNotEmpty(transferfailedVo1.getPayCurrency()) && !transferfailedVo1.getPayCurrency().equals("CNY")){
                    if(null!=transferfailedVo1.getSumPayAmountForeign()){
                        BigDecimal sumPayAmount= transferfailedVo1.getSumPayAmountForeign();
                        transferfailedVo.setSumPayAmount(sumPayAmount);
                    }
                }
                transferfailedVos1.add(transferfailedVo);
            }
        return transferfailedVos1;
    }

    /**
     * 更新转账失败处理工作池
     */
    @Override
    public TransferfailedVo updateTransferFailedInfo(String batchNo) {
            FinancePayInfo financePayInfo=financePayInfoMapper.selectFinancePayInfoBatchNo(batchNo);
            TransferfailedVo transferfailedVo=new TransferfailedVo();
            /*根据批次号查询医院信息*/
            ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(batchNo);
            //调接口所需的对象
            BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
            if(StringUtils.isNotNull(claimBatch)) {
                baseProviderInfo.setProviderCode(claimBatch.getHospitalcode());
            }
            //调用医院接口
            R<List<BaseProviderInfo>>  result =  getProviderInfoService.selectOrgInfo(baseProviderInfo);
            if (R.FAIL == result.getCode())
            {
                throw new BaseException(result.getMsg());
            }
            List<BaseProviderInfo> baseProviderList = result.getData();
            if(null != baseProviderList && !baseProviderList.isEmpty()) {
                BaseProviderInfo hospital = baseProviderList.get(0);
                BeanUtils.copyProperties(financePayInfo,transferfailedVo);
                transferfailedVo.setPayeeBank(hospital.getBankName());//开户行
                transferfailedVo.setAccName(hospital.getAccountName());//账户名
                transferfailedVo.setAccNo(hospital.getAccountNo());//账号

                financePayInfo.setPayeeBank(hospital.getBankName());
                financePayInfo.setAccName(hospital.getAccountName());
                financePayInfo.setAccNo(hospital.getAccountName());
                financePayInfoMapper.updateFinancePayInfo(financePayInfo);
            }
            if(StringUtils.isNotEmpty(financePayInfo.getPayCurrency()) && financePayInfo.getPayCurrency().equals("CNY")){
                if(null!=financePayInfo.getSumPayAmount()){
                    BigDecimal sumPayAmount= financePayInfo.getSumPayAmount();
                    transferfailedVo.setSumPayAmount(sumPayAmount);
                }
            }else if (StringUtils.isNotEmpty(financePayInfo.getPayCurrency()) && !financePayInfo.getPayCurrency().equals("CNY")){
                if(null!=financePayInfo.getSumPayAmountForeign()){
                    BigDecimal sumPayAmount= financePayInfo.getSumPayAmountForeign();
                    transferfailedVo.setSumPayAmount(sumPayAmount);
                }
            }
        return transferfailedVo;
    }




    /**
     * 删除转账失败信息
     *
     */
    @Override
    public int deleteFinanceTransferfailedList(String batchNo)
    {
        return financePayInfoMapper.deleteFinanceTransferfailedList(batchNo);
    }


    /**
     * 置借款数据为无效
     *
     * @param rptNo
     * @return
     */
    @Override
    public int deleteFinanceBorrow(String rptNo) {
        return financeBorrowInfoMapper.updateFinanceBorrowInfoByRptNo(rptNo);
    }

    /**
     * 根据报案号查询借款信息
     *
     * @param rptNo
     * @return
     */
    @Override
    public FinanceBorrowInfo selectBorrowInfo(String rptNo) {
        return financeBorrowInfoMapper.selectFinanceBorrowInfoByRptNo(rptNo);
    }

    /**
     * 更新借款表数据
     *
     * @param borrowInfo
     * @return
     */
    @Override
    public int updateBorrowInfo(FinanceBorrowInfo borrowInfo) {
        return financeBorrowInfoMapper.updateBorrowInfoByRptNo(borrowInfo);
    }
}

package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.TransferBy;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.UmCode;
import com.paic.ehis.cs.mapper.TransferByMapper;
import com.paic.ehis.cs.service.ITransferByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 转办信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-07
 */
@Service
public class TransferByServiceImpl implements ITransferByService
{
    @Autowired
    private TransferByMapper transferByMapper;

    /**
     * 查询转办信息 
     * 
     * @param transferId 转办信息 ID
     * @return 转办信息 
     */
    @Override
    public TransferBy selectTransferByById(Long transferId)
    {
        return transferByMapper.selectTransferByById(transferId);
    }

    /**
     * 查询转办信息 列表
     * 
     * @param transferBy 转办信息 
     * @return 转办信息 
     */
    @Override
    public List<TransferBy> selectTransferByList(TransferBy transferBy)
    {
        return transferByMapper.selectTransferByList(transferBy);
    }

    /**
     * 新增转办信息 
     * 
     * @param demandAcceptVo 转办信息
     * @return 结果
     */
    @Override
    public void insertTransferBy(DemandAcceptVo demandAcceptVo)
    {
        List<UmCode> list=demandAcceptVo.getUmCode();
        TransferBy transferBy=new TransferBy();
      // int count=0;
        for (UmCode umCode:list){
            //获得工单号
            transferBy.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
            //随机生成流水号
            transferBy.setTransferId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("transfer_id",10,6)));

            transferBy.setUmCode(umCode.getValue());
            transferBy.setReason(demandAcceptVo.getReason());
            transferBy.setCreatedBy(SecurityUtils.getUsername());
            transferBy.setCreatedTime(DateUtils.getNowDate());
            transferBy.setUpdatedBy(SecurityUtils.getUsername());
            transferBy.setUpdatedTime(DateUtils.getNowDate());
            transferByMapper.insertTransferBy(transferBy);
        }/*count++*/;
       // return count;
    }

    /**
     * 修改转办信息 
     * 
     * @param transferBy 转办信息 
     * @return 结果
     */
    @Override
    public int updateTransferBy(TransferBy transferBy)
    {
        return transferByMapper.updateTransferBy(transferBy);
    }

    /**
     * 批量删除转办信息 
     * 
     * @param transferIds 需要删除的转办信息 ID
     * @return 结果
     */
    @Override
    public int deleteTransferByByIds(Long[] transferIds)
    {
        return transferByMapper.deleteTransferByByIds(transferIds);
    }

    /**
     * 删除转办信息 信息
     * 
     * @param transferId 转办信息 ID
     * @return 结果
     */
    @Override
    public int deleteTransferByById(Long transferId)
    {
        return transferByMapper.deleteTransferByById(transferId);
    }
}

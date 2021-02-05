package com.paic.ehis.finance.mapper;

import com.paic.ehis.finance.domain.vo.ClaimCaseForeignPayInfoVO;
import com.paic.ehis.finance.domain.vo.ClaimCasePayInfoVO;

import java.util.List;

/**
 * 对公支付Mapper接口
 */
public interface ClaimCasePayMapper
{
    /**
     * 根据批次号查询案件信息
     *
     * @param batchNo
     * @return
     */
    public List<ClaimCasePayInfoVO> selectPayInfoList(String batchNo);

    /**
     * 根据批次号查询案件信息（外币）
     *
     * @param batchNo
     * @return
     */
    public List<ClaimCaseForeignPayInfoVO> selectForeignPayInfoList(String batchNo);
}

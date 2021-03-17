package com.paic.ehis.finance.service;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.finance.domain.dto.ClaimCasePayDTO;
import com.paic.ehis.finance.domain.vo.ClaimCaseForeignPayVO;
import com.paic.ehis.finance.domain.vo.ClaimCasePayVO;

import java.util.List;
import java.util.Map;

/**
 * 对公支付
 */
public interface IClaimCasePayService {

    /**
     * 查询当前登录人所属机构下的不全为已支付的批次信息
     *
     * @return
     */
    public List<Map<String,Object>> selectInitList(String organCode);

    /**
     * 查询对公支付的批次信息
     *
     * @param claimCasePayDTO
     * @return
     */
    public List<Map<String,Object>> selectBatchList(ClaimCasePayDTO claimCasePayDTO);

    /**
     * 根据批次号查询案件信息
     *
     * @param batchNo
     * @return
     */
    public ClaimCasePayVO getCaseInfo(String batchNo);

    /**
     * 对公支付-回退按钮
     *
     * @param rptNo
     * @return
     */
    public AjaxResult rollback(String rptNo);

    /**
     * 对公支付-确认支付按钮
     *
     * @param claimCasePayVO
     * @return
     */
    public int confirmPayment(ClaimCaseForeignPayVO claimCasePayVO);

    /**
     * 对公支付-案件借款按钮
     *
     * @param claimCasePayVO
     * @return
     */
    public int borrowingCase(ClaimCaseForeignPayVO claimCasePayVO);

    /**
     * 对公外币支付-页面初始化（外币、不全为已支付状态）
     *
     * @return
     */
    public List<Map<String,Object>> selectInitForeignList(String organCode);

    /**
     * 对公外币支付-查询
     *
     * @param claimCasePayDTO
     * @return
     */
    public List<Map<String,Object>> selectForeignBatchList(ClaimCasePayDTO claimCasePayDTO);

    /**
     * 根据批次号查询案件信息（外币）
     *
     * @param batchNo
     * @return
     */
    public ClaimCaseForeignPayVO getForeignCase(String batchNo);
}

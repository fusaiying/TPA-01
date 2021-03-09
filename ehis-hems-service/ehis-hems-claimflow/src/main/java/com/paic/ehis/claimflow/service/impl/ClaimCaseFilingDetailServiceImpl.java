package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.ClaimCaseFilingDetail;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingListVO;
import com.paic.ehis.claimflow.mapper.ClaimCaseFilingDetailMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseFilingMapper;
import com.paic.ehis.claimflow.service.IClaimCaseFilingDetailService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 案件归档明细Service业务层处理
 * 
 * @author sino
 * @date 2021-03-05
 */
@Service
public class ClaimCaseFilingDetailServiceImpl implements IClaimCaseFilingDetailService 
{
    @Autowired
    private ClaimCaseFilingDetailMapper claimCaseFilingDetailMapper;

    @Autowired
    private ClaimCaseFilingMapper claimCaseFilingMapper;

    /**
     * 查询案件归档明细
     * 
     * @param filingDetailId 案件归档明细ID
     * @return 案件归档明细
     */
    @Override
    public ClaimCaseFilingDetail selectClaimCaseFilingDetailById(Long filingDetailId)
    {
        return claimCaseFilingDetailMapper.selectClaimCaseFilingDetailById(filingDetailId);
    }

    /**
     * 查询案件归档明细列表
     * 
     * @param claimCaseFilingDetail 案件归档明细
     * @return 案件归档明细
     */
    @Override
    public List<ClaimCaseFilingDetail> selectClaimCaseFilingDetailList(ClaimCaseFilingDetail claimCaseFilingDetail)
    {
        return claimCaseFilingDetailMapper.selectClaimCaseFilingDetailList(claimCaseFilingDetail);
    }

    /**
     * 新增案件归档明细
     * 
     * @param claimCaseFilingDetail 案件归档明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseFilingDetail(ClaimCaseFilingDetail claimCaseFilingDetail)
    {
        claimCaseFilingDetail.setCreateTime(DateUtils.getNowDate());
        return claimCaseFilingDetailMapper.insertClaimCaseFilingDetail(claimCaseFilingDetail);
    }

    /**
     * 修改案件归档明细
     * 
     * @param claimCaseFilingDetail 案件归档明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseFilingDetail(ClaimCaseFilingDetail claimCaseFilingDetail)
    {
        claimCaseFilingDetail.setUpdateTime(DateUtils.getNowDate());
        return claimCaseFilingDetailMapper.updateClaimCaseFilingDetail(claimCaseFilingDetail);
    }

    /**
     * 批量删除案件归档明细
     * 
     * @param filingDetailIds 需要删除的案件归档明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseFilingDetailByIds(Long[] filingDetailIds)
    {
        return claimCaseFilingDetailMapper.deleteClaimCaseFilingDetailByIds(filingDetailIds);
    }

    /**
     * 删除案件归档明细信息
     * 
     * @param filingDetailId 案件归档明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseFilingDetailById(Long filingDetailId)
    {
        return claimCaseFilingDetailMapper.deleteClaimCaseFilingDetailById(filingDetailId);
    }


    @Override
    public int updateCaseFilingDetailInfo(List<ClaimCaseFilingDetail> dto) {

        int result = 0;
        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();
        ClaimCaseFilingListVO updateBean = new ClaimCaseFilingListVO();
        updateBean.setUpdateBy(username);
        updateBean.setUpdateTime(nowDate);

        for(ClaimCaseFilingDetail bean : dto) {
            bean.setUpdateBy(username);
            bean.setUpdateTime(nowDate);
            updateBean.setCaseBoxNo(bean.getCaseBoxNo());
            result += claimCaseFilingMapper.updateClaimCaseFilingEdit(updateBean);
            result += claimCaseFilingDetailMapper.updateClaimCaseFilingDetail(bean);
        }
        return result;
    }
}

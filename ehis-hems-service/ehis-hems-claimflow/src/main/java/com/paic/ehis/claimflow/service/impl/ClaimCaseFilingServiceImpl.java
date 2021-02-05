package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.ClaimCaseFiling;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseFilingDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingInformationVO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingListVO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingVO;
import com.paic.ehis.claimflow.mapper.ClaimBatchInvoiceFilingMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseFilingMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseMapper;
import com.paic.ehis.claimflow.mapper.SysUserMapper;
import com.paic.ehis.claimflow.service.IClaimCaseFilingService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.system.api.domain.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 案件归档Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseFilingServiceImpl implements IClaimCaseFilingService
{
    @Autowired
    private ClaimCaseFilingMapper claimCaseFilingMapper;

    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    @Autowired
    private ClaimBatchInvoiceFilingMapper claimBatchInvoiceFilingMapper;

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 查询案件归档
     * 
     * @param batchNo 案件归档ID
     * @return 案件归档
     */
    @Override
    public ClaimCaseFiling selectClaimCaseFilingById(String batchNo)
    {
        return claimCaseFilingMapper.selectClaimCaseFilingById(batchNo);
    }

    /**
     * 查询案件归档列表
     * 
     * @param claimCaseFiling 案件归档
     * @return 案件归档
     */
    @Override
    public List<ClaimCaseFiling> selectClaimCaseFilingList(ClaimCaseFiling claimCaseFiling)
    {
        return claimCaseFilingMapper.selectClaimCaseFilingList(claimCaseFiling);
    }

    /**
     * 新增案件归档
     * 
     * @param claimCaseFiling 案件归档
     * @return 结果
     */
    @Override
    public int insertClaimCaseFiling(ClaimCaseFiling claimCaseFiling)
    {
        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();
        claimCaseFiling.setStatus("Y");
        claimCaseFiling.setCreateBy(username);
        claimCaseFiling.setCreateTime(nowDate);
        return claimCaseFilingMapper.insertClaimCaseFiling(claimCaseFiling);
    }

    /**
     * 修改案件归档
     * 
     * @param claimCaseFiling 案件归档
     * @return 结果
     */
    @Override
    public int updateClaimCaseFiling(ClaimCaseFiling claimCaseFiling)
    {
        claimCaseFiling.setUpdateTime(DateUtils.getNowDate());
        return claimCaseFilingMapper.updateClaimCaseFiling(claimCaseFiling);
    }

    /**
     * 批量删除案件归档
     * 
     * @param batchNos 需要删除的案件归档ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseFilingByIds(String[] batchNos)
    {
        return claimCaseFilingMapper.deleteClaimCaseFilingByIds(batchNos);
    }

    /**
     * 删除案件归档信息
     * 
     * @param batchNo 案件归档ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseFilingById(String batchNo)
    {
        return claimCaseFilingMapper.deleteClaimCaseFilingById(batchNo);
    }

    /** 查询案件归档主页面 */
    @Override
    public List<ClaimCaseFilingListVO> selectCaseClaimCaseFilingList(ClaimCaseFilingDTO claimCaseFilingDTO) {

        //当前端不传值时默认展示当前登录机构最近一个月的归档数据
        if(StringUtils.isNull(claimCaseFilingDTO.getClaimType())
            && StringUtils.isNull(claimCaseFilingDTO.getDeptCode())
            && StringUtils.isNull(claimCaseFilingDTO.getBatchNo())
            && StringUtils.isNull(claimCaseFilingDTO.getRptNo())
            && StringUtils.isNull(claimCaseFilingDTO.getCaseBoxNo())){
            //设置时间为最近一个月
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) -30);
            claimCaseFilingDTO.setUpdateStartTime(calendar.getTime());
            claimCaseFilingDTO.setUpdateEndTime(DateUtils.parseDate(DateUtils.getTime()));
            //获取用户的所属机构,设置当前登录机构
            Long userId = SecurityUtils.getUserId();
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            claimCaseFilingDTO.setDeptCode( sysUser.getDeptId().toString());
        }
        //查询出所有数据记录
        List<ClaimCaseFilingVO> claimCaseFilingVoS = claimCaseFilingMapper.selectCaseClaimCaseFilingList(claimCaseFilingDTO);
        List<ClaimCaseFilingListVO> list = new ArrayList<>();
        //遍历集合
        for (ClaimCaseFilingVO claimCaseFilingVo1 :claimCaseFilingVoS) {

            ClaimCaseFilingListVO claimCaseFilingListVO = new ClaimCaseFilingListVO();

            claimCaseFilingListVO.setRptStartNo(claimCaseFilingVo1.getRptStartNo());
            claimCaseFilingListVO.setRptEndNo(claimCaseFilingVo1.getRptEndNo());

            if(StringUtils.isNotBlank(claimCaseFilingVo1.getRptStartNo()) && StringUtils.isNotBlank(claimCaseFilingVo1.getRptEndNo())) {
                String caseNum = claimCaseMapper.selectCaseClaimCaseFilingList(claimCaseFilingListVO);
                claimCaseFilingListVO.setCasenum(caseNum);
            }
            // cocy
            BeanUtils.copyProperties(claimCaseFilingVo1,claimCaseFilingListVO);

            list.add(claimCaseFilingListVO);
        }
        return list;
    }

    /** 改变是否销毁状态 */
    @Override
    public int updateClaimCaseFilingDestroy(ClaimCaseFilingListVO claimCaseFilingListVO) {
        claimCaseFilingListVO.setStatus("N");
        return claimCaseFilingMapper.updateClaimCaseFilingEdit(claimCaseFilingListVO);
    }

    /** 编辑按钮 */
    @Override
    public void updateClaimCaseFilingEdit(ClaimCaseFilingListVO claimCaseFilingListVO) {

        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();
        claimCaseFilingListVO.setUpdateBy(username);
        claimCaseFilingListVO.setUpdateTime(nowDate);
        claimCaseFilingMapper.updateClaimCaseFilingEdit(claimCaseFilingListVO);

    }

    /** 查询案件归档详细信息 */
    @Override
    public List<ClaimCaseFilingInformationVO> selectCaseClaimCaseFilingInfo(ClaimCaseFilingListVO claimCaseFilingListVO) {

        List<ClaimCaseFilingInformationVO> claimCaseFilingInformationVoS = claimCaseFilingMapper.selectCaseClaimCaseFilingInfo(claimCaseFilingListVO);

        return claimCaseFilingInformationVoS;
    }

    /** 保存案件归档详细信息 */
    @Override
    public int updateClaimCaseFilingInfo(List<ClaimCaseFilingInformationVO> claimCaseFilingInformationVO) {

        int result = 0;
        for(ClaimCaseFilingInformationVO bean : claimCaseFilingInformationVO) {
            result =  claimBatchInvoiceFilingMapper.updateClaimCaseFilingInfo(bean);
        }
        return result;
    }

}

package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.ClaimBatchInvoiceFiling;
import com.paic.ehis.claimflow.domain.ClaimCaseFiling;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseFilingDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingInformationVO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingListVO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingVO;
import com.paic.ehis.claimflow.domain.vo.RptNoVo;
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

        //默认展示当前登录机构最近一个月的归档数据,因为前端已经将时间设置为一个月前，进行传输，所以只需判断机构为空就查询当前登录机构，不为空则查询传输的数据
        if(StringUtils.isEmpty(claimCaseFilingDTO.getDeptCode())
                && StringUtils.isEmpty(claimCaseFilingDTO.getClaimType())
                && StringUtils.isEmpty(claimCaseFilingDTO.getBatchNo())
                && StringUtils.isEmpty(claimCaseFilingDTO.getRptNo())
                && StringUtils.isEmpty(claimCaseFilingDTO.getCaseBoxNo())){

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

        //新建一个集合，这个集合是返回给前端的
        List<ClaimCaseFilingInformationVO> claimCaseFilingInformationVo = new ArrayList<>();

        //新建一个实体类保存传给前端的信息
        ClaimCaseFilingInformationVO claimCaseFilingInformationVO = new ClaimCaseFilingInformationVO();

        //通过前端传过来的盒号查询出案件归档明细所需要的信息
        ClaimCaseFilingInformationVO claimCaseFilingInformationVoS = claimCaseFilingMapper.selectCaseClaimCaseFilingInfo(claimCaseFilingListVO);

        //通过前端传过来的报案起止号查询出报案号和批次号
        List<RptNoVo> rptNoVos = claimCaseMapper.selectCaseClaimCaseFilingRptNo(claimCaseFilingListVO);

        //循环遍历
        for (RptNoVo v:rptNoVos) {

            //向实体类中添加报案号数据
            claimCaseFilingInformationVO.setRptNo(v.getRptNo());

            //将查询到的信息添加到给前端的集合的实体类中
            claimCaseFilingInformationVO.setCaseBoxNo(claimCaseFilingInformationVoS.getCaseBoxNo());
            claimCaseFilingInformationVO.setDeptCode(claimCaseFilingInformationVoS.getDeptCode());
            claimCaseFilingInformationVO.setClaimType(claimCaseFilingInformationVoS.getClaimType());
            claimCaseFilingInformationVO.setIsFiling(claimCaseFilingInformationVoS.getIsFiling());
            claimCaseFilingInformationVO.setIsInvoiceBack(claimCaseFilingInformationVoS.getIsInvoiceBack());
            claimCaseFilingInformationVO.setIsSingle(claimCaseFilingInformationVoS.getIsSingle());
            claimCaseFilingInformationVO.setRemark(claimCaseFilingInformationVoS.getRemark());

            //将实体类添加到给前端的集合中
            claimCaseFilingInformationVo.add(claimCaseFilingInformationVO);
        }

        return claimCaseFilingInformationVo;
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

package com.paic.ehis.claimmgt.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.claimmgt.domain.ClaimCaseDist;
import com.paic.ehis.claimmgt.mapper.ClaimCaseDistMapper;
import com.paic.ehis.claimmgt.service.IClaimCaseDistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 案件分配规则Service业务层处理
 *
 * @author sino
 * @date 2021-01-22
 */
@Service
public class ClaimCaseDistServiceImpl implements IClaimCaseDistService
{
    @Autowired
    private ClaimCaseDistMapper claimCaseDistMapper;

   /* @Autowired
    private ClaimCaseMapper claimCaseMapper;*/
    @Autowired
    private SysUserServiceImpl sysUserMapper;

    /**
     * 查询案件分配规则
     *
     * @param distId 案件分配规则ID
     * @return 案件分配规则
     */
    @Override
    public ClaimCaseDist selectClaimCaseDistById(Long distId)
    {

        return claimCaseDistMapper.selectClaimCaseDistById(distId);
    }

    /**
     * 查询案件分配规则列表
     *
     * @param claimCaseDist 案件分配规则
     * @return 案件分配规则
     */
    @Override
    public List<ClaimCaseDist> selectClaimCaseDistList(ClaimCaseDist claimCaseDist)
    {
        //claimCaseDist.setStatus("Y");
        if (claimCaseDist.getIsEqually()=="Y"){
            String rate="均分";
            BigDecimal bd=new BigDecimal(rate);
            claimCaseDist.setRate(bd);
        }
        return claimCaseDistMapper.selectClaimCaseDistList(claimCaseDist);
    }

    /**
     * 新增案件分配规则
     *
     * @param claimCaseDist 案件分配规则
     * @return 结果
     */
    @Override
    public int insertClaimCaseDist(ClaimCaseDist claimCaseDist)
    {
        claimCaseDist.setCreateTime(DateUtils.getNowDate());
        return claimCaseDistMapper.insertClaimCaseDist(claimCaseDist);
    }

    /**
     * 修改案件分配规则
     *
     * @param claimCaseDist 案件分配规则
     * @return 结果
     */
    @Override
    public int updateClaimCaseDist(ClaimCaseDist claimCaseDist)
    {
        claimCaseDist.setUpdateTime(DateUtils.getNowDate());
        claimCaseDist.setStatus("Y");
        return claimCaseDistMapper.updateClaimCaseDist(claimCaseDist);
    }
    /**
     * 查询案件分配
     * @param claimCaseDist
     * @return
     */
    @Override
    public List<ClaimCaseDist> selectClaimCaseDist(ClaimCaseDist claimCaseDist) {
        return claimCaseDistMapper.selectClaimCaseDist(claimCaseDist);
    }


    /**
     * 批量删除案件分配规则
     *
     * @param distIds 需要删除的案件分配规则ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDistByIds(Long[] distIds)
    {
        return claimCaseDistMapper.deleteClaimCaseDistByIds(distIds);
    }

    /**
     * 删除案件分配规则信息
     *
     * @param distId 案件分配规则ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDistById(Long distId)
    {
        return claimCaseDistMapper.deleteClaimCaseDistById(distId);
    }

    /**
     * 一键均分
     * @param claimCaseDist
     * @return
     */
    @Override
    public int updateClaimCaseAverage(ClaimCaseDist claimCaseDist) {
        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();
        claimCaseDist.setUpdateBy(username);
        claimCaseDist.setUpdateTime(nowDate);
        claimCaseDist.setStatus("Y");
        claimCaseDist.setIsEqually(claimCaseDist.getIsEqually());
        return claimCaseDistMapper.updateClaimCaseAverage(claimCaseDist);
    }
    /**
     * 案件分配规则编辑超链接
     * @param claimCaseDist
     * @return
     */
    @Override
    public int updateClaimCaseDistOne(ClaimCaseDist claimCaseDist) {
        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();
        claimCaseDist.setCreateTime(nowDate);
        claimCaseDist.setCreateBy(username);
        claimCaseDist.setUpdateBy(username);
        claimCaseDist.setUpdateTime(nowDate);
      /*  claimCaseDist.setStatus("Y");
        claimCaseDist.setStatus();*/
        if(!claimCaseDist.getStatus().equals("02")) {
            claimCaseDist.setIsEqually("N");
        }
        claimCaseDist.setRoleId(claimCaseDist.getRoleId());
        return   claimCaseDistMapper.updateClaimCaseDistOne(claimCaseDist);
    }
    /**
     * 案件交单完成进入受理阶段分配操作人
     */
   /* @Override
    public int updateCaseDist(ClaimBatchDTO claimBatchDTO) {
        ClaimCase claimCase=new ClaimCase();
        //List<ClaimCase> claimCases=claimCaseMapper.selectClaimCaseList(claimCase);
        ClaimCaseDistVo claimCaseDistVo=claimCaseMapper.selectCaseDistNumber(claimBatchDTO.getClaimBatch().getBatchno());
        int i=claimCaseDistVo.getQuantity();//当前批次案子数量
        ClaimCaseDist claimCaseDist=new ClaimCaseDist();
        ClaimCaseDist caseDists=claimCaseDistMapper.selectClaimCaseDistListOne(claimCaseDist);
        double j=caseDists.getRate().doubleValue()/100;//获取当前操作人分配比例
        SysUser sysUser = sysUserMapper.selectUserById(claimCaseDist.getUserId());//根据用户id得到用户名
        for (int x=0;x<=i*j;x++) {
            //c.setUpdateBy(claimCaseDist.getUserId().toString());
            claimCase.setUpdateBy(sysUser.getUserName());
        }
        return claimCaseMapper.updateClaimCase(claimCase);
    }
*/
    /*@Override
    public int updateOtherCaseDist(String rptNo) {
        ClaimCase claimCase=new ClaimCase();
        ClaimCaseDist claimCaseDist=new ClaimCaseDist();
        ClaimCaseDist caseDists=claimCaseDistMapper.selectClaimCaseDistListOne(claimCaseDist);
        double j=caseDists.getRate().doubleValue()/100;//获取当前操作人分配比例
        ClaimCaseDistVo claimCaseDistVo=claimCaseMapper.selectCaseDistNumberOne(rptNo,claimCase.getCaseStatus());
        int i=claimCaseDistVo.getQuantity();//当前批次案子数量
        SysUser sysUser = sysUserMapper.selectUserById(claimCaseDist.getUserId());//根据用户id得到用户名
        if(j<j*(i+1)){
            claimCase.setUpdateBy(sysUser.getUserName());
        }
        return  claimCaseMapper.updateClaimCase(claimCase);
    }*/


}




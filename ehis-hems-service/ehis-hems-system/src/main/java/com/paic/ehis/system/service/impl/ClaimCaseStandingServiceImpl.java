package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCase;
import com.paic.ehis.system.domain.ClaimCaseStanding;
import com.paic.ehis.system.domain.vo.ClaimCaseStandingVo;
import com.paic.ehis.system.mapper.ClaimCaseMapper;
import com.paic.ehis.system.mapper.ClaimCaseStandingMapper;
import com.paic.ehis.system.service.IClaimCaseStandingService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 报案台账信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class ClaimCaseStandingServiceImpl implements IClaimCaseStandingService
{
    @Autowired
    private ClaimCaseStandingMapper claimCaseStandingMapper;

    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    /**
     * 查询报案台账信息 
     * 
     * @param rptNo 报案台账信息 ID
     * @return 报案台账信息 
     */
    @Override
    public ClaimCaseStanding selectClaimCaseStandingById(String rptNo)
    {
        return claimCaseStandingMapper.selectClaimCaseStandingById(rptNo);
    }

    /**
     * 查询报案台账信息 列表
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 报案台账信息 
     */
    @Override
    public List<ClaimCaseStanding> selectClaimCaseStandingList(ClaimCaseStanding claimCaseStanding)
    {
        return claimCaseStandingMapper.selectClaimCaseStandingList(claimCaseStanding);
    }

    /**
     * 新增报案台账信息 
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 结果
     */
    @Override
    public int insertClaimCaseStanding(ClaimCaseStanding claimCaseStanding)
    {
        claimCaseStanding.setCreateTime(DateUtils.getNowDate());
        return claimCaseStandingMapper.insertClaimCaseStanding(claimCaseStanding);
    }

    /**
     * 修改报案台账信息 
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 结果
     */
    @Override
    public int updateClaimCaseStanding(ClaimCaseStanding claimCaseStanding)
    {
        claimCaseStanding.setUpdateTime(DateUtils.getNowDate());
        return claimCaseStandingMapper.updateClaimCaseStanding(claimCaseStanding);
    }

    /**
     * 批量删除报案台账信息 
     * 
     * @param rptNos 需要删除的报案台账信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseStandingByIds(String[] rptNos)
    {
        return claimCaseStandingMapper.deleteClaimCaseStandingByIds(rptNos);
    }

    /**
     * 删除报案台账信息 信息
     * 
     * @param rptNo 报案台账信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseStandingById(String rptNo)
    {
        return claimCaseStandingMapper.deleteClaimCaseStandingById(rptNo);
    }

    /*********************************************/
    /**
     * 新增报案台账信息
     *
     * @param claimCaseStandingVo 报案台账信息
     * @return 结果
     */
    @Override
    public ClaimCaseStandingVo insertSysClaimCaseStanding(ClaimCaseStandingVo claimCaseStandingVo)
    {
        //获取报案号
//        Date date = new Date();//获取当前的日期
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//        String str = df.format(date);//获取String类型的时间
//        int number = (int) Math.floor(Math.random() * 1000);
//        String number1 = Integer.toString(number);
//        String bahtime="BAH"+str+number1;//报案号

        String bahtime="96"+"JGH0X"+PubFun.createMySqlMaxNoUseCache("RPTCODE",10,16);

        claimCaseStandingVo.setRptno(bahtime);
        claimCaseStandingVo.setCreateBy(SecurityUtils.getUsername());
        claimCaseStandingVo.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimCaseStandingVo.setUpdateBy(SecurityUtils.getUsername());
        claimCaseStandingVo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimCaseStandingVo.setStatus("Y");

        //处理 讲claimCaseStandingVo传变成claimCaseStanding类型
        ClaimCaseStanding claimCaseStanding = new ClaimCaseStanding();

        List<String> claimmaterials = claimCaseStandingVo.getClaimmaterials();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < claimmaterials.size(); i++) {
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(",");
            }
            sb.append(claimmaterials.get(i));
        }

        claimCaseStanding.setClaimmaterials(sb.toString());
        claimCaseStanding.setRptno(claimCaseStandingVo.getRptno());
        claimCaseStanding.setIdno(claimCaseStandingVo.getIdno());
        claimCaseStanding.setName(claimCaseStandingVo.getName());
        claimCaseStanding.setOtherinfo(claimCaseStandingVo.getOtherinfo());
        claimCaseStanding.setCreateBy(claimCaseStandingVo.getCreateBy());
        claimCaseStanding.setCreateTime(claimCaseStandingVo.getCreateTime());
        claimCaseStanding.setUpdateBy(claimCaseStandingVo.getUpdateBy());
        claimCaseStanding.setUpdateTime(claimCaseStandingVo.getUpdateTime());
        claimCaseStanding.setSearchValue(claimCaseStandingVo.getSearchValue());
        claimCaseStanding.setRemark(claimCaseStandingVo.getRemark());
        claimCaseStanding.setBeginTime(claimCaseStandingVo.getBeginTime());
        claimCaseStanding.setEndTime(claimCaseStandingVo.getEndTime());
        claimCaseStanding.setStatus("Y");

        ClaimCase claimCase = new ClaimCase();
//        String claimCaseNubmer = PubFun.createMySqlMaxNoUseCache("claim_case", 10, 10);
//        String claimCaseNumber1="GDH"+claimCaseNubmer;
        String claimCaseNumber1="JGH"+DateUtils.dateTimeNow("yyyy")+"X"+PubFun.createMySqlMaxNoUseCache("FILINGCODE",10,10);
        claimCase.setBatchNo(claimCaseStandingVo.getBatchno());//批次号
        claimCase.setRptNo(bahtime);//报案号
        claimCase.setFilingNo(claimCaseNumber1);//归档号
        claimCase.setCaseStatus("05");//案件状态
        claimCase.setCreateBy(SecurityUtils.getUsername());
        claimCase.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCase.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimCase.setStatus("Y");
        claimCaseMapper.insertClaimCase(claimCase);

        claimCaseStandingMapper.insertClaimCaseStanding(claimCaseStanding);
        return claimCaseStandingVo;
    }

    /**
     * 修改报案台账信息
     *
     * @param claimCaseStandingVo 报案台账信息
     * @return 结果
     */
    @Override
    public ClaimCaseStandingVo updateSysClaimCaseStanding(ClaimCaseStandingVo claimCaseStandingVo)//
    {
        claimCaseStandingVo.setUpdateBy(SecurityUtils.getUsername());
        claimCaseStandingVo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));

        //处理 讲claimCaseStandingVo传变成claimCaseStanding类型
        ClaimCaseStanding claimCaseStanding = new ClaimCaseStanding();

        List<String> claimmaterials = claimCaseStandingVo.getClaimmaterials();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < claimmaterials.size(); i++) {
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(",");
            }
            sb.append(claimmaterials.get(i));
        }

        claimCaseStanding.setClaimmaterials(sb.toString());
        claimCaseStanding.setRptno(claimCaseStandingVo.getRptno());
        claimCaseStanding.setIdno(claimCaseStandingVo.getIdno());
        claimCaseStanding.setName(claimCaseStandingVo.getName());
        claimCaseStanding.setOtherinfo(claimCaseStandingVo.getOtherinfo());
        claimCaseStanding.setCreateBy(claimCaseStandingVo.getCreateBy());
        claimCaseStanding.setCreateTime(claimCaseStandingVo.getCreateTime());
        claimCaseStanding.setUpdateBy(claimCaseStandingVo.getUpdateBy());
        claimCaseStanding.setUpdateTime(claimCaseStandingVo.getUpdateTime());
        claimCaseStanding.setSearchValue(claimCaseStandingVo.getSearchValue());
        claimCaseStanding.setRemark(claimCaseStandingVo.getRemark());
        claimCaseStanding.setBeginTime(claimCaseStandingVo.getBeginTime());
        claimCaseStanding.setEndTime(claimCaseStandingVo.getEndTime());
        claimCaseStanding.setStatus("Y");

        claimCaseStandingMapper.updateClaimCaseStanding(claimCaseStanding);
        return claimCaseStandingVo;
    }
}

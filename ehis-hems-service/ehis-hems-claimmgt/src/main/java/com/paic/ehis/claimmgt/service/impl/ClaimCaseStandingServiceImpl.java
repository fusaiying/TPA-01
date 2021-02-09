package com.paic.ehis.claimmgt.service.impl;


import com.paic.ehis.claimmgt.domain.ClaimCaseStanding;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseStandingDTO;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseStandingVo;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseStandingVo1;
import com.paic.ehis.claimmgt.mapper.ClaimCaseStandingMapper;
import com.paic.ehis.claimmgt.mapper.SysDeptMapper;
import com.paic.ehis.claimmgt.mapper.SysUserMapper;
import com.paic.ehis.claimmgt.service.IClaimCaseStandingService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    /*@Autowired
    private ClaimCaseMapper claimCaseMapper;

    @Autowired
    private PolicyInfoMapper policyInfoMapper;*/

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysDeptMapper deptMapper;

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

    @Override
    public List<ClaimCaseStandingVo1> selectClaimCaseStandingListNew(ClaimCaseStandingDTO claimCaseStandingDTO) {
        List<ClaimCaseStandingVo1> claimCaseStandingVos = claimCaseStandingMapper.selectClaimCaseStandingListNew(claimCaseStandingDTO);
       if(!claimCaseStandingVos.isEmpty()){
           for(ClaimCaseStandingVo1 claimCaseStandingVo1 :claimCaseStandingVos){
               claimCaseStandingVo1.setCompanyName(claimCaseStandingMapper.selectCompanyName(claimCaseStandingVo1.getRptno()));
               claimCaseStandingVo1.setClaimmaterialList(Arrays.asList((claimCaseStandingVo1.getClaimmaterials().split(","))));
           }
       }
        return claimCaseStandingVos;
    }


    @Override
    public List<ClaimCaseStandingVo1> selectClaimCaseStandingListFirst(ClaimCaseStandingDTO claimCaseStandingDTO) {
        String name = SecurityUtils.getUsername();
        int deptId = userMapper.selectDept(name);
        claimCaseStandingDTO.setOrgancode(String.valueOf(deptId));
        List<ClaimCaseStandingVo1> claimCaseStandingVos = claimCaseStandingMapper.selectClaimCaseStandingListNew(claimCaseStandingDTO);
        if(!claimCaseStandingVos.isEmpty()){
            for(ClaimCaseStandingVo1 claimCaseStandingVo1 :claimCaseStandingVos){
                claimCaseStandingVo1.setCompanyName(claimCaseStandingMapper.selectCompanyName(claimCaseStandingVo1.getRptno()));
                claimCaseStandingVo1.setClaimmaterialList(Arrays.asList((claimCaseStandingVo1.getClaimmaterials().split(","))));
            }
        }
        return claimCaseStandingVos;
    }

   /* @Override
    public List<ClaimCaseStanding> selectClaimCaseStandingListNew(ClaimCaseStanding claimCaseStanding) throws Exception {
        List<ClaimCaseStanding> claimCaseStandingList=claimCaseStandingMapper.selectClaimCaseStandingListNew(claimCaseStanding);
        for (ClaimCaseStanding claimCaseStanding1:claimCaseStandingList){
            PolicyInfo policyInfo = policyInfoMapper.selectPolicyInfoById(claimCaseStanding1.getPolicyNo());//查出出单公司的所有信息
            StringBuilder sb = new StringBuilder();
            String companyname = policyInfo.getCompanyName();//得到单个的出单公司名字
            //字符拼接
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append("|");
            }
            sb.append(companyname);
            claimCaseStanding1.setCompanyName(companyname);
            Map map = Dateutils.getCurrontTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            claimCaseStanding1.setdBefore(sdf.parse(String.valueOf(map.get("defaultStartDate"))));
            claimCaseStanding1.setdNow(sdf.parse(String.valueOf(map.get("defaultEndDate"))));
        }

        return claimCaseStandingList;
    }
*/
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

    @Override
    public int updateClaimCaseStandingVo1(ClaimCaseStandingVo1 claimCaseStandingVo1) {
        claimCaseStandingVo1.setClaimmaterials(StringUtils.join(claimCaseStandingVo1.getClaimmaterialList(), ","));
        return claimCaseStandingMapper.updateClaimCaseStandingVo1(claimCaseStandingVo1);
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
     * 直结交单-新增-新增报案台账信息
     *
     * @param claimCaseStandingVo 报案台账信息
     * @return 结果
     */
   /* @Override
    public ClaimCaseStandingVo insertSysClaimCaseStanding(ClaimCaseStandingVo claimCaseStandingVo)
    {
        //获取报案号
        String bahtime="96"+"JGH0X"+ PubFun.createMySqlMaxNoUseCache("RPTCODE",10,10);

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

        //归档号
        String claimCaseNumber1="JGHDQQW"+DateUtils.dateTimeNow("yyyy")+"X"+PubFun.createMySqlMaxNoUseCache("FILINGCODE",10,10);
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
*/
    /**
     * 直结交单-新增-修改报案台账信息
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

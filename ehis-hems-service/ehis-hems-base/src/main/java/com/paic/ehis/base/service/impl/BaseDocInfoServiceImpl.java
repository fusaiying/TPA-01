package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.base.base.utility.Dateutils;
import com.paic.ehis.base.domain.*;
import com.paic.ehis.base.mapper.BaseDocInfoMapper;
import com.paic.ehis.base.service.IBaseDocInfoService;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * base_doctor(医生信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-31
 */
@Service
public class BaseDocInfoServiceImpl implements IBaseDocInfoService {
    @Autowired
    private BaseDocInfoMapper baseDocInfoMapper;

    /**
     * 查询base_doctor(医生信息)
     *
     * @param docCode base_doctor(医生信息)ID
     * @return base_doctor(医生信息)
     */
    @Override
    public BaseDocInfo selectBaseDocInfoById(String docCode) {
        // 根据医生编码查询所有的服务机构编码
        List<String> providercode=baseDocInfoMapper.selectproviderinfo(docCode);
        // 根据医生编码查询所有医生信息
        List<BaseDocInfo> baseDocInfos=baseDocInfoMapper.selectBaseDocInfoById(docCode);
        BaseDocInfo baseDocInfo=new BaseDocInfo();
        if (!baseDocInfos.isEmpty()){
            baseDocInfo=baseDocInfos.get(0);
            baseDocInfo.setSupplierList(providercode);
        }
        return baseDocInfo;
    }

    /**
     * 查询base_doctor(医生信息)列表
     *
     * @param baseDocInfo base_doctor(医生信息)
     * @return base_doctor(医生信息)
     */
    @Override
    public List<BaseDocInfo> selectBaseDocInfoList(BaseDocInfo baseDocInfo) {
        return baseDocInfoMapper.selectBaseDocInfoList(baseDocInfo);
    }

    /*
     *  主查询页面需默认显示截止当前时间在30天内且合约状态为“有效”的数据
     */
    @Override
    public List<BaseDocInfo> selectBaseMonth(BaseDocInfo baseDocInfo) throws Exception {
        Map map = Dateutils.getCurrontTime2();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        baseDocInfo.setDBefore2(sdf.parse(String.valueOf(map.get("defaultStartDate"))));
        baseDocInfo.setDNow2(sdf.parse(String.valueOf(map.get("defaultEndDate"))));
        return baseDocInfoMapper.selectBaseMonth(baseDocInfo);
    }

    @Override
    public List<BaseDocInfo> selectSupplierList() {
        return baseDocInfoMapper.selectSupplierList();
    }
    /**
     * 新增base_doctor(医生信息)
     *
     * @param baseDocInfo base_doctor(医生信息)
     * @return 结果
     */
    @Override
    public BaseDocInfo insertBaseDocInfo(BaseDocInfo baseDocInfo) {

        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();

        List<String> supplierList =baseDocInfo.getSupplierList();
        int n = 0;
        boolean flag=false;
        //修改
        if(StringUtils.isNotEmpty(baseDocInfo.getDocCode())) {
            baseDocInfoMapper.deleteBaseDocInfoById(baseDocInfo.getDocCode());
            flag=true;
        }
        String doccode=null;
        if (supplierList.size() > 0) {
            if (baseDocInfo.getDocCode()!=null && baseDocInfo.getDocCode()!=""){
                doccode=baseDocInfo.getDocCode();
            }else {
                doccode = "D" + PubFun.createMySqlMaxNoUseCache("BaseDocInfo", 0, 9);
            }
            baseDocInfo.setDocCode(doccode);
            for (int i = 0; i < supplierList.size(); i++) {
                String suppliercode = supplierList.get(i);
                String suppliername=baseDocInfoMapper.selectproviderchname1(suppliercode);
                BaseDocInfo baseDocInfo1 = new BaseDocInfo();
                baseDocInfo1.setSupplierCode(suppliercode);
                baseDocInfo1.setComment(suppliername);
                baseDocInfo1.setDocName(baseDocInfo.getDocName());
                baseDocInfo1.setDocDept(baseDocInfo.getDocDept());
                baseDocInfo1.setDocPhone(baseDocInfo.getDocPhone());
                baseDocInfo1.setFiestDept(baseDocInfo.getFiestDept());
                baseDocInfo1.setSecondDept(baseDocInfo.getSecondDept());
                baseDocInfo1.setRemark(baseDocInfo.getRemark());
                baseDocInfo1.setStartTime(baseDocInfo.getStartTime());
                baseDocInfo1.setEndTime(baseDocInfo.getEndTime());
                baseDocInfo1.setStatus("Y");
                baseDocInfo1.setSerialNo(PubFun.createMySqlMaxNoUseCache("doctorSer", 0, 20));
                baseDocInfo1.setDocCode(doccode);
                baseDocInfo1.setCreateTime(nowDate);
                baseDocInfo1.setCreateBy(username);

                if(flag){
                    baseDocInfo1.setCreateTime(baseDocInfo.getCreateTime());
                    baseDocInfo1.setCreateBy(baseDocInfo.getCreateBy());
                    baseDocInfo1.setUpdateTime(nowDate);
                    baseDocInfo1.setUpdateBy(username);
                }
              /*  baseDocInfo1.setUpdateTime(nowDate);
                baseDocInfo1.setUpdateBy(username);*/
                int i1 = baseDocInfoMapper.insertBaseDocInfo(baseDocInfo1);
                n = n + i1;
            }
        }  return baseDocInfo;


    }


    /**
     * 修改base_doctor(医生信息)
     *
     * @param baseDocInfo base_doctor(医生信息)
     * @return 结果
     */
    @Override
    public int updateBaseDocInfo(BaseDocInfo baseDocInfo)
    {

        baseDocInfo.setUpdateTime(DateUtils.getNowDate());
        return baseDocInfoMapper.insertBaseDocInfo(baseDocInfo);
    }

    /**
     * 批量删除base_doctor(医生信息)
     *
     * @param docCodes 需要删除的base_doctor(医生信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseDocInfoByIds(String[] docCodes)
    {
        return baseDocInfoMapper.deleteBaseDocInfoByIds(docCodes);
    }

    /**
     * 删除base_doctor(医生信息)信息
     *
     * @param docCode base_doctor(医生信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseDocInfoById(String docCode)
    {
        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();
        BaseDocInfo baseDocInfo = new BaseDocInfo();
        baseDocInfo.setDocCode(docCode);
        baseDocInfo.setUpdateTime(nowDate);
        baseDocInfo.setUpdateBy(username);
        baseDocInfo.setStatus("N");
        return baseDocInfoMapper.updateBaseDocInfo(baseDocInfo);
    }
}

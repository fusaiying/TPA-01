package com.paic.ehis.product.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.product.domain.*;
import com.paic.ehis.product.mapper.ProductCheckInfoMapper;
import com.paic.ehis.product.mapper.ProductManagerLogMapper;
import com.paic.ehis.product.utils.Dateutils;
import com.paic.ehis.system.api.GetProviderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.product.mapper.ProductInfoMapper;
import com.paic.ehis.product.service.IProductInfoService;
import org.springframework.transaction.annotation.Transactional;
import com.paic.ehis.common.core.utils.SecurityUtils;

/**
 * base_product_info(服务产品)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
@Transactional
public class ProductInfoServiceImpl implements IProductInfoService 
{
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductManagerLogMapper productManagerLogMapper;

    @Autowired
    private ProductCheckInfoMapper productCheckInfoMapper;

    @Autowired
    private GetProviderInfoService getProviderInfoService;


    /**
     * 查询base_product_info(服务产品)
     * 
     * @param productInfo base_product_info(服务产品)ID
     * @return base_product_info(服务产品)
     */
    @Override
    public ProductInfo selectProductInfoById(ProductInfo productInfo)
    {
        return productInfoMapper.selectProductInfoById(productInfo.getProductCode());
    }

    /**
     * 查询base_product_info(服务产品)列表
     * 
     * @param productInfo base_product_info(服务产品)
     * @return base_product_info(服务产品)
     */
    @Override
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo) throws Exception
    {
            return productInfoMapper.selectProductInfoList(productInfo);
    }

    /**
     * 默认查询base_product_info(服务产品)列表
     * @return base_product_info(服务产品)
     * @throws Exception
     */
    @Override
    public List<ProductInfo> selectProductInfoListNull() throws Exception {
        Map map = Dateutils.getCurrontTime1();
        ProductInfo productInfo = new ProductInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        productInfo.setBeforeDate(sdf.parse(String.valueOf(map.get("defaultStartDate"))));//一个月前
        productInfo.setNowDate(sdf.parse(String.valueOf(map.get("defaultEndDate")))); //当前时间
        return productInfoMapper.selectDefaultListist(productInfo);
    }


    /**
     * 新增base_product_info(服务产品)
     * 
     * @param productInfo base_product_info(服务产品)
     * @return 结果
     */
    @Override
    public int insertProductInfo(ProductInfo productInfo)
    {
        //修改产品状态
        productInfoMapper.updateStatus(productInfo.getProductCode());
        productInfo.setCreateTime(DateUtils.getNowDate());
        productInfo.setUpdateTime(DateUtils.getNowDate());
        productInfo.setCreateBy(SecurityUtils.getUsername());
        productInfo.setUpdateBy(SecurityUtils.getUsername());
        productInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("productInfoSer", 12, 12));
        if(StringUtils.isBlank(productInfo.getProductCode())){
            productInfo.setProductCode("PD"+PubFun.createMySqlMaxNoUseCache("productCodeSer", 10, 8));
        }
        productInfo.setStatus("Y");
        productInfo.setBussinessStatus("01");//新建状态
        int count = productInfoMapper.insertProductInfo(productInfo);
        if(count >0){
            //轨迹表中判断数据是否存在；存在，变更轨迹表中数据的更新时间；不存在，轨迹表中插入数据
            productManagerLogMapper.updateStatus(productInfo.getProductCode());
            ProductManagerLog productManagerLog = new ProductManagerLog();
            productManagerLog.setCreateTime(DateUtils.getNowDate());
            productManagerLog.setUpdateTime(DateUtils.getNowDate());
            productManagerLog.setCreateBy(SecurityUtils.getUsername());
            productManagerLog.setUpdateBy(SecurityUtils.getUsername());
            productManagerLog.setBussinessStatus("01");//新建状态
            productManagerLog.setStatus("Y");
            productManagerLogMapper.insertProductManagerLog(productManagerLog);

        }
        return productInfoMapper.insertProductInfo(productInfo);
    }



    /**
     * 新增base_product_info(服务产品)
     *
     * @param productInfo base_product_info(服务产品)
     * @return 结果
     */
    @Override
    public ProductInfo insertInfo(ProductInfo productInfo)
    {
        int count = 0;
        //判断产品编码是否存在
        if(StringUtils.isBlank(productInfo.getProductCode())){//新增
            productInfo.setProductCode("PD"+PubFun.createMySqlMaxNoUseCache("productCodeSer", 10, 8));
        }else{//变更状态
            productInfoMapper.updateStatus(productInfo.getProductCode());
        }

        productInfo.setCreateTime(DateUtils.getNowDate());
        productInfo.setUpdateTime(DateUtils.getNowDate());
        productInfo.setCreateBy(SecurityUtils.getUsername());
        productInfo.setUpdateBy(SecurityUtils.getUsername());
        productInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("productInfoSer", 12, 12));
        productInfo.setStatus("Y");
        productInfo.setBussinessStatus("01");//新建状态
        //产品表数据插入
        count = productInfoMapper.insertProductInfo(productInfo);
        if(count >0){
            //轨迹表中判断数据是否存在；存在，变更轨迹表中数据的更新时间；不存在，轨迹表中插入数据
            productManagerLogMapper.updateStatus(productInfo.getProductCode());
            ProductManagerLog productManagerLog = new ProductManagerLog();
            productManagerLog.setCreateTime(DateUtils.getNowDate());
            productManagerLog.setUpdateTime(DateUtils.getNowDate());
            productManagerLog.setCreateBy(SecurityUtils.getUsername());
            productManagerLog.setCreateBy(SecurityUtils.getUsername());
            productManagerLog.setSerialNo(PubFun.createMySqlMaxNoUseCache("productManagerSer", 12, 12));
            productManagerLog.setProductCode(productInfo.getProductCode());
            productManagerLog.setBussinessStatus("01");//新建状态
            productManagerLog.setStatus("Y");
            productManagerLogMapper.insertProductManagerLog(productManagerLog);
        }
        return productInfo;
    }

    /**
     * 修改base_product_info(服务产品)
     * 
     * @param productInfo base_product_info(服务产品)
     * @return 结果
     */
    @Override
    public int updateProductInfo(ProductInfo productInfo)
    {
        productInfo.setUpdateTime(DateUtils.getNowDate());
        return productInfoMapper.updateProductInfo(productInfo);
    }

    /**
     * 批量删除base_product_info(服务产品)
     * 
     * @param serialNos 需要删除的base_product_info(服务产品)ID
     * @return 结果
     */
    @Override
    public int deleteProductInfoByIds(String[] serialNos)
    {
        return productInfoMapper.deleteProductInfoByIds(serialNos);
    }

    /**
     * 删除base_product_info(服务产品)信息
     * 
     * @param productCode base_product_info(服务产品)ID
     * @return 结果
     */
    @Override
    public int deleteProductInfoById(String productCode)
    {
        return productInfoMapper.deleteProductInfoById(productCode);
    }

    /**
     *查询服务项目列表
     */
    @Override
    public List<BaseServiceInfo> getServiceInfo(){
        return productInfoMapper.getServiceInfo();
    }

    /**
     *产品审核列表查询
     */
    @Override
    public List<ProductInfo> selectCheckList(ProductInfo productInfo)
    {
        return productInfoMapper.selectCheckList(productInfo);
    }


    /**
     *保存审核信息
     */
    @Override
    public int insertCheckInfo(ProductCheckInfo productCheckInfo){
        int count = 0;
        int i = 0;
        ProductManagerLog productManagerLog = new ProductManagerLog();
        if("01".equals(productCheckInfo.getCheckResult())){
            if("04".equals(productCheckInfo.getBussinessStatus())){
                count =productInfoMapper.updateProStatus4(productCheckInfo.getProductCode());
            }else{
                count =productInfoMapper.updateProStatus1(productCheckInfo.getProductCode());
            }
            //审核同意，变更产品状态为发布
            productManagerLog.setBussinessStatus("03");//发布
        }else if("02".equals(productCheckInfo.getCheckResult())){
            if("04".equals(productCheckInfo.getBussinessStatus())){
                i=1;
            }else{
                //审核不同意，变更产品状态为退回
                count =productInfoMapper.updateProStatus2(productCheckInfo.getProductCode());
                productManagerLog.setBussinessStatus("05");//退回
            }

        }
        if(count>0){
            //保存到审核日志表
            productCheckInfo.setCreateTime(DateUtils.getNowDate());
            productCheckInfo.setUpdateTime(DateUtils.getNowDate());
            productCheckInfo.setCreateBy(SecurityUtils.getUsername());
            productCheckInfo.setCreateBy(SecurityUtils.getUsername());
            productCheckInfo.setStatus("Y");
            productCheckInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("productCheckSer", 12, 12));
            productCheckInfoMapper.insertProductCheckInfo(productCheckInfo);
            //保存信息至轨迹表
            productManagerLog.setCreateTime(DateUtils.getNowDate());
            productManagerLog.setUpdateTime(DateUtils.getNowDate());
            productManagerLog.setCreateBy(SecurityUtils.getUsername());
            productManagerLog.setCreateBy(SecurityUtils.getUsername());
            productManagerLog.setProductCode(productCheckInfo.getProductCode());
            productManagerLog.setSerialNo(PubFun.createMySqlMaxNoUseCache("productManagerSer", 12, 12));
            productManagerLog.setStatus("Y");
            i = productManagerLogMapper.insertProductManagerLog(productManagerLog);
        }
        return i;
    }


    /**
     *产品审核列表查询
     */
    @Override
    public List<ProductInfo> mangerList(ProductInfo productInfo)
    {
        return productInfoMapper.mangerList(productInfo);
    }

    /**
     *产品审核列表查询
     */
    @Override
    public List<ProductInfo> mangerListNull() throws Exception
    {
        Map map = Dateutils.getCurrontTime1();
        ProductInfo productInfo = new ProductInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        productInfo.setBeforeDate(sdf.parse(String.valueOf(map.get("defaultStartDate"))));//一个月前
        productInfo.setNowDate(sdf.parse(String.valueOf(map.get("defaultEndDate")))); //当前时间
        return productInfoMapper.selectMangerListNull(productInfo);
    }


    /**
     *产品下线
     */
    @Override
    public int insertMangerInfo(ProductManagerLog productManagerLog)
    {
        //修改产品表状态为下线
        productManagerLog.setUpdateBy(SecurityUtils.getUsername());
        productManagerLog.setUpdateTime(DateUtils.getNowDate());
        int count = productInfoMapper.updateProStatus3(productManagerLog);

        if(count >0){
            //保存信息至轨迹表
            productManagerLog.setCreateTime(DateUtils.getNowDate());
            productManagerLog.setUpdateTime(DateUtils.getNowDate());
            productManagerLog.setCreateBy(SecurityUtils.getUsername());
            productManagerLog.setUpdateBy(SecurityUtils.getUsername());
            productManagerLog.setSerialNo(PubFun.createMySqlMaxNoUseCache("productManagerSer", 12, 12));
            productManagerLog.setStatus("Y");
            productManagerLogMapper.insertProductManagerLog(productManagerLog);
        }
        return count;
    }

    @Override
    public List<ProductInfo> checkField(ProductInfo productInfo)
    {
        return productInfoMapper.checkField(productInfo);
    }

    @Override
    public List<ProductSupplierInfo> selectSupplier(ProductInfo productInfo){
        return productInfoMapper.selectSupplier(productInfo);
    }


    @Override
    public List<ProductSupplierInfo> selectProductSupplier(ProductInfo productInfo){
        return productInfoMapper.selectProductSupplier(productInfo);
    }



    @Override
    public int insertSupplier(com.paic.ehis.system.api.domain.ProductSupplierInfoVo productSupplierInfoVo){
        //将之前的数据置失效
        int count = 0;
        getProviderInfoService.insertSupplier(productSupplierInfoVo);
        productInfoMapper.updatesupplierInfo(productSupplierInfoVo);
        if(!productSupplierInfoVo.getProductSupplierInfos().isEmpty()){
            for(com.paic.ehis.system.api.domain.ProductSupplierInfo productSupplierInfo:productSupplierInfoVo.getProductSupplierInfos()){
                productSupplierInfo.setCreateTime(DateUtils.getNowDate());
                productSupplierInfo.setCreateBy(SecurityUtils.getUsername());
                productSupplierInfo.setUpdateBy(SecurityUtils.getUsername());
                productSupplierInfo.setUpdateTime(DateUtils.getNowDate());
                productSupplierInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("productSupplierSer", 12, 12));
                productSupplierInfo.setProductCode(productSupplierInfoVo.getProductCode());
                productSupplierInfo.setStatus("Y");
            }
            count = productInfoMapper.insertSupplier(productSupplierInfoVo.getProductSupplierInfos());
        }else {
            count = 1;
        }
        return count;
    }

    @Override
    public int updateSupplierStatus(com.paic.ehis.system.api.domain.ProductSupplierInfoVo productSupplierInfoVo){
        int count = productInfoMapper.updatesupplierInfo(productSupplierInfoVo);
        if(count <= 0){
            count =1;
        }
        return count;
    }
}

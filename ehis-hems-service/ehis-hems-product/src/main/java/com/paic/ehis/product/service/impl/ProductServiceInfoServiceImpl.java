package com.paic.ehis.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;import com.paic.ehis.product.domain.*;
import com.paic.ehis.product.mapper.ProductInfoMapper;
import com.paic.ehis.product.mapper.ProductManagerLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.product.mapper.ProductServiceInfoMapper;
import com.paic.ehis.product.service.IProductServiceInfoService;
import org.springframework.transaction.annotation.Transactional;


/**
 * base_product_service_info(服务产品项目关联)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
@Transactional
public class ProductServiceInfoServiceImpl implements IProductServiceInfoService 
{
    @Autowired
    private ProductServiceInfoMapper productServiceInfoMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductManagerLogMapper productManagerLogMapper;

    /**
     * 查询base_product_service_info(服务产品项目关联)
     * 
     * @param serialNo base_product_service_info(服务产品项目关联)ID
     * @return base_product_service_info(服务产品项目关联)
     */
    @Override
    public ProductServiceInfo selectProductServiceInfoById(String serialNo)
    {
        return productServiceInfoMapper.selectProductServiceInfoById(serialNo);
    }

    /**
     * 查询base_product_service_info(服务产品项目关联)列表
     * 
     * @param productInfo base_product_service_info(服务产品项目关联)
     * @return base_product_service_info(服务产品项目关联)
     */
    @Override
    public List<ProductServiceInfo> selectProductServiceInfoList(ProductServiceInfo productServiceInfo)
    {
        return productServiceInfoMapper.selectProductServiceInfoList(productServiceInfo);
    }

    /**
     * 新增base_product_service_info(服务产品项目关联)
     * 
     * @param productServiceInfoVo base_product_service_info(服务产品项目关联)
     * @return 结果
     */
    @Override
    public int insertProductServiceInfo(ProductServiceInfoVo productServiceInfoVo)
    {
        int count = 0;
        String productCode = productServiceInfoVo.getProductCode();
        //修改目前已有数据的状态
        productServiceInfoMapper.updateStaus(productCode);
        if(!productServiceInfoVo.getProductServiceInfos().isEmpty()){
            List<ProductServiceInfo> productServiceInfos= new ArrayList<ProductServiceInfo>();
            for(ProductServiceInfo productServiceInfo:productServiceInfoVo.getProductServiceInfos()){
                productServiceInfo.setCreateTime(DateUtils.getNowDate());
                productServiceInfo.setUpdateTime(DateUtils.getNowDate());
                productServiceInfo.setCreateBy(SecurityUtils.getUsername());
                productServiceInfo.setCreateBy(SecurityUtils.getUsername());
                productServiceInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("productServiceInfoSer", 12, 12));
                productServiceInfo.setStatus("Y");
                productServiceInfos.add(productServiceInfo);
            }
            count = productServiceInfoMapper.insertProductServiceInfoNew(productServiceInfos);
        }else{
            count = 1;
        }
        return count;
    }


    @Override
    public int saveProductInfo(ProductSaveInfoVo productSaveInfoVo)
    {

        int count = 0;
        ProductInfo productInfo = productSaveInfoVo.getProductInfoData();
        if(StringUtils.isBlank(productInfo.getProductCode())){//新增
            productInfo.setProductCode("PD"+PubFun.createMySqlMaxNoUseCache("productCodeSer", 10, 8));
        }else{//变更状态
            productInfoMapper.updateStatus(productInfo.getProductCode());
            productServiceInfoMapper.updateStaus(productInfo.getProductCode());
        }
        productInfo.setCreateTime(DateUtils.getNowDate());
        productInfo.setUpdateTime(DateUtils.getNowDate());
        productInfo.setCreateBy(SecurityUtils.getUsername());
        productInfo.setCreateBy(SecurityUtils.getUsername());
        productInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("productInfoSer", 12, 12));
        productInfo.setStatus("Y");
        productInfo.setBussinessStatus("01");//新建状态
        //产品表数据插入
        count = productInfoMapper.insertProductInfo(productInfo);
        if(!productSaveInfoVo.getServicesAvailableData().getForm().isEmpty()){
            List<ProductServiceInfo> productServiceInfos= new ArrayList<ProductServiceInfo>();
            for(ProductServiceInfo productServiceInfo:productSaveInfoVo.getServicesAvailableData().getForm()){
                productServiceInfo.setCreateTime(DateUtils.getNowDate());
                productServiceInfo.setUpdateTime(DateUtils.getNowDate());
                productServiceInfo.setCreateBy(SecurityUtils.getUsername());
                productServiceInfo.setCreateBy(SecurityUtils.getUsername());
                productServiceInfo.setProductCode(productInfo.getProductCode());
                productServiceInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("productServiceInfoSer", 12, 12));
                productServiceInfo.setStatus("Y");
                productServiceInfos.add(productServiceInfo);
            }
            productServiceInfoMapper.insertProductServiceInfoNew(productServiceInfos);
        }
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
        return count;
    }



    @Override
    public int checkProductInfo(ProductSaveInfoVo productSaveInfoVo)
    {

        int count = 0;
        ProductInfo productInfo = productSaveInfoVo.getProductInfoData();
        if(StringUtils.isBlank(productInfo.getProductCode())){//新增
            productInfo.setProductCode("PD"+PubFun.createMySqlMaxNoUseCache("productCodeSer", 10, 8));
        }else{//变更状态
            productInfoMapper.updateStatus(productInfo.getProductCode());
            productServiceInfoMapper.updateStaus(productInfo.getProductCode());
        }
        productInfo.setCreateTime(DateUtils.getNowDate());
        productInfo.setUpdateTime(DateUtils.getNowDate());
        productInfo.setCreateBy(SecurityUtils.getUsername());
        productInfo.setCreateBy(SecurityUtils.getUsername());
        productInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("productInfoSer", 12, 12));
        productInfo.setStatus("Y");
        productInfo.setBussinessStatus("02");//审核状态
        //产品表数据插入
        count = productInfoMapper.insertProductInfo(productInfo);
        if(!productSaveInfoVo.getServicesAvailableData().getForm().isEmpty()){
            List<ProductServiceInfo> productServiceInfos= new ArrayList<ProductServiceInfo>();
            for(ProductServiceInfo productServiceInfo:productSaveInfoVo.getServicesAvailableData().getForm()){
                productServiceInfo.setCreateTime(DateUtils.getNowDate());
                productServiceInfo.setUpdateTime(DateUtils.getNowDate());
                productServiceInfo.setCreateBy(SecurityUtils.getUsername());
                productServiceInfo.setCreateBy(SecurityUtils.getUsername());
                productServiceInfo.setProductCode(productInfo.getProductCode());
                productServiceInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("productServiceInfoSer", 12, 12));
                productServiceInfo.setStatus("Y");
                productServiceInfos.add(productServiceInfo);
            }
            productServiceInfoMapper.insertProductServiceInfoNew(productServiceInfos);
        }
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
            productManagerLog.setBussinessStatus("02");//审核状态
            productManagerLog.setStatus("Y");
            productManagerLogMapper.insertProductManagerLog(productManagerLog);

        }
        return count;
    }


    /**
     * 修改base_product_service_info(服务产品项目关联)
     * 
     * @param productServiceInfo base_product_service_info(服务产品项目关联)
     * @return 结果
     */
    @Override
    public int updateProductServiceInfo(ProductServiceInfo productServiceInfo)
    {
        productServiceInfo.setUpdateTime(DateUtils.getNowDate());
        return productServiceInfoMapper.updateProductServiceInfo(productServiceInfo);
    }

    /**
     * 批量删除base_product_service_info(服务产品项目关联)
     * 
     * @param serialNos 需要删除的base_product_service_info(服务产品项目关联)ID
     * @return 结果
     */
    @Override
    public int deleteProductServiceInfoByIds(String[] serialNos)
    {
        return productServiceInfoMapper.deleteProductServiceInfoByIds(serialNos);
    }

    /**
     * 删除base_product_service_info(服务产品项目关联)信息
     * 
     * @param serialNo base_product_service_info(服务产品项目关联)ID
     * @return 结果
     */
    @Override
    public int deleteProductServiceInfoById(String serialNo)
    {
        return productServiceInfoMapper.deleteProductServiceInfoById(serialNo);
    }
}

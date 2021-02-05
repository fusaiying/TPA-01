package com.paic.ehis.system.mapper;

import com.paic.ehis.system.domain.ClaimProduct;
import com.paic.ehis.system.domain.ClaimProductPlanVO;
import com.paic.ehis.system.domain.dto.ClaimProductDTO;
import com.paic.ehis.system.domain.dto.ProcessedProductDTO;

import java.util.List;

/**
 * 产品信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-14
 */
public interface ClaimProductMapper 
{
    /**
     * 查询产品信息
     * 
     * @param riskCode 产品信息ID
     * @return 产品信息
     */
    public ClaimProduct selectClaimProductById(String riskCode);

    /**
     * 查询产品信息列表
     * 
     * @param claimProduct 产品信息
     * @return 产品信息集合
     */
    public List<ClaimProduct> selectClaimProductList(ClaimProduct claimProduct);

    /**
     * 查询产品信息列表
     *
     * @param processedProductDTO 产品信息
     * @return 产品信息集合
     */
    public List<ClaimProduct> selectClaimProductProcessedList(ProcessedProductDTO processedProductDTO);

    /**查询任务改派产品信息列表*/
    public List<ClaimProductDTO> selectClaimProduct(ClaimProductDTO claimProductDTO);

    /**查询任务改派产品信息列表(没有输入数据)*/
    public List<ClaimProductDTO> selectClaimProductNew(ClaimProductDTO claimProductDTO);

    /**
     * 新增产品信息
     * 
     * @param claimProduct 产品信息
     * @return 结果
     */
    public int insertClaimProduct(ClaimProduct claimProduct);

    /**
     * 修改产品信息
     * 
     * @param claimProduct 产品信息
     * @return 结果
     */
    public int updateClaimProduct(ClaimProduct claimProduct);

    /**
     * 重设产品信息
     *
     * @param claimProduct 产品信息
     * @return 结果
     */
    public int updateClaimProductReset(ClaimProduct claimProduct);

    /**
     * 删除产品信息
     * 
     * @param riskCode 产品信息ID
     * @return 结果
     */
    public int deleteClaimProductById(String riskCode);

    /**
     * 批量删除产品信息
     * 
     * @param riskCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimProductByIds(String[] riskCodes);

    /*************************************************/
    /**
     * 公共池-数据查询
     * @param claimProduct
     * @return
     */
    public List<ClaimProduct> selectClaimProductListAndPublicPool(ClaimProduct claimProduct);

    /**
     * 公共池-已处理-数据查询
     * @param claimProduct
     * @return
     */
    public List<ClaimProduct> selectClaimProductListAndPersonalPoolFinish(ClaimProduct claimProduct);

    /**
     *  公共池-待处理-数据查询
     * @param claimProduct
     * @return
     */
    public List<ClaimProduct> selectClaimProductListAndPersonalPoolUnfinished(ClaimProduct claimProduct);


    /************************************************/
   public List<ClaimProductDTO> selectProductQuery(ClaimProductDTO claimProductDTO);


    /**
     * 查询所有福利对象 列表
     *
     * @param riskCode 产品编码
     * @return 福利对象 集合
     */
    public List<ClaimProductPlanVO> getAll(String riskCode);

    /**
     *
     * @param riskCode
     * @return
     */
    public ClaimProduct selectClaimProductByIdOne(String riskCode);

    /**
     * 获取出单公司列表
     * @param riskCode
     * @return
     */
    public List<String> selectCompanyNameList(String riskCode);
}

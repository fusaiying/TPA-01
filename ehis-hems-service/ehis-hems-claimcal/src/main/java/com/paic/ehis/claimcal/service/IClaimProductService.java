package com.paic.ehis.claimcal.service;



import com.paic.ehis.claimcal.domain.ClaimProduct;
import com.paic.ehis.claimcal.domain.ClaimProductInfo;
import com.paic.ehis.claimcal.domain.ClaimProductPlanVO;
import com.paic.ehis.claimcal.domain.dto.ClaimProductDTO;
import com.paic.ehis.claimcal.domain.dto.ProcessedProductDTO;
import com.paic.ehis.claimcal.domain.dto.UpdateClaimProductDTO;

import java.util.List;

/**
 * 产品信息Service接口
 * 
 * @author sino
 * @date 2021-01-14
 */
public interface IClaimProductService 
{
    /**
     * 查询产品信息
     * 
     * @param riskCode 产品信息ID
     * @return 产品信息
     */
    public ClaimProduct selectClaimProductById(String riskCode);

    /**
     * 查询福利对象
     *
     * @param riskCode 产品信息ID
     * @return 产品信息
     */
    public List<ClaimProductPlanVO> selectProductById(String riskCode);

    /**
     * 查询产品信息
     *
     * @param riskCode 产品信息ID
     * @return 产品信息
     */
    public ClaimProductInfo selectClaimProductInfoById(String riskCode);

    /**
     * 查询产品信息列表
     * 
     * @param claimProduct 产品信息
     * @return 产品信息集合
     */
    public List<ClaimProduct> selectClaimProductList(ClaimProduct claimProduct);

    /**
     * 查询产品信息公共池 列表
     *
     * @param claimProduct 产品信息
     * @return 产品信息
     */
    public List<ClaimProduct> selectProductPublicList(ClaimProduct claimProduct);

    /**查询任务改派产品信息列表*/
    public List<ClaimProductDTO> selectClaimProduct(ClaimProductDTO claimProductDTO);

    /**修改任务改派操作人*/
    public void UpdateClaimProductDTO(UpdateClaimProductDTO updateClaimProductDTO);

    /**
     * 查询待处理产品信息 列表
     *
     * @param claimProduct 产品信息
     * @return 产品信息
     */
    public List<ClaimProduct> selectUntreatedProductList(ClaimProduct claimProduct);

    /**
     * 查询已处理产品信息 列表
     *
     * @param processedProductDTO 产品信息
     * @return 产品信息
     */
    public List<ClaimProduct> selectProcessedProductList(ProcessedProductDTO processedProductDTO);

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
     * @param riskCode 产品信息
     * @return 结果
     */
    public int updateClaimProductReset(String riskCode);

    /**
     * 修改产品信息
     *
     * @param riskCodes 产品信息
     * @return 结果
     */
    public int obtainClaimProduct(String[] riskCodes);

    /**
     * 批量删除产品信息
     * 
     * @param riskCodes 需要删除的产品信息ID
     * @return 结果
     */
    public int deleteClaimProductByIds(String[] riskCodes);

    /**
     * 删除产品信息信息
     * 
     * @param riskCode 产品信息ID
     * @return 结果
     */
    public int deleteClaimProductById(String riskCode);

    /*****************************/

    /**
     * 规则审核-公共池
     * @param claimProduct
     * @return
     */
    public List<ClaimProduct> selectClaimProductListAndPublicPool(ClaimProduct claimProduct);

    /**
     * 规则审核-公共池-获取-个人池
     * @param riskCodes
     * @return
     */
    public int updateClaimProductUpdateBy(String[] riskCodes);

    /**
     * 规则审核-个人池-已处理
     * @param claimProduct
     * @return
     */
    public List<ClaimProduct> selectClaimProductListAndPersonalPoolFinish(ClaimProduct claimProduct);

    /**
     * 规则审核-个人池-待处理
     * @param claimProduct
     * @return
     */
    public List<ClaimProduct> selectClaimProductListAndPersonalPoolUnfinished(ClaimProduct claimProduct);




    /*****************************/
//产品查询   工作池
   public List<ClaimProductDTO> selectProductQuery(ClaimProductDTO claimProductDTO);
}

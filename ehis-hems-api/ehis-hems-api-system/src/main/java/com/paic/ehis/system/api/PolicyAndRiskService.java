package com.paic.ehis.system.api;


import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.domain.CompanyRiskPolicyInfo;
import com.paic.ehis.system.api.domain.PolicyAndRiskRelation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName PolicyAndRiskService
 * @Description: TODO   险种、保单关联接口
 * @Author 硠君
 * @Date create in 14:13 2021/1/22
 * @Version 1.0
 **/
@FeignClient(contextId = "PolicyAndRiskService", value = ServiceNameConstants.CLAIM_FLOW_SERVICE)
public interface PolicyAndRiskService {
        /**
         * 通过保单号查询关联险种
         *
         * @param policyAndRiskRelation  险种编码
         * @return 结果
         */
        @PostMapping(value = "/policyRelation/relationList")
        public TableDataInfo getRelationList(@RequestBody PolicyAndRiskRelation policyAndRiskRelation);

        /**
         * 查询出单公司险种保单详情 列表
         *
         * @param policyAndRiskRelation  出单公司编码、险种编码
         * @return 结果
         */
        @PostMapping(value = "/policyRelation/companyPolicyList")
        public TableDataInfo getRelationCompanyList(@RequestBody PolicyAndRiskRelation policyAndRiskRelation);

        /**
         * 查询出单公司险种保单 字段列表
         *
         * @param policyAndRiskRelation  出单公司编码、险种编码
         * @return 结果
         */
        @PostMapping(value = "/policyRelation/validStartDateList")
        public TableDataInfo getValidStartDateList(@RequestBody PolicyAndRiskRelation policyAndRiskRelation);

        /**
         * 跨服务  保单已结算
         *
         * @param policyAndRiskRelation 保单险种关联
         * @return 结果
         */
        @PostMapping(value ="/policyRelation/settledPolicy")
        public AjaxResult settledPolicy(@RequestBody PolicyAndRiskRelation policyAndRiskRelation);

        /**
         * 根据保单号、分单号查询保单信息列表
         *
         * @param policyAndRiskRelation 保单信息
         * @return 保单信息集合
         */
        @PostMapping(value ="/info/policyInfoByPolicyNoList")
        public CompanyRiskPolicyInfo selectPolicyInfoListByPolicyNo(@RequestBody PolicyAndRiskRelation policyAndRiskRelation);

}

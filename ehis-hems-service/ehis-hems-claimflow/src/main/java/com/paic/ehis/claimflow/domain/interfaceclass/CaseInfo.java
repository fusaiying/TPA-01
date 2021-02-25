package com.paic.ehis.claimflow.domain.interfaceclass;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 主体类
 */
@Data
public class CaseInfo extends BaseEntity {
    /** 批次号 */
    private String batchNo;

    /** 报案号 */
    private String rptNo;

    /** 理赔申请原因      TODO:理赔申请原因实体类更换String*/
    private List<String> applicationReason;

    /** 案件来源 */
    private String caseSource;

    /** 案件信息 */

    /** 申请人信息 */

    /** 被保人信息 */

    /** 受理信息 */

    /** 领款人信息 */

    /** 账单信息  List*/

    /** 影像信息 */

}

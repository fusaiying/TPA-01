package com.paic.ehis.claimflow.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * Created by IntelliJ IDEA
 * User: fusaiying-傅赛赢
 *
 * @author: 傅赛赢
 * Date: 2021/2/8
 * subject:
 */
@Data
public class BaseCodeMappingNew extends BaseEntity {
    private static final long serialVersionUID = 1L;
    //编码类型(YYBZ-医院币种)
    private String codeType;
    private String originalCode;
    private String targetCode;

    @Override
    public String toString() {
        return "BaseCodeMappingNew{" +
                "codeType='" + codeType + '\'' +
                ", originalCode='" + originalCode + '\'' +
                ", targetCode='" + targetCode + '\'' +
                '}';
    }
}

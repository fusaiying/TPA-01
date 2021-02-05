package com.paic.ehis.claimcal.domain.dto;

import com.paic.ehis.claimcal.domain.ClaimCase;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA
 * User: fusaiying-傅赛赢
 *
 * @author: 傅赛赢
 * Date: 2021/2/3
 * subject:
 */
@Data
public class ClaimCaseProblemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private ClaimCase claimCase;
    private String problemType;
    private String problemView;
    private String rptNo;

    @Override
    public String toString() {
        return "ClaimCaseProblemDTO{" +
                "claimCase=" + claimCase +
                ", problemType='" + problemType + '\'' +
                ", problemView='" + problemView + '\'' +
                ", rptNo='" + rptNo + '\'' +
                '}';
    }
}

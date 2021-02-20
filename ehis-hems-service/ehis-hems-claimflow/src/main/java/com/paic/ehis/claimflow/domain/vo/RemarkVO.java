package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.claimflow.domain.ClaimCaseRemark;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName RemarkVO
 * @Description: TODO
 * @Author 硠君
 * @Date create in 15:12 2021/2/20
 * @Version 1.0
 **/

@Data
public class RemarkVO implements Serializable {
    private List<ClaimCaseRemark> remarkList;

    private String userName;
}

package com.paic.ehis.claimflow.domain.dto;

import com.paic.ehis.claimflow.domain.ClaimCaseProblem;
import lombok.Data;

import java.util.List;

@Data
public class ProblemTextDTO {
    private List<ClaimCaseProblem> claimCaseProblems;
    /**
     * Y-有未处理得问题件
     */
    private String problemStatus;

    @Override
    public String toString() {
        return "ProblemTextDTO{" +
                "claimCaseProblems=" + claimCaseProblems +
                ", problemStatus='" + problemStatus + '\'' +
                '}';
    }
}

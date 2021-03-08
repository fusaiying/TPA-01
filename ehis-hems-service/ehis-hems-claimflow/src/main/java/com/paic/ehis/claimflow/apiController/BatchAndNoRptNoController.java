package com.paic.ehis.claimflow.apiController;

import com.paic.ehis.claimflow.domain.dto.BatchNoRptNoDTO;
import com.paic.ehis.common.core.enums.PbwOnlineP;
import com.paic.ehis.claimflow.domain.vo.BatchNoAndCaseNo;
import com.paic.ehis.claimflow.domain.vo.BatchNoAndCaseNoVO;
import com.paic.ehis.claimflow.service.IClaimBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pbwApi")
public class BatchAndNoRptNoController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(BatchAndNoRptNoController.class);

    @Autowired
    private IClaimBatchService claimBatchService;

    /**
     * PBW在线理赔请求接口-完成机构交单动作
     *
     * @param batchNoRptNoDTO
     * @return batchNoRptNoVO
     */
    @PostMapping("/getBatchAndNoRptNo")
    public BatchNoAndCaseNoVO getBatchNoRptNo(BatchNoRptNoDTO batchNoRptNoDTO) {
        log.info("PBW在线理赔请求接口调用开始");
        BatchNoAndCaseNoVO batchNoAndCaseNoVO = new BatchNoAndCaseNoVO();
        String batchCount = batchNoRptNoDTO.getBatchCount();
        int i1 = Integer.parseInt(batchCount);
        if (i1 >= 0) {
            BatchNoAndCaseNo batchNoAndCaseNo = claimBatchService.insertClaimBatchPbw(batchNoRptNoDTO);
            batchNoAndCaseNoVO.setBatchNoAndCaseNo(batchNoAndCaseNo);
            batchNoAndCaseNoVO.setMessage(PbwOnlineP.Success.getMessage());
            batchNoAndCaseNoVO.setStatus(PbwOnlineP.Success.getStatus());
        } else {
            batchNoAndCaseNoVO.setMessage(PbwOnlineP.Defeat.getMessage());
            batchNoAndCaseNoVO.setStatus(PbwOnlineP.Defeat.getStatus());
            log.info("PBW在线理赔请求接口调用结束");
        }
        return batchNoAndCaseNoVO;
    }

    //影像件上传接口

}

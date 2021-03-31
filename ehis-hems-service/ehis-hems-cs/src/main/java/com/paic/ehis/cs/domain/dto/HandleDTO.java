package com.paic.ehis.cs.domain.dto;

import com.paic.ehis.cs.domain.AttachmentInfo;
import com.paic.ehis.cs.domain.vo.*;
import lombok.Data;

@Data
public class HandleDTO {
    AcceptVo acceptVo;

    ComplaintDealVo complaintDealVo;

    DemandAcceptVo demandAcceptVo;

    QualityVo qualityVo;

    AttachmentInfo attachmentInfo;

    ComplaintAcceptVo complaintAcceptVo;
}

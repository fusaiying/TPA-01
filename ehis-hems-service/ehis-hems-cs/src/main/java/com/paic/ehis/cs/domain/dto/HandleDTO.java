package com.paic.ehis.cs.domain.dto;

import com.paic.ehis.cs.domain.AttachmentInfo;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.QualityVo;
import lombok.Data;

@Data
public class HandleDTO {
    AcceptVo acceptVo;

    ComplaintDealVo complaintDealVo;

    DemandAcceptVo demandAcceptVo;

    QualityVo qualityVo;

    AttachmentInfo attachmentInfo;
}

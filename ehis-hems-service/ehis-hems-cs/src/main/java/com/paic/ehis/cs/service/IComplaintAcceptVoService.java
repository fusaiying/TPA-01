package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.ComplaintsCascade;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.Level3;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IComplaintAcceptVoService {
    List<ComplaintAcceptVo> selectComplaintAcceptVoList(AcceptDTO acceptDTO);

    List<ComplaintAcceptVo> selectComplaintAcceptVoListOne(AcceptDTO acceptDTO);

    public ComplaintAcceptVo selectComplaintAcceptVo(String workOrderNo);
    /**
     * 投诉新增页面
     * @param complaintAcceptVo
     * @return
     */
   public int insertComplaintInfo(ComplaintAcceptVo complaintAcceptVo);

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    int updateComplaintAcceptVo(ComplaintAcceptVo complaintAcceptVo);

    /**
     * 投诉处理
     * @param complaintDealVo
     * @return
     */
    int complaintHandling(ComplaintDealVo complaintDealVo);
    int complaintSaveHandling(ComplaintDealVo complaintDealVo);

    /**
     * 投诉分类级联关系
     */
    public List<ComplaintsCascade> selectComplaintsCascadeList();
    public List<Level3> selectLevel1();
    public List<Level3> selectLevel2(String parentCode);

    //投诉服务处理页面数据
    public ComplaintDealVo selectServiceProcess(String workOrderNo);

}

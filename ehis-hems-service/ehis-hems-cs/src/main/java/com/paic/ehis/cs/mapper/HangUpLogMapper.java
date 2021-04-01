package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.HangUpLog;

public interface HangUpLogMapper {

    public HangUpLog selectHangUpLogById(String workOrderNo);

    public HangUpLog selectHangUpLogByIdTwo(String workOrderNo);

    public int updateHangUpLog(HangUpLog hangUpLog);

    public int insertHangUpLog(HangUpLog hangUpLog);


}

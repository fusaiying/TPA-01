package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.HmpServConpro;

import java.util.List;

/**
 * 合约 与 服务项目关联信息
 */
public interface HmpServConproMapper {
    /**
     * 新增服务项目
     * @param hmpServConpro
     * @return
     */
    int insertHmpServConpro(List<HmpServConpro> hmpServConpro);

    /**
     * 根据合约名查询服务项目
     * @param contactno
     * @return
     */
    List<HmpServConpro> selectHmpServConproByNo(String contactno);

    /**
     * 根据合约号码  删除合约项目
     * @param contactno
     * @return
     */
    int deleteHmpServConproByNo(String contactno);
}

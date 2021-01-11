package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.HmpHospCharacter;

/**
 * 医疗特色信息Mapper接口
 *
 * @author sino
 * @date 2020-10-28
 */
public interface HmpHospCharacterMapper {
    /**
     * 根据合作机构编码查询医疗特色信息
     * @param servcomno
     * @return
     */
    HmpHospCharacter selectHmpHospCharacterByNo(String servcomno);

    /**
     * 新增医疗特色信息
     * @param hmpHospCharacter
     * @return
     */
    int insertHmpHospCharacter(HmpHospCharacter hmpHospCharacter);

    /**
     * 修改医疗特色信息
     * @param hmpHospCharacter
     * @return
     */
    int updateHmpHospCharacter(HmpHospCharacter hmpHospCharacter);
}

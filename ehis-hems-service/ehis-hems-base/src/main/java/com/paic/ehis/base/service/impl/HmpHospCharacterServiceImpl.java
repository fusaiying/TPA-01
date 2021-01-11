package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.domain.HmpHospCharacter;
import com.paic.ehis.base.mapper.HmpHospCharacterMapper;
import com.paic.ehis.base.service.IHmpHospCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 医疗特色信息Service业务层处理
 *
 * @author sino
 * @date 2020-10-28
 */
@Service
public class HmpHospCharacterServiceImpl implements IHmpHospCharacterService {

    @Autowired
    private HmpHospCharacterMapper hmpHospCharacterMapper;

    /**
     * 根据合作机构编码查询医疗特色信息
     * @param servcomno
     * @return
     */
    @Override
    public HmpHospCharacter selectHmpHospCharacterByNo(String servcomno) {
        return hmpHospCharacterMapper.selectHmpHospCharacterByNo(servcomno);
    }

    /**
     * 新增医疗特色信息
     * @param hmpHospCharacter
     * @return
     */
    @Override
    public int insertHmpHospCharacter(HmpHospCharacter hmpHospCharacter) {
        return hmpHospCharacterMapper.insertHmpHospCharacter(hmpHospCharacter);
    }

    @Override
    public int updateHmpHospCharacter(HmpHospCharacter hmpHospCharacter) {
        return hmpHospCharacterMapper.updateHmpHospCharacter(hmpHospCharacter);
    }
}

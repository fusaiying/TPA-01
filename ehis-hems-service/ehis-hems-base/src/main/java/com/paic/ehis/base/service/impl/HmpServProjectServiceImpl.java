package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.domain.HmpServProject;
import com.paic.ehis.base.mapper.HmpServProjectMapper;
import com.paic.ehis.base.service.IHmpServProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务项目Service业务层处理
 * 
 * @author sino
 * @date 2020-11-09
 */
@Service
public class HmpServProjectServiceImpl implements IHmpServProjectService
{
    @Autowired
    private HmpServProjectMapper hmpServProjectMapper;


    @Override
    public HmpServProject selectHmpServProjectNameByCode(String projectCode) {
        return hmpServProjectMapper.selectHmpServProjectNameByCode(projectCode);
    }

    @Override
    public HmpServProject selectHmpServProjectCodeByName(String projectname) {
        return hmpServProjectMapper.selectHmpServProjectCodeByName(projectname);
    }
}
